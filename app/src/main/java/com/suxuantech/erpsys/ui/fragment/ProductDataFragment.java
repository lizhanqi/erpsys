package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bigkoo.alertview.AlertView;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.CustomerProductEntity;
import com.suxuantech.erpsys.entity.PackageEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.entity.SimpleEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.adapter.ProductGroupAdaputer;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 产品资料
 */
public class ProductDataFragment extends BaseSupportFragment {
    @BindView(R.id.recycler_one_product)
    SwipeMenuRecyclerView mRecyclerOneProduct;
    @BindView(R.id.recycler_two_product)
    SwipeMenuRecyclerView mRecyclerTwoProduct;
    @BindView(R.id.cb_one_all)
    CheckBox mCbOneAll;
    @BindView(R.id.cb_two_all)
    CheckBox mCbTwoAll;
    @BindView(R.id.cb_all)
    CheckBox mCbAll;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.rl_delete)
    RelativeLayout mRlDelete;
    @BindView(R.id.stv_one_package)
    SuperTextView mStvOnePackage;
    private Unbinder unbinder;
    private ProductGroupAdaputer productGroupAdaputer2;
    private ProductGroupAdaputer productGroupAdaputer;

    public ProductDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        // ((OrderDetailActivity) getActivity()).setUseDefinedNavRightText("1111");
        useEventBus();
        initView(view);
        getProduct();
        return view;
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

    public void addPackage(String url) {
        //请求实体
        JavaBeanRequest<SimpleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SimpleEntity>(url, RequestMethod.POST, SimpleEntity.class);
        HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
            @Override
            public void onSucceed(int what, Response<SimpleEntity> response) {
                if (response.get().isOK()){
                    ToastUtils.showShort("添加套系成功");
                    getProduct();
                }
            }
            @Override
            public void onFailed(int what, Response<SimpleEntity> response) {
            }
        };
        request(2, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setData(CustomerProductEntity.DataBean dataBean) {
        productGroupAdaputer = new ProductGroupAdaputer(getActivity(), true, dataBean);
        mRecyclerOneProduct.setAdapter(productGroupAdaputer);
        //recycleview和其他view滑动冲突可以禁用
        mRecyclerOneProduct.setLayoutManager(new LinearLayoutManager(getActivity()) {
                                                 @Override
                                                 public boolean canScrollVertically() {
                                                     // 直接禁止垂直滑动
                                                     return false;
                                                 }
                                             }
        );
        productGroupAdaputer2 = new ProductGroupAdaputer(getActivity(), false, dataBean);
        mRecyclerTwoProduct.setLayoutManager(new LinearLayoutManager(getActivity()) {
                                                 @Override
                                                 public boolean canScrollVertically() {
                                                     // 直接禁止垂直滑动
                                                     return false;
                                                 }
                                             }
        );
        mRecyclerTwoProduct.setAdapter(productGroupAdaputer2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    boolean isShowCheckBox;

    public void change() {
        addPackageOrProductWindow();
        isShowCheckBox = !isShowCheckBox;
        if (isShowCheckBox) {
            mRlDelete.setVisibility(View.VISIBLE);
            mCbOneAll.setVisibility(View.VISIBLE);
            mCbTwoAll.setVisibility(View.VISIBLE);
        } else {
            mRlDelete.setVisibility(View.GONE);
            mCbOneAll.setVisibility(View.GONE);
            mCbTwoAll.setVisibility(View.GONE);
        }
        productGroupAdaputer.setShowCheckBox(isShowCheckBox);
        productGroupAdaputer2.setShowCheckBox(isShowCheckBox);
    }

    private void initView(View view) {
        mCbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCbOneAll.setChecked(isChecked);
                mCbTwoAll.setChecked(isChecked);
            }
        });
        mCbOneAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productGroupAdaputer.setCheckAll(isChecked);
                productGroupAdaputer.notifyDataSetChanged();
            }
        });
        mCbTwoAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productGroupAdaputer2.setCheckAll(isChecked);
                productGroupAdaputer2.notifyDataSetChanged();
            }
        });

    }

    public void addPackageOrProductWindow() {
        AlertView alertView = new AlertView(null, null, null, null, null, getContext(), AlertView.Style.ACTIONSHEET, null);
        View views = getLayoutInflater().inflate(R.layout.pop_package_product_addbutton, null);
        Button bn = views.findViewById(R.id.btn_add_package);
        bn.setClickable(true);
        alertView.addExtView(views);
        alertView.setCancelable(true);
        alertView.setContentContainerMargins(0, 0, 0, 0);
        if (ScreenUtils.checkDeviceHasNavigationBar(getContext())) {
            alertView.setRootViewMarginBootom(ScreenUtils.getNavigationBarHeight(getContext()));
            alertView.show();
        }
    }

    public void getProduct() {
        String orderId = getArguments().getString("orderId");
        String url = Contact.getFullUrl(Contact.CUSTOMER_PRODUCT, Contact.TOKEN, orderId, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<CustomerProductEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerProductEntity>(url, RequestMethod.POST, CustomerProductEntity.class);
        HttpListener<CustomerProductEntity> searchByCustmor = new HttpListener<CustomerProductEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerProductEntity> response) {

                if (response.get().isOK()) {
                    CustomerProductEntity.DataBean dataBean = response.get().getData();
                    if (dataBean != null) {
                        List<CustomerProductEntity.DataBean.YxBean.YxpBean> yxp = dataBean.getYx().getYxp();
                        List<CustomerProductEntity.DataBean.YxBean.YxfBean> yxf = dataBean.getYx().getYxf();
                        mStvOnePackage.setLeftString(yxp.get(0).getConsumption_name());
                        mStvOnePackage.setRightString("¥:" + yxp.get(0).getPrice());
                        mStvOnePackage.setRightTextColor(getResources().getColor(R.color.litte_red));
                        setData(dataBean);
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<CustomerProductEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
}
