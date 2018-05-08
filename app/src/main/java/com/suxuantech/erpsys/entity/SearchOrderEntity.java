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
 * @author Created by 李站旗 on 2017/12/11 0011 19:59 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 订单搜索结果对象
 */

public class SearchOrderEntity extends BaseResult {

    /**
     * Code : 200
     * Data : [{"id":52,"mname":"小斐","mphone":"15623657854","wname":"张雯","wphone":"12352456578","viptype":"","vipnum":"","area":"V2-五楼","iswhethercantake":"","order_type_name":null,"shareshoplist":"ZX002,","ordertopic":"水晶之恋|巴黎经典","from_index":null,"order_type":0,"isalreadytake":"","id1":52,"orderId":"SY18032100043","customerid":"42ca4038-70eb-46ed-8780-814ab313042f","order_type1":0,"consumption_type":"婚纱照","package_name":null,"targetdate":"20180321","targetime":null,"storeconsuitant1":"海芹","storeconsuitant2":"高丽艳 ","acceptor_address":null,"total_money":0,"payment_money":0,"nopayment_money":0,"supplementary_money":0,"bargain_money":0,"allfinishfate":null,"payoervedate":"","firstpaydate":null,"dressmony":0,"sellmoney":0,"ordernote":"2","vipnum1":null,"shop_order_name":null,"shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","create_time":"2018-03-21 14:52:23.000","blankoutannal":0,"blankoutannalremark":null,"iswhethercantake1":0,"isalreadytake1":0,"whethercantakedate":null,"alreadytakedate":null,"take_takaddress":null,"dresstotal":230,"dresspay":0,"dressnonpay":230,"uploadoriginal":0,"uploaddesign":0,"makeuptotal":0,"makeuppay":0,"makeupnonpay":0,"spImport_photocount":0,"spcount":0,"spbook_photocount":0,"sendtime":null,"getDate":null,"shareshoplist1":"ZX002,","opershop":"ZX002","turntype":null,"server1":2,"server2":0,"server3":0,"server4":0,"server5":0,"server6":0,"server7":0,"refund":0,"refunddate":null,"reservelock":0,"addlock":0,"order_type_name1":null,"ordertopic1":"水晶之恋|巴黎经典","customernote":"","qj_producttype":0,"qj_sjgetDate":null,"qj_bzname":null,"qj_produceover":0,"qj_produceoverday":null,"sj_sendnumber":0,"qj_staymark":"","qj_staymarkday":"","qj_staymarkbz":null,"storeconsuitant3":"田昭亮 ","indoordresscount":3,"outerdresscount":2,"seadresscount":5,"travelresscount":6,"dresstheme":"礼服主题4|礼服主题5","oeder_newclass":"","paizhaocount":0,"rucecount":0,"jingxiucount":0,"qj_psjdate":"","qj_renname":"","customerphoto":"","sj_state":"","packagecount":0,"xingming":"小斐 张雯","shouji":"15623657854 12352456578","blankoutannal1":"","refund1":"","mwechat":"123456","wwechat":"123456","mqq":null,"wqq":null,"cssname":"门市指名转介绍","refunddate1":null,"area1":"V2-五楼","mbirthdate":"20180321","wbirthdate":"20180331","midtype":null,"mid":null,"introducerid":null,"introducer_name":null,"weddingdate":"20180325","wIdtype":null,"wid":null,"mid1":null,"mid2":null,"customernote1":null,"email":"854658784@qq.com","customerid1":"42ca4038-70eb-46ed-8780-814ab313042f","address":"朝阳区","vipdiscount":"10","photodate":"2018-03-26 00:00:00.000","phototime":"1300","cameraman":"高丽艳 ","photostate":0,"phototype":"补拍","selectday":"20180326","selectTime":"1000","sptstate":0,"kanbanriqi":"20180414","kanbanshijian":"1300","kanbanstate":5}]
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
         * id : 52
         * mname : 小斐
         * mphone : 15623657854
         * wname : 张雯
         * wphone : 12352456578
         * viptype :
         * vipnum :
         * area : V2-五楼
         * iswhethercantake :
         * order_type_name : null
         * shareshoplist : ZX002,
         * ordertopic : 水晶之恋|巴黎经典
         * from_index : null
         * order_type : 0
         * isalreadytake :
         * id1 : 52
         * orderId : SY18032100043
         * customerid : 42ca4038-70eb-46ed-8780-814ab313042f
         * order_type1 : 0
         * consumption_type : 婚纱照
         * package_name : null
         * targetdate : 20180321
         * targetime : null
         * storeconsuitant1 : 海芹
         * storeconsuitant2 : 高丽艳
         * acceptor_address : null
         * total_money : 0
         * payment_money : 0
         * nopayment_money : 0
         * supplementary_money : 0
         * bargain_money : 0
         * allfinishfate : null
         * payoervedate :
         * firstpaydate : null
         * dressmony : 0
         * sellmoney : 0
         * ordernote : 2
         * vipnum1 : null
         * shop_order_name : null
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * create_time : 2018-03-21 14:52:23.000
         * blankoutannal : 0
         * blankoutannalremark : null
         * iswhethercantake1 : 0
         * isalreadytake1 : 0
         * whethercantakedate : null
         * alreadytakedate : null
         * take_takaddress : null
         * dresstotal : 230
         * dresspay : 0
         * dressnonpay : 230
         * uploadoriginal : 0
         * uploaddesign : 0
         * makeuptotal : 0
         * makeuppay : 0
         * makeupnonpay : 0
         * spImport_photocount : 0
         * spcount : 0
         * spbook_photocount : 0
         * sendtime : null
         * getDate : null
         * shareshoplist1 : ZX002,
         * opershop : ZX002
         * turntype : null
         * server1 : 2
         * server2 : 0
         * server3 : 0
         * server4 : 0
         * server5 : 0
         * server6 : 0
         * server7 : 0
         * refund : 0
         * refunddate : null
         * reservelock : 0
         * addlock : 0
         * order_type_name1 : null
         * ordertopic1 : 水晶之恋|巴黎经典
         * customernote :
         * qj_producttype : 0
         * qj_sjgetDate : null
         * qj_bzname : null
         * qj_produceover : 0
         * qj_produceoverday : null
         * sj_sendnumber : 0
         * qj_staymark :
         * qj_staymarkday :
         * qj_staymarkbz : null
         * storeconsuitant3 : 田昭亮
         * indoordresscount : 3
         * outerdresscount : 2
         * seadresscount : 5
         * travelresscount : 6
         * dresstheme : 礼服主题4|礼服主题5
         * oeder_newclass :
         * paizhaocount : 0
         * rucecount : 0
         * jingxiucount : 0
         * qj_psjdate :
         * qj_renname :
         * customerphoto :
         * sj_state :
         * packagecount : 0
         * xingming : 小斐 张雯
         * shouji : 15623657854 12352456578
         * blankoutannal1 :
         * refund1 :
         * mwechat : 123456
         * wwechat : 123456
         * mqq : null
         * wqq : null
         * cssname : 门市指名转介绍
         * refunddate1 : null
         * area1 : V2-五楼
         * mbirthdate : 20180321
         * wbirthdate : 20180331
         * midtype : null
         * mid : null
         * introducerid : null
         * introducer_name : null
         * weddingdate : 20180325
         * wIdtype : null
         * wid : null
         * mid1 : null
         * mid2 : null
         * customernote1 : null
         * email : 854658784@qq.com
         * customerid1 : 42ca4038-70eb-46ed-8780-814ab313042f
         * address : 朝阳区
         * vipdiscount : 10
         * photodate : 2018-03-26 00:00:00.000
         * phototime : 1300
         * cameraman : 高丽艳
         * photostate : 0
         * phototype : 补拍
         * selectday : 20180326
         * selectTime : 1000
         * sptstate : 0
         * kanbanriqi : 20180414
         * kanbanshijian : 1300
         * kanbanstate : 5
         */

