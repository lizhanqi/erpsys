package com.suxuantech.erpsys.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;

import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
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
 * @Description: todo(用一句话描述该文件做什么)
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MessageEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MessageEntity> data) {
        super(data);
        addItemType(MessageEntity.ONESELF, R.layout.oneself_message);
        addItemType(MessageEntity.OTHER_PEOPLE, R.layout.other_people_message);
        //     addItemType(MessageEntity.IMG, R.layout.image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        setTemplate(helper, item);
        LinearLayout layout = helper.getView(R.id.ll_msg);
        if (helper.getItemViewType() == MessageEntity.ONESELF) {
            messageSendingStatusMonitor(helper, item);
        }else {
            messageReadStatusMonitor(helper, item);
        }
        switch (item.getMsag().getContentType()) {
            default:
            case text:
                TextView msg = helper.getView(R.id.tv_msg);
                if (msg == null) {
                    msg = (TextView) mLayoutInflater.inflate(R.layout.msg_text, null);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    msg.setLayoutParams(lp);
                    layout.addView(msg);
                }
                helper.addOnLongClickListener(R.id.tv_msg);
                helper.addOnClickListener(R.id.tv_user_name);
                helper.addOnClickListener(R.id.img_msg_status);
                if (helper.getItemViewType() == MessageEntity.ONESELF) {
                    MessageStatus messageStatus = item.getMsag().getStatus();
                    if (messageStatus == MessageStatus.send_success) {
                        helper.getView(R.id.img_msg_status).setVisibility(View.GONE);
                    } else if (messageStatus == MessageStatus.send_going) {
                        helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
                        ((ImageView)helper.getView(R.id.img_msg_status)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.jmui_sending_img));
                        //发送中
                    } else if (messageStatus == MessageStatus.send_fail) {
                        helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
                        //发送失败
                    } else if (messageStatus == MessageStatus.send_draft) {
                        //草稿
                    }
                    msg.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_oneself_message));
                } else {
                    msg.setBackground(mContext.getResources().getDrawable(R.drawable.sl_bg_chat_other_people_message));
                }
                TextContent msag = (TextContent) item.getMsag().getContent();
                msg.setText(msag.getText());
                break;
        }
    }

    /**
     *收到的消息标记已读
     * @param helper
     * @param item
     */
    private void messageReadStatusMonitor(BaseViewHolder helper, MessageEntity item) {
        //消息接收方发送已读回执
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
        item.getMsag().setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseDesc) {
                if (responseCode == 0) {
                    //消息发送成功
                    helper.getView(R.id.img_msg_status).setVisibility(View.GONE);
                    helper.getView(R.id.tv_msg_read).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.tv_msg_read).setVisibility(View.GONE);
                    helper.getView(R.id.img_msg_status).setVisibility(View.VISIBLE);
                    //消息发送失败
                }
            }
        });
    }

    /**
     * 设置模板中的公共内容
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
            msgTime.setText(TimeUtils.millis2String(item.getMsag().getCreateTime()));
        } else {
            msgTime.setVisibility(View.GONE);
        }
        nameView.setText(userName);
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
     * @param retractedMessage
     */
    public void delMsgRetract(Message retractedMessage) {
    }
}

