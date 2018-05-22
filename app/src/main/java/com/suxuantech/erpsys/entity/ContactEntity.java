package com.suxuantech.erpsys.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

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
 * @author Created by 李站旗 on 2018/3/1 0001 21:15 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class ContactEntity implements MultiItemEntity {
    private List<BusinssunitEntity.DataBean> businssunitData;
    private List<StoreEntity.DataBean> storeData;
    private List<DepartmentEntiy.DataBean> departmentData;
    private List<StaffSearchEntity.DataBean> staffData;
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<BusinssunitEntity.DataBean> getBusinssunitData() {
        return businssunitData;
    }

    public void setBusinssunitData(List<BusinssunitEntity.DataBean> businssunitData) {
        this.businssunitData = businssunitData;
    }

    public List<StoreEntity.DataBean> getStoreData() {
        return storeData;
    }

    public void setStoreData(List<StoreEntity.DataBean> storeData) {
        this.storeData = storeData;
    }

    public List<DepartmentEntiy.DataBean> getDepartmentData() {
        return departmentData;
    }

    public void setDepartmentData(List<DepartmentEntiy.DataBean> departmentData) {
        this.departmentData = departmentData;
    }

    public List<StaffSearchEntity.DataBean> getStaffData() {
        return staffData;
    }

    public void setStaffData(List<StaffSearchEntity.DataBean> staffData) {
        this.staffData = staffData;
    }

    @Override
    public int getItemType() {

        return 0;
    }
}
