package com.suxuantech.erpsys.entity

import android.os.Parcel
import android.os.Parcelable

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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
class PhotoSchemeSearchEntity : BaseResult2<PhotoSchemeSearchEntity.DataBean>() {


    class DataBean : Parcelable {
        /**
         * customerid : df78a459-21a5-4435-89f9-681b46996321
         * xingming :  杨佳江
         * wphone : 13156987456
         * mphone :
         * cssname :
         * area :
         * weddingdate :
         * orderid : SY18011800001
         * targetdate : 20180118
         * consumption_type : 三亚旅拍
         * acceptor_address : null
         * total_money : 8194
         * payment_money : 2100
         * nopayment_money : 6094
         * storeconsuitant1 : 姜明
         * storeconsuitant2 :
         * storeconsuitant3 :
         * ordershop_name : 沈阳时尚经典婚纱店
         * ordernote :
         * phototype : 补拍
         * photodate : 6456-03-08 00:00:00.000
         * phototime : null
         * photostate : 已拍完
         * cameraman : 郭靖
         * photoassistant : 黄蓉
         * lighting_engineer : 杨过
         * designer : 风清扬
         * mentor : 小黑
         * dresser : 令狐冲
         * photonote :
         * photobase : 巴黎
         * id : 9
         * shop_code : ZX002
         * jiaji : 1
         * shop_name : 沈阳时尚经典婚纱店
         * photocount : 454
         * dress_count : 89898
         * locationcount : 10
         * Interiorcount : 10
         * shexiangshi : null
         * jianjishi : null
         * zhuozhuangshi : null
         * huazhuangzhuli : null
         * haijingshu : null
         * sheyingshiJB : null
         * huazhuangshiJB : null
         * selectday : 20180125
         */

        var customerid: String? = null
        var xingming: String? = null
        var wphone: String? = null
        var mphone: String? = null
        var cssname: String? = null
        var area: String? = null
        var weddingdate: String? = null
        var orderid: String? = null
        var targetdate: String? = null
        var consumption_type: String? = null
        var acceptor_address: String? = null
        var total_money: Int = 0
        var payment_money: Int = 0
        var nopayment_money: Int = 0
        var storeconsuitant1: String? = null
        var storeconsuitant2: String? = null
        var storeconsuitant3: String? = null
        var ordershop_name: String? = null
        var ordernote: String? = null
        var phototype: String? = null
        var photodate: String? = null
        var phototime: String? = null
        var photostate: String? = null
        var cameraman: String? = null
        var photoassistant: String? = null
        var lighting_engineer: String? = null
        var designer: String? = null
        var mentor: String? = null
        var dresser: String? = null
        var photonote: String? = null
        var photobase: String? = null
        var id: Int = 0
        var shop_code: String? = null
        var jiaji: Int = 0
        var shop_name: String? = null
        var photocount: String? = null
        var dress_count: Int = 0
        var locationcount: Int = 0
        var interiorcount: Int = 0
        var shexiangshi: String? = null
        var jianjishi: String? = null
        var zhuozhuangshi: String? = null
        var huazhuangzhuli: String? = null
        var haijingshu: String? = null
        var sheyingshiJB: String? = null
        var huazhuangshiJB: String? = null
        var selectday: String? = null

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.customerid)
            dest.writeString(this.xingming)
            dest.writeString(this.wphone)
            dest.writeString(this.mphone)
            dest.writeString(this.cssname)
            dest.writeString(this.area)
            dest.writeString(this.weddingdate)
            dest.writeString(this.orderid)
            dest.writeString(this.targetdate)
            dest.writeString(this.consumption_type)
            dest.writeString(this.acceptor_address)
            dest.writeInt(this.total_money)
            dest.writeInt(this.payment_money)
            dest.writeInt(this.nopayment_money)
            dest.writeString(this.storeconsuitant1)
            dest.writeString(this.storeconsuitant2)
            dest.writeString(this.storeconsuitant3)
            dest.writeString(this.ordershop_name)
            dest.writeString(this.ordernote)
            dest.writeString(this.phototype)
            dest.writeString(this.photodate)
            dest.writeString(this.phototime)
            dest.writeString(this.photostate)
            dest.writeString(this.cameraman)
            dest.writeString(this.photoassistant)
            dest.writeString(this.lighting_engineer)
            dest.writeString(this.designer)
            dest.writeString(this.mentor)
            dest.writeString(this.dresser)
            dest.writeString(this.photonote)
            dest.writeString(this.photobase)
            dest.writeInt(this.id)
            dest.writeString(this.shop_code)
            dest.writeInt(this.jiaji)
            dest.writeString(this.shop_name)
            dest.writeString(this.photocount)
            dest.writeInt(this.dress_count)
            dest.writeInt(this.locationcount)
            dest.writeInt(this.interiorcount)
            dest.writeString(this.shexiangshi)
            dest.writeString(this.jianjishi)
            dest.writeString(this.zhuozhuangshi)
            dest.writeString(this.huazhuangzhuli)
            dest.writeString(this.haijingshu)
            dest.writeString(this.sheyingshiJB)
            dest.writeString(this.huazhuangshiJB)
            dest.writeString(this.selectday)
        }

        constructor() {}

        protected constructor(`in`: Parcel) {
            this.customerid = `in`.readString()
            this.xingming = `in`.readString()
            this.wphone = `in`.readString()
            this.mphone = `in`.readString()
            this.cssname = `in`.readString()
            this.area = `in`.readString()
            this.weddingdate = `in`.readString()
            this.orderid = `in`.readString()
            this.targetdate = `in`.readString()
            this.consumption_type = `in`.readString()
            this.acceptor_address = `in`.readString()
            this.total_money = `in`.readInt()
            this.payment_money = `in`.readInt()
            this.nopayment_money = `in`.readInt()
            this.storeconsuitant1 = `in`.readString()
            this.storeconsuitant2 = `in`.readString()
            this.storeconsuitant3 = `in`.readString()
            this.ordershop_name = `in`.readString()
            this.ordernote = `in`.readString()
            this.phototype = `in`.readString()
            this.photodate = `in`.readString()
            this.phototime = `in`.readString()
            this.photostate = `in`.readString()
            this.cameraman = `in`.readString()
            this.photoassistant = `in`.readString()
            this.lighting_engineer = `in`.readString()
            this.designer = `in`.readString()
            this.mentor = `in`.readString()
            this.dresser = `in`.readString()
            this.photonote = `in`.readString()
            this.photobase = `in`.readString()
            this.id = `in`.readInt()
            this.shop_code = `in`.readString()
            this.jiaji = `in`.readInt()
            this.shop_name = `in`.readString()
            this.photocount = `in`.readString()
            this.dress_count = `in`.readInt()
            this.locationcount = `in`.readInt()
            this.interiorcount = `in`.readInt()
            this.shexiangshi = `in`.readString()
            this.jianjishi = `in`.readString()
            this.zhuozhuangshi = `in`.readString()
            this.huazhuangzhuli = `in`.readString()
            this.haijingshu = `in`.readString()
            this.sheyingshiJB = `in`.readString()
            this.huazhuangshiJB = `in`.readString()
            this.selectday = `in`.readString()
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<DataBean> = object : Parcelable.Creator<DataBean> {
                override fun createFromParcel(source: Parcel): DataBean {
                    return DataBean(source)
                }

                override fun newArray(size: Int): Array<DataBean?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
