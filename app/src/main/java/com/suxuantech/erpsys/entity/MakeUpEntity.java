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
public class MakeUpEntity  extends  BaseResult{

    /**
     * Code : 200
     * Data : [{"id":8,"orderId":"SY18011900004","customerid":"933f197c-bf86-4970-97cd-bf536fc83dda","makeupstate":"","makeupdate":"20180103","makeuptime":"1111","start_makeup_Time":"20180119","over_makeup_Time":null,"voucher_name":"化妆单据3","makeup_money":0,"havepaymen_moneyt":0,"nonpaymen_moneyt":0,"completion_date":null,"makeup_remarks":"lkpil","sellman":"姜一雁","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","create_time":"2018-01-19 00:00:00.000"}]
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
         * id : 8
         * orderId : SY18011900004
         * customerid : 933f197c-bf86-4970-97cd-bf536fc83dda
         * makeupstate :
         * makeupdate : 20180103
         * makeuptime : 1111
         * start_makeup_Time : 20180119
         * over_makeup_Time : null
         * voucher_name : 化妆单据3
         * makeup_money : 0
         * havepaymen_moneyt : 0
         * nonpaymen_moneyt : 0
         * completion_date : null
         * makeup_remarks : lkpil
         * sellman : 姜一雁
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * create_time : 2018-01-19 00:00:00.000
         */

        private int id;
        private String orderId;
        private String customerid;
        private String makeupstate;
        private String makeupdate;
        private String makeuptime;
        private String start_makeup_Time;
        private String over_makeup_Time;
        private String voucher_name;
        private int makeup_money;
        private int havepaymen_moneyt;
        private int nonpaymen_moneyt;
        private String completion_date;
        private String makeup_remarks;
        private String sellman;
        private String shop_code;
        private String shop_name;
        private String create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMakeupstate() {
            return makeupstate;
        }

        public void setMakeupstate(String makeupstate) {
            this.makeupstate = makeupstate;
        }

        public String getMakeupdate() {
            return makeupdate;
        }

        public void setMakeupdate(String makeupdate) {
            this.makeupdate = makeupdate;
        }

        public String getMakeuptime() {
            return makeuptime;
        }

        public void setMakeuptime(String makeuptime) {
            this.makeuptime = makeuptime;
        }

        public String getStart_makeup_Time() {
            return start_makeup_Time;
        }

        public void setStart_makeup_Time(String start_makeup_Time) {
            this.start_makeup_Time = start_makeup_Time;
        }

        public String getOver_makeup_Time() {
            return over_makeup_Time;
        }

        public void setOver_makeup_Time(String over_makeup_Time) {
            this.over_makeup_Time = over_makeup_Time;
        }

        public String getVoucher_name() {
            return voucher_name;
        }

        public void setVoucher_name(String voucher_name) {
            this.voucher_name = voucher_name;
        }

        public int getMakeup_money() {
            return makeup_money;
        }

        public void setMakeup_money(int makeup_money) {
            this.makeup_money = makeup_money;
        }

        public int getHavepaymen_moneyt() {
            return havepaymen_moneyt;
        }

        public void setHavepaymen_moneyt(int havepaymen_moneyt) {
            this.havepaymen_moneyt = havepaymen_moneyt;
        }

        public int getNonpaymen_moneyt() {
            return nonpaymen_moneyt;
        }

        public void setNonpaymen_moneyt(int nonpaymen_moneyt) {
            this.nonpaymen_moneyt = nonpaymen_moneyt;
        }

        public String getCompletion_date() {
            return completion_date;
        }

        public void setCompletion_date(String completion_date) {
            this.completion_date = completion_date;
        }

        public String getMakeup_remarks() {
            return makeup_remarks;
        }

        public void setMakeup_remarks(String makeup_remarks) {
            this.makeup_remarks = makeup_remarks;
        }

        public String getSellman() {
            return sellman;
        }

        public void setSellman(String sellman) {
            this.sellman = sellman;
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
