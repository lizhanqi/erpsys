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
 * @author Created by 李站旗 on 2018/5/7 0007 11:15 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class PhotoSchemeMonthEntity  extends  BaseResult{

    /**
     * Code : 200
     * Data : [{"riqi":"20180430","xingqi":"一","pccount":"0/8"},{"riqi":"20180501","xingqi":"二","pccount":"0/8"},{"riqi":"20180502","xingqi":"三","pccount":"0/8"},{"riqi":"20180503","xingqi":"四","pccount":"0/9"},{"riqi":"20180504","xingqi":"五","pccount":"0/8"},{"riqi":"20180505","xingqi":"六","pccount":"0/10"},{"riqi":"20180506","xingqi":"日","pccount":"0/12"},{"riqi":"20180507","xingqi":"一","pccount":"0/8"},{"riqi":"20180508","xingqi":"二","pccount":"0/8"},{"riqi":"20180509","xingqi":"三","pccount":"0/8"},{"riqi":"20180510","xingqi":"四","pccount":"0/9"},{"riqi":"20180511","xingqi":"五","pccount":"0/8"},{"riqi":"20180512","xingqi":"六","pccount":"0/10"},{"riqi":"20180513","xingqi":"日","pccount":"0/10"},{"riqi":"20180514","xingqi":"一","pccount":"0/8"},{"riqi":"20180515","xingqi":"二","pccount":"0/8"},{"riqi":"20180516","xingqi":"三","pccount":"0/8"},{"riqi":"20180517","xingqi":"四","pccount":"0/9"},{"riqi":"20180518","xingqi":"五","pccount":"0/8"},{"riqi":"20180519","xingqi":"六","pccount":"0/10"},{"riqi":"20180520","xingqi":"日","pccount":"0/10"},{"riqi":"20180521","xingqi":"一","pccount":"0/8"},{"riqi":"20180522","xingqi":"二","pccount":"0/8"},{"riqi":"20180523","xingqi":"三","pccount":"0/8"},{"riqi":"20180524","xingqi":"四","pccount":"0/9"},{"riqi":"20180525","xingqi":"五","pccount":"0/8"},{"riqi":"20180526","xingqi":"六","pccount":"0/10"},{"riqi":"20180527","xingqi":"日","pccount":"0/10"},{"riqi":"20180528","xingqi":"一","pccount":"0/8"},{"riqi":"20180529","xingqi":"二","pccount":"0/8"},{"riqi":"20180530","xingqi":"三","pccount":"0/8"},{"riqi":"20180531","xingqi":"四","pccount":"0/9"},{"riqi":"20180601","xingqi":"五","pccount":"0/8"},{"riqi":"20180602","xingqi":"六","pccount":"0/10"},{"riqi":"20180603","xingqi":"日","pccount":"0/10"},{"riqi":"20180604","xingqi":"一","pccount":"0/8"},{"riqi":"20180605","xingqi":"二","pccount":"0/8"},{"riqi":"20180606","xingqi":"三","pccount":"0/8"},{"riqi":"20180607","xingqi":"四","pccount":"0/9"},{"riqi":"20180608","xingqi":"五","pccount":"0/8"},{"riqi":"20180609","xingqi":"六","pccount":"0/10"},{"riqi":"20180610","xingqi":"日","pccount":"0/10"}]
     */

    private List<PhotoSchemeMonthEntity.DataBean> Data;
    public List<PhotoSchemeMonthEntity.DataBean> getData() {
        return Data;
    }

    public void setData(List<PhotoSchemeMonthEntity.DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * riqi : 20180430
         * xingqi : 一
         * pccount : 0/8
         */

        private String riqi;
        private String xingqi;
        private String pccount;

        public String getRiqi() {
            return riqi;
        }

        public void setRiqi(String riqi) {
            this.riqi = riqi;
        }

        public String getXingqi() {
            return xingqi;
        }

        public void setXingqi(String xingqi) {
            this.xingqi = xingqi;
        }

        public String getPccount() {
            return pccount;
        }

        public void setPccount(String pccount) {
            this.pccount = pccount;
        }
    }
}
