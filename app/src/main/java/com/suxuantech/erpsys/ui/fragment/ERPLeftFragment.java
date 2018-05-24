package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.suxuantech.erpsys.entity.HomeCustmoerCountEntity;
import com.suxuantech.erpsys.entity.HomeSumEntity;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.NoticeDetailActivity;
import com.suxuantech.erpsys.ui.activity.OutletsOrderActivity;
import com.suxuantech.erpsys.ui.activity.RegisterIntoShopActivity;
import com.suxuantech.erpsys.ui.activity.RegisterIntoShopSearchActivity;
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
import com.yanzhenjie.nohttp.RequestMethod;
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
    TextView mTvTodayTime;
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
    private QuickAdapter<String> quickAdapter;
    @Subscribe()
    @MainThread
    public void EventBus(String key){
        //todo 这里不知道是不是需要再次更新权限列表呢?
        if (key.equals("changeUser")){
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
            mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.translucent_black_90).init();
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
                    tvcn.append(new MyString(data.get(0).getRentotal()).setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    //   tvcn.append(new MyString("\n今日客资量").setSize(20));
                    tvon.setText((new MyString("今日订单量\u3000").setSize(15).setColor(getResources().getColor(R.color.mainNav_66))));
                    tvon.append(new MyString(data.get(0).getJktotal()).setSize(15).setColor(getResources().getColor(R.color.edit_text)));
                    //tvon.append(new MyString(" 今日订单量").setSize(20));
                    tvin.setText((new MyString("¥" + StringUtils.moneyFormat(data.get(0).getRealmoney())).setSize(20)).setColor(getResources().getColor(R.color.colorAccent)));
                    tvin.append(new MyString("\n今日营收").setSize(15).setSize(15).setColor(getResources().getColor(R.color.mainNav_66)));
                    tvrt.setText((new MyString("¥" + StringUtils.moneyFormat(data.get(0).getZongmoney())).setSize(20).setColor(getResources().getColor(R.color.colorAccent))));
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
        headView = inflater.inflate(R.layout.head_home, null, false);
        headView.findViewById(R.id.tv_today_customer_number).setOnClickListener(l -> {
            //进店登记的搜索
            Intent intent = new Intent(getActivity(), RegisterIntoShopSearchActivity.class);
            intent.putExtra("title", "今日客资");
            intent.putExtra("hideSearch", true);
            startActivity(intent);
        });
        headView.findViewById(R.id.tv_today_income).setOnClickListener(l -> {
            Intent intent = new Intent(getActivity(), SearchOrderActivity.class);
            intent.putExtra("title", "今日订单");
            intent.putExtra("hideSearch", true);
            startActivity(intent);
        });
        headView.findViewById(R.id.tv_today_order_number).setOnClickListener(l -> {
            Intent intent = new Intent(getActivity(), SearchOrderActivity.class);
            intent.putExtra("title", "今日订单");
            intent.putExtra("hideSearch", true);
            startActivity(intent);
        });
        headView.findViewById(R.id.tv_today_receipt).setOnClickListener(l -> {
            Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
            intent.putExtra("title", "今日收款");
            startActivity(intent);
        });
        module = inflater.inflate(R.layout.in_home_module, null, false);
        module.findViewById(R.id.tv_register_into_shop).setOnClickListener(o -> {
            startActivity(new Intent(getActivity(), RegisterIntoShopActivity.class));
        });
        module.findViewById(R.id.tv_outlets_order).setOnClickListener(o -> {
            startActivity(new Intent(getActivity(), OutletsOrderActivity.class));
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
        if (quickAdapter!=null){
            quickAdapter.notifyDataSetChanged();
            return;
        }
        //todo 这里还需改动,如果模块需要权限展示的话更需要改动
        String[] titles = getResources().getStringArray(R.array.home_title);
        List<String> strings = Arrays.asList(titles);
        quickAdapter = new QuickAdapter<String>(R.layout.item_home_card, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int i = strings.lastIndexOf(item);
                ImageView imgIcon = (ImageView) helper.getView(R.id.img_icon);
                TextView tvName = (TextView) helper.getView(R.id.tv_name);
                TextView tvValues = (TextView) helper.getView(R.id.tv_values);
                tvName.setText(item);
                if (i == 0) {
                    imgIcon.setImageResource(R.drawable.icon_make_into_store_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getJkyycount());
                } else if (i == 1) {
                    imgIcon.setImageResource(R.drawable.icon_photo_custmoer_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getPzcount());
                } else if (i == 2) {
                    imgIcon.setImageResource(R.drawable.icon_dress_customer_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getLfcount());
                } else if (i == 3) {
                    imgIcon.setImageResource(R.drawable.icon_make_up_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getHzcount());
                } else if (i == 4) {
                    imgIcon.setImageResource(R.drawable.icon_option_panel_custmoer_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getXpcount());
                } else if (i == 5) {
                    imgIcon.setImageResource(R.drawable.icon_get_photo_custmoer_home);
                    tvValues.setText(ERPLeftFragment.this.dataBeans.get(0).getQjcount());

                } else if (i == 6) {
                    imgIcon.setImageResource(R.drawable.icon_me_custmer_home);
                    tvName.setText(strings.get(i));
                    //   tvValues.setText(item.get());
                }
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
            if (position==6){
                Intent intent = new Intent(getActivity(), ContactsActivity.class);
                Bundle bundle = getBundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }else {
                Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
                intent.putExtra("title", strings.get(position));
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
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOption", true);
        bundle.putBoolean("fastEntrance", true);
        bundle.putBoolean("showDepartmentName", true);
        if (StringUtils.empty(App.getApplication().getUserInfor().getDepartment_name())){
            if (StringUtils.empty(App.getApplication().getUserInfor().getShop_name())){
                if (StringUtils.empty(App.getApplication().getUserInfor().getBrandclass())){
                    //仅仅获取集团联系人
                    bundle.putInt("type", 7);
                    bundle.putString("homeDepartmentName",  "集团联系人");
                    bundle.putString("keyCode","");
                }else {
                    //仅仅获取事业部联系人
                    bundle.putInt("type", 6);
                    bundle.putString("homeDepartmentName",  App.getApplication().getUserInfor().getBrandclass() );
                    bundle.putString("keyCode", App.getApplication().getUserInfor().getBrandclass_id() + "");
                }
            }else {
                //仅仅获取点面联系人
                bundle.putInt("type", 5);
                bundle.putString("homeDepartmentName",  App.getApplication().getUserInfor().getShop_name() );
                bundle.putString("keyCode", App.getApplication().getUserInfor().getShop_code() + "");
            }
        }else {
            //仅仅获取部门联系人
            bundle.putInt("type", 4);
            bundle.putString("homeDepartmentName",  App.getApplication().getUserInfor().getDepartment_name() );
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
                initCard();
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
}
