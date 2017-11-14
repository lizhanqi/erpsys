package com.suxuantech.erpsys.views;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.widget.NestedScrollView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lizhanqi on 17-11-14.
 */

public class Behver extends CoordinatorLayout.Behavior {
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {



        return !(dependency instanceof CoordinatorLayout );
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {

        return !(child  instanceof CoordinatorLayout );
    }
}
