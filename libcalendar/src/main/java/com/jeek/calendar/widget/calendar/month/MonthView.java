package com.jeek.calendar.widget.calendar.month;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jeek.calendar.library.R;
import com.jeek.calendar.widget.calendar.BingText;
import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.LunarCalendarUtils;
import com.jeek.calendar.widget.calendar.data.ScheduleDao;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Jimmy on 2016/10/6 0006.
 * 绘制一个月的天数
 */
public class MonthView extends View {
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private Paint mPaint;
    private Paint mLunarPaint;
    private int mNormalDayColor;//正常日期颜色
    private int mSelectDayColor; //选中日期颜色
    private int mSelectBGColor; //选中别经
    private int mSelectBGTodayColor;//选中进他别经
    private int mCurrentDayColor;
    private int mHintCircleColor;
    private int mLunarTextColor;
    private int mHolidayTextColor;
    private int mLastOrNextMonthTextColor;
    private int mCurrYear, mCurrMonth, mCurrDay;
    private int mSelYear, mSelMonth, mSelDay;
    private int mColumnSize, mRowSize, mSelectCircleSize;
    private int mDaySize;
    private int mLunarTextSize;
    private int mWeekRow; // 当前月份共几周
    private int mCircleRadius = 6;
    private int[][] mDaysText;
    private int[] mHolidays;
    private String[][] mHolidayOrLunarText;
    private boolean mIsShowLunar;
    private boolean mIsShowHint;
    private boolean mIsShowHolidayHint;
    private DisplayMetrics mDisplayMetrics;
    private OnMonthClickListener mDateClickListener;
    private GestureDetector mGestureDetector;
    private Bitmap mRestBitmap, mWorkBitmap;

    public MonthView(Context context, int year, int month) {
        this(context, null, year, month);
    }

    public MonthView(Context context, TypedArray array, int year, int month) {
        this(context, array, null, year, month);
    }

    public MonthView(Context context, TypedArray array, AttributeSet attrs, int year, int month) {
        this(context, array, attrs, 0, year, month);
    }

    public MonthView(Context context, TypedArray array, AttributeSet attrs, int defStyleAttr, int year, int month) {
        super(context, attrs, defStyleAttr);
        initAttrs(array, year, month);
        initPaint();
        initMonth();
        initGestureDetector();
        initTaskHint();
    }

    private void initTaskHint() {
        if (mIsShowHint) {
            // 从数据库中获取圆点提示数据
            ScheduleDao dao = ScheduleDao.getInstance(getContext());
            CalendarUtils.getInstance(getContext()).addTaskHints(mSelYear, mSelMonth, dao.getTaskHintByMonth(mSelYear, mSelMonth));
        }
    }

