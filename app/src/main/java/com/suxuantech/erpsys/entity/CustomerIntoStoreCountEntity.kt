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
class CustomerIntoStoreCountEntity : BaseResult() {

    /**
     * Code : 200
     * Data : [{"allcount":"54","newcount":"38","wjdcount":"49","jdwccount":"4","ycjcount":"8","lscount":"3"}]
     */

    var data: List<DataBean>? = null

    class DataBean {
        /**
         * allcount : 54
         * newcount : 38
         * wjdcount : 49
         * jdwccount : 4
         * ycjcount : 8
         * lscount : 3
         */

        var allcount: String? = ""
        var newcount: String? = ""
        var wjdcount: String? = ""
        var jdwccount: String? = ""
        var ycjcount: String? = ""
        var lscount: String? = ""
    }
}
