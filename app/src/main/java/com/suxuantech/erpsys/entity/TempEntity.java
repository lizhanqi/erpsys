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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity  extends  BaseResult{

    /**
     * Code : 200
     * Data : [{"id":23,"makeupid":25,"orderId":"SY18040200061","customerid":"6bfba44b-6583-436b-b357-fdafb6e20810","makeupItems":"唇彩","amount":1,"makeup_price":35,"makeup_total":35,"sellman":"","makeuptype":"早妆","makeupman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","create_time":"2018-04-16 00:00:00.000"}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
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

        private int id;
        private int makeupid;
        private String orderId;
        private String customerid;
        private String makeupItems;
        private int amount;
        private int makeup_price;
        private int makeup_total;
        private String sellman;
        private String makeuptype;
        private String makeupman;
        private String shop_code;
        private String shop_name;
        private String create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMakeupid() {
            return makeupid;
        }

        public void setMakeupid(int makeupid) {
            this.makeupid = makeupid;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getMakeupItems() {
            return makeupItems;
        }

        public void setMakeupItems(String makeupItems) {
            this.makeupItems = makeupItems;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getMakeup_price() {
            return makeup_price;
        }

        public void setMakeup_price(int makeup_price) {
            this.makeup_price = makeup_price;
        }

        public int getMakeup_total() {
            return makeup_total;
        }

        public void setMakeup_total(int makeup_total) {
            this.makeup_total = makeup_total;
        }

        public String getSellman() {
            return sellman;
        }

        public void setSellman(String sellman) {
            this.sellman = sellman;
        }

        public String getMakeuptype() {
            return makeuptype;
        }

        public void setMakeuptype(String makeuptype) {
            this.makeuptype = makeuptype;
        }

        public String getMakeupman() {
            return makeupman;
        }

        public void setMakeupman(String makeupman) {
            this.makeupman = makeupman;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
