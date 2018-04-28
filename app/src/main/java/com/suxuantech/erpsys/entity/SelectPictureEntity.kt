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
class SelectPictureEntity : BaseResult() {

    /**
     * Code : 200
     * Data : [{"id":17,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","selectday":"20180205","start_select_time":"20180204","over_select_time":null,"selectman":"姜哲","sptype":"选片","sptstate":"未选","sproom":"VIP1","isspurgent":"","spremarks":"werwe","spcount":0,"spbook_photocount":0,"spImport_photocount":0,"spnoticestate":"","spnoticedate":null,"spnoticetime":null,"spnoticeman":null,"spnoticenote":null,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","create_time":"2018-02-04 00:00:00.000","selectTime":"","select_record":"","select_recordtime":null,"select_order_name":"三亚旅拍1","designerman":"","americaneditorman":"","artistman":"","sptend_day":"","yhjxman":"","kanbanriqi":null,"kanbanshijian":null,"kanbanshi":null,"kanbanstate":"","kanbanendday":""},{"id":18,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","selectday":"20180205","start_select_time":"20180204","over_select_time":null,"selectman":"姜哲","sptype":"选片","sptstate":"未选完","sproom":"VIP1","isspurgent":"","spremarks":"werwe","spcount":0,"spbook_photocount":0,"spImport_photocount":0,"spnoticestate":"","spnoticedate":null,"spnoticetime":null,"spnoticeman":null,"spnoticenote":null,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","create_time":"2018-02-04 00:00:00.000","selectTime":"","select_record":"","select_recordtime":null,"select_order_name":"三亚旅拍","designerman":"杜玲","americaneditorman":"李寻欢","artistman":"金启玲","sptend_day":"","yhjxman":"","kanbanriqi":null,"kanbanshijian":null,"kanbanshi":null,"kanbanstate":"","kanbanendday":""},{"id":21,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","selectday":"20180228","start_select_time":"20180208","over_select_time":null,"selectman":"","sptype":"看版","sptstate":"未选完","sproom":"","isspurgent":"","spremarks":"werweyfghfghr2w345234525423","spcount":0,"spbook_photocount":0,"spImport_photocount":0,"spnoticestate":"","spnoticedate":null,"spnoticetime":null,"spnoticeman":null,"spnoticenote":null,"shop_name":"沈阳时尚经典网销部","shop_code":"SYWX","create_time":"2018-02-08 00:00:00.000","selectTime":"","select_record":"","select_recordtime":null,"select_order_name":"三亚旅拍6","designerman":"","americaneditorman":"","artistman":"","sptend_day":"","yhjxman":"","kanbanriqi":null,"kanbanshijian":null,"kanbanshi":null,"kanbanstate":"","kanbanendday":""}]
     */

    var data: List<DataBean>? = null

    class DataBean : Parcelable {
        /**
         * id : 17
         * orderId : SY18012000007
         * customerid : c3ddb62a-dcec-4b65-8d77-f335fbbd00f8
         * selectday : 20180205
         * start_select_time : 20180204
         * over_select_time : null
         * selectman : 姜哲
         * sptype : 选片
         * sptstate : 未选
         * sproom : VIP1
         * isspurgent :
         * spremarks : werwe
         * spcount : 0
         * spbook_photocount : 0
         * spImport_photocount : 0
         * spnoticestate :
         * spnoticedate : null
         * spnoticetime : null
         * spnoticeman : null
         * spnoticenote : null
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * create_time : 2018-02-04 00:00:00.000
         * selectTime :
         * select_record :
         * select_recordtime : null
         * select_order_name : 三亚旅拍1
         * designerman :
         * americaneditorman :
         * artistman :
         * sptend_day :
         * yhjxman :
         * kanbanriqi : null
         * kanbanshijian : null
         * kanbanshi : null
         * kanbanstate :
         * kanbanendday :
         */

