package com.suxuantech.erpsys.chat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.MessageEvent
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent
import cn.jpush.im.android.api.event.MessageRetractEvent
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.fragment.BaseSupportFragment
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import io.rong.imkit.fragment.ConversationListFragment
import org.greenrobot.eventbus.EventBus


/**
 * QuickAdapter fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
open class ConversationListFragment : BaseSupportFragment() {
    private var mColumnCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
        //订阅接收消息,子类只要重写onEvent就能收到
        JMessageClient.registerEventReceiver(this)
        initReceiver()
    }

    /**
     * 注册网络监听
     */
    var mReceiver: NetworkReceiver? = null;

    private fun initReceiver() {
        mReceiver = NetworkReceiver()
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        activity!!.registerReceiver(mReceiver, filter)
    }

    var adapter: MyItemRecyclerViewAdapter? = null;
    var netStatus: LinearLayout? = null;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        var list = view.findViewById<RecyclerView>(R.id.list)
        view.findViewById<View>(R.id.tv_net_error).setOnClickListener(View.OnClickListener {
            var intent =   Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        })
        netStatus = view.findViewById(R.id.ll_net_status)
        // Set the adapter
        if (list is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                list.layoutManager = LinearLayoutManager(context)
            } else {
                list.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            adapter = MyItemRecyclerViewAdapter(R.layout.fragment_item, JMessageClient.getConversationList())
            list.adapter = adapter;
            list.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.line)))
            adapter!!.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var con = adapter.data.get(position) as Conversation
                var infor = con.targetInfo as UserInfo
                var intent = Intent(activity, ConversationActivity::class.java)
                intent.putExtra("userid", infor.userName)
                intent.putExtra("name", infor.nickname)
                startActivity(intent)
            })
        }
        return view
    }


    //监听网络状态的广播
    inner class NetworkReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null && intent.action == "android.net.conn.CONNECTIVITY_CHANGE") {
                val manager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeInfo = manager.activeNetworkInfo
                if (null == activeInfo) {
                    netStatus!!.visibility = View.VISIBLE
                    //     mConvListView.showHeaderView()
                } else
                    netStatus!!.visibility = View.GONE
                //   mConvListView.dismissHeaderView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInfo = manager.activeNetworkInfo
        if (null == activeInfo) {
            netStatus!!.visibility = View.VISIBLE
        } else {
            netStatus!!.visibility = View.GONE
//            mConvListView.dismissHeaderView()
//            mConvListView.showLoadingHeader()
//            mBackgroundHandler.sendEmptyMessageDelayed(DISMISS_REFRESH_HEADER, 1000)
        }
    }




    companion object {
        private val ARG_COLUMN_COUNT = "column-count"
        fun newInstance(columnCount: Int): ConversationListFragment {
            val fragment = ConversationListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * 更新列表的消息
     */
    fun onEventMainThread(msg: String) {
        if (msg.equals("1")) {
            adapter!!.upData(JMessageClient.getConversationList())
        }
    }

    /**
     * 接受消息的事件 收到消息(在线的)主线程
     */
    fun onEventMainThread(event: MessageEvent) {
        EventBus.getDefault().post(Integer(JMessageClient.getAllUnReadMsgCount()))
        adapter!!.upData(JMessageClient.getConversationList())
    }

    /**
     * 对方读取消息后的状态更新
     * @param event
     */
    fun onEventMainThread(event: MessageReceiptStatusChangeEvent) {
        adapter!!.upData(JMessageClient.getConversationList())
    }

    /**
     * 消息撤回
     *
     * @param event
     */
    fun onEventMainThread(event: MessageRetractEvent) {
        adapter!!.upData(JMessageClient.getConversationList())
    }

    override fun onDestroy() {
        super.onDestroy()
        activity!!.unregisterReceiver(mReceiver)
    }

}
