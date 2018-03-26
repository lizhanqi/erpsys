package com.suxuantech.erpsys.chat;

import android.Manifest;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.keyboard.adaputer.SimpleAppsGridView;
import com.suxuantech.erpsys.chat.keyboard.entity.AppBean;
import com.suxuantech.erpsys.chat.keyboard.entity.EmotionBean;
import com.suxuantech.erpsys.chat.keyboard.weight.EmotionSinglePageView;
import com.suxuantech.erpsys.chat.keyboard.weight.KeyBoardView;
import com.suxuantech.erpsys.ui.dialog.DefaultRationale;
import com.suxuantech.erpsys.ui.dialog.PermissionSetting;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;
import sj.qqkeyboard.DefQqEmoticons;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on 2018/3/15 0015 14:24 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 聊天界面
 * <p>
 * 1.单聊
 * 1.1文字及表情
 * 1.2 图片(包含照相)
 * 1.3 位置
 * 1.4 文件
 * 1.5语音
 * 1.6 失败消息重发
 * 1.7已读,未读
 * 1.8 长按复制
 * 1.9删除,撤回
 * 2.群聊
 * 2.1  邀请加群,创建
 * 2.2  退群 ,踢人
 * 2.3 @ 某人,@全体 (最后再说)
 */

public class JConversationFragment extends Fragment implements KeyBoardView.AudioInput  , SensorEventListener {
    String UserID = "123456";
    private RecyclerView msgList;
    private MultipleItemQuickAdapter multipleItemQuickAdapter;
    private Conversation singleConversation;
    private LinearLayout mRootView;
    private KeyBoardView keyBoardView;
    private boolean isGrop;
    private Dialog mDialog;
    int pageOffset, limit = 10;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Rationale mRationale;
    PermissionSetting mSetting;
    Handler hd = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    public void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new com.yanzhenjie.permission.Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        permissionGrantedResult(permissions);
                    }
                })
                .onDenied(new com.yanzhenjie.permission.Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(JConversationFragment.this, permissions)) {
                            mSetting.showSetting(permissions);
                        } else {
                            ToastUtils.show(R.string.failure_permission);
                        }
                    }
                })
                .start();
    }

    /**
     * 权限授予结果
     *
     * @param permissions
     */
    public void permissionGrantedResult(List<String> permissions) {

    }

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public boolean hasPermission(@NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(getContext(), permission);
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) {
                continue;
            }
            result = AppOpsManagerCompat.noteProxyOp(getActivity(), op, getActivity().getPackageName());
            if (result != AppOpsManagerCompat.MODE_ALLOWED) {
                return false;
            }

        }
        return true;
    }
    //调用距离传感器，控制屏幕

    private SensorManager mManager;//传感器管理对象
    //屏幕开关
    private PowerManager localPowerManager = null;//电源管理对象
    private PowerManager.WakeLock localWakeLock = null;//电源锁
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (LinearLayout) inflater.inflate(R.layout.activity_conversation2, container, false);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getActivity());
        mManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        localPowerManager = (PowerManager)getActivity(). getSystemService(Context.POWER_SERVICE);
        localWakeLock =  localPowerManager.newWakeLock(32, "MyPower");
