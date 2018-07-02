package com.suxuantech.erpsys.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.CustomerProductEntity;
import com.suxuantech.erpsys.entity.DeleteCheckEntity;
import com.suxuantech.erpsys.entity.PackageEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.entity.SimpleEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.adapter.ProductGroupAdaputer;
import com.suxuantech.erpsys.ui.widget.ErrorView;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 产品资料
 */
public class ProductDataFragment extends BaseSupportFragment {
    @BindView(R.id.cb_all_prouduct)
    CheckBox mCbAllProuduct;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.rl_delete)
    RelativeLayout mRlDelete;
    @BindView(R.id.cb_one_all)
    CheckBox mCbOneAll;
    @BindView(R.id.stv_one_package)
    SuperTextView mStvOnePackage;
    @BindView(R.id.recycler_one_product)
    RecyclerView mRecyclerOneProduct;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.ll_content)
    LinearLayout llContent;
    private Unbinder unbinder;
    private ProductGroupAdaputer productGroupAdaputer;
    /**
     * order_type" : 6, // 7==已完成
     * "blankoutannal" : 0,  //1 == 作废
     * "reservelock" : 0, //一销 1锁定,2没锁
     * "addlock" : 0  //二销 1锁定,2没锁
     */
    private List<DeleteCheckEntity.DataBean> productPermission;
    private CustomerProductEntity.DataBean dataBean;
    ErrorView errorView;
    /**
     * 一销或者二销任意一项没锁定
     *
     * @return
     */
    public boolean canAdd() {
        return twoProductCanEdit() || oneProductCanEdit();
    }

    public boolean hasPackage() {
        if (dataBean != null && dataBean.getYx() != null && dataBean.getYx().getYxp() != null) {
            return true;
        }
        return false;
    }

    public boolean oneProductCanEdit() {
        if (productPermission != null && productPermission.get(0) != null &&
                productPermission.get(0).getOrder_type() != 7 && productPermission.get(0).getBlankoutannal() != 1 &&
                productPermission.get(0).getReservelock() != 1) {
            return true;
        }
        return false;
    }

    public boolean twoProductCanEdit() {
        if (productPermission != null && productPermission.get(0) != null &&
                productPermission.get(0).getOrder_type() != 7 && productPermission.get(0).getBlankoutannal() != 1 &&
                productPermission.get(0).getAddlock() != 1) {
            return true;
        }
        return false;
    }

    public ProductDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_data, container, false);
        errorView = new ErrorView(getContext());
        unbinder = ButterKnife.bind(this, view);
        useEventBus();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onCheckedChange();
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (isShowCheckBox) {
                editProduct();
            }
            deleteCheck();
            getProduct();
        });
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseMsg msg) {
        OptionHelp.UrlTag urlTag = msg.getUrlTag();
        switch (urlTag) {
            case PACKAGE:
                PackageEntity.DataBean packageChecked = (PackageEntity.DataBean) msg.getSingleChecked();
                String orderId = getArguments().getString("orderId");
                SearchOrderEntity.DataBean data = getArguments().getParcelable("data");
                String url = Contact.getFullUrl(Contact.ADD_PACKAGE, Contact.TOKEN, orderId, data.getCustomerid(),
                        packageChecked.getPackage_name(), packageChecked.getId(), App.getApplication().getUserInfor().getShop_code(),
                        App.getApplication().getUserInfor().getShop_name(), packageChecked.getPackage_price(), 0, App.getApplication().getUserInfor().getBrandclass_id());
                addPackage(url);
                break;
        }
    }

    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String msg) {
        if (msg.equals("pop")) {
            addPackageOrProductWindow();
        } else if (msg.equals("freshProduct")) {
            smartRefreshLayout.autoRefresh();
        }
    }

    /**
     * 添加包套
     *
     * @param url
     */
    public void addPackage(String url) {
        //请求实体
        JavaBeanRequest<SimpleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SimpleEntity>(url, RequestMethod.POST, SimpleEntity.class);
        HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
            @Override
            public void onSucceed(int what, Response<SimpleEntity> response) {
                if (response.get().isOK()) {
                    ToastUtils.snackbarShort("添加套系成功");
                    smartRefreshLayout.autoRefresh();
                } else {
                    String msg = StringUtils.safetyString(response.get().getCode()) + StringUtils.safetyString(response.get().getMsg());
                    AlertDialog.newBuilder(getActivity())
                            .setMessage(msg)
                            .setPositiveButton("确定", (DialogInterface var1, int var2) -> {
                                    }
                            ).show();

                }
            }

            @Override
            public void onFailed(int what, Response<SimpleEntity> response) {
                smartRefreshLayout.finishRefresh(false);
            }
        };
        request(2, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }


    public void setData(CustomerProductEntity.DataBean dataBean) {
        if (dataBean.getEx() == null && dataBean.getYx() == null) {
            smartRefreshLayout.setRefreshContent(errorView);
            return;
        } else {
            smartRefreshLayout.setRefreshContent(llContent);
        }
        productGroupAdaputer = new ProductGroupAdaputer(getActivity(), true, dataBean);
        mRecyclerOneProduct.setAdapter(productGroupAdaputer);
        //recycleview和其他view滑动冲突可以禁用
        mRecyclerOneProduct.setLayoutManager(new LinearLayoutManager(getActivity()) {
                                                 @Override
                                                 public boolean canScrollVertically() {
                                                     // 直接禁止垂直滑动
                                                     // return false;
                                                     return true;
                                                 }
                                             }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    boolean isShowCheckBox;

    /**
     * 编辑产品
     */
    public void editProduct() {
        isShowCheckBox = !isShowCheckBox;
        if (isShowCheckBox) {
            mRlDelete.setVisibility(View.VISIBLE);
            mCbOneAll.setVisibility(View.VISIBLE);
            if (!twoProductCanEdit() || !oneProductCanEdit()) {
                mCbOneAll.setEnabled(false);
            }
            EventBus.getDefault().post("89");
        } else {
            mRlDelete.setVisibility(View.GONE);
            mCbOneAll.setVisibility(View.GONE);
            EventBus.getDefault().post("88");
        }
        productGroupAdaputer.setShowCheckBox(isShowCheckBox, oneProductCanEdit(), twoProductCanEdit());
    }

    private void onCheckedChange() {
        mCbAllProuduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productGroupAdaputer.setCheckAll(isChecked, !mCbOneAll.isChecked(), oneProductCanEdit(), twoProductCanEdit());
            }
        });
        //包套
        mCbOneAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCbAllProuduct.isChecked()) {
                    productGroupAdaputer.setCheckAll(isChecked, !mCbOneAll.isChecked(), oneProductCanEdit(), twoProductCanEdit());
                } else {
                    mCbAllProuduct.setChecked(isChecked);
                }
                mCbAllProuduct.setEnabled(!isChecked);
            }
        });
    }

    BottomSheetDialog dialog;

    public void addPackageOrProductWindow() {
        View views = getLayoutInflater().inflate(R.layout.pop_package_product_addbutton, null);
        if (dialog == null) {
            dialog = new BottomSheetDialog(getActivity());
            dialog.setContentView(views);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
//        AlertView alertView = new AlertView(null, null, null, null, null, getContext(), AlertView.Style.ACTIONSHEET, null);
//        Button bn = views.findViewById(R.id.btn_add_package);
//        bn.setClickable(true);
//        alertView.addExtView(views);
//        alertView.setCancelable(true);
//        alertView.setContentContainerMargins(0, 0, 0, 0);
//        alertView.show();
    }

    /**
     * 获得客户产品
     */
    public void getProduct() {
        String orderId = getArguments().getString("orderId");
        String url = Contact.getFullUrl(Contact.CUSTOMER_PRODUCT, Contact.TOKEN, orderId, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<CustomerProductEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerProductEntity>(url, RequestMethod.POST, CustomerProductEntity.class);
        HttpListener<CustomerProductEntity> searchByCustmor = new HttpListener<CustomerProductEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerProductEntity> response) {
                dataBean = response.get().getData();
                smartRefreshLayout.finishRefresh();
                if (response.get().isOK()) {
                    if (dataBean != null) {
                        List<CustomerProductEntity.DataBean.YxBean.YxpBean> yxp = dataBean.getYx().getYxp();
                        mStvOnePackage.setVisibility(View.VISIBLE);
                        mStvOnePackage.setLeftString(yxp.get(0).getConsumption_name());
                        mStvOnePackage.setRightString("¥:" + yxp.get(0).getPrice());
                        mStvOnePackage.setRightTextColor(getResources().getColor(R.color.litte_red));
                    } else {
                        mStvOnePackage.setVisibility(View.GONE);
                        EventBus.getDefault().post("removeEdit");
                    }
                    EventBus.getDefault().post("80");
                } else {
                    mStvOnePackage.setVisibility(View.GONE);
                    EventBus.getDefault().post("removeEdit");
                }
                setData(dataBean);
            }

            @Override
            public void onFailed(int what, Response<CustomerProductEntity> response) {
                smartRefreshLayout.finishRefresh(false);
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 删除产品验证(验证包套是否锁定,是否完成,是否作废)
     */
    private void deleteCheck() {
        String orderId = getArguments().getString("orderId");
        String url = Contact.getFullUrl(Contact.DELETE_CHEKE, Contact.TOKEN, orderId, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<DeleteCheckEntity> deleteCheckEntityJavaBeanRequest = new JavaBeanRequest<DeleteCheckEntity>(url, DeleteCheckEntity.class);
        HttpListener<DeleteCheckEntity> searchByCustmor = new HttpListener<DeleteCheckEntity>() {
            @Override
            public void onSucceed(int what, Response<DeleteCheckEntity> response) {
                productPermission = response.get().getData();
                EventBus.getDefault().post("addButton");
            }

            @Override
            public void onFailed(int what, Response<DeleteCheckEntity> response) {

            }
        };
        request(0, deleteCheckEntityJavaBeanRequest, searchByCustmor, false, false);
    }

    @Subscribe
    @MainThread
    public void onEventMainThread(String add) {
        if (add.equals("edit")) {
            editProduct();
        } else if (add.equals("checkAll")) {
            mCbAllProuduct.setOnCheckedChangeListener(null);
            mCbAllProuduct.setChecked(true);
            onCheckedChange();
        } else if (add.equals("checkNone")) {
            mCbAllProuduct.setOnCheckedChangeListener(null);
            mCbAllProuduct.setChecked(false);
            onCheckedChange();
        }
    }

    @OnClick({R.id.tv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_delete:
                if (App.getApplication().hasPermission("A6")) {
                    deletProduc();
                } else {
                    ToastUtils.snackbarShort("无权限删除!", "确定");
                }
                break;
        }
    }

    /**
     * 删除产品,或者包套的判断
     */
    public void deletProduc() {
        String orderId = getArguments().getString("orderId");
        if (mCbOneAll.isChecked()) {
            if (canAdd()) {
                String fullUrl = Contact.getFullUrl(Contact.DELETE_PACKAGE, Contact.TOKEN, orderId,
                        App.getApplication().getUserInfor().getShop_code(), App.getApplication().getUserInfor().getBrandclass_id());
                delete(fullUrl, true);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            CustomerProductEntity.DataBean dbs = productGroupAdaputer.getDataBean();
            if (dataBean != null) {
                CustomerProductEntity.DataBean.YxBean yx = dbs.getYx();
                CustomerProductEntity.DataBean.ExBean ex = dbs.getEx();
                if (yx != null) {
                    List<CustomerProductEntity.DataBean.YxBean.YxfBean> yxf = yx.getYxf();
                    if (yxf != null) {
                        for (CustomerProductEntity.DataBean.YxBean.YxfBean d : yxf) {
                            if (d.isChecked()) {
                                sb.append(d.getId());
                                sb.append(";");
                            }
                        }
                    }
                }
                if (ex != null && ex.getExf() != null) {
                    List<CustomerProductEntity.DataBean.ExBean.ExfBean> exf = ex.getExf();
                    for (CustomerProductEntity.DataBean.ExBean.ExfBean d : exf) {
                        if (d.isChecked()) {
                            sb.append(d.getId());
                            sb.append(";");
                        }
                    }
                }
            }
            if (sb.length() > 1) {
                sb = sb.deleteCharAt(sb.lastIndexOf(";"));
            }
            if (StringUtils.empty(sb.toString()) || sb.toString().startsWith(";")) {
                return;
            }
            String cid = sb.toString();
            String fullUrl = Contact.getFullUrl(Contact.DELETE_PRODUCT, Contact.TOKEN, orderId, cid,
                    App.getApplication().getUserInfor().getShop_code(), App.getApplication().getUserInfor().getBrandclass_id());
            delete(fullUrl, false);
        }
    }

    /**
     * 删除产品,或者包套
     *
     * @param url
     * @param packageDelete
     */
    public void delete(String url, boolean packageDelete) {
        JavaBeanRequest<SimpleEntity> delete = new JavaBeanRequest<SimpleEntity>(url, SimpleEntity.class);
        delete.setMultipartFormEnable(true);
        HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
            @Override
            public void onSucceed(int what, Response<SimpleEntity> response) {
                smartRefreshLayout.autoRefresh();
                if (packageDelete) {
                    mCbOneAll.setChecked(false);
                    EventBus.getDefault().post("removeAllMenu");
                }
            }

            @Override
            public void onFailed(int what, Response<SimpleEntity> response) {
            }
        };
        request(0, delete, searchByCustmor, false, false);
    }
}
