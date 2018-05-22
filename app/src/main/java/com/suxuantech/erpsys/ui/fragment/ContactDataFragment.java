package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BusinssunitEntity;
import com.suxuantech.erpsys.entity.ContactEntity;
import com.suxuantech.erpsys.entity.DepartmentEntiy;
import com.suxuantech.erpsys.entity.StaffSearchEntity;
import com.suxuantech.erpsys.entity.StoreEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.StaffDetailsActivity;
import com.suxuantech.erpsys.ui.activity.StaffSearchActivity;
import com.suxuantech.erpsys.ui.adapter.ContanctsAdaputer;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ContactDataFragment extends BaseSupportFragment {
    @BindView(R.id.rl_organization)
    SwipeMenuRecyclerView mRlOrganization;
    @BindView(R.id.rv_navigation)
    RecyclerView navigationRecyclerView;
    @BindView(R.id.ll_fast_entry)
    LinearLayout fastEntry;
    @BindView(R.id.ll_data)
    LinearLayout mLlData;

    @BindView(R.id.tv_group)
    TextView tvGruop;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.tv_department)
    TextView mTvDepartment;
    @BindView(R.id.one_Search)
    OneKeyClearAutoCompleteText oneKeyClearAutoCompleteText;
    @BindView(R.id.smar_contancts)
    SmartRefreshLayout smartRefreshLayout;
    private Unbinder unbinder;
    ContanctsAdaputer contanctsAdaputer;
    private boolean isOption,
            searchEntrance,//搜索入口是否显示
            fastEntrance,//快速入口是否显示
            showNavigation,//是否显示导航
            showDepartmentName;//是否显示联系人上面的部门名称
    /**
     * 1事业部
     * 2品牌
     * 3.店面
     * 4.部门
     */
    private int type = 4;//根据类型获取不同的页面数据
    private String keyCode;//根据类型获取不同的页面数据
    private String homeDepartmentName;//首页的联系人顶部名称
    private ArrayList navigation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type", 4);
        navigation = getArguments().getStringArrayList("navigation");
        showDepartmentName = getArguments().getBoolean("showDepartmentName", false);
        homeDepartmentName = getArguments().getString("homeDepartmentName","");
        showNavigation = getArguments().getBoolean("showNavigation", true);
        fastEntrance = getArguments().getBoolean("fastEntrance", false);
        isOption = getArguments().getBoolean("isOption", false);
        searchEntrance = getArguments().getBoolean("searchEntrance", true);
        keyCode = getArguments().getString("keyCode");
        initView();
        getContactTreeContrl(type, keyCode);
    }
    private void statToFragement(Bundle bd, ArrayList navigation) {
        bd.putBoolean("searchEntrance", searchEntrance);
        bd.putStringArrayList("navigation", navigation);
        ContactDataFragment contactsFragment = new ContactDataFragment();
        contactsFragment.setArguments(bd);
        start(contactsFragment);
    }
    private void initView() {
        if (fastEntrance) {
            fastEntry.setVisibility(View.VISIBLE);
        } else {
            fastEntry.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlData.getLayoutParams();
            layoutParams.setMargins(0,0,0,0);
        }
        if (searchEntrance) {
            oneKeyClearAutoCompleteText.setVisibility(View.VISIBLE);
        } else {
            oneKeyClearAutoCompleteText.setVisibility(View.GONE);
        }
        if (StringUtils.empty(App.getApplication().getUserInfor().shop_code)) {
            tvShop.setVisibility(View.GONE);
        } else {
            tvShop.setVisibility(View.VISIBLE);
            tvShop.setText(App.getApplication().getUserInfor().shop_name);
        }
        if (!showDepartmentName||StringUtils.empty(homeDepartmentName)) {
            mTvDepartment.setVisibility(View.GONE);
        } else {
            mTvDepartment.setVisibility(View.VISIBLE);
            mTvDepartment.setText(homeDepartmentName);
        }
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            getContactTreeContrl(type, keyCode);
        });
        //很神奇,这里如果不进行复制设置返回按钮出栈的时候,导航有问题
        ArrayList<String> strings = new ArrayList<>();
        if (navigation != null) {
            strings.addAll(navigation);
        }
        QuickAdapter<String> stringQuickAdapter = new QuickAdapter<String>(R.layout.item_navigation_contancts, strings) {
            @Override
            protected void convert(com.chad.library.adapter.base.BaseViewHolder helper, String item) {
                TextView view = helper.getView(R.id.tv_nav_contanct);
                int i = navigation.lastIndexOf(item);
                if (i != navigation.size() - 1) {
                    view.setTextColor(getResources().getColor(R.color.themeColor));
                    view.setText(item + "->");
                } else {
                    view.setText(item);
                }
            }
        };
        if (showNavigation) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            navigationRecyclerView.setLayoutManager(linearLayoutManager);
            navigationRecyclerView.setAdapter(stringQuickAdapter);
            if (navigation != null) {
                linearLayoutManager.scrollToPosition(navigation.size() - 1);
            }
        }
        stringQuickAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            if (position < navigation.size() - 1) {
                int numberP = (navigation.size() - 1) - position;
                for (int i = 0; i < numberP; i++) {
                    pop();
                    navigation.remove(navigation.size() - 1);
                }
            }
        });
        contanctsAdaputer = new ContanctsAdaputer(getActivity());
        mRlOrganization.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlOrganization.setAdapter(contanctsAdaputer);
        contanctsAdaputer.setOnChildClickListener((GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) -> {
            ContactEntity contactEntity = contanctsAdaputer.getContactEntity();
            if (groupPosition == 3) {
                List<StaffSearchEntity.DataBean> staffData = contactEntity.getStaffData();
                if (staffData != null) {
                    Intent intts = new Intent(getActivity(), StaffDetailsActivity.class);
                    Bundle bd = new Bundle();
                    bd.putParcelable("data", staffData.get(childPosition));
                    intts.putExtras(bd);
                    startActivity(intts);
                }
            } else {
                Bundle bundle = new Bundle();
                if (groupPosition == 0) {
                    List<BusinssunitEntity.DataBean> businssunitData = contactEntity.getBusinssunitData();
                    if (businssunitData != null) {
                        BusinssunitEntity.DataBean dataBean = businssunitData.get(childPosition);
                        bundle.putString("keyCode", dataBean.getId() + "");
                        navigation.add(dataBean.getBrandclass());
                    }
                } else if (groupPosition == 1) {
                    List<StoreEntity.DataBean> storeData = contactEntity.getStoreData();
                    if (storeData != null) {
                        StoreEntity.DataBean dataBean = storeData.get(childPosition);
                        bundle.putString("keyCode", dataBean.getShop_code() + "");
                        navigation.add(dataBean.getShop_name());
                    }
                } else if (groupPosition == 2) {
                    List<DepartmentEntiy.DataBean> departmentData = contactEntity.getDepartmentData();
                    if (departmentData != null) {
                        DepartmentEntiy.DataBean dataBean = departmentData.get(childPosition);
                        bundle.putString("keyCode", dataBean.getId() + "");
                        navigation.add(dataBean.getDepartment_name());
                    }
                }
                //这里+2的原因因为查询是1-4直接,但是下表示0,但是同时1是集团查询,所以需要再加一个
                groupPosition += 2;
                bundle.putInt("type", groupPosition);
                bundle.putBoolean("isOption", isOption);
                bundle.putBoolean("fastEntrance", fastEntrance);
                statToFragement(bundle, navigation);
            }
        });
        oneKeyClearAutoCompleteText.setFocusable(false);
        oneKeyClearAutoCompleteText.setFocusableInTouchMode(false);
        oneKeyClearAutoCompleteText.setOnClickListener(it -> {
            Intent intent = new Intent(getActivity(), StaffSearchActivity.class);
            intent.putExtra("option", isOption);
            startActivity(intent);
        });
        tvGruop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navigation == null) {
                    navigation = new ArrayList();
                }
                navigation.clear();
                navigation.add("通讯录");
                navigation.add("集团");
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putBoolean("fastEntrance", false);
                bundle.putBoolean("isOption", isOption);
                bundle.putString("keyCode", "");
                bundle.putBoolean("showNavigation", true);
                statToFragement(bundle, navigation);
            }
        });

        tvShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navigation == null) {
                    navigation = new ArrayList();
                }
                navigation.clear();
                navigation.add("通讯录");
                navigation.add(App.getApplication().getUserInfor().shop_name);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 3);
                bundle.putBoolean("isOption", isOption);
                bundle.putBoolean("showNavigation", true);
                bundle.putBoolean("fastEntrance", false);
                bundle.putString("keyCode", App.getApplication().getUserInfor().shop_code + "");
                statToFragement(bundle, navigation);
            }
        });
    }

    /**
     * 获取联系人的总控
     * 1.集团下的品牌,部门,联系人
     * 2.品牌下的店面,部门,联系人
     * 3.店下的部门.以及联系人
     * 4.部门下的联系人
     * 5 仅店面下面的联系人
     * 6.仅仅品牌下的联系人
     * 7.仅集团下的联系人
     *
     * @param wicht
     */
    public void getContactTreeContrl(@IntRange(from = 1, to = 7) int wicht, String code) {
        if (wicht == 1) {
            getBusinssunit();
            getContancts(1, "");
            getDepartment(1, "");
        } else if (wicht == 2) {
            getContancts(2, code);
            getStoreByBusinssUnit(code);
            getDepartment(2, code);
        } else if (wicht == 3) {
            getDepartment(3, code);
            getContancts(3, code);
        } else if (wicht == 4) {
            getContancts(4, code);
        } else if (wicht == 5) {
            getContancts(3, code);
        } else if (wicht == 6) {
            getContancts(2, code);
        } else if (wicht == 7) {
            getContancts(1, "");
        }
    }

    /**
     * 获取集团下的事业部
     */
    public void getBusinssunit() {
        String url = Contact.getFullUrl(Contact.BUSINSSUNIT, Contact.PHP_PREFIX);
        JavaBeanRequest<BusinssunitEntity> businssunit = new JavaBeanRequest<BusinssunitEntity>(url, BusinssunitEntity.class);
        HttpListener<BusinssunitEntity> searchByCustmor = new HttpListener<BusinssunitEntity>() {
            @Override
            public void onSucceed(int what, Response<BusinssunitEntity> response) {
                smartRefreshLayout.finishRefresh();
                if (response.get().isOK()) {
                    contanctsAdaputer.getContactEntity().setBusinssunitData(response.get().getData());
                    contanctsAdaputer.changeDataSet();
                }

            }

            @Override
            public void onFailed(int what, Response<BusinssunitEntity> response) {
                smartRefreshLayout.finishRefresh();
            }
        };
        request(0, businssunit, searchByCustmor, false, false);
    }

    /**
     * 获取通讯录根据
     * (grade_type:部门所属：1集团，2品牌，3店面，4部门)
     * (1:’’,2:brandclass_id,3:shop_code，4:department_id)
     *
     * @param type 类型
     * @param Key  类型关键词
     */
    public void getContancts(@IntRange(from = 1, to = 4) int type, @NonNull String Key) {
        String url = Contact.getFullUrl(Contact.CONTACTS, Contact.PHP_PREFIX);
        JavaBeanRequest<StaffSearchEntity> contancts = new JavaBeanRequest<StaffSearchEntity>(url, StaffSearchEntity.class);
        contancts.add("grade_type", type);
        if (type == 2) {
            contancts.add("brandclass_id", Key);
        } else if (type == 3) {
            contancts.add("shop_code", Key);
        } else if (type == 4) {
            contancts.add("department_id", Key);
        }
        HttpListener<StaffSearchEntity> searchByCustmor = new HttpListener<StaffSearchEntity>() {
            @Override
            public void onSucceed(int what, Response<StaffSearchEntity> response) {
                smartRefreshLayout.finishRefresh();
                if (response.get().isOK()) {
                    contanctsAdaputer.getContactEntity().setStaffData(response.get().getData());
                    contanctsAdaputer.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(int what, Response<StaffSearchEntity> response) {
                smartRefreshLayout.finishRefresh();
            }
        };
        requestNOError(0, contancts, searchByCustmor, false, false);
    }

    /**
     * 获取部门
     * (grade_type:部门所属：1集团，2品牌，3店面，4部门)
     * (1:’’,2:brandclass_id,3:shop_code，4:department_id)
     *
     * @param type 类型
     * @param Key  类型关键词
     */
    public void getDepartment(@IntRange(from = 1, to = 4) int type, @NonNull String Key) {
        String url = Contact.getFullUrl(Contact.DEPARTMENT, Contact.PHP_PREFIX);
        JavaBeanRequest<DepartmentEntiy> department = new JavaBeanRequest<DepartmentEntiy>(url, DepartmentEntiy.class);
        department.add("grade_type", type);
        if (type == 2) {
            department.add("brandclass_id", Key);
        } else if (type == 3) {
            department.add("shop_code", Key);
        } else if (type == 4) {
            department.add("department_id", Key);
        }
        HttpListener<DepartmentEntiy> searchByCustmor = new HttpListener<DepartmentEntiy>() {
            @Override
            public void onSucceed(int what, Response<DepartmentEntiy> response) {
                smartRefreshLayout.finishRefresh();
                if (response.get().isOK()) {
                    contanctsAdaputer.getContactEntity().setDepartmentData(response.get().getData());
                    contanctsAdaputer.changeDataSet();
                }
            }

            @Override
            public void onFailed(int what, Response<DepartmentEntiy> response) {
                smartRefreshLayout.finishRefresh();
            }
        };
        request(0, department, searchByCustmor, false, false);
    }

    /**
     * 根据事业部获实体店面以及营销店
     */
    public void getStoreByBusinssUnit(@NonNull String brandclass_id) {
        String url = Contact.getFullUrl(Contact.STORE_BY_BUSINSSUNIT, Contact.PHP_PREFIX);
        JavaBeanRequest<StoreEntity> store = new JavaBeanRequest<StoreEntity>(url, StoreEntity.class);
        store.add("brandclass_id", brandclass_id);
        HttpListener<StoreEntity> searchByCustmor = new HttpListener<StoreEntity>() {
            @Override
            public void onSucceed(int what, Response<StoreEntity> response) {
                smartRefreshLayout.finishRefresh();
                if (response.get().isOK()) {
                    contanctsAdaputer.getContactEntity().setStoreData(response.get().getData());
                    contanctsAdaputer.changeDataSet();
                }
            }

            @Override
            public void onFailed(int what, Response<StoreEntity> response) {
                smartRefreshLayout.finishRefresh();
            }
        };
        requestNOError(0, store, searchByCustmor, false, false);
    }
}


