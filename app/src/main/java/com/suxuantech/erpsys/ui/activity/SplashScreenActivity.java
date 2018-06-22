package com.suxuantech.erpsys.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.BaseActivity;
import com.suxuantech.erpsys.utils.DisplayCutoutUtils;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import ezy.assist.compat.SettingsCompat;

public class SplashScreenActivity extends BaseActivity {
    private AlertDialog alertDialog;

    @Override
    public void permissionGrantedResult(List<String> permissions) {
        if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)) {
            transitionsActivity();
        } else {
            alertDialog.show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        DisplayCutoutUtils displayCutoutUtils = new DisplayCutoutUtils(this);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            displayCutoutUtils.openFullScreenModel();
        }
        setSwipeBackEnable(false);
        PackageManager pm = getPackageManager();
        String appName = getApplicationInfo().loadLabel(pm).toString();
        alertDialog = AlertDialog.newBuilder(SplashScreenActivity.this)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(appName + "需要在其他应用上层显示权限(悬浮窗权限).\n用来显示紧急公告!")
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

    private void gotoSetting() {
        PackageManager pm = getPackageManager();
        String appName = getApplicationInfo().loadLabel(pm).toString();
        Toast.makeText(this, appName + "需要在其他应用上层显示权限", Toast.LENGTH_LONG).show();
        // 跳转到悬浮窗权限设置页
        SettingsCompat.manageDrawOverlays(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        ScreenUtils.setFullScreen(this);
        if (!hasPermission(Permission.Group.STORAGE)) {
            requestPermission(Permission.Group.STORAGE);
        } else {
            if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)) {
                transitionsActivity();
            } else {
                alertDialog.show();
            }
        }
    }
    private void transitionsActivity() {
        if (islogin()) {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();
        }
    }

    /**
     *是否登录过
     * @return
     */
    private boolean islogin() {
        String name = SPUtils.getInstance().getString(LoginActivity.LOGIN_NAME);
        String passWord = SPUtils.getInstance().getString(LoginActivity.LOGIN_PASSWORD);
        if (!StringUtils.empty(name) && App.getApplication().getLoginData() != null) {
            return true;
        }
        return false;
    }
}
