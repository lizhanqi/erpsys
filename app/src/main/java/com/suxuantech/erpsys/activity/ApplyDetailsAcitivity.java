package com.suxuantech.erpsys.activity;

import android.os.Bundle;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

public class ApplyDetailsAcitivity extends ImmersedBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_details_acitivity);
        hideUserDefinedNav();
        setTitle("申请详情");
    }
}
