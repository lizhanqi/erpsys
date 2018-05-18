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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity  extends  BaseResult {


    /**
     * code : 200
     * msg : 
     * data : [{"id":82,"staffnumber":"S017","staffname":"刘扬 ","password":"MDAwMDAwMDAwMIV4oW0","department_id":4,"department_name":"门市部","main_position_id":12,"main_position_name":"门市主管","main_position_code":"p15169793587099","position_id":"43,90,94,95,116","position_code":"p15169810134774,p15169901128683,p15169901819415,p15169902052052,p15196094128600","zhiji_name":null,"turestaffname":"","group_company_name":"中国沈阳时尚经典婚纱摄影集团","brandclass":"时尚经典事业部","shop_name":"时尚经典旗舰店","shop_code":"VVC001","setkey":null,"group_id":0,"group_name":null,"is_group_admin":0,"sex":null,"entry_date":"","leave_date":null,"birthday":"","IDcard":"","nation":"","place_origin":"","political":"","education":"","graduation_school":"","marital_status":"","contract_endday":"","telephone":"13898151531","QQ":"","email":"","weixin":"","a_discount":"","b_discount":"","a_cost":null,"b_cost":null,"liaison_person":"","liaison_tel":"","salary_money":"","social_security":"","bank_cardnumber":"","addressinfo":"","remarkinfo":"","vice_work_type":null,"work_shop":"VVC001,VVC002,VVC002,VVC002,yshs,VVC002","open_id":"oaxtE1CFo1UxBOnwzaKTcms2mOZ8","open_id_name":null,"status":1,"Popedom1":null,"Popedom2":null,"Popedom3":null,"Popedom4":null,"Popedom5":null,"Popedom6":null,"Popedom7":null,"Popedom8":null,"Popedom9":null,"Popedom10":null,"Popedom11":null,"Popedom12":null,"Popedom13":null,"Popedom14":null,"Popedom15":null,"Popedom16":null,"Popedom17":null,"Popedom18":null,"zhiji_id":0,"add_time":"1521800752","sn":"S","grade_type":4,"last_login_ip":null,"last_login_time":null,"brandclass_id":1,"work_types":"22,1,2,22,22,2","is_jiguang":2}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 82
         * staffnumber : S017
         * staffname : 刘扬 
         * password : MDAwMDAwMDAwMIV4oW0
         * department_id : 4
         * department_name : 门市部
         * main_position_id : 12
         * main_position_name : 门市主管
         * main_position_code : p15169793587099
         * position_id : 43,90,94,95,116
         * position_code : p15169810134774,p15169901128683,p15169901819415,p15169902052052,p15196094128600
         * zhiji_name : null
         * turestaffname : 
         * group_company_name : 中国沈阳时尚经典婚纱摄影集团
         * brandclass : 时尚经典事业部
         * shop_name : 时尚经典旗舰店
         * shop_code : VVC001
         * setkey : null
         * group_id : 0
         * group_name : null
         * is_group_admin : 0
         * sex : null
         * entry_date : 
         * leave_date : null
         * birthday : 
         * IDcard : 
         * nation : 
         * place_origin : 
         * political : 
         * education : 
         * graduation_school : 
         * marital_status : 
         * contract_endday : 
         * telephone : 13898151531
         * QQ : 
         * email : 
         * weixin : 
         * a_discount : 
         * b_discount : 
         * a_cost : null
         * b_cost : null
         * liaison_person : 
         * liaison_tel : 
         * salary_money : 
         * social_security : 
         * bank_cardnumber : 
         * addressinfo : 
         * remarkinfo : 
         * vice_work_type : null
         * work_shop : VVC001,VVC002,VVC002,VVC002,yshs,VVC002
         * open_id : oaxtE1CFo1UxBOnwzaKTcms2mOZ8
         * open_id_name : null
         * status : 1
         * Popedom1 : null
         * Popedom2 : null
         * Popedom3 : null
         * Popedom4 : null
         * Popedom5 : null
         * Popedom6 : null
         * Popedom7 : null
         * Popedom8 : null
         * Popedom9 : null
         * Popedom10 : null
         * Popedom11 : null
         * Popedom12 : null
         * Popedom13 : null
         * Popedom14 : null
         * Popedom15 : null
         * Popedom16 : null
         * Popedom17 : null
         * Popedom18 : null
         * zhiji_id : 0
         * add_time : 1521800752
         * sn : S
         * grade_type : 4
         * last_login_ip : null
         * last_login_time : null
         * brandclass_id : 1
         * work_types : 22,1,2,22,22,2
         * is_jiguang : 2
         */

        private int id;
        private String staffnumber;
        private String staffname;
        private String password;
        private int department_id;
        private String department_name;
        private int main_position_id;
        private String main_position_name;
        private String main_position_code;
        private String position_id;
        private String position_code;
        private String zhiji_name;
        private String turestaffname;
        private String group_company_name;
        private String brandclass;
        private String shop_name;
        private String shop_code;
        private String setkey;
        private int group_id;
        private String group_name;
        private int is_group_admin;
        private String sex;
        private String entry_date;
        private String leave_date;
        private String birthday;
        private String IDcard;
        private String nation;
        private String place_origin;
        private String political;
        private String education;
        private String graduation_school;
        private String marital_status;
        private String contract_endday;
        private String telephone;
        private String QQ;
        private String email;
        private String weixin;
        private String a_discount;
        private String b_discount;
        private String a_cost;
        private String b_cost;
        private String liaison_person;
        private String liaison_tel;
        private String salary_money;
        private String social_security;
        private String bank_cardnumber;
        private String addressinfo;
        private String remarkinfo;
        private String vice_work_type;
        private String work_shop;
        private String open_id;
        private String open_id_name;
        private int status;
        private String Popedom1;
        private String Popedom2;
        private String Popedom3;
        private String Popedom4;
        private String Popedom5;
        private String Popedom6;
        private String Popedom7;
        private String Popedom8;
        private String Popedom9;
        private String Popedom10;
        private String Popedom11;
        private String Popedom12;
        private String Popedom13;
        private String Popedom14;
        private String Popedom15;
        private String Popedom16;
        private String Popedom17;
        private String Popedom18;
        private int zhiji_id;
        private String add_time;
        private String sn;
        private int grade_type;
        private String last_login_ip;
        private String last_login_time;
        private int brandclass_id;
        private String work_types;
        private int is_jiguang;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStaffnumber() {
            return staffnumber;
        }

        public void setStaffnumber(String staffnumber) {
            this.staffnumber = staffnumber;
        }

        public String getStaffname() {
            return staffname;
        }

        public void setStaffname(String staffname) {
            this.staffname = staffname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public int getMain_position_id() {
            return main_position_id;
        }

        public void setMain_position_id(int main_position_id) {
            this.main_position_id = main_position_id;
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

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }

        public String getPosition_code() {
            return position_code;
        }

        public void setPosition_code(String position_code) {
            this.position_code = position_code;
        }

        public String getZhiji_name() {
            return zhiji_name;
        }

        public void setZhiji_name(String zhiji_name) {
            this.zhiji_name = zhiji_name;
        }

        public String getTurestaffname() {
            return turestaffname;
        }

        public void setTurestaffname(String turestaffname) {
            this.turestaffname = turestaffname;
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

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public int getIs_group_admin() {
            return is_group_admin;
        }

        public void setIs_group_admin(int is_group_admin) {
            this.is_group_admin = is_group_admin;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEntry_date() {
            return entry_date;
        }

        public void setEntry_date(String entry_date) {
            this.entry_date = entry_date;
        }

        public String getLeave_date() {
            return leave_date;
        }

        public void setLeave_date(String leave_date) {
            this.leave_date = leave_date;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIDcard() {
            return IDcard;
        }

        public void setIDcard(String IDcard) {
            this.IDcard = IDcard;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getPlace_origin() {
            return place_origin;
        }

        public void setPlace_origin(String place_origin) {
            this.place_origin = place_origin;
        }

        public String getPolitical() {
            return political;
        }

        public void setPolitical(String political) {
            this.political = political;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getGraduation_school() {
            return graduation_school;
        }

        public void setGraduation_school(String graduation_school) {
            this.graduation_school = graduation_school;
        }

        public String getMarital_status() {
            return marital_status;
        }

        public void setMarital_status(String marital_status) {
            this.marital_status = marital_status;
        }

        public String getContract_endday() {
            return contract_endday;
        }

        public void setContract_endday(String contract_endday) {
            this.contract_endday = contract_endday;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getA_discount() {
            return a_discount;
        }

        public void setA_discount(String a_discount) {
            this.a_discount = a_discount;
        }

        public String getB_discount() {
            return b_discount;
        }

        public void setB_discount(String b_discount) {
            this.b_discount = b_discount;
        }

        public String getA_cost() {
            return a_cost;
        }

        public void setA_cost(String a_cost) {
            this.a_cost = a_cost;
        }

        public String getB_cost() {
            return b_cost;
        }

        public void setB_cost(String b_cost) {
            this.b_cost = b_cost;
        }

        public String getLiaison_person() {
            return liaison_person;
        }

        public void setLiaison_person(String liaison_person) {
            this.liaison_person = liaison_person;
        }

        public String getLiaison_tel() {
            return liaison_tel;
        }

        public void setLiaison_tel(String liaison_tel) {
            this.liaison_tel = liaison_tel;
        }

        public String getSalary_money() {
            return salary_money;
        }

        public void setSalary_money(String salary_money) {
            this.salary_money = salary_money;
        }

        public String getSocial_security() {
            return social_security;
        }

        public void setSocial_security(String social_security) {
            this.social_security = social_security;
        }

        public String getBank_cardnumber() {
            return bank_cardnumber;
        }

        public void setBank_cardnumber(String bank_cardnumber) {
            this.bank_cardnumber = bank_cardnumber;
        }

        public String getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(String addressinfo) {
            this.addressinfo = addressinfo;
        }

        public String getRemarkinfo() {
            return remarkinfo;
        }

        public void setRemarkinfo(String remarkinfo) {
            this.remarkinfo = remarkinfo;
        }

        public String getVice_work_type() {
            return vice_work_type;
        }

        public void setVice_work_type(String vice_work_type) {
            this.vice_work_type = vice_work_type;
        }

        public String getWork_shop() {
            return work_shop;
        }

        public void setWork_shop(String work_shop) {
            this.work_shop = work_shop;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getOpen_id_name() {
            return open_id_name;
        }

        public void setOpen_id_name(String open_id_name) {
            this.open_id_name = open_id_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPopedom1() {
            return Popedom1;
        }

        public void setPopedom1(String Popedom1) {
            this.Popedom1 = Popedom1;
        }

        public String getPopedom2() {
            return Popedom2;
        }

        public void setPopedom2(String Popedom2) {
            this.Popedom2 = Popedom2;
        }

        public String getPopedom3() {
            return Popedom3;
        }

        public void setPopedom3(String Popedom3) {
            this.Popedom3 = Popedom3;
        }

        public String getPopedom4() {
            return Popedom4;
        }

        public void setPopedom4(String Popedom4) {
            this.Popedom4 = Popedom4;
        }

        public String getPopedom5() {
            return Popedom5;
        }

        public void setPopedom5(String Popedom5) {
            this.Popedom5 = Popedom5;
        }

        public String getPopedom6() {
            return Popedom6;
        }

        public void setPopedom6(String Popedom6) {
            this.Popedom6 = Popedom6;
        }

        public String getPopedom7() {
            return Popedom7;
        }

        public void setPopedom7(String Popedom7) {
            this.Popedom7 = Popedom7;
        }

        public String getPopedom8() {
            return Popedom8;
        }

        public void setPopedom8(String Popedom8) {
            this.Popedom8 = Popedom8;
        }

        public String getPopedom9() {
            return Popedom9;
        }

        public void setPopedom9(String Popedom9) {
            this.Popedom9 = Popedom9;
        }

        public String getPopedom10() {
            return Popedom10;
        }

        public void setPopedom10(String Popedom10) {
            this.Popedom10 = Popedom10;
        }

        public String getPopedom11() {
            return Popedom11;
        }

        public void setPopedom11(String Popedom11) {
            this.Popedom11 = Popedom11;
        }

        public String getPopedom12() {
            return Popedom12;
        }

        public void setPopedom12(String Popedom12) {
            this.Popedom12 = Popedom12;
        }

        public String getPopedom13() {
            return Popedom13;
        }

        public void setPopedom13(String Popedom13) {
            this.Popedom13 = Popedom13;
        }

        public String getPopedom14() {
            return Popedom14;
        }

        public void setPopedom14(String Popedom14) {
            this.Popedom14 = Popedom14;
        }

        public String getPopedom15() {
            return Popedom15;
        }

        public void setPopedom15(String Popedom15) {
            this.Popedom15 = Popedom15;
        }

        public String getPopedom16() {
            return Popedom16;
        }

        public void setPopedom16(String Popedom16) {
            this.Popedom16 = Popedom16;
        }

        public String getPopedom17() {
            return Popedom17;
        }

        public void setPopedom17(String Popedom17) {
            this.Popedom17 = Popedom17;
        }

        public String getPopedom18() {
            return Popedom18;
        }

        public void setPopedom18(String Popedom18) {
            this.Popedom18 = Popedom18;
        }

        public int getZhiji_id() {
            return zhiji_id;
        }

        public void setZhiji_id(int zhiji_id) {
            this.zhiji_id = zhiji_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public int getGrade_type() {
            return grade_type;
        }

        public void setGrade_type(int grade_type) {
            this.grade_type = grade_type;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public int getBrandclass_id() {
            return brandclass_id;
        }

        public void setBrandclass_id(int brandclass_id) {
            this.brandclass_id = brandclass_id;
        }

        public String getWork_types() {
            return work_types;
        }

        public void setWork_types(String work_types) {
            this.work_types = work_types;
        }

        public int getIs_jiguang() {
            return is_jiguang;
        }

        public void setIs_jiguang(int is_jiguang) {
            this.is_jiguang = is_jiguang;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.staffnumber);
            dest.writeString(this.staffname);
            dest.writeString(this.password);
            dest.writeInt(this.department_id);
            dest.writeString(this.department_name);
            dest.writeInt(this.main_position_id);
            dest.writeString(this.main_position_name);
            dest.writeString(this.main_position_code);
            dest.writeString(this.position_id);
            dest.writeString(this.position_code);
            dest.writeString(this.zhiji_name);
            dest.writeString(this.turestaffname);
            dest.writeString(this.group_company_name);
            dest.writeString(this.brandclass);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.setkey);
            dest.writeInt(this.group_id);
            dest.writeString(this.group_name);
            dest.writeInt(this.is_group_admin);
            dest.writeString(this.sex);
            dest.writeString(this.entry_date);
            dest.writeString(this.leave_date);
            dest.writeString(this.birthday);
            dest.writeString(this.IDcard);
            dest.writeString(this.nation);
            dest.writeString(this.place_origin);
            dest.writeString(this.political);
            dest.writeString(this.education);
            dest.writeString(this.graduation_school);
            dest.writeString(this.marital_status);
            dest.writeString(this.contract_endday);
            dest.writeString(this.telephone);
            dest.writeString(this.QQ);
            dest.writeString(this.email);
            dest.writeString(this.weixin);
            dest.writeString(this.a_discount);
            dest.writeString(this.b_discount);
            dest.writeString(this.a_cost);
            dest.writeString(this.b_cost);
            dest.writeString(this.liaison_person);
            dest.writeString(this.liaison_tel);
            dest.writeString(this.salary_money);
            dest.writeString(this.social_security);
            dest.writeString(this.bank_cardnumber);
            dest.writeString(this.addressinfo);
            dest.writeString(this.remarkinfo);
            dest.writeString(this.vice_work_type);
            dest.writeString(this.work_shop);
            dest.writeString(this.open_id);
            dest.writeString(this.open_id_name);
            dest.writeInt(this.status);
            dest.writeString(this.Popedom1);
            dest.writeString(this.Popedom2);
            dest.writeString(this.Popedom3);
            dest.writeString(this.Popedom4);
            dest.writeString(this.Popedom5);
            dest.writeString(this.Popedom6);
            dest.writeString(this.Popedom7);
            dest.writeString(this.Popedom8);
            dest.writeString(this.Popedom9);
            dest.writeString(this.Popedom10);
            dest.writeString(this.Popedom11);
            dest.writeString(this.Popedom12);
            dest.writeString(this.Popedom13);
            dest.writeString(this.Popedom14);
            dest.writeString(this.Popedom15);
            dest.writeString(this.Popedom16);
            dest.writeString(this.Popedom17);
            dest.writeString(this.Popedom18);
            dest.writeInt(this.zhiji_id);
            dest.writeString(this.add_time);
            dest.writeString(this.sn);
            dest.writeInt(this.grade_type);
            dest.writeString(this.last_login_ip);
            dest.writeString(this.last_login_time);
            dest.writeInt(this.brandclass_id);
            dest.writeString(this.work_types);
            dest.writeInt(this.is_jiguang);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.staffnumber = in.readString();
            this.staffname = in.readString();
            this.password = in.readString();
            this.department_id = in.readInt();
            this.department_name = in.readString();
            this.main_position_id = in.readInt();
            this.main_position_name = in.readString();
            this.main_position_code = in.readString();
            this.position_id = in.readString();
            this.position_code = in.readString();
            this.zhiji_name = in.readString();
            this.turestaffname = in.readString();
            this.group_company_name = in.readString();
            this.brandclass = in.readString();
            this.shop_name = in.readString();
            this.shop_code = in.readString();
            this.setkey = in.readString();
            this.group_id = in.readInt();
            this.group_name = in.readString();
            this.is_group_admin = in.readInt();
            this.sex = in.readString();
            this.entry_date = in.readString();
            this.leave_date = in.readString();
            this.birthday = in.readString();
            this.IDcard = in.readString();
            this.nation = in.readString();
            this.place_origin = in.readString();
            this.political = in.readString();
            this.education = in.readString();
            this.graduation_school = in.readString();
            this.marital_status = in.readString();
            this.contract_endday = in.readString();
            this.telephone = in.readString();
            this.QQ = in.readString();
            this.email = in.readString();
            this.weixin = in.readString();
            this.a_discount = in.readString();
            this.b_discount = in.readString();
            this.a_cost = in.readString();
            this.b_cost = in.readString();
            this.liaison_person = in.readString();
            this.liaison_tel = in.readString();
            this.salary_money = in.readString();
            this.social_security = in.readString();
            this.bank_cardnumber = in.readString();
            this.addressinfo = in.readString();
            this.remarkinfo = in.readString();
            this.vice_work_type = in.readString();
            this.work_shop = in.readString();
            this.open_id = in.readString();
            this.open_id_name = in.readString();
            this.status = in.readInt();
            this.Popedom1 = in.readString();
            this.Popedom2 = in.readString();
            this.Popedom3 = in.readString();
            this.Popedom4 = in.readString();
            this.Popedom5 = in.readString();
            this.Popedom6 = in.readString();
            this.Popedom7 = in.readString();
            this.Popedom8 = in.readString();
            this.Popedom9 = in.readString();
            this.Popedom10 = in.readString();
            this.Popedom11 = in.readString();
            this.Popedom12 = in.readString();
            this.Popedom13 = in.readString();
            this.Popedom14 = in.readString();
            this.Popedom15 = in.readString();
            this.Popedom16 = in.readString();
            this.Popedom17 = in.readString();
            this.Popedom18 = in.readString();
            this.zhiji_id = in.readInt();
            this.add_time = in.readString();
            this.sn = in.readString();
            this.grade_type = in.readInt();
            this.last_login_ip = in.readString();
            this.last_login_time = in.readString();
            this.brandclass_id = in.readInt();
            this.work_types = in.readString();
            this.is_jiguang = in.readInt();
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
