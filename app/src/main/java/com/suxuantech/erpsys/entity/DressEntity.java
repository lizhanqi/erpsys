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
 * @author Created by 李站旗 on 2018/4/26 0026 13:44 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class DressEntity extends BaseResult2 <DressEntity.DataBean>{

    /**
     * Code : 200
     * Data : [{"id":15,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","dress_type":"--单据类型--","choosedate":"20180204","choosetime":"","getdate":"20180204","gettime":"","secretaireman":"纪金秀","returndate":"","returtime":"","dressremark":"","dressstate":null,"returnman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","dress_price":100,"dress_count":1,"dress_cost":0,"storeconsuitant1":"","dress_designer":"","dress_zhuguan":"","create_time":null,"setkey":"ZX002","dress_size":"","sj_weddingdate":"","dress_designerbz":""},{"id":17,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","dress_type":"--单据类型--","choosedate":"20180204","choosetime":"","getdate":"20180306","gettime":"","secretaireman":"纪金秀","returndate":"","returtime":"","dressremark":"","dressstate":null,"returnman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","dress_price":100,"dress_count":1,"dress_cost":0,"storeconsuitant1":"","dress_designer":"","dress_zhuguan":"","create_time":null,"setkey":"ZX002","dress_size":"","sj_weddingdate":"","dress_designerbz":""},{"id":18,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","dress_type":"--单据类型--","choosedate":"20180204","choosetime":"","getdate":"20180306","gettime":"","secretaireman":"纪金秀","returndate":"20180308","returtime":"","dressremark":"","dressstate":null,"returnman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","dress_price":100,"dress_count":1,"dress_cost":0,"storeconsuitant1":"","dress_designer":"","dress_zhuguan":"","create_time":null,"setkey":"ZX002","dress_size":"","sj_weddingdate":"","dress_designerbz":""},{"id":51,"orderId":"SY18012000007","customerid":"c3ddb62a-dcec-4b65-8d77-f335fbbd00f8","dress_type":"结婚礼服","choosedate":"","choosetime":"","getdate":"20180301","gettime":"","secretaireman":"","returndate":"20180316","returtime":"","dressremark":"","dressstate":"","returnman":"","shop_code":"ZX002","shop_name":"沈阳时尚经典婚纱店","dress_price":0,"dress_count":1,"dress_cost":0,"storeconsuitant1":"","dress_designer":"","dress_zhuguan":"","create_time":"2018-03-29 15:05:18.000","setkey":"ZX002","dress_size":"0","sj_weddingdate":"","dress_designerbz":""}]
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
         * id : 15
         * orderId : SY18012000007
         * customerid : c3ddb62a-dcec-4b65-8d77-f335fbbd00f8
         * dress_type : --单据类型--
         * choosedate : 20180204
         * choosetime :
         * getdate : 20180204
         * gettime :
         * secretaireman : 纪金秀
         * returndate :
         * returtime :
         * dressremark :
         * dressstate : null
         * returnman :
         * shop_code : ZX002
         * shop_name : 沈阳时尚经典婚纱店
         * dress_price : 100
         * dress_count : 1
         * dress_cost : 0
         * storeconsuitant1 :
         * dress_designer :
         * dress_zhuguan :
         * create_time : null
         * setkey : ZX002
         * dress_size :
         * sj_weddingdate :
         * dress_designerbz :
         */
        private boolean isExpand;
        private int id;
        private String orderId;
        private String customerid;
        private String dress_type;
        private String choosedate;
        private String choosetime;
        private String getdate;
        private String gettime;
        private String secretaireman;
        private String returndate;
        private String returtime;
        private String dressremark;
        private Object dressstate;
        private String returnman;
        private String shop_code;
        private String shop_name;
        private int dress_price;
        private int dress_count;
        private int dress_cost;
        private String storeconsuitant1;
        private String dress_designer;
        private String dress_zhuguan;
        private Object create_time;
        private String setkey;
        private String dress_size;
        private String sj_weddingdate;
        private String dress_designerbz;

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public boolean isExpand() {
            return isExpand;
        }

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

        public String getDress_type() {
            return dress_type;
        }

        public void setDress_type(String dress_type) {
            this.dress_type = dress_type;
        }

        public String getChoosedate() {
            return choosedate;
        }

        public void setChoosedate(String choosedate) {
            this.choosedate = choosedate;
        }

        public String getChoosetime() {
            return choosetime;
        }

        public void setChoosetime(String choosetime) {
            this.choosetime = choosetime;
        }

        public String getGetdate() {
            return getdate;
        }

        public void setGetdate(String getdate) {
            this.getdate = getdate;
        }

        public String getGettime() {
            return gettime;
        }

        public void setGettime(String gettime) {
            this.gettime = gettime;
        }

        public String getSecretaireman() {
            return secretaireman;
        }

        public void setSecretaireman(String secretaireman) {
            this.secretaireman = secretaireman;
        }

        public String getReturndate() {
            return returndate;
        }

        public void setReturndate(String returndate) {
            this.returndate = returndate;
        }

        public String getReturtime() {
            return returtime;
        }

        public void setReturtime(String returtime) {
            this.returtime = returtime;
        }

        public String getDressremark() {
            return dressremark;
        }

        public void setDressremark(String dressremark) {
            this.dressremark = dressremark;
        }

        public Object getDressstate() {
            return dressstate;
        }

        public void setDressstate(Object dressstate) {
            this.dressstate = dressstate;
        }

        public String getReturnman() {
            return returnman;
        }

        public void setReturnman(String returnman) {
            this.returnman = returnman;
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

        public int getDress_price() {
            return dress_price;
        }

        public void setDress_price(int dress_price) {
            this.dress_price = dress_price;
        }

        public int getDress_count() {
            return dress_count;
        }

        public void setDress_count(int dress_count) {
            this.dress_count = dress_count;
        }

        public int getDress_cost() {
            return dress_cost;
        }

        public void setDress_cost(int dress_cost) {
            this.dress_cost = dress_cost;
        }

        public String getStoreconsuitant1() {
            return storeconsuitant1;
        }

        public void setStoreconsuitant1(String storeconsuitant1) {
            this.storeconsuitant1 = storeconsuitant1;
        }

        public String getDress_designer() {
            return dress_designer;
        }

        public void setDress_designer(String dress_designer) {
            this.dress_designer = dress_designer;
        }

        public String getDress_zhuguan() {
            return dress_zhuguan;
        }

        public void setDress_zhuguan(String dress_zhuguan) {
            this.dress_zhuguan = dress_zhuguan;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getSetkey() {
            return setkey;
        }

        public void setSetkey(String setkey) {
            this.setkey = setkey;
        }

        public String getDress_size() {
            return dress_size;
        }

        public void setDress_size(String dress_size) {
            this.dress_size = dress_size;
        }

        public String getSj_weddingdate() {
            return sj_weddingdate;
        }

        public void setSj_weddingdate(String sj_weddingdate) {
            this.sj_weddingdate = sj_weddingdate;
        }

        public String getDress_designerbz() {
            return dress_designerbz;
        }

        public void setDress_designerbz(String dress_designerbz) {
            this.dress_designerbz = dress_designerbz;
        }
    }
}

