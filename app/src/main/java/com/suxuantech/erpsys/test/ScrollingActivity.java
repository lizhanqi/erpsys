package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.yanzhenjie.statusview.StatusUtils;

import java.util.List;

/**
 * 暂时搁置这里
 */
public class ScrollingActivity extends StatusImmersedBaseActivity {
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        findViewById(R.id.title).setOnClickListener(this);
        findViewById(R.id.nav).setOnClickListener(this);
        findViewById(R.id.sys).setOnClickListener(this);
        findViewById(R.id.hid).setOnClickListener(this);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.jzjp).setOnClickListener(this);
        findViewSetOnClick(R.id.hengp);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.title:
               getStatusView().setBackgroundResource(R.color.album_ColorPrimaryBlack);
                StatusUtils.setFullToStatusBar(this);
//                StatusUtils.setFullToStatusBar(this);
//                StatusUtils.setStatusBarColor(this,getResources().getColor(R.color.album_ColorPrimaryBlack));
                break;
            case R.id.nav:
                StatusUtils.setFullToNavigationBar(this);
                StatusUtils.setNavigationBarColor(this,getResources().getColor(R.color.album_ColorPrimaryBlack));
                break;
            case R.id.sys:
                StatusUtils.setSystemBarColor(this,getResources().getColor(R.color.album_FontLight),getResources().getColor(R.color.album_FontLight));
                break;
            case R.id.jzjp:
                forbiddenTakeScreen();
                break;
            case R.id.hid:
                hideStatus();
                break;
            case R.id.show:
                showStatus();
                break;
            case R.id.hengp:
                screenMustOrientation(true);
                break;


        }
    }
//
//    @Override
//    public ImmerseType immerseType() {
//        return ImmerseType.ALLIMMERSE;
//    }
}
