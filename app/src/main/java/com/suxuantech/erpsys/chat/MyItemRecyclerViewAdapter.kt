package com.suxuantech.erpsys.chat

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import cn.jpush.im.android.api.content.TextContent
import cn.jpush.im.android.api.enums.ContentType
import cn.jpush.im.android.api.enums.ConversationType
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
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
//        val groupInfo = item.getTargetInfo() as GroupInfo
            var head = helper.getView<ImageView>(R.id.img_list_head)
            var content = helper.getView<TextView>(R.id.tv_list_content)
            var name = helper.getView<TextView>(R.id.tv_list_name)
            var time = helper.getView<TextView>(R.id.tv_list_time)
            var unread = helper.getView<TextView>(R.id.tv_unread)
            unread.text = "" + item.unReadMsgCnt
            name.setText(userInfo.userName)
            if(item.latestMessage!=null) {
                val timeFormat = TimeFormat(mContext, item.latestMessage.createTime)
                time.setText(timeFormat.detailTime)
                if (item.latestMessage.contentType == ContentType.text) {
                    val textContent = item.latestMessage.content as TextContent
                    content.setText(textContent.text)
                } else if (item.latestMessage.contentType == ContentType.file) {
                    content.setText("[小视频]")
                } else if (item.latestMessage.contentType == ContentType.image) {
                    content.setText("[图片]")
                } else if (item.latestMessage.contentType == ContentType.location) {
                    content.setText("[位置]")
                } else {
                    content.setText("")
                }
            }
        }
    }

    fun upData(conversationList: List<Conversation>) {
        mData=conversationList
        notifyDataSetChanged()
    }
}


///**
// * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
// * specified [OnListFragmentInteractionListener].
// * TODO: Replace the implementation with code for your data type.
// */
//class MyItemRecyclerViewAdapter(private val mValues: List<DummyItem>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.fragment_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.mItem = mValues[position]
//        holder.mIdView.text = mValues[position].id
//        holder.mContentView.text = mValues[position].content
//
//        holder.mView.setOnClickListener {
//            mListener?.onListFragmentInteraction(holder.mItem)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return mValues.size
//    }
//
//    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
//        val mIdView: TextView
//        val mContentView: TextView
//        var mItem: DummyItem? = null
//
//        init {
//            mIdView = mView.findViewById<View>(R.id.id) as TextView
//            mContentView = mView.findViewById<View>(R.id.content) as TextView
//        }
//
//        override fun toString(): String {
//            return super.toString() + " '" + mContentView.text + "'"
//        }
//    }
//}
