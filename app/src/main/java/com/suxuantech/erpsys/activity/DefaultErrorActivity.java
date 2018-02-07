/*
 * Copyright 2014-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suxuantech.erpsys.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.AppUtil;
import com.suxuantech.erpsys.utils.MailManager;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public final class DefaultErrorActivity extends AppCompatActivity {

    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //BOARD 主板
        String phoneInfo = "BOARD: " + android.os.Build.BOARD;
        phoneInfo += "<br/>BOOTLOADER: " + android.os.Build.BOOTLOADER;
        //BRAND 运营商
        phoneInfo += "<br/>BRAND: " + android.os.Build.BRAND;
        phoneInfo += "<br/>CPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += "<br/>CPU_ABI2: " + android.os.Build.CPU_ABI2;
        //DEVICE 驱动
        phoneInfo += "<br/>DEVICE: " + android.os.Build.DEVICE;
        //DISPLAY 显示
        phoneInfo += "<br/>DISPLAY: " + android.os.Build.DISPLAY;
        //指纹
        phoneInfo += "<br/>FINGERPRINT: " + android.os.Build.FINGERPRINT;
        //HARDWARE 硬件
        phoneInfo += "<br/>HARDWARE: " + android.os.Build.HARDWARE;
        phoneInfo += "<br/>HOST: " + android.os.Build.HOST;
        phoneInfo += "<br/>ID: " + android.os.Build.ID;
        //MANUFACTURER 生产厂家
        phoneInfo += "<br/>MANUFACTURER: " + android.os.Build.MANUFACTURER;
        //MODEL 机型
        phoneInfo += "<br/>MODEL: " + android.os.Build.MODEL;
        phoneInfo += "<br/>PRODUCT: " + android.os.Build.PRODUCT;
        phoneInfo += "<br/>RADIO: " + android.os.Build.RADIO;
        phoneInfo += "<br/>RADITAGSO: " + android.os.Build.TAGS;
        phoneInfo += "<br/>TIME: " + android.os.Build.TIME;
        phoneInfo += "<br/>TYPE: " + android.os.Build.TYPE;
        phoneInfo += "<br/>USER: " + android.os.Build.USER;
        //VERSION.RELEASE 固件版本
        phoneInfo += "<br/>VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += "<br/> VERSION.CODENAME: " + android.os.Build.VERSION.CODENAME;
        //VERSION.INCREMENTAL 基带版本
        phoneInfo += "<br/>VERSION.INCREMENTAL: " + android.os.Build.VERSION.INCREMENTAL;
        //VERSION.SDK SDK版本
        phoneInfo += "<br/>VERSION.SDK: " + android.os.Build.VERSION.SDK;
        phoneInfo += "<br/>VERSION.SDK_INT: " + android.os.Build.VERSION.SDK_INT+"<br/>";
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());
        MailManager.getInstance().sendMail(getApplication().getString(R.string.app_name) + "发生错误；错误版本号：" + AppUtil.getVersionCode(getApplicationContext()) + "错误版本名称：" + AppUtil.getVersionName(getApplicationContext()), phoneInfo+errorInformation.replace("\n", "<br/>"));
        //This is needed to avoid a crash if the developer has not specified
        //an app-level theme that extends Theme.AppCompat
        TypedArray a = obtainStyledAttributes(cat.ereza.customactivityoncrash.R.styleable.AppCompatTheme);
        if (!a.hasValue(cat.ereza.customactivityoncrash.R.styleable.AppCompatTheme_windowActionBar)) {
            setTheme(cat.ereza.customactivityoncrash.R.style.Theme_AppCompat_Light_DarkActionBar);
        }
        a.recycle();

        setContentView(cat.ereza.customactivityoncrash.R.layout.customactivityoncrash_default_error_activity);

        //Close/restart button logic:
        //If a class if set, use restart.
        //Else, use close and just finish the app.
        //It is recommended that you follow this logic if implementing a custom error activity.
        Button restartButton = findViewById(cat.ereza.customactivityoncrash.R.id.customactivityoncrash_error_activity_restart_button);

        final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        if (config == null) {
            //This should never happen - Just finish the activity to avoid a recursive crash.
            finish();
            return;
        }

        if (config.isShowRestartButton() && config.getRestartActivityClass() != null) {
            restartButton.setText(cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_restart_app);
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.restartApplication(DefaultErrorActivity.this, config);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.closeApplication(DefaultErrorActivity.this, config);
                }
            });
        }

        Button moreInfoButton = findViewById(cat.ereza.customactivityoncrash.R.id.customactivityoncrash_error_activity_more_info_button);

        if (config.isShowErrorDetails()) {
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //We retrieve all the error data and show it

                    AlertDialog dialog = new AlertDialog.Builder(DefaultErrorActivity.this)
                            .setTitle(cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_error_details_title)
                            .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(DefaultErrorActivity.this, getIntent()))
                            .setPositiveButton(cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_error_details_close, null)
                            .setNeutralButton(cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_error_details_copy,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            copyErrorToClipboard();
                                        }
                                    })
                            .show();
                    TextView textView = dialog.findViewById(android.R.id.message);
                    if (textView != null) {
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(cat.ereza.customactivityoncrash.R.dimen.customactivityoncrash_error_activity_error_details_text_size));
                    }
                }
            });
        } else {
            moreInfoButton.setVisibility(View.GONE);
        }

        Integer defaultErrorActivityDrawableId = config.getErrorDrawable();
        ImageView errorImageView = findViewById(cat.ereza.customactivityoncrash.R.id.customactivityoncrash_error_activity_image);

        if (defaultErrorActivityDrawableId != null) {
            errorImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), defaultErrorActivityDrawableId, getTheme()));
        }
    }

    private void copyErrorToClipboard() {
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(DefaultErrorActivity.this, getIntent());

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Are there any devices without clipboard...?
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText(getString(cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(DefaultErrorActivity.this, cat.ereza.customactivityoncrash.R.string.customactivityoncrash_error_activity_error_details_copied, Toast.LENGTH_SHORT).show();
        }
    }
}
