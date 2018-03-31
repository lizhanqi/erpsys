package com.suxuantech.erpsys.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import com.suxuantech.erpsys.R
import java.util.*

class JLoginActivity : ChatBaseActivity() {
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this
        //注册接收消息(成为订阅者), 注册后可以直接重写onEvent方法接收消息
        JMessageClient.registerEventReceiver(this)
        setContentView(R.layout.activity_jlogin)
        val btnRegist = findViewById<Button>(R.id.bt_regist)
        btnRegist    .setOnClickListener(View.OnClickListener { v ->
            val tex   =  findViewById<TextView>(R.id.et_name)
            val tname=       tex.text
            val pass   =  findViewById<TextView>(R.id.et_pass)
            val passtext=       pass.text
            regist(tname,passtext)
        })
        val btnLogin = findViewById<Button>(R.id.bt_login)
        btnLogin.setOnClickListener(View.OnClickListener { v ->
            val tex   =  findViewById<TextView>(R.id.et_name)
            val tname=       tex.text
            val pass   =  findViewById<TextView>(R.id.et_pass)
            val passtext=       pass.text
            login(tname,passtext)
        })
        val bt10086 = findViewById<Button>(R.id.bt_10086)
        bt10086.setOnClickListener(View.OnClickListener { v ->
            login("123456","123456")
        })
        val bt123456 = findViewById<Button>(R.id.bt_123456)
        bt123456.setOnClickListener(View.OnClickListener { v ->
            login("10086","10086")
        })
        //  与hello
        val bthello = findViewById<Button>(R.id.bt_hello)
        bthello.setOnClickListener(View.OnClickListener { v ->
            login("10000","10000")
        })
    }

    private fun regist(tname: CharSequence, passtext: CharSequence) {
        //设置用户信息聊天对象及群聊Id, 此处使用了前缀加上随机生成的4个字符组成用户名, 而聊天对象是提前注册好的
        //关于注册在ReadMe中也有提到
            //val username = getUsername(4)
            var mMyName = tname.toString() //+ username
            val dialog = DialogCreator.createLoadingDialog(this, "注册中ing....")
            dialog.show()
            JMessageClient.register(mMyName, passtext.toString(), object : BasicCallback() {
                override fun gotResult(status: Int, desc: String) {
                    dialog.dismiss()
                    if (status == 0) {
                        Toast.makeText(mContext, " 用户名:" + mMyName + "注册成功", Toast.LENGTH_SHORT).show()
                    } else {
                        HandleResponseCode.onHandle(mContext, status, false)
                    }
                }
            })
    }
    /**
     * 产生一个随机的字符串
     */
    fun getUsername(length: Int): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        val buf = StringBuffer()
        for (i in 0 until length) {
            val num = random.nextInt(62)
            buf.append(str[num])
        }

        return buf.toString()
    }
    fun  tochat(who :String){
        var v =Intent(  JLoginActivity@this, ConversationActivity::class.java)
        v.putExtra("name",who)
        startActivity( v)
    }
    /**
     * 登录
     */
    fun login(tname: CharSequence, passtext: CharSequence) {
        val loadingDialog = DialogCreator.createLoadingDialog(this,"登录中ing...")
        loadingDialog.show()
        JMessageClient.login(tname.toString(), passtext.toString(), object : BasicCallback() {
            override fun gotResult(status: Int, desc: String) {
                loadingDialog.dismiss()
                if (status == 0) {
                    if(tname.equals("123456")){
                        tochat("10086")
                    }else if(tname.equals("10000")){
                        tochat("hello")
                    }else{
                        tochat("123456")
                    }
                    Toast.makeText(mContext, "登录成功!", Toast.LENGTH_SHORT).show()
                    Log.d("DemoActivity", "Login success")
                } else {
                    HandleResponseCode.onHandle(mContext, status, false)
                }
            }
        })
    }

}
