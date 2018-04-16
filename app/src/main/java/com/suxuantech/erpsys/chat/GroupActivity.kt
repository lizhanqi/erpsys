package com.suxuantech.erpsys.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import cn.jpush.im.android.api.ChatRoomManager
import cn.jpush.im.android.api.callback.RequestCallback
import cn.jpush.im.android.api.model.ChatRoomInfo
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.utils.ToastUtils


class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        setSupportActionBar(findViewById(R.id.tb_group))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        /**
         * 获取当前用户所加入的所有聊天室的信息。
         * @param callback 接口回调
         * @since 2.4.0
         */
        ChatRoomManager.getChatRoomListByUser(object : RequestCallback<List<ChatRoomInfo>>() {
            override fun gotResult(i: Int, s: String, chatRoomInfos: List<ChatRoomInfo>) {
               // chatRoomInfos.forEach()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_group, menu)
        return true
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> this.finish()//真正实现回退功能的代码
         R.id.crete_group -> ToastUtils.showShort("建群")
        }

        return super.onOptionsItemSelected(item)
    }
}
