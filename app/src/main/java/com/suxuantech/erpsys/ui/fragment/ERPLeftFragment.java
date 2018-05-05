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
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.HomeCustmoerCountEntity;
import com.suxuantech.erpsys.entity.HomeSumEntity;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.HistoryNoticeActivity;
import com.suxuantech.erpsys.ui.activity.NoticeDetailActivity;
import com.suxuantech.erpsys.ui.activity.OutletsOrderActivity;
import com.suxuantech.erpsys.ui.activity.RegisterIntoShopActivity;
import com.suxuantech.erpsys.ui.activity.SearchOrderActivity;
import com.suxuantech.erpsys.ui.activity.TodayCustomerActivity;
import com.suxuantech.erpsys.ui.activity.base.BaseLazyFragment;
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
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.rv_card)
    RecyclerView mRvCard;
    private View headView;
    private View headView1;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setStatusBarView(getActivity(), mRootView.findViewById(R.id.tv_company_name));
        initRefresh();
        initCard();
        //   initTabLayout();
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

    private void initCard() {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        String Url = Contact.getFullUrl(Contact.HOME_CUSTOMER_COUNT, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<HomeCustmoerCountEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<HomeCustmoerCountEntity>(Url, RequestMethod.POST, HomeCustmoerCountEntity.class);
        HttpListener<HomeCustmoerCountEntity> searchByCustmor = new HttpListener<HomeCustmoerCountEntity>() {
            @Override
            public void onSucceed(int what, Response<HomeCustmoerCountEntity> response) {
                if (response.get().isOK()) {
                    List<HomeCustmoerCountEntity.DataBean> data = response.get().getData();
                    setAdapter(data);
                }
            }

            @Override
            public void onFailed(int what, Response<HomeCustmoerCountEntity> response) {

            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
        String homesum = Contact.getFullUrl(Contact.HOME_SUM, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<HomeSumEntity> homeSumRequset = new JavaBeanRequest<HomeSumEntity>(homesum, RequestMethod.POST, HomeSumEntity.class);
        HttpListener<HomeSumEntity> homeSumListener = new HttpListener<HomeSumEntity>() {
            @Override
            public void onSucceed(int what, Response<HomeSumEntity> response) {
                if (response.get().isOK()) {
                    List<HomeSumEntity.DataBean> data = response.get().getData();
                    TextView tvcn = headView.findViewById(R.id.tv_today_customer_number);
                    TextView tvin = headView.findViewById(R.id.tv_today_income);
                    TextView tvon = headView.findViewById(R.id.tv_today_order_number);
                    TextView tvrt = headView.findViewById(R.id.tv_today_receipt);
                    tvcn.setText(new MyString("今日客资量\u3000" + data.get(0).getRentotal()).setSize(15));
                    //   tvcn.append(new MyString("\n今日客资量").setSize(20));
                    tvon.setText((new MyString("今日订单量\u3000" + data.get(0).getJktotal()).setSize(15)));
                    //tvon.append(new MyString(" 今日订单量").setSize(20));

                    tvin.setText((new MyString(data.get(0).getRealmoney()).setSize(20)));
                    tvin.append(new MyString("\n今日营收").setSize(15));
                    tvrt.setText((new MyString(data.get(0).getZongmoney()).setSize(20)));
                    tvrt.append(new MyString("\n今日收款").setSize(15));
                }
            }

            @Override
            public void onFailed(int what, Response<HomeSumEntity> response) {

            }
        };
        request(33, homeSumRequset, homeSumListener, false, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        headView = inflater.inflate(R.layout.head_home, null, false);
        headView1 = inflater.inflate(R.layout.head_home, null, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setAdapter(List<HomeCustmoerCountEntity.DataBean> data) {
//        ArrayList<String> strings = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.home_title);
        List<String> strings = Arrays.asList(titles);
        //网格布局管理器
        mRvCard.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
        DefaultItemDecoration2 defaultItemDecoration = new DefaultItemDecoration2(getResources().getColor(R.color.gray_f9), 30, 30, QuickAdapter.HEADER_VIEW, QuickAdapter.FOOTER_VIEW);
        defaultItemDecoration.setHasHead(true);
        QuickAdapter<String> quickAdapter = new QuickAdapter<String>(R.layout.item_home_card, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int i = strings.lastIndexOf(item);
                ImageView imgIcon = (ImageView) helper.getView(R.id.img_icon);
                TextView tvName = (TextView) helper.getView(R.id.tv_name);
                TextView tvValues = (TextView) helper.getView(R.id.tv_values);
                tvName.setText(item);
                if (i == 0) {
                    tvValues.setText(data.get(0).getJkyycount());
                } else if (i == 1) {
                    tvValues.setText(data.get(0).getPzcount());
                } else if (i == 2) {
                    tvValues.setText(data.get(0).getLfcount());
                } else if (i == 3) {
                    tvValues.setText(data.get(0).getHzcount());
                } else if (i == 4) {
                    tvValues.setText(data.get(0).getXpcount());
                } else if (i == 5) {
                    tvValues.setText(data.get(0).getQjcount());
                } else if (i == 6) {
                    tvName.setText(strings.get(i));
                    //   tvValues.setText(item.get());
                }
            }
        };
        quickAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getActivity(), TodayCustomerActivity.class);
            intent.putExtra("title", strings.get(position));
            startActivity(intent);
        });

        quickAdapter.addHeaderView(headView);
        mRvCard.addItemDecoration(defaultItemDecoration);
        mRvCard.setAdapter(quickAdapter);
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
                Intent intent = new Intent(getActivity(), RegisterIntoShopActivity.class);
                intent.putExtra("title","排程");
                startActivity(intent);
            //    startActivity(new Intent(getActivity(), ScheduleActivity.class));
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
