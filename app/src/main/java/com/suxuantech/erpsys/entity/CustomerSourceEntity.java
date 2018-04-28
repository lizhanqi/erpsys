package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2018/4/16 0016 16:45 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class CustomerSourceEntity extends  BaseResult {

    /**
     * Code : 200
     * Msg : success
     * Data : [{"id":"1","cus_name":"自然入客","shop_name":"","shop_code":"","setkey":""},{"id":"2","cus_name":"自然老顾客转介绍","shop_name":"","shop_code":"","setkey":""},{"id":"3","cus_name":"网络","shop_name":"","shop_code":"","setkey":""},{"id":"4","cus_name":"网络老顾客转介绍","shop_name":"","shop_code":"","setkey":""},{"id":"5","cus_name":"门市指名转介绍","shop_name":"","shop_code":"","setkey":""},{"id":"6","cus_name":"非营业部转介绍","shop_name":"","shop_code":"","setkey":""},{"id":"7","cus_name":"婚博会","shop_name":"","shop_code":"","setkey":""},{"id":"8","cus_name":"外展及其他","shop_name":"","shop_code":"","setkey":""}]
     */

    private String Msg;
    private List<DataBean> Data;
    @Override
    public String getMsg() {
        return Msg;
    }

    @Override
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
         * cus_name : 自然入客
         * shop_name :
         * shop_code :
         * setkey :
         */

        private String id;
        private String cus_name;
        private String shop_name;
        private String shop_code;
        private String setkey;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCus_name() {
            return cus_name;
        }

        public void setCus_name(String cus_name) {
            this.cus_name = cus_name;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }
    }
}
