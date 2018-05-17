package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2018/5/16 0016 21:31 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class DeleteCheckEntity extends BaseResult {

    /**
     * Code : 200
     * Data : [{"blankoutannal":0,"order_type":6,"reservelock":0,"addlock":0}]
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * blankoutannal : 0
         * order_type : 6
         * reservelock : 0
         * addlock : 0
         */

        private int blankoutannal;
        private int order_type;
        private int reservelock;
        private int addlock;

        public int getBlankoutannal() {
            return blankoutannal;
        }

        public void setBlankoutannal(int blankoutannal) {
            this.blankoutannal = blankoutannal;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getReservelock() {
            return reservelock;
        }

        public void setReservelock(int reservelock) {
            this.reservelock = reservelock;
        }

        public int getAddlock() {
            return addlock;
        }

        public void setAddlock(int addlock) {
            this.addlock = addlock;
        }
    }
}
