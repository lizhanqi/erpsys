package com.suxuantech.erpsys.utils;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

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
 * @author Created by 李站旗 on 2017/11/21 0021 18:21 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 一个TextView显示两种颜色的字
 */

public class TextUtils {

    public static void setColorText(TextView textView, String normalText, String colorText, int mColor){
//       红颜色
        String str=normalText+"<font color='"+textView.getResources().getColor(mColor)+"'>"+colorText+"</font>";

        textView.setText(Html.fromHtml(str)); ;
    }

    public static void setColorTextSmall(TextView textView, String normalText, String colorText, int mColor){
//        <small>红颜色</small>
        String str=normalText+"<font color='"+textView.getResources().getColor(mColor)+"'> <small>"+colorText+"</small></font>";
        textView.setText(Html.fromHtml(str)); ;
    }

    /**
     * 加粗的特殊字体
     * @param textView
     * @param normalText
     * @param colorText
     * @param mColor
     */
    public static void setColorTextStrong(TextView textView, String normalText, String colorText, int mColor){
//        <small>红颜色</small>
        String str=normalText+"<font color='"+textView.getResources().getColor(mColor)+"'> <strong>"+colorText+"</strong></font>";
        textView.setText(Html.fromHtml(str)); ;
    }

    public static void setDeleteLine(TextView textView,String deleteText ){
//        <small>红颜色</small>
        SpannableString sp = new SpannableString(deleteText);
        sp.setSpan(new StrikethroughSpan(), 0, deleteText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(sp); ;
    }
}
