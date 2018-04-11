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
 * @author Created by 李站旗 on 2017/12/8 0008 13:02 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  接单点
 */

public class OrderReceivingSiteEntity extends BaseResult {
    /**
     * Code : 666
     * Msg : 成功
     * Data : [{"id":"1","acceptoraddress_name":"沈阳分点","shop_name":"沈阳店","shop_code":"66666666","setkey":""},{"id":"2","acceptoraddress_name":"长春分点","shop_name":"长春店","shop_code":"5555555555","setkey":""},{"id":"3","acceptoraddress_name":"大兴区接单点","shop_name":"eric_测试店","shop_code":"7777","setkey":""},{"id":"4","acceptoraddress_name":"昌平区接单点","shop_name":"eric_测试店","shop_code":"7777","setkey":""}]
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
         * acceptoraddress_name : 沈阳分点
         * shop_name : 沈阳店
         * shop_code : 66666666
         * setkey :
         */

        private String id;
        private String acceptoraddress_name;
        private String shop_name;
        private String shop_code;
        private String setkey;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAcceptoraddress_name() {
            return acceptoraddress_name;
        }

        public void setAcceptoraddress_name(String acceptoraddress_name) {
            this.acceptoraddress_name = acceptoraddress_name;
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
