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
 * @author Created by 李站旗 on 2017/12/7 0007 15:59 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 请求服务器结果，主要是判断是否成功，正常来讲应该所有的网络bean都继承这个类，
 */

public abstract class BaseResult {
    public String mCode;
    private String Msg;
    public String getMsg() {
        return Msg;
    }
    public void setMsg(String msg) {
        Msg = msg;
    }
    public boolean isOK() {
        return mCode!=null&&mCode.equals("200");
    }
    public String getCode() {
        return mCode;
    }
    public void setCode(String Code) {
        this.mCode = Code;
    }

}
