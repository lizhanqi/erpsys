package com.suxuantech.erpsys.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.yanzhenjie.permission.Permission;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ezy.assist.compat.SettingsCompat;

public class SplashScreenActivity extends BaseActivity {
    @Override
    public void permissionGrantedResult(List<String> permissions) {
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }

        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatus();
        hideSystemNavigation();
        requestPermission(Permission.Group.CAMERA);
        if (SettingsCompat.canDrawOverlays(this)){
        } else {
        }
  }
  private  void gotoSetting(){
      PackageManager pm =getPackageManager();
      String appName = getApplicationInfo().loadLabel(pm).toString();
      Toast.makeText(this,appName+"需要在其他应用上层显示权限",Toast.LENGTH_LONG).show();
      // 跳转到悬浮窗权限设置页
      SettingsCompat.manageDrawOverlays(this);
  }
    @Override
    protected void widgetClick(View v) {

    }
}
