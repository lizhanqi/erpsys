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
 * @author Created by 李站旗 on 2018/5/12 0012 19:44 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TodayPhotoScheduleEntity extends BaseResult {

    /**
     * Code : 200
     * Data : [{"pcid":2331,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2332,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2333,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2334,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2335,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2336,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2337,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null},{"pcid":2338,"pcday":"20180501","pctime":"1400","islock":0,"pcremarks":null,"orderId":null,"customerid":null,"photoid":null,"area":"全部","shop_code":"ZX002","image":0,"xingming":"","wphone":null,"mphone":null,"cssname":null,"phototype":null,"photodate":null,"phototime":null,"photostate":null,"cameraman":null,"photoassistant":null,"lighting_engineer":null,"designer":null,"mentor":null,"dresser":null,"photonote":null,"photobase":null,"targetdate":null,"consumption_type":null,"acceptor_address":null,"total_money":null,"payment_money":null,"nopayment_money":null,"storeconsuitant1":null,"storeconsuitant2":null,"storeconsuitant3":null,"ordernote":null}]
     */

    private String Code;
    private List<DataBean> Data;
    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements  Parcelable {
        /**
         * pcid : 2331
         * pcday : 20180501
         * pctime : 1400
         * islock : 0
         * pcremarks : null
         * orderId : null
         * customerid : null
         * photoid : null
         * area : 全部
         * shop_code : ZX002
         * image : 0
         * xingming : 
         * wphone : null
         * mphone : null
         * cssname : null
         * phototype : null
         * photodate : null
         * phototime : null
         * photostate : null
         * cameraman : null
         * photoassistant : null
         * lighting_engineer : null
         * designer : null
         * mentor : null
         * dresser : null
         * photonote : null
         * photobase : null
         * targetdate : null
         * consumption_type : null
         * acceptor_address : null
         * total_money : null
         * payment_money : null
         * nopayment_money : null
         * storeconsuitant1 : null
         * storeconsuitant2 : null
         * storeconsuitant3 : null
         * ordernote : null
         */

        private int pcid;
        private String pcday;
        private String pctime;
        private int islock;
        private String pcremarks;
        private String orderId;
        private String customerid;
        private String photoid;
        private String area;
        private String shop_code;
        private int image;
        private String xingming;
        private String wphone;
        private String mphone;
        private String cssname;
        private String phototype;
        private String photodate;
        private String phototime;
        private String photostate;
        private String cameraman;
        private String photoassistant;
        private String lighting_engineer;
        private String designer;
        private String mentor;
        private String dresser;
        private String photonote;
        private String photobase;
        private String targetdate;
        private String consumption_type;
        private String acceptor_address;
        private String total_money;
        private String payment_money;
        private String nopayment_money;
        private String storeconsuitant1;
        private String storeconsuitant2;
        private String storeconsuitant3;
        private String ordernote;

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

        public String getPhotoid() {
            return photoid;
        }

        public void setPhotoid(String photoid) {
            this.photoid = photoid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
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

        public String getPhototime() {
            return phototime;
        }

        public void setPhototime(String phototime) {
            this.phototime = phototime;
        }

        public String getPhotostate() {
            return photostate;
        }

        public void setPhotostate(String photostate) {
            this.photostate = photostate;
        }

        public String getCameraman() {
            return cameraman;
        }

        public void setCameraman(String cameraman) {
            this.cameraman = cameraman;
        }

        public String getPhotoassistant() {
            return photoassistant;
        }

        public void setPhotoassistant(String photoassistant) {
            this.photoassistant = photoassistant;
        }

        public String getLighting_engineer() {
            return lighting_engineer;
        }

        public void setLighting_engineer(String lighting_engineer) {
            this.lighting_engineer = lighting_engineer;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(String designer) {
            this.designer = designer;
        }

        public String getMentor() {
            return mentor;
        }

        public void setMentor(String mentor) {
            this.mentor = mentor;
        }

        public String getDresser() {
            return dresser;
        }

        public void setDresser(String dresser) {
            this.dresser = dresser;
        }

        public String getPhotonote() {
            return photonote;
        }

        public void setPhotonote(String photonote) {
            this.photonote = photonote;
        }

        public String getPhotobase() {
            return photobase;
        }

        public void setPhotobase(String photobase) {
            this.photobase = photobase;
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

        public String getStoreconsuitant1() {
            return storeconsuitant1;
        }

        public void setStoreconsuitant1(String storeconsuitant1) {
            this.storeconsuitant1 = storeconsuitant1;
        }

        public String getStoreconsuitant2() {
            return storeconsuitant2;
        }

        public void setStoreconsuitant2(String storeconsuitant2) {
            this.storeconsuitant2 = storeconsuitant2;
        }

        public String getStoreconsuitant3() {
            return storeconsuitant3;
        }

        public void setStoreconsuitant3(String storeconsuitant3) {
            this.storeconsuitant3 = storeconsuitant3;
        }

        public String getOrdernote() {
            return ordernote;
        }

        public void setOrdernote(String ordernote) {
            this.ordernote = ordernote;
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
            dest.writeString(this.photoid);
            dest.writeString(this.area);
            dest.writeString(this.shop_code);
            dest.writeInt(this.image);
            dest.writeString(this.xingming);
            dest.writeString(this.wphone);
            dest.writeString(this.mphone);
            dest.writeString(this.cssname);
            dest.writeString(this.phototype);
            dest.writeString(this.photodate);
            dest.writeString(this.phototime);
            dest.writeString(this.photostate);
            dest.writeString(this.cameraman);
            dest.writeString(this.photoassistant);
            dest.writeString(this.lighting_engineer);
            dest.writeString(this.designer);
            dest.writeString(this.mentor);
            dest.writeString(this.dresser);
            dest.writeString(this.photonote);
            dest.writeString(this.photobase);
            dest.writeString(this.targetdate);
            dest.writeString(this.consumption_type);
            dest.writeString(this.acceptor_address);
            dest.writeString(this.total_money);
            dest.writeString(this.payment_money);
            dest.writeString(this.nopayment_money);
            dest.writeString(this.storeconsuitant1);
            dest.writeString(this.storeconsuitant2);
            dest.writeString(this.storeconsuitant3);
            dest.writeString(this.ordernote);
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
            this.photoid = in.readString();
            this.area = in.readString();
            this.shop_code = in.readString();
            this.image = in.readInt();
            this.xingming = in.readString();
            this.wphone = in.readString();
            this.mphone = in.readString();
            this.cssname = in.readString();
            this.phototype = in.readString();
            this.photodate = in.readString();
            this.phototime = in.readString();
            this.photostate = in.readString();
            this.cameraman = in.readString();
            this.photoassistant = in.readString();
            this.lighting_engineer = in.readString();
            this.designer = in.readString();
            this.mentor = in.readString();
            this.dresser = in.readString();
            this.photonote = in.readString();
            this.photobase = in.readString();
            this.targetdate = in.readString();
            this.consumption_type = in.readString();
            this.acceptor_address = in.readString();
            this.total_money = in.readString();
            this.payment_money = in.readString();
            this.nopayment_money = in.readString();
            this.storeconsuitant1 = in.readString();
            this.storeconsuitant2 = in.readString();
            this.storeconsuitant3 = in.readString();
            this.ordernote = in.readString();
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
