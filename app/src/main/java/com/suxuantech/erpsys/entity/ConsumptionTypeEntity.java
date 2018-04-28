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
 * @author Created by 李站旗 on 2017/12/7 0007 17:44 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  消费类型
 */

public class ConsumptionTypeEntity extends BaseResult {

    /**
     * Code : 666
     * Msg : 成功
     * Data : [{"id":"1","consumption_name":"结婚照","shop_name":"长春店","shop_code":"5555555555","setkey":""},{"id":"2","consumption_name":"全家福","shop_name":"沈阳店","shop_code":"66666666","setkey":""},{"id":"3","consumption_name":"儿童照","shop_name":"吉林店","shop_code":"34343","setkey":""},{"id":"4","consumption_name":"写真","shop_name":"沈阳店","shop_code":"66666666","setkey":""},{"id":"5","consumption_name":"艺术","shop_name":"沈阳店","shop_code":"66666666","setkey":""},{"id":"6","consumption_name":"哈哈","shop_name":"沈阳店","shop_code":"66666666","setkey":""},{"id":"7","consumption_name":"婚纱照-消费类型","shop_name":"eric_测试店","shop_code":"7777","setkey":""},{"id":"8","consumption_name":"儿童照-消费类型","shop_name":"eric_测试店","shop_code":"7777","setkey":""},{"id":"9","consumption_name":"旅拍-消费类型","shop_name":"eric_测试店","shop_code":"7777","setkey":""}]
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
         * consumption_name : 结婚照
         * shop_name : 长春店
         * shop_code : 5555555555
         * setkey :
         */

        private String id;
        private String consumption_name;
        private String shop_name;
        private String shop_code;
        private String setkey;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsumption_name() {
            return consumption_name;
        }

        public void setConsumption_name(String consumption_name) {
            this.consumption_name = consumption_name;
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
