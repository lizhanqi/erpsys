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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.activity.fragment.CustomerInformationFragment;
import com.suxuantech.erpsys.activity.fragment.ProductDataFragment;
import com.suxuantech.erpsys.activity.fragment.TakeDataFragment;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 订单详情，整个订单的所有信息都在这里了
 */
public class OrderDetailActivity extends StatusImmersedBaseActivity {

    private String[] stringArray;
        private PopupWindow window;
    private SwipeMenuRecyclerView recycleView;
    private BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;
    private ProductDataFragment productDataFragment;

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
            case R.id.tv_nav_right:
                if (productDataFragment!=null){
                    productDataFragment.change();
                }
                break;
            default:
        }
    }
    Bundle bd=  new Bundle();
    public void gotoFragment(){
            switch (current){
                default:
                    startFragment(CustomerInformationFragment.class,false);
                break;
                case "服务费用":
                    startFragment(ServiceFeeFragment.class);
                    break;
                case "取件资料":
                    bd.putInt("witch",0);
                    startFragment(fragment(TakeDataFragment.class,bd));
                    break;
                case "摄影资料":
                    bd.putInt("witch",1);
                    startFragment(fragment(TakeDataFragment.class,bd));
                    break;
                case "选片资料":
                    bd.putInt("witch",2);
                    startFragment(fragment(TakeDataFragment.class,bd));
                    break;
                case "礼服资料":
                    bd.putInt("witch",3);
                    startFragment(fragment(TakeDataFragment.class,bd));
                    break;
                case "产品资料":
                    productDataFragment = fragment(ProductDataFragment.class);
                    startFragment(productDataFragment);
                    break;


            }
    }
    public void  select(){
        LinearLayout ll_window = idGetView(R.id.ll_window);
        recycleView = idGetView(R.id.smrv_select);
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(stringArray));
        this.recycleView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                current = stringArray[position];
                window.dismiss();
                stringBaseRecyclerAdapter.notifyDataSetChanged();
                setUseDefinedNavTitle(current);
                gotoFragment();
            }
        });
        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(OrderDetailActivity.this.recycleView, objects, R.layout.item_pop_type) {
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
        this.recycleView.setLayoutManager(new LinearLayoutManager(this));
        this.recycleView.setAdapter(stringBaseRecyclerAdapter);

    }

    public void showpopupwindow(){
        // 将一个布局文件填充为view
        View vw = View.inflate(this, R.layout.pop_select_information,null);
        recycleView = vw.findViewById(R.id.smrv_select);
        TextView tvt = vw.findViewById(R.id.tv_nav_title);
         vw.findViewById(R.id.head_nav_use_defined_root).setBackgroundColor(getResources().getColor(R.color.white));

        vw.findViewById(R.id.head_nav_use_defined_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        tvt.setText(current);
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(stringArray));
        recycleView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                window.dismiss();
                String temp = stringArray[position];
                if (temp.equals(current)){
                    return;
                }
                current = stringArray[position];
                stringBaseRecyclerAdapter.notifyDataSetChanged();
                setUseDefinedNavTitle(current);
                gotoFragment();
            }
        });
        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(recycleView, objects, R.layout.item_pop_type) {
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
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(stringBaseRecyclerAdapter);
        //真坑MAT.CH_PARENT
        window = new PopupWindow(vw, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 背景色
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent_black_90)));
        // 设置该pop外区域可触摸
        window.setOutsideTouchable(true);
        window.setFocusable(true); // 设置PopupWindow可获得焦点
        window.setTouchable(true); // 设置PopupWindow可触摸
        window.showAsDropDown(getLineView());
    }
    @Override
    protected int fragmentLayoutId() {
        return R.id.container;
    }
}
