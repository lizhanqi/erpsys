/*
 * Copyright 2017 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suxuantech.erpsys.test;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ContentImmersedBaseActivity;
import com.yanzhenjie.statusview.NavigationView;
import com.yanzhenjie.statusview.StatusUtils;
import com.yanzhenjie.statusview.StatusView;

import java.util.List;

/**
 * <p>
 * Content intrusion status bar.
 * </p>
 * Created by YanZhenjie on 2017/8/2.
 */
public class GoodsDetailsActivity extends ContentImmersedBaseActivity {
    NestedScrollView mNestedScrollView;
    StatusView mStatusView;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    View mHeaderView;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details_test);
        StatusUtils.setFullToStatusBar(this);  // StatusBar.
        StatusUtils.setFullToNavigationBar(this); // NavigationBar.

        mToolbar =setSupportToolbar();
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        mStatusView = (StatusView) findViewById(R.id.status_view);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        mHeaderView = findViewById(R.id.header);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int headerHeight = mHeaderView.getHeight();
                int scrollDistance = Math.min(scrollY, headerHeight);
                int statusAlpha = (int) ((float) scrollDistance / (float) headerHeight * 255F);
                setAnyBarAlpha(statusAlpha);
            }
        });
        setAnyBarAlpha(0);

    }

    @Override
    public void widgetClick(View v) {

    }

    private void setAnyBarAlpha(int alpha) {
        mToolbar.getBackground().mutate().setAlpha(alpha);
        mToolbar.setTitleTextColor(Color.argb(alpha, 0, 255, 0));
        int childCount = mToolbar.getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = mToolbar.getChildAt(i);
//            if(childAt instanceof Text){
//                childAt.setcol
//            }
            Drawable  background= childAt.getBackground();
            if(background!=null){
                background.mutate().setAlpha(alpha);
            }


        }
        mStatusView.getBackground().mutate().setAlpha(alpha);
        mNavigationView.getBackground().mutate().setAlpha(255 - alpha);
    }

}