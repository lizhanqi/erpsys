package com.suxuantech.erpsys.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.blankj.utilcode.util.KeyboardUtils
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.utils.AppUtil
import com.suxuantech.erpsys.utils.MailManager
import com.yanzhenjie.alertdialog.AlertDialog
class FeedbackActivity : TitleNavigationActivity() {
    var etFeeback: EditText? = null
    var etFeebackPeople: EditText? = null
    var btnFeedbak: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        etFeeback = findViewById<EditText>(R.id.et_feeback)
        etFeebackPeople = findViewById<EditText>(R.id.et_feeback_people)
        btnFeedbak = findViewById<Button>(R.id.btn_feedbak)
        btnFeedbak?.setOnClickListener(View.OnClickListener {
            if (!etFeeback?.text?.isEmpty()!!) {
                KeyboardUtils.hideSoftInput(it)
            var  con=   "反馈联系人:" + etFeebackPeople?.text.toString() + "</br>反馈问题:" + etFeeback?.text.toString();
           //     com.android.email.Utility.base64Encode(mFileName)
       // var name2 = "=?utf8?B?" + name + "?="

                MailManager.getInstance().sendMail("问题反馈,版本号：" + AppUtil.getVersionCode(applicationContext) + "版本名称：" +
                        AppUtil.getVersionName(applicationContext),   con   )

                var d = AlertDialog.newBuilder(this).setMessage("感谢您的反馈,我们会第一时间解决该问题").setNegativeButton("确定", null)
                        .setOnDismissListener(DialogInterface.OnDismissListener {
                            it.dismiss()
                            finish()
                        }).create()
                d.show()

            } else {
                etFeeback?.requestFocus()
            }
        })


    }

}