        private int id;
        private String mname;
        private String mphone;
        private String wname;
        private String wphone;
        private String viptype;
        private String vipnum;
        private String area;
        private String iswhethercantake;
        private String order_type_name;
        private String shareshoplist;
        private String ordertopic;
        private String from_index;
        private int order_type;
        private String isalreadytake;
        private int id1;
        private String orderId;
        private String customerid;
        private int order_type1;
        private String consumption_type;
        private String package_name;
        private String targetdate;
        private String targetime;
        private String storeconsuitant1;
        private String storeconsuitant2;
        private String acceptor_address;
        private int total_money;
        private int payment_money;
        private int nopayment_money;
        private int supplementary_money;
        private int bargain_money;
        private String allfinishfate;
        private String payoervedate;
        private String firstpaydate;
        private int dressmony;
        private int sellmoney;
        private String ordernote;
        private String vipnum1;
        private String shop_order_name;
        private String shop_code;
        private String shop_name;
        private String create_time;
        private int blankoutannal;
        private String blankoutannalremark;
        private int iswhethercantake1;
        private int isalreadytake1;
        private String whethercantakedate;
        private String alreadytakedate;
        private String take_takaddress;
        private int dresstotal;
        private int dresspay;
        private int dressnonpay;
        private int uploadoriginal;
        private int uploaddesign;
        private int makeuptotal;
        private int makeuppay;
        private int makeupnonpay;
        private int spImport_photocount;
        private int spcount;
        private int spbook_photocount;
        private String sendtime;
        private String getDate;
        private String shareshoplist1;
        private String opershop;
        private String turntype;
        private int server1;
        private int server2;
        private int server3;
        private int server4;
        private int server5;
        private int server6;
        private int server7;
        private int refund;
        private String refunddate;
        private int reservelock;
        private int addlock;
        private String order_type_name1;
        private String ordertopic1;
        private String customernote;
        private int qj_producttype;
        private String qj_sjgetDate;
        private String qj_bzname;
        private int qj_produceover;
        private String qj_produceoverday;
        private int sj_sendnumber;
        private String qj_staymark;
        private String qj_staymarkday;
        private String qj_staymarkbz;
        private String storeconsuitant3;
        private int indoordresscount;
        private int outerdresscount;
        private int seadresscount;
        private int travelresscount;
        private String dresstheme;
        private String oeder_newclass;
        private int paizhaocount;
        private int rucecount;
        private int jingxiucount;
        private String qj_psjdate;
        private String qj_renname;
        private String customerphoto;
        private String sj_state;
        private int packagecount;
        private String xingming;
        private String shouji;
        private String blankoutannal1;
        private String refund1;
        private String mwechat;
        private String wwechat;
        private String mqq;
        private String wqq;
        private String cssname;
        private String refunddate1;
        private String area1;
        private String mbirthdate;
        private String wbirthdate;
        private String midtype;
        private String mid;
        private String introducerid;
        private String introducer_name;
        private String weddingdate;
        private String wIdtype;
        private String wid;
        private String mid1;
        private String mid2;
        private String customernote1;
        private String email;
        private String customerid1;
        private String address;
        private String vipdiscount;
        private String photodate;
        private String phototime;
        private String cameraman;
        private int photostate;
        private String phototype;
        private String selectday;
        private String selectTime;
        private int sptstate;
        private String kanbanriqi;
        private String kanbanshijian;
        private int kanbanstate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getIswhethercantake() {
            return iswhethercantake;
        }

