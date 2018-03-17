package com.suxuantech.erpsys.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.ProgressUpdateCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.enums.MessageStatus;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

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
        //    boolean oneSelf = helper.getItemViewType() == MessageEntity.ONESELF;
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

            } else if (messageStatus == MessageStatus.send_going) {
                //发送中
                helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_msg_read).setVisibility(View.GONE);
                ((ImageView) helper.getView(R.id.img_msg_status)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.jmui_sending_img));
            } else if (messageStatus == MessageStatus.send_fail) {
                //发送失败
                helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
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
        switch (msg.getContentType()) {
            case file:
                break;
            case location:
                break;
            case image:
                ImageView pic = helper.getView(R.id.img_msg);
                View view = mLayoutInflater.inflate(R.layout.msg_image, null);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
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
            default:
                break;
            case text:
                TextView msgView = helper.getView(R.id.tv_msg);
                msgView = (TextView) mLayoutInflater.inflate(R.layout.msg_text, null);
                ViewGroup.LayoutParams lps = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                msgView.setLayoutParams(lps);
                layout.addView(msgView);
                helper.addOnLongClickListener(R.id.tv_msg);
                setTextMessage(helper, item, oneSelf, msgView);
                break;
        }
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
}

