package com.suxuantech.erpsys.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

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
 * @author Created by 李站旗 on 2018/4/19 0019 20:09 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:   NestedScrollView 中EditText位于底部被输入法遮盖,需要在
 *        清单文件设置 strongwindowSoftInputMode="stateVisible|adjustResize
 *        但是布局文件中fitsSystemWindows 为false或者默认是false ,导致 windowSoftInputMode 不能生效
 *        (另外补充一点全屏模式也会失效,这个View针对全屏貌似应该不行,也有人说fitsSystemWindows=false 也是一种全屏,如果在其他全屏模式下本View不能生效
 *        请另寻出路)
 *        但是因为某些原因fitsSystemWindows不能直接为true ,例如显现问题:(toolbar向下平移了statusbar的高度，也就是说statusbar是全白的)
 *        因此问题冲突了
 *        经过搜索找到了靠谱的解决方案以及描述:
 *        来源:http://blog.163.com/ittfxin%40126/blog/static/11067486320162210549679/
 *       代码来自上文中的最后解决方法:https://stackoverflow.com/questions/21092888/windowsoftinputmode-adjustresize-not-working-with-translucent-action-navbar
 */

public class StrongWindowSoftInputModeLayout extends RelativeLayout {
    private int[] mInsets = new int[4];
    public StrongWindowSoftInputModeLayout(Context context) {
        super(context);
    }
    public StrongWindowSoftInputModeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public StrongWindowSoftInputModeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            mInsets[1] = insets.getSystemWindowInsetTop();
            mInsets[2] = insets.getSystemWindowInsetRight();
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,  insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }
}