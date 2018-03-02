package com.suxuantech.erpsys.activity.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.dialog.DefaultRationale;
import com.suxuantech.erpsys.dialog.PermissionSetting;
import com.suxuantech.erpsys.utils.KeyBoardUtils;
import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackActivity;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackActivityDelegate;

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
 * @author Created by 李站旗 on 2017/11/3 15:46 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 最基础的Activity(封装了常用的一些页面功能,以及权限处理)
 * 说明:关于权限的
 * 首先:关于权限这里可以是permissionListener也可是其他对象,如果是其他对象,那么你可以在那个对象类中写注解方式回调
 * 其次关于请求我提供了两种方式,
 * 一种是用我提供的使用addMustPermission()和requestMustPermission有由我来处理,这里一般都是页面必须使的权限
 *这里发起的请求我的请求码是MUSTPERMISSIONCODE,这里WRITE_EXTERNAL_STORAGE和READ_EXTERNAL_STORAGE不用添加,我已经添加好了
 * 还有一种你自己发起某个请求使用requstPermissions方法,如果回调如果为null,那么回调使用我的,结果在permissionResult中
 * 还有这里有两个弹窗
 * Rationale
 * rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
 *这样避免用户勾选不再提示，导致以后无法申请权限
 *如果想自定义 Rational
 *  1.可以请求时候传入一个RationaleListener,然后只要调用rationale.resume()就可以继续申请。
 *  2.或者重写rationaleDialog,然后调用rationale.resume方法
 * DeniedPermissionDiaLog(未获得权限被勾选了总是拒绝的)
 * 1.可以重写alwaysDeniedPermissionDiaLog方法(有主意事项,alwaysDeniedPermissionDiaLog在方法中有说明)
 * 2.或者可以传入一个permissionListener,然后自己处理剩下的流程,如果你有了permissionListener,
 * 那么我就不会管理你的了如果想还用permissionResult方法接收你可以调用我的这个也可以
 * 其他说明:useButterKnife 这里默认提供了一个注解你可以直接使用，页面销毁的时候也会自动销毁
 * 1.注解点击事件为方法onClick(View view)时候无法走widgetClick点击事件（也就是idSetOnClick无法生效）,
 * 如果注解的方法不是onClick(View view) 而是其他的比如onxxx(View v) 这时候如果也设置了idSetOnClick那么就会走widgetClick
 * 总结 注意这里主要的就是onClick是否被重写
 * 如果别重写了onClick，那么注解优先级高，如果没有重写onClick那么idSetOnClick高
 *
 * 支持 EventBus消息接收
 *  如果使用EventBus也就是useEventBus方法
 *  那么必须在该页面一定要有一个方法用来接收消息，
 *  当然这个方法需要注解：@Subscribe而且这个方法必须是public类型的
 */
public  class BaseActivity extends SupportActivity implements View.OnClickListener  , ISwipeBackActivity {
    final SwipeBackActivityDelegate mDelegate = new SwipeBackActivityDelegate(this);

    /**
     * 按钮快速点击时间(多少毫秒内点击同一个算快速点击)
     */
    private int fastClickTime=1000;
    /**
     * [防止快速点击的记录上次点击时间
     */
    private  long lastClick = 0;
    /**
     * 上次点击的View
     */
   private View lastView;
    /**
     *请求必须权限的请求码
     */
    private final int MUSTPERMISSIONCODE = 2626;
    /**
     * 去设置页面的请求码
     * 默认值(因为这里可能会有用到)
     */
    private  int  goToSetting=-66;
    /**
     * Tag不解释
     */
    protected final String TAG = this.getClass().getSimpleName();
    /**
     *     本页面需要的权限(hash的目的是去除重复添加的,没必要多次重复添加)
     */
    private HashSet<String> permissionSet = new HashSet<String>();
    /**
     * 网络队列,默认每个Activity都应该有一个队列,用这个队列在页面销毁可以全部取消major
     */
    private RequestQueue requestQueue;
    private Unbinder unbinder;
    private Rationale mRationale;
    private PermissionSetting mSetting;

