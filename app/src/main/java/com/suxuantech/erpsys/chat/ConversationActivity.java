package com.suxuantech.erpsys.chat;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.EncodeUtils;
import com.suxuantech.erpsys.R;

import cn.jpush.im.android.eventbus.EventBus;
import cn.jzvd.JZVideoPlayer;

public class ConversationActivity extends ChatBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
         setSwipeBackEnable(false);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        setContentView(R.layout.ac_convertion);
        supportToolbar();
        boolean name = getIntent().hasExtra("name");
        if (name){
            boolean base64 = getIntent().getBooleanExtra("base64",false);
            if (base64){
                setTitle( new String(EncodeUtils.base64Decode(getIntent().getStringExtra("name"))));
            }else {
                setTitle(  getIntent().getStringExtra("name"));
            }
        }else {
            Toast.makeText(this,"空联系人",Toast.LENGTH_SHORT).show();
            finish();
        }

        //User=  getIntent().getStringExtra("name");
       // JMessageClient.registerEventReceiver(this);
    }
//    /**
//     * 对方读取消息后的状态更新
//     *
//     * @param event
//     */
//    public void onEventMainThread(MessageReceiptStatusChangeEvent event) {
//        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
//    }
//
//    /**
//     * 接受消息的事件 收到消息(在线的)主线程
//     */
//    public void onEventMainThread(MessageEvent event) {
//        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
//    }
//
//    /**
//     * 消息撤回
//     *
//     * @param event
//     */
//    public void onEventMainThread(MessageRetractEvent event) {
//        (( JConversationFragment ) getFragmentManager().findFragmentById(R.id.fra)).onEventMainThread(event);
//    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new String("1"));
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressedSupport() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }
}
