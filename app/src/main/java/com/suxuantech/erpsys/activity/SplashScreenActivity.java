package com.suxuantech.erpsys.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yanzhenjie.statusview.StatusUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusUtils.setFullToNavigationBar(this);
        super.onCreate(savedInstanceState);
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
}