    /**
     * 权限授予结果
     * @param permissions
     */
     public void  permissionGrantedResult(List<String> permissions ){

     }
    public void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        permissionGrantedResult(permissions);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }else {
                            toast(R.string.failure_permission);
                        }
                    }
                })
                .start();
    }


    private void requestPermission(String[]... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        permissionGrantedResult(permissions);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }else {
                            toast(R.string.failure_permission);
                        }
                    }
                })
                .start();
    }


    /**
     * Check if the calling context has a set of permissions.
     *
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public   boolean hasPermission(  @NonNull String... permissions) {
        return hasPermission(Arrays.asList(permissions));
    }

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public  boolean hasPermission(  @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result == PackageManager.PERMISSION_DENIED) return false;

            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) continue;
            result = AppOpsManagerCompat.noteProxyOp(this, op, this.getPackageName());
            if (result != AppOpsManagerCompat.MODE_ALLOWED) return false;

        }
        return true;
    }

    //----------------------页面管理------------------------------
    /**
     * 禁止截屏
     */
    public void forbiddenTakeScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }
    /**
     * 隐藏状态栏
     */
    public void hideStatus() {
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /**
     * 显示状态栏
     */
    public void showStatus() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**
         * 下边代码会5.0以下系统被切掉一块
         * 下面是强制有标题的,这样就会导致再次设置FLAG_FULLSCREEN无效
         */
//        this.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);;//取消ActionBar
    }
    /**
     * 强制屏幕方向
     *
     * @param forceHorizontalScreen 横屏还是竖屏
     */
    public void screenMustOrientation(boolean forceHorizontalScreen) {
        if (forceHorizontalScreen) {
            //横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            //竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 获取屏幕
     * @param isContainStatusBar 是否包含状态栏
     * @return
     */
    public Bitmap getScreen(boolean isContainStatusBar){
        if (isContainStatusBar){
            return  ScreenUtils.snapShotWithStatusBar(this);
        }else {
            return ScreenUtils.snapShotWithoutStatusBar(this);
        }
    }

    /**
     * 隐藏软键盘
     * @param et
     */
    public void hideSoftKeyBoard(EditText et){
        KeyBoardUtils.closeKeybord(et,this);
    }
    /**
     * 打开软键盘
     * @param et
     */
    public void showSoftKeyBoard(EditText et){
        KeyBoardUtils.openKeybord(et,this);
    }
    /**
     * 弹出一个吐司(不建议这种方式,因为最好在吧文字抽出来更规范)
     * @param str
     */
    @Deprecated
    public void toast(String str) {
        ToastUtils.show(str);
    }

    /**
     * 弹出一个吐司
     * @param str
     */
    public void toast(@StringRes int str) {
        ToastUtils.show(str);
    }
    //----------------------页面管理------------------------------
    /**
     * 获取本页面的队列
     * @return RequestQueue 队列
     */
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    //------------------------------------生命周期---------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
        permissionSet.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionSet.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        requestQueue = NoHttp.newRequestQueue();
        dLog(TAG + "-->onCreate()");
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dLog(TAG + "--->onResume()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //加上判断
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (useButterKnife&&unbinder!=null){
            unbinder.unbind();
        }
        requestQueue.cancelAll();
        requestQueue.stop();
        dLog(TAG + "--->onDestroy()");
    }
    //----------------生命周期end-----------------
    //--------------------------------------日志模块------------------
    /**
     * 错误日志(Tag是当前Activity名字)
     * @param str
     */
    public void eLog(String str) {
        L.e(TAG, str);
    }
    /**
     * 警告日志(Tag是当前Activity名字)
     * @param str
     */
    public void wLog(String str) {
        L.w(TAG, str);
    }
    /**
     * debug日志(Tag是当前Activity名字)
     *
     * @param str
     */
    public void dLog(String str) {
            L.d(TAG, str);
    }
    /**
     * infor日志(Tag是当前Activity名字)
     *
     * @param str
     */
    public void iLog(String str) {
        L.d(TAG, str);
    }

    /**
     * [日志输出输入的Tag来自Application统一的]
     *
     * @param msg
     */
    protected void appLog(String msg) {
        L.d(msg);
    }
    //--------------------------------------日志模块end------------------
    //--------------------------------findView-----------------
    boolean  useButterKnife ,useEventBus ;

    /**
     * 使用注解
     */
   public void useButterKnife(){
       useButterKnife=true;
       unbinder = ButterKnife.bind(this);
   }

    /**
     * 使用EventBus
     *  如果使用EventBus
     *  那么必须在在该页面有一个方法用来接收消息，
     *  当然这个方法需要注解：@Subscribe 且方法必须是public
     */
   public  void  useEventBus(){
       useEventBus=true;
       //加上判断
       if (useEventBus&&!EventBus.getDefault().isRegistered(this)) {
           EventBus.getDefault().register(this);
       }
   }
    @Override
    public void onStart() {
        super.onStart();
        //加上判断
        if (useEventBus&&!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
        }
    }

    /**
     * 根据id找view
     * @param resId id资源
     * @param <T>
     * @return
     */
    public <T extends View> T idGetView(@IdRes int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * 找view并设置点击事件
     * @param resId 资源id
     * @param <T>
     * @return
     */
    public <T extends View> T idSetOnClick(@IdRes int resId) {
        T viewById = super.findViewById(resId);
         if (useButterKnife){
            wLog("idSetOnClick提示：\n" +
                    "ButterKnife和idsetConclick同时使用了\n" +
                    "请确保onClick(View v)不被ButterKnife回调重写。\n" +
                    "如果您的ButterKnife点击回调方法为onClick(View v),\n" +
                    "那么idSetOnClick(int resId)无法生效；\n" +
                    "ButterKnife点击回调注解非onClick(View v)；\n" +
                    "那么idSetOnClick(int resId)的点击事件widgetClick(View view)优先,\n" +
                    "ButterKnife点击事件无效.");
        }
        viewById.setOnClickListener(this);
        return viewById;
    }

    /**
     * 找到VIew并设置点击事件
     * @param id 资源id
     * @return
     */
    public View findViewSetOnClick(@IdRes int id) {
        return idSetOnClick(id);
    }
//  ------------------------------页面跳转---------------
    /**
     * [页面跳转]
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    /**
     * [含有Bundle通过Class打开编辑界面]
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

//  ------------------------------页面跳转end---------------

    //---------------------------------View点击----------------

    /**
     * view点击事件
     * @param v 点击的view
     */
     public  void widgetClick(View v){}

    /**
     * 多少毫秒以内的点击同一个View都是快速点击
     *
     * @return
     */
    public boolean fastClick(View v) {
        if (System.currentTimeMillis() - lastClick <= fastClickTime && lastView == v) {
            toast("已经点过了");
            return false;
        }
        lastView = v;
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * View的点击事件
     *如果你使用了ButterKnifename不会走这个的
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (fastClick(v)) {
            widgetClick(v);
        }
    }
    //--------------------------------------View点击end------------------

    public void hideSystemNavigation(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    /**
     *     获取是否存在NavigationBar(也就是是否存在导航栏)
     */
    public  boolean checkDeviceHasNavigationBar( ) {
        return ScreenUtils.checkDeviceHasNavigationBar(getBaseContext());
    }
    /**
     //透明状态栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     //透明导航栏
     *getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
     * 获取状态栏高度
     * @return
     */
    public  int getStatusBarHeight( ) {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result =getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     * @return
     */
    public  int getNavigationBarHeight( ) {
        return  ScreenUtils.getNavigationBarHeight(getBaseContext());
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mDelegate.getSwipeBackLayout();
    }

    /**
     * 是否可滑动
     * @param enable
     */
    @Override
    public void setSwipeBackEnable(boolean enable) {
        mDelegate.setSwipeBackEnable(enable);
    }

    @Override
    public void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel) {
        mDelegate.setEdgeLevel(edgeLevel);
    }

    @Override
    public void setEdgeLevel(int widthPixel) {
        mDelegate.setEdgeLevel(widthPixel);
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return mDelegate.swipeBackPriority();
    }


}
