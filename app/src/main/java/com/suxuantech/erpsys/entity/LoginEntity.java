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
    private List<UserEntity> Data;

    @Override
    public String getMsg() {
        return Msg;
    }

    @Override
    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<UserEntity> getData() {
        return Data;
    }

    public void setData(List<UserEntity> Data) {
        this.Data = Data;
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
        this.Data = new ArrayList<UserEntity>();
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
