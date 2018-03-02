package com.suxuantech.erpsys.activity;

import android.os.Bundle;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

import butterknife.ButterKnife;

/**
 * 排程类型
 * 选择什么样的排程服务
 */
public class ScheduleTypeActivity extends ImmersedBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_type);
        showUserDefinedNav();
        setTitle("排程");
        ButterKnife.bind(this);
        useButterKnife();
    }

}
