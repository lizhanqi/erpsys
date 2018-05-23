package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anye.greendao.gen.ContanctsFastEntranceEntityDao;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BusinssunitEntity;
import com.suxuantech.erpsys.entity.ContactEntity;
import com.suxuantech.erpsys.entity.ContanctsFastEntranceEntity;
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
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.greendao.query.QueryBuilder;

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
    @BindView(R.id.wmrv_quickentry)
    SwipeMenuRecyclerView quickentryRecycleView;
    @BindView(R.id.ll_fast_entry)
    LinearLayout fastEntry;
    @BindView(R.id.ll_data)
    LinearLayout mLlData;
    @BindView(R.id.tv_group)
    TextView tvGruop;
    @BindView(R.id.tv_department)
    TextView mTvDepartment;
    @BindView(R.id.one_Search)
    OneKeyClearAutoCompleteText oneKeyClearAutoCompleteText;
    @BindView(R.id.smar_contancts)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.v_line)
    View line;
    private Unbinder unbinder;
    ContanctsAdaputer contanctsAdaputer;
    public static final int GROUP_TYPE = 1, BUSINESS_UNIT_TYPE = 2, STORE_TYPE = 3, DEPARTMENT_TYPE = 4;
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
    private int type = DEPARTMENT_TYPE;//根据类型获取不同的页面数据
    private String keyCode;//根据类型获取不同的页面数据
    private String homeDepartmentName;//首页的联系人顶部名称
    private ArrayList navigation;
    private ContanctsFastEntranceEntityDao contanctsFastEntranceEntityDao;
    public static String FRESH_FAST_ENTRANCE = "freshFast";
    QuickAdapter<ContanctsFastEntranceEntity> fastEntranceQuickAdapter = new QuickAdapter<ContanctsFastEntranceEntity>(R.layout.item_textview, null) {
        @Override
        public int getItemViewType(int position) {
            return -5;
        }

        @Override
        protected void convert(com.chad.library.adapter.base.BaseViewHolder helper, ContanctsFastEntranceEntity item) {
            TextView view = helper.getView(R.id.tv_item);
            view.setBackgroundColor(view.getResources().getColor(R.color.white));
            view.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
            view.setCompoundDrawablePadding(50);
            String addrees = item.getAddrees();
            String name = item.getName();
            view.setText(new MyString(name).setSize(17));
            if (item.getType() == DEPARTMENT_TYPE) {
                Drawable drawable = view.getResources().getDrawable(R.drawable.icon_contancts_department);
                drawable.setBounds(0, 0, 120, 120);
                view.setCompoundDrawables(drawable, null, null, null);
            } else {
                Drawable drawable = view.getResources().getDrawable(R.drawable.icon_organization);
                drawable.setBounds(0, 0, drawable.getMinimumHeight(), drawable.getMinimumHeight());
                view.setCompoundDrawables(drawable, null, null, null);
            }
            if (!StringUtils.empty(addrees)) {
                view.append(new MyString("\n" + addrees).setSize(14));
            }
        }
    };
    QuickAdapter<String> navigationQuickAdapter = new QuickAdapter<String>(R.layout.item_navigation_contancts, null) {
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
    /**
     * 创建菜单：
     */
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            if (viewType > 0 && viewType != DEPARTMENT_TYPE) {
                SwipeMenuItem closeItem = new SwipeMenuItem(getActivity())
                        .setBackground(R.color.themeColor)
                        .setText("添加入口")
                        .setTextColor(Color.WHITE)
                        .setWidth(200)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(closeItem); // 在Item右侧添加一个菜单。
            } else if (viewType < 0) {
                SwipeMenuItem closeItem = new SwipeMenuItem(getActivity())
                        .setBackground(R.color.colorAccent)
                        .setText("移除首页")
                        .setTextColor(Color.WHITE)
                        .setWidth(200)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(closeItem); // 在Item右侧添加一个菜单。
            }
        }
    };
    /**
     * 菜单监听
     */
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();
            int counts = -1;
            int groupPosition = 0;//记录第几组
            int childrenPosition = -1;//记录第几组的第几个(这里-1代表就是分组的组名)
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            //获取多个分组
            int groupCount = contanctsAdaputer.getGroupCount();
            boolean isLoop = true;
            //统计分组多少个的时候能
            for (int i = 0; i < groupCount; i++) {
                if (isLoop) {
                    groupPosition = i;
                    for (int j = 0; j < contanctsAdaputer.getChildrenCount(i); j++) {
                        counts++;
                        if (counts == adapterPosition) {
                            childrenPosition = j;
                            isLoop = false;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            addToHome(groupPosition, childrenPosition);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        useEventBus();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type", DEPARTMENT_TYPE);
        navigation = getArguments().getStringArrayList("navigation");
        showDepartmentName = getArguments().getBoolean("showDepartmentName", false);
        homeDepartmentName = getArguments().getString("homeDepartmentName", "");
        showNavigation = getArguments().getBoolean("showNavigation", true);
        fastEntrance = getArguments().getBoolean("fastEntrance", false);
        isOption = getArguments().getBoolean("isOption", false);
        searchEntrance = getArguments().getBoolean("searchEntrance", true);
        keyCode = getArguments().getString("keyCode");
        contanctsFastEntranceEntityDao = App.getApplication().getDaoSession().getContanctsFastEntranceEntityDao();
        initView();
        getContactTreeContrl(type, keyCode);
    }

    /**
     * 跳转到一个新的页面
     *
     * @param bd
     * @param navigation
     */
    private void statToFragement(Bundle bd, ArrayList navigation) {
        bd.putBoolean("searchEntrance", searchEntrance);
        bd.putStringArrayList("navigation", navigation);
        ContactDataFragment contactsFragment = new ContactDataFragment();
        contactsFragment.setArguments(bd);
        start(contactsFragment);
    }

    /**
     * 添加或者删除一个快捷方式
     *
     * @param group
     * @param childen
     */
    private void addToHome(int group, int childen) {
        ContactEntity contactEntity = contanctsAdaputer.getContactEntity();
        ContanctsFastEntranceEntity contanctsFastEntranceEntity = new ContanctsFastEntranceEntity();

        if (group == 0) {
            List<BusinssunitEntity.DataBean> businssunitData = contactEntity.getBusinssunitData();
            BusinssunitEntity.DataBean dataBean = businssunitData.get(childen);
            contanctsFastEntranceEntity.setKey(dataBean.getId() + "");
            contanctsFastEntranceEntity.setName(dataBean.getBrandclass());
            contanctsFastEntranceEntity.setType(BUSINESS_UNIT_TYPE);

        } else if (group == 1) {
            List<StoreEntity.DataBean> storeData = contactEntity.getStoreData();
            StoreEntity.DataBean dataBean = storeData.get(childen);
            contanctsFastEntranceEntity.setKey(dataBean.getShop_code() + "");
            contanctsFastEntranceEntity.setName(dataBean.getShop_name());
            contanctsFastEntranceEntity.setType(STORE_TYPE);

        } else if (group == 2) {
            List<DepartmentEntiy.DataBean> departmentData = contactEntity.getDepartmentData();
            DepartmentEntiy.DataBean dataBean = departmentData.get(childen);
            contanctsFastEntranceEntity.setKey(dataBean.getId() + "");
            contanctsFastEntranceEntity.setName(dataBean.getDepartment_name());
            contanctsFastEntranceEntity.setType(DEPARTMENT_TYPE);
            StringBuilder stringBuilder = new StringBuilder();
            if (!StringUtils.empty(dataBean.getBrandclass()) && !dataBean.getBrandclass().equals("0")) {
                stringBuilder.append(dataBean.getBrandclass());
            }
            if (!StringUtils.empty(dataBean.getShop_name()) && !dataBean.getShop_name().equals("0")) {
                stringBuilder.append("->");
                stringBuilder.append(dataBean.getShop_name());
            }
            contanctsFastEntranceEntity.setAddrees(stringBuilder.toString());
        }
        if (!isAddedHome(contanctsFastEntranceEntity.getType(), contanctsFastEntranceEntity.getKey())) {
            contanctsFastEntranceEntityDao.insert(contanctsFastEntranceEntity);
        } else {
            QueryBuilder<ContanctsFastEntranceEntity> queryBuilder = contanctsFastEntranceEntityDao.queryBuilder();
            queryBuilder.where(ContanctsFastEntranceEntityDao.Properties.Key.eq(contanctsFastEntranceEntity.getKey()),
                    ContanctsFastEntranceEntityDao.Properties.Type.eq(contanctsFastEntranceEntity.getType()));
            queryBuilder.buildDelete().executeDeleteWithoutDetachingEntities();
        }
        EventBus.getDefault().post(FRESH_FAST_ENTRANCE);
    }

    //通知首页更新
    @Subscribe
    public void notifyFastHome(String contanct) {
        List<ContanctsFastEntranceEntity> contanctsFastEntranceEntities = contanctsFastEntranceEntityDao.loadAll();
        contanctsAdaputer.setContanctsFastEntranceEntities(contanctsFastEntranceEntities);
        contanctsAdaputer.notifyDataSetChanged();
        fastEntranceQuickAdapter.updateAll(contanctsFastEntranceEntities);
    }

    private boolean isAddedHome(int type, String key) {
        for (ContanctsFastEntranceEntity d : contanctsFastEntranceEntityDao.loadAll()) {
            if (d.getType() == type && key.equals(d.getKey())) {
                return true;
            }
        }
        return false;
    }

    private void initView() {
        if (fastEntrance) {
            fastEntry.setVisibility(View.VISIBLE);
        } else {
            fastEntry.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlData.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
        }
        if (searchEntrance) {
            oneKeyClearAutoCompleteText.setVisibility(View.VISIBLE);
        } else {
            oneKeyClearAutoCompleteText.setVisibility(View.GONE);
        }
        if (!showDepartmentName || StringUtils.empty(homeDepartmentName)) {
            mTvDepartment.setVisibility(View.GONE);
        } else {
            mTvDepartment.setVisibility(View.VISIBLE);
            mTvDepartment.setText(homeDepartmentName);
        }
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            notifyFastHome(FRESH_FAST_ENTRANCE);
            getContactTreeContrl(type, keyCode);
        });
        //很神奇,这里如果不进行复制设置返回按钮出栈的时候,导航有问题
        ArrayList<String> navString = new ArrayList<>();
        if (navigation != null) {
            navString.addAll(navigation);
        }
        if (showNavigation) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            navigationRecyclerView.setLayoutManager(linearLayoutManager);
            navigationQuickAdapter.updateAll(navString);
            navigationRecyclerView.setAdapter(navigationQuickAdapter);
            if (navigation != null) {
                linearLayoutManager.scrollToPosition(navigation.size() - 1);
            }
            line.setVisibility(View.VISIBLE);
        }else {
            line.setVisibility(View.GONE);
        }
        navigationQuickAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            if (position < navigation.size() - 1) {
                int numberP = (navigation.size() - 1) - position;
                for (int i = 0; i < numberP; i++) {
                    pop();
                    navigation.remove(navigation.size() - 1);
                }
            }
        });
        if (!isOption) {
            mRlOrganization.setSwipeMenuCreator(mSwipeMenuCreator);
        }
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7));
        defaultItemDecoration.setJustLeftOffsetX(50);
        mRlOrganization.addItemDecoration(defaultItemDecoration);
        mRlOrganization.setSwipeMenuItemClickListener(mMenuItemClickListener);
        contanctsAdaputer = new ContanctsAdaputer(getActivity());
        contanctsAdaputer.setOption(isOption);
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
                bundle.putInt("type", GROUP_TYPE);
                bundle.putBoolean("fastEntrance", false);
                bundle.putBoolean("isOption", isOption);
                bundle.putString("keyCode", "");
                bundle.putBoolean("showNavigation", true);
                statToFragement(bundle, navigation);
            }
        });
        fastEntranceQuickAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            ContanctsFastEntranceEntity contanctsFastEntranceEntity = fastEntranceQuickAdapter.getData().get(position);
            if (navigation == null) {
                navigation = new ArrayList();
            }
            navigation.clear();
            navigation.add("通讯录");
            navigation.add(contanctsFastEntranceEntity.getName());
            Bundle bundle = new Bundle();
            bundle.putInt("type", contanctsFastEntranceEntity.getType());
            bundle.putBoolean("fastEntrance", false);
            bundle.putBoolean("isOption", isOption);
            bundle.putString("keyCode", contanctsFastEntranceEntity.getKey());
            bundle.putBoolean("showNavigation", true);
            statToFragement(bundle, navigation);
        });
        quickentryRecycleView.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        quickentryRecycleView.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                int position = menuBridge.getPosition();
                ContanctsFastEntranceEntity contanctsFastEntranceEntity = fastEntranceQuickAdapter.getData().get(position);
                QueryBuilder<ContanctsFastEntranceEntity> queryBuilder = contanctsFastEntranceEntityDao.queryBuilder();
                queryBuilder.where(ContanctsFastEntranceEntityDao.Properties.Key.eq(contanctsFastEntranceEntity.getKey()), ContanctsFastEntranceEntityDao.Properties.Type.eq(contanctsFastEntranceEntity.getType()));
                queryBuilder.buildDelete().executeDeleteWithoutDetachingEntities();
                EventBus.getDefault().post(FRESH_FAST_ENTRANCE);
            }
        });
        quickentryRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        quickentryRecycleView.setAdapter(fastEntranceQuickAdapter);
        quickentryRecycleView.addItemDecoration(defaultItemDecoration);
        notifyFastHome(FRESH_FAST_ENTRANCE);
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
        if (wicht == GROUP_TYPE) {
            getBusinssunit();
            getContancts(GROUP_TYPE, "");
            getDepartment(GROUP_TYPE, "");
        } else if (wicht == BUSINESS_UNIT_TYPE) {
            getContancts(BUSINESS_UNIT_TYPE, code);
            getStoreByBusinssUnit(code);
            getDepartment(BUSINESS_UNIT_TYPE, code);
        } else if (wicht == STORE_TYPE) {
            getDepartment(STORE_TYPE, code);
            getContancts(STORE_TYPE, code);
        } else if (wicht == DEPARTMENT_TYPE) {
            getContancts(DEPARTMENT_TYPE, code);
        } else if (wicht == 5) {
            getContancts(STORE_TYPE, code);
        } else if (wicht == 6) {
            getContancts(BUSINESS_UNIT_TYPE, code);
        } else if (wicht == 7) {
            getContancts(GROUP_TYPE, "");
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
        requestNOError(0, businssunit, searchByCustmor, false, false);
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
        if (type == BUSINESS_UNIT_TYPE) {
            contancts.add("brandclass_id", Key);
        } else if (type == STORE_TYPE) {
            contancts.add("shop_code", Key);
        } else if (type == DEPARTMENT_TYPE) {
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
        if (type == BUSINESS_UNIT_TYPE) {
            department.add("brandclass_id", Key);
        } else if (type == STORE_TYPE) {
            department.add("shop_code", Key);
        } else if (type == DEPARTMENT_TYPE) {
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
        requestNOError(0, department, searchByCustmor, false, false);
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

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            navigation.remove(navigation.size() - 1);
            pop();
        } else {
            return false;
        }
        return true;
    }
}


