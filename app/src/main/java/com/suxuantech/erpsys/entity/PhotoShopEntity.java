package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2018/4/18 0018 17:35 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class PhotoShopEntity extends BaseResult2<PhotoShopEntity.DataBean> {
    public static class DataBean {
        /**
         * id : 1
         * shop_code : VVC001
         * shop_name : 时尚经典旗舰店
         * brandclass_id : 1
         * brandclass : 时尚经典事业部
         * sn : S
         * numberorder :
         * shopaddress :
         * receiv_shop_code :
         * shop_type : 1
         * is_dig : 2
         * is_marketing : 2
         * is_sk : 2
         * is_group : 1
         * is_manage : 2
         * is_synch : 2
         * is_photo : 1
         * belong_shop_code : VVC009
         * belong_shop_name : 时尚经典渠道部
         * weight : 0
         * end_date :
         * wx_public :
         */

        private String id;
        private String shop_code;
        private String shop_name;
        private String brandclass_id;
        private String brandclass;
        private String sn;
        private String numberorder;
        private String shopaddress;
        private String receiv_shop_code;
        private String shop_type;
        private String is_dig;
        private String is_marketing;
        private String is_sk;
        private String is_group;
        private String is_manage;
        private String is_synch;
        private String is_photo;
        private String belong_shop_code;
        private String belong_shop_name;
        private String weight;
        private String end_date;
        private String wx_public;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getBrandclass_id() {
            return brandclass_id;
        }

        public void setBrandclass_id(String brandclass_id) {
            this.brandclass_id = brandclass_id;
        }

        public String getBrandclass() {
            return brandclass;
        }

        public void setBrandclass(String brandclass) {
            this.brandclass = brandclass;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getNumberorder() {
            return numberorder;
        }

        public void setNumberorder(String numberorder) {
            this.numberorder = numberorder;
        }

        public String getShopaddress() {
            return shopaddress;
        }

        public void setShopaddress(String shopaddress) {
            this.shopaddress = shopaddress;
        }

        public String getReceiv_shop_code() {
            return receiv_shop_code;
        }

        public void setReceiv_shop_code(String receiv_shop_code) {
            this.receiv_shop_code = receiv_shop_code;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getIs_dig() {
            return is_dig;
        }

        public void setIs_dig(String is_dig) {
            this.is_dig = is_dig;
        }

        public String getIs_marketing() {
            return is_marketing;
        }

        public void setIs_marketing(String is_marketing) {
            this.is_marketing = is_marketing;
        }

        public String getIs_sk() {
            return is_sk;
        }

        public void setIs_sk(String is_sk) {
            this.is_sk = is_sk;
        }

        public String getIs_group() {
            return is_group;
        }

        public void setIs_group(String is_group) {
            this.is_group = is_group;
        }

        public String getIs_manage() {
            return is_manage;
        }

        public void setIs_manage(String is_manage) {
            this.is_manage = is_manage;
        }

        public String getIs_synch() {
            return is_synch;
        }

        public void setIs_synch(String is_synch) {
            this.is_synch = is_synch;
        }

        public String getIs_photo() {
            return is_photo;
        }

        public void setIs_photo(String is_photo) {
            this.is_photo = is_photo;
        }

        public String getBelong_shop_code() {
            return belong_shop_code;
        }

        public void setBelong_shop_code(String belong_shop_code) {
            this.belong_shop_code = belong_shop_code;
        }

        public String getBelong_shop_name() {
            return belong_shop_name;
        }

        public void setBelong_shop_name(String belong_shop_name) {
            this.belong_shop_name = belong_shop_name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getWx_public() {
            return wx_public;
        }

        public void setWx_public(String wx_public) {
            this.wx_public = wx_public;
        }
    }
}
