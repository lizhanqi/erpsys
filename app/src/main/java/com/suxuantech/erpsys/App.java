package com.suxuantech.erpsys;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.suxuantech.erpsys.entity.LoginEntity;
import com.suxuantech.erpsys.ui.activity.DefaultErrorActivity;
import com.suxuantech.erpsys.ui.widget.ViewAttr;
import com.suxuantech.erpsys.utils.JsonUtil;
import com.suxuantech.erpsys.utils.L;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import cn.jpush.im.android.api.JMessageClient;
import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;
import me.yokeyword.fragmentation.Fragmentation;
import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.loader.SkinManager;


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
 * @author Created by 李站旗 on 2017/11/2 20:32 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 应用入口文件
 */

public class App extends Application {
    protected static Context context;
    private static App application;
    public static   boolean ISDEBUG=true;
    public static String APP_LOG_NAME = "debug";
    public final static   String LOGINFILENAME="login_inf";
    private LoginEntity userInfo;
    public     LoginEntity getLoginData(){
        //return    CacheUtils.getInstance().getJSONObject(LOGINFILENAME);
        if (userInfo==null){
            String string = CacheUtils.getInstance().getString(LOGINFILENAME);
            userInfo= JsonUtil.parseJson(string,LoginEntity.class);
        }
        return userInfo;
    }
    public   LoginEntity.DataBean getUserInfor(){
    return  App.getApplication().getLoginData().getData().get(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        context = this.getApplicationContext();
        initSkinPeeler();
        //指定刷新默认的方式
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context);//指定为 Header，默认是 贝塞尔雷达Header\n
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
       return new FalsifyFooter(context);//指定为 Footer，默认是 BallPulseFooter;
            }
        });
        SDKInitializer.initialize(this);
       // FreelineCore.init(this);
        Utils.init(this);
        RongIM.init(this);

        registerActivityListener();

        newinitNohttp();
         Fragmentation.builder()
                 // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                 .stackViewMode(Fragmentation.BUBBLE)
                 .debug(true)
                 .install();
        if (!ISDEBUG) {
            initErrorPage();
            try {
                RongPushClient.checkManifest(this);
            } catch (RongException e) {
                e.printStackTrace();
            }
        }
        JMessageClient.setDebugMode(ISDEBUG);
        JMessageClient.init(this);
    }

    /**
     * 换肤初始化
     */
    private void initSkinPeeler() {
         SkinManager.getInstance().init(this);
        SkinConfig.setCanChangeStatusColor(true);
        SkinConfig.setCanChangeFont(true);
        SkinConfig.setDebug(true);
     SkinConfig.addSupportAttr("view", new ViewAttr());
        //SkinConfig.addSupportAttr("button", new RadioButtonAttr());
        SkinConfig.enableGlobalSkinApply();
    }

    /**
     * dex 分包个别手机如果不在这里init还是会报错,经常报错找不到某某.class
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

    /**
     * 初始化崩溃页面
     */
    private void initErrorPage() {
        CaocConfig.Builder.create()
               .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
             .enabled(true) //default: true//是否启用
                 .trackActivities(true)
                   /* .showErrorDetails(true) //default: true是否展示崩溃详情
                .showRestartButton(false) //default: true//是否显示重启按钮
               //default: false页面足迹
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(null) //default: bug image
                .restartActivity(MainActivity.class) //default: null (your app's launch activity)
              */
               //.eventListener(new MyEventListener()) //default: null没啥屌用
                .errorActivity(DefaultErrorActivity.class) //default: null (default error activity)
                .apply();
    }

