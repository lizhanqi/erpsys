package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends ImmersedBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        showUserDefinedNav();
    }
}
