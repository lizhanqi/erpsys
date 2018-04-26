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
 * @author Created by 李站旗 on 2018/4/25 0025 10:09 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class CustomerProductEntity extends  BaseResult {

    /**
     * Code : 200
     * Data : {"yx":{"yxp":[{"id":"944","orderId":"SY18041900068","sorting":"0","consumption_name":"8999丽莎水晶包套","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"8999.00","total":"8999.00","categories":"套系","issources":"0"}],"yxf":[{"id":"945","orderId":"SY18041900068","sorting":"1","consumption_name":"30寸油画框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"946","orderId":"SY18041900068","sorting":"2","consumption_name":"10寸摆台","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"947","orderId":"SY18041900068","sorting":"3","consumption_name":"5X7闪亮牛角花框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"948","orderId":"SY18041900068","sorting":"4","consumption_name":"唇彩","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"949","orderId":"SY18041900068","sorting":"5","consumption_name":"西装","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"950","orderId":"SY18041900068","sorting":"6","consumption_name":"爱丽丝","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"951","orderId":"SY18041900068","sorting":"7","consumption_name":"8*9水晶相册","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"}]},"ex":{"exf":[]}}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * yx : {"yxp":[{"id":"944","orderId":"SY18041900068","sorting":"0","consumption_name":"8999丽莎水晶包套","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"8999.00","total":"8999.00","categories":"套系","issources":"0"}],"yxf":[{"id":"945","orderId":"SY18041900068","sorting":"1","consumption_name":"30寸油画框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"946","orderId":"SY18041900068","sorting":"2","consumption_name":"10寸摆台","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"947","orderId":"SY18041900068","sorting":"3","consumption_name":"5X7闪亮牛角花框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"},{"id":"948","orderId":"SY18041900068","sorting":"4","consumption_name":"唇彩","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"949","orderId":"SY18041900068","sorting":"5","consumption_name":"西装","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"950","orderId":"SY18041900068","sorting":"6","consumption_name":"爱丽丝","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0"},{"id":"951","orderId":"SY18041900068","sorting":"7","consumption_name":"8*9水晶相册","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0"}]}
         * ex : {"exf":[]}
         */

        private YxBean yx;
        private ExBean ex;

        public YxBean getYx() {
            return yx;
        }

        public void setYx(YxBean yx) {
            this.yx = yx;
        }

        public ExBean getEx() {
            return ex;
        }

        public void setEx(ExBean ex) {
            this.ex = ex;
        }

        public static class YxBean {
            private List<YxpBean> yxp;
            private List<YxfBean> yxf;

            public List<YxpBean> getYxp() {
                return yxp;
            }

            public void setYxp(List<YxpBean> yxp) {
                this.yxp = yxp;
            }

            public List<YxfBean> getYxf() {
                return yxf;
            }

            public void setYxf(List<YxfBean> yxf) {
                this.yxf = yxf;
            }

            public static class YxpBean {
                /**
                 * id : 944
                 * orderId : SY18041900068
                 * sorting : 0
                 * consumption_name : 8999丽莎水晶包套
                 * amount : 1
                 * take_away_date :
                 * whethercantake :
                 * whether_already_take :
                 * price : 8999.00
                 * total : 8999.00
                 * categories : 套系
                 * issources : 0
                 */

                private String id;
                private String orderId;
                private String sorting;
                private String consumption_name;
                private String amount;
                private String take_away_date;
                private String whethercantake;
                private String whether_already_take;
                private String price;
                private String total;
                private String categories;
                private String issources;

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

                public String getSorting() {
                    return sorting;
                }

                public void setSorting(String sorting) {
                    this.sorting = sorting;
                }

                public String getConsumption_name() {
                    return consumption_name;
                }

                public void setConsumption_name(String consumption_name) {
                    this.consumption_name = consumption_name;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getTake_away_date() {
                    return take_away_date;
                }

                public void setTake_away_date(String take_away_date) {
                    this.take_away_date = take_away_date;
                }

                public String getWhethercantake() {
                    return whethercantake;
                }

                public void setWhethercantake(String whethercantake) {
                    this.whethercantake = whethercantake;
                }

                public String getWhether_already_take() {
                    return whether_already_take;
                }

                public void setWhether_already_take(String whether_already_take) {
                    this.whether_already_take = whether_already_take;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getTotal() {
                    return total;
                }

                public void setTotal(String total) {
                    this.total = total;
                }

                public String getCategories() {
                    return categories;
                }

                public void setCategories(String categories) {
                    this.categories = categories;
                }

                public String getIssources() {
                    return issources;
                }

                public void setIssources(String issources) {
                    this.issources = issources;
                }
            }

            public static class YxfBean {
                /**
                 * id : 945
                 * orderId : SY18041900068
                 * sorting : 1
                 * consumption_name : 30寸油画框
                 * amount : 1
                 * take_away_date :
                 * whethercantake :
                 * whether_already_take :
                 * price : 0.00
                 * total : 0.00
                 * categories : 摄影
                 * issources : 0
                 */

                private String id;
                private String orderId;
                private String sorting;
                private String consumption_name;
                private String amount;
                private String take_away_date;
                private String whethercantake;
                private String whether_already_take;
                private String price;
                private String total;
                private String categories;
                private String issources;

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

                public String getSorting() {
                    return sorting;
                }

                public void setSorting(String sorting) {
                    this.sorting = sorting;
                }

                public String getConsumption_name() {
                    return consumption_name;
                }

                public void setConsumption_name(String consumption_name) {
                    this.consumption_name = consumption_name;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getTake_away_date() {
                    return take_away_date;
                }

                public void setTake_away_date(String take_away_date) {
                    this.take_away_date = take_away_date;
                }

                public String getWhethercantake() {
                    return whethercantake;
                }

                public void setWhethercantake(String whethercantake) {
                    this.whethercantake = whethercantake;
                }

                public String getWhether_already_take() {
                    return whether_already_take;
                }

                public void setWhether_already_take(String whether_already_take) {
                    this.whether_already_take = whether_already_take;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getTotal() {
                    return total;
                }

                public void setTotal(String total) {
                    this.total = total;
                }

                public String getCategories() {
                    return categories;
                }

                public void setCategories(String categories) {
                    this.categories = categories;
                }

                public String getIssources() {
                    return issources;
                }

                public void setIssources(String issources) {
                    this.issources = issources;
                }
            }
        }

        public static class ExBean {
            private List<?> exf;

            public List<?> getExf() {
                return exf;
            }

            public void setExf(List<?> exf) {
                this.exf = exf;
            }
        }
    }
}