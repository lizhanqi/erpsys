package com.suxuantech.erpsys.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;

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
 * @author Created by 李站旗 on 2018/4/26 0026 15:05 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 可以自定义String的颜色,大小,等,TextView可以通过append,追加文本,就能实现textview显示多种大小,颜色的文字
 */

public class MyString extends SpannableString {
    String str;
    int color;
    int size;
    String url;
    ////设置颜色
//spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FE6026")), 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////设置字体大小，true表示前面的字体大小20单位为dip
//spannableString.setSpan(new AbsoluteSizeSpan(20, true), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////设置链接
//spannableString.setSpan(new URLSpan("www.baidu.com"), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    public MyString(CharSequence source) {
        super(source);
    }
    public MyString(CharSequence source, String str, @IntRange(from = 0, to = 255) int color, int size, String url) {
        super(source);
        this.str = str;
        setColor(color);
        setSize(size);
        setUrl(url);
    }
//设置删除线
public MyString setDeletLine(){
    setSpan(new StrikethroughSpan(), 0, length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    return this;
}
    public MyString setColor(@ColorInt  int color) {
        this.color = color;
        setSpan(new ForegroundColorSpan(color), 0, length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    /**
     * 示例: setColor("#FE6026");
     *
     * @param mcolor
     */
    public MyString setColor(String mcolor) {
        this.color = Color.parseColor(mcolor);
        setSpan(new ForegroundColorSpan(color), 0, length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    /**
     * @param size
     */
    public MyString setSize(int size) {
        this.size = size;
        setSpan(new AbsoluteSizeSpan(size, true), 0, length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public MyString setUrl(String url) {
        this.url = url;
        setSpan(new URLSpan(url), 0, length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }
}
