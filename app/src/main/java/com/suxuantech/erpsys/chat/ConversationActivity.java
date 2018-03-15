package com.suxuantech.erpsys.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import java.util.List;
import java.util.Locale;
import java.util.Set;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import sj.qqkeyboard.DefQqEmoticons;

public class ConversationActivity extends ChatBaseActivity {
    String  User="123456";
    private RecyclerView msgList;
    private MultipleItemQuickAdapter multipleItemQuickAdapter;
    private ArrayList<MessageEntity> allMessage;
    private Conversation singleConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation2);
        showUserDefinedNav();
        setTitle(   getIntent().getStringExtra("name"));
        User=  getIntent().getStringExtra("name");
        JMessageClient.registerEventReceiver(this);
        //会话对方
      ///  singleConversation = Conversation.createSingleConversation(User);
        singleConversation = JMessageClient.getSingleConversation(User);
        if (singleConversation == null) {
            singleConversation = Conversation.createSingleConversation(User);
        }
        //会话消息列表
       // List<Conversation> conversationList = JMessageClient.getConversationList();
        List<Message> conversation = singleConversation.getAllMessage();
        allMessage = new ArrayList<>();
        for (Message ms:conversation ){
            allMessage.add(new MessageEntity(ms));
        }
        initView(singleConversation);
        multipleItemQuickAdapter = new MultipleItemQuickAdapter(allMessage);
        multipleItemQuickAdapter.setUpFetchEnable(true);
        msgList.setLayoutManager(new LinearLayoutManager(this));
        msgList.setAdapter(multipleItemQuickAdapter);
        msgList.scrollToPosition(allMessage.size()-1);
        multipleItemQuickAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                return false;
            }
        });
        multipleItemQuickAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                return false;
            }
        });
        multipleItemQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        multipleItemQuickAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                float OldListY = (float) location[1];
                float OldListX = (float) location[0];
                new TipView.Builder(ConversationActivity.this, msgList, (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight())
                        .addItem(new TipItem("复制"))
                        .addItem(new TipItem("转发"))
                        .addItem(new TipItem("删除"))
                        .setOnItemClickListener(new TipView.OnItemClickListener() {
                            @Override
                            public void onItemClick(String str, final int position) {
                                ToastUtils.show(str);
                            }
                            @Override
                            public void dismiss() {

                            }
                        });
                return false;
            }
        });
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
        ToastUtils.show(text+"***发送给:"+User);
        //方式1
        Message sendTextMessage = singleConversation.createSendTextMessage(text);
        //方式二
        TextContent content = new TextContent(text);
        Message sendMessage = singleConversation.createSendMessage(content);
        //发送消息
       // JMessageClient.sendMessage(sendTextMessage);
        //   //设置需要已读回执
        MessageSendingOptions options = new MessageSendingOptions();
        options.setNeedReadReceipt(true);
        JMessageClient.sendMessage(sendTextMessage, options);
        //更新页面
        MessageEntity messageEntity = new MessageEntity(sendTextMessage);
        allMessage.add(messageEntity);
        msgList.scrollToPosition(allMessage.size()-1);
        multipleItemQuickAdapter.notifyDataSetChanged();
    }
    /**
     如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
     sdk会发送此事件通知上层。
     **/
    public void onEvent(ConversationRefreshEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是
        //MSG_ROAMING_COMPLETE
        ConversationRefreshEvent.Reason reason = event.getReason();
        Log.i("JIM",String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
        Log.i("JIM","事件发生的原因 : " + reason);
    }
    /**
     类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
     即可实现离线消息的接收。
     **/
    public void onEvent(OfflineMessageEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        List<Message> newMessageList = event.getOfflineMessageList();//获取此次线期间会话收到的新消息列表
      Log.i("JIM",String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
    }
//    /**
//     * 收到消息(在线的)
//     */
//    public void onEvent(MessageEvent event) {//子线程
//        event.getMessage().getContent();
//        ToastUtils.show(event.getMessage().getContent().toJson());
//    }

    /**
     *     接受消息的事件 收到消息(在线的)主线程
     */
        public void onEventMainThread(MessageEvent event) {
        if (event.getMessage().getFromUser().getUserName().equals(User)) {
            event.getMessage().getContent();
            allMessage.add(new MessageEntity(event.getMessage()));
            msgList.scrollToPosition(allMessage.size()-1);
            multipleItemQuickAdapter.notifyDataSetChanged();
//            event.getMessage().setHaveRead(new BasicCallback() {
//                @Override
//                public void gotResult(int i, String s) {
//
//                }
//            });
        }
    }

    /**
     * 对方读取消息后的状态更新
     * @param event
     */
    public void onEventMainThread(MessageReceiptStatusChangeEvent event) {
        List<MessageReceiptStatusChangeEvent.MessageReceiptMeta> messageReceiptMetas = event.getMessageReceiptMetas();
        for (MessageReceiptStatusChangeEvent.MessageReceiptMeta meta : messageReceiptMetas) {
            long serverMsgId = meta.getServerMsgId();
            int unReceiptCnt = meta.getUnReceiptCnt();
            multipleItemQuickAdapter.setUpdateReceiptCount(serverMsgId, unReceiptCnt);
        }
    }
    /**
     * 消息撤回
     * @param event
     */
    public void onEventMainThread(MessageRetractEvent event) {
        Message retractedMessage = event.getRetractedMessage();
        multipleItemQuickAdapter.delMsgRetract(retractedMessage);
    }
}
