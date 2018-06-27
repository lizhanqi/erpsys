package com.suxuantech.erpsys;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.antfortune.freeline.util.AppUtils;
import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.codemonkeylabs.fpslibrary.TinyDancer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.suxuantech.erpsys.entity.LoginEntity;
import com.suxuantech.erpsys.entity.UserEntity;
import com.suxuantech.erpsys.ui.activity.DefaultErrorActivity;
import com.suxuantech.erpsys.ui.activity.LoginActivity;
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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 应用入口文件
 */

public class App extends Application {
    protected static Context context;
    private static App application;
    public static boolean ISDEBUG = true;
    /**
     * 是否关心登录即时通讯
     */
    public static boolean CARE_IM_LOGIN = true;
    public static String APP_LOG_NAME = "debug";
    /**
     * 登录用户信息保存的文件名
     */
    public final static String LOGIN_FILE_NAME = "login_inf";
    private LoginEntity userInfo;
    private List<String> userPermission;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public void setUserPermission(List<String> userPermission) {
        this.userPermission = userPermission;
    }
    /**
     * 退出登录
     */
    public void loginOut() {
        JMessageClient.logout();
        userInfo = null;
        userPermission = null;
        CacheUtils.getInstance().remove(App.LOGIN_FILE_NAME);
        SPUtils.getInstance().remove(LoginActivity.LOGIN_NAME);
        SPUtils.getInstance().remove(LoginActivity.LOGIN_PASSWORD);
        Intent intent = new Intent(App.getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
    }

    /**
     * 检测是否有权限
     * Add("A1","门市销售");    Add("A2","门市查询");           Add("A3","门市开单");Add("A5","添加产品");Add("A6","删除产品")
     * Add("B1","礼服销售");    Add("B2","礼服查询");
     * Add("C1","化妆销售");    Add("C2","化妆查询");
     * Add("D1","摄影销售");    Add("D2","摄影销售");
     * Add("E1","选片销售");    Add("E2","选片查询");
     * Add("F1","取件订单");    Add("F2","取件订单查询");
     * Add("I1","统计分析");    Add("I2","订单统计");         Add("I3","实收明细统);
     * Add("J1","前台收银");    Add("J2","收款查询");         Add("I3","实收明细统计");
     * Add("K1","进客登记");    Add("K2","进客信息查询");     Add("K3", "新建客资");Add ("K5", "删除客资"):
     * Add("K8","已进店状态");  Add ("K9","未进店状态");      Add("K10",“转订单");    Add("K11", "流失");
     * Add("K14","查询其他门市客资"); Add ("K15", “查询其他登记人客资");
     * Add("M1","摄影排程");    Add ("M2", “排程查询");     Add ("M3", “排程"); Add ("M4", “删除排程");
     * Add("M5","占位");        Add ("M6", “取消占位");
     *
     * @param permisstion
     * @return
     */
    public boolean hasPermission(String permisstion) {
        if (userPermission != null) {
            return userPermission.contains(permisstion);
        } else {
            return false;
        }
    }

    public LoginEntity getLoginData() {
        //return    CacheUtils.getInstance().getJSONObject(LOGINFILENAME);
        if (userInfo == null) {
            String string = CacheUtils.getInstance().getString(LOGIN_FILE_NAME);
            userInfo = JsonUtil.parseJson(string, LoginEntity.class);
        }
        return userInfo;
    }

    public App releaseLoginData() {
        userInfo = null;
        return this;
    }

    public UserEntity getUserInfor() {
        return App.getApplication().getLoginData().getData().get(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = this.getApplicationContext();
        if (AppUtils.isMainProcess(this)){
            //初始化greendao
            setDatabase();
            //  initSkinPeeler();
            //初始化刷新默认头尾
            initFreshDefault();
            //初始化百度地图
            SDKInitializer.initialize(this);
            // FreelineCore.init(this);
            //工具类初始化
            Utils.init(this);
            //融云初始化
            RongIM.init(this);
            //Activity的队列监听
            registerActivityListener();
            //网络初始化
            newinitNohttp();
            //错误页初始化
            initErrorPage();
            //fragmention显示球初始化
            Fragmentation.builder()
                    // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                    .stackViewMode(Fragmentation.BUBBLE)
                    .debug(ISDEBUG)
                    .install();

            if (!ISDEBUG) {
                try {
                    //融云检测初始化
                    RongPushClient.checkManifest(this);
                } catch (RongException e) {
                    e.printStackTrace();
                }
            }else{
                //帧数检测初始化
                TinyDancer.create()
                        .show(context);
            }

            //极光IM初始化
            JMessageClient.setDebugMode(ISDEBUG);
           // JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_LED|JMessageClient.NOTI_MODE_NO_VIBRATE);
            JMessageClient.init(this);
        }
    }

    private void initFreshDefault() {
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
    }

    /**
     * 包名判断是否为主进程
     * @param
     * @return
     */
    public boolean isMainProcess(){
        return getApplicationContext().getPackageName().equals(getCurrentProcessName());
    }
    /**
     * 获取当前进程名
     */
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }
    /**
     * 换肤初始化
     */
    private void initSkinPeeler() {
//        SkinManager.getInstance().init(this);
//        SkinConfig.setCanChangeStatusColor(true);
//        SkinConfig.setCanChangeFont(true);
//        SkinConfig.setDebug(true);
//        SkinConfig.addSupportAttr("view", new ViewAttr());
//        //SkinConfig.addSupportAttr("button", new RadioButtonAttr());
//        SkinConfig.enableGlobalSkinApply();
    }

    /**
     * dex 分包个别手机如果不在这里init还是会报错,经常报错找不到某某.class
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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
    private void newinitNohttp() {
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
                .networkExecutor(new OkHttpNetworkExecutor())
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
        L.d("activityList:size:" + mActivitys.size());
    }

    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity) {
        mActivitys.remove(activity);
        L.d("activityList:size:" + mActivitys.size());
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return null;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        return activity;
    }

    public static List<Activity> getmActivitys() {
        return mActivitys;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (mActivitys == null || mActivitys.isEmpty()) {
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
    public void finishActivity(Class<?> cls) {
        if (mActivitys == null || mActivitys.isEmpty()) {
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
    public Activity findActivity(Class<?> cls) {
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
    public static void appExit() {
        try {
            L.d("app exit");
            finishAllActivity();
        } catch (Exception e) {
        }
    }

    private boolean isForeground = false;//应用是否处于前端

    public boolean isForeground() {
        return isForeground;
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
                isForeground = true;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                isForeground = false;
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }


            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == mActivitys && mActivitys.isEmpty()) {
                    return;
                }
                if (mActivitys.contains(activity)) {
                    /**
                     *  监听到 Activity销毁事件 将该Activity 从list中移除
                     */
                    popActivity(activity);
                }
            }
        });
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "sxdb", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
