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
class TodayMoneyEntity : BaseResult() {

    /**
     * Code : 200
     * Data : [{"id":82,"orderId":"SY18022300026","payclass":"门市收款","paytype":"现金","paymentdate":"20180510","payment_money":200,"cashierman":"海芹","fundname":"定金","xingming":" 文宇","shouji":" 12121121212"},{"id":83,"orderId":"SY18050500102","payclass":"门市收款","paytype":"现金","paymentdate":"20180510","payment_money":3000,"cashierman":"海芹","fundname":"定金","xingming":" kilo","shouji":" 18801266363"},{"id":84,"orderId":"SY18050500102","payclass":"门市收款","paytype":"刷卡","paymentdate":"20180510","payment_money":500,"cashierman":"海芹","fundname":"拍照款","xingming":" kilo","shouji":" 18801266363"},{"id":85,"orderId":"SY18050500102","payclass":"礼服收款","paytype":"现金","paymentdate":"20180510","payment_money":360,"cashierman":"海芹","fundname":"定金","xingming":" kilo","shouji":" 18801266363"},{"id":86,"orderId":"SY18050800103","payclass":"化妆收款","paytype":"现金","paymentdate":"20180510","payment_money":800,"cashierman":"海芹","fundname":"定金","xingming":"夏娟 ","shouji":"15863522451 "},{"id":87,"orderId":"SY18050800103","payclass":"门市收款","paytype":"支付宝转账","paymentdate":"20180510","payment_money":100,"cashierman":"海芹","fundname":"定金","xingming":"夏娟 ","shouji":"15863522451 "}]
     */

    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 82
         * orderId : SY18022300026
         * payclass : 门市收款
         * paytype : 现金
         * paymentdate : 20180510
         * payment_money : 200
         * cashierman : 海芹
         * fundname : 定金
         * xingming :  文宇
         * shouji :  12121121212
         */

        var id: Int = 0
        var orderId: String? = null
        var payclass: String? = null
        var paytype: String? = null
        var paymentdate: String? = null
        var payment_money: Int = 0
        var cashierman: String? = null
        var fundname: String? = null
        var xingming: String? = null
        var shouji: String? = null
    }
}
