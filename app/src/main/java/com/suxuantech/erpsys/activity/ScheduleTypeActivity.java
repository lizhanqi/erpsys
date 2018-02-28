package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 排程类型
 * 选择什么样的排程服务
 */
public class ScheduleTypeActivity extends ImmersedBaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_type);
        showUserDefinedNav();
        setTitle("排程");
        ButterKnife.bind(this);
        useButterKnife();
    }

    @Override
    protected void widgetClick(View v) {

    }
}
