package com.suxuantech.erpsys.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.CompanyDomainEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.base.BaseActivity;
import com.suxuantech.erpsys.utils.DisplayCutoutUtils;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import ezy.assist.compat.SettingsCompat;

public class SplashScreenActivity extends BaseActivity {
    private AlertDialog alertDialog;

    @Override
    public void permissionGrantedResult(List<String> permissions) {
        if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)) {
            transitionsActivity(0);
        } else {
            alertDialog.show();
        }
    }

    /*
    登录极光聊天的服务器
     */
    public void loginJG(String jgName, String jgPassWord) {
        jgPassWord = EncryptUtils.encryptMD5ToString(jgPassWord).toLowerCase();
        eLog("极光:" + jgName + "极光密码:" + jgPassWord);
        JMessageClient.login(jgName, jgPassWord, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    App.getApplication().finishActivity(LoginActivity.class);
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    App.getApplication().loginOut();
                }
            }
        });
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
//            if (SettingsCompat.canDrawOverlays(SplashScreenActivity.this)) {
//
//            } else {
//                alertDialog.show();
//            }
            transitionsActivity(1500);
        }
    } 

    private void transitionsActivity(int delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (islogin()) {
                    String cacheID = SPUtils.getInstance().getString(LoginActivity.COMPANY_ID);
                    if (!StringUtils.empty(cacheID)) {
                        getCompanyDomainById(cacheID, true);
                    } else {
                        App.getApplication().loginOut();
                    }
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, delayMillis);
    }

    /**
     * 获取客户的域名地址
     *
     * @param companyID 客户企业id
     * @param isCache   是否从缓存去拿
     */
    public void getCompanyDomainById(String companyID, boolean isCache) {
        JavaBeanRequest stringRequest = new JavaBeanRequest(Contact.COMPANY_DOMAIN, CompanyDomainEntity.class);
        stringRequest.add("app_code", companyID);
        String key = "app_code=" + companyID + Contact.CONTACT_KEY;
        key = EncryptUtils.encryptMD5ToString(key).toLowerCase();
        stringRequest.addHeader("ACCESS-TOKEN", key);
        if (isCache) {
            stringRequest.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        } else {
            stringRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        }
        HttpListener<CompanyDomainEntity> httpListener = new HttpListener<CompanyDomainEntity>() {
            @Override
            public void onSucceed(int what, Response<CompanyDomainEntity> response) {
                if (response.get().isOK()) {
                    List<CompanyDomainEntity.DataBean> data = response.get().getData();
                    LoginActivity.domainAssignment(data);
                    if (App.CARE_IM_LOGIN){
                        loginJG(App.getApplication().getUserInfor().getJg_username(), App.getApplication().getUserInfor().getStaffnumber());
                    }else {
                        App.getApplication().finishActivity(LoginActivity.class);
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    ToastUtils.snackbarShort("企业域名错误");
                    App.getApplication().loginOut();
                }
            }

            @Override
            public void onFailed(int what, Response<CompanyDomainEntity> response) {
                ToastUtils.snackbarShort("企业域名错误");
                App.getApplication().loginOut();
            }
        };
        request(0, stringRequest, httpListener, false, false);
    }

    /**
     * 是否登录过
     *
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
