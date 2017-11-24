package com.suxuantech.erpsys.activity;

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
 * @author Created by 李站旗 on 2017/11/23 0023 10:40 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.activity.fragment.CustomerInformationFragment;
import com.suxuantech.erpsys.activity.fragment.TakeDataFragment;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 订单详情，整个订单的所有信息都在这里了
 */
public class OrderDetailActivity extends StatusImmersedBaseActivity {

    private String[] stringArray;
    private PopupWindow window;
    private SwipeMenuRecyclerView viewById;
    private BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }
    String current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        showUsetDefinedNav();
        getNavTitleView().setOnClickListener(this);
        stringArray = getResources().getStringArray(R.array.order);
        current=stringArray[0];
        setUseDefinedNavTitle(current);
        gotoFragment();
    }
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()){
            case R.id.tv_nav_title:
                showpopupwindow();
                break;
            case R.id.tv_nav_left:
                finish();
                break;
            default:

        }

    }

    public void gotoFragment(){
            switch (current){
                default:startFragment(CustomerInformationFragment.class);
                break;
                case "取件资料":
                    startFragment(TakeDataFragment.class);
                    break;
            }
    }
    public void showpopupwindow(){
        // 将一个布局文件填充为view
        View vw = View.inflate(this, R.layout.pop_select_information,null);
        viewById = vw.findViewById(R.id.smrv_select);
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(stringArray));
        viewById.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                current = stringArray[position];
                window.dismiss();
                stringBaseRecyclerAdapter.notifyDataSetChanged();
                setUseDefinedNavTitle(current);
                gotoFragment();
            }
        });

        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(viewById, objects, R.layout.item_pop_type) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_type);
                if (  current.equals(item)) {
                    holder.getView(R.id.ll_root_bg).setBackgroundColor(getResources().getColor(R.color.translucent_black_90));
                }
                view.setTextSize(25);
                view.setText(item);
            }
        };
      viewById.setLayoutManager(new LinearLayoutManager(this));
        viewById.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        viewById.setAdapter(stringBaseRecyclerAdapter);
        window = new PopupWindow(vw, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT- (int) getResources().getDimension(R.dimen.px146));
        // 背景色
        window.setBackgroundDrawable(new ColorDrawable());
        // 获取焦点（光标）
        // window.setFocusable(true);
        // 设置该pop外区域可触摸
        window.setOutsideTouchable(true);
        window.setFocusable(true); // 设置PopupWindow可获得焦点
        window.setTouchable(true); // 设置PopupWindow可触摸
        window.setHeight(2000);
        window.setBackgroundDrawable(getResources().getDrawable( R.color.translucent_black_90));

        // 第一种方式展示pop
        // p.showAtLocation(rl, Gravity.CENTER, 0, 100);
        // 展示出来
        window.showAsDropDown(getLineView());
        //p.showAsDropDown(tv,100,200);
        //p.showAtLocation(tv, Gravity.TOP, 200, 300);
        // 找到popwindn页面的按钮设置关闭事件

    }
    @Override
    protected int fragmentLayoutId() {
        return R.id.container;
    }
}
