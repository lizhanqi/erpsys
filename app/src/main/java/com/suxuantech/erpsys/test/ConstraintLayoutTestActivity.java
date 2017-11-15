package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;

import java.util.List;

public class ConstraintLayoutTestActivity extends StatusImmersedBaseActivity {
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_test);
    }

    @Override
    public void widgetClick(View v) {

    }
}