    private void initGestureDetector() {
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                doClickAction((int) e.getX(), (int) e.getY());
                return true;
            }
        });
    }

    private void initAttrs(TypedArray array, int year, int month) {
        if (array != null) {
            mSelectDayColor = array.getColor(R.styleable.MonthCalendarView_month_selected_text_color, Color.parseColor("#FFFFFF"));
            mSelectBGColor = array.getColor(R.styleable.MonthCalendarView_month_selected_circle_color, Color.parseColor("#E8E8E8"));
            mSelectBGTodayColor = array.getColor(R.styleable.MonthCalendarView_month_selected_circle_today_color, Color.parseColor("#FF8594"));
            mNormalDayColor = array.getColor(R.styleable.MonthCalendarView_month_normal_text_color, Color.parseColor("#575471"));
            mCurrentDayColor = array.getColor(R.styleable.MonthCalendarView_month_today_text_color, Color.parseColor("#FF8594"));
            mHintCircleColor = array.getColor(R.styleable.MonthCalendarView_month_hint_circle_color, Color.parseColor("#FE8595"));
            mLastOrNextMonthTextColor = array.getColor(R.styleable.MonthCalendarView_month_last_or_next_month_text_color, Color.parseColor("#ACA9BC"));
            mLunarTextColor = array.getColor(R.styleable.MonthCalendarView_month_lunar_text_color, Color.parseColor("#ACA9BC"));
            mHolidayTextColor = array.getColor(R.styleable.MonthCalendarView_month_holiday_color, Color.parseColor("#A68BFF"));
            mDaySize = array.getInteger(R.styleable.MonthCalendarView_month_day_text_size, 13);
            mLunarTextSize = array.getInteger(R.styleable.MonthCalendarView_month_day_lunar_text_size, 8);
            mIsShowHint = array.getBoolean(R.styleable.MonthCalendarView_month_show_task_hint, true);
            mIsShowLunar = array.getBoolean(R.styleable.MonthCalendarView_month_show_lunar, true);
            mIsShowHolidayHint = array.getBoolean(R.styleable.MonthCalendarView_month_show_holiday_hint, true);
        } else {
            mSelectDayColor = Color.parseColor("#FFFFFF");
            mSelectBGColor = Color.parseColor("#E8E8E8");
            mSelectBGTodayColor = Color.parseColor("#FF8594");
            mNormalDayColor = Color.parseColor("#575471");
            mCurrentDayColor = Color.parseColor("#FF8594");
            mHintCircleColor = Color.parseColor("#FE8595");
            mLastOrNextMonthTextColor = Color.parseColor("#ACA9BC");
            mHolidayTextColor = Color.parseColor("#A68BFF");
            mDaySize = 13;
            mLunarTextSize = 8;
            mIsShowHint = true;
            mIsShowLunar = true;
            mIsShowHolidayHint = true;
        }
        mSelYear = year;
        mSelMonth = month;
        mRestBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_rest_day);
        mWorkBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_work_day);
        mHolidays = CalendarUtils.getInstance(getContext()).getHolidays(mSelYear, mSelMonth + 1);
    }

    private void initPaint() {
        mDisplayMetrics = getResources().getDisplayMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mDaySize * mDisplayMetrics.scaledDensity);

        mLunarPaint = new Paint();
        mLunarPaint.setAntiAlias(true);
        mLunarPaint.setTextSize(mLunarTextSize * mDisplayMetrics.scaledDensity);
        mLunarPaint.setColor(mLunarTextColor);
    }

    private void initMonth() {
        Calendar calendar = Calendar.getInstance();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        if (mSelYear == mCurrYear && mSelMonth == mCurrMonth) {
            setSelectYearMonth(mSelYear, mSelMonth, mCurrDay);
        } else {
            setSelectYearMonth(mSelYear, mSelMonth, 1);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        clearData();
        drawLastMonth(canvas);//上个月
        int selected[] = drawThisMonth(canvas);
        drawNextMonth(canvas);//下个月
        drawHintCircle(canvas);//默认原点
        drawLunarText(canvas, selected);//农历
        drawHoliday(canvas);//假期.
        drawUserText(canvas);
    }

    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
        mSelectCircleSize = (int) (mColumnSize / 3.2);
        while (mSelectCircleSize > mRowSize / 2) {
            mSelectCircleSize = (int) (mSelectCircleSize / 1.3);
        }
    }

    private void clearData() {
        mDaysText = new int[6][7];
        mHolidayOrLunarText = new String[6][7];
    }
    /**
     * 绘制上个月
     * @param canvas
     */
    private void drawLastMonth(Canvas canvas) {
        initPaint();
        int lastYear, lastMonth;
        if (mSelMonth == 0) {
            lastYear = mSelYear - 1;
            lastMonth = 11;
        } else {
            lastYear = mSelYear;
            lastMonth = mSelMonth - 1;
        }
        mPaint.setColor(mLastOrNextMonthTextColor);
        int monthDays = CalendarUtils.getMonthDays(lastYear, lastMonth);
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        Log.i("Month", "drawLastMonth: " + weekNumber);
        for (int day = 0; day < weekNumber - 1; day++) {
            mDaysText[0][day] = monthDays - weekNumber + day + 2;
            String dayString = String.valueOf(mDaysText[0][day]);
            int startX = (int) (mColumnSize * day + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            canvas.drawText(dayString, startX, startY, mPaint);
            mHolidayOrLunarText[0][day] = CalendarUtils.getHolidayFromSolar(lastYear, lastMonth, mDaysText[0][day]);
        }
    }

    /**
     * 绘制本月
     * @param canvas
     * @return
     */
    private int[] drawThisMonth(Canvas canvas) {
        String dayString;
        int selectedPoint[] = new int[2];
        int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        for (int day = 0; day < monthDays; day++) {
            dayString = String.valueOf(day + 1);
            int col = (day + weekNumber - 1) % 7;//列
            int row = (day + weekNumber - 1) / 7;//行
            mDaysText[row][col] = day + 1;//确定第几行第列是什么日期
            //计算开始位置
            int startX = (int) (mColumnSize * col + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            if (dayString.equals(String.valueOf(mSelDay))) {
                int startRecX = mColumnSize * col;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;
                if (mSelYear == mCurrYear && mCurrMonth == mSelMonth && day + 1 == mCurrDay) {
                    mPaint.setColor(mSelectBGTodayColor);
                } else {
                    mPaint.setColor(mSelectBGColor);
                }
                canvas.drawCircle((startRecX + endRecX) / 2, (startRecY + endRecY) / 2, mSelectCircleSize, mPaint);
                mWeekRow = row + 1;
            }
            if (dayString.equals(String.valueOf(mSelDay))) {
                selectedPoint[0] = row;
                selectedPoint[1] = col;
                mPaint.setColor(mSelectDayColor);
            } else if (dayString.equals(String.valueOf(mCurrDay)) && mCurrDay != mSelDay && mCurrMonth == mSelMonth && mCurrYear == mSelYear) {
                mPaint.setColor(mCurrentDayColor);
            } else {
                mPaint.setColor(mNormalDayColor);
            }
            canvas.drawText(dayString, startX, startY, mPaint);
            mHolidayOrLunarText[row][col] = CalendarUtils.getHolidayFromSolar(mSelYear, mSelMonth, mDaysText[row][col]);
        }
        return selectedPoint;
    }

    private void drawNextMonth(Canvas canvas) {
        mPaint.setColor(mLastOrNextMonthTextColor);
        int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);//本月天数
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);//本月开始周几
        int nextMonthDays = 42 - monthDays - weekNumber + 1;//最多42个就可表达，因为有时候35个也可以，所以这里计算  最多个数-当月天数-开始位置
        Log.i("lastMonth", "drawNextMonth: " + nextMonthDays);
        int nextMonth = mSelMonth + 1;
        int nextYear = mSelYear;
        if (nextMonth == 12) {
            nextMonth = 0;
            nextYear += 1;
        }
        for (int day = 0; day < nextMonthDays; day++) {
            int column = (monthDays + weekNumber - 1 + day) % 7;
            int row = 5 - (nextMonthDays - day - 1) / 7;
            try {
                mDaysText[row][column] = day + 1;
                mHolidayOrLunarText[row][column] = CalendarUtils.getHolidayFromSolar(nextYear, nextMonth, mDaysText[row][column]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String dayString = String.valueOf(mDaysText[row][column]);
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            canvas.drawText(dayString, startX, startY, mPaint);
        }
    }

    /**
     * 绘制农历
     *
     * @param canvas
     * @param selected
     */
    private void drawLunarText(Canvas canvas, int[] selected) {
        if (mIsShowLunar) {
            int firstYear, firstMonth, firstDay;
            int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
            if (weekNumber == 1) {
                firstYear = mSelYear;
                firstMonth = mSelMonth + 1;
                firstDay = 1;
            } else {
                int monthDays;
                if (mSelMonth == 0) {
                    firstYear = mSelYear - 1;
                    firstMonth = 11;
                    monthDays = CalendarUtils.getMonthDays(firstYear, firstMonth);
                    firstMonth = 12;
                } else {
                    firstYear = mSelYear;
                    firstMonth = mSelMonth - 1;
                    monthDays = CalendarUtils.getMonthDays(firstYear, firstMonth);
                    firstMonth = mSelMonth;
                }
                firstDay = monthDays - weekNumber + 2;
            }
            LunarCalendarUtils.Lunar lunar = LunarCalendarUtils.solarToLunar(new LunarCalendarUtils.Solar(firstYear, firstMonth, firstDay));
            int days;
            int day = lunar.lunarDay;
            int leapMonth = LunarCalendarUtils.leapMonth(lunar.lunarYear);
            days = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);
            for (int i = 0; i < 42; i++) {
                int column = i % 7;
                int row = i / 7;
                if (day > days) {
                    day = 1;
                    if (lunar.lunarMonth == 12) {
                        lunar.lunarMonth = 1;
                        lunar.lunarYear = lunar.lunarYear + 1;
                    }
                    if (lunar.lunarMonth == leapMonth) {
                        days = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);
                    } else {
                        lunar.lunarMonth++;
                        days = LunarCalendarUtils.daysInLunarMonth(lunar.lunarYear, lunar.lunarMonth);
                    }
                }
                if (row == 0 && mDaysText[row][column] >= 23 || row >= 4 && mDaysText[row][column] <= 14) {
                    mLunarPaint.setColor(mLunarTextColor);
                } else {
                    mLunarPaint.setColor(mHolidayTextColor);
                }
                String dayString = mHolidayOrLunarText[row][column];
                if ("".equals(dayString)) {
                    dayString = LunarCalendarUtils.getLunarHoliday(lunar.lunarYear, lunar.lunarMonth, day);
                }
                if ("".equals(dayString)) {
                    dayString = LunarCalendarUtils.getLunarDayString(day);
                    mLunarPaint.setColor(mLunarTextColor);
                }
                if (selected[0] == row && selected[1] == column) {
                    mLunarPaint.setColor(mSelectDayColor);
                }

                int startX = (int) (mColumnSize * column + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
                int startY = (int) (mRowSize * row + mRowSize * 0.72 - (mLunarPaint.ascent() + mLunarPaint.descent()) / 2);
                canvas.drawText(dayString, startX, startY, mLunarPaint);
                day++;
            }
        }
    }
    private void drawHoliday(Canvas canvas) {
        if (mIsShowHolidayHint) {
            Rect rect = new Rect(0, 0, mRestBitmap.getWidth(), mRestBitmap.getHeight());
            Rect rectF = new Rect();
            int distance = (int) (mSelectCircleSize / 2.5);
            for (int i = 0; i < mHolidays.length; i++) {
                int column = i % 7;
                int row = i / 7;
                rectF.set(mColumnSize * (column + 1) - mRestBitmap.getWidth() - distance, mRowSize * row + distance, mColumnSize * (column + 1) - distance, mRowSize * row + mRestBitmap.getHeight() + distance);
                if (mHolidays[i] == 1) {
                    canvas.drawBitmap(mRestBitmap, rect, rectF, null);
                } else if (mHolidays[i] == 2) {
                    canvas.drawBitmap(mWorkBitmap, rect, rectF, null);
                }
            }
        }
    }
    BingText monthUserText;
    /**
     * 绑定用户绘制内容
      * @param monthUserText
     */
  public void    bingUserText( BingText monthUserText){
     this.monthUserText=monthUserText;
      invalidate();
  }

    /**
     * 绘制用户自己文本信息
     * @param canvas
     */
    private void drawUserText(Canvas canvas) {
        if (monthUserText==null||mIsShowLunar)
            return;
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        {
            //上个月
            int lastYear, lastMonth;
            if (mSelMonth == 0) {
                lastYear = mSelYear - 1;
                lastMonth = 11;
            } else {
                lastYear = mSelYear;
                lastMonth = mSelMonth - 1;
            }
            mLunarPaint.setColor(mLunarTextColor);
            int monthLastDays = CalendarUtils.getMonthDays(lastYear, lastMonth);
            for (int day = 0; day < weekNumber - 1; day++) {
                int column = day % 7;
                int row = day / 7;
                int lastday = monthLastDays - weekNumber + day + 2;
                String dayString = String.valueOf(lastday);
                int startX = (int) (mColumnSize * column + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
                int startY = (int) (mRowSize * row + mRowSize * 0.72 - (mLunarPaint.ascent() + mLunarPaint.descent()) / 2);
//            selfDraw.draw(mSelYear,lastMonth+1,te,startX,startY,canvas,mPaint);
                String userText = monthUserText.bingText(mSelYear, lastMonth + 1, lastday);
                canvas.drawText(userText, startX - mLunarPaint.measureText(userText) / 2 + mLunarPaint.measureText(day + "") / 2, startY, mLunarPaint);
            }
        }
        //本月
        int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
        String dayString;
        for (int day = 0; day < monthDays; day++) {
            //日期
            dayString = String.valueOf(day + 1);
            int col = (day + weekNumber - 1) % 7;//列
            int row = (day + weekNumber - 1) / 7;//行
            //计算开始位置
            int startX = (int) (mColumnSize * col + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
            int startY = (int) (mRowSize * row + mRowSize * 0.72 - (mLunarPaint.ascent() + mLunarPaint.descent()) / 2);

            //如果是选中的日子
            if (dayString.equals(String.valueOf(mSelDay))) {
                mLunarPaint.setColor(mSelectDayColor);//当前位置字体的颜色
            } else {
                mLunarPaint.setColor(mLunarTextColor);//已经选择的颜色
            }
//            selfDraw.draw(mSelYear,mSelMonth+1,day + 1,startX,startY,canvas,mLunarPaint);
            String userText = monthUserText.bingText(mSelYear,mSelMonth+1,day + 1);
            canvas.drawText(userText, startX - mLunarPaint.measureText(userText) / 2 + mLunarPaint.measureText(day + "") / 2, startY, mLunarPaint);
        }
        //下个月绘制
        {
            int need = 42;
//        if (weekNumber - 1+monthDays<=35){//最多42个就可表达，因为有时候35个也可以，所以这里计算  最多个数-当月天数-开始位置
//            need=35;//这里别用了下面计算会有问题
//        }
            //下月需要多少天
            int nextMonthDays = need - monthDays - weekNumber + 1;//最多42个就可表达，因为有时候35个也可以，所以这里计算  最多个数-当月天数-开始位置
            int nextMonth = mSelMonth + 1;
            int nextYear = mSelYear;
            //判断当前月份是否是12月如果是12月那么下一个月就是新的一年
            if (nextMonth == 12) {
                nextMonth = 0;
                nextYear += 1;
            }
            for (int day = 0; day < nextMonthDays; day++) {
                int column = (monthDays + weekNumber - 1 + day) % 7;//行
                int row = 5 - (nextMonthDays - day - 1) / 7;//列
                int currentday = day + 1;
                //当前日期文字
                dayString = String.valueOf(mDaysText[row][column]);
                int startX = (int) (mColumnSize * column + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
                int startY = (int) (mRowSize * row + mRowSize * 0.72 - (mLunarPaint.ascent() + mLunarPaint.descent()) / 2);
                mLunarPaint.setColor(mLunarTextColor);
                //selfDraw.draw(nextYear,nextMonth+1,currentday,startX,startY,canvas,mLunarPaint);
                String userText = monthUserText.bingText(nextYear, nextMonth + 1, currentday);
                canvas.drawText(userText, startX - mLunarPaint.measureText(userText) / 2 + mLunarPaint.measureText(day + "") / 2, startY, mLunarPaint);
            }
        }
    }
    /**
     * 绘制圆点提示
     *
     * @param canvas
     */
    private void drawHintCircle(Canvas canvas) {
        if (mIsShowHint) {
            List<Integer> hints = CalendarUtils.getInstance(getContext()).getTaskHints(mSelYear, mSelMonth);
            if (hints.size() > 0) {
                mPaint.setColor(mHintCircleColor);
                int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
                int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
                for (int day = 0; day < monthDays; day++) {
                    int col = (day + weekNumber - 1) % 7;
                    int row = (day + weekNumber - 1) / 7;
                    if (!hints.contains(day + 1)) continue;
                    float circleX = (float) (mColumnSize * col + mColumnSize * 0.5);
                    float circleY = (float) (mRowSize * row + mRowSize * 0.75);
                    canvas.drawCircle(circleX, circleY, mCircleRadius, mPaint);
                }
            }
        }
    }
    @Override
    public boolean performClick() {
        return super.performClick();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    /**
     * 设置选中的年月日
     * @param year
     * @param month 0-11
     * @param day 1-31
     */
    public void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }
    private void doClickAction(int x, int y) {
        if (y > getHeight())
            return;
        int row = y / mRowSize;
        int column = x / mColumnSize;
        column = Math.min(column, 6);
        int clickYear = mSelYear, clickMonth = mSelMonth;
        if (row == 0) {
            if (mDaysText[row][column] >= 23) {
                if (mSelMonth == 0) {
                    clickYear = mSelYear - 1;
                    clickMonth = 11;
                } else {
                    clickYear = mSelYear;
                    clickMonth = mSelMonth - 1;
                }
                if (mDateClickListener != null) {
                    mDateClickListener.onClickLastMonth(clickYear, clickMonth, mDaysText[row][column]);
                }
            } else {
                clickThisMonth(clickYear, clickMonth, mDaysText[row][column]);
            }
        } else {
            int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
            int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
            int nextMonthDays = 42 - monthDays - weekNumber + 1;
            if (mDaysText[row][column] <= nextMonthDays && row >= 4) {
                if (mSelMonth == 11) {
                    clickYear = mSelYear + 1;
                    clickMonth = 0;
                } else {
                    clickYear = mSelYear;
                    clickMonth = mSelMonth + 1;
                }
                if (mDateClickListener != null) {
                    mDateClickListener.onClickNextMonth(clickYear, clickMonth, mDaysText[row][column]);
                }
            } else {
                clickThisMonth(clickYear, clickMonth, mDaysText[row][column]);
            }
        }
    }
    /**
     * 跳转到某日期
     * @param year
     * @param month
     * @param day
     */
    public void clickThisMonth(int year, int month, int day) {
        if (mDateClickListener != null) {
            mDateClickListener.onClickThisMonth(year, month, day);
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }
    /**
     * 获取当前选择年
     *
     * @return
     */
    public int getSelectYear() {
        return mSelYear;
    }

    /**
     * 获取当前选择月
     *
     * @return
     */
    public int getSelectMonth() {
        return mSelMonth;
    }

    /**
     * 获取当前选择日
     * @return
     */
    public int getSelectDay() {
        return this.mSelDay;
    }

    public int getRowSize() {
        return mRowSize;
    }

    public int getWeekRow() {
        return mWeekRow;
    }

    /**
     * 添加多个圆点提示
     *
     * @param hints
     */
    public void addTaskHints(List<Integer> hints) {
        if (mIsShowHint) {
            CalendarUtils.getInstance(getContext()).addTaskHints(mSelYear, mSelMonth, hints);
            invalidate();
        }
    }

    /**
     * 删除多个圆点提示
     *
     * @param hints
     */
    public void removeTaskHints(List<Integer> hints) {
        if (mIsShowHint) {
            CalendarUtils.getInstance(getContext()).removeTaskHints(mSelYear, mSelMonth, hints);
            invalidate();
        }
    }

    /**
     * 添加一个圆点提示
     *
     * @param day
     */
    public boolean addTaskHint(Integer day) {
        if (mIsShowHint) {
            if (CalendarUtils.getInstance(getContext()).addTaskHint(mSelYear, mSelMonth, day)) {
                invalidate();
                return true;
            }
        }
        return false;
    }

    /**
     * 删除一个圆点提示
     *
     * @param day
     */
    public boolean removeTaskHint(Integer day) {
        if (mIsShowHint) {
            if (CalendarUtils.getInstance(getContext()).removeTaskHint(mSelYear, mSelMonth, day)) {
                invalidate();
                return true;
            }
        }
        return false;
    }

    /**
     * 设置点击日期监听
     *
     * @param dateClickListener
     */
    public void setOnDateClickListener(OnMonthClickListener dateClickListener) {
        this.mDateClickListener = dateClickListener;
    }

}

