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
 * @author Created by 李站旗 on 2018/4/20 0020 10:59 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class NewOrderTypeEntity extends BaseResult2 <NewOrderTypeEntity.DataBean>{

    /**
     * {"Code":"200","Msg":"success","Data":[{"id":9,"ordertype":"免单2"},{"id":10,"ordertype":"正常2"},{"id":12,"ordertype":"免单3"}]}
     * Code : 200
     * Data : [{"id":9,"ordertype":"免单2"},{"id":10,"ordertype":"正常2"},{"id":12,"ordertype":"免单3"}]
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
         * id : 9
         * ordertype : 免单2
         */

        private int id;
        private String ordertype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }
    }
}
