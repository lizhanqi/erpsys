package com.suxuantech.erpsys.activity;

import android.Manifest;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.suxuantech.erpsys.bean.DistrictBean;
import com.suxuantech.erpsys.fragment.CRMFragment;
import com.suxuantech.erpsys.fragment.ERPFragment;
import com.suxuantech.erpsys.fragment.MsgFragment;
import com.suxuantech.erpsys.fragment.MyFragment;
import com.suxuantech.erpsys.fragment.WorkFragment;
import com.suxuantech.erpsys.nohttp.DownLoad;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.nohttp.StringRequest;
import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.fragment.NoFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.yanzhenjie.statusview.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private BottomNavigationBar bottomNavigationBar;
    private long mExitTime = 0;
    private ArrayList<NoFragment> fragments =new ArrayList<>();
    private MsgFragment msgFragment;
    private MyFragment myFragment;
    private ERPFragment erpFragment;
    private WorkFragment workFragment;
    private CRMFragment crmFragment;
    /**
     * 导航栏点击切换的事件
     */
    BottomNavigationBar.OnTabSelectedListener onTabSelectedListener=new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {

        }
        @Override
        public void onTabUnselected(int position) {
            if(position==bottomNavigationBar.getCurrentSelectedPosition()){
            return;
            }
            dLog(position+"当前"+            bottomNavigationBar.getCurrentSelectedPosition());
            switch (bottomNavigationBar.getCurrentSelectedPosition()){
                case 4:
                    StatusUtils.setStatusBarColor(MainActivity.this,getResources().getColor(R.color.themeColor));
                    startFragment(MyFragment.class);
                 //   startFragment(fragments.get(position),fragments.get(  bottomNavigationBar.getCurrentSelectedPosition()),true, REQUEST_CODE_INVALID);
//                    netsStringSample();
                    Down();
                    break;
                case 3:

                    startFragment(CRMFragment.class);
                    //startFragment(fragments.get(position),fragments.get(  bottomNavigationBar.getCurrentSelectedPosition()),false, REQUEST_CODE_INVALID);
                    break;
                case 2:
                    startFragment(ERPFragment.class);
                //    startFragment(fragments.get(position),fragments.get(  bottomNavigationBar.getCurrentSelectedPosition()),false, REQUEST_CODE_INVALID);
           break;
                case 1:
                    startFragment(WorkFragment.class);
                    //startFragment(fragments.get(position),fragments.get(  bottomNavigationBar.getCurrentSelectedPosition()),false, REQUEST_CODE_INVALID);
                    break;
                default:
                    startFragment(MsgFragment.class);
                    //startFragment(fragments.get(position),fragments.get(  bottomNavigationBar.getCurrentSelectedPosition()),false, REQUEST_CODE_INVALID);
                    break;
            }
        }
        @Override
        public void onTabReselected(int position) {
            dLog("再选");
        }
    };

    /**
     * 监听返回键 点击2次退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closeActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 点击两次返回键退出APP
     */
    private void closeActivity() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showInCenter("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requstPermissions(0,Manifest.permission.SYSTEM_ALERT_WINDOW);
//        msgFragment = new MsgFragment();
//        workFragment = new WorkFragment();
//        erpFragment = new ERPFragment();
//        crmFragment = new CRMFragment();
//        myFragment = new MyFragment();
//        fragments.add(msgFragment);
//        fragments.add(workFragment);
//        fragments.add(erpFragment);
//        fragments.add(crmFragment);
//        fragments.add(myFragment);
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
        initMyBottomNavigation();
        StatusUtils.setFullToStatusBar(this);
        StatusUtils.setFullToNavigationBar(this);
//        //设置状态栏和导航栏颜色
//        StatusUtils.setSystemBarColor(this,getResources().getColor(R.color.translucent_black_90),getResources().getColor(R.color.translucent_black_90));
          //清空下布局文件中的导航栏颜色,因为布局文件中的颜色比较重,如果设置淡颜色可能无法着色或者着色错误
        findViewById(R.id.navigation_view_main).setBackground(null);
        //状态栏颜色
        StatusUtils.setStatusBarColor(this,getResources().getColor(R.color.translucent_black_90));
        //导航栏颜色
        StatusUtils.setNavigationBarColor(this,getResources().getColor(R.color.themeColor));
    }

    /**
     * 初始化页面的导航
     */
    private void initMyBottomNavigation() {
        TextBadgeItem badgeItem=new TextBadgeItem().setBorderWidth(1).setBackgroundColorResource(R.color.msgColor).setText("2").setHideOnSelect(true);
        BottomNavigationItem msgItem = new BottomNavigationItem(R.drawable.icon_msg_pressed, getString(R.string.msg));
        msgItem.setBadgeItem(badgeItem);
        msgItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_msg_normal));//非选中的图片
        msgItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        msgItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem workItem = new BottomNavigationItem(R.drawable.icon_work_pressed, getString(R.string.work));
        workItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_work_normal));//非选中的图片
        workItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        workItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem erpItem = new BottomNavigationItem(R.drawable.icon_erp_pressed, getString(R.string.erp));
        erpItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_erp_normal));//非选中的图片
        BottomNavigationItem crmItem = new BottomNavigationItem(R.drawable.icon_crm_pressed, getString(R.string.crm));
        crmItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_crm_normal));//非选中的图片
        BottomNavigationItem myItem = new BottomNavigationItem(R.drawable.icon_my_pressed, getString(R.string.my));
        myItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_my_normal));//非选中的图片
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(msgItem)
                .addItem(workItem)
                .addItem(erpItem)
                .addItem(crmItem)
                .addItem(myItem)
                .setFirstSelectedPosition(2)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(onTabSelectedListener);
        startFragment(ERPFragment.class);
    }
    /**
     * 设置底部导航栏颜色
     * @param color
     */
    public void setNavColor(@ColorInt int color){
           // 方式一
        //这个是清空主页面的底部导航栏颜色的,防止上次颜色比较重,新设置的值无法生效,或者颜色偏
        findViewById(R.id.navigation_view_main).setBackground(null);
        /**
         * 有效的设置底部颜色
         */
        StatusUtils.setNavigationBarColor(this,color);
        //
      //findViewById(R方式二:.id.navigation_view_main).setBackground(new ColorDrawable(getResources().getColor(R.color.green)));
    }
    @Override
    protected int fragmentLayoutId() {
        return   R.id.main_content;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        bottomNavigationBar.postInvalidate();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int screenWidth = ScreenUtils.getScreenWidth(this);
            try {
//                ReflexUtils.setNotAccessibleProperty(mStatusView, "screenWidth", screenWidth);
//                ReflexUtils.setNotAccessibleProperty(mNavigationView, "screenWidth", screenWidth);
                findViewById(R.id.navigation_view_main).setVisibility(View.GONE);//隐藏,因为测试发现导航栏不会横过来
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            int screenWidth = ScreenUtils.getScreenWidth(this);
            findViewById(R.id.navigation_view_main).setVisibility(View.VISIBLE);
            try {
//                ReflexUtils.setNotAccessibleProperty(mStatusView, "screenWidth", screenWidth);
//                ReflexUtils.setNotAccessibleProperty(mNavigationView, "screenWidth", screenWidth);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            default:
        }
    }
    //请求权限后结果
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
    public  void netsBeanSample() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体
        JavaBeanRequest<DistrictBean> districtBeanJavaBeanRequest = new JavaBeanRequest<DistrictBean>(url,DistrictBean.class);
//        HttpResponseListener<DistrictBean> districtBeanHttpResponseListener = new HttpResponseListener<DistrictBean>(null);
        HttpListener<DistrictBean> searchByCustmor = new HttpListener<DistrictBean>(){
            @Override
            public void onSucceed(int what, Response<DistrictBean> response) {
                L.i("what = [" + what + "], response = [" + response + "]");
            }
            @Override
            public void onFailed(int what, Response<DistrictBean> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
        //CallServer.getInstance().add(this, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }
    public void Down(){
        String u="http://sw.bos.baidu.com/sw-search-sp/software/e25c4cc36a934/QQ_8.9.6.22427_setup.exe";
        DownLoad downLoad = new DownLoad(10, u);

    }
        public  void netsStringSample() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体
       StringRequest districtBeanJavaBeanRequest = new StringRequest(url,RequestMethod.POST);
        HttpListener<String> searchByCustmor = new HttpListener<String>(){
            @Override
            public void onSucceed(int what, Response<String> response) {
                L.i("what = [" + what + "], response = [" + response + "]");
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
//        new  CallServer().setQueue();
     //   CallServer.getInstance().add(this, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }


    public void send() {
        Request<String> stringRequest = NoHttp.createStringRequest("http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0", RequestMethod.POST);
        // stringRequest.addHeader("Content-Type", "application/json");
        //stringRequest.setDefineRequestBodyForJson("{\"x\":1,\"y\":2}");
/*        stringRequest.add("x",1);
        stringRequest.add("y",4);*/
        RequestQueue requestQueueInstance = NoHttp.getRequestQueueInstance();
        requestQueueInstance.add(0, stringRequest, new SimpleResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        super.onStart(what);
                    }
                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                        L.d("NoHttpSample",response.get());
                    }
                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                    }
                }
        );
    }
}

