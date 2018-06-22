package com.suxuantech.erpsys.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;


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
 * @author Created by 李站旗 on 2018/6/21 0021 11:31 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 刘海屏控制
 */
public class DisplayCutoutUtils {
    private Activity mAc;
    public DisplayCutoutUtils(Activity ac) {
        mAc = ac;
    }
    //在使用LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES的时候，状态栏会显示为白色，这和主内容区域颜色冲突,
    //所以我们要开启沉浸式布局模式，即真正的全屏模式,以实现状态和主体内容背景一致
    //@RequiresApi(api = Build.VERSION_CODES.P)
    public void openFullScreenModel(){
        mAc.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = mAc.getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        mAc.getWindow().setAttributes(lp);
        View decorView = mAc.getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        systemUiVisibility |= flags;
        mAc.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    //获取状态栏高度
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height","dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void controlView(){
        View decorView = mAc.getWindow().getDecorView();
        if(decorView != null){
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                WindowInsets windowInsets = decorView.getRootWindowInsets();
                if(windowInsets != null){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                        //getBoundingRects返回List<Rect>,没一个list表示一个不可显示的区域，即刘海屏，可以遍历这个list中的Rect,
                        //即可以获得每一个刘海屏的坐标位置,当然你也可以用类似getSafeInsetBottom的api
                        Log.d("hwj", "**controlView**" + displayCutout.getBoundingRects());
                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetBottom());
                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetLeft());
                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetRight());
                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetTop());
                    }
                }
            }
        }
    }
}
