/*
 * Copyright © Yan Zhenjie. All Rights Reserved
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
package com.suxuantech.erpsys.utils;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;

/**
 * Created by Yan Zhenjie on 2016/10/16.
 */
public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast mToast;

    /**
     * 弹出一个吐司
     *
     * @param msg
     */
    public static void showShort(CharSequence msg) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 弹出一个吐司(不建议这种方式,因为最好在吧文字抽出来更规范)
     *
     * @param msg
     */
    public static void showLong(CharSequence msg) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void show(CharSequence msg, int toastTime) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, toastTime);
        mToast.show();
    }

    public static void showInCenterShort(CharSequence msg) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showShort(@StringRes int stringId) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), stringId, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void show(@StringRes int stringId, int toastTime) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), stringId, toastTime);
        mToast.show();
    }

    /**
     * toast取消
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
    static Snackbar make;
    public static void snackbarShort(String text) {
        snackbarShort(text, null, null);
    }

    public static void snackbarShort(int text) {
        snackbarShort(text, null, null);
    }

    public static void snackbarShort(String text, String action) {
        snackbarShort(text, action, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static void snackbarShort(int text, String action) {
        snackbarShort(text, action, null);
    }

    public static void snackbarShort(String text, String action, View.OnClickListener listener) {
        Activity topActivity = App.getApplication().getTopActivity();
        if (topActivity == null) {
            showShort(text);
            return;
        }
        View decorView =   topActivity.getWindow().getDecorView();
        View viewById = decorView.findViewById(R.id.content_view_layout);
        if (viewById != null) {
            decorView =   viewById;
        }
        if (make != null) {
            make.dismiss();
        }
        make = Snackbar.make(decorView, text, Snackbar.LENGTH_INDEFINITE);
//        App.getApplication().getResources().getColor(R.color.noticeOrange);
        View view = make.getView();
        ViewGroup parent = (ViewGroup) view.getParent();
        view.setBackgroundResource(R.color.noticeOrange);
        make.setText(text);
        make.setDuration(5000);
        make.setAction(action, listener == null ? new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        } : listener).show();
    }

    public static void snackbarShort(Activity activity,String text, String action, View.OnClickListener listener) {
        if (activity == null) {
            showShort(text);
            return;
        }
        View decorView =   activity.getWindow().getDecorView();
        View viewById = decorView.findViewById(R.id.content_view_layout);
        if (viewById != null) {
            decorView =   viewById;
        }
        if (make != null) {
            make.dismiss();
        }
        make = Snackbar.make(decorView, text, Snackbar.LENGTH_INDEFINITE);
//        App.getApplication().getResources().getColor(R.color.noticeOrange);
        View view = make.getView();
        ViewGroup parent = (ViewGroup) view.getParent();
        view.setBackgroundResource(R.color.noticeOrange);
        make.setText(text);
        make.setDuration(5000);
        make.setAction(action, listener == null ? new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        } : listener).show();
    }

    public static void snackbarShort(int text, String action, View.OnClickListener listener) {
        snackbarShort(App.getApplication().getResources().getString(text), action, listener);
    }
}
