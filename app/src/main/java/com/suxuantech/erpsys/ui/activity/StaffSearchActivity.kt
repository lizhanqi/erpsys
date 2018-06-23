package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.StaffSearchEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText
import com.suxuantech.erpsys.utils.MyString
import com.suxuantech.erpsys.utils.StringUtils
import com.suxuantech.erpsys.utils.Text2Bitmap
import com.yanzhenjie.nohttp.rest.Response

class StaffSearchActivity : TitleNavigationActivity() {
    val adapter: QuickAdapter<StaffSearchEntity.DataBean> = object : QuickAdapter<StaffSearchEntity.DataBean>(R.layout.item_contact, null) {
        override fun convert(helper: BaseViewHolder?, item: StaffSearchEntity.DataBean?) {
            val imagHead = helper?.getView<ImageView>(R.id.img_contact_head)
            val contanTextView = helper?.getView<TextView>(R.id.tv_contact_name)
            val checkBox = helper?.getView<CheckBox>(R.id.cb_person)
            val group_company_name = StringUtils.safetyString(item?.group_company_name);
            val brandclass = StringUtils.safetyString(item?.brandclass);
            val shop_name = StringUtils.safetyString(item?.shop_name);
            val department_name = StringUtils.safetyString(item?.department_name);
            val main_position_name = StringUtils.safetyString(item?.main_position_name);
            var detail = StringBuilder();
            detail.append("\n")
            detail.append(group_company_name)
            detail.append("->")
            detail.append(brandclass)
            detail.append("->")
            detail.append(shop_name)
            detail.append("->")
            detail.append(department_name)
            detail.append("->")
            detail.append(main_position_name)
            detail.replace(Regex(""),"")
            //这里用java的,kotlin的            detail.replace(Regex(""),"") 无效但是debug可以
                var re= detail.replace(Regex("->->"),"")
            if (!StringUtils.empty(brandclass)) {
                re= re.replace(  group_company_name + "->", "");
           }
           re= re.replace(Regex("->->"),"")
//            var re = StringUtils.replace(detail.toString(), "->->", "->")
//            if (!StringUtils.empty(brandclass)) {
//                re= StringUtils.replace( re,group_company_name + "->", "");
//            }
//              re = StringUtils.replace(re, "->->", "->")
//            * brandclass : 时尚经典事业部
//            * shop_name : 时尚经典旗舰店
//            * department_name : 门市部
//            * main_position_name : 门市主管
            contanTextView?.setText(MyString(StringUtils.safetyString(item?.staffname)).setSize(22))
            contanTextView?.append(MyString(re).setSize(10))
            val option = intent.getBooleanExtra("option", false);
            if (option) {
                checkBox?.visibility = View.VISIBLE
                contanTextView?.setCompoundDrawables(null, null, null, null)
            } else {
                checkBox?.visibility = View.GONE
            }
            if (!StringUtils.empty(item?.staffname)) {
                imagHead?.setImageBitmap(Text2Bitmap.getNameBitMap(StringUtils.safetyString(item?.staffname), resources.getColor(R.color.white)))
            } else
                imagHead?.setImageBitmap(Text2Bitmap.getNewBitMap("未知", resources.getColor(R.color.white)))
//                val options2 = RequestOptions()
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher)//预加载图片
//                        .error(R.mipmap.ic_launcher)//加载失败显示图片
//                        .priority(Priority.HIGH)//优先级
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
//                        .transform(GlideRoundTransform(10))//转化为圆形
//                Glide?.with(imagHead).load(item?.getHead()).apply(options2).into(imagHead)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_search)
        supportToolbar()
        setCenterTitle("通讯录搜索")
        val defaultItemDecoration = DefaultItemDecoration(resources.getColor(R.color.mainNavline_e7))
        defaultItemDecoration.setJustLeftOffsetX(resources.getDimension(R.dimen.px50).toInt())
        var otcKey = findViewById<OneKeyClearAutoCompleteText>(R.id.otc_key);
        var rvResult = findViewById<RecyclerView>(R.id.rv_result);
        rvResult.addItemDecoration(defaultItemDecoration)
        rvResult.adapter = adapter
        otcKey.setLeftDrawableClickListen {
            searchPerson(otcKey.text.toString().trim(), true)
        }
        otcKey.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchPerson(otcKey.text.toString().trim(), true)
                return@OnEditorActionListener true
            }
            false
        })
    }

    fun searchPerson(key: String, show: Boolean) {
        if (StringUtils.empty(key)) {
            return
        }
        var url = Contact.getFullUrl(Contact.SEARCH_PERSON, Contact.OA)
        val login = JavaBeanRequest(url, StaffSearchEntity::class.java)
        login.add("key_words", key)
        val searchByCustmor = object : HttpListener<StaffSearchEntity> {
            override fun onSucceed(what: Int, response: Response<StaffSearchEntity>) {
                if (response.get().isOK) {
                    if (response.get().isOK) {
                        setData2View(response.get().data)
                    } else {

                    }
                }
            }

            override fun onFailed(what: Int, response: Response<StaffSearchEntity>) {
            }
        }
        request<StaffSearchEntity>(0, login, searchByCustmor, false, show)
    }

    fun setData2View(data: List<StaffSearchEntity.DataBean>?) {
        adapter.updateAll(data)
        val option = intent.getBooleanExtra("option", false);
            adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intts = Intent(this, StaffDetailsActivity::class.java)
                var bd = Bundle();
                bd.putParcelable("data", data?.get(position))
                intts.putExtras(bd)
                startActivity(intts)
        }
    }
}
