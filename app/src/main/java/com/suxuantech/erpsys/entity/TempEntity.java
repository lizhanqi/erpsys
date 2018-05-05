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
public class TempEntity  extends  BaseResult{

    /**
     * Code : 200
     * Data : [{"zongmoney":"","rentotal":"0","realmoney":"","jktotal":"0"}]
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
         * zongmoney :
         * rentotal : 0
         * realmoney :
         * jktotal : 0
         */

        private String zongmoney;
        private String rentotal;
        private String realmoney;
        private String jktotal;

        public String getZongmoney() {
            return zongmoney;
        }

        public void setZongmoney(String zongmoney) {
            this.zongmoney = zongmoney;
        }

        public String getRentotal() {
            return rentotal;
        }

        public void setRentotal(String rentotal) {
            this.rentotal = rentotal;
        }

        public String getRealmoney() {
            return realmoney;
        }

        public void setRealmoney(String realmoney) {
            this.realmoney = realmoney;
        }

        public String getJktotal() {
            return jktotal;
        }

        public void setJktotal(String jktotal) {
            this.jktotal = jktotal;
        }
    }
}
