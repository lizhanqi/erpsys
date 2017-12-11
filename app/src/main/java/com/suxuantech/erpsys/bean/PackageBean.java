package com.suxuantech.erpsys.bean;

import java.util.List;

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
 * @author Created by 李站旗 on 2017/12/8 0008 17:35 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 获取包套
 */

public class PackageBean extends Result {

    /**
     * Msg : 成功
     * Data : [{"package_name":"6999普通","package_price":6999,"id":5},{"package_name":"8999普通","package_price":6999,"id":7},{"package_name":"8999套系","package_price":8999,"id":9},{"package_name":"3999套系","package_price":8999,"id":10}]
     */

    private String Msg;
    private List<DataBean> Data;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * package_name : 6999普通
         * package_price : 6999
         * id : 5
         */

        private String package_name;
        private int package_price;
        private int id;

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public int getPackage_price() {
            return package_price;
        }

        public void setPackage_price(int package_price) {
            this.package_price = package_price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
