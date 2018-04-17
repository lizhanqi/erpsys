package com.suxuantech.erpsys.ui.dialog;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
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
 * @author Created by 李站旗 on 2018/4/17 0017 11:17 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 加载中
 */

public class LoadingView  extends ProgressDialog {
    public LoadingView(Activity context) {
        this(context,R.style.CustomDialog);
    }
    public LoadingView(Fragment context) {
        this(context.getActivity(),R.style.CustomDialog);
    }
    public LoadingView(Activity context, int theme) {
        super(context, theme);
        setOwnerActivity(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }
    @Override
    public void show() {
        Activity activity = getOwnerActivity();
        if ( activity != null && !activity.isFinishing() ) {
            super.show();
        }
    }
    @Override
    public void dismiss() {
        if (isShowing()) {
            Activity activity = getOwnerActivity();
            if ( activity != null && !activity.isFinishing()) {
                super.dismiss();
            }
        }
    }
}

