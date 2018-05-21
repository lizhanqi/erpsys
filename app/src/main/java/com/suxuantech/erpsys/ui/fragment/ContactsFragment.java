package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.suxuantech.erpsys.ui.activity.base.ContactsActivity;
import com.suxuantech.erpsys.ui.adapter.ContanctsAdaputer;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ContactsFragment extends BaseSupportFragment {
    @BindView(R.id.rl_organization)
    SwipeMenuRecyclerView mRlOrganization;
    @BindView(R.id.ll_fast_entry)
    LinearLayout fastEntry;
    @BindView(R.id.tv_group)
    TextView tvGruop;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.one_Search)
    OneKeyClearAutoCompleteText oneKeyClearAutoCompleteText;
    @BindView(R.id.smar_contancts)
    SmartRefreshLayout smartRefreshLayout;
    private Unbinder unbinder;
    ContanctsAdaputer contanctsAdaputer;

    private boolean isOption,
            searchEntrance,//搜索入口是否显示
            fastEntrance;//快速入口是否显示
    /**
     * 1事业部
     * 2品牌
     * 3.店面
     * 4.部门
     */
    private int type = 4;//根据类型获取不同的页面数据
    private String keyCode;//根据类型获取不同的页面数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        type = getArguments().getInt("type", 4);
        fastEntrance = getArguments().getBoolean("fastEntrance",false);
        if (fastEntrance){
            fastEntry.setVisibility(View.VISIBLE);
        }else {
            fastEntry.setVisibility(View.GONE);
        }
        isOption = getArguments().getBoolean("isOption",false);
        isOption = getArguments().getBoolean("searchEntrance",true);
        keyCode = getArguments().getString("keyCode");
        getContactTreeContrl(type, keyCode);
    }
    private void  statToFragement(Bundle bd){
        ContactsFragment contactsFragment = new ContactsFragment();
        contactsFragment.setArguments(bd);
//        getTargetFragment().getFragmentManager().beginTransaction().add(contactsFragment)
      //  start();
    }
    private void initView() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            getContactTreeContrl(type, keyCode);
        });
        contanctsAdaputer = new ContanctsAdaputer(getActivity());
        mRlOrganization.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlOrganization.setAdapter(contanctsAdaputer);
        contanctsAdaputer.setOnChildClickListener((GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) -> {
            ContactEntity contactEntity = contanctsAdaputer.getContactEntity();
            if (groupPosition == 3) {
                List<StaffSearchEntity.DataBean> staffData = contactEntity.getStaffData();
                if (staffData!=null){
                    Intent intts =new Intent(getActivity(), StaffDetailsActivity.class);
                    Bundle bd =new Bundle();
                    bd.putParcelable("data", staffData.get(childPosition));
                    intts.putExtras(bd);
                    startActivity(intts);
                }
            }else {
                Bundle bundle = new Bundle();
                if (groupPosition==0){
                    List<BusinssunitEntity.DataBean> businssunitData = contactEntity.getBusinssunitData();
                    if (businssunitData!=null){
                        BusinssunitEntity.DataBean dataBean = businssunitData.get(childPosition);
                        bundle.putString("keyCode",dataBean.getId()+"");
                    }
                }else if (groupPosition==1){
                    List<StoreEntity.DataBean> storeData = contactEntity.getStoreData();
                    if (storeData!=null){
                        StoreEntity.DataBean dataBean = storeData.get(childPosition);
                        bundle.putString("keyCode",dataBean.getShop_code()+"");
                    }
                }else if (groupPosition==2){
                    List<DepartmentEntiy.DataBean> departmentData = contactEntity.getDepartmentData();
                    if (departmentData!=null){
                        DepartmentEntiy.DataBean dataBean = departmentData.get(childPosition);
                        bundle.putString("keyCode",dataBean.getId()+"");
                    }
                }
                //这里+2的原因因为查询是1-4直接,但是下表示0,但是同时1是集团查询,所以需要再加一个
                groupPosition+=2;
                bundle.putInt("type", groupPosition);
                bundle.putBoolean("isOption",false);
                statToFragement(bundle);
//                Intent intent = new Intent(getActivity(),ContactsActivity.class);
//                startActivity(intent);
            }
        });
        oneKeyClearAutoCompleteText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getActivity(), StaffSearchActivity.class);
                startActivity(intent);
                return false;
            }
        });
        tvGruop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(),ContactsActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("isOption",false);
                intent.putExtra("keyCode","");
                bundle.putInt("type",1);
                bundle.putBoolean("isOption",false);
                bundle.putString("keyCode","");
                statToFragement(bundle);
                //startActivity(intent);
            }
        });
        tvShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(),ContactsActivity.class);
                intent.putExtra("type", 3);
                intent.putExtra("isOption",false);
                intent.putExtra("keyCode", App.getApplication().getUserInfor().shop_code+"");
                bundle.putInt("type",3);
                bundle.putBoolean("isOption",false);
                bundle.putString("keyCode",App.getApplication().getUserInfor().shop_code+"");
                statToFragement(bundle);
                //startActivity(intent);
            }
        });
    }

    /**
     * 获取联系人的总控
     * 1.事业部
     * 2.品牌
     * 3.店
     * 4.部门
     *
     * @param wicht
     */
    public void getContactTreeContrl(@IntRange(from = 1, to = 4) int wicht, String code) {
        if (wicht == 1) {
            getBusinssunit();
            getContancts(wicht, "");
            getDepartment(wicht, "");
        } else if (wicht == 2) {
            getContancts(wicht, code);
            getStoreByBusinssUnit(code);
            getDepartment(wicht, code);
        } else if (wicht == 3) {
            getDepartment(wicht, code);
            getContancts(wicht, code);
        } else if (wicht == 4) {
            getContancts(wicht, code);
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
        request(0, contancts, searchByCustmor, false, false);
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


