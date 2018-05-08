package com.suxuantech.erpsys.entity

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
class MakeUpDetailsEntity : BaseResult() {

    /**
     * Code : 200
     * Data : [{"id":23,"makeupid":25,"orderId":"SY18040200061","customerid":"6bfba44b-6583-436b-b357-fdafb6e20810","makeupItems":"唇彩","amount":1,"makeup_price":35,"makeup_total":35,"sellman":"","makeuptype":"早妆","makeupman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","create_time":"2018-04-16 00:00:00.000"}]
     */

    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 23
         * makeupid : 25
         * orderId : SY18040200061
         * customerid : 6bfba44b-6583-436b-b357-fdafb6e20810
         * makeupItems : 唇彩
         * amount : 1
         * makeup_price : 35
         * makeup_total : 35
         * sellman :
         * makeuptype : 早妆
         * makeupman :
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * create_time : 2018-04-16 00:00:00.000
         */

        var id: Int = 0
        var makeupid: Int = 0
        var orderId: String? = null
        var customerid: String? = null
        var makeupItems: String? = null
        var amount: Int = 0
        var makeup_price: Int = 0
        var makeup_total: Int = 0
        var sellman: String? = null
        var makeuptype: String? = null
        var makeupman: String? = null
        var shop_code: String? = null
        var shop_name: String? = null
        var create_time: String? = null
    }
}
