package com.suxuantech.erpsys.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.yanzhenjie.statusview.StatusUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusUtils.setFullToNavigationBar(this);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
            startActivity(MainActivity.class);
            finish();
            }

        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
     //  overridePendingTransition (R.anim.anim_enter,R.anim.anim_exit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        Slide slide = new Slide();
//        slide.setDuration(1000);
//            Explode ex = new Explode();
//            ex.setDuration(2500);
//            getWindow().setEnterTransition(ex);
        }else {
          //  overridePendingTransition (R.anim.anim_enter,R.anim.anim_exit);
        }
    }
    @Override
    protected void widgetClick(View v) {

    }
}
