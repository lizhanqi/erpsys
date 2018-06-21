package com.suxuantech.erpsys.common;

import android.content.Context
import android.content.Intent
import com.suxuantech.erpsys.ui.activity.BaseWebActivity

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
 * @author Created by 李站旗 on 2018/6/20 0020 20:01 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
class WebActivityConfig {


    private var mContext: Context? = null;
    /**
     * 是否展示toolbar进行导航
     */
    private var showToolbar = true;
    /**
     * 是否显示toolbar的一键关闭
     */
    private var showOneClose = true;
    /**
     *导航是否有菜单的刷新
     */
    private var toolbarMoreMenu = true;
    /**
     *导航是否有菜单的刷新
     */
    private var showTitleBack = true;
    /**
     *网址
     */
    private var url = "";

    constructor (context: Context) {
        if (context == null) {
            throw IllegalArgumentException("Context不能为空")
        }
        mContext = context
    }

    public fun showToolbar(isShow: Boolean): WebActivityConfig {
        showToolbar = isShow!!
        return this;
    }

    public fun showOneClose(isShow: Boolean): WebActivityConfig {
        showOneClose = isShow!!
        return this;
    }

    public fun showMoreMenu(isShow: Boolean): WebActivityConfig {
        toolbarMoreMenu = isShow
        return this;
    }
    public fun showTitleBack(isShow: Boolean): WebActivityConfig {
        showTitleBack = isShow
        return this;
    }

    public fun loadUrl(url: String): WebActivityConfig {
        this.url = url
        return this;
    }

    public fun start() {
        var intn = Intent(mContext, BaseWebActivity::class.java)
        intn.putExtra("showToolbar", showToolbar)
        intn.putExtra("showOneClose", showOneClose)
        intn.putExtra("showMoreMenu", toolbarMoreMenu)
        intn.putExtra("showTitleBack", showTitleBack)
        intn.putExtra("url", url)
        mContext?.startActivity(intn)
    }

}