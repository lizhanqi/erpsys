package com.suxuantech.erpsys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
 * @author Created by 李站旗 on 2017/11/7 19:30 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 时间工具
 */

public class DateUtil {

    private DateUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 枚举日期格式
     */
    public enum DatePattern {
        /**
         * 格式："yyyy-MM-dd HH:mm:ss"
         */
        ALL_TIME {
            @Override
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式："yyyy-MM"
         */
        ONLY_MONTH {
            @Override
            public String getValue() {
                return "yyyy-MM";
            }
        },
        /**
         * 格式："yyyy-MM-dd"
         */
        ONLY_DAY {
            @Override
            public String getValue() {
                return "yyyy-MM-dd";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH"
         */
        ONLY_HOUR {
            @Override
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH:mm"
         */
        ONLY_MINUTE {
            @Override
            public String getValue() {
                return "yyyy-MM-dd HH:mm";
            }
        },
        /**
         * 格式："MM-dd"
         */
        ONLY_MONTH_DAY {
            @Override
            public String getValue() {
                return "MM-dd";
            }
        },
        /**
         * 格式："MM-dd HH:mm"
         */
        ONLY_MONTH_SEC {
            @Override
            public String getValue() {
                return "MM-dd HH:mm";
            }
        },
        /**
         * 格式："HH:mm:ss"
         */
        ONLY_TIME {
            @Override
            public String getValue() {
                return "HH:mm:ss";
            }
        },
        /**
         * 格式："HH:mm"
         */
        ONLY_HOUR_MINUTE {
            @Override
            public String getValue() {
                return "HH:mm";
            }
        },

        /**
         * 格式："HH:mm"
         */
        JUST_DAY_NUMBER {
            @Override
            public String getValue() {
                return "yyyyMMdd";
            }
        },
        /**
         * 格式："HH:mm"
         */
        JUST_YEAR_MONTH_NUMBER {
            @Override
            public String getValue() {
                return "yyyyMM";
            }
        },
        /**
         * 格式："HH:mm"
         */
        YEAR_MONTH_TEXT {
            @Override
            public String getValue() {
                return "yyyy年MM月";
            }
        },
        YEAR_MONTHE_DAY_TEXT {
            @Override
            public String getValue() {
                return "yyyy年MM月dd日";
            }
        },
        DATE_MINUTE_TEXT {
            @Override
            public String getValue() {
                return "yyyy年MM月dd日HH时mm分";
            }
        };

        public abstract String getValue();
    }

    /**
     * 时间拼接
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public String dateJoinDayTogether(int year, int month, int day, String division) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year);
        stringBuilder.append(division);
        if (month < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(month);
        stringBuilder.append(division);
        if (day < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(day);
        return stringBuilder.toString();
    }

    ;


    /**
     * 时间拼接
     *
     * @param year
     * @param month
     * @return
     */
    public String dateJoinMonthTogether(int year, int month, String division) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year);
        stringBuilder.append(division);
        if (month < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(month);
        return stringBuilder.toString();
    }

    ;

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式2017-05-04 10:54:21
     */
    public static String getNowDate(DatePattern pattern) {
        String dateString = null;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        dateString = sdf.format(dateNow);
        return dateString;
    }

    public static String DateMerger(int year, int month, int day, DatePattern datePattern) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date newDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern.getValue(), Locale.CHINA);
        String s = sdf.format(newDate);
        return s;
    }
    /**
     * 将一个日期字符串转换成Data对象
     *
     * @param dateString 日期字符串
     * @param pattern    转换格式
     * @return 返回转换后的日期对象
     */
    public static Date stringToDate(String dateString, DatePattern pattern) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间String 转换格式
     *
     * @param dateString 时间串
     * @param source     原格式
     * @param target     目标格式
     * @return 目标格式串
     */
    public static String String2String(String dateString, DatePattern source, DatePattern target) {
        if (android.text.TextUtils.isEmpty(dateString)) {
            return "";
        }
        return dateToString(stringToDate(dateString, source), target);
    }

    /**
     * 将date转换成字符串
     *
     * @param date    日期
     * @param pattern 日期的目标格式
     * @return
     */
    public static String dateToString(Date date, DatePattern pattern) {
        String string = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        string = sdf.format(date);
        return string;
    }

    /**
     * 获取指定日期周几
     *
     * @param date 指定日期
     * @return 返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六"
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0) {
            week = 0;
        }
        return weekDays[week];
    }

    /**
     * 获取指定日期对应周几的序列
     *
     * @param date 指定日期
     * @return 周一：1    周二：2    周三：3    周四：4    周五：5    周六：6    周日：7
     */
    public static int getIndexWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            return 7;
        } else {
            return --index;
        }
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月号
     *
     * @return
     */
    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取本月份的天数
     *
     * @return
     */
    public static int getNowDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE) + 1);
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 对应天数
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || year % 400 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }
}