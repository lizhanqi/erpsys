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
 * @author Created by 李站旗 on 2018/5/17 0017 13:55 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class PHPLoginEntity extends BaseResult {

    /**
     * code : 200
     * msg : 登录成功
     * data : {"staff_id":37,"session_id":"hVUcmBPLKz","ip":"127.0.0.1","login_type":"PC","login_method":"user_name","expiry":"2018-05-18 13:52:08","valid_date":90,"staffname":"SSS","staffnumber":"S001","brandclass":"时尚经典事业部","shop_name":"时尚经典旗舰店","shop_code":"VVC001","belong_shop_name":"时尚经典渠道部","belong_shop_code":"VVC009","is_marketing":2,"is_sk":2,"shop_type":1,"receiv_shop_code":"","is_group":1,"end_date":null,"setkey":null,"department_name":"门市部","department_id":4,"main_position_name":"ERP系统高级管理员","main_position_code":"p15169837225860","grade_type":4,"position_code":"p15169793315051,p15169901128683,p15169902642583","status":1,"sn":"S","main_work_type":"22","work_type":"1,1,22,22","open_id":"","is_belong":4,"brandclass_id":1}
     */
    private UserEntity data;
    public UserEntity getData() {
        return data;
    }
    public void setData(UserEntity data) {
        this.data = data;
    }

}
