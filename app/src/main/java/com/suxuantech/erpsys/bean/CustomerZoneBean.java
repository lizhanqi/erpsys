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
 * @author Created by 李站旗 on 2017/12/8 0008 14:22 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 客户分区
 */

public class CustomerZoneBean  extends Result{

    /**
     * Msg : 成功
     * Data : [{"id":"1","area_name":"东北","shop_name":"长春店","setkey":"","shop_code":"5555555555"},{"id":"2","area_name":"华北","shop_name":"沈阳店","setkey":"","shop_code":"66666666"},{"id":"3","area_name":"西南","shop_name":"沈阳店","setkey":"","shop_code":"66666666"},{"id":"4","area_name":"东北-eric-分区","shop_name":"eric_测试店","setkey":"","shop_code":"7777"},{"id":"6","area_name":"华南-eric-分区","shop_name":"eric_测试店","setkey":"","shop_code":"7777"},{"id":"7","area_name":"华北-eric-分区","shop_name":"eric_测试店","setkey":"","shop_code":"7777"},{"id":"8","area_name":"尊贵区-eric-分区","shop_name":"eric_测试店","setkey":"","shop_code":"7777"}]
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
         * id : 1
         * area_name : 东北
         * shop_name : 长春店
         * setkey :
         * shop_code : 5555555555
         */

        private String id;
        private String area_name;
        private String shop_name;
        private String setkey;
        private String shop_code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }
    }
}
