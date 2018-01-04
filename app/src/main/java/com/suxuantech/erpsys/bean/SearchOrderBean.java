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
 * @author Created by 李站旗 on 2017/12/11 0011 19:59 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 订单搜索结果对象
 */

public class SearchOrderBean extends  Result {
    /**
     * Msg : 成功
     * Data : [{"customerid":"","wname":"li","wphone":"15311440759","mname":"","mphone":"","area":"VIP区","weddingdate":"20171211","customernote":"","shop_code":"0","shop_name":"0","orderId":"41981277-10b8-49cc-b1b8-6e81e9077fad","consumption_type":"eric_测试店","targetdate":"20171211","package_name":null,"total_money":0,"payment_money":0,"nopayment_money":0}]
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
         * customerid :
         * wname : li
         * wphone : 15311440759
         * mname :
         * mphone :
         * area : VIP区
         * weddingdate : 20171211
         * customernote :
         * shop_code : 0
         * shop_name : 0
         * orderId : 41981277-10b8-49cc-b1b8-6e81e9077fad
         * consumption_type : eric_测试店
         * targetdate : 20171211
         * package_name : null
         * total_money : 0
         * payment_money : 0
         * nopayment_money : 0
         */

        private String customerid;
        private String wname;
        private String wphone;
        private String mname;
        private String mphone;
        private String area;
        private String weddingdate;
        private String customernote;
        private String shop_code;
        private String shop_name;
        private String orderId;
        private String consumption_type;
        private String targetdate;
        private String package_name;
        private String total_money;
        private String payment_money;
        private String nopayment_money;

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getWname() {
            return wname;
        }

        public void setWname(String wname) {
            this.wname = wname;
        }

        public String getWphone() {
            return wphone;
        }

        public void setWphone(String wphone) {
            this.wphone = wphone;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getMphone() {
            return mphone;
        }

        public void setMphone(String mphone) {
            this.mphone = mphone;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getWeddingdate() {
            return weddingdate;
        }

        public void setWeddingdate(String weddingdate) {
            this.weddingdate = weddingdate;
        }

        public String getCustomernote() {
            return customernote;
        }

        public void setCustomernote(String customernote) {
            this.customernote = customernote;
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

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getConsumption_type() {
            return consumption_type;
        }

        public void setConsumption_type(String consumption_type) {
            this.consumption_type = consumption_type;
        }

        public String getTargetdate() {
            return targetdate;
        }

        public void setTargetdate(String targetdate) {
            this.targetdate = targetdate;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getPayment_money() {
            return payment_money;
        }

        public void setPayment_money(String payment_money) {
            this.payment_money = payment_money;
        }

        public String getNopayment_money() {
            return nopayment_money;
        }

        public void setNopayment_money(String nopayment_money) {
            this.nopayment_money = nopayment_money;
        }
    }
}
