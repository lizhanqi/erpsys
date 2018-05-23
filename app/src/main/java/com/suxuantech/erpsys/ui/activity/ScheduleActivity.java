package com.suxuantech.erpsys.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.jeek.calendar.widget.calendar.BingText;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BaseScheme;
import com.suxuantech.erpsys.entity.PhotoSchemeMonthEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.entity.SimpleEntity;
import com.suxuantech.erpsys.entity.TodayOptionPhotoSchemeEntity;
import com.suxuantech.erpsys.entity.TodayPhotoScheduleEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.SearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.ui.TypeFlag;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.GroupAdaputer;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on 2017/11/18 0018 16:16 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 排程页面
 */

public class ScheduleActivity extends TitleNavigationActivity implements ISearchOrderPresenter {
    private ScheduleLayout schedule_layout;
    private PtrClassicFrameLayout mPtrFrame;
    private GroupAdaputer groupAdaputer;
    /**
     * 创建菜单：
     */
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            if (viewType == 111) {
                Drawable drawable = getResources().getDrawable(R.drawable.icon_delete_bucket);
                DrawableCompat.setTint(drawable, getResources().getColor(R.color.white));
                SwipeMenuItem closeItem = new SwipeMenuItem(ScheduleActivity.this)
                        .setBackground(R.color.themeColor)
                        .setText("删除")
                        .setImage(drawable)
                        .setTextColor(Color.WHITE)
                        .setWidth(100)
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
            if (!App.getApplication().hasPermission("M4")){
            toastShort("无权删除排程");
            return;
            }
            int counts = -1;
            int groupPosition = 0;//记录第几组
            int childrenPosition = -1;//记录第几组的第几个(这里-1代表就是分组的组名)
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            //获取多个分组
            int groupCount = groupAdaputer.getGroupCount();
            boolean isLoop = true;
            //统计分组多少个的时候能
            for (int i = 0; i < groupCount; i++) {
                if (isLoop) {
                    groupPosition = i;
                    counts++;
                    if (adapterPosition == counts) {
                        childrenPosition = -1;
                        break;
                    }
                    for (int j = 0; j < groupAdaputer.getChildrenCount(i); j++) {
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
            toastShort("第" + groupPosition + "组的第" + childrenPosition);
            deleteScheme(groupPosition, childrenPosition);
        }
    };
    private List<TodayOptionPhotoSchemeEntity.DataBean> dayOptionScheme;
    private SearchOrderPresenter mSearchOrderPresenter;
    private Map<String, List<PhotoSchemeMonthEntity.DataBean>> allMonthData = new HashMap<>();
    private MenuItem menuItem;

    /**
     * 删除排程
     */
    public void deleteScheme(int groupPosition, int childrenPosition) {

        Map<String, List<BaseScheme>> getdata = groupAdaputer.getdata();
        ArrayList<String> strings = new ArrayList<>(getdata.keySet());
        BaseScheme baseScheme = getdata.get(strings.get(groupPosition)).get(childrenPosition);
        String url = "";
        String lastValue = "";
        if (getIntent().getStringExtra("title").equals("拍照排程")) {
            for (int i = 0; i < dayPhotoSchedule.size(); i++) {
                if (dayPhotoSchedule.get(i).getPcid() == baseScheme.getPcid()) {
                    lastValue = dayPhotoSchedule.get(i).getPhotoid();
                    break;
                }
            }
            url = Contact.getFullUrl(Contact.DELETE_PHOTOGRAPH_SCHEME, Contact.TOKEN, baseScheme.getPcid(), lastValue);
        } else if (getIntent().getStringExtra("title").equals("选片排程")) {
            for (int i = 0; i < dayOptionScheme.size(); i++) {
                if (dayOptionScheme.get(i).getPcid() == baseScheme.getPcid()) {
                    lastValue = dayOptionScheme.get(i).getSpid();
                    break;
                }
            }
            url = Contact.getFullUrl(Contact.DELETE_OPTION_PANEL_SCHEME, Contact.TOKEN, baseScheme.getPcid(), lastValue);
        } else {
            return;
        }
        //请求实体
        JavaBeanRequest<SimpleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SimpleEntity>(url, SimpleEntity.class);
        HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
            @Override
            public void onSucceed(int what, Response<SimpleEntity> response) {
                mPtrFrame.autoRefresh();
            }

            @Override
            public void onFailed(int what, Response<SimpleEntity> response) {
            }
        };

        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, true);
    }

