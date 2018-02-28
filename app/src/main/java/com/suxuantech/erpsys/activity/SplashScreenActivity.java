package com.suxuantech.erpsys.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends ImmersedBaseActivity {


    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).init();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
            startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
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
