package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.BingText;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.suxuantech.erpsys.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class CanlanderActivity extends AppCompatActivity {
    private MonthCalendarView monthCalendarView;
    private ScheduleLayout schedule_layout;
    private TextView viewById;
    private PtrClassicFrameLayout mPtrFrame;
    BingText bt = new BingText() {
        @Override
        public String bingText(int year, int month, int day) {
            if (month==10&&day==31){
                return "10月底";
            }
            if (month==11&&day==11){
                return "光棍节";
            }
            if (month==11&&day==30){
                return "11月30";
            }
            if (month==11&&day==31){
                return "11月31号";
            }
            if (month==12&&day==1){
                return "12月1号";
            }
            if (month==11&&day==10){
                return "11月10号";
            }
            if (month==1&&day==1){
                return "元旦";
            }       if (month==1&&day==6){
                return "1月6";
            }


            return "";
        }
    };
    private WeekCalendarView weekCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canlander);
        initView();
        SwipeMenuRecyclerView mRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.rvScheduleList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        MainAdapter mAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addFooterView(getLayoutInflater().inflate(R.layout.head_test,null));
        mRecyclerView.useDefaultLoadMore();
        List<String> dataList = createDataList(0);
        mAdapter.notifyDataSetChanged(dataList);
        mRecyclerView.loadMoreFinish(false, true);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_grid_view_frame);
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
    private void initView() {
        weekCalendarView = (WeekCalendarView) findViewById(R.id.week_calendar);

        viewById  = (TextView) findViewById(R.id.tvshows);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthCalendarView.getCurrentMonthView().bingUserText(bt);
                weekCalendarView.getCurrentWeekView().bingUserText(bt);
            }
        });

        findViewById(R.id.today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthCalendarView.setTodayToView();
            }
        });


        monthCalendarView = (MonthCalendarView) findViewById(R.id.month_calendar);
        monthCalendarView.setTodayToView();

          schedule_layout = (ScheduleLayout) findViewById(R.id.schedule_layout);
        schedule_layout.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                CanlanderActivity.this.viewById.setText(year+"年"+(++month)+"月"+day);
            }

            @Override
            public void onPageChange(int year, int month, int day) {
                CanlanderActivity.this.viewById.setText(year+"年"+(++month)+"月"+day);
            }
        });


    }
    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        for (int i = start; i < start + 50; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }

}
