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
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class TempEntity {
    int icon;
    boolean edit;
    String key;
    String value;
    String hint;
    boolean mustFill;
    boolean option;
    Object flag;

    public TempEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TempEntity(int icon, String key, String value) {
        this.icon = icon;
        this.key = key;
        this.value = value;
    }

    public TempEntity(int icon, String key, String value, String hint) {
        this.icon = icon;
        this.key = key;
        this.value = value;
        this.hint = hint;
    }

    public TempEntity(int icon, String key, String value, String hint, boolean mustFill) {
        this.icon = icon;
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.mustFill = mustFill;
    }

    public TempEntity(int icon, boolean edit, String key, String value) {
        this.icon = icon;
        this.edit = edit;
        this.key = key;
        this.value = value;
    }

    public TempEntity(int icon, boolean edit, String key, String value, String hint, boolean mustFill, boolean option) {
        this.icon = icon;
        this.edit = edit;
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.mustFill = mustFill;
        this.option = option;
    }
}
