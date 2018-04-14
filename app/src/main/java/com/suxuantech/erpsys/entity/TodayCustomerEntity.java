package com.suxuantech.erpsys.entity;

import com.google.gson.annotations.SerializedName;

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
 * @author Created by 李站旗 on 2018/4/13 0013 16:37 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class TodayCustomerEntity extends  BaseResult {


    /**
     * Code : 200
     * Msg : success
     * Data : [{"mname":"","mphone":"","wname":"紫琪","wphone":"15236985896","viptype":"","vipnum":"","area":"VVIP-二楼","targetdate":"20180402","shop_name2":"沈阳时尚经典婚纱店","shop_code2":"ZX002","consumption_type":"艺术照","mwechat":"","wwechat":"","customerid":"6bfba44b-6583-436b-b357-fdafb6e20810","orderId":"SY18040200061","order_type_name":null,"ordernote":"gdfgdg","total_money":7121,"payment_money":7000,"nopayment_money":121,"storeconsuitant1":"海芹","storeconsuitant2":"春娜","xingming":" 紫琪","shouji":" 15236985896","id":68,"orderId1":"SY18040200061","customerid1":"6bfba44b-6583-436b-b357-fdafb6e20810","photodate":"2018-04-02 00:00:00.000","phototime":"0900","start_photo_time":null,"over_photo_time":null,"cameraman":"高丽艳 ","photoassistant":"田昭亮 ","lighting_engineer":"","designer":"","mentor":"","phototype":"拍照","photostate":0,"dresser":"海芹","photonote":"","photocount":"13","locationcount":2,"Interiorcount":11,"communicationtime":null,"photobase":"","communication_state":null,"communication_date":null,"communication_man":null,"communication_note":null,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","create_time":null,"secretaireman":"春娜","topic":"水晶之恋|巴黎经典|巴黎经典","topicremark":"","returndate":"2018-04-08 00:00:00.000","getdate":"2018-04-02 00:00:00.000","choosedate":"2018-04-03 00:00:00.000","dress_count":10,"jiaji":0,"setkey":"ZX002","photo_shop_code":"ZX002","shexiangshi":"小飞","jianjishi":"海芹","zhuozhuangshi":"海芹","huazhuangzhuli":"海芹","haijingshu":14,"sheyingshiJB":"总监","huazhuangshiJB":"赴韩总监","dresschoosetime":"1000","dressgettime":"1200","dressreturtime":"1111","islast":1,"pz_staymark":"已通知","pz_staymarkday":"20180412","pz_staymarkbz":"","lf_staymark":"未联络到","lf_staymarkday":"20180413","lf_staymarkbz":"","getdate1":"2018-04-02 00:00:00.000","dresstotal":280,"dresspay":100,"dressnonpay":180,"package_name":"6999丽莎水晶包套"},{"mname":"呵呵","mphone":"18811111122","wname":"","wphone":"","viptype":"","vipnum":"","area":"VVIP-二楼","targetdate":"20180402","shop_name2":"沈阳时尚经典婚纱店","shop_code2":"ZX002","consumption_type":"全家福","mwechat":"","wwechat":"","customerid":"5e18e492-083e-4d5d-a7e4-94c66ac8b58b","orderId":"SY18040200062","order_type_name":null,"ordernote":"","total_money":0,"payment_money":0,"nopayment_money":0,"storeconsuitant1":"小飞","storeconsuitant2":"","xingming":"呵呵 ","shouji":"18811111122 ","id":69,"orderId1":"SY18040200062","customerid1":"5e18e492-083e-4d5d-a7e4-94c66ac8b58b","photodate":"2018-04-12 00:00:00.000","phototime":"1300","start_photo_time":null,"over_photo_time":null,"cameraman":"高丽艳 ","photoassistant":"田昭亮 ","lighting_engineer":"","designer":"","mentor":"","phototype":"拍照","photostate":null,"dresser":"","photonote":"","photocount":null,"locationcount":null,"Interiorcount":null,"communicationtime":null,"photobase":"","communication_state":null,"communication_date":null,"communication_man":null,"communication_note":null,"shop_name":"","shop_code":"ZX002","create_time":null,"secretaireman":null,"topic 04-13 16:36:21.593 29134-29579/com.suxuantech.erpsys I/NoHttp: ":null,"topicremark":null,"returndate":null,"getdate":null,"choosedate":null,"dress_count":null,"jiaji":0,"setkey":"ZX002","photo_shop_code":null,"shexiangshi":null,"jianjishi":null,"zhuozhuangshi":null,"huazhuangzhuli":null,"haijingshu":null,"sheyingshiJB":null,"huazhuangshiJB":null,"dresschoosetime":null,"dressgettime":null,"dressreturtime":null,"islast":1,"pz_staymark":null,"pz_staymarkday":null,"pz_staymarkbz":null,"lf_staymark":null,"lf_staymarkday":null,"lf_staymarkbz":null,"getdate1":null,"dresstotal":0,"dresspay":0,"dressnonpay":0,"package_name":""}]
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
         * mname :
         * mphone :
         * wname : 紫琪
         * wphone : 15236985896
         * viptype :
         * vipnum :
         * area : VVIP-二楼
         * targetdate : 20180402
         * shop_name2 : 沈阳时尚经典婚纱店
         * shop_code2 : ZX002
         * consumption_type : 艺术照
         * mwechat :
         * wwechat :
         * customerid : 6bfba44b-6583-436b-b357-fdafb6e20810
         * orderId : SY18040200061
         * order_type_name : null
         * ordernote : gdfgdg
         * total_money : 7121
         * payment_money : 7000
         * nopayment_money : 121
         * storeconsuitant1 : 海芹
         * storeconsuitant2 : 春娜
         * xingming :  紫琪
         * shouji :  15236985896
         * id : 68
         * orderId1 : SY18040200061
         * customerid1 : 6bfba44b-6583-436b-b357-fdafb6e20810
         * photodate : 2018-04-02 00:00:00.000
         * phototime : 0900
         * start_photo_time : null
         * over_photo_time : null
         * cameraman : 高丽艳
         * photoassistant : 田昭亮
         * lighting_engineer :
         * designer :
         * mentor :
         * phototype : 拍照
         * photostate : 0
         * dresser : 海芹
         * photonote :
         * photocount : 13
         * locationcount : 2
         * Interiorcount : 11
         * communicationtime : null
         * photobase :
         * communication_state : null
         * communication_date : null
         * communication_man : null
         * communication_note : null
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * create_time : null
         * secretaireman : 春娜
         * topic : 水晶之恋|巴黎经典|巴黎经典
         * topicremark :
         * returndate : 2018-04-08 00:00:00.000
         * getdate : 2018-04-02 00:00:00.000
         * choosedate : 2018-04-03 00:00:00.000
         * dress_count : 10
         * jiaji : 0
         * setkey : ZX002
         * photo_shop_code : ZX002
         * shexiangshi : 小飞
         * jianjishi : 海芹
         * zhuozhuangshi : 海芹
         * huazhuangzhuli : 海芹
         * haijingshu : 14
         * sheyingshiJB : 总监
         * huazhuangshiJB : 赴韩总监
         * dresschoosetime : 1000
         * dressgettime : 1200
         * dressreturtime : 1111
         * islast : 1
         * pz_staymark : 已通知
         * pz_staymarkday : 20180412
         * pz_staymarkbz :
         * lf_staymark : 未联络到
         * lf_staymarkday : 20180413
         * lf_staymarkbz :
         * getdate1 : 2018-04-02 00:00:00.000
         * dresstotal : 280
         * dresspay : 100
         * dressnonpay : 180
         * package_name : 6999丽莎水晶包套
         * topic 04-13 16:36:21.593 29134-29579/com.suxuantech.erpsys I/NoHttp:  : null
         */

        private String mname;
        private String mphone;
        private String wname;
        private String wphone;
        private String viptype;
        private String vipnum;
        private String area;
        private String targetdate;
        private String shop_name2;
        private String shop_code2;
        private String consumption_type;
        private String mwechat;
        private String wwechat;
        private String customerid;
        private String orderId;
        private Object order_type_name;
        private String ordernote;
        private int total_money;
        private int payment_money;
        private int nopayment_money;
        private String storeconsuitant1;
        private String storeconsuitant2;
        private String xingming;
        private String shouji;
        private int id;
        private String orderId1;
        private String customerid1;
        private String photodate;
        private String phototime;
        private Object start_photo_time;
        private Object over_photo_time;
        private String cameraman;
        private String photoassistant;
        private String lighting_engineer;
        private String designer;
        private String mentor;
        private String phototype;
        private int photostate;
        private String dresser;
        private String photonote;
        private String photocount;
        private int locationcount;
        private int Interiorcount;
        private Object communicationtime;
        private String photobase;
        private Object communication_state;
        private Object communication_date;
        private Object communication_man;
        private Object communication_note;
        private String shop_name;
        private String shop_code;
        private Object create_time;
        private String secretaireman;
        private String topic;
        private String topicremark;
        private String returndate;
        private String getdate;
        private String choosedate;
        private int dress_count;
        private int jiaji;
        private String setkey;
        private String photo_shop_code;
        private String shexiangshi;
        private String jianjishi;
        private String zhuozhuangshi;
        private String huazhuangzhuli;
        private int haijingshu;
        private String sheyingshiJB;
        private String huazhuangshiJB;
        private String dresschoosetime;
        private String dressgettime;
        private String dressreturtime;
        private int islast;
        private String pz_staymark;
        private String pz_staymarkday;
        private String pz_staymarkbz;
        private String lf_staymark;
        private String lf_staymarkday;
        private String lf_staymarkbz;
        private String getdate1;
        private int dresstotal;
        private int dresspay;
        private int dressnonpay;
        private String package_name;
        @SerializedName("topic 04-13 16:36:21.593 29134-29579/com.suxuantech.erpsys I/NoHttp: ")
        private Object _$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153; // FIXME check this code

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

        public String getTargetdate() {
            return targetdate;
        }

        public void setTargetdate(String targetdate) {
            this.targetdate = targetdate;
        }

        public String getShop_name2() {
            return shop_name2;
        }

        public void setShop_name2(String shop_name2) {
            this.shop_name2 = shop_name2;
        }

        public String getShop_code2() {
            return shop_code2;
        }

        public void setShop_code2(String shop_code2) {
            this.shop_code2 = shop_code2;
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

        public String getWwechat() {
            return wwechat;
        }

        public void setWwechat(String wwechat) {
            this.wwechat = wwechat;
        }

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Object getOrder_type_name() {
            return order_type_name;
        }

        public void setOrder_type_name(Object order_type_name) {
            this.order_type_name = order_type_name;
        }

        public String getOrdernote() {
            return ordernote;
        }

        public void setOrdernote(String ordernote) {
            this.ordernote = ordernote;
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

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getShouji() {
            return shouji;
        }

        public void setShouji(String shouji) {
            this.shouji = shouji;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderId1() {
            return orderId1;
        }

        public void setOrderId1(String orderId1) {
            this.orderId1 = orderId1;
        }

        public String getCustomerid1() {
            return customerid1;
        }

        public void setCustomerid1(String customerid1) {
            this.customerid1 = customerid1;
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

        public Object getStart_photo_time() {
            return start_photo_time;
        }

        public void setStart_photo_time(Object start_photo_time) {
            this.start_photo_time = start_photo_time;
        }

        public Object getOver_photo_time() {
            return over_photo_time;
        }

        public void setOver_photo_time(Object over_photo_time) {
            this.over_photo_time = over_photo_time;
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

        public String getPhototype() {
            return phototype;
        }

        public void setPhototype(String phototype) {
            this.phototype = phototype;
        }

        public int getPhotostate() {
            return photostate;
        }

        public void setPhotostate(int photostate) {
            this.photostate = photostate;
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

        public String getPhotocount() {
            return photocount;
        }

        public void setPhotocount(String photocount) {
            this.photocount = photocount;
        }

        public int getLocationcount() {
            return locationcount;
        }

        public void setLocationcount(int locationcount) {
            this.locationcount = locationcount;
        }

        public int getInteriorcount() {
            return Interiorcount;
        }

        public void setInteriorcount(int Interiorcount) {
            this.Interiorcount = Interiorcount;
        }

        public Object getCommunicationtime() {
            return communicationtime;
        }

        public void setCommunicationtime(Object communicationtime) {
            this.communicationtime = communicationtime;
        }

        public String getPhotobase() {
            return photobase;
        }

        public void setPhotobase(String photobase) {
            this.photobase = photobase;
        }

        public Object getCommunication_state() {
            return communication_state;
        }

        public void setCommunication_state(Object communication_state) {
            this.communication_state = communication_state;
        }

        public Object getCommunication_date() {
            return communication_date;
        }

        public void setCommunication_date(Object communication_date) {
            this.communication_date = communication_date;
        }

        public Object getCommunication_man() {
            return communication_man;
        }

        public void setCommunication_man(Object communication_man) {
            this.communication_man = communication_man;
        }

        public Object getCommunication_note() {
            return communication_note;
        }

        public void setCommunication_note(Object communication_note) {
            this.communication_note = communication_note;
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

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getSecretaireman() {
            return secretaireman;
        }

        public void setSecretaireman(String secretaireman) {
            this.secretaireman = secretaireman;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getTopicremark() {
            return topicremark;
        }

        public void setTopicremark(String topicremark) {
            this.topicremark = topicremark;
        }

        public String getReturndate() {
            return returndate;
        }

        public void setReturndate(String returndate) {
            this.returndate = returndate;
        }

        public String getGetdate() {
            return getdate;
        }

        public void setGetdate(String getdate) {
            this.getdate = getdate;
        }

        public String getChoosedate() {
            return choosedate;
        }

        public void setChoosedate(String choosedate) {
            this.choosedate = choosedate;
        }

        public int getDress_count() {
            return dress_count;
        }

        public void setDress_count(int dress_count) {
            this.dress_count = dress_count;
        }

        public int getJiaji() {
            return jiaji;
        }

        public void setJiaji(int jiaji) {
            this.jiaji = jiaji;
        }

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }

        public String getPhoto_shop_code() {
            return photo_shop_code;
        }

        public void setPhoto_shop_code(String photo_shop_code) {
            this.photo_shop_code = photo_shop_code;
        }

        public String getShexiangshi() {
            return shexiangshi;
        }

        public void setShexiangshi(String shexiangshi) {
            this.shexiangshi = shexiangshi;
        }

        public String getJianjishi() {
            return jianjishi;
        }

        public void setJianjishi(String jianjishi) {
            this.jianjishi = jianjishi;
        }

        public String getZhuozhuangshi() {
            return zhuozhuangshi;
        }

        public void setZhuozhuangshi(String zhuozhuangshi) {
            this.zhuozhuangshi = zhuozhuangshi;
        }

        public String getHuazhuangzhuli() {
            return huazhuangzhuli;
        }

        public void setHuazhuangzhuli(String huazhuangzhuli) {
            this.huazhuangzhuli = huazhuangzhuli;
        }

        public int getHaijingshu() {
            return haijingshu;
        }

        public void setHaijingshu(int haijingshu) {
            this.haijingshu = haijingshu;
        }

        public String getSheyingshiJB() {
            return sheyingshiJB;
        }

        public void setSheyingshiJB(String sheyingshiJB) {
            this.sheyingshiJB = sheyingshiJB;
        }

        public String getHuazhuangshiJB() {
            return huazhuangshiJB;
        }

        public void setHuazhuangshiJB(String huazhuangshiJB) {
            this.huazhuangshiJB = huazhuangshiJB;
        }

        public String getDresschoosetime() {
            return dresschoosetime;
        }

        public void setDresschoosetime(String dresschoosetime) {
            this.dresschoosetime = dresschoosetime;
        }

        public String getDressgettime() {
            return dressgettime;
        }

        public void setDressgettime(String dressgettime) {
            this.dressgettime = dressgettime;
        }

        public String getDressreturtime() {
            return dressreturtime;
        }

        public void setDressreturtime(String dressreturtime) {
            this.dressreturtime = dressreturtime;
        }

        public int getIslast() {
            return islast;
        }

        public void setIslast(int islast) {
            this.islast = islast;
        }

        public String getPz_staymark() {
            return pz_staymark;
        }

        public void setPz_staymark(String pz_staymark) {
            this.pz_staymark = pz_staymark;
        }

        public String getPz_staymarkday() {
            return pz_staymarkday;
        }

        public void setPz_staymarkday(String pz_staymarkday) {
            this.pz_staymarkday = pz_staymarkday;
        }

        public String getPz_staymarkbz() {
            return pz_staymarkbz;
        }

        public void setPz_staymarkbz(String pz_staymarkbz) {
            this.pz_staymarkbz = pz_staymarkbz;
        }

        public String getLf_staymark() {
            return lf_staymark;
        }

        public void setLf_staymark(String lf_staymark) {
            this.lf_staymark = lf_staymark;
        }

        public String getLf_staymarkday() {
            return lf_staymarkday;
        }

        public void setLf_staymarkday(String lf_staymarkday) {
            this.lf_staymarkday = lf_staymarkday;
        }

        public String getLf_staymarkbz() {
            return lf_staymarkbz;
        }

        public void setLf_staymarkbz(String lf_staymarkbz) {
            this.lf_staymarkbz = lf_staymarkbz;
        }

        public String getGetdate1() {
            return getdate1;
        }

        public void setGetdate1(String getdate1) {
            this.getdate1 = getdate1;
        }

        public int getDresstotal() {
            return dresstotal;
        }

        public void setDresstotal(int dresstotal) {
            this.dresstotal = dresstotal;
        }

        public int getDresspay() {
            return dresspay;
        }

        public void setDresspay(int dresspay) {
            this.dresspay = dresspay;
        }

        public int getDressnonpay() {
            return dressnonpay;
        }

        public void setDressnonpay(int dressnonpay) {
            this.dressnonpay = dressnonpay;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public Object get_$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153() {
            return _$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153;
        }

        public void set_$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153(Object _$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153) {
            this._$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153 = _$Topic04131636215932913429579ComSuxuantechErpsysINoHttp153;
        }
    }
}
