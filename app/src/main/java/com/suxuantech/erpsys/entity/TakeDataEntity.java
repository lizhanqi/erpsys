package com.suxuantech.erpsys.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

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
public class TakeDataEntity extends BaseResult2<TakeDataEntity.DataBean> {

    /**
     * Code : 200
     * Data : [{"id":428,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","sorting":8,"categories":"摄影","consumption_name":"8*9水晶相册","amount":1,"remarks":"","transfer_certificate":1,"take_away_date":"2018-04-27 00:00:00.000","item_pagecount":33,"item_sheetscount":10,"take_away_time":null,"whethercantake":"完成","whether_already_take":"已取","size":"","getuser":"萌萌","price":0,"cost":12,"total":0,"send_count":0,"shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","issources":0,"create_time":"2018-02-01 18:52:33.000","take_can_date":"2018-04-18 00:00:00.000","Urgent":"","take_urgent_date":null,"sj_sendtype":"","sj_senddate":null,"sj_sendname":"","sj_sendnumberplate":"","sj_sendphone":"","sj_sendaddress":"","sj_sendremarks":"","sj_area":""}]
     */

    @SerializedName("Data")
    private List<DataBean> DataX;

    public List<DataBean> getDataX() {
        return DataX;
    }

    public void setDataX(List<DataBean> DataX) {
        this.DataX = DataX;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 428
         * orderId : SY18012000007
         * customerid : c3ddb62a-dcec-4b65-8d77-f335fbbd00f8
         * sorting : 8
         * categories : 摄影
         * consumption_name : 8*9水晶相册
         * amount : 1
         * remarks :
         * transfer_certificate : 1
         * take_away_date : 2018-04-27 00:00:00.000
         * item_pagecount : 33
         * item_sheetscount : 10
         * take_away_time : null
         * whethercantake : 完成
         * whether_already_take : 已取
         * size :
         * getuser : 萌萌
         * price : 0
         * cost : 12
         * total : 0
         * send_count : 0
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * issources : 0
         * create_time : 2018-02-01 18:52:33.000
         * take_can_date : 2018-04-18 00:00:00.000
         * Urgent :
         * take_urgent_date : null
         * sj_sendtype :
         * sj_senddate : null
         * sj_sendname :
         * sj_sendnumberplate :
         * sj_sendphone :
         * sj_sendaddress :
         * sj_sendremarks :
         * sj_area :
         */

        private int id;
        private String orderId;
        private String customerid;
        private int sorting;
        private String categories;
        private String consumption_name;
        private int amount;
        private String remarks;
        private int transfer_certificate;
        private String take_away_date;
        private int item_pagecount;
        private int item_sheetscount;
        private String take_away_time;
        private String whethercantake;
        private String whether_already_take;
        private String size;
        private String getuser;
        private int price;
        private int cost;
        private int total;
        private int send_count;
        private String shop_code;
        private String shop_name;
        private int issources;
        private String create_time;
        private String take_can_date;
        private String Urgent;
        private String take_urgent_date;
        private String sj_sendtype;
        private String sj_senddate;
        private String sj_sendname;
        private String sj_sendnumberplate;
        private String sj_sendphone;
        private String sj_sendaddress;
        private String sj_sendremarks;
        private String sj_area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSorting() {
            return sorting;
        }

        public void setSorting(int sorting) {
            this.sorting = sorting;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getConsumption_name() {
            return consumption_name;
        }

        public void setConsumption_name(String consumption_name) {
            this.consumption_name = consumption_name;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getTransfer_certificate() {
            return transfer_certificate;
        }

        public void setTransfer_certificate(int transfer_certificate) {
            this.transfer_certificate = transfer_certificate;
        }

        public String getTake_away_date() {
            return take_away_date;
        }

        public void setTake_away_date(String take_away_date) {
            this.take_away_date = take_away_date;
        }

        public int getItem_pagecount() {
            return item_pagecount;
        }

        public void setItem_pagecount(int item_pagecount) {
            this.item_pagecount = item_pagecount;
        }

        public int getItem_sheetscount() {
            return item_sheetscount;
        }

        public void setItem_sheetscount(int item_sheetscount) {
            this.item_sheetscount = item_sheetscount;
        }

        public String getTake_away_time() {
            return take_away_time;
        }

        public void setTake_away_time(String take_away_time) {
            this.take_away_time = take_away_time;
        }

        public String getWhethercantake() {
            return whethercantake;
        }

        public void setWhethercantake(String whethercantake) {
            this.whethercantake = whethercantake;
        }

        public String getWhether_already_take() {
            return whether_already_take;
        }

        public void setWhether_already_take(String whether_already_take) {
            this.whether_already_take = whether_already_take;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getGetuser() {
            return getuser;
        }

        public void setGetuser(String getuser) {
            this.getuser = getuser;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSend_count() {
            return send_count;
        }

        public void setSend_count(int send_count) {
            this.send_count = send_count;
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

        public int getIssources() {
            return issources;
        }

        public void setIssources(int issources) {
            this.issources = issources;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTake_can_date() {
            return take_can_date;
        }

        public void setTake_can_date(String take_can_date) {
            this.take_can_date = take_can_date;
        }

        public String getUrgent() {
            return Urgent;
        }

        public void setUrgent(String Urgent) {
            this.Urgent = Urgent;
        }

        public String getTake_urgent_date() {
            return take_urgent_date;
        }

        public void setTake_urgent_date(String take_urgent_date) {
            this.take_urgent_date = take_urgent_date;
        }

        public String getSj_sendtype() {
            return sj_sendtype;
        }

        public void setSj_sendtype(String sj_sendtype) {
            this.sj_sendtype = sj_sendtype;
        }

        public String getSj_senddate() {
            return sj_senddate;
        }

        public void setSj_senddate(String sj_senddate) {
            this.sj_senddate = sj_senddate;
        }

        public String getSj_sendname() {
            return sj_sendname;
        }

        public void setSj_sendname(String sj_sendname) {
            this.sj_sendname = sj_sendname;
        }

        public String getSj_sendnumberplate() {
            return sj_sendnumberplate;
        }

        public void setSj_sendnumberplate(String sj_sendnumberplate) {
            this.sj_sendnumberplate = sj_sendnumberplate;
        }

        public String getSj_sendphone() {
            return sj_sendphone;
        }

        public void setSj_sendphone(String sj_sendphone) {
            this.sj_sendphone = sj_sendphone;
        }

        public String getSj_sendaddress() {
            return sj_sendaddress;
        }

        public void setSj_sendaddress(String sj_sendaddress) {
            this.sj_sendaddress = sj_sendaddress;
        }

        public String getSj_sendremarks() {
            return sj_sendremarks;
        }

        public void setSj_sendremarks(String sj_sendremarks) {
            this.sj_sendremarks = sj_sendremarks;
        }

        public String getSj_area() {
            return sj_area;
        }

        public void setSj_area(String sj_area) {
            this.sj_area = sj_area;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.orderId);
            dest.writeString(this.customerid);
            dest.writeInt(this.sorting);
            dest.writeString(this.categories);
            dest.writeString(this.consumption_name);
            dest.writeInt(this.amount);
            dest.writeString(this.remarks);
            dest.writeInt(this.transfer_certificate);
            dest.writeString(this.take_away_date);
            dest.writeInt(this.item_pagecount);
            dest.writeInt(this.item_sheetscount);
            dest.writeString(this.take_away_time);
            dest.writeString(this.whethercantake);
            dest.writeString(this.whether_already_take);
            dest.writeString(this.size);
            dest.writeString(this.getuser);
            dest.writeInt(this.price);
            dest.writeInt(this.cost);
            dest.writeInt(this.total);
            dest.writeInt(this.send_count);
            dest.writeString(this.shop_code);
            dest.writeString(this.shop_name);
            dest.writeInt(this.issources);
            dest.writeString(this.create_time);
            dest.writeString(this.take_can_date);
            dest.writeString(this.Urgent);
            dest.writeString(this.take_urgent_date);
            dest.writeString(this.sj_sendtype);
            dest.writeString(this.sj_senddate);
            dest.writeString(this.sj_sendname);
            dest.writeString(this.sj_sendnumberplate);
            dest.writeString(this.sj_sendphone);
            dest.writeString(this.sj_sendaddress);
            dest.writeString(this.sj_sendremarks);
            dest.writeString(this.sj_area);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.orderId = in.readString();
            this.customerid = in.readString();
            this.sorting = in.readInt();
            this.categories = in.readString();
            this.consumption_name = in.readString();
            this.amount = in.readInt();
            this.remarks = in.readString();
            this.transfer_certificate = in.readInt();
            this.take_away_date = in.readString();
            this.item_pagecount = in.readInt();
            this.item_sheetscount = in.readInt();
            this.take_away_time = in.readString();
            this.whethercantake = in.readString();
            this.whether_already_take = in.readString();
            this.size = in.readString();
            this.getuser = in.readString();
            this.price = in.readInt();
            this.cost = in.readInt();
            this.total = in.readInt();
            this.send_count = in.readInt();
            this.shop_code = in.readString();
            this.shop_name = in.readString();
            this.issources = in.readInt();
            this.create_time = in.readString();
            this.take_can_date = in.readString();
            this.Urgent = in.readString();
            this.take_urgent_date = in.readString();
            this.sj_sendtype = in.readString();
            this.sj_senddate = in.readString();
            this.sj_sendname = in.readString();
            this.sj_sendnumberplate = in.readString();
            this.sj_sendphone = in.readString();
            this.sj_sendaddress = in.readString();
            this.sj_sendremarks = in.readString();
            this.sj_area = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
