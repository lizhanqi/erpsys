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
 * @author Created by 李站旗 on 2018/5/19 0019 17:47 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class DepartmentEntiy extends BaseResult2 <DepartmentEntiy.DataBean>{
    public static class DataBean {
        /**
         * id : 52
         * department_name : 客服部
         * grade_type : 3
         * channel_list : null
         * group_company_name : 中国沈阳时尚经典婚纱摄影集团
         * brandclass : 时尚经典全球旅拍事业部
         * shop_name : 时尚旅拍客服中心
         * shop_code : KF001
         * setkey : null
         * brandclass_id : 2
         */
        private int id;
        private String department_name;
        private int grade_type;
        private String channel_list;
        private String group_company_name;
        private String brandclass;
        private String shop_name;
        private String shop_code;
        private String setkey;
        private int brandclass_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public int getGrade_type() {
            return grade_type;
        }

        public void setGrade_type(int grade_type) {
            this.grade_type = grade_type;
        }

        public String getChannel_list() {
            return channel_list;
        }

        public void setChannel_list(String channel_list) {
            this.channel_list = channel_list;
        }

        public String getGroup_company_name() {
            return group_company_name;
        }

        public void setGroup_company_name(String group_company_name) {
            this.group_company_name = group_company_name;
        }

        public String getBrandclass() {
            return brandclass;
        }

        public void setBrandclass(String brandclass) {
            this.brandclass = brandclass;
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

        public int getBrandclass_id() {
            return brandclass_id;
        }

        public void setBrandclass_id(int brandclass_id) {
            this.brandclass_id = brandclass_id;
        }
    }
}
