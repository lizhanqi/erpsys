package com.suxuantech.erpsys.entity;

import android.os.Parcel;
import android.os.Parcelable;

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
 * @author Created by 李站旗 on 2018/4/23 0023 11:47 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class RegisterEntity extends  BaseResult2 <RegisterEntity.DataBean>{

    /**
     * Code : 200
     * Data : [{"id":4,"customer_number":"SY18011900003","customer_name":"宋仲基","customer_sex":"男","customer_tel":"13146929215","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"北京市朝阳区","mate_name":"宋慧乔","mate_sex":"女","mate_tel":"13146929145","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"婚纱照","customer_cource":"自然进店","customer_intention":"一般","customer_orderaddress":"王府井接单点","customer_area":"","op_signinfo":null,"op_signday":null,"xingming":"宋仲基 宋慧乔","shouji":"13146929215 13146929145","customer_birthday":"19810120","mate_birthday":"19810128","wedding_date":"2018-02-01 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-01-31 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"流失","statusremark":"","shop_codeZD":null,"shop_nameZD":null,"from_index":"15163246484381","customer_from":"CRM","brandid":1},{"id":5,"customer_number":"SY18011900003","customer_name":"宋仲基","customer_sex":"男","customer_tel":"13146929256","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"宋慧乔","mate_sex":"女","mate_tel":"13146929589","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"","customer_cource":"自然进店","customer_intention":"一般","customer_orderaddress":"王府井接单点","customer_area":"","op_signinfo":null,"op_signday":null,"xingming":"宋仲基 宋慧乔","shouji":"13146929256 13146929589","customer_birthday":"19810219","mate_birthday":"19811219","wedding_date":"2018-01-16 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-01-01 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163268643570","customer_from":"CRM","brandid":1},{"id":6,"customer_number":"SY18011900003","customer_name":"陈晓","customer_sex":"男","customer_tel":"13146929874","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"北京市海淀区","mate_name":"陈妍希","mate_sex":"","mate_tel":"13146929548","mate_wechat":"","mate_weibo":null,"mate_qq":"","co 04-23 03:46:20.222 19975-20732/com.suxuantech.erpsys I/NoHttp: nsultation_type":"艺术照","customer_cource":"自然进店","customer_intention":"一般","customer_orderaddress":"西单接单点","customer_area":"","op_signinfo":null,"op_signday":null,"xingming":"陈晓 陈妍希","shouji":"13146929874 13146929548","customer_birthday":"19810921","mate_birthday":"19821001","wedding_date":"2018-02-22 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-17 00:00:00.000","syjd_day":null,"yp_day":"2018-01-31 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163276111311","customer_from":"CRM","brandid":1},{"id":7,"customer_number":"SY18011900004","customer_name":"全智贤","customer_sex":"女","customer_tel":"18612564756","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"北京市丰台区","mate_name":"","mate_sex":"","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"三亚旅拍","customer_cource":"网络订单","customer_intention":"一般","customer_orderaddress":"","customer_area":"VIP区","op_signinfo":null,"op_signday":null,"xingming":"全智贤 ","shouji":"18612564756 ","customer_birthday":"19811019","mate_birthday":null,"wedding_date":"2018-06-22 00:00:00.000","dj_staff":"尹一苹","fp_staff":"小飞","sales_staff":"姜明","dj_day":"2018-01-19 00:00:00.000","fp_day":"2018-01-22 00:00:00.000","yjd_day":"2018-01-20 00:00:00.000","syjd_day":null,"yp_day":"2018-01-26 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163278032721","customer_from":"CRM","brandid":1},{"id":9,"customer_number":"SY18011900004","customer_name":"王立宏","customer_sex":"女","customer_tel":"13146929789","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"","mate_sex":"女","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"艺术照","customer_cource":"婚博会","customer_intention":"有兴趣","customer_orderaddress":"龙湖接单点","customer_area":"VIP区","op_signinfo":null,"op_signday":null,"xingming":"王立宏 ","shouji":"13146929789 ","customer_birthday":"19820225","mate_birthday":null,"wedding_date":"2018-04-06 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"姜明","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-02 00:00:00.000","syjd_day":null,"yp_day":" 04-23 03:46:20.223 19975-20732/com.suxuantech.erpsys I/NoHttp: 2018-03-09 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163315982828","customer_from":"CRM","brandid":1},{"id":12,"customer_number":"SY18011900004","customer_name":"王立宏","customer_sex":"男","customer_tel":"13146929245","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"","mate_sex":"女","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"三亚旅拍","customer_cource":"婚博会","customer_intention":"有兴趣","customer_orderaddress":"王府井接单点","customer_area":"VIP区","op_signinfo":null,"op_signday":null,"xingming":"王立宏 ","shouji":"13146929245 ","customer_birthday":"19820223","mate_birthday":"19830119","wedding_date":"2018-05-05 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-02-16 00:00:00.000","syjd_day":null,"yp_day":"2018-03-23 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163319383656","customer_from":"CRM","brandid":1},{"id":13,"customer_number":"SY18011900004","customer_name":"梁朝伟","customer_sex":"男","customer_tel":"18612565456","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"刘嘉玲","mate_sex":"女","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"艺术照","customer_cource":"大众点评","customer_intention":"有兴趣","customer_orderaddress":"王府井接单点","customer_area":"贵宾区","op_signinfo":null,"op_signday":null,"xingming":"梁朝伟 刘嘉玲","shouji":"18612565456 ","customer_birthday":"19670623","mate_birthday":"19750119","wedding_date":"2018-08-11 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-03-15 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163320663286","customer_from":"CRM","brandid":1},{"id":14,"customer_number":"SY18011900004","customer_name":"全智贤","customer 04-23 03:46:20.223 19975-20732/com.suxuantech.erpsys I/NoHttp: _sex":"女","customer_tel":"13146925789","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"","mate_sex":"男","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"","customer_cource":"自然进店","customer_intention":"有兴趣","customer_orderaddress":"龙湖接单点","customer_area":"VIP区","op_signinfo":null,"op_signday":null,"xingming":"全智贤 ","shouji":"13146925789 ","customer_birthday":"98130820","mate_birthday":null,"wedding_date":"2018-05-11 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-03-21 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163322044472","customer_from":"CRM","brandid":1},{"id":15,"customer_number":"SY18011900004","customer_name":"肖宇","customer_sex":"男","customer_tel":"13146929125","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"","mate_name":"","mate_sex":"女","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"艺术照","customer_cource":"网络订单","customer_intention":"有兴趣","customer_orderaddress":"西单接单点","customer_area":"贵宾区","op_signinfo":null,"op_signday":null,"xingming":"肖宇 ","shouji":"13146929125 ","customer_birthday":"19820808","mate_birthday":null,"wedding_date":"2018-07-20 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-02-22 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163323647277","customer_from":"CRM","brandid":1},{"id":16,"customer_number":"SY18011900004","customer_name":"杜维","customer_sex":"男","customer_tel":"13146929756","customer_wechat":"","customer_weibo":null,"customer_qq":"","province_id":null,"province":null,"city_id":null,"city":null,"county_id":null,"county":null,"customer_address":"北京市","mate_name":"","mate_sex":"女","mate_tel":"","mate_wechat":"","mate_weibo":null,"mate_qq":"","consultation_type":"婚纱照","customer_cource":"大众点评","customer_intention":"不再联系","customer_orderaddress":"","customer_area":"贵宾区","op_signinfo":null,"op_signday":null,"xingming":"杜维 ","shouji":"13 04-23 03:46:20.223 19975-20732/com.suxuantech.erpsys I/NoHttp: 146929756 ","customer_birthday":"19811221","mate_birthday":null,"wedding_date":"2018-07-06 00:00:00.000","dj_staff":"尹一苹","fp_staff":null,"sales_staff":"尹一苹","dj_day":"2018-01-19 00:00:00.000","fp_day":null,"yjd_day":"2018-01-19 00:00:00.000","syjd_day":null,"yp_day":"2018-04-06 00:00:00.000","order_day":null,"order_number":null,"last_trackday":null,"creator_day":"2018-01-19 00:00:00.000","customer_remark":"","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","setkey":"ZX002","is_fp":"未分配","is_intostore":"","is_success":"","is_valid":"","is_loss":"","statusremark":null,"shop_codeZD":null,"shop_nameZD":null,"from_index":"15163325036386","customer_from":"CRM","brandid":1}]
     */

    @SerializedName("Data")
    private List<DataBean> DataX;

    public List<DataBean> getDataX() {
        return DataX;
    }

    public void setDataX(List<DataBean> DataX) {
        this.DataX = DataX;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 4
         * customer_number : SY18011900003
         * customer_name : 宋仲基
         * customer_sex : 男
         * customer_tel : 13146929215
         * customer_wechat :
         * customer_weibo : null
         * customer_qq :
         * province_id : null
         * province : null
         * city_id : null
         * city : null
         * county_id : null
         * county : null
         * customer_address : 北京市朝阳区
         * mate_name : 宋慧乔
         * mate_sex : 女
         * mate_tel : 13146929145
         * mate_wechat :
         * mate_weibo : null
         * mate_qq :
         * consultation_type : 婚纱照
         * customer_cource : 自然进店
         * customer_intention : 一般
         * customer_orderaddress : 王府井接单点
         * customer_area :
         * op_signinfo : null
         * op_signday : null
         * xingming : 宋仲基 宋慧乔
         * shouji : 13146929215 13146929145
         * customer_birthday : 19810120
         * mate_birthday : 19810128
         * wedding_date : 2018-02-01 00:00:00.000
         * dj_staff : 尹一苹
         * fp_staff : null
         * sales_staff :
         * dj_day : 2018-01-19 00:00:00.000
         * fp_day : null
         * yjd_day : 2018-01-19 00:00:00.000
         * syjd_day : null
         * yp_day : 2018-01-31 00:00:00.000
         * order_day : null
         * order_number : null
         * last_trackday : null
         * creator_day : 2018-01-19 00:00:00.000
         * customer_remark :
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * setkey : ZX002
         * is_fp : 未分配
         * is_intostore :
         * is_success :
         * is_valid :
         * is_loss : 流失
         * statusremark :
         * shop_codeZD : null
         * shop_nameZD : null
         * from_index : 15163246484381
         * customer_from : CRM
         * brandid : 1
         * co 04-23 03:46:20.222 19975-20732/com.suxuantech.erpsys I/NoHttp: nsultation_type : 艺术照
         * customer 04-23 03:46:20.223 19975-20732/com.suxuantech.erpsys I/NoHttp: _sex : 女
         */

        private int id;
        private String customer_number;
        private String customer_name;
        private String customer_sex;
        private String customer_tel;
        private String customer_wechat;
        private String customer_weibo;
        private String customer_qq;
        private String province_id;
        private String province;
        private String city_id;
        private String city;
        private String county_id;
        private String county;
        private String customer_address;
        private String mate_name;
        private String mate_sex;
        private String mate_tel;
        private String mate_wechat;
        private String mate_weibo;
        private String mate_qq;
        private String consultation_type;
        private String customer_cource;
        private String customer_intention;
        private String customer_orderaddress;
        private String customer_area;
        private String op_signinfo;
        private String op_signday;
        private String xingming;
        private String shouji;
        private String customer_birthday;
        private String mate_birthday;
        private String wedding_date;
        private String dj_staff;
        private String fp_staff;
        private String sales_staff;
        private String dj_day;
        private String fp_day;
        private String yjd_day;
        private String syjd_day;
        private String yp_day;
        private String order_day;
        private String order_number;
        private String last_trackday;
        private String creator_day;
        private String customer_remark;
        private String shop_name;
        private String shop_code;
        private String setkey;
        private String is_fp;
        private String is_intostore;
        private String is_success;
        private String is_valid;
        private String is_loss;
        private String statusremark;
        private String shop_codeZD;
        private String shop_nameZD;
        private String from_index;
        private String customer_from;
        private int brandid;
        @SerializedName("co 04-23 03:46:20.222 19975-20732/com.suxuantech.erpsys I/NoHttp: nsultation_type")
        private String _$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322; // FIXME check this code
        @SerializedName("customer 04-23 03:46:20.223 19975-20732/com.suxuantech.erpsys I/NoHttp: _sex")
        private String _$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4; // FIXME check this code

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomer_number() {
            return customer_number;
        }

        public void setCustomer_number(String customer_number) {
            this.customer_number = customer_number;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_sex() {
            return customer_sex;
        }

        public void setCustomer_sex(String customer_sex) {
            this.customer_sex = customer_sex;
        }

        public String getCustomer_tel() {
            return customer_tel;
        }

        public void setCustomer_tel(String customer_tel) {
            this.customer_tel = customer_tel;
        }

        public String getCustomer_wechat() {
            return customer_wechat;
        }

        public void setCustomer_wechat(String customer_wechat) {
            this.customer_wechat = customer_wechat;
        }

        public String getCustomer_weibo() {
            return customer_weibo;
        }

        public void setCustomer_weibo(String customer_weibo) {
            this.customer_weibo = customer_weibo;
        }

        public String getCustomer_qq() {
            return customer_qq;
        }

        public void setCustomer_qq(String customer_qq) {
            this.customer_qq = customer_qq;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty_id() {
            return county_id;
        }

        public void setCounty_id(String county_id) {
            this.county_id = county_id;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getCustomer_address() {
            return customer_address;
        }

        public void setCustomer_address(String customer_address) {
            this.customer_address = customer_address;
        }

        public String getMate_name() {
            return mate_name;
        }

        public void setMate_name(String mate_name) {
            this.mate_name = mate_name;
        }

        public String getMate_sex() {
            return mate_sex;
        }

        public void setMate_sex(String mate_sex) {
            this.mate_sex = mate_sex;
        }

        public String getMate_tel() {
            return mate_tel;
        }

        public void setMate_tel(String mate_tel) {
            this.mate_tel = mate_tel;
        }

        public String getMate_wechat() {
            return mate_wechat;
        }

        public void setMate_wechat(String mate_wechat) {
            this.mate_wechat = mate_wechat;
        }

        public String getMate_weibo() {
            return mate_weibo;
        }

        public void setMate_weibo(String mate_weibo) {
            this.mate_weibo = mate_weibo;
        }

        public String getMate_qq() {
            return mate_qq;
        }

        public void setMate_qq(String mate_qq) {
            this.mate_qq = mate_qq;
        }

        public String getConsultation_type() {
            return consultation_type;
        }

        public void setConsultation_type(String consultation_type) {
            this.consultation_type = consultation_type;
        }

        public String getCustomer_cource() {
            return customer_cource;
        }

        public void setCustomer_cource(String customer_cource) {
            this.customer_cource = customer_cource;
        }

        public String getCustomer_intention() {
            return customer_intention;
        }

        public void setCustomer_intention(String customer_intention) {
            this.customer_intention = customer_intention;
        }

        public String getCustomer_orderaddress() {
            return customer_orderaddress;
        }

        public void setCustomer_orderaddress(String customer_orderaddress) {
            this.customer_orderaddress = customer_orderaddress;
        }

        public String getCustomer_area() {
            return customer_area;
        }

        public void setCustomer_area(String customer_area) {
            this.customer_area = customer_area;
        }

        public String getOp_signinfo() {
            return op_signinfo;
        }

        public void setOp_signinfo(String op_signinfo) {
            this.op_signinfo = op_signinfo;
        }

        public String getOp_signday() {
            return op_signday;
        }

        public void setOp_signday(String op_signday) {
            this.op_signday = op_signday;
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

        public String getCustomer_birthday() {
            return customer_birthday;
        }

        public void setCustomer_birthday(String customer_birthday) {
            this.customer_birthday = customer_birthday;
        }

        public String getMate_birthday() {
            return mate_birthday;
        }

        public void setMate_birthday(String mate_birthday) {
            this.mate_birthday = mate_birthday;
        }

        public String getWedding_date() {
            return wedding_date;
        }

        public void setWedding_date(String wedding_date) {
            this.wedding_date = wedding_date;
        }

        public String getDj_staff() {
            return dj_staff;
        }

        public void setDj_staff(String dj_staff) {
            this.dj_staff = dj_staff;
        }

        public String getFp_staff() {
            return fp_staff;
        }

        public void setFp_staff(String fp_staff) {
            this.fp_staff = fp_staff;
        }

        public String getSales_staff() {
            return sales_staff;
        }

        public void setSales_staff(String sales_staff) {
            this.sales_staff = sales_staff;
        }

        public String getDj_day() {
            return dj_day;
        }

        public void setDj_day(String dj_day) {
            this.dj_day = dj_day;
        }

        public String getFp_day() {
            return fp_day;
        }

        public void setFp_day(String fp_day) {
            this.fp_day = fp_day;
        }

        public String getYjd_day() {
            return yjd_day;
        }

        public void setYjd_day(String yjd_day) {
            this.yjd_day = yjd_day;
        }

        public String getSyjd_day() {
            return syjd_day;
        }

        public void setSyjd_day(String syjd_day) {
            this.syjd_day = syjd_day;
        }

        public String getYp_day() {
            return yp_day;
        }

        public void setYp_day(String yp_day) {
            this.yp_day = yp_day;
        }

        public String getOrder_day() {
            return order_day;
        }

        public void setOrder_day(String order_day) {
            this.order_day = order_day;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getLast_trackday() {
            return last_trackday;
        }

        public void setLast_trackday(String last_trackday) {
            this.last_trackday = last_trackday;
        }

        public String getCreator_day() {
            return creator_day;
        }

        public void setCreator_day(String creator_day) {
            this.creator_day = creator_day;
        }

        public String getCustomer_remark() {
            return customer_remark;
        }

        public void setCustomer_remark(String customer_remark) {
            this.customer_remark = customer_remark;
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

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }

        public String getIs_fp() {
            return is_fp;
        }

        public void setIs_fp(String is_fp) {
            this.is_fp = is_fp;
        }

        public String getIs_intostore() {
            return is_intostore;
        }

        public void setIs_intostore(String is_intostore) {
            this.is_intostore = is_intostore;
        }

        public String getIs_success() {
            return is_success;
        }

        public void setIs_success(String is_success) {
            this.is_success = is_success;
        }

        public String getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(String is_valid) {
            this.is_valid = is_valid;
        }

        public String getIs_loss() {
            return is_loss;
        }

        public void setIs_loss(String is_loss) {
            this.is_loss = is_loss;
        }

        public String getStatusremark() {
            return statusremark;
        }

        public void setStatusremark(String statusremark) {
            this.statusremark = statusremark;
        }

        public String getShop_codeZD() {
            return shop_codeZD;
        }

        public void setShop_codeZD(String shop_codeZD) {
            this.shop_codeZD = shop_codeZD;
        }

        public String getShop_nameZD() {
            return shop_nameZD;
        }

        public void setShop_nameZD(String shop_nameZD) {
            this.shop_nameZD = shop_nameZD;
        }

        public String getFrom_index() {
            return from_index;
        }

        public void setFrom_index(String from_index) {
            this.from_index = from_index;
        }

        public String getCustomer_from() {
            return customer_from;
        }

        public void setCustomer_from(String customer_from) {
            this.customer_from = customer_from;
        }

        public int getBrandid() {
            return brandid;
        }

        public void setBrandid(int brandid) {
            this.brandid = brandid;
        }

        public String get_$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322() {
            return _$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322;
        }

        public void set_$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322(String _$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322) {
            this._$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322 = _$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322;
        }

        public String get_$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4() {
            return _$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4;
        }

        public void set_$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4(String _$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4) {
            this._$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4 = _$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.customer_number);
            dest.writeString(this.customer_name);
            dest.writeString(this.customer_sex);
            dest.writeString(this.customer_tel);
            dest.writeString(this.customer_wechat);
            dest.writeString(this.customer_weibo);
            dest.writeString(this.customer_qq);
            dest.writeString(this.province_id);
            dest.writeString(this.province);
            dest.writeString(this.city_id);
            dest.writeString(this.city);
            dest.writeString(this.county_id);
            dest.writeString(this.county);
            dest.writeString(this.customer_address);
            dest.writeString(this.mate_name);
            dest.writeString(this.mate_sex);
            dest.writeString(this.mate_tel);
            dest.writeString(this.mate_wechat);
            dest.writeString(this.mate_weibo);
            dest.writeString(this.mate_qq);
            dest.writeString(this.consultation_type);
            dest.writeString(this.customer_cource);
            dest.writeString(this.customer_intention);
            dest.writeString(this.customer_orderaddress);
            dest.writeString(this.customer_area);
            dest.writeString(this.op_signinfo);
            dest.writeString(this.op_signday);
            dest.writeString(this.xingming);
            dest.writeString(this.shouji);
            dest.writeString(this.customer_birthday);
            dest.writeString(this.mate_birthday);
            dest.writeString(this.wedding_date);
            dest.writeString(this.dj_staff);
            dest.writeString(this.fp_staff);
            dest.writeString(this.sales_staff);
            dest.writeString(this.dj_day);
            dest.writeString(this.fp_day);
            dest.writeString(this.yjd_day);
            dest.writeString(this.syjd_day);
            dest.writeString(this.yp_day);
            dest.writeString(this.order_day);
            dest.writeString(this.order_number);
            dest.writeString(this.last_trackday);
            dest.writeString(this.creator_day);
            dest.writeString(this.customer_remark);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.setkey);
            dest.writeString(this.is_fp);
            dest.writeString(this.is_intostore);
            dest.writeString(this.is_success);
            dest.writeString(this.is_valid);
            dest.writeString(this.is_loss);
            dest.writeString(this.statusremark);
            dest.writeString(this.shop_codeZD);
            dest.writeString(this.shop_nameZD);
            dest.writeString(this.from_index);
            dest.writeString(this.customer_from);
            dest.writeInt(this.brandid);
            dest.writeString(this._$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322);
            dest.writeString(this._$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.customer_number = in.readString();
            this.customer_name = in.readString();
            this.customer_sex = in.readString();
            this.customer_tel = in.readString();
            this.customer_wechat = in.readString();
            this.customer_weibo = in.readString();
            this.customer_qq = in.readString();
            this.province_id = in.readString();
            this.province = in.readString();
            this.city_id = in.readString();
            this.city = in.readString();
            this.county_id = in.readString();
            this.county = in.readString();
            this.customer_address = in.readString();
            this.mate_name = in.readString();
            this.mate_sex = in.readString();
            this.mate_tel = in.readString();
            this.mate_wechat = in.readString();
            this.mate_weibo = in.readString();
            this.mate_qq = in.readString();
            this.consultation_type = in.readString();
            this.customer_cource = in.readString();
            this.customer_intention = in.readString();
            this.customer_orderaddress = in.readString();
            this.customer_area = in.readString();
            this.op_signinfo = in.readString();
            this.op_signday = in.readString();
            this.xingming = in.readString();
            this.shouji = in.readString();
            this.customer_birthday = in.readString();
            this.mate_birthday = in.readString();
            this.wedding_date = in.readString();
            this.dj_staff = in.readString();
            this.fp_staff = in.readString();
            this.sales_staff = in.readString();
            this.dj_day = in.readString();
            this.fp_day = in.readString();
            this.yjd_day = in.readString();
            this.syjd_day = in.readString();
            this.yp_day = in.readString();
            this.order_day = in.readString();
            this.order_number = in.readString();
            this.last_trackday = in.readString();
            this.creator_day = in.readString();
            this.customer_remark = in.readString();
            this.shop_name = in.readString();
            this.shop_code = in.readString();
            this.setkey = in.readString();
            this.is_fp = in.readString();
            this.is_intostore = in.readString();
            this.is_success = in.readString();
            this.is_valid = in.readString();
            this.is_loss = in.readString();
            this.statusremark = in.readString();
            this.shop_codeZD = in.readString();
            this.shop_nameZD = in.readString();
            this.from_index = in.readString();
            this.customer_from = in.readString();
            this.brandid = in.readInt();
            this._$Co04230346202221997520732ComSuxuantechErpsysINoHttpNsultation_type322 = in.readString();
            this._$Customer04230346202231997520732ComSuxuantechErpsysINoHttp_sex4 = in.readString();
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
