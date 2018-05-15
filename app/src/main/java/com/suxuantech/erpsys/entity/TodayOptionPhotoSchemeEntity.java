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
 * @author Created by 李站旗 on 2018/5/12 0012 20:17 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TodayOptionPhotoSchemeEntity extends BaseResult {

    /**
     * Code : 200
     * Data : [{"pcid":886,"pcday":"20180501","pctime":"0900","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"spid":null,"area":"全部","comarea":null,"shop_code":"ZX002","jibie":null,"weddingdate":null,"xingming":"","wphone":null,"mphone":null,"cssname":null,"select_order_name":null,"phototype":null,"photodate":null,"photostate":"","photonote":null,"cameraman":null,"selectday":null,"selectTime":null,"selectman":null,"sptype":"","sptstate":null,"sproom":null,"isspurgent":"否","spremarks":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null},{"pcid":887,"pcday":"20180501","pctime":"0900","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"spid":null,"area":"全部","comarea":null,"shop_code":"ZX002","jibie":null,"weddingdate":null,"xingming":"","wphone":null,"mphone":null,"cssname":null,"select_order_name":null,"phototype":null,"photodate":null,"photostate":"","photonote":null,"cameraman":null,"selectday":null,"selectTime":null,"selectman":null,"sptype":"","sptstate":null,"sproom":null,"isspurgent":"否","spremarks":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null},{"pcid":888,"pcday":"20180501","pctime":"0900","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"spid":null,"area":"全部","comarea":null,"shop_code":"ZX002","jibie":null,"weddingdate":null,"xingming":"","wphone":null,"mphone":null,"cssname":null,"select_order_name":null,"phototype":null,"photodate":null,"photostate":"","photonote":null,"cameraman":null,"selectday":null,"selectTime":null,"selectman":null,"sptype":"","sptstate":null,"sproom":null,"isspurgent":"否","spremarks":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null},{"pcid":889,"pcday":"20180501","pctime":"0900","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"spid":null,"area":"全部","comarea":null,"shop_code":"ZX002","jibie":null,"weddingdate":null,"xingming":"","wphone":null,"mphone":null,"cssname":null,"select_order_name":null,"phototype":null,"photodate":null,"photostate":"","photonote":null,"cameraman":null,"selectday":null,"selectTime":null,"selectman":null,"sptype":"","sptstate":null,"sproom":null,"isspurgent":"否","spremarks":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null},{"pcid":890,"pcday":"20180501","pctime":"0900","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"spid":null,"area":"全部","comarea":null,"shop_code":"ZX002","jibie":null,"weddingdate":null,"xingming":"","wphone":null,"mphone":null,"cssname":null,"select_order_name":null,"phototype":null,"photodate":null,"photostate":"","photonote":null,"cameraman":null,"selectday":null,"selectTime":null,"selectman":null,"sptype":"","sptstate":null,"sproom":null,"isspurgent":"否","spremarks":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements   Parcelable {
        /**
         * pcid : 886
         * pcday : 20180501
         * pctime : 0900
         * islock : 0
         * pcremarks : null
         * orderId : null
         * customerid : null
         * spid : null
         * area : 全部
         * comarea : null
         * shop_code : ZX002
         * jibie : null
         * weddingdate : null
         * xingming : 
         * wphone : null
         * mphone : null
         * cssname : null
         * select_order_name : null
         * phototype : null
         * photodate : null
         * photostate : 
         * photonote : null
         * cameraman : null
         * selectday : null
         * selectTime : null
         * selectman : null
         * sptype : 
         * sptstate : null
         * sproom : null
         * isspurgent : 否
         * spremarks : null
         * targetdate : null
         * consumption_type : null
         * acceptor_address : null
         * total_money : null
         * payment_money : null
         * nopayment_money : null
         */

        private int pcid;
        private String pcday;
        private String pctime;
        private int islock;
        private String pcremarks;
        private String orderId;
        private String customerid;
        private String spid;
        private String area;
        private String comarea;
        private String shop_code;
        private String jibie;
        private String weddingdate;
        private String xingming;
        private String wphone;
        private String mphone;
        private String cssname;
        private String select_order_name;
        private String phototype;
        private String photodate;
        private String photostate;
        private String photonote;
        private String cameraman;
        private String selectday;
        private String selectTime;
        private String selectman;
        private String sptype;
        private String sptstate;
        private String sproom;
        private String isspurgent;
        private String spremarks;
        private String targetdate;
        private String consumption_type;
        private String acceptor_address;
        private String total_money;
        private String payment_money;
        private String nopayment_money;

        public int getPcid() {
            return pcid;
        }

        public void setPcid(int pcid) {
            this.pcid = pcid;
        }

        public String getPcday() {
            return pcday;
        }

        public void setPcday(String pcday) {
            this.pcday = pcday;
        }

        public String getPctime() {
            return pctime;
        }

        public void setPctime(String pctime) {
            this.pctime = pctime;
        }

        public int getIslock() {
            return islock;
        }

        public void setIslock(int islock) {
            this.islock = islock;
        }

        public String getPcremarks() {
            return pcremarks;
        }

        public void setPcremarks(String pcremarks) {
            this.pcremarks = pcremarks;
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

        public String getSpid() {
            return spid;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getComarea() {
            return comarea;
        }

        public void setComarea(String comarea) {
            this.comarea = comarea;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getJibie() {
            return jibie;
        }

        public void setJibie(String jibie) {
            this.jibie = jibie;
        }

        public String getWeddingdate() {
            return weddingdate;
        }

        public void setWeddingdate(String weddingdate) {
            this.weddingdate = weddingdate;
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

        public String getSelect_order_name() {
            return select_order_name;
        }

        public void setSelect_order_name(String select_order_name) {
            this.select_order_name = select_order_name;
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

        public String getSptstate() {
            return sptstate;
        }

        public void setSptstate(String sptstate) {
            this.sptstate = sptstate;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.pcid);
            dest.writeString(this.pcday);
            dest.writeString(this.pctime);
            dest.writeInt(this.islock);
            dest.writeString(this.pcremarks);
            dest.writeString(this.orderId);
            dest.writeString(this.customerid);
            dest.writeString(this.spid);
            dest.writeString(this.area);
            dest.writeString(this.comarea);
            dest.writeString(this.shop_code);
            dest.writeString(this.jibie);
            dest.writeString(this.weddingdate);
            dest.writeString(this.xingming);
            dest.writeString(this.wphone);
            dest.writeString(this.mphone);
            dest.writeString(this.cssname);
            dest.writeString(this.select_order_name);
            dest.writeString(this.phototype);
            dest.writeString(this.photodate);
            dest.writeString(this.photostate);
            dest.writeString(this.photonote);
            dest.writeString(this.cameraman);
            dest.writeString(this.selectday);
            dest.writeString(this.selectTime);
            dest.writeString(this.selectman);
            dest.writeString(this.sptype);
            dest.writeString(this.sptstate);
            dest.writeString(this.sproom);
            dest.writeString(this.isspurgent);
            dest.writeString(this.spremarks);
            dest.writeString(this.targetdate);
            dest.writeString(this.consumption_type);
            dest.writeString(this.acceptor_address);
            dest.writeString(this.total_money);
            dest.writeString(this.payment_money);
            dest.writeString(this.nopayment_money);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.pcid = in.readInt();
            this.pcday = in.readString();
            this.pctime = in.readString();
            this.islock = in.readInt();
            this.pcremarks = in.readString();
            this.orderId = in.readString();
            this.customerid = in.readString();
            this.spid = in.readString();
            this.area = in.readString();
            this.comarea = in.readString();
            this.shop_code = in.readString();
            this.jibie = in.readString();
            this.weddingdate = in.readString();
            this.xingming = in.readString();
            this.wphone = in.readString();
            this.mphone = in.readString();
            this.cssname = in.readString();
            this.select_order_name = in.readString();
            this.phototype = in.readString();
            this.photodate = in.readString();
            this.photostate = in.readString();
            this.photonote = in.readString();
            this.cameraman = in.readString();
            this.selectday = in.readString();
            this.selectTime = in.readString();
            this.selectman = in.readString();
            this.sptype = in.readString();
            this.sptstate = in.readString();
            this.sproom = in.readString();
            this.isspurgent = in.readString();
            this.spremarks = in.readString();
            this.targetdate = in.readString();
            this.consumption_type = in.readString();
            this.acceptor_address = in.readString();
            this.total_money = in.readString();
            this.payment_money = in.readString();
            this.nopayment_money = in.readString();
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
