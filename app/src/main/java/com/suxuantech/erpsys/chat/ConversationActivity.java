package com.suxuantech.erpsys.chat;

import android.media.AudioManager;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.suxuantech.erpsys.R;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jzvd.JZVideoPlayer;

public class ConversationActivity extends ChatBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
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

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
