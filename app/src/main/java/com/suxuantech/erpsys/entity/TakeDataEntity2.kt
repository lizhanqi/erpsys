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
class TakeDataEntity2 : BaseResult2<TakeDataEntity2.DataBean>() {

    /**
     * Code : 200
     * Data : [{"id":428,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","sorting":8,"categories":"摄影","consumption_name":"8*9水晶相册","amount":1,"remarks":"","transfer_certificate":1,"take_away_date":"2018-04-27 00:00:00.000","item_pagecount":33,"item_sheetscount":10,"take_away_time":null,"whethercantake":"完成","whether_already_take":"已取","size":"","getuser":"萌萌","price":0,"cost":12,"total":0,"send_count":0,"shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","issources":0,"create_time":"2018-02-01 18:52:33.000","take_can_date":"2018-04-18 00:00:00.000","Urgent":"","take_urgent_date":null,"sj_sendtype":"","sj_senddate":null,"sj_sendname":"","sj_sendnumberplate":"","sj_sendphone":"","sj_sendaddress":"","sj_sendremarks":"","sj_area":""}]
     */

    class DataBean : Parcelable {
        /**
         * id : 428
         * orderId : SY18012000007
         * customerid : c3ddb62a-dcec-4b65-8d77-f335fbbd00f8
         * sorting : 8
         * categories : 摄影
         * consumption_name : 8*9水晶相册
         * amount : 1
         * remarks :
         * transfer_certificate : 1
         * take_away_date : 2018-04-27 00:00:00.000
         * item_pagecount : 33
         * item_sheetscount : 10
         * take_away_time : null
         * whethercantake : 完成
         * whether_already_take : 已取
         * size :
         * getuser : 萌萌
         * price : 0
         * cost : 12
         * total : 0
         * send_count : 0
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * issources : 0
         * create_time : 2018-02-01 18:52:33.000
         * take_can_date : 2018-04-18 00:00:00.000
         * Urgent :
         * take_urgent_date : null
         * sj_sendtype :
         * sj_senddate : null
         * sj_sendname :
         * sj_sendnumberplate :
         * sj_sendphone :
         * sj_sendaddress :
         * sj_sendremarks :
         * sj_area :
         */

        var id: Int = 0
        var orderId: String? = null
        var customerid: String? = null
        var sorting: Int = 0
        var categories: String? = null
        var consumption_name: String? = null
        var amount: Int = 0
        var remarks: String? = null
        var transfer_certificate: Int = 0
        var take_away_date: String? = null
        var item_pagecount: Int = 0
        var item_sheetscount: Int = 0
        var take_away_time: String? = null
        var whethercantake: String? = null
        var whether_already_take: String? = null
        var size: String? = null
        var getuser: String? = null
        var price: Int = 0
        var cost: Int = 0
        var total: Int = 0
        var send_count: Int = 0
        var shop_code: String? = null
        var shop_name: String? = null
        var issources: Int = 0
        var create_time: String? = null
        var take_can_date: String? = null
        var urgent: String? = null
        var take_urgent_date: String? = null
        var sj_sendtype: String? = null
        var sj_senddate: String? = null
        var sj_sendname: String? = null
        var sj_sendnumberplate: String? = null
        var sj_sendphone: String? = null
        var sj_sendaddress: String? = null
        var sj_sendremarks: String? = null
        var sj_area: String? = null

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.id)
            dest.writeString(this.orderId)
            dest.writeString(this.customerid)
            dest.writeInt(this.sorting)
            dest.writeString(this.categories)
            dest.writeString(this.consumption_name)
            dest.writeInt(this.amount)
            dest.writeString(this.remarks)
            dest.writeInt(this.transfer_certificate)
            dest.writeString(this.take_away_date)
            dest.writeInt(this.item_pagecount)
            dest.writeInt(this.item_sheetscount)
            dest.writeString(this.take_away_time)
            dest.writeString(this.whethercantake)
            dest.writeString(this.whether_already_take)
            dest.writeString(this.size)
            dest.writeString(this.getuser)
            dest.writeInt(this.price)
            dest.writeInt(this.cost)
            dest.writeInt(this.total)
            dest.writeInt(this.send_count)
            dest.writeString(this.shop_code)
            dest.writeString(this.shop_name)
            dest.writeInt(this.issources)
            dest.writeString(this.create_time)
            dest.writeString(this.take_can_date)
            dest.writeString(this.urgent)
            dest.writeString(this.take_urgent_date)
            dest.writeString(this.sj_sendtype)
            dest.writeString(this.sj_senddate)
            dest.writeString(this.sj_sendname)
            dest.writeString(this.sj_sendnumberplate)
            dest.writeString(this.sj_sendphone)
            dest.writeString(this.sj_sendaddress)
            dest.writeString(this.sj_sendremarks)
            dest.writeString(this.sj_area)
        }

        constructor() {}
        protected constructor(`in`: Parcel) {
            this.id = `in`.readInt()
            this.orderId = `in`.readString()
            this.customerid = `in`.readString()
            this.sorting = `in`.readInt()
            this.categories = `in`.readString()
            this.consumption_name = `in`.readString()
            this.amount = `in`.readInt()
            this.remarks = `in`.readString()
            this.transfer_certificate = `in`.readInt()
            this.take_away_date = `in`.readString()
            this.item_pagecount = `in`.readInt()
            this.item_sheetscount = `in`.readInt()
            this.take_away_time = `in`.readString()
            this.whethercantake = `in`.readString()
            this.whether_already_take = `in`.readString()
            this.size = `in`.readString()
            this.getuser = `in`.readString()
            this.price = `in`.readInt()
            this.cost = `in`.readInt()
            this.total = `in`.readInt()
            this.send_count = `in`.readInt()
            this.shop_code = `in`.readString()
            this.shop_name = `in`.readString()
            this.issources = `in`.readInt()
            this.create_time = `in`.readString()
            this.take_can_date = `in`.readString()
            this.urgent = `in`.readString()
            this.take_urgent_date = `in`.readString()
            this.sj_sendtype = `in`.readString()
            this.sj_senddate = `in`.readString()
            this.sj_sendname = `in`.readString()
            this.sj_sendnumberplate = `in`.readString()
            this.sj_sendphone = `in`.readString()
            this.sj_sendaddress = `in`.readString()
            this.sj_sendremarks = `in`.readString()
            this.sj_area = `in`.readString()
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
