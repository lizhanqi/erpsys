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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description:订单详情，整个订单的所有信息都在这里了
 */

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.fragment.CustomerInformationFragment;
import com.suxuantech.erpsys.ui.fragment.DressMaterialFragment;
import com.suxuantech.erpsys.ui.fragment.MakeUpFragment;
import com.suxuantech.erpsys.ui.fragment.ProductDataFragment;
import com.suxuantech.erpsys.ui.fragment.TabControlFragment;
import com.suxuantech.erpsys.ui.fragment.TakeDataFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import me.yokeyword.fragmentation.SupportFragment;

public class OrderDetailActivity extends TitleNavigationActivity implements DressMaterialFragment.OnFragmentInteractionListener {
    private String[] stringArray;
    private PopupWindow mPopupWindow;
    private SwipeMenuRecyclerView recycleView;
    private BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;
    private ProductDataFragment productDataFragment;
    String current;
    public static int SHOW_PHOTO_MATERIAL = 4, SHOW_OPTION_PANEL_MATERIAL = 5;
    public static String SHOWONPOSITION = "showOnPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        useEventBus();
        supportToolbar();
        getNavTitleView().setOnClickListener(this);
        Drawable drawable = getResources().getDrawable(R.drawable.arrows_down_gray);
        getImgToolbarCenter().setImageDrawable(drawable);
        stringArray = getResources().getStringArray(R.array.order);
        current = stringArray[getIntent().getIntExtra(SHOWONPOSITION, 0)];
        setCenterTitle(current);
        gotoFragment();
    }

    private SupportFragment[] mFragments = new SupportFragment[10];

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ll_toolbar_center_root:
            case R.id.img_toolbar_center:
            case R.id.tv_toolbar_center_title:
                if (getIntent().getBooleanExtra("canChange", true)) {
                    showpopupwindow();
                }
                break;
            case R.id.tv_nav_title:
                showpopupwindow();
                break;
            case R.id.tv_nav_left:
                finish();
                break;
            case R.id.tv_nav_right:
                if (mFragments[3] != null) {
                    productDataFragment.editProduct();
                }
                break;
            default:
        }
    }


    public void gotoFragment() {
        if (menu != null) {
            menu.clear();
        }
        switch (current) {
            default:
                if (mFragments[0] == null) {
                    CustomerInformationFragment customerInformationFragment = new CustomerInformationFragment();
                    mFragments[0] = customerInformationFragment;
                    Bundle bund = getIntent().getExtras();
                    customerInformationFragment.setArguments(bund);
                }
                loadRootFragment(R.id.container, mFragments[0], false, true);
                break;
            case "服务费用":
                if (mFragments[1] == null) {
                    ServiceFeeFragment serviceFeeFragment = new ServiceFeeFragment();
                    mFragments[1] = serviceFeeFragment;
                }
                loadRootFragment(R.id.container, mFragments[1], false, true);
                break;
            case "付款明细":
                toTabFragement(TabControlFragment.whichInFragement.PAY_DETAILS);
                break;
            case "取件资料":
                if (mFragments[5] == null) {
                    TakeDataFragment takeDataFragment = new TakeDataFragment();
                    mFragments[5] = takeDataFragment;
                    String orderId = getIntent().getStringExtra("orderId");
                    Bundle bundle = new Bundle();
                    bundle.putString("orderId", orderId);
                    takeDataFragment.setArguments(bundle);
                }
                loadRootFragment(R.id.container, mFragments[5], false, true);
                break;
            case "摄影资料":
                toTabFragement(TabControlFragment.whichInFragement.SHOOT);
                break;
            case "化妆资料":
                if (mFragments[6] == null) {
                    MakeUpFragment makeUpFragment = new MakeUpFragment();
                    mFragments[6] = makeUpFragment;
                    Bundle data = getIntent().getExtras();
                    makeUpFragment.setArguments(data);
                }
                loadRootFragment(R.id.container, mFragments[6], false, true);
                break;
            case "选片资料":
                toTabFragement(TabControlFragment.whichInFragement.OPTION_PANEL);
                break;
            case "礼服资料":
                if (mFragments[4] == null) {
                    DressMaterialFragment dressMaterialFragment = new DressMaterialFragment();
                    mFragments[4] = dressMaterialFragment;
                    String orderId = getIntent().getStringExtra("orderId");
                    Bundle bundle = new Bundle();
                    bundle.putString("orderId", orderId);
                    dressMaterialFragment.setArguments(bundle);
                }
                loadRootFragment(R.id.container, mFragments[4], false, true);
                break;
            case "产品资料":
                if (mFragments[3] == null) {
                    productDataFragment = new ProductDataFragment();
                    mFragments[3] = productDataFragment;
                    Bundle bundle = getIntent().getExtras();
                    productDataFragment.setArguments(bundle);
                }
                loadRootFragment(R.id.container, productDataFragment, false, true);
                break;
        }
    }

    @Subscribe
    @MainThread
    public void onEventMainThread(String add) {
        Drawable editDrawable = getResources().getDrawable(R.drawable.icon_edit_product);
        DrawableCompat.setTint(editDrawable, getResources().getColor(R.color.white));
        MenuItem editMenu = menu.findItem(1);
        MenuItem addMenu = menu.findItem(2);
        if (add.equals("80")) {
            //编辑按钮不存在, 并且有包套,一销二销任意一项不锁定才能添加编辑按钮
            if (editMenu == null && productDataFragment.hasPackage() && (productDataFragment.oneProductCanEdit()
                    || productDataFragment.twoProductCanEdit())) {
                menu.add(Menu.NONE, 1, 0, "10").setIcon(editDrawable)
                        .setOnMenuItemClickListener(menuItem -> {
                            EventBus.getDefault().post("edit");
                            return true;
                        })
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
        } else if (add.equals("88")) {
            if (editMenu != null && editMenu.getTitle().equals("10")) {
                editMenu.setIcon(editDrawable);
            }
        } else if (add.equals("89")) {
            Drawable doneDrawable = getResources().getDrawable(R.drawable.icon_product_ok);
            DrawableCompat.setTint(doneDrawable, getResources().getColor(R.color.white));
            if (editMenu != null && editMenu.getTitle().equals("10")) {
                editMenu.setIcon(doneDrawable);
            }
            //添加按钮不存在,并且单子不作废,未完成,以及
        } else if (add.equals("addButton") && productDataFragment.canAdd() && addMenu == null) {
            Drawable addDrawle = getResources().getDrawable(R.drawable.icon_add_product);
            DrawableCompat.setTint(addDrawle, getResources().getColor(R.color.white));
            int heit = (int) (addDrawle.getMinimumHeight() * 0.5);
            addDrawle.setBounds(0, 0, heit, heit);
            menu.add(Menu.NONE, 2, 1, "sss")
                    .setEnabled(true)
                    .setOnMenuItemClickListener(menuItem -> {
                        if (productDataFragment.hasPackage()) {
                            String[] s = {"一销产品"};
                            AlertView alertView = new AlertView("添加产品", null, getString(R.string.cancel), null, s, this, AlertView.Style.ACTIONSHEET, new OnItemClickListener() {
                                @Override
                                public void onItemClick(Object o, int position) {//position -1是取消按钮
                                    recoverImmersionBar();
                                    if (position < 0) {
                                        return;
                                    }
                                    OptionHelp optionHelp = new OptionHelp(OrderDetailActivity.this);
                                    optionHelp.setTitle(s[position]);
                                    optionHelp.setUrlTag(OptionHelp.UrlTag.PRODUCT);
                                    optionHelp.setMultiple(true);
                                    Intent creat = optionHelp.creat();
                                    creat.putExtras(getIntent().getExtras());
                                    startActivity(creat);
                                }
                            });
                            alertView.setMarginBottom(30);
                            alertView.show();
                            EventBus.getDefault().post("addProductWindow");
                        } else {
                            OptionHelp optionHelp = new OptionHelp(this);
                            optionHelp.setTitle("添加包套");
                            optionHelp.setUrlTag(OptionHelp.UrlTag.PACKAGE);
                            Intent creat = optionHelp.creat();
                            startActivity(creat);
                        }
                        return true;
                    })
                    .setIcon(addDrawle)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else if (add.equals("removeAllMenu")) {
            menu.clear();
        } else if (add.equals("removeEdit")) {
            menu.removeItem(1);
        }
    }


    /**
     * @param witch
     */
    private void toTabFragement(TabControlFragment.whichInFragement witch) {
        Bundle bd = new Bundle();
        bd.putSerializable("witch", witch);
        bd.putString("orderId", getIntent().getStringExtra("orderId"));
        TabControlFragment tabControlFragment = new TabControlFragment();
        mFragments[2] = tabControlFragment;
        tabControlFragment.setArguments(bd);
        loadRootFragment(R.id.container, mFragments[2], false, true);
    }

    /**
     * 选择窗口
     */
    public void showpopupwindow() {
        // 将一个布局文件填充为view
        View vw = View.inflate(this, R.layout.pop_select_information, null);
        recycleView = vw.findViewById(R.id.smrv_select);
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(stringArray));
        recycleView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                mPopupWindow.dismiss();
                String temp = stringArray[position];
                if (temp.equals(current)) {
                    return;
                }
                current = stringArray[position];
                stringBaseRecyclerAdapter.notifyDataSetChanged();
                setCenterTitle(current);
                gotoFragment();
            }
        });
        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(recycleView, objects, R.layout.item_pop_type) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_type);
                if (current.equals(item)) {
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
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                getImgToolbarCenter().setRotation(360);
            }
        });
        showAsDropDown(mPopupWindow, findViewById(R.id.v_line), 0, 0);
        getImgToolbarCenter().setRotation(180);
    }

    /**
     * popupWindow 兼容7.0以下以及7.0,7.1,8.0
     *
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
}
