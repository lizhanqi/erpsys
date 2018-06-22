package com.suxuantech.erpsys.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.baidu.mapapi.SDKInitializer
import com.suxuantech.erpsys.R

class ConversationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        SDKInitializer.initialize(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_list)
    }
}
