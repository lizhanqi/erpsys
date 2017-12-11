package com.suxuantech.erpsys.bean;

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
 * @author Created by 李站旗 on 2017/12/8 0008 21:20 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 获取产品
 */

public class ProductBean extends Result {

    /**
     * Msg : 成功
     * Data : [{"item_name":"放大","item_price":10,"id":6},{"item_name":"放大","item_price":10,"id":7},{"item_name":"精品121","item_price":21,"id":8},{"item_name":"精品","item_price":35,"id":9},{"item_name":"5X7闪亮牛角花框","item_price":10,"id":23},{"item_name":"唇彩","item_price":35,"id":27},{"item_name":"西装","item_price":35,"id":29},{"item_name":"爱丽丝","item_price":35,"id":31},{"item_name":"粉底","item_price":2,"id":32},{"item_name":"隐形","item_price":35,"id":33},{"item_name":"普通3","item_price":35,"id":34},{"item_name":"普通4","item_price":35,"id":35}]
     */

    private String Msg;
    private List<DataBean> Data;

    public String getMsg() {
        return Msg;
    }

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
         * item_name : 放大
         * item_price : 10.0
         * id : 6
         */

        private String item_name;
        private double item_price;
        private int id;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public double getItem_price() {
            return item_price;
        }

        public void setItem_price(double item_price) {
            this.item_price = item_price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