    @Subscribe
    public void Event(String url) {
        if (url.startsWith("http")) {
            //请求实体
            JavaBeanRequest<SimpleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SimpleEntity>(url, RequestMethod.POST, SimpleEntity.class);
            HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
                @Override
                public void onSucceed(int what, Response<SimpleEntity> response) {
                    if (response.get().isOK()) {
                        mPtrFrame.autoRefresh();
                    }
                }

                @Override
                public void onFailed(int what, Response<SimpleEntity> response) {

                }
            };
            request(12, districtBeanJavaBeanRequest, searchByCustmor, true, true);
        } else if (url.equals("refresh")) {
            mPtrFrame.autoRefresh();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchOrderPresenter = new SearchOrderPresenter(this);
        setContentView(R.layout.activity_schedule);
        useEventBus();
        supportToolbar();
        if (getIntent().hasExtra("title")) {
            setTitle(getIntent().getStringExtra("title"));
        }
        initView();
        SwipeMenuRecyclerView mRecyclerView = findViewById(R.id.rvScheduleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.useDefaultLoadMore();
        mRecyclerView.loadMoreFinish(false, true);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        groupAdaputer = new GroupAdaputer(this);
        groupAdaputer.setOnChildClickListener((GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) -> {
            Map<String, List<BaseScheme>> getdata = groupAdaputer.getdata();
            ArrayList<String> strings = new ArrayList<>(getdata.keySet());
            BaseScheme baseScheme = getdata.get(strings.get(groupPosition)).get(childPosition);
            String orderId = baseScheme.getOrderId();
            if (StringUtils.empty(orderId)){
                return;
            }
            mSearchOrderPresenter.sosoNetOrder(orderId,
                    App.getContext().getResources().getString(R.string.start_time),
                    App.getContext().getResources().getString(R.string.end_time), true, false);
        });
        // 设置监听器。
        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        mRecyclerView.setItemViewSwipeEnabled(false);
        mRecyclerView.setAdapter(groupAdaputer);
        mPtrFrame = findViewById(R.id.rotate_header_grid_view_frame);
        mPtrFrame.setMinimumHeight(200);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getIntent().hasExtra("title")) {
                            if (getIntent().getStringExtra("title").equals("拍照排程")) {
                                getMonthPhotoScheme("2018-05-01");
                                getPhotographSchemeByDay("2018-05-01");
                            } else if (getIntent().getStringExtra("title").equals("选片排程")) {
                                todayOptionPhotoScheme("2018-05-01");
                                getMonthOptionPanelScheme("2018-05-01");
                            }
                        }
                        mPtrFrame.refreshComplete();
                    }
                }, 0);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(true);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.autoRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scheme_menu, menu);
        menuItem = menu.findItem(R.id.action_settings);
        this.menu = menu;
        setMenu();
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 更新选中的日期
     */
    private void setMenu() {
        StringBuffer stringBuffer = new StringBuffer();
        int currentSelectYear = schedule_layout.getCurrentSelectYear();
        int currentSelectMonth = schedule_layout.getCurrentSelectMonth();
        int currentSelectDay = schedule_layout.getCurrentSelectDay();
        currentSelectMonth++;
        stringBuffer.append(currentSelectYear);
        if (currentSelectMonth < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(currentSelectMonth);
        if (currentSelectDay < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(currentSelectDay);
        menuItem.setTitle(stringBuffer.toString());
        if (!DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER).equals(stringBuffer.toString())) {
            menu.findItem(R.id.action_today).setOnMenuItemClickListener((MenuItem var1) -> {
                schedule_layout.getMonthCalendar().setTodayToView();
                schedule_layout.getMonthCalendar().setTodayToView();
                return true;
            }).setVisible(true);
        } else {
            menu.findItem(R.id.action_today).setVisible(false);
        }
    }

    private void initView() {
        schedule_layout = findViewById(R.id.schedule_layout);
        schedule_layout.getMonthCalendar().setTodayToView();
        schedule_layout.getMonthCalendar().setTodayToView();
        schedule_layout.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                month++;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(year);
                stringBuffer.append("-");
                if (month < 10) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(month);
                stringBuffer.append("-");
                if (day < 10) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(day);

                if (getIntent().getStringExtra("title").equals("拍照排程")) {
                    getMonthPhotoScheme(stringBuffer.toString());
                    getPhotographSchemeByDay(stringBuffer.toString());
                } else if (getIntent().getStringExtra("title").equals("选片排程")) {
                    todayOptionPhotoScheme(stringBuffer.toString());
                    getMonthOptionPanelScheme(stringBuffer.toString());
                }
                setMenu();
            }

            @Override
            public void onPageChange(int year, int month, int day) {
            }
        });
    }

    ArrayList<String> queue = new ArrayList<>();
    BingText bingText = new BingText() {
        @Override
        public String bingText(int year, int month, int day) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(year);
            if (month < 10) {
                stringBuffer.append(0);
            }
            stringBuffer.append(month);
            List<PhotoSchemeMonthEntity.DataBean> dataBeans = allMonthData.get(stringBuffer.toString());
            if (dataBeans != null) {
                if (day < 10) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(day);
                for (PhotoSchemeMonthEntity.DataBean dataBean : dataBeans) {
                    if (stringBuffer.toString().equals(dataBean.getRiqi())) {
                        return dataBean.getPccount();
                    }
                }
            } else {
                if (day < 10) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(day);
                stringBuffer.insert(4, "-");
                stringBuffer.insert(7, "-");
                if (getIntent().getStringExtra("title").equals("拍照排程")) {
                    getMonthPhotoScheme(stringBuffer.toString());
                } else if (getIntent().getStringExtra("title").equals("选片排程")) {
                    getMonthOptionPanelScheme(stringBuffer.toString());
                }

            }
            return "";
        }
    };

    /**
     * 获取一个某一个月的
     * 拍照排程
     *
     * @param anyDay "2018-05-01"
     *               PICTRUE_SCHEME_BY_MONTH
     */
    public void getMonthPhotoScheme(String anyDay) {
        StringBuffer stringBuffer = new StringBuffer(anyDay);
        stringBuffer.deleteCharAt(4);

        String substring = stringBuffer.substring(0, 6);
        if (queue.contains(substring)) {
            return;
        }
        if (allMonthData.keySet().contains(substring)) {
            MonthCalendarView monthCalendar = schedule_layout.getMonthCalendar();
            WeekCalendarView weekCalendar = schedule_layout.getWeekCalendar();
            MonthView currentMonthView = monthCalendar.getCurrentMonthView();
            weekCalendar.getCurrentWeekView().bingUserText(bingText);
            currentMonthView.bingUserText(bingText);
            schedule_layout.postInvalidate();
            return;
        }
        queue.add(substring);
        String fullUrl = Contact.getFullUrl(Contact.PHOTO_SCHEME_BY_MONTH, Contact.TOKEN, anyDay, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<PhotoSchemeMonthEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoSchemeMonthEntity>(fullUrl, RequestMethod.POST, PhotoSchemeMonthEntity.class);
        HttpListener<PhotoSchemeMonthEntity> searchByCustmor = new HttpListener<PhotoSchemeMonthEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoSchemeMonthEntity> response) {
                queue.remove(substring);
                if (response.get().isOK()) {
                    allMonthData.put(substring, response.get().getData());
                    MonthCalendarView monthCalendar = schedule_layout.getMonthCalendar();
                    WeekCalendarView weekCalendar = schedule_layout.getWeekCalendar();
                    MonthView currentMonthView = monthCalendar.getCurrentMonthView();
                    weekCalendar.getCurrentWeekView().bingUserText(bingText);
                    currentMonthView.bingUserText(bingText);
                    schedule_layout.postInvalidate();
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoSchemeMonthEntity> response) {
                queue.remove(substring);
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }


    /**
     * 获取一个某一个月的
     * 选片排程
     *
     * @param anyDay "2018-05-01"
     */
    public void getMonthOptionPanelScheme(String anyDay) {
        StringBuffer stringBuffer = new StringBuffer(anyDay);
        stringBuffer.deleteCharAt(4);
        String substring = stringBuffer.substring(0, 6);
        if (queue.contains(substring)) {
            return;
        }
        if (allMonthData.keySet().contains(substring)) {
            MonthCalendarView monthCalendar = schedule_layout.getMonthCalendar();
            WeekCalendarView weekCalendar = schedule_layout.getWeekCalendar();
            MonthView currentMonthView = monthCalendar.getCurrentMonthView();
            weekCalendar.getCurrentWeekView().bingUserText(bingText);
            currentMonthView.bingUserText(bingText);
            schedule_layout.postInvalidate();
            return;
        }
        queue.add(substring);
        String fullUrl = Contact.getFullUrl(Contact.PICTRUE_SCHEME_BY_MONTH, Contact.TOKEN, anyDay, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<PhotoSchemeMonthEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoSchemeMonthEntity>(fullUrl, RequestMethod.POST, PhotoSchemeMonthEntity.class);
        HttpListener<PhotoSchemeMonthEntity> searchByCustmor = new HttpListener<PhotoSchemeMonthEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoSchemeMonthEntity> response) {
                queue.remove(substring);
                if (response.get().isOK()) {
                    allMonthData.put(substring, response.get().getData());
                    MonthCalendarView monthCalendar = schedule_layout.getMonthCalendar();
                    WeekCalendarView weekCalendar = schedule_layout.getWeekCalendar();
                    MonthView currentMonthView = monthCalendar.getCurrentMonthView();
                    weekCalendar.getCurrentWeekView().bingUserText(bingText);
                    currentMonthView.bingUserText(bingText);
                    schedule_layout.postInvalidate();
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoSchemeMonthEntity> response) {
                queue.remove(substring);
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }


    List<TodayPhotoScheduleEntity.DataBean> dayPhotoSchedule;

    /**
     * 获取某一天的拍照排程详情
     */
    public void getPhotographSchemeByDay(String day) {
        String fullUrl = Contact.getFullUrl(Contact.PHOTO_SCHEME_BY_DAY, Contact.TOKEN, day, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TodayPhotoScheduleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<TodayPhotoScheduleEntity>(fullUrl, RequestMethod.POST, TodayPhotoScheduleEntity.class);
        HttpListener<TodayPhotoScheduleEntity> searchByCustmor = new HttpListener<TodayPhotoScheduleEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayPhotoScheduleEntity> response) {
                if (response.get().isOK()) {
                    dayPhotoSchedule = response.get().getData();
                    List<BaseScheme> schemes = new ArrayList<>();
                    for (TodayPhotoScheduleEntity.DataBean d : dayPhotoSchedule) {
                        BaseScheme baseScheme = new BaseScheme();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("allData", d);
                        bundle.setClassLoader(getClass().getClassLoader());
                        baseScheme.setBundle(bundle);
                        baseScheme.setArea(d.getArea());
                        baseScheme.setCustomerid(d.getCustomerid());
                        baseScheme.setIslock(d.getIslock());
                        baseScheme.setOrderId(d.getOrderId());
                        baseScheme.setPcday(d.getPcday());
                        baseScheme.setPctime(d.getPctime());
                        baseScheme.setShop_code(d.getShop_code());
                        baseScheme.setPcid(d.getPcid());
                        baseScheme.setXingming(d.getXingming());
                        schemes.add(baseScheme);
                    }
                    sedDayAdaputer(schemes, TypeFlag.PHOTOGRAPH);
                }
            }

            @Override
            public void onFailed(int what, Response<TodayPhotoScheduleEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 获取某一天的选片排程详情
     */
    public void todayOptionPhotoScheme(String day) {
        String fullUrl = Contact.getFullUrl(Contact.TODAY_PICTRUE_SCHEME, Contact.TOKEN, day, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TodayOptionPhotoSchemeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<TodayOptionPhotoSchemeEntity>(fullUrl, RequestMethod.POST, TodayOptionPhotoSchemeEntity.class);
        HttpListener<TodayOptionPhotoSchemeEntity> searchByCustmor = new HttpListener<TodayOptionPhotoSchemeEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayOptionPhotoSchemeEntity> response) {
                if (response.get().isOK()) {
                    dayOptionScheme = response.get().getData();
                    List<BaseScheme> schemes = new ArrayList<>();
                    for (TodayOptionPhotoSchemeEntity.DataBean d : dayOptionScheme) {
                        BaseScheme baseScheme = new BaseScheme();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("allData", d);
                        bundle.setClassLoader(getClass().getClassLoader());
                        baseScheme.setBundle(bundle);
                        baseScheme.setArea(d.getArea());
                        baseScheme.setCustomerid(d.getCustomerid());
                        baseScheme.setIslock(d.getIslock());
                        baseScheme.setOrderId(d.getOrderId());
                        baseScheme.setPcday(d.getPcday());
                        baseScheme.setPctime(d.getPctime());
                        baseScheme.setShop_code(d.getShop_code());
                        baseScheme.setPcid(d.getPcid());
                        baseScheme.setXingming(d.getXingming());
                        schemes.add(baseScheme);
                    }
                    sedDayAdaputer(schemes, TypeFlag.OPTION_PANEL);
                }
            }

            @Override
            public void onFailed(int what, Response<TodayOptionPhotoSchemeEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 每日的详情设置适配器刷新
     *
     * @param data
     */
    private void sedDayAdaputer(List<BaseScheme> data, TypeFlag flage) {
        Map<String, List<BaseScheme>> stringListMap = groupData(data);
        groupAdaputer.setSchemeType(flage);
        groupAdaputer.fresh(stringListMap);
    }

    /**
     * 对list进行分组
     *
     * @param billingList
     * @return
     * @throws Exception
     */
    private Map<String, List<BaseScheme>> groupData(List<BaseScheme> billingList) {
        Map<String, List<BaseScheme>> resultMap = new HashMap<String, List<BaseScheme>>();
        for (BaseScheme item : billingList) {
            if (resultMap.containsKey(item.getPctime())) {//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(item.getPctime()).add(item);
            } else {//map中不存在，新建key，用来存放数据
                List<BaseScheme> tmpList = new ArrayList<BaseScheme>();
                tmpList.add(item);
                resultMap.put(item.getPctime(), tmpList);
            }
        }
        return resultMap;
    }

    @Override
    public void searchSucceed(List<SearchOrderEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        if (data == null || data.size() == 0) {
            ToastUtils.snackbarShort("未找到客户资料");
        } else if (data.size() > 1) {
            ToastUtils.snackbarShort("找到多条资料,无法进行跳转");
        } else {
            Intent intent = new Intent(this, OrderDetailActivity.class);
            SearchOrderEntity.DataBean dataBean = data.get(0);
            intent.putExtra("orderId", dataBean.getOrderId());
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", dataBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void searchFailed(Response<SearchOrderEntity> response, int pageIndex) {

    }
}

