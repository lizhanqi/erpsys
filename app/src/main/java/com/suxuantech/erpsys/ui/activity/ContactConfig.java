package com.suxuantech.erpsys.ui.activity;

import java.io.Serializable;
import java.util.HashSet;

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
 * @author Created by 李站旗 on 2018/5/21 0021 20:33 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class ContactConfig implements Serializable{
    private boolean quickEntry;//快速入口
    private boolean quickSearchEntry;//快速搜索入口
    private boolean isOption;
    private String keyCode;
    private int type;
    private HashSet<String>  nav =new HashSet<String>();

    public boolean isQuickEntry() {
        return quickEntry;
    }

    public void setQuickEntry(boolean quickEntry) {
        this.quickEntry = quickEntry;
    }

    public boolean isQuickSearchEntry() {
        return quickSearchEntry;
    }

    public void setQuickSearchEntry(boolean quickSearchEntry) {
        this.quickSearchEntry = quickSearchEntry;
    }

    public boolean isOption() {
        return isOption;
    }

    public void setOption(boolean option) {
        isOption = option;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HashSet<String> getNav() {
        return nav;
    }

    public void addNav(String  title) {
        nav.add(title);
    }
}