//        AudioModeManger audioModeManger = new AudioModeManger();
//        if (audioModeManger == null) {
//            audioModeManger = new AudioModeManger();
//        }
//        audioModeManger.register();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
            mManager.registerListener(this, mManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),// 距离感应器
             SensorManager.SENSOR_DELAY_NORMAL);//注册传感器，第一个参数为距离监听器，第二个是传感器类型，第三个是延迟类型

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserID = getActivity().getIntent().getStringExtra("name");
        singleConversation = JMessageClient.getSingleConversation(UserID);
        if (singleConversation == null) {
            singleConversation = Conversation.createSingleConversation(UserID);
        }

        initView();
    }

    public List<MessageEntity> loadMessage() {
        List<Message> conversation = singleConversation.getMessagesFromNewest(pageOffset * limit, limit);
        if (conversation != null && conversation.size() == limit) {
            pageOffset++;
        } else {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
        List<MessageEntity> messages = new ArrayList<>();
        for (Message ms : conversation) {
            messages.add(new MessageEntity(ms));
        }
        Collections.reverse(messages);
        hd.sendEmptyMessageDelayed(0, 2000);

        return messages;
    }

    private void initView() {
        msgList = mRootView.findViewById( R.id.rv_msg);
        mSwipeRefreshLayout = mRootView.findViewById(R.id.srl_load);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.de_title_bg, R.color.themeColor, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<MessageEntity> messageEntities = loadMessage();
                multipleItemQuickAdapter.appendTopData(messageEntities);
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
           multipleItemQuickAdapter = new MultipleItemQuickAdapter(loadMessage());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager  .setStackFromEnd(true);
        msgList.setLayoutManager(linearLayoutManager);
        //这里请用:bindToRecyclerView 因为需要使用到getViewByPosition
        //   msgList.setAdapter(multipleItemQuickAdapter);
        multipleItemQuickAdapter.bindToRecyclerView(msgList);
        msgList.scrollToPosition(multipleItemQuickAdapter.getData().size() - 1);
        multipleItemQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        multipleItemQuickAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
//                KeyboardUtils.hideSoftInput(view);
                MessageEntity msgenty = (MessageEntity) adapter.getData().get(position);
                ContentType messageType = msgenty.getMsag().getContentType();
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                float OldListY = (float) location[1];
                float OldListX = (float) location[0];
                TipView.Builder builder = new TipView.Builder(getActivity(), getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT), (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight());
                if (msgenty.getMsag().getContentType() == ContentType.text) {
                    builder.addItem(new TipItem("复制"));
                }
                builder.addItem(new TipItem("删除"));
                if (msgenty.getMsag().getDirect() == MessageDirect.send) {
                    builder.addItem(new TipItem("撤回"));
                }

                builder.setOnItemClickListener(new TipView.OnItemClickListener() {
                    @Override
                    public void onItemClick(String str, final int position) {
                        if (str.equals("复制")) {
                            final String content = ((TextContent) msgenty.getMsag().getContent()).getText();
                            if (Build.VERSION.SDK_INT > 11) {
                                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Simple text", content);
                                clipboard.setPrimaryClip(clip);
                            } else {
                                android.text.ClipboardManager clip = (android.text.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                if (clip.hasText()) {
                                    clip.getText();
                                }
                            }
                            Toast.makeText(getActivity(), "已复制", Toast.LENGTH_SHORT).show();
                        } else if (str.equals("撤回")) {
                            //撤回
                            singleConversation.retractMessage(msgenty.getMsag(), new BasicCallback() {
                                @Override
                                public void gotResult(int i, String s) {
                                    if (i == 855001) {
                                        Toast.makeText(getActivity(), "发送时间过长，不能撤回", Toast.LENGTH_SHORT).show();
                                    } else if (i == 0) {
                                        multipleItemQuickAdapter.delMsgRetract(msgenty.getMsag());
                                    }
                                }
                            });
                        } else {
                            //删除
                            singleConversation.deleteMessage(msgenty.getMsag().getId());
                            multipleItemQuickAdapter.removeMessage(msgenty.getMsag());
                        }
                    }

                    @Override
                    public void dismiss() {
                    }
                });
                builder.create();
                return false;
            }
        });
        multipleItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MessageEntity messageEntity = (MessageEntity) adapter.getData().get(position);
                Message content = messageEntity.getMsag();
                showResendDialog(content, position);
            }
        });
        keyBoardView = mRootView.findViewById(R.id.keyboard);
        keyBoardView.setSendListen(new KeyBoardView.SendListen() {
            @Override
            public void send(Editable s) {
                sendMssageText(s);
            }
        });
        keyBoardView.setAudioInput(this);
        SimpleAppsGridView simpleAppsGridView = new SimpleAppsGridView(getActivity());
        simpleAppsGridView.setOnItemClickListener(new SimpleAppsGridView.OnItemClickListener() {
            @Override
            public void onItemClick(ArrayList<AppBean> mAppBeanList, int position, AdapterView<?> parent, View view) {
                if (position == 0) {
                    gotoSelectImage();
                } else if (position == 1) {
                    gotoCamera();
                }
            }
        });

        keyBoardView.setPluginViews(simpleAppsGridView);
        EmotionSinglePageView emotionView = new EmotionSinglePageView(getActivity());
        emotionView.withText(keyBoardView.getRcEditText());
        Set<String> strings1 = DefQqEmoticons.sQqEmoticonHashMap.keySet();
        ArrayList<EmotionBean> strings = new ArrayList<>();
        for (String eb : strings1) {
            strings.add(new EmotionBean(DefQqEmoticons.sQqEmoticonHashMap.get(eb), eb, EmotionBean.type.mimap));
        }
        emotionView.setColumns(7);
        emotionView.setUseDelete(false);
        emotionView.setEmotion(strings);
        keyBoardView.addEmotionView(emotionView, getResources().getDrawable(strings.get(0).icon));
        keyBoardView.setRecordListener(new KeyBoardView.RecordListener() {
            @Override
            public void onRecordFinished(int duration, String path) {
                sendVoice(duration, path);
            }
        });
    }

    /**
     * 发送语音
     *
     * @param duration
     * @param path
     */
    private void sendVoice(int duration, String path) {
        try {
            Message singleVoiceMessage = JMessageClient.createSingleVoiceMessage(UserID, new File(path), duration);
            singleVoiceMessage.setUnreceiptCnt(1);
            //设置需要已读回执
            MessageSendingOptions options = new MessageSendingOptions();
            options.setNeedReadReceipt(true);
            JMessageClient.sendMessage(singleVoiceMessage, options);
            //更新页面
            MessageEntity messageEntity = new MessageEntity(singleVoiceMessage);
            multipleItemQuickAdapter.appendData(messageEntity);
            //allMessage.add(messageEntity);
            msgList.scrollToPosition(multipleItemQuickAdapter.getData().size() - 1);
//            multipleItemQuickAdapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //重发对话框
    public void showResendDialog(Message content, int position) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.jmui_cancel_btn) {
                    mDialog.dismiss();
                } else {
                    mDialog.dismiss();
                    switch (content.getContentType()) {
                        case image:
                        case text:
                            //设置需要已读回执
                            MessageSendingOptions options = new MessageSendingOptions();
                            options.setNeedReadReceipt(true);
                            //手动设置,最低需要1个人读,不然刚发送的,在列表中有问题
                            content.setUnreceiptCnt(1);
                            content.setOnSendCompleteCallback(new BasicCallback() {
                                @Override
                                public void gotResult(int i, String s) {
                                    multipleItemQuickAdapter.notifyItemChanged(position);
                                }
                            });
                            JMessageClient.sendMessage(content, options);
                            //更新页面
                            multipleItemQuickAdapter.notifyItemChanged(position);
                        case voice:
                            //     resendTextOrVoice(holder, msg);
                            break;
                        //  case image:
                        // resendImage(holder, msg);
                        //   break;
                        case file:
                            //    resendFile(holder, msg);
                            break;
                    }
                }
            }
        };
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDialog = DialogCreator.createResendDialog(getActivity(), listener);
        mDialog.getWindow().setLayout((int) (0.8 * dm.widthPixels), WindowManager.LayoutParams.WRAP_CONTENT);
        mDialog.show();
    }


    /***
     *拍照发送图片
     */
    private void gotoCamera() {
        Album.camera(this) // 相机功能。
                .image() // 拍照。
                // .filePath() // 文件保存路径，非必须。
                .requestCode(666)
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                        if (666 == requestCode) {
                            sendImage(result);
                        }
                    }
                })
                .start();
    }

    /**
     * 图库发送图片
     */
    private void gotoSelectImage() {
        Album.image(this) // 选择图片。
                .multipleChoice()
                .requestCode(888)
                .camera(false)
                .columnCount(3)
                .selectCount(10)
                .checkedList(null)
                //.filterSize(5)
                //.filterMimeType()
                // .afterFilterVisibility() // 显示被过滤掉的文件，但它们是不可用的。
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        if (requestCode == 888) {
                            for (AlbumFile f : result) {
                                if (f.getMediaType() == AlbumFile.TYPE_IMAGE) {
                                    String path = f.getPath();
                                    sendImage(path);
                                }
                            }
                        }
                    }
                })
                .start();
    }

    void sendMssageText(Editable text) {
        Message sendTextMessage = singleConversation.createSendTextMessage(String.valueOf(new SpannableString(text)));
        //设置需要已读回执
        MessageSendingOptions options = new MessageSendingOptions();
        options.setNeedReadReceipt(true);
        //手动设置,最低需要1个人读,不然刚发送的,在列表中有问题
        sendTextMessage.setUnreceiptCnt(1);
        JMessageClient.sendMessage(sendTextMessage, options);
        //更新页面
        MessageEntity messageEntity = new MessageEntity(sendTextMessage);
//        allMessage.add(messageEntity);
        multipleItemQuickAdapter.appendData(messageEntity);
        msgList.scrollToPosition(multipleItemQuickAdapter.getData().size() - 1);
        multipleItemQuickAdapter.notifyDataSetChanged();
    }

    void sendImage(String file) {
        //方式二
        try {
            ImageContent content = new ImageContent(new File(file));
            Message sendMessage = singleConversation.createSendMessage(content);
            sendMessage.setUnreceiptCnt(1);
            //设置需要已读回执
            MessageSendingOptions options = new MessageSendingOptions();
            options.setNeedReadReceipt(true);
            JMessageClient.sendMessage(sendMessage, options);
            //更新页面
            MessageEntity messageEntity = new MessageEntity(sendMessage);
            multipleItemQuickAdapter.appendData(messageEntity);
            msgList.scrollToPosition(multipleItemQuickAdapter.getData().size() - 1);
            // multipleItemQuickAdapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
     * sdk会发送此事件通知上层。
     **/
    public void onEvent(ConversationRefreshEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是
        //MSG_ROAMING_COMPLETE
        ConversationRefreshEvent.Reason reason = event.getReason();
        Log.i("JIM", String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
        Log.i("JIM", "事件发生的原因 : " + reason);
    }

    /**
     * 类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
     * 即可实现离线消息的接收。
     **/
    public void onEvent(OfflineMessageEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        List<Message> newMessageList = event.getOfflineMessageList();//获取此次线期间会话收到的新消息列表
        Log.i("JIM", String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
    }
//    /**
//     * 收到消息(在线的)
//     */
//    public void onEvent(MessageEvent event) {//子线程
//        event.getMessage().getContent();
//        ToastUtils.show(event.getMessage().getContent().toJson());
//    }

    /**
     * 接受消息的事件 收到消息(在线的)主线程
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.getMessage().getFromUser().getUserName().equals(UserID)) {
            event.getMessage().getContent();
            multipleItemQuickAdapter.appendData(new MessageEntity(event.getMessage()));
            msgList.scrollToPosition(multipleItemQuickAdapter.getData().size() - 1);
        }
    }

    /**
     * 对方读取消息后的状态更新
     *
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
     *
     * @param event
     */
    public void onEventMainThread(MessageRetractEvent event) {
        Message retractedMessage = event.getRetractedMessage();
        multipleItemQuickAdapter.delMsgRetract(retractedMessage);
    }

    /**
     * 录制音频按钮
     */
    @Override
    public void onAudioInputClick() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(Manifest.permission.RECORD_AUDIO);
        objects.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        objects.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean b = hasPermission(objects);
        if (!b){
            requestPermission(Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mManager != null){
            localWakeLock.release();//释放电源锁，如果不释放finish这个acitivity后仍然会有自动锁屏的效果，不信可以试一试
            mManager.unregisterListener(this);//注销传感器监听
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        multipleItemQuickAdapter.stopVoice(null);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] its = event.values;
        if (its != null && event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            //经过测试，当手贴近距离感应器的时候its[0]返回值为0.0，当手离开时返回1.0
            if (its[0] == 0.0) {//
                if (localWakeLock.isHeld()) {
                    return;
                } else{
                    localWakeLock.acquire();// 申请设备电源锁
                    multipleItemQuickAdapter.soundSourceFromSpeakerphone(false);
                }
            } else {// 远离手机
                if (localWakeLock.isHeld()) {
                    return;
                } else{
                    localWakeLock.setReferenceCounted(false);
                    localWakeLock.release(); // 释放设备电源锁
                    multipleItemQuickAdapter.soundSourceFromSpeakerphone(true);
                }
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

