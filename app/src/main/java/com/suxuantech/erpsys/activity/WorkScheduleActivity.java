package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.suxuantech.erpsys.views.ScheduleSwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 工作排程
 */
public class WorkScheduleActivity extends ImmersedBaseActivity {

    @BindView(R.id.tv_in_service)
    TextView mTvInService;
    @BindView(R.id.month_calendar)
    MonthCalendarView mMonthCalendar;
    @BindView(R.id.week_calendar)
    WeekCalendarView mWeekCalendar;
    @BindView(R.id.rvScheduleList)
    ScheduleSwipeMenuRecyclerView mRvScheduleList;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    @BindView(R.id.rlScheduleList)
    RelativeLayout mRlScheduleList;
    @BindView(R.id.schedule_layout)
    ScheduleLayout mScheduleLayout;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_schedule);
        ButterKnife.bind(this);
        showUserDefinedNav();
        setTitle("工作排程");
        int currentSelectDay = mScheduleLayout.getCurrentSelectDay();
        setUseDefinedNavRightText(currentSelectDay+"");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("111");        strings.add("111");        strings.add("111");        strings.add("111");        strings.add("111");
        new BaseRecyclerAdapter<String>(mRvScheduleList, strings, R.layout.item_work_schedule) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
            }
        };
        mRvScheduleList.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.myBackground_f7),0,30));
        mRotateHeaderGridViewFrame.getHeader().setBackgroundColor(getResources().getColor(R.color.themeColor));
        mRotateHeaderGridViewFrame. setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRotateHeaderGridViewFrame.refreshComplete();
                    }
                }, 3000);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mScheduleLayout.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                month++;
                setUseDefinedNavRightText(year+""+month+""+day);
            }

            @Override
            public void onPageChange(int year, int month, int day) {
                month++;
                setUseDefinedNavRightText(year+""+month+""+day);
            }
        });

    }

    @Override
    protected void widgetClick(View v) {
    }
    @OnClick({R.id.tv_in_service, R.id.month_calendar, R.id.week_calendar, R.id.rvScheduleList, R.id.rotate_header_grid_view_frame, R.id.rlScheduleList, R.id.schedule_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
            mMonthCalendar.setTodayToView();
            break;

            case R.id.tv_in_service:
                break;
            case R.id.month_calendar:
                break;
            case R.id.week_calendar:
                break;
            case R.id.rvScheduleList:
                break;
            case R.id.rotate_header_grid_view_frame:
                break;
            case R.id.rlScheduleList:
                break;
            case R.id.schedule_layout:
                break;
        }
    }
}
