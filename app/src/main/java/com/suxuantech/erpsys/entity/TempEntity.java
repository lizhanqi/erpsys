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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity  extends  BaseResult2<TempEntity.DataBean> {


    /**
     * code : 200
     * msg : 登录成功
     * data : {"staff_id":391,"session_id":"stDKIVMxjy","ip":"127.0.0.1","login_type":"PC","login_method":"user_name","expiry":"2018-05-19 20:12:52","valid_date":90,"staffname":"如来","staffnumber":"KF002","brandclass":"时尚经典全球旅拍事业部","shop_name":"时尚旅拍客服中心","shop_code":"KF001","belong_shop_name":"时尚旅拍客服中心","belong_shop_code":"KF001","is_marketing":1,"is_sk":1,"shop_type":1,"receiv_shop_code":"","is_group":1,"end_date":null,"setkey":null,"department_name":"客服部","department_id":52,"main_position_name":"客服主管","main_position_code":"p15181128254642","grade_type":4,"position_code":null,"status":1,"sn":"KF","main_work_type":"22","work_type":",22","open_id":"o3mGr0iz8l7_gptL0cFB-rvSq6Yo","is_belong":4,"brandclass_id":2}
     */

    public static class DataBean {
        /**
         * staff_id : 391
         * session_id : stDKIVMxjy
         * ip : 127.0.0.1
         * login_type : PC
         * login_method : user_name
         * expiry : 2018-05-19 20:12:52
         * valid_date : 90
         * staffname : 如来
         * staffnumber : KF002
         * brandclass : 时尚经典全球旅拍事业部
         * shop_name : 时尚旅拍客服中心
         * shop_code : KF001
         * belong_shop_name : 时尚旅拍客服中心
         * belong_shop_code : KF001
         * is_marketing : 1
         * is_sk : 1
         * shop_type : 1
         * receiv_shop_code :
         * is_group : 1
         * end_date : null
         * setkey : null
         * department_name : 客服部
         * department_id : 52
         * main_position_name : 客服主管
         * main_position_code : p15181128254642
         * grade_type : 4
         * position_code : null
         * status : 1
         * sn : KF
         * main_work_type : 22
         * work_type : ,22
         * open_id : o3mGr0iz8l7_gptL0cFB-rvSq6Yo
         * is_belong : 4
         * brandclass_id : 2
         */

        private int staff_id;
        private String session_id;
        private String ip;
        private String login_type;
        private String login_method;
        private String expiry;
        private int valid_date;
        private String staffname;
        private String staffnumber;
        private String brandclass;
        private String shop_name;
        private String shop_code;
        private String belong_shop_name;
        private String belong_shop_code;
        private int is_marketing;
        private int is_sk;
        private int shop_type;
        private String receiv_shop_code;
        private int is_group;
        private String end_date;
        private String setkey;
        private String department_name;
        private int department_id;
        private String main_position_name;
        private String main_position_code;
        private int grade_type;
        private String position_code;
        private int status;
        private String sn;
        private String main_work_type;
        private String work_type;
        private String open_id;
        private int is_belong;
        private int brandclass_id;

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getLogin_type() {
            return login_type;
        }

        public void setLogin_type(String login_type) {
            this.login_type = login_type;
        }

        public String getLogin_method() {
            return login_method;
        }

        public void setLogin_method(String login_method) {
            this.login_method = login_method;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

        public int getValid_date() {
            return valid_date;
        }

        public void setValid_date(int valid_date) {
            this.valid_date = valid_date;
        }

        public String getStaffname() {
            return staffname;
        }

        public void setStaffname(String staffname) {
            this.staffname = staffname;
        }

        public String getStaffnumber() {
            return staffnumber;
        }

        public void setStaffnumber(String staffnumber) {
            this.staffnumber = staffnumber;
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

        public String getBelong_shop_name() {
            return belong_shop_name;
        }

        public void setBelong_shop_name(String belong_shop_name) {
            this.belong_shop_name = belong_shop_name;
        }

        public String getBelong_shop_code() {
            return belong_shop_code;
        }

        public void setBelong_shop_code(String belong_shop_code) {
            this.belong_shop_code = belong_shop_code;
        }

        public int getIs_marketing() {
            return is_marketing;
        }

        public void setIs_marketing(int is_marketing) {
            this.is_marketing = is_marketing;
        }

        public int getIs_sk() {
            return is_sk;
        }

        public void setIs_sk(int is_sk) {
            this.is_sk = is_sk;
        }

        public int getShop_type() {
            return shop_type;
        }

        public void setShop_type(int shop_type) {
            this.shop_type = shop_type;
        }

        public String getReceiv_shop_code() {
            return receiv_shop_code;
        }

        public void setReceiv_shop_code(String receiv_shop_code) {
            this.receiv_shop_code = receiv_shop_code;
        }

        public int getIs_group() {
            return is_group;
        }

        public void setIs_group(int is_group) {
            this.is_group = is_group;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public String getMain_position_name() {
            return main_position_name;
        }

        public void setMain_position_name(String main_position_name) {
            this.main_position_name = main_position_name;
        }

        public String getMain_position_code() {
            return main_position_code;
        }

        public void setMain_position_code(String main_position_code) {
            this.main_position_code = main_position_code;
        }

        public int getGrade_type() {
            return grade_type;
        }

        public void setGrade_type(int grade_type) {
            this.grade_type = grade_type;
        }

        public String getPosition_code() {
            return position_code;
        }

        public void setPosition_code(String position_code) {
            this.position_code = position_code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getMain_work_type() {
            return main_work_type;
        }

        public void setMain_work_type(String main_work_type) {
            this.main_work_type = main_work_type;
        }

        public String getWork_type() {
            return work_type;
        }

        public void setWork_type(String work_type) {
            this.work_type = work_type;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public int getIs_belong() {
            return is_belong;
        }

        public void setIs_belong(int is_belong) {
            this.is_belong = is_belong;
        }

        public int getBrandclass_id() {
            return brandclass_id;
        }

        public void setBrandclass_id(int brandclass_id) {
            this.brandclass_id = brandclass_id;
        }
    }
}
