package com.suxuantech.erpsys.entity;

import java.io.Serializable;
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
 * @author Created by 李站旗 on 2018/5/9 0009 10:52 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class PaymentDetailsEntity extends BaseResult {

    /**
     * Code : 200
     * Data : {"ms":[{"id":"62","orderId":"SY18040200061","payclass":"门市收款","paytype":"现金","paymentdate":"20180404","payment_money":"7000.00","cashierman":"海芹","fundname":"定金"}],"lf":[{"id":"60","orderId":"SY18040200061","payclass":"礼服收款","paytype":"现金","paymentdate":"20180404","payment_money":"100.00","cashierman":"海芹","fundname":"定金"}],"hz":[{"id":"61","orderId":"SY18040200061","payclass":"化妆收款","paytype":"现金","paymentdate":"20180404","payment_money":"10.00","cashierman":"海芹","fundname":"定金"}],"vc":[]}
     */

    private DataBean Data;
    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {

        private List<MsBean> ms;
        private List<LfBean> lf;
        private List<HzBean> hz;
        private List<?> vc;

        public List<MsBean> getMs() {
            return ms;
        }

        public void setMs(List<MsBean> ms) {
            this.ms = ms;
        }

        public List<LfBean> getLf() {
            return lf;
        }

        public void setLf(List<LfBean> lf) {
            this.lf = lf;
        }

        public List<HzBean> getHz() {
            return hz;
        }

        public void setHz(List<HzBean> hz) {
            this.hz = hz;
        }

        public List<?> getVc() {
            return vc;
        }

        public void setVc(List<?> vc) {
            this.vc = vc;
        }

        public static class MsBean {
            /**
             * id : 62
             * orderId : SY18040200061
             * payclass : 门市收款
             * paytype : 现金
             * paymentdate : 20180404
             * payment_money : 7000.00
             * cashierman : 海芹
             * fundname : 定金
             */

            private String id;
            private String orderId;
            private String payclass;
            private String paytype;
            private String paymentdate;
            private String payment_money;
            private String cashierman;
            private String fundname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getPayment_money() {
                return payment_money;
            }

            public void setPayment_money(String payment_money) {
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
        }

        public static class LfBean {
            /**
             * id : 60
             * orderId : SY18040200061
             * payclass : 礼服收款
             * paytype : 现金
             * paymentdate : 20180404
             * payment_money : 100.00
             * cashierman : 海芹
             * fundname : 定金
             */

            private String id;
            private String orderId;
            private String payclass;
            private String paytype;
            private String paymentdate;
            private String payment_money;
            private String cashierman;
            private String fundname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getPayment_money() {
                return payment_money;
            }

            public void setPayment_money(String payment_money) {
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
        }

        public static class HzBean {
            /**
             * id : 61
             * orderId : SY18040200061
             * payclass : 化妆收款
             * paytype : 现金
             * paymentdate : 20180404
             * payment_money : 10.00
             * cashierman : 海芹
             * fundname : 定金
             */

            private String id;
            private String orderId;
            private String payclass;
            private String paytype;
            private String paymentdate;
            private String payment_money;
            private String cashierman;
            private String fundname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getPayment_money() {
                return payment_money;
            }

            public void setPayment_money(String payment_money) {
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
        }
    }
}
