package com.suxuantech.erpsys.nohttp;

import com.blankj.utilcode.util.EncryptUtils;
import com.suxuantech.erpsys.App;

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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 统一的网络接口地址
 */

public class Contact {
    private Contact() {        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //http://192.168.0.15:8033/SXWebErpAppStaff/SX_GetOrderNum?Token=SX&code=00
    public static String TESTIP = "http://192.168.0.15:8033";
    public static String PHP_PREFIX = "http://oa.erp.suxuantech.cn/api.php?m=login&a=";
    public static String TOKEN = "^******^";

    public static String getFullUrl(String template, Object... replace) {
        if (replace == null) {
            return template;
        }
        if (App.ISDEBUG) {
            for (int i = 0; i < replace.length; i++) {
                replace[i] = replace[i].toString();
            }
            com.yanzhenjie.nohttp.Logger.d(template.startsWith("%s") ? (String.format(template, replace)) : TESTIP + (String.format(template, replace)));
        }
        //把所有类型转为String,并且编码
        for (int i = 0; i < replace.length; i++) {
            if (template.startsWith("%s") && replace[i].toString().startsWith("http")) {
                continue;
            } else {
                replace[i] = URLEncoder.encode(replace[i].toString());
            }
        }
        return template.startsWith("%s") ? (String.format(template, replace)) : TESTIP + (String.format(template, replace));
    }


    public static class SignateInfo {
        public String signature;
        public int random;
        public long currentTimeMillis;

        public SignateInfo(String signature, int random, long currentTimeMillis) {
            this.signature = signature;
            this.random = random;
            this.currentTimeMillis = currentTimeMillis;
        }
    }

    /**
     * 签名获取
     *
     * @return
     */
    public static SignateInfo getSignate() {
        String signature = "oUWKYeqCEojOvbmsynvWTctJSAVeoMZv";
        int Min = 99999;
        int Max = 999999;
        int random = Min + (int) (Math.random() * ((Max - Min)));
        //java时间戳是13位的毫秒,1000毫秒=1秒,所以需要除以一千 得到的是秒的时间戳也就是10位的
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        stringBuffer.append(random);
        stringBuffer.append(signature);
        signature = EncryptUtils.encryptMD5ToString(stringBuffer.toString()).toLowerCase();
        return new SignateInfo(signature, random, currentTimeMillis);
    }

    /**
     * 登录接口
     */
    public static String LOGIN = "/SXWebErpAppStaff/SX_LoginCheck?Token=%s&userID=%s&userPwd=%s";
    /**
     * 今日拍照
     */
    public static String Today_TakePhoto = "/SXWebErpAppStaff/SX_CustomerPhotoInfoDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日礼服
     */
    public static String TODAY_FULL_DRESS = "/SXWebErpAppStaff/SX_CustomerDressCountDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日取件
     */
    public static String TODAY_PICK_UP_PHOTO = "/SXWebErpAppStaff/SX_CustomerGetTakeawayItemDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";

    /**
     * 今日化妆
     */
    public static String TODAY_MAKE_UP = "/SXWebErpAppStaff/SX_CustomerMakeupDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日预约进店
     */
    public static String TODAY_APPOINTMENT_TIME = "/SXWebErpAppStaff/SX_GuestInfoDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 今日选片
     */
    public static String TODAY_OPTION_PANEL = "/SXWebErpAppStaff/SX_CustomerXuanPianDay?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 客户意向
     */
    public static String CUSTOMER_INTENTION = "/SXWebErpAppStaff/SX_IntentionNameSet?Token=%s&Code=%s";
    /**
     * 客户来源
     */
    public static String CUSTOMER_SOURCE = "/SXWebErpAppStaff/SX_CusNameSet?Token=%s&Code=%s";

    /**
     * 进客登记
     */
    public static String GUEST_REGISTRATION = "/SXWebErpAppStaff/SX_OpenGuestInfo?Token=%s";


    /**
     * 获得订单编号
     */
    public static String ORDER_NUMBER = "/SXWebErpAppStaff/SX_GetOrderNum?Token=%s&code=%s&SN=%s";
    /**
     * 获得消费类型设定
     */
    public static String CONSUMPTION_TYPE = "/SXWebErpAppStaff/SX_ConsumptionKindSet?Token=%s&code=%s";
    /**
     * 接单点
     */
    public static String ORDER_RECEIVING_SITE = "/SXWebErpAppStaff/SX_AOAcceptorAddress?Token=%s&code=%s";

    /**
     * 拍摄店(实体店)
     */
    public static String PHOTO_SHOP = "/SXWebErpAppStaff/SX_BrandPhotoShopNameSet?Token=%s&BrandName=%s";

    /**
     * 门市接待
     */
    public static String OUTLETS_RECEPTION = "/SXWebErpAppStaff/SX_MenShiSet?Token=%s&Code=%s";

    /**
     * 网销人
     */
    public static String RECEPTION_MARKET = "/SXWebErpAppStaff/SX_WangXiaoMenShiSet?Token=%s&Code=%s";

    /**
     * 客户分区
     */
    public static String CUSTOMER_ZONE = "/SXWebErpAppStaff/SX_A0AreaSet?Token=%s&code=%s";

    /**
     * 获得包套
     */
    public static String PACKAGE = "/SXWebErpAppStaff/SX_PackageSet?Token=%s&code=%s";

    /**
     * 获得产品
     */
    public static String PRODUCT = "/SXWebErpAppStaff/SX_ConsumptionItmsSet?Token=%s&code=%s";
    /**
     * 开婚纱单
     */
    public static String OPEN_WEDDING_ORDER = "/SXWebErpAppStaff/SX_Open_WeddingOrder?Token=%s&package_name=%s&cid=%s&Code=%s&shopname=%s&PZCode=%s&PZshopname=%s&PZCode1=%s&PZshopname1=%s&zktype=%s&staffid=%s";
    /**
     * 订单搜索
     */

    public static String SEARCH_ORDER_NEW = "/SX_CrmApi/Sel_CustomerOrderList?Token=%s&CustomerType=%s&brandid=%s&startdate=%s&enddate=%s&orderInfo=%s";


    /**
     * 订单搜索
     */
    public static String SEARCH_ORDER = "/SXWebErpAppStaff/SX_InquireCustomerInfo?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s";
    /**
     * 新单类型
     */
    public static String NEW_ORDER_TYPE = "/SXWebErpAppStaff/SX_OrderTypeSet?Token=%S&Code=%S&consumptiontype=%S";
    /**
     * 摄影主题
     */
    public static String SHOOT_THEME = "/SXWebErpAppStaff/SX_TopicSet?Token=%s&Code=%s";

    /**
     * 礼服主题
     */
    public static String DRESS_THEME = "/SXWebErpAppStaff/SX_DressThemesetSet?Token=%s&Code=%s";
    /**
     * 进客搜索
     */
    public static String INQUIRE_GUEST_INFO = "/SXWebErpAppStaff/SX_InquireGuestInfo?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s&JKID=%s&salesstaff=%s&djstaff=%s";
    /**
     * 首页数据
     */
    public static String HOME_CUSTOMER_COUNT = "/SXWebErpAppStaff/SX_CustomerDayCount?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 修改进店状态
     * 进客ID
     * 进店类型（数字类型1进店进客，2进店非进客）
     */
    public static String INTO_STORE_STATUS = "/SXWebErpAppStaff/SX_AlterIntoStore?Token=%s&JKID=%s&JDType=%s";
    /**
     * 修改未进店状态
     */
    public static String SB = "/SXWebErpAppStaff/SX_AlterNoIntoStore?Token=%s&JKID=%s&Statusremark=%s";
    /**
     * 修改流失状态
     */
    public static String RUN_AWAY = "/SXWebErpAppStaff/SX_AlterLoss?Token=%s&JKID=%s&Statusremark=%s";

    /**
     * 获取客户产品.
     */
    public static String CUSTOMER_PRODUCT = "/SXWebErpAppStaff/SX_CustomerConsumptionContent?Token=%S&orderid=%S&Code=%S";
    /**
     * 客户礼服资料获取
     */
    public static String CUSTOMER_DRESS = "/SXWebErpAppStaff/SX_CustomerDressCount?Token=%s&orderid=%s&Code=%s";
    /***
     *客户取件资料
     */
    public static String CUSTOMER_TAKEDATA = "/SXWebErpAppStaff/SX_CustomerGetTakeawayItem?Token=%s&orderid=%s&Code=%s";


    /**
     * 获取进店客户总数
     */

    public static String CUSTOMER_INTO_STORE_COUNT = "/SXWebErpAppStaff/SX_InquireGuestInfoCount?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s&salesstaff=%s&djstaff=%s";
    /**
     * 未标记客户进店日期的总数
     */
    public static String UNREMARK_CUSTOMER_INTO_STORE_DATE_COUNT = "/SXWebErpAppStaff/SX_DJRQWBJYYJDGuestInfoCount?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s&salesstaff=%s&djstaff=%s";
    /**
     * 客户选片信息
     */
    public static String CUSTOMER_SELECTED_PICTURE = "/SXWebErpAppStaff/SX_CustomerXuanPian?Token=%s&orderid=%s&Code=%s";
    /**
     * 客户摄影资料
     */
    public static String CUSTOMER_PHOTO_INFO = "/SXWebErpAppStaff/SX_CustomerPhotoInfo?Token=%s&orderid=%s&Code=%s";
    /**
     * 首页数据汇总
     */
    public static String HOME_SUM = "/SXWebErpAppStaff/SX_DataSummary?Token=%s&StartDate=%s&EndDate=%s&Code=%s";
    /**
     * 添加包套
     */
    public static String ADD_PACKAGE = "/SXWebErpAppStaff/SX_AddPackage?Token=%s&orderid=%s&customerid=%s&package_name=%s&pid=%s&Code=%s&shopname=%s&txprice=%s&isyanzheng=%s&brandid=%s";


    /**
     * 添加产品
     */
    public static String ADD_PRODUCTS = "/SXWebErpAppStaff/SX_AddPackage?Token=%s&orderid=%s&customerid=%s&package_name=%s&pid=%s&Code=%s&shopname=%s&txprice=%s&isyanzheng=%s&brandid=%s";
    /**
     * 某一个月可拍照排程情况
     */
    public static String PHOTO_SCHEME_BY_MONTH = "/SXWebErpAppStaff/SX_PhotoPaiChengMonthinfo?Token=%s&vPcday=%s&Code=%s";
    /**
     * 当天可拍照排程
     */
    public static String PHOTO_SCHEME_BY_DAY = "/SXWebErpAppStaff/SX_PhotoPaiChengDayinfo?Token=%s&vPcday=%s&Code=%s";

    /**
     * 登录人权限
     */
    public static String LOGIN_PERMISSION = "/SXWebErpAppStaff/SX_HQFunction?Token=%s&worktype=%s&gwcode=%s&Code=%s";
    /**
     * 获得客户化妆资料信息
     */
    public static String MAKE_UP = "/SXWebErpAppStaff/SX_CustomerMakeupCount?Token=%s&orderid=%s&Code=%s";
    /**
     * 获得客户化妆产品明细资料信息
     */
    public static String MAKE_UP_DATEILES = "/SXWebErpAppStaff/SX_CustomerMakeupMingXi?Token=%s&orderId=%s&MakeupId=%s&Code=%s";
    /**
     * 订单付款历史列表
     */
    public static String PAYMENT = "/SXWebErpAppStaff/SX_Paymentcontent?Token=%s&orderid=%s&Code=%s";
    /**
     * 今日收款
     */
    public static String TODAY_COLLECTION_MONEY = "/SXWebErpAppStaff/SX_MSPaymentcontent?Token=%s&StartDate=%s&EndDate=%s&pageIndex=%s&pageSize=%s&Code=%s";
    /**
     * 添加产品
     */
    public static String ADD_PRODUCT = "/SXWebErpAppStaff/SX_AddConsumptioncontent?Token=%s&orderid=%s&customerid=%s&cid=%s&issources=%s&isyanzheng=%s&Code=%s&shopname=%s&brandid=%s";
    /**
     * 今日选片排程
     */
    public static String TODAY_PICTRUE_SCHEME = "/SXWebErpAppStaff/SX_SPPaiChengDayinfo?Token=%s&vPcday=%s&Code=%s";
    /**
     * 本月选片可排程排程
     */
    public static String PICTRUE_SCHEME_BY_MONTH = "/SXWebErpAppStaff/SX_SPPaiChengMonthinfo?Token=%s&vPcday=%s&Code=%s";
    /**
     * 拍照排程锁定
     */
    public static String LOCK_PHOTOGRAPH_SCHEME = "/SXWebErpAppStaff/SX_PhotoPaiChengLock?Token=%s&pcid=%s&Code=%s";
    /**
     * 拍照解锁
     */
    public static String UNLOCK_PHOTOGRAPH_SCHEME = "/SXWebErpAppStaff/SX_PhotoPaiChengOpenLock?Token=%s&pcid=%s&Code=%s";
    /**
     * 选片锁定排程
     */
    public static String LOCK_OPTION_PANEL_SCHEME = "/SXWebErpAppStaff/SX_SPPaiChengLock?Token=%s&pcid=%s&Code=%s";
    /**
     * 选片解锁排程
     */
    public static String UNLOCK_OPTION_PANEL_SCHEME = "/SXWebErpAppStaff/SX_SPPaiChengOpenLock?Token=%s&pcid=%s&Code=%s";
    /**
     * 新增拍照排程
     */
    public static String ADD_PHOTOGRAPH_SCHEME = "/SXWebErpAppStaff/SX_AddPhotoPaiCheng?Token=%s&pcday=%s&Code=%s";
    /**
     * 修改拍照排程
     */
    public static String CHANGE_PHOTOGRAPH_SCHEME = "/SXWebErpAppStaff/SX_UpdatePhotoPaiCheng?Token=%s&pcday=%s&Code=%s&oldpcid=%s";
    /**
     * 删除拍照排程
     */
    public static String DELETE_PHOTOGRAPH_SCHEME = "/SXWebErpAppStaff/SX_DelPhotoPaiCheng?Token=%s&pcid=%s&photoid=%s";
    /**
     * 新增选片排程
     */
    public static String ADD_OPTION_PANEL_SCHEME = "/SXWebErpAppStaff/SX_AddSPPaiCheng?Token=%s&pcday=%s&Code=%s";
    /**
     * 修改选片排程
     */
    public static String CHANGE_OPTION_PANEL_SCHEME = "/SXWebErpAppStaff/SX_UpdateSPPaiCheng?Token=%s&pcday=%s&Code=%s&oldpcid=%s";
    /**
     * 删除选片排程
     */
    public static String DELETE_OPTION_PANEL_SCHEME = "/SXWebErpAppStaff/SX_DelSPPaiCheng?Token=%s&pcid=%s&spid=%s";

    /**
     * 选片类型
     */
    public static String OPTION_PANEL_TYPE_SET = "/SXWebErpAppStaff/SX_SelectTypeSet?Token=%s&Code=%s";

    /**
     * 拍照类型
     */
    public static String PHOTO_TYPE_SET = "/SXWebErpAppStaff/SX_PhotoTypeSet?Token=%s&Code=%s";
    /**
     * 删除产品
     */
    public static String DELETE_PRODUCT = "/SXWebErpAppStaff/SX_DelConsumptioncontent?Token=%s&orderid=%s&cid=%s&Code=%s&brandid=%s";
    /**
     * 删除包套
     */
    public static String DELETE_PACKAGE = "/SXWebErpAppStaff/SX_DelAllConsumptioncontent?Token=%s&orderid=%s&Code=%s&brandid=%s";
    /**
     * 删除产品验证(验证包套是否锁定,是否完成,是否作废)
     */
    public static String DELETE_CHEKE = "/SXWebErpAppStaff/SX_DinDanYanZheng?Token=%s&orderid=%s&Code=%s";
    /**
     * 一销,二销锁定验证(无用)
     */
    public static String PRODUCT_LOCK = "/SXWebErpAppStaff/SX_LockZheng?Token=%s&orderid=%s&isye=%s&Code=%s";

    /**
     * php登录
     */
    public static String PHP_LOGIN = "%scheck";
    /**
     * 可切换店面
     */
    public static String CAN_EXECUTE_SHOP = "/SXWebErpAppStaff/SX_OpertionShop?Token=%s&Staffid=%s";
    /**
     * 获取集团下的事业部列表
     */
    public static String BUSINSSUNIT = "%sgetBrandList";
    /**
     * 获取事业部下的店面列表
     * brandclass_id：事业部id
     */
    public static String STORE_BY_BUSINSSUNIT = "%sgetShopList";
    /**
     * 获取部门
     * (grade_type:部门所属：1集团，2品牌，3店面)
     * (1:’’,2:brandclass_id,3:shop_code)
     */
    public static String DEPARTMENT = "%sgetDepartmentList";
    /**
     * 获取人员
     * (grade_type:部门所属：1集团，2品牌，3店面，4部门)
     * (1:’’,2:brandclass_id,3:shop_code，4:department_id)
     */
    public static String  CONTACTS = "%sgetStaffList";
    /**
     * 通讯录搜索
     */
    public static String SEARCH_PERSON = "%sseacherStaff";
    /**
     * 选片排程搜索
     */
    public static String  OPTION_PANEL_SCHEME="/SXWebErpAppStaff/SX_CXXPPaiChengCusInfo?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s";

    /**
     * 拍照排程搜索
     */
    public static String  PHOTO_SCHEME_SEARCH="/SXWebErpAppStaff/SX_CXPhotoPaiChengCusInfo?Token=%s&StartDate=%s&EndDate=%s&orderid=%s&pageIndex=%s&pageSize=%s&Code=%s";
    /**
     * 获得今日进客资料信息(首页的今日客资量)
     */
    public static String  SEARCH_TODAY_CUSTOMER="/SXWebErpAppStaff/SX_JKGuestInfoMX?Token=%s&StartDate=%s&EndDate=%s&pageIndex=%s&pageSize=%s&Code=%s";
    /**
     * 进客登记(根据进客类型)当日进客登记不同类型数据
     */
    public static String  RegisterType="/SXWebErpAppStaff/SX_InquireGuestInfoDay?Token=%s&StartDate=%s&EndDate=%s&JKTyep=%s&pageIndex=%s&pageSize=%s&Code=%s&salesstaff=%s&djstaff=%s";
}
