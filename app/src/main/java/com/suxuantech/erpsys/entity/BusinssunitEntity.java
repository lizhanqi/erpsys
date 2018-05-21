package com.suxuantech.erpsys.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

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
 * @author Created by 李站旗 on 2018/5/19 0019 17:45 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class BusinssunitEntity extends BaseResult2<BusinssunitEntity.DataBean>  {
    public static class DataBean implements MultiItemEntity {
        /**
         * id : 1
         * brandclass : 时尚经典事业部
         * group_company_name : 中国沈阳时尚经典婚纱摄影集团
         * nickname : 时尚事业部
         * order_rule : 2
         * proportion : 30
         * mall_url : http://shop2.erp.suxuantech.cn
         * vip_url : http://vip.sx.com1
         * wx_public :
         * wx_appid : wxfeb97760604fa235
         * wx_secret : 663df33acc024acc57a8cd51332383b9
         * wx_mch_id :
         * wx_pay_key :
         * valid_date : 90
         */

        private int id;
        private String brandclass;
        private String group_company_name;
        private String nickname;
        private int order_rule;
        private int proportion;
        private String mall_url;
        private String vip_url;
        private String wx_public;
        private String wx_appid;
        private String wx_secret;
        private String wx_mch_id;
        private String wx_pay_key;
        private int valid_date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrandclass() {
            return brandclass;
        }

        public void setBrandclass(String brandclass) {
            this.brandclass = brandclass;
        }

        public String getGroup_company_name() {
            return group_company_name;
        }

        public void setGroup_company_name(String group_company_name) {
            this.group_company_name = group_company_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOrder_rule() {
            return order_rule;
        }

        public void setOrder_rule(int order_rule) {
            this.order_rule = order_rule;
        }

        public int getProportion() {
            return proportion;
        }

        public void setProportion(int proportion) {
            this.proportion = proportion;
        }

        public String getMall_url() {
            return mall_url;
        }

        public void setMall_url(String mall_url) {
            this.mall_url = mall_url;
        }

        public String getVip_url() {
            return vip_url;
        }

        public void setVip_url(String vip_url) {
            this.vip_url = vip_url;
        }

        public String getWx_public() {
            return wx_public;
        }

        public void setWx_public(String wx_public) {
            this.wx_public = wx_public;
        }

        public String getWx_appid() {
            return wx_appid;
        }

        public void setWx_appid(String wx_appid) {
            this.wx_appid = wx_appid;
        }

        public String getWx_secret() {
            return wx_secret;
        }

        public void setWx_secret(String wx_secret) {
            this.wx_secret = wx_secret;
        }

        public String getWx_mch_id() {
            return wx_mch_id;
        }

        public void setWx_mch_id(String wx_mch_id) {
            this.wx_mch_id = wx_mch_id;
        }

        public String getWx_pay_key() {
            return wx_pay_key;
        }

        public void setWx_pay_key(String wx_pay_key) {
            this.wx_pay_key = wx_pay_key;
        }

        public int getValid_date() {
            return valid_date;
        }

        public void setValid_date(int valid_date) {
            this.valid_date = valid_date;
        }

        @Override
        public int getItemType() {
            return  1;
        }
    }
}
