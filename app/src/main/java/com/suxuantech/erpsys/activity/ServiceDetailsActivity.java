package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;

import java.util.List;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends StatusImmersedBaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
        setTitle("服务详情");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        showUsetDefinedNav();

    }

    @Override
    protected void widgetClick(View v) {

    }
}
