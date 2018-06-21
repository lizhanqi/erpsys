package com.suxuantech.erpsys.ui.activity

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.util.Linkify
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.AppUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.chat.ConversationActivity
import com.suxuantech.erpsys.entity.FormEntity
import com.suxuantech.erpsys.entity.StaffSearchEntity
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.BounceScrollView
import com.suxuantech.erpsys.ui.widget.ScrollEditText
import com.suxuantech.erpsys.utils.MyString
import com.suxuantech.erpsys.utils.StringUtils
import com.suxuantech.erpsys.utils.ToastUtils

class StaffDetailsActivity : TitleNavigationActivity() {
    var dampView: BounceScrollView? = null
    var imgTop: ImageView? = null
    var img_tel: ImageView? = null
    var img_msg: ImageView? = null
    var tb: Toolbar? = null
    var tvNameAndPost: TextView? = null
    var rvDetails: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_details)

        lineView.visibility= View.GONE
        var data = intent.extras.getParcelable<StaffSearchEntity.DataBean>("data");
        var uri = Uri.parse("tel:"+data.telephone);
        var telIntent = Intent(Intent.ACTION_DIAL, uri);
        dampView = findViewById<BounceScrollView>(R.id.dampView)
        imgTop = findViewById<ImageView>(R.id.img_top)
        dampView?.setImageView(imgTop)
        tb = findViewById<Toolbar>(R.id.tb)
        setSupportActionBar(tb)
        supportToolbar()
        tvNameAndPost = findViewById<TextView>(R.id.tv_name_and_post)
        rvDetails = findViewById<RecyclerView>(R.id.rv_details)
        img_tel = findViewById<ImageView>(R.id.img_tel)
        img_msg= findViewById<ImageView>(R.id.img_msg)
        img_tel?.setOnClickListener {
            startActivity(telIntent)
        }
        img_msg?.setOnClickListener {
            if (data.staffname!=App.getApplication().userInfor.staffname){
                if(StringUtils.empty(data.jg_username)){
                    ToastUtils.snackbarShort("对方未曾登录过系统,收不到消息哦","确定")
                }else{
                    val intent = Intent(this, ConversationActivity::class.java)
                    intent.putExtra("name",  data.staffname)
                    intent.putExtra("userid",  data.jg_username)
                    startActivity(intent)
                }

            }else{
                ToastUtils.snackbarShort("不能与自己聊天哦","确定")
            }
        }
        tvNameAndPost?.setText(MyString(StringUtils.safetyString(data.staffname)).setSize(25))
        tvNameAndPost?.append(MyString("\n"+StringUtils.safetyString(data.main_position_name)).setSize(15))
//        data.qq="1938364661"
//        data.weixin="code03280318"
//        data.email="1032992210@qq.com"
        val arrayList = ArrayList<FormEntity>();
        arrayList.add(FormEntity("所属部门:", StringUtils.safetyString(data.department_name)))
        arrayList.add(FormEntity("所属店面:", StringUtils.safetyString(data.shop_name)))
        arrayList.add(FormEntity("职工编号:", StringUtils.safetyString(data.staffnumber)))
        val telEntity = FormEntity("手\u2000机\u2000号:", StringUtils.safetyString(data.telephone), Linkify.PHONE_NUMBERS);
        telEntity.valueAutoAction = telIntent
        arrayList.add(telEntity)
        var qqFormEntity = FormEntity("Q\u3000\u3000Q:", StringUtils.safetyString(data.qq))
        if (AppUtils.isAppInstalled("com.tencent.mobileqq")) {
            var qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin="+StringUtils.safetyString(data.qq)+"&version=1";
            val QQIntent = Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl));
          //  val QQIntent = packageManager.getLaunchIntentForPackage("com.tencent.mobileqq")
            qqFormEntity.valueAutoAction = QQIntent;
        }
        arrayList.add(qqFormEntity)
        val wechatFormEntity = FormEntity("微\u3000\u3000信:", StringUtils.safetyString(data.weixin));
        if (AppUtils.isAppInstalled("com.tencent.mm")) {
            val wechatIntent = Intent(Intent.ACTION_MAIN)
            val cmp = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
            wechatIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            wechatIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            wechatIntent.component = cmp
            wechatFormEntity.valueAutoAction=wechatIntent;
        }
        arrayList.add(wechatFormEntity)
        val mainlFormEntity = FormEntity("邮\u3000\u3000箱:", StringUtils.safetyString(data.email), Linkify.EMAIL_ADDRESSES);
        val mailIntent = Intent(Intent.ACTION_SENDTO)
        mailIntent.data = Uri.parse("mailto:"+StringUtils.safetyString(data.email))
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "来自"+ App.getApplication().userInfor.staffname+"的邮件")
        mailIntent.putExtra(Intent.EXTRA_TEXT, "\u3000\u3000您好!我是")
        mainlFormEntity.valueAutoAction=mailIntent
        arrayList.add(mainlFormEntity)
        var adapter = object : QuickAdapter<FormEntity>(R.layout.item_form, arrayList) {
            override fun convert(helper: BaseViewHolder?, item: FormEntity?) {
                var key = helper?.getView<TextView>(R.id.tv_form_key)
                var value = helper?.getView<ScrollEditText>(R.id.tv_form_value);
               // value?.setAutoLinkMask(item?.valueAutoLink!!)
                key?.setText(item?.key)
                value?.setText(item?.value)
                //如果设置这种代替xml中edittext的  android:editable="false"但是需要点击两次才能跳转,感觉比较慢
                value?.setKeyListener(null);
                //设置不可编辑状态：这种不能复制,但是可以点击直接跳转到对应的意图页面
//                value?.setFocusable(false);
//                value?.setFocusableInTouchMode(false);
//                设置可编辑状态：()
//                editText.setFocusableInTouchMode(true);
//                editText.setFocusable(true);
//                editText.requestFocus();
                if (item?.valueAutoAction != null) {
                    value?.isClickable=true;
                    value?.isEnabled=true;
                    value?.setOnClickListener {
                        startActivity(item.valueAutoAction)
                    }
                }
            }
        };
        rvDetails?.adapter = adapter
    }

    override fun initImmersionBar() {
        statusBarFollow = imgTop
        super.initImmersionBar()
    }
}
