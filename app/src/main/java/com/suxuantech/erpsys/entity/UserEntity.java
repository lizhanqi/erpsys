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
 * @author Created by 李站旗 on 2018/5/17 0017 15:19 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
    public   class UserEntity {
    /**
     * staff_id : 37
     * session_id : hVUcmBPLKz
     * ip : 127.0.0.1
     * login_type : PC
     * login_method : user_name
     * expiry : 2018-05-18 13:52:08
     * valid_date : 90
     * staffname : SSS
     * staffnumber : S001
     * brandclass : 时尚经典事业部
     * shop_name : 时尚经典旗舰店
     * shop_code : VVC001
     * belong_shop_name : 时尚经典渠道部
     * belong_shop_code : VVC009
     * is_marketing : 2
     * is_sk : 2
     * shop_type : 1
     * receiv_shop_code :
     * is_group : 1
     * end_date : null
     * setkey : null
     * department_name : 门市部
     * department_id : 4
     * main_position_name : ERP系统高级管理员
     * main_position_code : p15169837225860
     * grade_type : 4
     * position_code : p15169793315051,p15169901128683,p15169902642583
     * status : 1
     * sn : S
     * main_work_type : 22
     * work_type : 1,1,22,22
     * open_id :
     * is_belong : 4
     * brandclass_id : 1
     */

    public int staff_id;
    public String session_id;
    public String ip;
    public String login_type;
    public String login_method;
    public String expiry;
    public int valid_date;
    public String staffname;
    public String staffnumber;
    public String brandclass;
    public String shop_name;
    public String shop_code;
    public String belong_shop_name;
    public String belong_shop_code;
    public int is_marketing;
    public int is_sk;
    public int shop_type;
    public String receiv_shop_code;
    public int is_group;
    public Object end_date;
    public Object setkey;
    public String department_name;
    public int department_id;
    public String main_position_name;
    public String main_position_code;
    public int grade_type;
    public String position_code;
    public int status;
    public String sn;
    public String main_work_type;
    public String work_type;
    public String open_id;
    public int is_belong;
    public int brandclass_id;

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

    public Object getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Object end_date) {
        this.end_date = end_date;
    }

    public Object getSetkey() {
        return setkey;
    }

    public void setSetkey(Object setkey) {
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
