package com.suxuantech.erpsys.activity;

import android.os.Bundle;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.SimpleStatusActivity;

public class NoticeDetailActivity extends SimpleStatusActivity {
    /**
     * 通知详情
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        showUsetDefinedNav();
        setUseDefinedNavRightDrawable(getResources().getDrawable(R.drawable.icon_notice_menu));
        setTitle(getString(R.string.notice));
    }
}
