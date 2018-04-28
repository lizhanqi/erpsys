package com.suxuantech.erpsys.ui.activity;

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
 * @Description:订单详情，整个订单的所有信息都在这里了
 */

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.fragment.CustomerInformationFragment;
import com.suxuantech.erpsys.ui.fragment.DressMaterialFragment;
import com.suxuantech.erpsys.ui.fragment.ProductDataFragment;
import com.suxuantech.erpsys.ui.fragment.TabControlFragment;
import com.suxuantech.erpsys.ui.fragment.TakeDataFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

public class OrderDetailActivity extends ImmersedBaseActivity implements DressMaterialFragment.OnFragmentInteractionListener {
    private String[] stringArray;
    private PopupWindow mPopupWindow;
    private SwipeMenuRecyclerView recycleView;
    private BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;
    private ProductDataFragment productDataFragment;
    String current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        showUserDefinedNav();
        getNavTitleView().setOnClickListener(this);
        stringArray = getResources().getStringArray(R.array.order);
        current=stringArray[0];
        setUseDefinedNavTitle(current);
        gotoFragment();
    }

    private SupportFragment[] mFragments = new SupportFragment[10];
    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.tv_nav_title:
                showpopupwindow();
                break;
            case R.id.tv_nav_left:
                finish();
                break;
            case R.id.tv_nav_right:
                if (mFragments[3]!=null){
                    productDataFragment.change();
                }
                break;
            default:
        }
    }

    public void gotoFragment(){
            switch (current){
                default:
                    if (findFragment(CustomerInformationFragment.class)==null){
                        CustomerInformationFragment customerInformationFragment = new CustomerInformationFragment();
                        mFragments[0]=customerInformationFragment;
                        Bundle bund = getIntent().getBundleExtra("bund");
                        customerInformationFragment.setArguments(bund);
                        loadRootFragment(R.id.container,  mFragments[0],false,true);
                    }else {
                        start(mFragments[0], ISupportFragment.SINGLETASK);
                    }
                break;
                case "服务费用":
                    if (findFragment(ServiceFeeFragment.class)==null){
                        ServiceFeeFragment serviceFeeFragment = new ServiceFeeFragment();
                       // loadRootFragment(R.id.container, serviceFeeFragment);
                        mFragments[1]=serviceFeeFragment;
                        loadRootFragment(R.id.container,  mFragments[1],false,true);
                    }else {
                        start(mFragments[1], ISupportFragment.SINGLETASK);
                    }
                    break;
                case "取件资料":
//                    toTabFragement(0);
                    if (findFragment(TakeDataFragment.class)==null){
                        TakeDataFragment     takeDataFragment = new TakeDataFragment();
                        // loadRootFragment(R.id.container, serviceFeeFragment);
                        mFragments[5]= takeDataFragment;
                        String orderId = getIntent().getStringExtra("orderId");
                        Bundle bundle = new Bundle();
                        bundle.putString("orderId",orderId);
                        takeDataFragment.setArguments(bundle);
                        loadRootFragment(R.id.container,  mFragments[5],false,true);
                    }else {
                        start(mFragments[5], ISupportFragment.SINGLETASK);
                    }


                    break;
                case "摄影资料":
                    toTabFragement(1);
                    break;
                case "选片资料":
                    toTabFragement(2);
                    break;
                case "礼服资料":
                 //   toTabFragement(3);
                    if (findFragment(ProductDataFragment.class)==null) {
                        DressMaterialFragment          dressMaterialFragment = new DressMaterialFragment();
                        // loadRootFragment(R.id.container, serviceFeeFragment);
                        mFragments[4] = dressMaterialFragment;
                        String orderId = getIntent().getStringExtra("orderId");
                        Bundle bundle = new Bundle();
                        bundle.putString("orderId", orderId);
                        dressMaterialFragment.setArguments(bundle);
                        loadRootFragment(R.id.container, mFragments[4], false, true);
                    }
                    break;
                case "产品资料":
                    if (findFragment(ProductDataFragment.class)==null){
                        productDataFragment = new ProductDataFragment();
                        // loadRootFragment(R.id.container, serviceFeeFragment);
                        mFragments[3]= productDataFragment;
                        String orderId = getIntent().getStringExtra("orderId");
                        Bundle bundle = new Bundle();
                        bundle.putString("orderId",orderId);
                        productDataFragment.setArguments(bundle);
                        loadRootFragment(R.id.container,  mFragments[3],false,true);
                    }else {
                        start(mFragments[3], ISupportFragment.SINGLETASK);
                    }

                    break;
            }
    }
    private void toTabFragement(int witch){
        Bundle bd=  new Bundle();
        bd.putInt("witch",witch);
        if (findFragment(TabControlFragment.class)==null){
            TabControlFragment tabControlFragment = new TabControlFragment();
            // loadRootFragment(R.id.container, serviceFeeFragment);
            tabControlFragment.setArguments(bd);
            mFragments[2]=tabControlFragment;
            loadRootFragment(R.id.container,  mFragments[2],false,true);
        }else {
            // 传递的bundle数据，会调用目标Fragment的onNewBundle(Bundle newBundle)方法
            mFragments[2].putNewBundle(bd);
            start(mFragments[2], ISupportFragment.SINGLETASK);
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
                view.setTextSize(15);
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
        showAsDropDown(mPopupWindow,getLineView(),0,0);
    }
    /**
     *popupWindow 兼容7.0以下以及7.0,7.1,8.0
     * @param pw     popupWindow
     * @param anchor v
     * @param xoff   x轴偏移
     * @param yoff   y轴偏移
     */
    public static void showAsDropDown(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }

    @Override
    public void onFragmentInteraction(@NotNull Uri uri) {

    }
//    @Override
//    protected int fragmentLayoutId() {
//        return R.id.container;
//    }
}
