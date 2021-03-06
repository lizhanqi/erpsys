package com.jeek.calendar.widget.calendar.month;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.jeek.calendar.library.R;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;

import org.joda.time.DateTime;

/**
 * Created by Jimmy on 2016/10/6 0006.
 */
public class MonthAdapter extends PagerAdapter {
    private SparseArray<MonthView> mViews;
    private Context mContext;
    private TypedArray mArray;
    private MonthCalendarView mMonthCalendarView;
    private int mMonthCount= ScheduleLayout.YEAR*12;
       public MonthAdapter(Context context, TypedArray array, MonthCalendarView monthCalendarView) {
        mContext = context;
        mArray = array;
        mMonthCalendarView = monthCalendarView;
        mViews = new SparseArray<>();
//        mMonthCount = array.getInteger(R.styleable.MonthCalendarView_month_count, 48);
    }

    @Override
    public int getCount() {
        return mMonthCount;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mViews.get(position) == null) {
            int date[] = getYearAndMonth(position);
            MonthView monthView = new MonthView(mContext, mArray, date[0], date[1]);
            monthView.setId(position);
            monthView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            monthView.invalidate();
            monthView.setOnDateClickListener(mMonthCalendarView);
            mViews.put(position, monthView);
        }
        container.addView(mViews.get(position));
        return mViews.get(position);
    }
    //获得年和月
    private int[] getYearAndMonth(int position) {
        int date[] = new int[2];
        DateTime time = new DateTime();
        time = time.plusMonths(position - mMonthCount / 2);
        date[0] = time.getYear();
        date[1] = time.getMonthOfYear() - 1;
        return date;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public SparseArray<MonthView> getViews() {
        return mViews;
    }
    public int getMonthCount() {
        return mMonthCount;
    }
  public   interface  UserDraw{
        public void  draw(int year, int month, int day, int startX, int startY, Paint mPaint, Canvas canvas);
    }


//    public void  drawUserInf(MonthAdapter.UserDraw draw){
//        for (int i = 0; i <mViews.size() ; i++) {
//            MonthView monthView = mViews.get(i);
//            if (monthView!=null){
//                monthView.drawUserText(draw);
//            }
//        }
//    }
}
