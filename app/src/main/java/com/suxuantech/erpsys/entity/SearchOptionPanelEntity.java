package com.suxuantech.erpsys.entity;

import android.os.Parcel;
import android.os.Parcelable;

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
 * @author Created by 李站旗 on 2018/5/24 0024 17:06 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class SearchOptionPanelEntity extends BaseResult  {


    /**
     * Code : 200
     * Data : [{"customerid":"df78a459-21a5-4435-89f9-681b46996321","xingming":" 杨佳江","wphone":"13156987456","mphone":"","cssname":"","area":"","weddingdate":"","orderid":"SY18011800001","targetdate":"20180118","consumption_type":"三亚旅拍","acceptor_address":null,"total_money":8194,"payment_money":2100,"nopayment_money":6094,"ordernote":"","phototype":"补拍","photodate":"6456-03-08 00:00:00.000","photostate":"已拍完","photonote":"","cameraman":"郭靖","selectday":"20180125","selectTime":"","start_select_time":"20180119","over_select_time":null,"selectman":"","sptype":"选片","sproom":"","isspurgent":"否","spremarks":"","id":3,"spcount":0,"spbook_photocount":0,"spImport_photocount":0,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","select_order_name":"三亚旅拍","sptstate":"未选"}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {

        /**
         * customerid : df78a459-21a5-4435-89f9-681b46996321
         * xingming :  杨佳江
         * wphone : 13156987456
         * mphone :
         * cssname :
         * area :
         * weddingdate :
         * orderid : SY18011800001
         * targetdate : 20180118
         * consumption_type : 三亚旅拍
         * acceptor_address : null
         * total_money : 8194
         * payment_money : 2100
         * nopayment_money : 6094
         * ordernote :
         * phototype : 补拍
         * photodate : 6456-03-08 00:00:00.000
         * photostate : 已拍完
         * photonote :
         * cameraman : 郭靖
         * selectday : 20180125
         * selectTime :
         * start_select_time : 20180119
         * over_select_time : null
         * selectman :
         * sptype : 选片
         * sproom :
         * isspurgent : 否
         * spremarks :
         * id : 3
         * spcount : 0
         * spbook_photocount : 0
         * spImport_photocount : 0
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * select_order_name : 三亚旅拍
         * sptstate : 未选
         */

        private String customerid;
        private String xingming;
        private String wphone;
        private String mphone;
        private String cssname;
        private String area;
        private String weddingdate;
        private String orderid;
        private String targetdate;
        private String consumption_type;
        private String acceptor_address;
        private int total_money;
        private int payment_money;
        private int nopayment_money;
        private String ordernote;
        private String phototype;
        private String photodate;
        private String photostate;
        private String photonote;
        private String cameraman;
        private String selectday;
        private String selectTime;
        private String start_select_time;
        private String over_select_time;
        private String selectman;
        private String sptype;
        private String sproom;
        private String isspurgent;
        private String spremarks;
        private int id;
        private int spcount;
        private int spbook_photocount;
        private int spImport_photocount;
        private String shop_name;
        private String shop_code;
        private String select_order_name;
        private String sptstate;

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getWphone() {
            return wphone;
        }

        public void setWphone(String wphone) {
            this.wphone = wphone;
        }

        public String getMphone() {
            return mphone;
        }

        public void setMphone(String mphone) {
            this.mphone = mphone;
        }

        public String getCssname() {
            return cssname;
        }

        public void setCssname(String cssname) {
            this.cssname = cssname;
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

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTargetdate() {
            return targetdate;
        }

        public void setTargetdate(String targetdate) {
            this.targetdate = targetdate;
        }

        public String getConsumption_type() {
            return consumption_type;
        }

        public void setConsumption_type(String consumption_type) {
            this.consumption_type = consumption_type;
        }

        public String getAcceptor_address() {
            return acceptor_address;
        }

        public void setAcceptor_address(String acceptor_address) {
            this.acceptor_address = acceptor_address;
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

        public String getOrdernote() {
            return ordernote;
        }

        public void setOrdernote(String ordernote) {
            this.ordernote = ordernote;
        }

        public String getPhototype() {
            return phototype;
        }

        public void setPhototype(String phototype) {
            this.phototype = phototype;
        }

        public String getPhotodate() {
            return photodate;
        }

        public void setPhotodate(String photodate) {
            this.photodate = photodate;
        }

        public String getPhotostate() {
            return photostate;
        }

        public void setPhotostate(String photostate) {
            this.photostate = photostate;
        }

        public String getPhotonote() {
            return photonote;
        }

        public void setPhotonote(String photonote) {
            this.photonote = photonote;
        }

        public String getCameraman() {
            return cameraman;
        }

        public void setCameraman(String cameraman) {
            this.cameraman = cameraman;
        }

        public String getSelectday() {
            return selectday;
        }

        public void setSelectday(String selectday) {
            this.selectday = selectday;
        }

        public String getSelectTime() {
            return selectTime;
        }

        public void setSelectTime(String selectTime) {
            this.selectTime = selectTime;
        }

        public String getStart_select_time() {
            return start_select_time;
        }

        public void setStart_select_time(String start_select_time) {
            this.start_select_time = start_select_time;
        }

        public String getOver_select_time() {
            return over_select_time;
        }

        public void setOver_select_time(String over_select_time) {
            this.over_select_time = over_select_time;
        }

        public String getSelectman() {
            return selectman;
        }

        public void setSelectman(String selectman) {
            this.selectman = selectman;
        }

        public String getSptype() {
            return sptype;
        }

        public void setSptype(String sptype) {
            this.sptype = sptype;
        }

        public String getSproom() {
            return sproom;
        }

        public void setSproom(String sproom) {
            this.sproom = sproom;
        }

        public String getIsspurgent() {
            return isspurgent;
        }

        public void setIsspurgent(String isspurgent) {
            this.isspurgent = isspurgent;
        }

        public String getSpremarks() {
            return spremarks;
        }

        public void setSpremarks(String spremarks) {
            this.spremarks = spremarks;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSpcount() {
            return spcount;
        }

        public void setSpcount(int spcount) {
            this.spcount = spcount;
        }

        public int getSpbook_photocount() {
            return spbook_photocount;
        }

        public void setSpbook_photocount(int spbook_photocount) {
            this.spbook_photocount = spbook_photocount;
        }

        public int getSpImport_photocount() {
            return spImport_photocount;
        }

        public void setSpImport_photocount(int spImport_photocount) {
            this.spImport_photocount = spImport_photocount;
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

        public String getSelect_order_name() {
            return select_order_name;
        }

        public void setSelect_order_name(String select_order_name) {
            this.select_order_name = select_order_name;
        }

        public String getSptstate() {
            return sptstate;
        }

        public void setSptstate(String sptstate) {
            this.sptstate = sptstate;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.customerid);
            dest.writeString(this.xingming);
            dest.writeString(this.wphone);
            dest.writeString(this.mphone);
            dest.writeString(this.cssname);
            dest.writeString(this.area);
            dest.writeString(this.weddingdate);
            dest.writeString(this.orderid);
            dest.writeString(this.targetdate);
            dest.writeString(this.consumption_type);
            dest.writeString(this.acceptor_address);
            dest.writeInt(this.total_money);
            dest.writeInt(this.payment_money);
            dest.writeInt(this.nopayment_money);
            dest.writeString(this.ordernote);
            dest.writeString(this.phototype);
            dest.writeString(this.photodate);
            dest.writeString(this.photostate);
            dest.writeString(this.photonote);
            dest.writeString(this.cameraman);
            dest.writeString(this.selectday);
            dest.writeString(this.selectTime);
            dest.writeString(this.start_select_time);
            dest.writeString(this.over_select_time);
            dest.writeString(this.selectman);
            dest.writeString(this.sptype);
            dest.writeString(this.sproom);
            dest.writeString(this.isspurgent);
            dest.writeString(this.spremarks);
            dest.writeInt(this.id);
            dest.writeInt(this.spcount);
            dest.writeInt(this.spbook_photocount);
            dest.writeInt(this.spImport_photocount);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.select_order_name);
            dest.writeString(this.sptstate);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.customerid = in.readString();
            this.xingming = in.readString();
            this.wphone = in.readString();
            this.mphone = in.readString();
            this.cssname = in.readString();
            this.area = in.readString();
            this.weddingdate = in.readString();
            this.orderid = in.readString();
            this.targetdate = in.readString();
            this.consumption_type = in.readString();
            this.acceptor_address = in.readString();
            this.total_money = in.readInt();
            this.payment_money = in.readInt();
            this.nopayment_money = in.readInt();
            this.ordernote = in.readString();
            this.phototype = in.readString();
            this.photodate = in.readString();
            this.photostate = in.readString();
            this.photonote = in.readString();
            this.cameraman = in.readString();
            this.selectday = in.readString();
            this.selectTime = in.readString();
            this.start_select_time = in.readString();
            this.over_select_time = in.readString();
            this.selectman = in.readString();
            this.sptype = in.readString();
            this.sproom = in.readString();
            this.isspurgent = in.readString();
            this.spremarks = in.readString();
            this.id = in.readInt();
            this.spcount = in.readInt();
            this.spbook_photocount = in.readInt();
            this.spImport_photocount = in.readInt();
            this.shop_name = in.readString();
            this.shop_code = in.readString();
            this.select_order_name = in.readString();
            this.sptstate = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
