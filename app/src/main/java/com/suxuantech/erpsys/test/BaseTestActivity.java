package com.suxuantech.erpsys.test;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.RotateAnimation;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.suxuantech.erpsys.dialog.WaitDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BaseTestActivity extends BaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
//    <uses-permission android:name="android.permission.BODY_SENSORS"/>
//    <uses-permission android:name="android.permission.READ_CALENDAR"/>
//    <uses-permission android:name="android.permission.WRITE_CALENDAR"/>
        addMustPermission(true, Manifest.permission.BODY_SENSORS, Manifest.permission.RECORD_AUDIO);
        setContentView(R.layout.activity_base_test);
        findViewSetOnClick(R.id.other_permission);
        findViewSetOnClick(R.id.look);
        findViewSetOnClick(R.id.add);
    }

    @Override
    public void alwaysDeniedPermissionDiaLog(final int requestSettingCode, final List<String> deniedPermissions) {
        AndPermission.defaultSettingDialog(this, requestSettingCode).setNegativeButton("不要", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGotoSettings(requestSettingCode,deniedPermissions);
            }
        }).setMessage("自定义的拒绝哈哈").show();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.other_permission:
                requstPermissions(110, null, true, Manifest.permission.SEND_SMS);
                break;
            case R.id.look:
                HashSet<String> permissionList = getPermissionList();
                ArrayList<String> strings = new ArrayList<>(permissionList);
                for (String s: strings ) {
                    dLog(s);
                }
                break;
            case R.id.add:
                addMustPermission(false, Manifest.permission.WRITE_CALENDAR);
                break;
            default:
        }

    }



}
