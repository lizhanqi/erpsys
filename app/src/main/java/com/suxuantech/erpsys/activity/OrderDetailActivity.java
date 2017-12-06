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
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.fragment.CustomerInformationFragment;
import com.suxuantech.erpsys.fragment.ProductDataFragment;
import com.suxuantech.erpsys.fragment.TakeDataFragment;
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
        private PopupWindow mPopupWindow;
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

    /**
     * 选择窗口
     */
    public void showpopupwindow(){
        // 将一个布局文件填充为view
        View vw = View.inflate(this, R.layout.pop_select_information,null);
        recycleView = vw.findViewById(R.id.smrv_select);
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(stringArray));
        recycleView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                mPopupWindow.dismiss();
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
        mPopupWindow = new PopupWindow(vw, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 背景色
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent_black_90)));
        // 设置该pop外区域可触摸
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        if (Build.VERSION.SDK_INT < 24) {
            //7.0以下么问题可以使用在某一个view下展示
            mPopupWindow.showAsDropDown(getLineView());
        } else {
            //7.0+就不行了使用这种方式指定
            int[] location = new int[2];    getLineView().getLocationOnScreen(location);
            mPopupWindow.showAtLocation(getLineView(), Gravity.NO_GRAVITY, 0, location[1]+getLineView().getHeight());
            //一定要update不然显示的仍然不对，之前不知道，仅仅写了上边两行然后还是没作用
            mPopupWindow.update();
        }
    }
    @Override
    protected int fragmentLayoutId() {
        return R.id.container;
    }
}
