package com.suxuantech.erpsys.rongim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;

public class SubConversationListActivtiy extends ImmersedBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_conversation_list_activtiy);
        Intent intent = getIntent();
        if (intent.getData() == null) {
            return;
        }
        //聚合会话参数
        String type = intent.getData().getQueryParameter("type");
        if (type == null){
            return;
        }
        showUserDefinedNav();
        if (type.equals("group")) {
            setTitle(R.string.de_actionbar_sub_group);
        } else if (type.equals("private")) {
            setTitle(R.string.de_actionbar_sub_private);
        } else if (type.equals("discussion")) {
            setTitle(R.string.de_actionbar_sub_discussion);
        } else if (type.equals("system")) {
            setTitle(R.string.de_actionbar_sub_system);
        } else {
            setTitle(R.string.de_actionbar_sub_defult);
        }

    }

    @Override
    protected void widgetClick(View v) {
        if (v.getId()==R.id.tv_nav_left){
            finish();
        }
    }
}
