package com.suxuantech.erpsys.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suxuantech.erpsys.activity.base.BaseActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ezy.assist.compat.SettingsCompat;

import static com.yanzhenjie.permission.AndPermission.defaultSettingDialog;

public class SplashScreenActivity extends BaseActivity {


    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
        if(hasPermission){
            requestMustPermission(true);
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatus();
        hideSystemNavigation();
        addMustPermission( Manifest.permission.READ_EXTERNAL_STORAGE);
        addMustPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addMustPermission( Manifest.permission.READ_PHONE_STATE);
        addMustPermission( Manifest.permission.READ_CONTACTS);
        if (SettingsCompat.canDrawOverlays(this)){
         requestMustPermission(true );
        } else {
            defaultSettingDialog(this).setNegativeButton("好的去设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    gotoSetting();
                }
            }).setMessage("我们需要在其他应用上层显示为您提供公司的系统通知,需要悬浮框权限,请给与我们").setPositiveButton("aaa").show();
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
