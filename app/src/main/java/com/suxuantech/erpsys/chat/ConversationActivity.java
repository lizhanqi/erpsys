package com.suxuantech.erpsys.chat;

import android.os.Bundle;

import com.suxuantech.erpsys.R;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;

public class ConversationActivity extends ChatBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_convertion);
//        showUserDefinedNav();
        setTitle(   getIntent().getStringExtra("name"));
        //User=  getIntent().getStringExtra("name");
        JMessageClient.registerEventReceiver(this);
    }
    /**
     * 对方读取消息后的状态更新
     *
     * @param event
     */
    public void onEventMainThread(MessageReceiptStatusChangeEvent event) {
        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
    }

    /**
     * 接受消息的事件 收到消息(在线的)主线程
     */
    public void onEventMainThread(MessageEvent event) {
        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
    }

    /**
     * 消息撤回
     *
     * @param event
     */
    public void onEventMainThread(MessageRetractEvent event) {
        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
    }
}
