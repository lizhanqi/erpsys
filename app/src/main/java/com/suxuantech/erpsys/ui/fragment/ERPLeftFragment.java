package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.HistoryNoticeActivity;
import com.suxuantech.erpsys.ui.activity.NoticeDetailActivity;
import com.suxuantech.erpsys.ui.activity.OutletsOrderActivity;
import com.suxuantech.erpsys.ui.activity.RegisterIntoShopActivity;
import com.suxuantech.erpsys.ui.activity.ScheduleActivity;
import com.suxuantech.erpsys.ui.activity.SearchOrderActivity;
import com.suxuantech.erpsys.ui.activity.base.BaseLazyFragment;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;
import com.suxuantech.erpsys.ui.dialog.NoticeDialog;
import com.suxuantech.erpsys.ui.widget.MarqueTextView;
import com.suxuantech.erpsys.ui.widget.WaveHelper;
import com.suxuantech.erpsys.ui.widget.WaveView;
import com.suxuantech.erpsys.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class ERPLeftFragment extends BaseLazyFragment {


    @BindView(R.id.tv_company_name)
    TextView mTvCompanyName;
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
    @BindView(R.id.tv_register_into_shop)
    TextView mTvRegisterIntoShop;
    @BindView(R.id.tv_outlets_order)
    TextView mTvOutletsOrder;
    @BindView(R.id.tv_order_search)
    TextView mTvOrderSearch;
    @BindView(R.id.tv_schedule)
    TextView mTvSchedule;
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
    private View view;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_erp_left;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setStatusBarView(getActivity(), mRootView.findViewById(R.id.tv_company_name));
        initRefresh();
        initTabLayout();
        //initBall(view);
//                Intent intent = new Intent(getActivity(), OptionActivity.class);
//                OptionHelp multiple = new OptionHelp(getActivity()).setMultiple(false);
//                multiple.setAllData(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                multiple.setCheckedData(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                multiple.setCheckedData("礼服");
//                multiple.setTitle("选择");
  //                multiple.setUrl("11111");

//                intent.putExtra("All",new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                intent.putExtra("Checked",new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                intent.putExtra("Title","选择");
//                intent.putExtra("Multiple",false);
  //                startActivity(multiple.creat());
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
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
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

    }

    @OnClick({R.id.tv_msg_number, R.id.rl_msg, R.id.tv_notice_details, R.id.tv_company_name, R.id.tv_today_time, R.id.tv_notice, R.id.tv_info, R.id.left_top, R.id.tv_register_into_shop, R.id.tv_outlets_order, R.id.tv_order_search, R.id.tv_schedule, R.id.schedule_wave, R.id.task_wave, R.id.tablayout_home, R.id.view_tl, R.id.vp_home, R.id.relativeLayout2, R.id.refreshLayout, R.id.root_layout_content_immersed})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_msg_number:
                break;
            case R.id.rl_msg:
                startActivity(new Intent(getActivity(), HistoryNoticeActivity.class));
                break;
            case R.id.tv_notice_details:
                startActivity(new Intent(getActivity(), NoticeDetailActivity.class));
                break;
            case R.id.tv_company_name:
                break;
            case R.id.tv_today_time:
                break;
            case R.id.tv_notice:
                break;
            case R.id.tv_info:
                break;
            case R.id.left_top:
                break;
            case R.id.tv_register_into_shop:
                startActivity(new Intent(getActivity(), RegisterIntoShopActivity.class));
                break;
            case R.id.tv_outlets_order:
                startActivity(new Intent(getActivity(), OutletsOrderActivity.class));
                break;
            case R.id.tv_order_search:
                startActivity(new Intent(getActivity(), SearchOrderActivity.class));
                break;
            case R.id.tv_schedule:
                startActivity(new Intent(getActivity(), ScheduleActivity.class));
                break;
            case R.id.schedule_wave:
                break;
            case R.id.task_wave:
                break;
            case R.id.tablayout_home:
                break;
            case R.id.view_tl:
                break;
            case R.id.vp_home:
                break;
            case R.id.relativeLayout2:
                break;
            case R.id.refreshLayout:
                break;
            case R.id.root_layout_content_immersed:
                break;
        }
    }
}
