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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity extends BaseResult  {

    /**
     * Code : 200
     * Data : [{"allcount":"54","newcount":"38","wjdcount":"49","jdwccount":"4","ycjcount":"8","lscount":"3"}]
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
         * allcount : 54
         * newcount : 38
         * wjdcount : 49
         * jdwccount : 4
         * ycjcount : 8
         * lscount : 3
         */

        private String allcount;
        private String newcount;
        private String wjdcount;
        private String jdwccount;
        private String ycjcount;
        private String lscount;

        public String getAllcount() {
            return allcount;
        }

        public void setAllcount(String allcount) {
            this.allcount = allcount;
        }

        public String getNewcount() {
            return newcount;
        }

        public void setNewcount(String newcount) {
            this.newcount = newcount;
        }

        public String getWjdcount() {
            return wjdcount;
        }

        public void setWjdcount(String wjdcount) {
            this.wjdcount = wjdcount;
        }

        public String getJdwccount() {
            return jdwccount;
        }

        public void setJdwccount(String jdwccount) {
            this.jdwccount = jdwccount;
        }

        public String getYcjcount() {
            return ycjcount;
        }

        public void setYcjcount(String ycjcount) {
            this.ycjcount = ycjcount;
        }

        public String getLscount() {
            return lscount;
        }

        public void setLscount(String lscount) {
            this.lscount = lscount;
        }
    }
}
