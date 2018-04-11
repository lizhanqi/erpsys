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
 * @author Created by 李站旗 on 2017/12/9 0009 14:47 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  婚纱单的对应临时传递用的对象
 */

public class OpenOrderTempEntity {
    String  orderId;
    String  custromerName;
    String  phoneNumber;
    String  sex;
    String  consumptionType;
    String OutletsReception;
    String  OrderReceivingSite;
    String Reception;

    public String getReception() {
        return Reception;
    }

    public void setReception(String reception) {
        Reception = reception;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustromerName() {
        return custromerName;
    }

    public void setCustromerName(String custromerName) {
        this.custromerName = custromerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType;
    }

    public String getOutletsReception() {
        return OutletsReception;
    }

    public void setOutletsReception(String outletsReception) {
        OutletsReception = outletsReception;
    }

    public String getOrderReceivingSite() {
        return OrderReceivingSite;
    }

    public void setOrderReceivingSite(String orderReceivingSite) {
        OrderReceivingSite = orderReceivingSite;
    }

    public String getPackageSetName() {
        return PackageSetName;
    }

    public void setPackageSetName(String packageSetName) {
        PackageSetName = packageSetName;
    }

    public String getPackageSetID() {
        return PackageSetID;
    }

    public void setPackageSetID(String packageSetID) {
        PackageSetID = packageSetID;
    }

    public String getCustomerZone() {
        return CustomerZone;
    }

    public void setCustomerZone(String customerZone) {
        CustomerZone = customerZone;
    }

    public String getPhotographyRemarks() {
        return PhotographyRemarks;
    }

    public void setPhotographyRemarks(String photographyRemarks) {
        PhotographyRemarks = photographyRemarks;
    }

    public String getCustomerRemarks() {
        return CustomerRemarks;
    }

    public void setCustomerRemarks(String customerRemarks) {
        CustomerRemarks = customerRemarks;
    }

    public String getMarriageDate() {
        return MarriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        MarriageDate = marriageDate;
    }

    String   PackageSetName;
    String     PackageSetID;
    String  CustomerZone;
    String   PhotographyRemarks;
    String  CustomerRemarks;
    String     MarriageDate;


}
