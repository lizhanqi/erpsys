package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;

import butterknife.ButterKnife;

/**
 * 排程类型
 * 选择什么样的排程服务
 */
public class ScheduleTypeActivity extends TitleNavigationActivity {

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
