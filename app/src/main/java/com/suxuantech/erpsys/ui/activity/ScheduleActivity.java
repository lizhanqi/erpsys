package com.suxuantech.erpsys.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.BingText;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.PhotoSchemeMonthEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.GroupAdaputer;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 排程页面
 */

public class ScheduleActivity extends TitleNavigationActivity {
    private MonthCalendarView monthCalendarView;
    private ScheduleLayout schedule_layout;
    private TextView viewById;
    private PtrClassicFrameLayout mPtrFrame;
    private WeekCalendarView weekCalendarView;
    private GroupAdaputer groupAdaputer;
    /**
     * 创建菜单：
     */
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
           // if(viewType== com.donkingliang.groupedadapter.R.integer.type_child){
                if (viewType==111){
                    SwipeMenuItem closeItem = new SwipeMenuItem(ScheduleActivity.this)
                            .setBackground(R.color.themeColor)
                            .setText("删除")
                            .setTextColor(Color.WHITE)
                            .setWidth(100)
                            .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//                    SwipeMenuItem deleteItem = new SwipeMenuItem(ScheduleActivity.this) ; // 各种文字和图标属性设置。
//                    deleteItem.setWeight( ViewGroup.LayoutParams.MATCH_PARENT);
//                    deleteItem.setText("删除"+viewType);
                    rightMenu.addMenuItem(closeItem); // 在Item右侧添加一个菜单。
                }
           // }
        }
    };
    /**
     *菜单监听
     */

    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            int  counts=-1;
            int groupPosition = 0;//记录第几组
            int childrenPosition=-1;//记录第几组的第几个(这里-1代表就是分组的组名)
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                //获取多个分组
            int groupCount = groupAdaputer.getGroupCount();
            boolean isLoop=true;
            //统计分组多少个的时候能
            for (int i=0;i<groupCount;i++){
                if (isLoop) {
                    groupPosition=i;
                     counts++;
                if (adapterPosition==counts){
                    childrenPosition=-1;
                    break;
                }
              for (int j = 0; j < groupAdaputer.getChildrenCount(i); j++) {
                        counts++;
                        if (counts==adapterPosition) {
                            childrenPosition = j;
                            isLoop = false;
                            break;
                        }
                    }
                }else {
                    break;
                }
            }
           // toastShort("第"+groupPosition+"组的第"+childrenPosition);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        supportToolbar();
        if(getIntent().hasExtra("title") ){
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
                v. getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        groupAdaputer = new GroupAdaputer(this);
        // 设置监听器。
        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        mRecyclerView.setItemViewSwipeEnabled(false);
        mRecyclerView.setAdapter(groupAdaputer);
        mPtrFrame = findViewById(R.id.rotate_header_grid_view_frame);
        mPtrFrame.setMinimumHeight(100);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 3000);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(true);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 100);
    }


    BingText bt = new BingText() {

        @Override
        public String bingText(int year, int month, int day) {
            return "400";
        }
    };

    private void initView() {
        weekCalendarView = findViewById(R.id.week_calendar);
        monthCalendarView = findViewById(R.id.month_calendar);
        monthCalendarView.setTodayToView();

        schedule_layout = findViewById(R.id.schedule_layout);
        schedule_layout.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
            }

            @Override
            public void onPageChange(int year, int month, int day) {
            }
        });
        SparseArray<MonthView> monthViews = monthCalendarView.getMonthViews();
        monthCalendarView.getCurrentMonthView().bingUserText(bt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        int childCount = decorView.getChildCount();
       iLog(childCount+"");
    }
    public void  get(){
        String fullUrl = Contact.getFullUrl(Contact.PHOTO_SCHEME_BY_MONTH, Contact.TOKEN, "2018-05-01", App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<PhotoSchemeMonthEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoSchemeMonthEntity>(fullUrl, RequestMethod.POST, PhotoSchemeMonthEntity.class);
        HttpListener<PhotoSchemeMonthEntity> searchByCustmor = new HttpListener<PhotoSchemeMonthEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoSchemeMonthEntity> response) {
                if (response.get().isOK()){
                    List<PhotoSchemeMonthEntity.DataBean> data = response.get().getData();
                    setMonthAdapter(data);
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoSchemeMonthEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
    private void setMonthAdapter(List<PhotoSchemeMonthEntity.DataBean> data) {

    }
    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        for (int i = start; i < start + 50; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }

}

