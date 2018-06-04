package com.suxuantech.erpsys.chat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.jpush.im.android.api.content.TextContent
import cn.jpush.im.android.api.enums.ContentType
import cn.jpush.im.android.api.enums.ConversationType
import cn.jpush.im.android.api.enums.MessageDirect
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import com.blankj.utilcode.util.EncodeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.chat.ConversationListFragment.OnListFragmentInteractionListener
import com.suxuantech.erpsys.chat.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(layoutResId: Int, data: List<Conversation>?) : BaseQuickAdapter<Conversation, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: com.chad.library.adapter.base.BaseViewHolder, item: Conversation) {
        if (item.type == ConversationType.single) {
            val userInfo = item.getTargetInfo() as UserInfo
            var head = helper.getView<ImageView>(R.id.img_list_head)
            var content = helper.getView<TextView>(R.id.tv_list_content)
            var name = helper.getView<TextView>(R.id.tv_list_name)
            var time = helper.getView<TextView>(R.id.tv_list_time)
            var unread = helper.getView<TextView>(R.id.tv_unread)

            if(item.unReadMsgCnt>0){
                unread.visibility= View.VISIBLE
                unread.text = "" + item.unReadMsgCnt
            }else{
                unread.visibility= View.GONE
            }
            name.setText(String(EncodeUtils.base64Decode(userInfo.userName) ));
            if (item.latestMessage != null) {
                val timeFormat = TimeFormat(mContext, item.latestMessage.createTime)
                time.setText(timeFormat.detailTime)
                val oneSelf = item.latestMessage.getDirect() == MessageDirect.send
                if (item.latestMessage.contentType == ContentType.text) {
                    val textContent = item.latestMessage.content as TextContent
                    if (oneSelf) {
                        if (item.latestMessage.unreceiptCnt > 0) {
                            content.setText("[未读]" + textContent.text)
                        } else {
                            content.setText("[已读]" + textContent.text)
                        }
                    } else {
                        content.setText(textContent.text)
                    }
                } else if (item.latestMessage.contentType == ContentType.file) {
                    if (oneSelf) {
                        if (item.latestMessage.unreceiptCnt > 0) {
                            content.setText("[未读]")
                        } else {
                            content.setText("[已读][小视频]")
                        }
                    } else {
                        content.setText("[小视频]")
                    }
                } else if (item.latestMessage.contentType == ContentType.image) {
                    if (oneSelf) {
                        if (item.latestMessage.unreceiptCnt > 0) {
                            content.setText("[未读][图片]")
                        } else {
                            content.setText("[已读][图片]")
                        }
                    } else {
                        content.setText("[图片]")
                    }
                } else if (item.latestMessage.contentType == ContentType.location) {
                    if (oneSelf) {
                        if (item.latestMessage.unreceiptCnt > 0) {
                            content.setText("[未读][位置]")
                        } else {
                            content.setText("[已读][位置]")
                        }
                    } else {
                        content.setText("[位置]")

                    }
                } else {
                    content.setText("")
                }
            }
        }
    }

    fun upData(conversationList: List<Conversation>) {
        mData = conversationList
        notifyDataSetChanged()
    }
}
