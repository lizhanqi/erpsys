package com.suxuantech.erpsys.ui.dialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.suxuantech.erpsys.R;
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
 * @author Created by 李站旗 on 2018/5/8 0008 16:30 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 仿照小米底部弹窗
 * 用法:
 *CustomBottomDialog customBottomDialog = new CustomBottomDialog(LayoutInflater.from(this).inflate(R.layout.dialog_layout, null), this, R.style.style_dialog);
 *customBottomDialog.show();
 */



/**
 * Created by NiYang on 2017/4/16.
 */

public class CustomBottomDialog extends Dialog {
    private Context mContext = null;

    public CustomBottomDialog(View contentView, Context context) {
        super(context);
        this.mContext = context;
        init(contentView);
    }

    public CustomBottomDialog(View contentView, Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        init(contentView);
    }

    protected CustomBottomDialog(View contentView, Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        init(contentView);
    }

    private void init(View contentView) {
        setContentView(contentView);
        Window window = getWindow();//获取Dialog的Window对象
        window.setGravity(Gravity.BOTTOM);//把Dialog的窗体设置在屏幕的底部
        window.setWindowAnimations(R.style.dialog_animation);//给窗体设置动画
        //获取的是Dialog窗体的布局参数,注意这里不是Dialog中的View的布局参数
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = 15;//设置Dialog的窗体距离屏幕底部的距离
        //获取Activity的Window管理器
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        //设置Dialog的窗体的宽度,屏幕的宽度-15
        lp.width = windowManager.getDefaultDisplay().getWidth()-dp2px(15);
        window.setAttributes(lp);
    }

    /**
     * dp转px
     * @param dp dp值
     * @return 转换后的px的值
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param sp sp值
     * @return 转换后的px值
     */
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }
}