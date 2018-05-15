package com.suxuantech.erpsys.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

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
 * @author Created by 李站旗 on 2018/5/14 0014 16:14 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class BaseScheme  implements Serializable, Parcelable {
    private int pcid;
    private String pcday;
    private String pctime;
    private int islock;
    private String orderId;
    private Bundle bundle;

    public String getXingming() {
        return xingming;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    private String xingming;
    private String customerid;
    private String area;
    private String shop_code;

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public String getPcday() {
        return pcday;
    }

    public void setPcday(String pcday) {
        this.pcday = pcday;
    }

    public String getPctime() {
        return pctime;
    }

    public void setPctime(String pctime) {
        this.pctime = pctime;
    }

    public int getIslock() {
        return islock;
    }

    public void setIslock(int islock) {
        this.islock = islock;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pcid);
        dest.writeString(this.pcday);
        dest.writeString(this.pctime);
        dest.writeInt(this.islock);
        dest.writeString(this.orderId);
        dest.writeBundle(bundle);
        dest.writeString(this.xingming);
        dest.writeString(this.customerid);
        dest.writeString(this.area);
        dest.writeString(this.shop_code);
    }

    public BaseScheme() {
    }

    protected BaseScheme(Parcel in) {
        this.pcid = in.readInt();
        this.pcday = in.readString();
        this.pctime = in.readString();
        this.islock = in.readInt();
        this.orderId = in.readString();
        bundle = in.readBundle();
        this.xingming = in.readString();
        this.customerid = in.readString();
        this.area = in.readString();
        this.shop_code = in.readString();
    }

    public static final Parcelable.Creator<BaseScheme> CREATOR = new Parcelable.Creator<BaseScheme>() {
        @Override
        public BaseScheme createFromParcel(Parcel source) {
            return new BaseScheme(source);
        }

        @Override
        public BaseScheme[] newArray(int size) {
            return new BaseScheme[size];
        }
    };
}
