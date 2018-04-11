package com.suxuantech.erpsys.nohttp;

import com.blankj.utilcode.util.EncryptUtils;

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
    public static String TOKEN = "%5e******%5e";

    public static String getFullUrl(String template, Object... replace) {
        return  TESTIP + String.format(template, replace);
     //   return URLEncoder.encode(TESTIP + String.format(template, replace));
    }
    public static class SignateInfo {
        public String signate;
        public int random;
        public  long currentTimeMillis;
        public SignateInfo(String signate, int random, long currentTimeMillis) {
            this.signate = signate;
            this.random = random;
            this.currentTimeMillis = currentTimeMillis;
        }
    }

    /**
     * 签名 获取
     * @return
     */
    public static SignateInfo getSignate() {
        String signate = "oUWKYeqCEojOvbmsynvWTctJSAVeoMZv";
        int Min = 99999;
        int Max = 999999;
        int random = Min + (int) (Math.random() * ((Max - Min)));
        long currentTimeMillis = System.currentTimeMillis();
        signate = EncryptUtils.encryptMD5ToString(currentTimeMillis + random + signate);
       return  new  SignateInfo(signate,random,currentTimeMillis);
    }
    public static String LOGIN = "/SXWebErpAppStaff/SX_CustomerPhotoInfoDay?Token=%s&userID=%s&userPwd=%s";
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
    public static String OUTLETS_RECEPTION = "/SXWebErpAppStaff/SX_Menshi?Token=%s&code=%s";

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
