package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity extends BaseResult2<TempEntity.DataBean> {


    /**
     * Code : 200
     * Data : [{"id":16,"orderId":"SY18012000007","photodate":"2018-01-31 00:00:00.000","phototime":"1300","cameraman":"","photoassistant":"","lighting_engineer":"","designer":"","mentor":"","phototype":"","photostate":"","dresser":"","photonote":"","photocount":null,"locationcount":null,"Interiorcount":null,"shop_name":null,"shop_code":"ZX002","secretaireman":"纪金秀","topic":null,"returndate":null,"getdate":null,"choosedate":"2018-02-24 00:00:00.000","dress_count":null,"photo_shop_code":null,"shexiangshi":null,"jianjishi":null,"zhuozhuangshi":null,"huazhuangzhuli":null,"haijingshu":null,"sheyingshiJB":null,"huazhuangshiJB":null,"dresschoosetime":"1000","dressgettime":"","dressreturtime":"","islast":0,"photostate1":""},{"id":24,"orderId":"SY18012000007","photodate":"2018-01-30 00:00:00.000","phototime":"1300","cameraman":"","photoassistant":"","lighting_engineer":"杨过","designer":"","mentor":"","phototype":"拍照","photostate":"全未拍","dresser":"","photonote":"","photocount":"0","locationcount":0,"Interiorcount":0,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","secretaireman":"王语嫣","topic":"","returndate":null,"getdate":null,"choosedate":"2018-02-24 00:00:00.000","dress_count":0,"photo_shop_code":"ZX002","shexiangshi":null,"jianjishi":null,"zhuozhuangshi":null,"huazhuangzhuli":null,"haijingshu":null,"sheyingshiJB":null,"huazhuangshiJB":null,"dresschoosetime":"1000","dressgettime":"","dressreturtime":"","islast":1,"photostate1":"全未拍"}]
     */

    public static class DataBean implements Parcelable {
        /**
         * id : 16
         * orderId : SY18012000007
         * photodate : 2018-01-31 00:00:00.000
         * phototime : 1300
         * cameraman :
         * photoassistant :
         * lighting_engineer :
         * designer :
         * mentor :
         * phototype :
         * photostate :
         * dresser :
         * photonote :
         * photocount : null
         * locationcount : null
         * Interiorcount : null
         * shop_name : null
         * shop_code : ZX002
         * secretaireman : 纪金秀
         * topic : null
         * returndate : null
         * getdate : null
         * choosedate : 2018-02-24 00:00:00.000
         * dress_count : null
         * photo_shop_code : null
         * shexiangshi : null
         * jianjishi : null
         * zhuozhuangshi : null
         * huazhuangzhuli : null
         * haijingshu : null
         * sheyingshiJB : null
         * huazhuangshiJB : null
         * dresschoosetime : 1000
         * dressgettime :
         * dressreturtime :
         * islast : 0
         * photostate1 :
         */

        private int id;
        private String orderId;
        private String photodate;
        private String phototime;
        private String cameraman;
        private String photoassistant;
        private String lighting_engineer;
        private String designer;
        private String mentor;
        private String phototype;
        private String photostate;
        private String dresser;
        private String photonote;
        private String photocount;
        private String locationcount;
        private String Interiorcount;
        private String shop_name;
        private String shop_code;
        private String secretaireman;
        private String topic;
        private String returndate;
        private String getdate;
        private String choosedate;
        private String dress_count;
        private String photo_shop_code;
        private String shexiangshi;
        private String jianjishi;
        private String zhuozhuangshi;
        private String huazhuangzhuli;
        private String haijingshu;
        private String sheyingshiJB;
        private String huazhuangshiJB;
        private String dresschoosetime;
        private String dressgettime;
        private String dressreturtime;
        private int islast;
        private String photostate1;

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

        public String getPhotodate() {
            return photodate;
        }

        public void setPhotodate(String photodate) {
            this.photodate = photodate;
        }

        public String getPhototime() {
            return phototime;
        }

        public void setPhototime(String phototime) {
            this.phototime = phototime;
        }

        public String getCameraman() {
            return cameraman;
        }

        public void setCameraman(String cameraman) {
            this.cameraman = cameraman;
        }

        public String getPhotoassistant() {
            return photoassistant;
        }

        public void setPhotoassistant(String photoassistant) {
            this.photoassistant = photoassistant;
        }

        public String getLighting_engineer() {
            return lighting_engineer;
        }

        public void setLighting_engineer(String lighting_engineer) {
            this.lighting_engineer = lighting_engineer;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(String designer) {
            this.designer = designer;
        }

        public String getMentor() {
            return mentor;
        }

        public void setMentor(String mentor) {
            this.mentor = mentor;
        }

        public String getPhototype() {
            return phototype;
        }

        public void setPhototype(String phototype) {
            this.phototype = phototype;
        }

        public String getPhotostate() {
            return photostate;
        }

        public void setPhotostate(String photostate) {
            this.photostate = photostate;
        }

        public String getDresser() {
            return dresser;
        }

        public void setDresser(String dresser) {
            this.dresser = dresser;
        }

        public String getPhotonote() {
            return photonote;
        }

        public void setPhotonote(String photonote) {
            this.photonote = photonote;
        }

        public String getPhotocount() {
            return photocount;
        }

        public void setPhotocount(String photocount) {
            this.photocount = photocount;
        }

        public String getLocationcount() {
            return locationcount;
        }

        public void setLocationcount(String locationcount) {
            this.locationcount = locationcount;
        }

        public String getInteriorcount() {
            return Interiorcount;
        }

        public void setInteriorcount(String Interiorcount) {
            this.Interiorcount = Interiorcount;
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

        public String getSecretaireman() {
            return secretaireman;
        }

        public void setSecretaireman(String secretaireman) {
            this.secretaireman = secretaireman;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getReturndate() {
            return returndate;
        }

        public void setReturndate(String returndate) {
            this.returndate = returndate;
        }

        public String getGetdate() {
            return getdate;
        }

        public void setGetdate(String getdate) {
            this.getdate = getdate;
        }

        public String getChoosedate() {
            return choosedate;
        }

        public void setChoosedate(String choosedate) {
            this.choosedate = choosedate;
        }

        public String getDress_count() {
            return dress_count;
        }

        public void setDress_count(String dress_count) {
            this.dress_count = dress_count;
        }

        public String getPhoto_shop_code() {
            return photo_shop_code;
        }

        public void setPhoto_shop_code(String photo_shop_code) {
            this.photo_shop_code = photo_shop_code;
        }

        public String getShexiangshi() {
            return shexiangshi;
        }

        public void setShexiangshi(String shexiangshi) {
            this.shexiangshi = shexiangshi;
        }

        public String getJianjishi() {
            return jianjishi;
        }

        public void setJianjishi(String jianjishi) {
            this.jianjishi = jianjishi;
        }

        public String getZhuozhuangshi() {
            return zhuozhuangshi;
        }

        public void setZhuozhuangshi(String zhuozhuangshi) {
            this.zhuozhuangshi = zhuozhuangshi;
        }

        public String getHuazhuangzhuli() {
            return huazhuangzhuli;
        }

        public void setHuazhuangzhuli(String huazhuangzhuli) {
            this.huazhuangzhuli = huazhuangzhuli;
        }

        public String getHaijingshu() {
            return haijingshu;
        }

        public void setHaijingshu(String haijingshu) {
            this.haijingshu = haijingshu;
        }

        public String getSheyingshiJB() {
            return sheyingshiJB;
        }

        public void setSheyingshiJB(String sheyingshiJB) {
            this.sheyingshiJB = sheyingshiJB;
        }

        public String getHuazhuangshiJB() {
            return huazhuangshiJB;
        }

        public void setHuazhuangshiJB(String huazhuangshiJB) {
            this.huazhuangshiJB = huazhuangshiJB;
        }

        public String getDresschoosetime() {
            return dresschoosetime;
        }

        public void setDresschoosetime(String dresschoosetime) {
            this.dresschoosetime = dresschoosetime;
        }

        public String getDressgettime() {
            return dressgettime;
        }

        public void setDressgettime(String dressgettime) {
            this.dressgettime = dressgettime;
        }

        public String getDressreturtime() {
            return dressreturtime;
        }

        public void setDressreturtime(String dressreturtime) {
            this.dressreturtime = dressreturtime;
        }

        public int getIslast() {
            return islast;
        }

        public void setIslast(int islast) {
            this.islast = islast;
        }

        public String getPhotostate1() {
            return photostate1;
        }

        public void setPhotostate1(String photostate1) {
            this.photostate1 = photostate1;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.orderId);
            dest.writeString(this.photodate);
            dest.writeString(this.phototime);
            dest.writeString(this.cameraman);
            dest.writeString(this.photoassistant);
            dest.writeString(this.lighting_engineer);
            dest.writeString(this.designer);
            dest.writeString(this.mentor);
            dest.writeString(this.phototype);
            dest.writeString(this.photostate);
            dest.writeString(this.dresser);
            dest.writeString(this.photonote);
            dest.writeString(this.photocount);
            dest.writeString(this.locationcount);
            dest.writeString(this.Interiorcount);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_code);
            dest.writeString(this.secretaireman);
            dest.writeString(this.topic);
            dest.writeString(this.returndate);
            dest.writeString(this.getdate);
            dest.writeString(this.choosedate);
            dest.writeString(this.dress_count);
            dest.writeString(this.photo_shop_code);
            dest.writeString(this.shexiangshi);
            dest.writeString(this.jianjishi);
            dest.writeString(this.zhuozhuangshi);
            dest.writeString(this.huazhuangzhuli);
            dest.writeString(this.haijingshu);
            dest.writeString(this.sheyingshiJB);
            dest.writeString(this.huazhuangshiJB);
            dest.writeString(this.dresschoosetime);
            dest.writeString(this.dressgettime);
            dest.writeString(this.dressreturtime);
            dest.writeInt(this.islast);
            dest.writeString(this.photostate1);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.orderId = in.readString();
            this.photodate = in.readString();
            this.phototime = in.readString();
            this.cameraman = in.readString();
            this.photoassistant = in.readString();
            this.lighting_engineer = in.readString();
            this.designer = in.readString();
            this.mentor = in.readString();
            this.phototype = in.readString();
            this.photostate = in.readString();
            this.dresser = in.readString();
            this.photonote = in.readString();
            this.photocount = in.readString();
            this.locationcount = in.readString();
            this.Interiorcount = in.readString();
            this.shop_name = in.readString();
            this.shop_code = in.readString();
            this.secretaireman = in.readString();
            this.topic = in.readString();
            this.returndate = in.readString();
            this.getdate = in.readString();
            this.choosedate = in.readString();
            this.dress_count = in.readString();
            this.photo_shop_code = in.readString();
            this.shexiangshi = in.readString();
            this.jianjishi = in.readString();
            this.zhuozhuangshi = in.readString();
            this.huazhuangzhuli = in.readString();
            this.haijingshu = in.readString();
            this.sheyingshiJB = in.readString();
            this.huazhuangshiJB = in.readString();
            this.dresschoosetime = in.readString();
            this.dressgettime = in.readString();
            this.dressreturtime = in.readString();
            this.islast = in.readInt();
            this.photostate1 = in.readString();
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
