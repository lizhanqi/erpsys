package com.suxuantech.erpsys.activity.base;

import android.support.annotation.StringRes;
import android.view.View;

import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.fragment.NoFragment;

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
 * @author Created by 李站旗 on 2017/11/17 0017 13:15 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public abstract class BaseNoFragment  extends NoFragment implements View.OnClickListener{
    /**
     * Tag不解释
     */
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 按钮快速点击时间(多少毫秒内点击同一个算快速点击)
     */
    private int fastClickTime=1000;
    /**
     * [防止快速点击的记录上次点击时间
     */
    private  long lastClick = 0;
    /**
     * 上次点击的View
     */
    private View lastView;
    /**
     * view点击事件(防止快速点击的)
     * @param v 点击的view
     */
    protected abstract void widgetClick(View v);

    /**
     * 多少毫秒以内的点击同一个View都是快速点击
     *
     * @return
     */
    private boolean fastClick(View v) {
        if (System.currentTimeMillis() - lastClick <= fastClickTime && lastView == v) {
            toast("已经点过了");
            return false;
        }
        lastView = v;
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * 弹出一个吐司(不建议这种方式,因为最好在吧文字抽出来更规范)
     * @param str
     */
    @Deprecated
    public void toast(String str) {
        ToastUtils.show(str);
    }
    /**
     * 弹出一个吐司
     * @param str
     */
    public void toast(@StringRes int str) {
        ToastUtils.show(str);
    }
    /**
     * View的点击事件(防止快速点击的)
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (fastClick(v)) {
            widgetClick(v);
        }
    }
    //--------------------------------------日志模块------------------
    /**
     * 错误日志(Tag是当前Activity名字)
     * @param str
     */
    public void eLog(String str) {
        L.e(TAG, str);
    }

    /**
     * debug日志(Tag是当前Activity名字)
     *
     * @param str
     */
    public void dLog(String str) {
        L.d(TAG, str);
    }

    /**
     * infor日志(Tag是当前Activity名字)
     *
     * @param str
     */
    public void iLog(String str) {
        L.d(TAG, str);
    }

    /**
     * [日志输出输入的Tag来自Application统一的]
     *
     * @param msg
     */
    protected void appLog(String msg) {
        L.d(msg);
    }
    //--------------------------------------日志模块end------------------
}
