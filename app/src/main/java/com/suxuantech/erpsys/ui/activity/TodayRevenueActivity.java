package com.suxuantech.erpsys.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.AdjustDrawableTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
 * @author Created by 李站旗 on 2017/11/18 0018 16:16 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 当日营收
 */
public class TodayRevenueActivity extends TitleNavigationActivity {

    @BindView(R.id.tv_nav_left)
    AdjustDrawableTextView mTvNavLeft;
    @BindView(R.id.tv_nav_title)
    AdjustDrawableTextView mTvNavTitle;
    @BindView(R.id.tv_nav_right)
    AdjustDrawableTextView mTvNavRight;
    @BindView(R.id.v_line)
    View mVLine;
    @BindView(R.id.head_nav_use_defined_root)
    View head;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_revenue);
        ButterKnife.bind(this);
        head.setBackgroundColor(getResources().getColor(R.color.themeColor));
        mTvNavTitle.setText("单日");
        mTvNavRight.setText("20171104");
        mVLine.setBackgroundColor(getResources().getColor(R.color.themeColor));
        mTvNavTitle.setTextColor(getResources().getColor(R.color.white));
        ArrayList<String> objects = new ArrayList<>();
        objects.add("经典11");
        objects.add("经典11");
        objects.add("经典11");
        objects.add("经典11");
        objects.add("经典33");
        SwipeMenuRecyclerView view = idGetView(R.id.total_recyle);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        BaseRecyclerAdapter stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(view, objects, R.layout.item_revenue) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView tv = holder.getView(R.id.tv_store_name);
                TextView ra = holder.getView(R.id.tv_ranking);
                ra.setText(position + "");
                tv.setText(item);
            }
        };
        view.setAdapter(stringBaseRecyclerAdapter);

    }

    @Override
    @OnClick({R.id.tv_nav_left, R.id.tv_nav_title, R.id.tv_nav_right, R.id.v_line})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.tv_nav_left:
                finish();
                break;
            case R.id.tv_nav_title:
                break;
            case R.id.tv_nav_right:
                break;
            case R.id.v_line:
                break;
        }
    }
}