        public void setIswhethercantake(String iswhethercantake) {
            this.iswhethercantake = iswhethercantake;
        }

        public String getOrder_type_name() {
            return order_type_name;
        }

        public void setOrder_type_name(String order_type_name) {
            this.order_type_name = order_type_name;
        }

        public String getShareshoplist() {
            return shareshoplist;
        }

        public void setShareshoplist(String shareshoplist) {
            this.shareshoplist = shareshoplist;
        }

        public String getOrdertopic() {
            return ordertopic;
        }

        public void setOrdertopic(String ordertopic) {
            this.ordertopic = ordertopic;
        }

        public String getFrom_index() {
            return from_index;
        }

        public void setFrom_index(String from_index) {
            this.from_index = from_index;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public String getIsalreadytake() {
            return isalreadytake;
        }

        public void setIsalreadytake(String isalreadytake) {
            this.isalreadytake = isalreadytake;
        }

        public int getId1() {
            return id1;
        }

        public void setId1(int id1) {
            this.id1 = id1;
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

        public int getOrder_type1() {
            return order_type1;
        }

        public void setOrder_type1(int order_type1) {
            this.order_type1 = order_type1;
        }

        public String getConsumption_type() {
            return consumption_type;
        }

        public void setConsumption_type(String consumption_type) {
            this.consumption_type = consumption_type;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getTargetdate() {
            return targetdate;
        }

        public void setTargetdate(String targetdate) {
            this.targetdate = targetdate;
        }

        public String getTargetime() {
            return targetime;
        }

        public void setTargetime(String targetime) {
            this.targetime = targetime;
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

        public int getSupplementary_money() {
            return supplementary_money;
        }

        public void setSupplementary_money(int supplementary_money) {
            this.supplementary_money = supplementary_money;
        }

        public int getBargain_money() {
            return bargain_money;
        }

        public void setBargain_money(int bargain_money) {
            this.bargain_money = bargain_money;
        }

        public String getAllfinishfate() {
            return allfinishfate;
        }

        public void setAllfinishfate(String allfinishfate) {
            this.allfinishfate = allfinishfate;
        }

        public String getPayoervedate() {
            return payoervedate;
        }

        public void setPayoervedate(String payoervedate) {
            this.payoervedate = payoervedate;
        }

        public String getFirstpaydate() {
            return firstpaydate;
        }

        public void setFirstpaydate(String firstpaydate) {
            this.firstpaydate = firstpaydate;
        }

        public int getDressmony() {
            return dressmony;
        }

        public void setDressmony(int dressmony) {
            this.dressmony = dressmony;
        }

        public int getSellmoney() {
            return sellmoney;
        }

        public void setSellmoney(int sellmoney) {
            this.sellmoney = sellmoney;
        }

        public String getOrdernote() {
            return ordernote;
        }

        public void setOrdernote(String ordernote) {
            this.ordernote = ordernote;
        }

        public String getVipnum1() {
            return vipnum1;
        }

        public void setVipnum1(String vipnum1) {
            this.vipnum1 = vipnum1;
        }

        public String getShop_order_name() {
            return shop_order_name;
        }

        public void setShop_order_name(String shop_order_name) {
            this.shop_order_name = shop_order_name;
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

        public int getBlankoutannal() {
            return blankoutannal;
        }

        public void setBlankoutannal(int blankoutannal) {
            this.blankoutannal = blankoutannal;
        }

        public String getBlankoutannalremark() {
            return blankoutannalremark;
        }

        public void setBlankoutannalremark(String blankoutannalremark) {
            this.blankoutannalremark = blankoutannalremark;
        }

        public int getIswhethercantake1() {
            return iswhethercantake1;
        }

        public void setIswhethercantake1(int iswhethercantake1) {
            this.iswhethercantake1 = iswhethercantake1;
        }

        public int getIsalreadytake1() {
            return isalreadytake1;
        }

        public void setIsalreadytake1(int isalreadytake1) {
            this.isalreadytake1 = isalreadytake1;
        }

        public String getWhethercantakedate() {
            return whethercantakedate;
        }

        public void setWhethercantakedate(String whethercantakedate) {
            this.whethercantakedate = whethercantakedate;
        }

        public String getAlreadytakedate() {
            return alreadytakedate;
        }

        public void setAlreadytakedate(String alreadytakedate) {
            this.alreadytakedate = alreadytakedate;
        }

        public String getTake_takaddress() {
            return take_takaddress;
        }

        public void setTake_takaddress(String take_takaddress) {
            this.take_takaddress = take_takaddress;
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

        public int getUploadoriginal() {
            return uploadoriginal;
        }

        public void setUploadoriginal(int uploadoriginal) {
            this.uploadoriginal = uploadoriginal;
        }

        public int getUploaddesign() {
            return uploaddesign;
        }

        public void setUploaddesign(int uploaddesign) {
            this.uploaddesign = uploaddesign;
        }

        public int getMakeuptotal() {
            return makeuptotal;
        }

        public void setMakeuptotal(int makeuptotal) {
            this.makeuptotal = makeuptotal;
        }

        public int getMakeuppay() {
            return makeuppay;
        }

        public void setMakeuppay(int makeuppay) {
            this.makeuppay = makeuppay;
        }

        public int getMakeupnonpay() {
            return makeupnonpay;
        }

        public void setMakeupnonpay(int makeupnonpay) {
            this.makeupnonpay = makeupnonpay;
        }

        public int getSpImport_photocount() {
            return spImport_photocount;
        }

        public void setSpImport_photocount(int spImport_photocount) {
            this.spImport_photocount = spImport_photocount;
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

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getGetDate() {
            return getDate;
        }

        public void setGetDate(String getDate) {
            this.getDate = getDate;
        }

        public String getShareshoplist1() {
            return shareshoplist1;
        }

        public void setShareshoplist1(String shareshoplist1) {
            this.shareshoplist1 = shareshoplist1;
        }

        public String getOpershop() {
            return opershop;
        }

        public void setOpershop(String opershop) {
            this.opershop = opershop;
        }

        public String getTurntype() {
            return turntype;
        }

        public void setTurntype(String turntype) {
            this.turntype = turntype;
        }

        public int getServer1() {
            return server1;
        }

        public void setServer1(int server1) {
            this.server1 = server1;
        }

        public int getServer2() {
            return server2;
        }

        public void setServer2(int server2) {
            this.server2 = server2;
        }

        public int getServer3() {
            return server3;
        }

        public void setServer3(int server3) {
            this.server3 = server3;
        }

        public int getServer4() {
            return server4;
        }

        public void setServer4(int server4) {
            this.server4 = server4;
        }

        public int getServer5() {
            return server5;
        }

        public void setServer5(int server5) {
            this.server5 = server5;
        }

        public int getServer6() {
            return server6;
        }

        public void setServer6(int server6) {
            this.server6 = server6;
        }

        public int getServer7() {
            return server7;
        }

        public void setServer7(int server7) {
            this.server7 = server7;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public String getRefunddate() {
            return refunddate;
        }

        public void setRefunddate(String refunddate) {
            this.refunddate = refunddate;
        }

        public int getReservelock() {
            return reservelock;
        }

        public void setReservelock(int reservelock) {
            this.reservelock = reservelock;
        }

        public int getAddlock() {
            return addlock;
        }

        public void setAddlock(int addlock) {
            this.addlock = addlock;
        }

        public String getOrder_type_name1() {
            return order_type_name1;
        }

        public void setOrder_type_name1(String order_type_name1) {
            this.order_type_name1 = order_type_name1;
        }

        public String getOrdertopic1() {
            return ordertopic1;
        }

        public void setOrdertopic1(String ordertopic1) {
            this.ordertopic1 = ordertopic1;
        }

        public String getCustomernote() {
            return customernote;
        }

        public void setCustomernote(String customernote) {
            this.customernote = customernote;
        }

        public int getQj_producttype() {
            return qj_producttype;
        }

        public void setQj_producttype(int qj_producttype) {
            this.qj_producttype = qj_producttype;
        }

        public String getQj_sjgetDate() {
            return qj_sjgetDate;
        }

        public void setQj_sjgetDate(String qj_sjgetDate) {
            this.qj_sjgetDate = qj_sjgetDate;
        }

        public String getQj_bzname() {
            return qj_bzname;
        }

        public void setQj_bzname(String qj_bzname) {
            this.qj_bzname = qj_bzname;
        }

        public int getQj_produceover() {
            return qj_produceover;
        }

        public void setQj_produceover(int qj_produceover) {
            this.qj_produceover = qj_produceover;
        }

        public String getQj_produceoverday() {
            return qj_produceoverday;
        }

        public void setQj_produceoverday(String qj_produceoverday) {
            this.qj_produceoverday = qj_produceoverday;
        }

        public int getSj_sendnumber() {
            return sj_sendnumber;
        }

        public void setSj_sendnumber(int sj_sendnumber) {
            this.sj_sendnumber = sj_sendnumber;
        }

        public String getQj_staymark() {
            return qj_staymark;
        }

        public void setQj_staymark(String qj_staymark) {
            this.qj_staymark = qj_staymark;
        }

        public String getQj_staymarkday() {
            return qj_staymarkday;
        }

        public void setQj_staymarkday(String qj_staymarkday) {
            this.qj_staymarkday = qj_staymarkday;
        }

        public String getQj_staymarkbz() {
            return qj_staymarkbz;
        }

        public void setQj_staymarkbz(String qj_staymarkbz) {
            this.qj_staymarkbz = qj_staymarkbz;
        }

        public String getStoreconsuitant3() {
            return storeconsuitant3;
        }

        public void setStoreconsuitant3(String storeconsuitant3) {
            this.storeconsuitant3 = storeconsuitant3;
        }

        public int getIndoordresscount() {
            return indoordresscount;
        }

        public void setIndoordresscount(int indoordresscount) {
            this.indoordresscount = indoordresscount;
        }

        public int getOuterdresscount() {
            return outerdresscount;
        }

        public void setOuterdresscount(int outerdresscount) {
            this.outerdresscount = outerdresscount;
        }

        public int getSeadresscount() {
            return seadresscount;
        }

        public void setSeadresscount(int seadresscount) {
            this.seadresscount = seadresscount;
        }

        public int getTravelresscount() {
            return travelresscount;
        }

        public void setTravelresscount(int travelresscount) {
            this.travelresscount = travelresscount;
        }

        public String getDresstheme() {
            return dresstheme;
        }

        public void setDresstheme(String dresstheme) {
            this.dresstheme = dresstheme;
        }

        public String getOeder_newclass() {
            return oeder_newclass;
        }

        public void setOeder_newclass(String oeder_newclass) {
            this.oeder_newclass = oeder_newclass;
        }

        public int getPaizhaocount() {
            return paizhaocount;
        }

        public void setPaizhaocount(int paizhaocount) {
            this.paizhaocount = paizhaocount;
        }

        public int getRucecount() {
            return rucecount;
        }

        public void setRucecount(int rucecount) {
            this.rucecount = rucecount;
        }

        public int getJingxiucount() {
            return jingxiucount;
        }

        public void setJingxiucount(int jingxiucount) {
            this.jingxiucount = jingxiucount;
        }

        public String getQj_psjdate() {
            return qj_psjdate;
        }

        public void setQj_psjdate(String qj_psjdate) {
            this.qj_psjdate = qj_psjdate;
        }

        public String getQj_renname() {
            return qj_renname;
        }

        public void setQj_renname(String qj_renname) {
            this.qj_renname = qj_renname;
        }

        public String getCustomerphoto() {
            return customerphoto;
        }

        public void setCustomerphoto(String customerphoto) {
            this.customerphoto = customerphoto;
        }

        public String getSj_state() {
            return sj_state;
        }

        public void setSj_state(String sj_state) {
            this.sj_state = sj_state;
        }

        public int getPackagecount() {
            return packagecount;
        }

        public void setPackagecount(int packagecount) {
            this.packagecount = packagecount;
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

        public String getBlankoutannal1() {
            return blankoutannal1;
        }

        public void setBlankoutannal1(String blankoutannal1) {
            this.blankoutannal1 = blankoutannal1;
        }

        public String getRefund1() {
            return refund1;
        }

        public void setRefund1(String refund1) {
            this.refund1 = refund1;
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

        public String getMqq() {
            return mqq;
        }

        public void setMqq(String mqq) {
            this.mqq = mqq;
        }

        public String getWqq() {
            return wqq;
        }

        public void setWqq(String wqq) {
            this.wqq = wqq;
        }

        public String getCssname() {
            return cssname;
        }

        public void setCssname(String cssname) {
            this.cssname = cssname;
        }

        public String getRefunddate1() {
            return refunddate1;
        }

        public void setRefunddate1(String refunddate1) {
            this.refunddate1 = refunddate1;
        }

        public String getArea1() {
            return area1;
        }

        public void setArea1(String area1) {
            this.area1 = area1;
        }

        public String getMbirthdate() {
            return mbirthdate;
        }

        public void setMbirthdate(String mbirthdate) {
            this.mbirthdate = mbirthdate;
        }

        public String getWbirthdate() {
            return wbirthdate;
        }

        public void setWbirthdate(String wbirthdate) {
            this.wbirthdate = wbirthdate;
        }

        public String getMidtype() {
            return midtype;
        }

        public void setMidtype(String midtype) {
            this.midtype = midtype;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getIntroducerid() {
            return introducerid;
        }

        public void setIntroducerid(String introducerid) {
            this.introducerid = introducerid;
        }

        public String getIntroducer_name() {
            return introducer_name;
        }

        public void setIntroducer_name(String introducer_name) {
            this.introducer_name = introducer_name;
        }

        public String getWeddingdate() {
            return weddingdate;
        }

        public void setWeddingdate(String weddingdate) {
            this.weddingdate = weddingdate;
        }

        public String getWIdtype() {
            return wIdtype;
        }

        public void setWIdtype(String wIdtype) {
            this.wIdtype = wIdtype;
        }

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
        }

        public String getMid1() {
            return mid1;
        }

        public void setMid1(String mid1) {
            this.mid1 = mid1;
        }

        public String getMid2() {
            return mid2;
        }

        public void setMid2(String mid2) {
            this.mid2 = mid2;
        }

        public String getCustomernote1() {
            return customernote1;
        }

        public void setCustomernote1(String customernote1) {
            this.customernote1 = customernote1;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCustomerid1() {
            return customerid1;
        }

        public void setCustomerid1(String customerid1) {
            this.customerid1 = customerid1;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVipdiscount() {
            return vipdiscount;
        }

        public void setVipdiscount(String vipdiscount) {
            this.vipdiscount = vipdiscount;
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

        public String getCameraman() {
            return cameraman;
        }

        public void setCameraman(String cameraman) {
            this.cameraman = cameraman;
        }

        public int getPhotostate() {
            return photostate;
        }

        public void setPhotostate(int photostate) {
            this.photostate = photostate;
        }

        public String getPhototype() {
            return phototype;
        }

        public void setPhototype(String phototype) {
            this.phototype = phototype;
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

        public int getSptstate() {
            return sptstate;
        }

        public void setSptstate(int sptstate) {
            this.sptstate = sptstate;
        }

        public String getKanbanriqi() {
            return kanbanriqi;
        }

        public void setKanbanriqi(String kanbanriqi) {
            this.kanbanriqi = kanbanriqi;
        }

        public String getKanbanshijian() {
            return kanbanshijian;
        }

        public void setKanbanshijian(String kanbanshijian) {
            this.kanbanshijian = kanbanshijian;
        }

        public int getKanbanstate() {
            return kanbanstate;
        }

        public void setKanbanstate(int kanbanstate) {
            this.kanbanstate = kanbanstate;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.mname);
            dest.writeString(this.mphone);
            dest.writeString(this.wname);
            dest.writeString(this.wphone);
            dest.writeString(this.viptype);
            dest.writeString(this.vipnum);
            dest.writeString(this.area);
            dest.writeString(this.iswhethercantake);
            dest.writeString(this.order_type_name);
            dest.writeString(this.shareshoplist);
            dest.writeString(this.ordertopic);
            dest.writeString(this.from_index);
            dest.writeInt(this.order_type);
            dest.writeString(this.isalreadytake);
            dest.writeInt(this.id1);
            dest.writeString(this.orderId);
            dest.writeString(this.customerid);
            dest.writeInt(this.order_type1);
            dest.writeString(this.consumption_type);
            dest.writeString(this.package_name);
            dest.writeString(this.targetdate);
            dest.writeString(this.targetime);
            dest.writeString(this.storeconsuitant1);
            dest.writeString(this.storeconsuitant2);
            dest.writeString(this.acceptor_address);
            dest.writeInt(this.total_money);
            dest.writeInt(this.payment_money);
            dest.writeInt(this.nopayment_money);
            dest.writeInt(this.supplementary_money);
            dest.writeInt(this.bargain_money);
            dest.writeString(this.allfinishfate);
            dest.writeString(this.payoervedate);
            dest.writeString(this.firstpaydate);
            dest.writeInt(this.dressmony);
            dest.writeInt(this.sellmoney);
            dest.writeString(this.ordernote);
            dest.writeString(this.vipnum1);
            dest.writeString(this.shop_order_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.shop_name);
            dest.writeString(this.create_time);
            dest.writeInt(this.blankoutannal);
            dest.writeString(this.blankoutannalremark);
            dest.writeInt(this.iswhethercantake1);
            dest.writeInt(this.isalreadytake1);
            dest.writeString(this.whethercantakedate);
            dest.writeString(this.alreadytakedate);
            dest.writeString(this.take_takaddress);
            dest.writeInt(this.dresstotal);
            dest.writeInt(this.dresspay);
            dest.writeInt(this.dressnonpay);
            dest.writeInt(this.uploadoriginal);
            dest.writeInt(this.uploaddesign);
            dest.writeInt(this.makeuptotal);
            dest.writeInt(this.makeuppay);
            dest.writeInt(this.makeupnonpay);
            dest.writeInt(this.spImport_photocount);
            dest.writeInt(this.spcount);
            dest.writeInt(this.spbook_photocount);
            dest.writeString(this.sendtime);
            dest.writeString(this.getDate);
            dest.writeString(this.shareshoplist1);
            dest.writeString(this.opershop);
            dest.writeString(this.turntype);
            dest.writeInt(this.server1);
            dest.writeInt(this.server2);
            dest.writeInt(this.server3);
            dest.writeInt(this.server4);
            dest.writeInt(this.server5);
            dest.writeInt(this.server6);
            dest.writeInt(this.server7);
            dest.writeInt(this.refund);
            dest.writeString(this.refunddate);
            dest.writeInt(this.reservelock);
            dest.writeInt(this.addlock);
            dest.writeString(this.order_type_name1);
            dest.writeString(this.ordertopic1);
            dest.writeString(this.customernote);
            dest.writeInt(this.qj_producttype);
            dest.writeString(this.qj_sjgetDate);
            dest.writeString(this.qj_bzname);
            dest.writeInt(this.qj_produceover);
            dest.writeString(this.qj_produceoverday);
            dest.writeInt(this.sj_sendnumber);
            dest.writeString(this.qj_staymark);
            dest.writeString(this.qj_staymarkday);
            dest.writeString(this.qj_staymarkbz);
            dest.writeString(this.storeconsuitant3);
            dest.writeInt(this.indoordresscount);
            dest.writeInt(this.outerdresscount);
            dest.writeInt(this.seadresscount);
            dest.writeInt(this.travelresscount);
            dest.writeString(this.dresstheme);
            dest.writeString(this.oeder_newclass);
            dest.writeInt(this.paizhaocount);
            dest.writeInt(this.rucecount);
            dest.writeInt(this.jingxiucount);
            dest.writeString(this.qj_psjdate);
            dest.writeString(this.qj_renname);
            dest.writeString(this.customerphoto);
            dest.writeString(this.sj_state);
            dest.writeInt(this.packagecount);
            dest.writeString(this.xingming);
            dest.writeString(this.shouji);
            dest.writeString(this.blankoutannal1);
            dest.writeString(this.refund1);
            dest.writeString(this.mwechat);
            dest.writeString(this.wwechat);
            dest.writeString(this.mqq);
            dest.writeString(this.wqq);
            dest.writeString(this.cssname);
            dest.writeString(this.refunddate1);
            dest.writeString(this.area1);
            dest.writeString(this.mbirthdate);
            dest.writeString(this.wbirthdate);
            dest.writeString(this.midtype);
            dest.writeString(this.mid);
            dest.writeString(this.introducerid);
            dest.writeString(this.introducer_name);
            dest.writeString(this.weddingdate);
            dest.writeString(this.wIdtype);
            dest.writeString(this.wid);
            dest.writeString(this.mid1);
            dest.writeString(this.mid2);
            dest.writeString(this.customernote1);
            dest.writeString(this.email);
            dest.writeString(this.customerid1);
            dest.writeString(this.address);
            dest.writeString(this.vipdiscount);
            dest.writeString(this.photodate);
            dest.writeString(this.phototime);
            dest.writeString(this.cameraman);
            dest.writeInt(this.photostate);
            dest.writeString(this.phototype);
            dest.writeString(this.selectday);
            dest.writeString(this.selectTime);
            dest.writeInt(this.sptstate);
            dest.writeString(this.kanbanriqi);
            dest.writeString(this.kanbanshijian);
            dest.writeInt(this.kanbanstate);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.mname = in.readString();
            this.mphone = in.readString();
            this.wname = in.readString();
            this.wphone = in.readString();
            this.viptype = in.readString();
            this.vipnum = in.readString();
            this.area = in.readString();
            this.iswhethercantake = in.readString();
            this.order_type_name = in.readString();
            this.shareshoplist = in.readString();
            this.ordertopic = in.readString();
            this.from_index = in.readString();
            this.order_type = in.readInt();
            this.isalreadytake = in.readString();
            this.id1 = in.readInt();
            this.orderId = in.readString();
            this.customerid = in.readString();
            this.order_type1 = in.readInt();
            this.consumption_type = in.readString();
            this.package_name = in.readString();
            this.targetdate = in.readString();
            this.targetime = in.readString();
            this.storeconsuitant1 = in.readString();
            this.storeconsuitant2 = in.readString();
            this.acceptor_address = in.readString();
            this.total_money = in.readInt();
            this.payment_money = in.readInt();
            this.nopayment_money = in.readInt();
            this.supplementary_money = in.readInt();
            this.bargain_money = in.readInt();
            this.allfinishfate = in.readString();
            this.payoervedate = in.readString();
            this.firstpaydate = in.readString();
            this.dressmony = in.readInt();
            this.sellmoney = in.readInt();
            this.ordernote = in.readString();
            this.vipnum1 = in.readString();
            this.shop_order_name = in.readString();
            this.shop_code = in.readString();
            this.shop_name = in.readString();
            this.create_time = in.readString();
            this.blankoutannal = in.readInt();
            this.blankoutannalremark = in.readString();
            this.iswhethercantake1 = in.readInt();
            this.isalreadytake1 = in.readInt();
            this.whethercantakedate = in.readString();
            this.alreadytakedate = in.readString();
            this.take_takaddress = in.readString();
            this.dresstotal = in.readInt();
            this.dresspay = in.readInt();
            this.dressnonpay = in.readInt();
            this.uploadoriginal = in.readInt();
            this.uploaddesign = in.readInt();
            this.makeuptotal = in.readInt();
            this.makeuppay = in.readInt();
            this.makeupnonpay = in.readInt();
            this.spImport_photocount = in.readInt();
            this.spcount = in.readInt();
            this.spbook_photocount = in.readInt();
            this.sendtime = in.readString();
            this.getDate = in.readString();
            this.shareshoplist1 = in.readString();
            this.opershop = in.readString();
            this.turntype = in.readString();
            this.server1 = in.readInt();
            this.server2 = in.readInt();
            this.server3 = in.readInt();
            this.server4 = in.readInt();
            this.server5 = in.readInt();
            this.server6 = in.readInt();
            this.server7 = in.readInt();
            this.refund = in.readInt();
            this.refunddate = in.readString();
            this.reservelock = in.readInt();
            this.addlock = in.readInt();
            this.order_type_name1 = in.readString();
            this.ordertopic1 = in.readString();
            this.customernote = in.readString();
            this.qj_producttype = in.readInt();
            this.qj_sjgetDate = in.readString();
            this.qj_bzname = in.readString();
            this.qj_produceover = in.readInt();
            this.qj_produceoverday = in.readString();
            this.sj_sendnumber = in.readInt();
            this.qj_staymark = in.readString();
            this.qj_staymarkday = in.readString();
            this.qj_staymarkbz = in.readString();
            this.storeconsuitant3 = in.readString();
            this.indoordresscount = in.readInt();
            this.outerdresscount = in.readInt();
            this.seadresscount = in.readInt();
            this.travelresscount = in.readInt();
            this.dresstheme = in.readString();
            this.oeder_newclass = in.readString();
            this.paizhaocount = in.readInt();
            this.rucecount = in.readInt();
            this.jingxiucount = in.readInt();
            this.qj_psjdate = in.readString();
            this.qj_renname = in.readString();
            this.customerphoto = in.readString();
            this.sj_state = in.readString();
            this.packagecount = in.readInt();
            this.xingming = in.readString();
            this.shouji = in.readString();
            this.blankoutannal1 = in.readString();
            this.refund1 = in.readString();
            this.mwechat = in.readString();
            this.wwechat = in.readString();
            this.mqq = in.readString();
            this.wqq = in.readString();
            this.cssname = in.readString();
            this.refunddate1 = in.readString();
            this.area1 = in.readString();
            this.mbirthdate = in.readString();
            this.wbirthdate = in.readString();
            this.midtype = in.readString();
            this.mid = in.readString();
            this.introducerid = in.readString();
            this.introducer_name = in.readString();
            this.weddingdate = in.readString();
            this.wIdtype = in.readString();
            this.wid = in.readString();
            this.mid1 = in.readString();
            this.mid2 = in.readString();
            this.customernote1 = in.readString();
            this.email = in.readString();
            this.customerid1 = in.readString();
            this.address = in.readString();
            this.vipdiscount = in.readString();
            this.photodate = in.readString();
            this.phototime = in.readString();
            this.cameraman = in.readString();
            this.photostate = in.readInt();
            this.phototype = in.readString();
            this.selectday = in.readString();
            this.selectTime = in.readString();
            this.sptstate = in.readInt();
            this.kanbanriqi = in.readString();
            this.kanbanshijian = in.readString();
            this.kanbanstate = in.readInt();
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
