package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.suxuantech.erpsys.dialog.WaitDialog;

public class BaseTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_test);
        findViewSetOnClick(R.id.heng);

    }

    @Override
    public void widgetClick(View v) {
        WaitDialog waitDialog = new WaitDialog(this);
        waitDialog.show();
    }
}
