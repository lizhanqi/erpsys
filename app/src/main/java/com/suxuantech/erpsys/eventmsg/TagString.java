package com.suxuantech.erpsys.eventmsg;

import android.os.Parcel;
import android.os.Parcelable;

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
 * @author Created by 李站旗 on 2018/4/20 0020 14:52 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 选择页面兼容如果有重复的数据,可以根据唯一标志符区分
 */

public class TagString implements Parcelable {
    String text;
    Object tag;

    public TagString(String text) {
        this.text = text;
    }

    public TagString(String text, Object tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeParcelable((Parcelable) this.tag, flags);
    }

    protected TagString(Parcel in) {
        this.text = in.readString();
        this.tag = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Parcelable.Creator<TagString> CREATOR = new Parcelable.Creator<TagString>() {
        @Override
        public TagString createFromParcel(Parcel source) {
            return new TagString(source);
        }

        @Override
        public TagString[] newArray(int size) {
            return new TagString[size];
        }
    };
}
