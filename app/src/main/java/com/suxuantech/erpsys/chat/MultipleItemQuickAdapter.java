package com.suxuantech.erpsys.chat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.DensityUtils;
import com.suxuantech.erpsys.utils.ToastUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.ProgressUpdateCallback;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.LocationContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.enums.MessageStatus;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

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
 * @author Created by 李站旗 on 2018/3/13 0013 16:28 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 聊天内容适配器
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MessageEntity, BaseViewHolder> {
    private AnimationDrawable mVoiceAnimation;
    private MediaPlayer mediaPlayer;
    private FileInputStream mFIS;
    private FileDescriptor mFD;
    private AudioManager audioManager;

    public MultipleItemQuickAdapter(List<MessageEntity> data) {
        super(data);
        addItemType(MessageEntity.ONESELF, R.layout.oneself_message);
        addItemType(MessageEntity.OTHER_PEOPLE, R.layout.other_people_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        Message msg = item.getMsag();
        setTemplate(helper, item);
        setMasseRead(msg, helper.getView(R.id.tv_msg_read));
        LinearLayout layout = helper.getView(R.id.ll_msg);
        boolean oneSelf = msg.getDirect() == MessageDirect.send;
        if (msg.getContentType() == ContentType.prompt) {
            TextView view = helper.getView(R.id.tv_msg_recall);
            view.setVisibility(View.VISIBLE);
            helper.getView(R.id.rl_msg).setVisibility(View.GONE);
            //todo 群聊还需要设置xxx 撤回了一条消息
            if (oneSelf) {
                view.setText("您撤回了一条消息");
            } else {
                view.setText("对方撤回了一条消息");
            }
            return;
        } else {
            helper.getView(R.id.rl_msg).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_msg_recall).setVisibility(View.GONE);
        }
        if (oneSelf) {
            messageSendingStatusMonitor(helper, item);
            MessageStatus messageStatus = msg.getStatus();
            if (messageStatus == MessageStatus.send_success) {
                helper.getView(R.id.img_msg_status).setVisibility(View.GONE);
                helper.getView(R.id.tv_msg_read).setVisibility(View.VISIBLE);
                notifyItemChanged(mData.lastIndexOf(item));
            } else if (messageStatus == MessageStatus.send_going) {
                //发送中
                ImageView sendView = helper.getView(R.id.img_msg_status);
                sendView.setVisibility(View.VISIBLE);
                sendView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.jmui_message_sending));
                helper.getView(R.id.tv_msg_read).setVisibility(View.GONE);
                ((ImageView) helper.getView(R.id.img_msg_status)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.jmui_sending_img));
            } else if (messageStatus == MessageStatus.send_fail) {
                //发送失败
                ImageView sendView = helper.getView(R.id.img_msg_status);
                sendView.setVisibility(View.VISIBLE);
                ((ImageView) helper.getView(R.id.img_msg_status)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_msg_send_failed));
                helper.getView(R.id.tv_msg_read).setVisibility(View.GONE);
                helper.addOnClickListener(R.id.img_msg_status);
            } else if (messageStatus == MessageStatus.send_draft) {
                //草稿
            } else if (messageStatus == MessageStatus.created) {

            }
        } else {
            messageReadStatusMonitor(helper, item);
        }
        if (oneSelf) {
            for (int i = 2; i < layout.getChildCount(); i++) {
                layout.removeViewAt(i);
            }
        } else {
            for (int i = 1; i < layout.getChildCount(); i++) {
                layout.removeViewAt(i);
            }
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (msg.getContentType()) {
            case eventNotification:
                break;
            case custom:
                break;
            case unknown:
                break;
            case file:
                //是否是视频
                String extra = msg.getContent().getStringExtra("video");
                int videoWidth = Integer.parseInt(msg.getContent().getStringExtra("width"));
                int videoHeight =Integer.parseInt(msg.getContent().getStringExtra("height")) ;
                int rotation =Integer.parseInt(msg.getContent().getStringExtra("rotation")) ;
                View fileView = mLayoutInflater.inflate(R.layout.msg_video, null);
                JZVideoPlayerStandard thumbnailView = fileView.findViewById(R.id.videoplayer);
                int  w = videoWidth / (videoWidth / videoHeight) / 5;
                int h= videoHeight/(videoWidth/videoHeight)/5;
                ViewGroup.LayoutParams lvp = thumbnailView.thumbImageView.getLayoutParams();
                if (rotation==90||rotation==270){
                    lvp.width=h;
                    lvp.height=w;
                    thumbnailView.setLayoutParams(new LinearLayout.LayoutParams(h,w));
                    fileView.setLayoutParams( new ViewGroup.LayoutParams(h,w));
                }else {
                    //横屏拍摄的
                    lvp.width=w;
                    lvp.height=h;
                    thumbnailView.setLayoutParams(new LinearLayout.LayoutParams(w,h));
                    fileView.setLayoutParams( new ViewGroup.LayoutParams(w,h));
                }
                thumbnailView.thumbImageView.setLayoutParams(lvp);
                layout.addView(fileView);
                if (!TextUtils.isEmpty(extra)) {
                    FileContent videoFileContent = (FileContent) msg.getContent();
                    String videoPath = videoFileContent.getLocalPath();
                    if (videoPath == null) {
                        videoFileContent.downloadFile(msg, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                ToastUtils.show(s + "");
                                notifyItemChanged(mData.lastIndexOf(item));
                            }
                        });
                    } else {
                        thumbnailView.setUp(videoPath, JZVideoPlayer.SCREEN_WINDOW_LIST);
                        String thumbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + msg.getServerMessageId();
                        String path = extractThumbnail(videoPath, thumbPath);
                        Glide.with(mContext).load(new File(path)).into(thumbnailView.thumbImageView);
                    }
                }
                break;
            case location:
                LocationContent locationContent = (LocationContent) msg.getContent();
                RelativeLayout inflate = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_location_msg, null);
                inflate.setLayoutParams(layoutParams);
                layout.addView(inflate);
                ImageView mapView = inflate.findViewById(R.id.map_location);
                Number scale = locationContent.getScale();
                String address = locationContent.getAddress();
                Number latitude = locationContent.getLatitude();
                double lat = latitude.doubleValue();
                Number longitude = locationContent.getLongitude();
                double lot = longitude.doubleValue();
                double scle = scale.doubleValue();
                if (scale.doubleValue() < 10) {
                    scle = 10;
                } else {
                    scle = scale.doubleValue();
                }
                if (scale.doubleValue() > 19) {
                    scle = 19;
                } else {
                    scle = scale.doubleValue();
                }
                String mapUri = "http://api.map.baidu.com/staticimage?width=" + DensityUtils.dp2px(mContext, 200) + "&height=" + DensityUtils.dp2px(mContext, 120) + "&center=" + lot + "," + lat + "&zoom=" + scle;
                Glide.with(mContext).load(mapUri).into(mapView);
                TextView location = inflate.findViewById(R.id.tv_location);
                location.setText(address);
                break;
            case voice:
                VoiceContent voiceContent = (VoiceContent) msg.getContent();
                int length = voiceContent.getDuration();
                //控制语音长度显示，长度增幅随语音长度逐渐缩小
                int width = (length * 5) + 50;
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setPadding(40, 20, 20, 40);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView voiceLenth = new TextView(mContext);
                voiceLenth.setMaxWidth(150);
                voiceLenth.setMinWidth(70);
                DisplayMetrics dm = new DisplayMetrics();
                ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
                voiceLenth.setWidth((int) (width * dm.density));
                String lengthStr = length + mContext.getString(R.string.jmui_symbol_second);
                voiceLenth.setText(lengthStr);
                ImageView voice = new ImageView(mContext);
                voiceContent.downloadVoiceFile(msg, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        //已经ok
                    }
                });
                voice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.jmui_send_3));
                voice.setId(R.id.id_voice_image);
                if (oneSelf) {
                    linearLayout.addView(voiceLenth);
                    linearLayout.addView(voice);
                    linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_oneself_message));
                } else {
                    linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_other_people_message));
                    linearLayout.addView(voice);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    voiceLenth.setLayoutParams(params);
                    voiceLenth.setGravity(Gravity.RIGHT);
                    linearLayout.addView(voiceLenth);
                }
                layout.addView(linearLayout);
                if (currentVoiceMessage == item) {
                    startAnimation(item);
                }
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playVoice(item);
                    }
                });

                break;
            case image:
                ImageView pic = helper.getView(R.id.img_msg);
                View view = mLayoutInflater.inflate(R.layout.msg_image, null);
                view.setLayoutParams(layoutParams);
                pic = view.findViewById(R.id.img_msg);
                layout.addView(view);
                if (oneSelf) {
                    msg.setOnContentUploadProgressCallback(new ProgressUpdateCallback() {
                        @Override
                        public void onProgressUpdate(double v) {
                        }
                    });
                } else {
                }
                helper.addOnLongClickListener(R.id.img_msg);
                ImageContent imageContent = (ImageContent) msg.getContent();
                //大图  imageContent.getLocalPath(); //小图 imageContent.getLocalThumbnailPath()
                Glide.with(mContext).load(imageContent.getLocalThumbnailPath()).into(pic);
                break;
            case prompt:
                break;
            default:
                break;
            case text:
                TextView msgView = helper.getView(R.id.tv_msg);
                msgView = (TextView) mLayoutInflater.inflate(R.layout.msg_text, null);
                msgView.setLayoutParams(layoutParams);
                layout.addView(msgView);
                helper.addOnLongClickListener(R.id.tv_msg);
                setTextMessage(helper, item, oneSelf, msgView);
                break;
        }
    }


    /**
     * 播放单条语音
     *
     * @param item
     */
    void playVoice(MessageEntity item, int seekTo) {
        Message msg = item.getMsag();
        try {
            mediaPlayer.reset();
            VoiceContent vc = (VoiceContent) msg.getContent();
            mFIS = new FileInputStream(vc.getLocalPath());
            mFD = mFIS.getFD();
            mediaPlayer.setDataSource(mFD);
            mediaPlayer.prepare();
            mediaPlayer.seekTo(seekTo);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    startAnimation(item);
                    mp.start();
                    currentVoiceMessage = item;
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    currentVoiceMessage = null;
                    stopVoice(item);
                    //如果播放的是发送者的,则进行播放下一条
                    if (item.getMsag().getDirect() == MessageDirect.receive) {
                        int j = mData.lastIndexOf(item);
                        for (int i = j + 1; i < mData.size(); i++) {
                            MessageEntity messageEntity = mData.get(i);
                            if (messageEntity.getMsag().getDirect() == MessageDirect.receive && messageEntity.getMsag().getContentType() == ContentType.voice) {
                                playVoice(messageEntity, 0);
                                break;
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(mContext, R.string.jmui_file_not_found_toast, Toast.LENGTH_SHORT).show();
            VoiceContent vc = (VoiceContent) msg.getContent();
            vc.downloadVoiceFile(msg, new DownloadCompletionCallback() {
                @Override
                public void onComplete(int status, String desc, File file) {
                    if (status == 0) {
                        Toast.makeText(mContext, R.string.download_completed_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, R.string.file_fetch_failed, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } finally {
            try {
                if (mFIS != null) {
                    mFIS.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 播放语音
     *
     * @param item
     * @param voice
     */
    //上次停止的语音id
    private int lastVoiceMessageId;
    //上次语音播放位置
    int stopPosition;
    private MessageEntity currentVoiceMessage;

    private void playVoice(MessageEntity item) {
        if (mediaPlayer == null) {
            initMediaPlayer();
        }
        //点击的是正在播放的就立即停止
        if (mediaPlayer.isPlaying() && currentVoiceMessage == item) {
            try {
                Log.i(TAG, "playVoice: 停播并记录");
                //停止则动画停止
                stopVoice(item);
                lastVoiceMessageId = item.getMsag().getId();
                stopPosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.reset();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            return;

        } else if (currentVoiceMessage != null && currentVoiceMessage != item) {
            Log.i(TAG, "playVoice: 下一个");
            mediaPlayer.stop();
            stopAnimation(currentVoiceMessage);
            playVoice(item, 0);
            //点击的其他的,立即停止当前播放的,并播放当前的点击的
        } else if (item.getMsag().getId() == lastVoiceMessageId && currentVoiceMessage == item) {
            //稍微减下来一点点,可以连续性更好知道上次听到了哪里
            playVoice(item, stopPosition - 3);
            Log.i(TAG, "playVoice: 继续播");
        } else {
            //其他直接从头播放
            playVoice(item, 0);
            Log.i(TAG, "playVoice: 新播放");
        }
    }

    public void startAnimation(MessageEntity item) {
        int viewPosition = mData.lastIndexOf(item);
        ImageView voice = (ImageView) getViewByPosition(viewPosition, R.id.id_voice_image);
        if (voice != null) {
            voice.setImageResource(R.drawable.jmui_voice_send);
            mVoiceAnimation = (AnimationDrawable) voice.getDrawable();
            mVoiceAnimation.start();
        }
    }

    public void stopVoice(MessageEntity messageEntity) {
        stopAnimation(messageEntity);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (audioManager != null) {
            audioManager.setMode(AudioManager.MODE_NORMAL);
        }

    }

    private void stopAnimation(MessageEntity item) {
        int viewPosition = mData.lastIndexOf(item);
        ImageView voice = (ImageView) getViewByPosition(viewPosition, R.id.id_voice_image);
        if (voice != null) {
            voice.setImageResource(R.drawable.jmui_send_3);
            mVoiceAnimation.stop();
        }
    }

    private void initMediaPlayer() {
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.setSpeakerphoneOn(false);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    private void setTextMessage(BaseViewHolder helper, MessageEntity item, boolean oneSelf, TextView msgView) {
        TextView readView = helper.getView(R.id.tv_msg_read);
        helper.addOnLongClickListener(R.id.tv_msg);
        TextContent msag = (TextContent) item.getMsag().getContent();
        msgView.setText(msag.getText());
        if (oneSelf) {
            msgView.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_oneself_message));
        } else {
            msgView.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_other_people_message));
        }
    }

    /**
     * 收到的消息标记已读
     * 将消息标记为已读
     *
     * @param helper
     * @param item
     */
    private void messageReadStatusMonitor(BaseViewHolder helper, MessageEntity item) {
        if (item.getMsag().getDirect() == MessageDirect.receive && !item.getMsag().haveRead()) {
            item.getMsag().setHaveRead(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                }
            });
        }
    }

    /**
     * 消息发送状态监听
     *
     * @param helper
     * @param item
     */
    private void messageSendingStatusMonitor(BaseViewHolder helper, MessageEntity item) {
        MessageStatus status = item.getMsag().getStatus();
        if (!item.getMsag().isSendCompleteCallbackExists() && status != MessageStatus.send_success) {
            item.getMsag().setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseDesc) {
                    if (responseCode == 0) {
                        //消息发送成功
                        helper.getView(R.id.img_msg_status).setVisibility(View.GONE);
                        helper.getView(R.id.tv_msg_read).setVisibility(View.VISIBLE);
                    } else {
                        //消息发送失败
                        helper.getView(R.id.tv_msg_read).setVisibility(View.GONE);
                        helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
                        helper.addOnClickListener(R.id.img_msg_status);
                    }
                }
            });
        }
    }

    /**
     * 设置模板中的公共内容
     * 1.发送人姓名,头像,时间以及点击事件
     *
     * @param helper
     * @param item
     */
    private void setTemplate(BaseViewHolder helper, MessageEntity item) {
        UserInfo fromUser = item.getMsag().getFromUser();
        String userName = fromUser.getUserName();
        TextView nameView = helper.getView(R.id.tv_user_name);
        TextView msgTime = helper.getView(R.id.tv_convertion_time);
        if (isConvertion(mData.lastIndexOf(item))) {
            msgTime.setVisibility(View.VISIBLE);
            //    msgTime.setText(TimeUtils.millis2String(item.getMsag().getCreateTime()));
            TimeFormat timeFormat = new TimeFormat(mContext, item.getMsag().getCreateTime());
            msgTime.setText(timeFormat.getDetailTime());
            msgTime.setVisibility(View.VISIBLE);
        } else {
            msgTime.setVisibility(View.GONE);
        }
        nameView.setText(userName);
        // helper.addOnClickListener(R.id.tv_user_name);
    }

    /**
     * 判断是否是一次新的会话
     * (时长是5分钟)
     */
    boolean isConvertion(int currentConver) {
        if (currentConver == 0) {
            return true;
        } else {
            long createTime = mData.get(currentConver).getMsag().getCreateTime();
            long lastTime = mData.get(currentConver - 1).getMsag().getCreateTime();
            long timeSpan = TimeUtils.getTimeSpan(lastTime, createTime, 1000 * 60);
            return timeSpan >= 5;
        }
    }

    /**
     * 更新未读状态未已读
     *
     * @param serverMsgId
     * @param unReceiptCnt
     */
    public void setUpdateReceiptCount(long serverMsgId, int unReceiptCnt) {
        for (MessageEntity message : mData) {
            if (message.getMsag().getServerMessageId() == serverMsgId) {
                message.getMsag().setUnreceiptCnt(unReceiptCnt);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 消息撤回
     *
     * @param retractedMessage
     */
    public void delMsgRetract(Message retractedMessage) {
        notifyDataSetChanged();
    }

    /**
     * 设置自己的消息是否被读取
     * 这里最后发送的一条
     * msg.getUnreceiptCnt() 获取未读取人数
     * 永远是 0,不知道为什么,
     * 因此在发送的时候需要带设置   msg.setUnreceiptCnt(1); 最低是1
     *
     * @param msg
     * @param text_receipt
     */
    private void setMasseRead(Message msg, TextView text_receipt) {
        if (msg.getDirect() == MessageDirect.send && !msg.getContentType().equals(ContentType.prompt) && msg.getContentType() != ContentType.custom) {
            if (msg.getUnreceiptCnt() == 0) {
                if (msg.getTargetType() == ConversationType.group) {
                    text_receipt.setText("全部已读");
                } else if (!((UserInfo) msg.getTargetInfo()).getUserName().equals(JMessageClient.getMyInfo().getUserName())) {
                    text_receipt.setText("已读");
                }
                text_receipt.setTextColor(mContext.getResources().getColor(R.color.line));
            } else {
                text_receipt.setTextColor(mContext.getResources().getColor(R.color.themeColor));
                if (msg.getTargetType() == ConversationType.group) {
                    text_receipt.setText(msg.getUnreceiptCnt() + "人未读");
                } else if (!((UserInfo) msg.getTargetInfo()).getUserName().equals(JMessageClient.getMyInfo().getUserName())) {
                    text_receipt.setText("未读");
                }
            }
        }
    }

    public void removeMessage(Message message) {
        mData.remove(message);
        notifyDataSetChanged();
    }

    public void appendData(MessageEntity messageEntity) {
        mData.add(messageEntity);
        notifyDataSetChanged();
    }

    public void appendTopData(List<MessageEntity> messageEntities) {
        mData.addAll(0, messageEntities);
        notifyItemRangeInserted(0, messageEntities.size());
    }

    /**
     * 设置图片最小宽高
     *
     * @param path      图片路径
     * @param imageView 显示图片的View
     */
    private ImageView setPictureScale(String extra, Message message, String path, final ImageView imageView) {

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);


        //计算图片缩放比例
        double imageWidth = opts.outWidth;
        double imageHeight = opts.outHeight;
        return setDensity(extra, message, imageWidth, imageHeight, imageView);
    }

    private ImageView setDensity(String extra, Message message, double imageWidth, double imageHeight, ImageView imageView) {
        if (extra != null) {
            imageWidth = 200;
            imageHeight = 200;
        } else {
            if (imageWidth > 350) {
                imageWidth = 550;
                imageHeight = 250;
            } else if (imageHeight > 450) {
                imageWidth = 300;
                imageHeight = 450;
            } else if ((imageWidth < 50 && imageWidth > 20) || (imageHeight < 50 && imageHeight > 20)) {
                imageWidth = 200;
                imageHeight = 300;
            } else if (imageWidth < 20 || imageHeight < 20) {
                imageWidth = 100;
                imageHeight = 150;
            } else {
                imageWidth = 300;
                imageHeight = 450;
            }
        }

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = (int) imageWidth;
        params.height = (int) imageHeight;
        imageView.setLayoutParams(params);
        return imageView;
    }

    public void soundSourceFromSpeakerphone(boolean isSpeakerphoneOn) {
//        if(audioManager!=null&&mediaPlayer != null) {
//            if (isSpeakerphoneOn){
//                audioManager.setMode(AudioManager.MODE_NORMAL);
//                audioManager.setSpeakerphoneOn(isSpeakerphoneOn);
//                mediaPlayer.setAudioStreamType(AudioManager.MODE_NORMAL );
//            }else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    audioManager.setMode(AudioManager.MODE_IN_CALL);
//                }else {
//                    audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
//                }
//                audioManager.setSpeakerphoneOn(isSpeakerphoneOn);
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
//            }
//        }
    }


    public static void download(String urlString, String filename, String savePath) throws Exception {
// 构造URL
        URL url = new URL(urlString);
// 打开连接
        URLConnection con = url.openConnection();
//设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
// 输入流
        InputStream is = con.getInputStream();
// 1K的数据缓冲
        byte[] bs = new byte[1024];
// 读取到的数据长度
        int len;
// 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
// 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
// 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public static String extractThumbnail(String videoPath, String thumbPath) {
        if (!AttachmentStore.isFileExist(thumbPath)) {
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND);
            if (bitmap != null) {
                AttachmentStore.saveBitmap(bitmap, thumbPath, true);
                return thumbPath;
            }
        }
        return thumbPath;
    }
}

