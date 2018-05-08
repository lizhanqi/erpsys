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
     * Data : {"yx":{"yxp":[{"id":"719","orderId":"SY18032600049","sorting":"0","consumption_name":"7999丽莎水晶包套","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"7999.00","total":"7999.00","categories":"套系","issources":"0","hasproduct":"0"}],"yxf":[{"id":"712","orderId":"SY18032600049","sorting":"1","consumption_name":"18寸水晶相册","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"完成","whether_already_take":"已取","price":"0.00","total":"0.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"713","orderId":"SY18032600049","sorting":"2","consumption_name":"30寸油画框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"715","orderId":"SY18032600049","sorting":"4","consumption_name":"唇彩","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"716","orderId":"SY18032600049","sorting":"5","consumption_name":"西装","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"717","orderId":"SY18032600049","sorting":"6","consumption_name":"爱丽丝","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"25.00","total":"25.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"725","orderId":"SY18032600049","sorting":"12","consumption_name":"18寸水晶相册","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"10.00","total":"10.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"726","orderId":"SY18032600049","sorting":"13","consumption_name":"30寸油画框","amount":"3","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"10.00","total":"30.00","categories":"摄影","issources":"0","hasproduct":"0"}]},"ex":{"exf":[{"id":"722","orderId":"SY18032600049","sorting":"10","consumption_name":"10寸摆台","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"35.00","total":"35.00","categories":"摄影","issources":"1","hasproduct":"0"},{"id":"723","orderId":"SY18032600049","sorting":"11","consumption_name":"当季发表礼服*4","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"35.00","total":"35.00","categories":"礼服","issources":"1","hasproduct":"0"}]}}
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
         * yx : {"yxp":[{"id":"719","orderId":"SY18032600049","sorting":"0","consumption_name":"7999丽莎水晶包套","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"7999.00","total":"7999.00","categories":"套系","issources":"0","hasproduct":"0"}],"yxf":[{"id":"712","orderId":"SY18032600049","sorting":"1","consumption_name":"18寸水晶相册","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"完成","whether_already_take":"已取","price":"0.00","total":"0.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"713","orderId":"SY18032600049","sorting":"2","consumption_name":"30寸油画框","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"715","orderId":"SY18032600049","sorting":"4","consumption_name":"唇彩","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"716","orderId":"SY18032600049","sorting":"5","consumption_name":"西装","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"0.00","total":"0.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"717","orderId":"SY18032600049","sorting":"6","consumption_name":"爱丽丝","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"25.00","total":"25.00","categories":"化妆","issources":"0","hasproduct":"0"},{"id":"725","orderId":"SY18032600049","sorting":"12","consumption_name":"18寸水晶相册","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"10.00","total":"10.00","categories":"摄影","issources":"0","hasproduct":"0"},{"id":"726","orderId":"SY18032600049","sorting":"13","consumption_name":"30寸油画框","amount":"3","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"10.00","total":"30.00","categories":"摄影","issources":"0","hasproduct":"0"}]}
         * ex : {"exf":[{"id":"722","orderId":"SY18032600049","sorting":"10","consumption_name":"10寸摆台","amount":"1","take_away_date":"2018/3/28 0:00:00","whethercantake":"","whether_already_take":"","price":"35.00","total":"35.00","categories":"摄影","issources":"1","hasproduct":"0"},{"id":"723","orderId":"SY18032600049","sorting":"11","consumption_name":"当季发表礼服*4","amount":"1","take_away_date":"","whethercantake":"","whether_already_take":"","price":"35.00","total":"35.00","categories":"礼服","issources":"1","hasproduct":"0"}]}
         */

        private YxBean yx;
        private ExBean ex;
        private  boolean ext_yx;
        private  boolean ext_ex;

        public boolean isExt_yx() {
            return ext_yx;
        }

        public void setExt_yx(boolean ext_yx) {
            this.ext_yx = ext_yx;
        }

        public boolean isExt_ex() {
            return ext_ex;
        }

        public void setExt_ex(boolean ext_ex) {
            this.ext_ex = ext_ex;
        }

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
                 * id : 719
                 * orderId : SY18032600049
                 * sorting : 0
                 * consumption_name : 7999丽莎水晶包套
                 * amount : 1
                 * take_away_date :
                 * whethercantake :
                 * whether_already_take :
                 * price : 7999.00
                 * total : 7999.00
                 * categories : 套系
                 * issources : 0
                 * hasproduct : 0
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
                private String hasproduct;

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

                public String getHasproduct() {
                    return hasproduct;
                }

                public void setHasproduct(String hasproduct) {
                    this.hasproduct = hasproduct;
                }
            }

            public static class YxfBean {
                /**
                 * id : 712
                 * orderId : SY18032600049
                 * sorting : 1
                 * consumption_name : 18寸水晶相册
                 * amount : 1
                 * take_away_date : 2018/3/28 0:00:00
                 * whethercantake : 完成
                 * whether_already_take : 已取
                 * price : 0.00
                 * total : 0.00
                 * categories : 摄影
                 * issources : 0
                 * hasproduct : 0
                 */
                private  boolean checked;

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean checked) {
                    this.checked = checked;
                }

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
                private String hasproduct;

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

                public String getHasproduct() {
                    return hasproduct;
                }

                public void setHasproduct(String hasproduct) {
                    this.hasproduct = hasproduct;
                }
            }
        }

        public static class ExBean {
            private List<ExfBean> exf;

            public List<ExfBean> getExf() {
                return exf;
            }

            public void setExf(List<ExfBean> exf) {
                this.exf = exf;
            }

            public static class ExfBean {
                /**
                 * id : 722
                 * orderId : SY18032600049
                 * sorting : 10
                 * consumption_name : 10寸摆台
                 * amount : 1
                 * take_away_date : 2018/3/28 0:00:00
                 * whethercantake :
                 * whether_already_take :
                 * price : 35.00
                 * total : 35.00
                 * categories : 摄影
                 * issources : 1
                 * hasproduct : 0
                 */
                private  boolean checked;

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean checked) {
                    this.checked = checked;
                }

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
                private String hasproduct;

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

                public String getHasproduct() {
                    return hasproduct;
                }

                public void setHasproduct(String hasproduct) {
                    this.hasproduct = hasproduct;
                }
            }
        }
    }
}