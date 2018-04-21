package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2017/12/8 0008 17:35 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 获取包套
 */

public class PackageEntity extends BaseResult2 <PackageEntity.DataBean>{

    /**
     * Code : 200
     * Data : [{"id":13,"package_name":"6999丽莎水晶包套","package_price":6999,"pack_remark":"","package_cost":86,"iscanuse":1,"package_count":1,"shop_code":"ZX002","setkey":"ZX002","iscanupdate":null,"package_per":30,"paizhaoshu":10,"jingxiushu":11,"ruceshu":20,"shineifz":21,"waijingfz":22,"neijingfz":23,"lvpaifz":21,"fuwufenqu":""},{"id":14,"package_name":"7999丽莎水晶包套","package_price":7999,"pack_remark":"","package_cost":59,"iscanuse":1,"package_count":1,"shop_code":"ZX002","setkey":"ZX002","iscanupdate":null,"package_per":30,"paizhaoshu":0,"jingxiushu":0,"ruceshu":0,"shineifz":0,"waijingfz":0,"neijingfz":0,"lvpaifz":0,"fuwufenqu":""},{"id":15,"package_name":"8999丽莎水晶包套","package_price":8999,"pack_remark":"","package_cost":70,"iscanuse":1,"package_count":12,"shop_code":"ZX002","setkey":"ZX002","iscanupdate":null,"package_per":30,"paizhaoshu":0,"jingxiushu":0,"ruceshu":0,"shineifz":0,"waijingfz":0,"neijingfz":0,"lvpaifz":0,"fuwufenqu":""},{"id":16,"package_name":"自定义组合包套","package_price":0,"pack_remark":"","package_cost":0,"iscanuse":1,"package_count":1,"shop_code":"ZX002","setkey":"ZX002","iscanupdate":null,"package_per":30,"paizhaoshu":0,"jingxiushu":0,"ruceshu":0,"shineifz":0,"waijingfz":0,"neijingfz":0,"lvpaifz":0,"fuwufenqu":""},{"id":17,"package_name":"8999丽莎水晶包套11","package_price":8999,"pack_remark":"","package_cost":40,"iscanuse":1,"package_count":12,"shop_code":"ZX002","setkey":"ZX002","iscanupdate":null,"package_per":30,"paizhaoshu":0,"jingxiushu":0,"ruceshu":0,"shineifz":0,"waijingfz":0,"neijingfz":0,"lvpaifz":0,"fuwufenqu":""}]
     */

    @SerializedName("Data")
    private List<DataBean> DataX;

    public List<DataBean> getDataX() {
        return DataX;
    }

    public void setDataX(List<DataBean> DataX) {
        this.DataX = DataX;
    }

    public static class DataBean {
        /**
         * id : 13
         * package_name : 6999丽莎水晶包套
         * package_price : 6999
         * pack_remark :
         * package_cost : 86
         * iscanuse : 1
         * package_count : 1
         * shop_code : ZX002
         * setkey : ZX002
         * iscanupdate : null
         * package_per : 30
         * paizhaoshu : 10
         * jingxiushu : 11
         * ruceshu : 20
         * shineifz : 21
         * waijingfz : 22
         * neijingfz : 23
         * lvpaifz : 21
         * fuwufenqu :
         */

        private int id;
        private String package_name;
        private int package_price;
        private String pack_remark;
        private int package_cost;
        private int iscanuse;
        private int package_count;
        private String shop_code;
        private String setkey;
        private Object iscanupdate;
        private int package_per;
        private int paizhaoshu;
        private int jingxiushu;
        private int ruceshu;
        private int shineifz;
        private int waijingfz;
        private int neijingfz;
        private int lvpaifz;
        private String fuwufenqu;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public int getPackage_price() {
            return package_price;
        }

        public void setPackage_price(int package_price) {
            this.package_price = package_price;
        }

        public String getPack_remark() {
            return pack_remark;
        }

        public void setPack_remark(String pack_remark) {
            this.pack_remark = pack_remark;
        }

        public int getPackage_cost() {
            return package_cost;
        }

        public void setPackage_cost(int package_cost) {
            this.package_cost = package_cost;
        }

        public int getIscanuse() {
            return iscanuse;
        }

        public void setIscanuse(int iscanuse) {
            this.iscanuse = iscanuse;
        }

        public int getPackage_count() {
            return package_count;
        }

        public void setPackage_count(int package_count) {
            this.package_count = package_count;
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

        public Object getIscanupdate() {
            return iscanupdate;
        }

        public void setIscanupdate(Object iscanupdate) {
            this.iscanupdate = iscanupdate;
        }

        public int getPackage_per() {
            return package_per;
        }

        public void setPackage_per(int package_per) {
            this.package_per = package_per;
        }

        public int getPaizhaoshu() {
            return paizhaoshu;
        }

        public void setPaizhaoshu(int paizhaoshu) {
            this.paizhaoshu = paizhaoshu;
        }

        public int getJingxiushu() {
            return jingxiushu;
        }

        public void setJingxiushu(int jingxiushu) {
            this.jingxiushu = jingxiushu;
        }

        public int getRuceshu() {
            return ruceshu;
        }

        public void setRuceshu(int ruceshu) {
            this.ruceshu = ruceshu;
        }

        public int getShineifz() {
            return shineifz;
        }

        public void setShineifz(int shineifz) {
            this.shineifz = shineifz;
        }

        public int getWaijingfz() {
            return waijingfz;
        }

        public void setWaijingfz(int waijingfz) {
            this.waijingfz = waijingfz;
        }

        public int getNeijingfz() {
            return neijingfz;
        }

        public void setNeijingfz(int neijingfz) {
            this.neijingfz = neijingfz;
        }

        public int getLvpaifz() {
            return lvpaifz;
        }

        public void setLvpaifz(int lvpaifz) {
            this.lvpaifz = lvpaifz;
        }

        public String getFuwufenqu() {
            return fuwufenqu;
        }

        public void setFuwufenqu(String fuwufenqu) {
            this.fuwufenqu = fuwufenqu;
        }
    }
}