//    /**
//     * 初始化网络
//     */
//    private void initNohttp() {
//        NoHttp.initialize(this);
////        配置超时毫秒数，默认10 * 1000ms
//        NoHttp.initialize(this, new NoHttp.Config()
//                // 设置全局连接超时时间，单位毫秒
//                .setConnectTimeout(5 * 1000)
//                // 设置全局服务器响应超时时间，单位毫秒
//                .setReadTimeout(5 * 1000)
//        );
////        配置缓存，控制开关
//        NoHttp.initialize(this, new NoHttp.Config()
//                .setCacheStore(
//                        // 保存到数据库
//                        new DBCacheStore(this).setEnable(true) // 如果不使用缓存，设置false禁用。
//                        // 或者保存到SD卡：new DiskCacheStore(this)
//                )
//        );
////        配置网络层
//        NoHttp.initialize(this, new NoHttp.Config()
//                        // 使用HttpURLConnection
//                        .setNetworkExecutor(new URLConnectionNetworkExecutor())
//                // 或者使用OkHttp
//                // .setNetworkExecutor(new OkHttpNetworkExecutor())
//        );
//        Logger.setDebug(false);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
//        Logger.setTag("NoHttp");// 设置NoHttp打印Log的tag。这个专门打印网络请求的
//    }
    private  void newinitNohttp(){
        Logger.setDebug(ISDEBUG);
        Logger.setTag("NoHttp");// 设置NoHttp打印L og的tag。这个专门打印网络请求的
        NoHttp.initialize(context);
        InitializationConfig.newBuilder(context) // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(context).setEnable(true)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(context).setEnable(true)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new  OkHttpNetworkExecutor())
                // 全局通用Header，add是添加，多次调用add不会覆盖上次add。
                //.addHeader()
                // 全局通用Param，add是添加，多次调用add不会覆盖上次add。
               // .addParam()
               // .sslSocketFactory() // 全局SSLSocketFactory。
                //.hostnameVerifier() // 全局HostnameVerifier。
                //.retry(1)// 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
    }
    public static App getApplication() {
        return application;
    }

    /**
     * 维护Activity 的list
     */
    private static List<Activity> mActivitys = Collections.synchronizedList(new LinkedList<Activity>());


    public static Context getContext() {
        return context;
    }
    /**
     * @param activity 作用说明 ：添加一个activity到管理里
     */
    public void pushActivity(Activity activity) {
        mActivitys.add(activity);
        L.d("activityList:size:"+mActivitys.size());
    }

    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity) {
        mActivitys.remove(activity);
          L.d("activityList:size:"+mActivitys.size());
    }
    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        if (mActivitys == null||mActivitys.isEmpty()) {
            return null;
        }
        Activity activity = mActivitys.get(mActivitys.size()-1);
        return activity;
    }
    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        if (mActivitys == null||mActivitys.isEmpty()) {
            return;
        }
        Activity activity = mActivitys.get(mActivitys.size()-1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (mActivitys == null||mActivitys.isEmpty()) {
            return;
        }
        if (activity != null) {
            mActivitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        if (mActivitys == null||mActivitys.isEmpty()) {
            return;
        }
        for (Activity activity : mActivitys) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public static Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (mActivitys != null) {
            for (Activity activity : mActivitys) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }
    /**
     * @return 作用说明 ：获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity = null;
        synchronized (mActivitys) {
            final int size = mActivitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivitys.get(size);
        }
        return mBaseActivity;
    }
    /**
     * @return 作用说明 ：获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName() {
        Activity mBaseActivity = null;
        synchronized (mActivitys) {
            final int size = mActivitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivitys.get(size);
        }
        return mBaseActivity.getClass().getName();
    }
    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (mActivitys == null) {
            return;
        }
        for (Activity activity : mActivitys) {
            activity.finish();
        }
        mActivitys.clear();
    }

    /**
     * 退出应用程序
     */
    public  static void appExit() {
        try {
              L.d("app exit");
            finishAllActivity();
        } catch (Exception e) {
        }
    }


    private void registerActivityListener() {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    /**
                     *  监听到 Activity创建事件 将该 Activity 加入list
                     */
                    pushActivity(activity);

                }
                @Override
                public void onActivityStarted(Activity activity) {

                }
                @Override
                public void onActivityResumed(Activity activity) {

                }
                @Override
                public void onActivityPaused(Activity activity) {

                }
                @Override
                public void onActivityStopped(Activity activity) {

                }
                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }
                @Override
                public void onActivityDestroyed(Activity activity) {
                    if (null==mActivitys&&mActivitys.isEmpty()){
                        return;
                    }
                    if (mActivitys.contains(activity)){
                        /**
                         *  监听到 Activity销毁事件 将该Activity 从list中移除
                         */
                        popActivity(activity);
                    }
                }
            });
    }
}
