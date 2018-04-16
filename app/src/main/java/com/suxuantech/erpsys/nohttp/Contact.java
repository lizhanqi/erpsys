package com.suxuantech.erpsys.nohttp;

import com.blankj.utilcode.util.EncryptUtils;

import java.net.URLEncoder;

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
 * @author Created by 李站旗 on 2017/12/7 0007 14:41 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 统一的网络接口地址
 */

public class Contact {
    private Contact() {        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //http://192.168.0.15:8033/SXWebErpAppStaff/SX_GetOrderNum?Token=SX&code=00
    public static String TESTIP = "http://192.168.0.15:8033";
    public static String TOKEN = "^******^";
    public static String getFullUrl(String template, Object... replace) {
        //return  TESTIP + String.format(template, replace);
        if (replace==null){
            return  template;
        }
        //把所有类型转为String,并且编码
        for(int i=0;i<replace.length;i++){
            replace[i] = URLEncoder.encode( replace[i] .toString());
        }
        return   TESTIP + (String.format(template, replace)) ;
    }

    public static class SignateInfo {
        public String signature;
        public int random;
        public  long currentTimeMillis;
        public SignateInfo(String signature, int random, long currentTimeMillis) {
            this.signature = signature;
            this.random = random;
            this.currentTimeMillis = currentTimeMillis;
        }
    }
    /**
     * 签名获取
     * @return
     */
    public static SignateInfo getSignate() {
        String signature = "oUWKYeqCEojOvbmsynvWTctJSAVeoMZv";
        int Min = 99999;
        int Max = 999999;
        int random = Min + (int) (Math.random() * ((Max - Min)));
        //java时间戳是13位的毫秒,1000毫秒=1秒,所以需要除以一千 得到的是秒的时间戳也就是10位的
         long currentTimeMillis =System.currentTimeMillis() /1000 ;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        stringBuffer.append(random);
        stringBuffer.append(signature);
        signature = EncryptUtils.encryptMD5ToString(stringBuffer.toString()).toLowerCase();
       return  new  SignateInfo(signature,random,currentTimeMillis);
    }
    public static String LOGIN = "/SXWebErpAppStaff/SX_CustomerPhotoInfoDay?Token=%s&userID=%s&userPwd=%s";
    /**
     * 今日拍照
     */
   public static String Today_TakePhoto="/SXWebErpAppStaff/SX_CustomerPhotoInfoDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日取件
     */
    public static String TODAY_FULL_DRESS="/SXWebErpAppStaff/SX_CustomerPhotoInfoDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日礼服
     */
    public static String TODAY_PICK_UP_PHOTO="/SXWebErpAppStaff/SX_CustomerDressCountDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";

    /**
     *进入化妆
     */
    public static String TODAY_MAKE_UP="/SXWebErpAppStaff/SX_CustomerMakeupDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s" ;
    /**
     * 今日预约进店
     */
    public static String TODAY_APPOINTMENT_TIME ="/SXWebErpAppStaff/SX_GuestInfoDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s" ;
    /**
     * 今日选片
     */
    public static String TODAY_OPTION_PANEL ="/SXWebErpAppStaff/SX_CustomerXuanPianDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s" ;
    /**
     * 客户意向
     */
        public static String CUSTOMER_INTENTION ="/SXWebErpAppStaff/SX_IntentionNameSet?Token=%s&Code=%s" ;
    /**
     * 客户来源
     */
    public static String CUSTOMER_SOURCE="/SXWebErpAppStaff/SX_CusNameSet?Token=%s&Code=%s";

    /**
     *进客登记
     */
    public static String GUEST_REGISTRATION="/SXWebErpAppStaff/SX_OpenGuestInfo?Token=%s";


    /**
     * 获得订单编号
     */
    public static String ORDER_NUMBER = "/SXWebErpAppStaff/SX_GetOrderNum?Token=%s&code=%s";
    /**
     * 获得消费类型设定
     */
    public static String CONSUMPTION_TYPE = "/SXWebErpAppStaff/SX_ConsumptionKindSet?Token=%s&code=%s";
    /**
     * 接单点
     */
    public static String ORDER_RECEIVING_SITE = "/SXWebErpAppStaff/SX_AOAcceptorAddress?Token=%s&code=%s";

    /**
     * 门市接待
     */
    public static String OUTLETS_RECEPTION = "/SXWebErpAppStaff/SX_MenShiSet?Token=%s&Code=%s";

    /**
     * 客户分区
     */
    public static String CUSTOMER_ZONE = "/SXWebErpAppStaff/SX_A0AreaSet?Token=%s&code=%s";

    /**
     * 获得包套
     */
    public static String PACKAGE = "/SXWebErpAppStaff/SX_SelPageNameToxi?Token=%s&code=%s";

    /**
     * 获得包套
     */
    public static String PRODUCT = "/SXWebErpAppStaff/SX_SelPageConsumptionItms?Token=%s&code=%s";
    /**
     * 开婚纱单
     */
    public static String OPEN_WEDDING_ORDER = "/SXWebErpAppStaff/SX_Open_WeddingOrder?Token=%s&package_name=%s&pid=%s&pp=%s&code=%s&shopname=%s";
    /**
     * 订单搜索
     */
    public static String SEARCH_ORDER = "/SXWebErpAppStaff/SX_CustomerInfo?Token=%s&orderid=%s&ckey=%s&bTime=%s&eTime=%s&pageIndex=%d&pageSize=%d";
}
