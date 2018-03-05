package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;

public class NoticeDetailActivity extends ImmersedBaseActivity {

    /**
     * 通知详情
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        showUserDefinedNav();
        setUseDefinedNavRightDrawable(getResources().getDrawable(R.drawable.icon_notice_menu));
        setTitle(getString(R.string.notice));
  }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
     public  void widgetClick(View v) {
        switch (v.getId()){
            case  R.id.tv_nav_left:
              finish();
                break;
            case  R.id.tv_nav_right:
                App.findActivity(HistoryNoticeActivity.class);
                startActivity(HistoryNoticeActivity.class);
                finish();
                break;
                default:
        }
    }
}
