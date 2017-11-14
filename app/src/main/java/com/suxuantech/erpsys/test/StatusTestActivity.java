package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.yanzhenjie.statusview.StatusUtils;

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
 * @author Created by 李站旗 on 2017/11/4 18:03 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class StatusTestActivity extends StatusImmersedBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_test);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.or1).setOnClickListener(this);
        findViewById(R.id.or2).setOnClickListener(this);
        findViewById(R.id.c2).setOnClickListener(this);
        findViewById(R.id.c1).setOnClickListener(this);
        findViewById(R.id.tb1).setOnClickListener(this);
        findViewById(R.id.tb2).setOnClickListener(this);
        findViewById(R.id.tsy).setOnClickListener(this);
        findViewById(R.id.q1).setOnClickListener(this);
        findViewById(R.id.q2).setOnClickListener(this);
        setSupportToolbar();
    }
    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //这种方式作用小于setNavigationBarColor
//                DrawableCompat.setTint(mNavigationView.getBackground().mutate(),  getResources().getColor(R.color.album_ColorPrimaryBlack));
               // mNavigationView.setBackgroundColor(  getResources().getColor(R.color.album_ColorPrimaryBlack));
                //这里一定要在调用一次因为如果系统设置了背景色,那么侵入的view就无效了,需要再次侵入一次
//                StatusUtils.setFullToNavigationBar(this);
                getNavigationView().setBackgroundColor(  getResources().getColor(R.color.album_ColorPrimaryBlack));
                StatusUtils.setFullToNavigationBar(this);
              //  StatusUtils.setStatusBarColor(this, getResources().getColor(R.color.green));
                break;
            case R.id.button1:
                StatusUtils.setNavigationBarColor(this, getResources().getColor(R.color.green));
                break;
            case R.id.or1:
                //这种方式作用小于setNavigationBarColor
                // DrawableCompat.setTint(mStatusView.getBackground().mutate(),  getResources().getColor(R.color.album_ColorPrimaryBlack));
                getStatusView().setBackgroundColor(  getResources().getColor(R.color.album_ColorPrimaryBlack));
                StatusUtils.setFullToStatusBar(this);
                break;
            case R.id.or2:
                StatusUtils.setStatusBarColor(this, getResources().getColor(R.color.green));
                break;
            case R.id.c1:
                StatusUtils.setSystemBarColor(this,getResources().getColor(R.color.white),getResources().getColor(R.color.white));
                break;
            case R.id.c2:
                break;
            case R.id.q1:
                hideStatus();
                break;
            case R.id.q2:
                showStatus();
                break;
            case R.id.tb1:

                break;
            case R.id.tb2:

                break;
            case R.id.tsy:
                screenMustOrientation(true);
                break;

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

