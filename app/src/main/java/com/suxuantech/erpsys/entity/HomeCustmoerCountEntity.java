package com.suxuantech.erpsys.entity;

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
 * @author Created by 李站旗 on 2018/4/23 0023 15:16 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class HomeCustmoerCountEntity extends BaseResult2<HomeCustmoerCountEntity.DataBean>{

    /**
     * Code : 200
     * Msg : success
     * Data : [{"pzcount":"0","qjcount":"0","lfcount":"0","hzcount":"0","jkyycount":"0","xpcount":"0"}]
     */
    public static class DataBean {
        /**
         * pzcount : 0
         * qjcount : 0
         * lfcount : 0
         * hzcount : 0
         * jkyycount : 0
         * xpcount : 0
         */

        private String pzcount;
        private String qjcount;
        private String lfcount;
        private String hzcount;
        private String jkyycount;
        private String xpcount;

        public String getPzcount() {
            return pzcount;
        }

        public void setPzcount(String pzcount) {
            this.pzcount = pzcount;
        }

        public String getQjcount() {
            return qjcount;
        }

        public void setQjcount(String qjcount) {
            this.qjcount = qjcount;
        }

        public String getLfcount() {
            return lfcount;
        }

        public void setLfcount(String lfcount) {
            this.lfcount = lfcount;
        }

        public String getHzcount() {
            return hzcount;
        }

        public void setHzcount(String hzcount) {
            this.hzcount = hzcount;
        }

        public String getJkyycount() {
            return jkyycount;
        }

        public void setJkyycount(String jkyycount) {
            this.jkyycount = jkyycount;
        }

        public String getXpcount() {
            return xpcount;
        }

        public void setXpcount(String xpcount) {
            this.xpcount = xpcount;
        }
    }
}
