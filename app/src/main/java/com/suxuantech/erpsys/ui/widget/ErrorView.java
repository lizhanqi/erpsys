package com.suxuantech.erpsys.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.suxuantech.erpsys.R;

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
 * @author Created by 李站旗 on 2018/7/1 0001 15:56 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class ErrorView extends LinearLayout {
    View inflate;
    TextView tverror;
    ProgressBar progressBar;

    public ErrorView(Context context) {
        super(context);
        inflate = inflate(context, R.layout.empty_view, this);
        tverror = inflate.findViewById(R.id.tv_error_text);
        progressBar = inflate.findViewById(R.id.progress_error);
        setBackgroundColor(context.getResources().getColor(R.color.bgColor_divier));
    }
    public void setErrorText(String text) {
        tverror.setText(text);
        progressBar.setVisibility(INVISIBLE);
    }

    public void reset( ) {
        tverror.setText("没有数据,点击重试");
        progressBar.setVisibility(INVISIBLE);
    }

    public void setProgressBarVisible(boolean b) {
        if (b) {
            progressBar.setVisibility(VISIBLE);
        } else {
            progressBar.setVisibility(INVISIBLE);
        }
    }
}
