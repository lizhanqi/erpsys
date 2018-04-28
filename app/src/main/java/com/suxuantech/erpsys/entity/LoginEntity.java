package com.suxuantech.erpsys.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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
 * @author Created by 李站旗 on 2018/4/11 0011 16:20 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 登录实体
 */
public class LoginEntity extends BaseResult implements Parcelable {
    /**
     * Code : 200
     * Msg : success
     * Data : [{"staff_id":"404","session_id":"DduRzvHGcP","ip":"114.242.53.96","login_type":"PC","login_method":"user_name","expiry":"2018-04-12 16:21:08","staffname":"小飞","staffnumber":"SY001","brandclass":"时尚经典事业部","shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","belong_shop_name":"沈阳时尚经典婚纱店","belong_shop_code":"ZX002","is_marketing":"2","is_sk":"2","shop_type":"1","receiv_shop_code":"","is_group":"2","end_date":"","setkey":"","department_name":"管理部","department_id":"54","main_position_name":"管理员","main_position_code":"p15196150267964","grade_type":"4","position_code":"p15169100931034,p15169793315051,p15169796813421,p15169798813228,p15169810882882,p15169811036060,p15169901128683,p15175413769707,p15211111695472,p15211111698673,p15211111698250,p15211111696338","status":"1","sn":"SY","main_work_type":"22","work_type":"22,1,7,15,,6,14","open_id":"o3mGr0rWvmqIJjD3yDdSyznGPYsY","is_belong":"4","brandclass_id":"1"}]
     */
    private String Msg;
    private List<DataBean> Data;

    @Override
    public String getMsg() {
        return Msg;
    }

    @Override
    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * staff_id : 404
         * session_id : DduRzvHGcP
         * ip : 114.242.53.96
         * login_type : PC
         * login_method : user_name
         * expiry : 2018-04-12 16:21:08
         * staffname : 小飞
         * staffnumber : SY001
         * brandclass : 时尚经典事业部
         * shop_name : 沈阳时尚经典婚纱店
         * shop_code : ZX002
         * belong_shop_name : 沈阳时尚经典婚纱店
         * belong_shop_code : ZX002
         * is_marketing : 2
         * is_sk : 2
         * shop_type : 1
         * receiv_shop_code :
         * is_group : 2
         * end_date :
         * setkey :
         * department_name : 管理部
         * department_id : 54
         * main_position_name : 管理员
         * main_position_code : p15196150267964
         * grade_type : 4
         * position_code : p15169100931034,p15169793315051,p15169796813421,p15169798813228,p15169810882882,p15169811036060,p15169901128683,p15175413769707,p15211111695472,p15211111698673,p15211111698250,p15211111696338
         * status : 1
         * sn : SY
         * main_work_type : 22
         * work_type : 22,1,7,15,,6,14
         * open_id : o3mGr0rWvmqIJjD3yDdSyznGPYsY
         * is_belong : 4
         * brandclass_id : 1
         */

        private String staff_id;
        private String session_id;
        private String ip;
        private String login_type;
        private String login_method;
        private String expiry;
        private String staffname;
        private String staffnumber;
        private String brandclass;
        private String shop_name;
        private String shop_code;
        private String belong_shop_name;
        private String belong_shop_code;
        private String is_marketing;
        private String is_sk;
        private String shop_type;
        private String receiv_shop_code;
        private String is_group;
        private String end_date;
        private String setkey;
        private String department_name;
        private String department_id;
        private String main_position_name;
        private String main_position_code;
        private String grade_type;
        private String position_code;
        private String status;
        private String sn;
        private String main_work_type;
        private String work_type;
        private String open_id;
        private String is_belong;
        private String brandclass_id;

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
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

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getReceiv_shop_code() {
            return receiv_shop_code;
        }

        public void setReceiv_shop_code(String receiv_shop_code) {
            this.receiv_shop_code = receiv_shop_code;
        }

        public String getIs_group() {
            return is_group;
        }

        public void setIs_group(String is_group) {
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

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
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

        public String getGrade_type() {
            return grade_type;
        }

        public void setGrade_type(String grade_type) {
            this.grade_type = grade_type;
        }

        public String getPosition_code() {
            return position_code;
        }

        public void setPosition_code(String position_code) {
            this.position_code = position_code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getIs_belong() {
            return is_belong;
        }

        public void setIs_belong(String is_belong) {
            this.is_belong = is_belong;
        }

        public String getBrandclass_id() {
            return brandclass_id;
        }

        public void setBrandclass_id(String brandclass_id) {
            this.brandclass_id = brandclass_id;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Msg);
        dest.writeList(this.Data);
    }

    public LoginEntity() {
    }

    protected LoginEntity(Parcel in) {
        this.Msg = in.readString();
        this.Data = new ArrayList<DataBean>();
        in.readList(this.Data, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<LoginEntity> CREATOR = new Parcelable.Creator<LoginEntity>() {
        @Override
        public LoginEntity createFromParcel(Parcel source) {
            return new LoginEntity(source);
        }

        @Override
        public LoginEntity[] newArray(int size) {
            return new LoginEntity[size];
        }
    };
}
