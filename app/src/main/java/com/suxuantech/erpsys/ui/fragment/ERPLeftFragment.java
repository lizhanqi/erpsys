package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.hanks.htextview.fall.FallTextView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.FormEntity;
import com.suxuantech.erpsys.entity.HomeCustmoerCountEntity;
import com.suxuantech.erpsys.entity.HomeSumEntity;
import com.suxuantech.erpsys.entity.PermissionEntity;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.NoticeDetailActivity;
import com.suxuantech.erpsys.ui.activity.OutletsOrderActivity;
import com.suxuantech.erpsys.ui.activity.RegisterIntoShopActivity;
import com.suxuantech.erpsys.ui.activity.SearchOrderActivity;
import com.suxuantech.erpsys.ui.activity.TodayCustomerActivity;
import com.suxuantech.erpsys.ui.activity.base.BaseLazyFragment;
import com.suxuantech.erpsys.ui.activity.base.ContactsActivity;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.dialog.NoticeDialog;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration2;
import com.suxuantech.erpsys.ui.widget.MarqueTextView;
import com.suxuantech.erpsys.ui.widget.WaveHelper;
import com.suxuantech.erpsys.ui.widget.WaveView;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class ERPLeftFragment extends BaseLazyFragment {
    @BindView(R.id.tv_company_name)
    FallTextView mTvCompanyName;
    @BindView(R.id.tv_today_time)
    FallTextView mTvTodayTime;
    @BindView(R.id.tv_msg_number)
    TextView mTvMsgNumber;
    @BindView(R.id.rl_msg)
    RelativeLayout mRlMsg;
    @BindView(R.id.tv_notice)
    TextView mTvNotice;
    @BindView(R.id.tv_info)
    MarqueTextView mTvInfo;
    @BindView(R.id.tv_notice_details)
    TextView mTvNoticeDetails;
    @BindView(R.id.left_top)
    RelativeLayout mLeftTop;
    @BindView(R.id.schedule_wave)
    WaveView mScheduleWave;
    @BindView(R.id.task_wave)
    WaveView mTaskWave;
    @BindView(R.id.tablayout_home)
    TabLayout mTablayoutHome;
    @BindView(R.id.view_tl)
    View mViewTl;
    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.relativeLayout2)
    RelativeLayout mRelativeLayout2;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout mRefreshLayout;
    @BindView(R.id.root_layout_content_immersed)
    CoordinatorLayout mRootLayoutContentImmersed;
    @BindView(R.id.rv_card)
    RecyclerView mRvCard;
    private View headView;
    private View module;
    private List<HomeCustmoerCountEntity.DataBean> dataBeans;
    private QuickAdapter<FormEntity> quickAdapter;

    @Subscribe()
    @MainThread
    public void EventBus(String key) {
        if (key.equals("changeUser")) {
            mRefreshLayout.startRefresh();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setStatusBarView(getActivity(), mRootView.findViewById(R.id.tv_company_name));
        initRefresh();
        useEventBus();
        mTvCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionHelp optionHelp = new OptionHelp(getActivity());
                optionHelp.setUrlTag(OptionHelp.UrlTag.OPTION_EXCUTE_SHOP);
                //  optionHelp.setTitle("");
                startActivity(optionHelp.creat());
            }
        });
        //   initTabLayout();
        //initBall(view);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_erp_left;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initImmersionBar();
        }
    }

    @Override
    public void initImmersionBar() {
        if (getActivity() != null) {
            super.initImmersionBar();
            mImmersionBar.statusBarDarkFont(false).init();
        }
    }

    public void alertShow4() {
        AlertView alertView = new AlertView("标题", null, "取消",
                new String[]{"高亮按钮1"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3"},
                getActivity(), AlertView.Style.ACTIONSHEET, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {

            }
        });
        if (ScreenUtils.checkDeviceHasNavigationBar(getContext())) {
            alertView.setPaddingBottom(ScreenUtils.getNavigationBarHeight(getContext()));
        }
        alertView.show();
    }

    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NoticeDialog.Builder nb = new NoticeDialog.Builder(getContext());
            nb.setTitleLeftDrawable(getResources().getDrawable(R.drawable.icon_msg_task));
            nb.setDigestText("消息简介\n日期那年月日");
            nb.setTitleText("新任务:");
            nb.setOnClickDetails(new NoticeDialog.OnClickDetails() {
                @Override
                public void onClickDetails() {
                    startActivity(new Intent(getActivity(), NoticeDetailActivity.class));
                }
            });
            nb.build().show();
        }
    };

    private void initCard() {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        String Url = Contact.getFullUrl(Contact.HOME_CUSTOMER_COUNT, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<HomeCustmoerCountEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<HomeCustmoerCountEntity>(Url, RequestMethod.POST, HomeCustmoerCountEntity.class);
        HttpListener<HomeCustmoerCountEntity> searchByCustmor = new HttpListener<HomeCustmoerCountEntity>() {
            @Override
            public void onSucceed(int what, Response<HomeCustmoerCountEntity> response) {
                if (response.get().isOK()) {
                    dataBeans = response.get().getData();
                    setData2View(dataBeans);
                }
                mRefreshLayout.finishRefreshing();
            }

            @Override
            public void onFailed(int what, Response<HomeCustmoerCountEntity> response) {
                mRefreshLayout.finishRefreshing();
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
        String homesum = Contact.getFullUrl(Contact.HOME_SUM, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<HomeSumEntity> homeSumRequset = new JavaBeanRequest<HomeSumEntity>(homesum, RequestMethod.POST, HomeSumEntity.class);
        HttpListener<HomeSumEntity> homeSumListener = new HttpListener<HomeSumEntity>() {
            @Override
            public void onSucceed(int what, Response<HomeSumEntity> response) {
                mRefreshLayout.finishRefreshing();
                if (response.get().isOK()) {
                    List<HomeSumEntity.DataBean> data = response.get().getData();
                    TextView tvcn = headView.findViewById(R.id.tv_today_customer_number);
                    TextView tvin = headView.findViewById(R.id.tv_today_income);
                    TextView tvon = headView.findViewById(R.id.tv_today_order_number);
                    TextView tvrt = headView.findViewById(R.id.tv_today_receipt);
                    tvcn.setText(new MyString("今日客资量\u3000").setSize(15).setColor(getResources().getColor(R.color.mainNav_66)));
                    if (App.getApplication().hasPermission("K2")) {
                        tvcn.append(new MyString(StringUtils.empty(data.get(0).getJktotal()) ? "0" : data.get(0).getJktotal()).setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    } else {
                        tvcn.append(new MyString("__").setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    }
                    tvon.setText((new MyString("今日订单量\u3000").setSize(15).setColor(getResources().getColor(R.color.mainNav_66))));
                    if (App.getApplication().hasPermission("I2")) {
                        tvon.append(new MyString(StringUtils.empty(data.get(0).getRentotal()) ? "0" : data.get(0).getRentotal()).setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    } else {
                        tvon.append(new MyString("__").setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    }
                    if (App.getApplication().hasPermission("I2")) {
                        tvin.setText((new MyString("¥" + StringUtils.moneyFormat(StringUtils.empty(data.get(0).getZongmoney()) ? " 0.00" : data.get(0).getZongmoney())).setSize(20).setColor(getResources().getColor(R.color.colorAccent))));
                    } else {
                        tvin.setText((new MyString("¥\u2000__").setSize(20).setColor(getResources().getColor(R.color.colorAccent))));
                    }
                    tvin.append(new MyString("\n今日营收").setSize(15).setSize(15).setColor(getResources().getColor(R.color.mainNav_66)));
                    if (App.getApplication().hasPermission("I3")) {
                        tvrt.setText((new MyString("¥" + StringUtils.moneyFormat(StringUtils.empty(data.get(0).getRealmoney()) ? " 0.00" : data.get(0).getRealmoney())).setSize(20)).setColor(getResources().getColor(R.color.colorAccent)));
                    } else {
                        tvrt.setText((new MyString("¥\u2000__").setSize(20)).setColor(getResources().getColor(R.color.colorAccent)));
                    }
                    tvrt.append(new MyString("\n今日收款").setSize(15).setColor(getResources().getColor(R.color.mainNav_66)));
                }
            }

            @Override
            public void onFailed(int what, Response<HomeSumEntity> response) {
                mRefreshLayout.finishRefreshing();
            }
        };
        request(33, homeSumRequset, homeSumListener, false, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initImmersionBar();
        headView = inflater.inflate(R.layout.head_home, null, false);
        headView.findViewById(R.id.tv_today_customer_number).setOnClickListener(l -> {
            if (App.getApplication().hasPermission("K2")) {
                Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
                intent.putExtra("title", "今日客资");
                startActivity(intent);
            } else {
                ToastUtils.snackbarShort("无权查看", "确定");
            }
        });
        headView.findViewById(R.id.tv_today_income).setOnClickListener(l -> {
            if (App.getApplication().hasPermission("I2")) {
                Intent intent = new Intent(getActivity(), SearchOrderActivity.class);
                intent.putExtra("title", "今日订单");
                intent.putExtra("hideSearch", true);
                startActivity(intent);
            } else {
                ToastUtils.snackbarShort("无权查看", "确定");
            }
        });
        headView.findViewById(R.id.tv_today_order_number).setOnClickListener(l -> {
            if (App.getApplication().hasPermission("I2")) {
                Intent intent = new Intent(getActivity(), SearchOrderActivity.class);
                intent.putExtra("title", "今日订单");
                intent.putExtra("hideSearch", true);
                startActivity(intent);
            } else {
                ToastUtils.snackbarShort("无权查看", "确定");
            }
        });
        headView.findViewById(R.id.tv_today_receipt).setOnClickListener(l -> {
            if (App.getApplication().hasPermission("I3")) {
                Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
                intent.putExtra("title", "今日收款");
                startActivity(intent);
            } else {
                ToastUtils.snackbarShort("无权查看", "确定");
            }
        });
        module = inflater.inflate(R.layout.in_home_module, null, false);
        module.findViewById(R.id.tv_register_into_shop).setOnClickListener(o -> {
            startActivity(new Intent(getActivity(), RegisterIntoShopActivity.class));
        });
        module.findViewById(R.id.tv_outlets_order).setOnClickListener(o -> {
            if (App.getApplication().hasPermission("A3")) {
                startActivity(new Intent(getActivity(), OutletsOrderActivity.class));
            } else {
                ToastUtils.snackbarShort("无权开单", "确定");
            }
        });
        module.findViewById(R.id.tv_order_search).setOnClickListener(o -> {
            startActivity(new Intent(getActivity(), SearchOrderActivity.class));
        });
        module.findViewById(R.id.tv_schedule).setOnClickListener(o -> {
            Intent intent = new Intent(getActivity(), RegisterIntoShopActivity.class);
            intent.putExtra("title", "排程");
            startActivity(intent);
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setData2View(List<HomeCustmoerCountEntity.DataBean> dataBeans) {
        List<FormEntity> strings = homeData(dataBeans);
        if (quickAdapter != null) {
            quickAdapter.updateAll(strings);
            quickAdapter.notifyDataSetChanged();
            return;
        }
        quickAdapter = new QuickAdapter<FormEntity>(R.layout.item_home_card, strings) {
            @Override
            protected void convert(BaseViewHolder helper, FormEntity item) {
                ImageView imgIcon = (ImageView) helper.getView(R.id.img_icon);
                TextView tvName = (TextView) helper.getView(R.id.tv_name);
                TextView tvValues = (TextView) helper.getView(R.id.tv_values);
                imgIcon.setImageResource(item.getIcon());
                tvName.setText(item.getKey());
                tvValues.setText(StringUtils.empty(item.getValue()) ? "0" : item.getValue());
            }
        };
        mRvCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mRefreshLayout.setEnableRefresh(false);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    mRefreshLayout.setEnableRefresh(true);
                }
                return false;
            }
        });
        quickAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 6) {
                Intent intent = new Intent(getActivity(), ContactsActivity.class);
                Bundle bundle = getBundle();
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
                intent.putExtra("title", strings.get(position).getKey());
                startActivity(intent);
            }
        });
        quickAdapter.addHeaderView(module);
        quickAdapter.addHeaderView(headView);
        mRvCard.setAdapter(quickAdapter);
        DefaultItemDecoration2 defaultItemDecoration = new DefaultItemDecoration2(getResources().getColor(R.color.gray_f9), 30, 30, QuickAdapter.HEADER_VIEW, QuickAdapter.FOOTER_VIEW);
        defaultItemDecoration.setHasHead(true);
        mRvCard.addItemDecoration(defaultItemDecoration);
        mRefreshLayout.setEnableLoadmore(false);
    }

    @NonNull
    private List<FormEntity> homeData(List<HomeCustmoerCountEntity.DataBean> dataBeans) {
        String[] titles = getResources().getStringArray(R.array.home_title);
        List<FormEntity> strings = new ArrayList<FormEntity>();
        HomeCustmoerCountEntity.DataBean dataBean = dataBeans.get(0);
        if (App.getApplication().hasPermission("K2")) {
            strings.add(new FormEntity(R.drawable.icon_make_into_store_home, titles[0], dataBean.getJkyycount()));
        }
        if (App.getApplication().hasPermission("D2")) {
            strings.add(new FormEntity(R.drawable.icon_photo_custmoer_home, titles[1], dataBean.getPzcount()));
        }
        if (App.getApplication().hasPermission("B2")) {
            strings.add(new FormEntity(R.drawable.icon_dress_customer_home, titles[2], dataBean.getLfcount()));
        }
        if (App.getApplication().hasPermission("C2")) {
            strings.add(new FormEntity(R.drawable.icon_make_up_home, titles[3], dataBean.getHzcount()));
        }

        if (App.getApplication().hasPermission("E2")) {
            strings.add(new FormEntity(R.drawable.icon_option_panel_custmoer_home, titles[4], dataBean.getXpcount()));
        }

        if (App.getApplication().hasPermission("F2")) {
            strings.add(new FormEntity(R.drawable.icon_get_photo_custmoer_home, titles[5], dataBean.getQjcount()));
        }
        return strings;
    }

    @NonNull
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOption", true);
        bundle.putBoolean("fastEntrance", true);
        bundle.putBoolean("showDepartmentName", true);
        if (StringUtils.empty(App.getApplication().getUserInfor().getDepartment_name())) {
            if (StringUtils.empty(App.getApplication().getUserInfor().getShop_name())) {
                if (StringUtils.empty(App.getApplication().getUserInfor().getBrandclass())) {
                    //仅仅获取集团联系人
                    bundle.putInt("type", 7);
                    bundle.putString("homeDepartmentName", "集团联系人");
                    bundle.putString("keyCode", "");
                } else {
                    //仅仅获取事业部联系人
                    bundle.putInt("type", 6);
                    bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getBrandclass());
                    bundle.putString("keyCode", App.getApplication().getUserInfor().getBrandclass_id() + "");
                }
            } else {
                //仅仅获取点面联系人
                bundle.putInt("type", 5);
                bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getShop_name());
                bundle.putString("keyCode", App.getApplication().getUserInfor().getShop_code() + "");
            }
        } else {
            //仅仅获取部门联系人
            bundle.putInt("type", 4);
            bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getDepartment_name());
            bundle.putString("keyCode", App.getApplication().getUserInfor().getDepartment_id() + "");
        }
        return bundle;
    }

    /**
     * 动画球,暂时不用
     *
     * @param view
     */
    private void initBall(View view) {
        WaveView taskWave = view.findViewById(R.id.task_wave);
        WaveView scheduleWave = view.findViewById(R.id.schedule_wave);
        taskWave.setWaveColor(getResources().getColor(R.color.wavel), getResources().getColor(R.color.wave), getResources().getColor(R.color.wavebg));
        scheduleWave.setWaveColor(getResources().getColor(R.color.wavel), getResources().getColor(R.color.wave), getResources().getColor(R.color.wavebg));
        WaveHelper taskWaveHelper = new WaveHelper(taskWave);
        taskWaveHelper.start();
        WaveHelper scheduleWaveHelper = new WaveHelper(scheduleWave);
        scheduleWaveHelper.start();
        taskWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hd.sendEmptyMessageDelayed(0, 5000);
            }
        });
    }

    private ArrayList<Fragment> fragments;

    /**
     * 首页数据不用了
     */
    private void initTabLayout() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        TabLayout tableLayoutHome = mRootView.findViewById(R.id.tablayout_home);
        ViewPager viewPagerHome = mRootView.findViewById(R.id.vp_home);
        String[] arryTitle = getResources().getStringArray(R.array.home_title);
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        for (Fragment f : childFragmentManager.getFragments()) {
            ft.remove(f);
        }
        ft.commit();
        viewPagerHome.removeAllViews();
        viewPagerHome.removeAllViewsInLayout();
        //这里必要时可以放开
        //   viewPagerHome.destroyDrawingCache();
        viewPagerHome.setOffscreenPageLimit(arryTitle.length);
        viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                fragments.get(position).setUserVisibleHint(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        childFragmentManager.executePendingTransactions();
        ArrayList<RegisterEntity.DataBean> a = null;
        QuickAdapter value = new QuickAdapter<RegisterEntity.DataBean>(R.layout.item_register_info, a) {
            @Override
            protected void convert(BaseViewHolder helper, RegisterEntity.DataBean item) {

            }
        };
        value.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        if (fragments == null) {
            fragments = new ArrayList<Fragment>();
            for (int i = 0; i < arryTitle.length; i++) {
                HomeDataFragement homeDataFragement = new HomeDataFragement();
                Bundle bundle = new Bundle();
                bundle.putString("title", arryTitle[i]);
                homeDataFragement.setArguments(bundle);
                fragments.add(homeDataFragement);
            }
        }

        DefaultFragmentAdapter defaultFragmentAdapter = new DefaultFragmentAdapter(childFragmentManager, new ArrayList<>(Arrays.asList(arryTitle)), new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {
                fragments.get(positon).setUserVisibleHint(true);
                return fragments.get(positon);
            }
        });
        viewPagerHome.setAdapter(defaultFragmentAdapter);
        viewPagerHome.setOffscreenPageLimit(fragments.size());
        tableLayoutHome.setupWithViewPager(viewPagerHome);
    }

    private void initRefresh() {
        //网格布局管理器
        // mRvCard.setNestedScrollingEnabled();
        mRvCard.setLayoutManager(new GridLayoutManager(getContext(), 2) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                // return false;
                return true;
            }
        });
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mTvCompanyName.animateText(StringUtils.safetyString(App.getApplication().getUserInfor().getShop_name()));
                mTvTodayTime.animateText(DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY));
                getPermisstion();
            }

            @Override
            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            }

            @Override
            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
                if (Math.abs(fraction - 1.0f) > 0) {
                } else {
                }
            }
        });
        mRefreshLayout.startRefresh();
    }

    /**
     * 获取登录人权限
     */
    public void getPermisstion() {
        App.getApplication().releaseLoginData();
        if (App.getApplication().getUserInfor().getMain_work_type().isEmpty()) {
            ToastUtils.snackbarShort("未设置工作类型!无法获取权限");
            return;
        }
        if (App.getApplication().getUserInfor().getMain_position_code().isEmpty()) {
            ToastUtils.snackbarShort("未设置主岗位,无法获取权限");
            return;
        }
        String string = Contact.getFullUrl(Contact.LOGIN_PERMISSION, Contact.TOKEN, App.getApplication().getUserInfor().getMain_work_type() + "," + App.getApplication().getUserInfor().getWork_type(),
        App.getApplication().getUserInfor().getMain_position_code() + "," + App.getApplication().getUserInfor().getPosition_code(), App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<PermissionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PermissionEntity>(string, RequestMethod.POST, PermissionEntity.class);
        districtBeanJavaBeanRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        HttpListener<PermissionEntity> searchByCustmor = new HttpListener<PermissionEntity>() {
            @Override
            public void onSucceed(int what, Response<PermissionEntity> response) {
                if (response.get().isOK() || response.get().getData() != null) {
                    List<String> data = response.get().getData();
                    App.getApplication().setUserPermission(data);
                    initCard();
                } else {

                }
            }

            @Override
            public void onFailed(int what, Response<PermissionEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }


    public void addShortLunch(String title, int lunchIcon, Class<?> cls) {
        //创建一个添加快捷方式的Intent
        Intent addSC = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        addSC.putExtra("duplicate", false);
        //快捷键的标题
        //快捷键的图标
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getActivity(), lunchIcon);
        //创建单击快捷键启动本程序的Intent
        Intent launcherIntent = new Intent(getActivity(), cls);
        //设置快捷键的标题
        addSC.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        //设置快捷键的图标
        addSC.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //设置单击此快捷键启动的程序
        addSC.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
        //向系统发送添加快捷键的广播
        getActivity().sendBroadcast(addSC);
    }


}
