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
     * Data : [{"id":82,"orderId":"SY18022300026","payclass":"门市收款","paytype":"现金","paymentdate":"20180510","payment_money":200,"cashierman":"海芹","fundname":"定金","xingming":" 文宇","shouji":" 12121121212"},{"id":83,"orderId":"SY18050500102","payclass":"门市收款","paytype":"现金","paymentdate":"20180510","payment_money":3000,"cashierman":"海芹","fundname":"定金","xingming":" kilo","shouji":" 18801266363"},{"id":84,"orderId":"SY18050500102","payclass":"门市收款","paytype":"刷卡","paymentdate":"20180510","payment_money":500,"cashierman":"海芹","fundname":"拍照款","xingming":" kilo","shouji":" 18801266363"},{"id":85,"orderId":"SY18050500102","payclass":"礼服收款","paytype":"现金","paymentdate":"20180510","payment_money":360,"cashierman":"海芹","fundname":"定金","xingming":" kilo","shouji":" 18801266363"},{"id":86,"orderId":"SY18050800103","payclass":"化妆收款","paytype":"现金","paymentdate":"20180510","payment_money":800,"cashierman":"海芹","fundname":"定金","xingming":"夏娟 ","shouji":"15863522451 "},{"id":87,"orderId":"SY18050800103","payclass":"门市收款","paytype":"支付宝转账","paymentdate":"20180510","payment_money":100,"cashierman":"海芹","fundname":"定金","xingming":"夏娟 ","shouji":"15863522451 "}]
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
         * id : 82
         * orderId : SY18022300026
         * payclass : 门市收款
         * paytype : 现金
         * paymentdate : 20180510
         * payment_money : 200
         * cashierman : 海芹
         * fundname : 定金
         * xingming :  文宇
         * shouji :  12121121212
         */

        private int id;
        private String orderId;
        private String payclass;
        private String paytype;
        private String paymentdate;
        private int payment_money;
        private String cashierman;
        private String fundname;
        private String xingming;
        private String shouji;

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

        public String getPayclass() {
            return payclass;
        }

        public void setPayclass(String payclass) {
            this.payclass = payclass;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getPaymentdate() {
            return paymentdate;
        }

        public void setPaymentdate(String paymentdate) {
            this.paymentdate = paymentdate;
        }

        public int getPayment_money() {
            return payment_money;
        }

        public void setPayment_money(int payment_money) {
            this.payment_money = payment_money;
        }

        public String getCashierman() {
            return cashierman;
        }

        public void setCashierman(String cashierman) {
            this.cashierman = cashierman;
        }

        public String getFundname() {
            return fundname;
        }

        public void setFundname(String fundname) {
            this.fundname = fundname;
        }

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getShouji() {
            return shouji;
        }

        public void setShouji(String shouji) {
            this.shouji = shouji;
        }
    }
}
