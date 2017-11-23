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

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.suxuantech.erpsys.App;

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
     * 弹出一个吐司(不建议这种方式,因为最好在吧文字抽出来更规范)
     * @param msg
     */
    @Deprecated
    public static void show(  CharSequence msg) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT);
        mToast.show();
    }
    public static void show(  CharSequence msg,int toastTime) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, toastTime);
        mToast.show();
    }
    public static void showInCenter(  CharSequence msg) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }

    public static void show( @StringRes int stringId) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), stringId, Toast.LENGTH_SHORT);
        mToast.show();
    }
    public static void show( @StringRes int stringId,int toastTime) {
        cancelToast();
        mToast = Toast.makeText(App.getApplication(), stringId, toastTime);
        mToast.show();
    }
    /**
     *toast取消
     */
    public static void cancelToast(){
        if(mToast != null){
            mToast.cancel();
            mToast = null;
        }
    }
}
