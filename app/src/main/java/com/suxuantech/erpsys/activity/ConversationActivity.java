package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

import java.util.List;

public class ConversationActivity extends ImmersedBaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void widgetClick(View v) {

    }
}
