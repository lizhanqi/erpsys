package com.suxuantech.erpsys.ui.activity.base

import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.suxuantech.erpsys.R

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
 * @author Created by 李站旗 on 2017/11/6 9:25 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 状态栏和导航栏沉浸式
 */

abstract class ImmersionActivity : BaseActivity() {
    public var mImmersionBar: ImmersionBar? = null
    override fun onRestart() {
        initImmersionBar()
        super.onRestart()
    }

    override fun onStart() {
        initImmersionBar()
        super.onStart()
    }

    override fun onResume() {
        initImmersionBar()
        super.onResume()
    }

    override fun onDestroy() {
        //在销毁释放内存
        if (mImmersionBar != null) {
            mImmersionBar!!.destroy()
        }
        super.onDestroy()
    }
    public  fun diarlog(){
    //    mImmersionBar.barParams.co
    }

    /**
     * 初始化沉浸式状态栏
     */
    public open fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
        mImmersionBar!!.fitsSystemWindows(true)
        mImmersionBar!!.statusBarDarkFont(true, 0.15f)
        mImmersionBar!!.statusBarColor(R.color.status)
        mImmersionBar!!.keyboardEnable(false)
        mImmersionBar!!.navigationBarColor(R.color.navigation)
        mImmersionBar?.addTag("default")  //给上面参数打标记，以后可以通过标记恢复
        mImmersionBar!!.init()
    }
    /**
     * 设置透明度给导航
     */
    public  fun  alphaImmersionBar(targColor:Int, alpha:Float){
        mImmersionBar    ?.statusBarColor (targColor)
                ?.navigationBarColor(targColor)
        mImmersionBar?.barAlpha(alpha)
        mImmersionBar?.init();
    }
    /**
     * 增加暗色
     */
    public  fun  immersionBarDark( ){
        mImmersionBar    ?.statusBarColor (R.color.translucent_black_95)
         ?.navigationBarColor(R.color.translucent_black_95)
        mImmersionBar?.barAlpha(0f)
        mImmersionBar?.init();
    }

    /**
     *
     */
    public fun immersionBarTransformColor( targColor:Int,alpha: Float, surport: View){
        mImmersionBar?.statusBarColorTransform(targColor)
                ?.navigationBarColorTransform(targColor)
                if (surport!=null){
                    mImmersionBar   ?.addViewSupportTransformColor(surport)
                }
        mImmersionBar?.barAlpha(alpha)
        mImmersionBar?.init();

    }



    /**
     *
     */
    public  fun   recoverImmersionBar(){
        mImmersionBar?.getTag("default")?.init() //根据tag标记来恢复
    }
}
