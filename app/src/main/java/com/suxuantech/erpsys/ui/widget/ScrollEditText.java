package com.suxuantech.erpsys.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.suxuantech.erpsys.utils.L;

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
 * @author Created by 李站旗 on 2018/4/16 0016 11:51 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class ScrollEditText extends android.support.v7.widget.AppCompatEditText {
    public ScrollEditText(Context context) {
        super(context);
    }

    public ScrollEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    float lastScrollY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getLineCount() > getMaxLines()) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                lastScrollY = ev.getRawY();
                L.d("lldd","down:"+lastScrollY);
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                //滑动到头并且还在继续上滑动,或者滑动到底部就不要再拦截了(有误差)
                int sum = getLineHeight() * getLineCount() - getLineHeight() * getMaxLines();
                //计算上次与本次差
                float diff = lastScrollY - ev.getRawY();
                if (diff>0){//下滑动并且到达了底部也不要处理了
                    //底部这里用abs的原因是,因为计算sum的时候有些误差
                    if (Math.abs(sum - getScrollY())<5) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }else if (diff<0){//上滑动
                    if (getScrollY() == 0) {//上滑动并且已经到达了顶部就不要在处理了
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                lastScrollY = ev.getRawY();
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onTouchEvent(ev);
    }
}