        var id: Int = 0
        var orderId: String? = null
        var customerid: String? = null
        var selectday: String? = null
        var start_select_time: String? = null
        var over_select_time: String? = null
        var selectman: String? = null
        var sptype: String? = null
        var sptstate: String? = null
        var sproom: String? = null
        var isspurgent: String? = null
        var spremarks: String? = null
        var spcount: Int = 0
        var spbook_photocount: Int = 0
        var spImport_photocount: Int = 0
        var spnoticestate: String? = null
        var spnoticedate: String? = null
        var spnoticetime: String? = null
        var spnoticeman: String? = null
        var spnoticenote: String? = null
        var shop_name: String? = null
        var shop_code: String? = null
        var create_time: String? = null
        var selectTime: String? = null
        var select_record: String? = null
        var select_recordtime: String? = null
        var select_order_name: String? = null
        var designerman: String? = null
        var americaneditorman: String? = null
        var artistman: String? = null
        var sptend_day: String? = null
        var yhjxman: String? = null
        var kanbanriqi: String? = null
        var kanbanshijian: String? = null
        var kanbanshi: String? = null
        var kanbanstate: String? = null
        var kanbanendday: String? = null

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.id)
            dest.writeString(this.orderId)
            dest.writeString(this.customerid)
            dest.writeString(this.selectday)
            dest.writeString(this.start_select_time)
            dest.writeString(this.over_select_time)
            dest.writeString(this.selectman)
            dest.writeString(this.sptype)
            dest.writeString(this.sptstate)
            dest.writeString(this.sproom)
            dest.writeString(this.isspurgent)
            dest.writeString(this.spremarks)
            dest.writeInt(this.spcount)
            dest.writeInt(this.spbook_photocount)
            dest.writeInt(this.spImport_photocount)
            dest.writeString(this.spnoticestate)
            dest.writeString(this.spnoticedate)
            dest.writeString(this.spnoticetime)
            dest.writeString(this.spnoticeman)
            dest.writeString(this.spnoticenote)
            dest.writeString(this.shop_name)
            dest.writeString(this.shop_code)
            dest.writeString(this.create_time)
            dest.writeString(this.selectTime)
            dest.writeString(this.select_record)
            dest.writeString(this.select_recordtime)
            dest.writeString(this.select_order_name)
            dest.writeString(this.designerman)
            dest.writeString(this.americaneditorman)
            dest.writeString(this.artistman)
            dest.writeString(this.sptend_day)
            dest.writeString(this.yhjxman)
            dest.writeString(this.kanbanriqi)
            dest.writeString(this.kanbanshijian)
            dest.writeString(this.kanbanshi)
            dest.writeString(this.kanbanstate)
            dest.writeString(this.kanbanendday)
        }

        constructor() {}

        protected constructor(`in`: Parcel) {
            this.id = `in`.readInt()
            this.orderId = `in`.readString()
            this.customerid = `in`.readString()
            this.selectday = `in`.readString()
            this.start_select_time = `in`.readString()
            this.over_select_time = `in`.readString()
            this.selectman = `in`.readString()
            this.sptype = `in`.readString()
            this.sptstate = `in`.readString()
            this.sproom = `in`.readString()
            this.isspurgent = `in`.readString()
            this.spremarks = `in`.readString()
            this.spcount = `in`.readInt()
            this.spbook_photocount = `in`.readInt()
            this.spImport_photocount = `in`.readInt()
            this.spnoticestate = `in`.readString()
            this.spnoticedate = `in`.readString()
            this.spnoticetime = `in`.readString()
            this.spnoticeman = `in`.readString()
            this.spnoticenote = `in`.readString()
            this.shop_name = `in`.readString()
            this.shop_code = `in`.readString()
            this.create_time = `in`.readString()
            this.selectTime = `in`.readString()
            this.select_record = `in`.readString()
            this.select_recordtime = `in`.readString()
            this.select_order_name = `in`.readString()
            this.designerman = `in`.readString()
            this.americaneditorman = `in`.readString()
            this.artistman = `in`.readString()
            this.sptend_day = `in`.readString()
            this.yhjxman = `in`.readString()
            this.kanbanriqi = `in`.readString()
            this.kanbanshijian = `in`.readString()
            this.kanbanshi = `in`.readString()
            this.kanbanstate = `in`.readString()
            this.kanbanendday = `in`.readString()
        }

        companion object {

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
