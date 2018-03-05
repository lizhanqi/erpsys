package com.suxuantech.erpsys.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.BaseActivity;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.Permission;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ezy.assist.compat.SettingsCompat;

public class SplashScreenActivity extends BaseActivity {

    private AlertDialog alertDialog;

    @Override
    public void permissionGrantedResult(List<String> permissions) {
        if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)){
            TimerTask task = new TimerTask(){
                @Override
                public void run(){
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                    finish();
                }

            };
            Timer timer = new Timer();
            timer.schedule(task, 3000);
        } else {
            alertDialog.show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        hideStatus();
        hideSystemNavigation();
        PackageManager pm =getPackageManager();
        String appName = getApplicationInfo().loadLabel(pm).toString();
        alertDialog = AlertDialog.newBuilder(SplashScreenActivity.this)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(appName+"需要在其他应用上层显示权限(悬浮窗权限).\n用来显示紧急公告!")
                .setPositiveButton(R.string.resume, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoSetting();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
    }
    boolean isToSetting;
  private  void gotoSetting(){
      PackageManager pm =getPackageManager();
      String appName = getApplicationInfo().loadLabel(pm).toString();
      Toast.makeText(this,appName+"需要在其他应用上层显示权限",Toast.LENGTH_LONG).show();
      // 跳转到悬浮窗权限设置页
      SettingsCompat.manageDrawOverlays(this);
      isToSetting=true;
  }

    @Override
    protected void onResume() {
        super.onResume();
        if(! hasPermission(Permission.Group.STORAGE)){
            requestPermission(Permission.Group.STORAGE);
        }else {
            if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)){
                TimerTask task = new TimerTask(){
                    @Override
                    public void run(){
                        startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                        finish();
                    }

                };
                Timer timer = new Timer();
                timer.schedule(task, 3000);
            } else {
                alertDialog.show();
            }
        }
    }
}
