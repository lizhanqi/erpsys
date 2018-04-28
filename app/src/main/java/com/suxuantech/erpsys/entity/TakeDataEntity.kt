package com.suxuantech.erpsys.entity

import com.google.gson.annotations.SerializedName

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
class TakeDataEntity : BaseResult2<TakeDataEntity.DataBean>() {

    /**
     * Code : 200
     * Data : [{"id":257,"orderId":"SY18012000015","customerid":"e4c88104-124d-41ae-ad69-495a16da0b37","sorting":1,"categories":"摄影","consumption_name":"18寸水晶相册","amount":1,"remarks":"","transfer_certificate":1,"take_away_date":null,"item_pagecount":6,"item_sheetscount":6,"take_away_time":null,"whethercantake":"完成","whether_already_take":"","size":"","getuser":null,"price":0,"cost":5,"total":0,"send_count":2,"shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","issources":0,"create_time":"2018-01-20 13:54:06.000","take_can_date":"2018-01-20 00:00:00.000","Urgent":"","take_urgent_date":null,"sj_sendtype":null,"sj_senddate":null,"sj_sendname":null,"sj_sendnumberplate":null,"sj_sendphone":null,"sj_sendaddress":null,"sj_sendremarks":null,"sj_area":""},{"id":264,"orderId":"SY18012000015","customerid":"e4c88104-124d-41ae-ad69-495a16da0b37","sorting":8,"categories":"摄影","consumption_name":"8*9水晶相册","amount":1,"remarks":"","transfer_certificate":1,"take_away_date":null,"item_pagecount":33,"item_sheetscount":10,"take_away_time":null,"whethercantake":"完成","whether_already_take":"","size":"","getuser":null,"price":0,"cost":12,"total":0,"send_count":1,"shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","issources":0,"create_time":"2018-01-20 13:54:06.000","take_can_date":"2018-01-20 00:00:00.000","Urgent":"","take_urgent_date":null,"sj_sendtype":null,"sj_senddate":null,"sj_sendname":null,"sj_sendnumberplate":null,"sj_sendphone":null,"sj_sendaddress":null,"sj_sendremarks":null,"sj_area":""}]
     */

    @SerializedName("Data")
    var dataX: List<DataBean>? = null

    class DataBean {
        /**
         * id : 257
         * orderId : SY18012000015
         * customerid : e4c88104-124d-41ae-ad69-495a16da0b37
         * sorting : 1
         * categories : 摄影
         * consumption_name : 18寸水晶相册
         * amount : 1
         * remarks :
         * transfer_certificate : 1
         * take_away_date : null
         * item_pagecount : 6
         * item_sheetscount : 6
         * take_away_time : null
         * whethercantake : 完成
         * whether_already_take :
         * size :
         * getuser : null
         * price : 0
         * cost : 5
         * total : 0
         * send_count : 2
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * issources : 0
         * create_time : 2018-01-20 13:54:06.000
         * take_can_date : 2018-01-20 00:00:00.000
         * Urgent :
         * take_urgent_date : null
         * sj_sendtype : null
         * sj_senddate : null
         * sj_sendname : null
         * sj_sendnumberplate : null
         * sj_sendphone : null
         * sj_sendaddress : null
         * sj_sendremarks : null
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
        var take_away_date: Any? = null
        var item_pagecount: Int = 0
        var item_sheetscount: Int = 0
        var take_away_time: Any? = null
        var whethercantake: String? = null
        var whether_already_take: String? = null
        var size: String? = null
        var getuser: Any? = null
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
        var take_urgent_date: Any? = null
        var sj_sendtype: Any? = null
        var sj_senddate: Any? = null
        var sj_sendname: Any? = null
        var sj_sendnumberplate: Any? = null
        var sj_sendphone: Any? = null
        var sj_sendaddress: Any? = null
        var sj_sendremarks: Any? = null
        var sj_area: String? = null
    }
}
