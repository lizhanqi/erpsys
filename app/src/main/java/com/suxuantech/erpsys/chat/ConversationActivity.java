package com.suxuantech.erpsys.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.keyboard.adaputer.SimpleAppsGridView;
import com.suxuantech.erpsys.chat.keyboard.entity.EmotionBean;
import com.suxuantech.erpsys.chat.keyboard.entity.ImageBean;
import com.suxuantech.erpsys.chat.keyboard.weight.EmotionView;
import com.suxuantech.erpsys.chat.keyboard.weight.ImageEmotionView;
import com.suxuantech.erpsys.chat.keyboard.weight.KeyBoardView;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.testemticon.DefXhsEmoticons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import sj.qqkeyboard.DefQqEmoticons;

import static com.suxuantech.erpsys.chat.MessageEntity.TEXT;

public class ConversationActivity extends ChatBaseActivity {
    String  User="123456";
    private RecyclerView msgList;
    private MultipleItemQuickAdapter multipleItemQuickAdapter;
    private ArrayList<MessageEntity> ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation2);
        showUserDefinedNav();
        setTitle(        getIntent().getStringExtra("name"));
        //会话对方
        Conversation singleConversation = Conversation.createSingleConversation(User);
        //会话消息列表
        JMessageClient.getConversationList();
        ms = new ArrayList<>();

        for (int i=0;i<50;i++){
            MessageEntity messageEntity = new MessageEntity(TEXT);
            messageEntity.setMsag("111__"+i);
            ms.  add(messageEntity);
        }
        initView(singleConversation);
        multipleItemQuickAdapter = new MultipleItemQuickAdapter(ms);
        multipleItemQuickAdapter.setUpFetchEnable(true);
        msgList.setLayoutManager(new LinearLayoutManager(this));
        msgList.setAdapter(multipleItemQuickAdapter);
    }

    private void initView(Conversation singleConversation) {
        msgList = findViewById(R.id.rv_msg);

        KeyBoardView viewById = findViewById(R.id.keyboard);
        viewById.setSendListen(new KeyBoardView.SendListen() {
            @Override
            public void send(String s) {
                sendMssage(s);
            }
        });
        viewById.setAudioInput(new KeyBoardView.AudioInput() {
            @Override
            public void onClick() {
                singleConversation.getAllMessage();
            }
        });
        viewById.setPluginViews(new SimpleAppsGridView(this));
        viewById.addEmotionView( new SimpleAppsGridView(this),getResources().getDrawable(R.drawable.icon_msg_task));
        EmotionView emotionView = new EmotionView(this);
        emotionView.withText(viewById.getRcEditText());
        // 表情 -withsource
        //  ArrayList<EmojiBean> emojiArray = new ArrayList<>();
        Set<String> strings1 = DefQqEmoticons.sQqEmoticonHashMap.keySet();
        //Collections.addAll(emojiArray, DefEmoticons.getDefEmojiArray());
        ArrayList<EmotionBean> strings = new ArrayList<>();
        for(String eb: strings1 ){
            strings.add(new EmotionBean(DefQqEmoticons.sQqEmoticonHashMap.get(eb),eb, EmotionBean.type.mimap));
        }
        emotionView.setRow(4);
        emotionView.setLine(5);
        emotionView.setUseDelete(false);
        emotionView.setEmotion(strings);
        viewById. addEmotionView( emotionView, getResources().getDrawable(strings.get(0).icon));
        ImageEmotionView imageEmotionView = new ImageEmotionView(this);
        ArrayList<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(new  ImageBean("icon_clear","ssss"));
        imageBeans.add(new  ImageBean("jmui_loading_8","ssss"));
        imageBeans.add(new  ImageBean("icon_consumption_type","ssss"));
        imageEmotionView.setEmotion(imageBeans);
        imageEmotionView.setOnEmotionClick(new ImageEmotionView.EmotionClick() {
            @Override
            public void onClick(ArrayList<ImageBean> emotion, int posstion) {
                ToastUtils.show(posstion+""+emotion.get(posstion));
            }
        });
        viewById.addEmotionView(imageEmotionView,getResources().getDrawable(R.drawable.icon_contact_normal));
        ///********      2*   asset下的*************
        EmotionView emotionView2 = new EmotionView(this);
        HashMap<String, String> sXhsEmoticonHashMap = DefXhsEmoticons.sXhsEmoticonHashMap;
    }
    void sendMssage( String text){
        Message singleTextMessage = JMessageClient.createSingleTextMessage(User, text);
        MessageEntity messageEntity = new MessageEntity(TEXT);
        messageEntity.setMsag(text);
        ms.add(messageEntity);
        msgList.scrollToPosition(ms.size()-1);
        multipleItemQuickAdapter.notifyDataSetChanged();
        singleTextMessage.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                ToastUtils.show(i+"发送成功:"+s);
            }
        });
    }
}
