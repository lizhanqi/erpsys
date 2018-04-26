package com.suxuantech.erpsys.entity;

import android.os.Parcel;
import android.os.Parcelable;

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
 * @author Created by 李站旗 on 2018/4/24 0024 17:17 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class SearchOrderInforEntity extends BaseResult2<SearchOrderInforEntity.DataBean> {

    /**
     * Code : 200
     * Msg : success
     * Data : [{"brandid":1,"customer_from_index":"","mname":"","mphone":"","wname":"幺叔","wphone":"18801299876","viptype":"","vipnum":"","area":"VIP,-三楼","orderId":"SY18041900067","targetdate":"20180419","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","consumption_type":"情侣照","mwechat":"","take_takaddress":null,"getDate":null,"wwechat":"","total_money":0,"payment_money":0,"nopayment_money":0,"bargain_money":0,"supplementary_money":0,"allfinishfate":null},{"brandid":1,"customer_from_index":"","mname":"","mphone":"","wname":"幺叔","wphone":"18801299876","viptype":"贵宾卡","vipnum":"","area":"VIP,-三楼","orderId":"SY18041900068","targetdate":"20180419","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","consumption_type":"情侣照","mwechat":"","take_takaddress":null,"getDate":null,"wwechat":"","total_money":0,"payment_money":0,"nopayment_money":0,"bargain_money":0,"supplementary_money":0,"allfinishfate":null},{"brandid":1,"customer_from_index":"","mname":"","mphone":"","wname":"Lina ","wphone":"18801234567","viptype":"","vipnum":"","area":"V2-五楼","orderId":"SY18042100070","targetdate":"20180421","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","consumption_type":"艺术照","mwechat":"","take_takaddress":null,"getDate":null,"wwechat":"","total_money":0,"payment_money":0,"nopayment_money":0,"bargain_money":0,"supplementary_money":0,"allfinishfate":null}]
     */


    public static class DataBean implements Parcelable {
        /**
         * brandid : 1
         * customer_from_index :
         * mname :
         * mphone :
         * wname : 幺叔
         * wphone : 18801299876
         * viptype :
         * vipnum :
         * area : VIP,-三楼
         * orderId : SY18041900067
         * targetdate : 20180419
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * consumption_type : 情侣照
         * mwechat :
         * take_takaddress : null
         * getDate : null
         * wwechat :
         * total_money : 0
         * payment_money : 0
         * nopayment_money : 0
         * bargain_money : 0
         * supplementary_money : 0
         * allfinishfate : null
         */

        private int brandid;
        private String customer_from_index;
        private String mname;
        private String mphone;
        private String wname;
        private String wphone;
        private String viptype;
        private String vipnum;
        private String area;
        private String orderId;
        private String targetdate;
        private String shop_name;
        private String shop_code;
        private String consumption_type;
        private String mwechat;
        private String take_takaddress;
        private String getDate;
        private String wwechat;
        private int total_money;
        private int payment_money;
        private int nopayment_money;
        private int bargain_money;
        private int supplementary_money;
        private String allfinishfate;

        public int getBrandid() {
            return brandid;
        }

        public void setBrandid(int brandid) {
            this.brandid = brandid;
        }

        public String getCustomer_from_index() {
            return customer_from_index;
        }

        public void setCustomer_from_index(String customer_from_index) {
            this.customer_from_index = customer_from_index;
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

        public String getViptype() {
            return viptype;
        }

        public void setViptype(String viptype) {
            this.viptype = viptype;
        }

        public String getVipnum() {
            return vipnum;
        }

        public void setVipnum(String vipnum) {
            this.vipnum = vipnum;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getTargetdate() {
            return targetdate;
        }

        public void setTargetdate(String targetdate) {
            this.targetdate = targetdate;
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

        public String getConsumption_type() {
            return consumption_type;
        }

        public void setConsumption_type(String consumption_type) {
            this.consumption_type = consumption_type;
        }

        public String getMwechat() {
            return mwechat;
        }

        public void setMwechat(String mwechat) {
            this.mwechat = mwechat;
        }

        public String getTake_takaddress() {
            return take_takaddress;
        }

        public void setTake_takaddress(String take_takaddress) {
            this.take_takaddress = take_takaddress;
        }

        public String getGetDate() {
            return getDate;
        }

        public void setGetDate(String getDate) {
            this.getDate = getDate;
        }

        public String getWwechat() {
            return wwechat;
        }

        public void setWwechat(String wwechat) {
            this.wwechat = wwechat;
        }

        public int getTotal_money() {
            return total_money;
        }

        public void setTotal_money(int total_money) {
            this.total_money = total_money;
        }

        public int getPayment_money() {
            return payment_money;
        }

        public void setPayment_money(int payment_money) {
            this.payment_money = payment_money;
        }

        public int getNopayment_money() {
            return nopayment_money;
        }

        public void setNopayment_money(int nopayment_money) {
            this.nopayment_money = nopayment_money;
        }

        public int getBargain_money() {
            return bargain_money;
        }

        public void setBargain_money(int bargain_money) {
            this.bargain_money = bargain_money;
        }

        public int getSupplementary_money() {
            return supplementary_money;
        }

        public void setSupplementary_money(int supplementary_money) {
            this.supplementary_money = supplementary_money;
        }

        public String getAllfinishfate() {
            return allfinishfate;
        }

        public void setAllfinishfate(String allfinishfate) {
            this.allfinishfate = allfinishfate;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.brandid);
            dest.writeString(this.customer_from_index);
            dest.writeString(this.mname);
            dest.writeString(this.mphone);
            dest.writeString(this.wname);
            dest.writeString(this.wphone);
            dest.writeString(this.viptype);
            dest.writeString(this.vipnum);
            dest.writeString(this.area);
            dest.writeString(this.orderId);
            dest.writeString(this.targetdate);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.consumption_type);
            dest.writeString(this.mwechat);
            dest.writeString(this.take_takaddress);
            dest.writeString(this.getDate);
            dest.writeString(this.wwechat);
            dest.writeInt(this.total_money);
            dest.writeInt(this.payment_money);
            dest.writeInt(this.nopayment_money);
            dest.writeInt(this.bargain_money);
            dest.writeInt(this.supplementary_money);
            dest.writeString(this.allfinishfate);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.brandid = in.readInt();
            this.customer_from_index = in.readString();
            this.mname = in.readString();
            this.mphone = in.readString();
            this.wname = in.readString();
            this.wphone = in.readString();
            this.viptype = in.readString();
            this.vipnum = in.readString();
            this.area = in.readString();
            this.orderId = in.readString();
            this.targetdate = in.readString();
            this.shop_name = in.readString();
            this.shop_code = in.readString();
            this.consumption_type = in.readString();
            this.mwechat = in.readString();
            this.take_takaddress = in.readString();
            this.getDate = in.readString();
            this.wwechat = in.readString();
            this.total_money = in.readInt();
            this.payment_money = in.readInt();
            this.nopayment_money = in.readInt();
            this.bargain_money = in.readInt();
            this.supplementary_money = in.readInt();
            this.allfinishfate = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
