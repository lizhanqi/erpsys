package com.suxuantech.erpsys.activity.base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.suxuantech.erpsys.utils.KeyBoardUtils;
import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yanzhenjie.permission.Request;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
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
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener{

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
    /**
     * 去设置页面设置权限临时存储的,防止多次去设置,分不清回来的是那个
     */
    private HashMap<Integer ,HashSet<String>> gotoSettingPermission=new HashMap<>();
    /**
     * 请求权限的监听
     * 结果监听
     */
    private PermissionListener permissionListener=new PermissionListener(){
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            permissionResult(true,requestCode,grantPermissions);
        }
        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this,deniedPermissions)){
                gotoSettingPermission.put(requestCode,new HashSet<String>(deniedPermissions));
                goToSetting=requestCode;
                alwaysDeniedPermissionDiaLog(requestCode,deniedPermissions);
            }else {
                permissionResult(false,requestCode,deniedPermissions);
            }

        }
    };
    /**
     * 授权前的告知对话框
     */
    private   RationaleListener  rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            rationaleDialog(requestCode,rationale);
        }
    };
    private Unbinder unbinder;

    /**
     * 询问弹窗(可以重写)
     * 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
     * @param requestCode 请求码
     * @param rationale
     */
   public void  rationaleDialog(int requestCode,Rationale rationale){
       AndPermission.rationaleDialog(this, rationale).show();
    }
    /**
     * 总是拒绝弹窗(切记如果这里重写后自定义弹窗那么取消按钮一定要调用下resetGotoSettings,确定按钮必须管,那个你需要跳转到设置页面)
     * restGeotoSettings();进行重置,移除去设置页面的请求权限,权限请求失败了
     * @param deniedPermissions 设置权限
     */
    public  void alwaysDeniedPermissionDiaLog(final int requestSettingCode, final List<String> deniedPermissions) {
        AndPermission.defaultSettingDialog(this, requestSettingCode).setNegativeButton("不要", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGotoSettings(requestSettingCode,deniedPermissions);
            }
        }).show();

    }
    /**
     * 当提示用户进行去设置页面给权限时候,用户点击了取消,移除去设置的码,并回调给结果
     * @param requestSettingCode
     * @param deniedPermissions
     */
    public  final  void resetGotoSettings (final int requestSettingCode, final List<String> deniedPermissions){
        //移除
        gotoSettingPermission.remove(requestSettingCode);
        //重置设置
        goToSetting=-66;
        permissionResult(false,requestSettingCode,deniedPermissions);
    }
//---------------对话的框end-----------------------------------------
// -------------------权限请求-------------------
    /**
     * 添加必须的权限
     * @param isNowRequest 是否现在发起权限请求
     * @param useDefaultRationale 是否使用默认Rationale弹窗
     * @param permission 权限
     */
    public final void addMustPermission(boolean isNowRequest,boolean useDefaultRationale,String... permission) {
        for (String per : permission) {
            permissionSet.add(per);
        }
        if (isNowRequest ) {
            requestMustPermission(useDefaultRationale);
        }
    }
    /**
     *   添加必须的权限(没有Rationale)
     * @param isNowRequest 是否现在请求
     * @param permission
     *
     */
    public final void addMustPermission(boolean isNowRequest,String... permission) {
        for (String per : permission) {
            permissionSet.add(per);
        }
        if (isNowRequest ) {
            requestMustPermission(false);
        }
    }
    /**
     * 仅仅添加必须权限
     * @param permission
     */
    public final void addMustPermission(String... permission) {
        for (String per : permission) {
            permissionSet.add(per);
        }
    }
    /**
     * 发起重要的页面必须的请求
     * @param useDefaultRationale 是否使用默认提示框
     */
    public void requestMustPermission(boolean useDefaultRationale){
        requstPermissions(MUSTPERMISSIONCODE,true,null,null,new ArrayList<String>(permissionSet));
    }

    /**
     * 获取本页面必须的权限
     * @return
     */
    public HashSet<String> getPermissionSet(){
        return permissionSet;
    }
    /**
     * 移除一个页面必须的权限(一般不会用)
     * @param permission
     */
    public void removeMustPermission(String... permission) {
        for (String per : permission) {
            permissionSet.remove(per);
        }
    }
    /**
     * 权限最终结果
     * @param hasPermission 是否有权限
     * @param requsetcode 请求码 (通过addMustPermission中的权限) 请求码是MUSTPERMISSIONCODE
     * @param permission 请求的权限列表
     */
    protected abstract void permissionResult(boolean hasPermission, int requsetcode, List<String> permission);

    /**
     * 请求权限(没有Rationale,回调用我的)
     * @param requsetCode 请求码
     * @param permissions 请求的权限
     */
    public void requstPermissions(int requsetCode,List<String> permissions) {
        requstPermissions(requsetCode,false,null,permissionListener,permissions);
    }

    /**
     * 请求权限(没有Rationale提示,回调使用我提供的)
     * @param requsetCode
     * @param permissions
     */
    public void requstPermissions(int requsetCode,String... permissions) {
        requstPermissions( requsetCode,false, permissions);
    }
    /**
     * 请求权限(请求权限,是否使用默认的提示框回调用我的)
     * @param requsetCode 请求码
     * @param useRationale 是否使用默认的提示框
     * @param permissions 权限
     */
    public void requstPermissions(int requsetCode,boolean useRationale,String... permissions) {
        //添加使用Hash过滤一遍重复的
        HashSet<String> objects = new HashSet<>();
        for (String per : permissions) {
            objects.add(per);
        }
        requstPermissions(requsetCode,useRationale,null,permissionListener,new ArrayList<String>(objects){});
    }

    /**
     * 请求权限(自定义Rationale,回调用我的)
     * @param requsetCode
     * @param userRationale
     * @param permissions
     */
    public void requstPermissions(RationaleListener userRationale,int requsetCode,String... permissions) {
        //添加使用Hash过滤一遍重复的
       HashSet<String> objects = new HashSet<>();
        for (String per : permissions) {
            objects.add(per);
        }
        requstPermissions(requsetCode,false,userRationale,permissionListener,new ArrayList<String>(objects){});
   }
    /***
     * 请求权限(自定义的Rationale和回调)
     * @param requsetCode 请求码
     * @param userRationale 用户自定义Rationale (null则不进行设置Rationale包括我提供的)
     * @param permissionListener 权限结果回调(null则就是我的,如果不是permissionListener那么就是注解方式回调自己写注解)
     * @param permissions  权限
     */
    public void requstPermissions(RationaleListener userRationale, Object permissionListener ,int requsetCode, String... permissions) {
        HashSet<String> objects = new HashSet<>();//添加使用Hash过滤一遍重复的
        for (String per : permissions) {
            objects.add(per);
        }
        requstPermissions(requsetCode,false,userRationale,permissionListener,new ArrayList<String>(objects){});
    }

    /** 请求权限 (是否使用默认Rationale和自定义回调)
     * @param requsetCode 请求码
     * @param useDefaultRationale 是否使用我提供的Rationale,
     * @param permissionListener   你的回调(如果是null则用我的)
     * @param permissions 权限
     */
    public void requstPermissions(int requsetCode, Object permissionListener , boolean useDefaultRationale, String... permissions) {
        //添加使用Hash过滤一遍重复的
        HashSet<String> objects = new HashSet<>();
        for (String per : permissions) {
            objects.add(per);
        }
        requstPermissions(requsetCode,useDefaultRationale,null,permissionListener,new ArrayList<String>(objects){});
    }
    /**
     * 最终进行权限请求的方方法(如果不设置Rationale,默认的给flase,rationale给null)
     * @param requsetCode 请求码
     * @param permissions 权限
     * @param useDefaultRationale 是否使用默认的Rationale
     * @param rationale 用户的 rationale
     * @param permissionListener 用户的结果回调(为什么是Object因为用户使用注解进行的时候方便)如果为空就用我的
     */
    public void requstPermissions(int requsetCode,boolean useDefaultRationale,RationaleListener rationale,Object permissionListener,List<String> permissions) {
//        String[] tempPermission = permissionList.toArray(new String[]{});//关键语句
        if (!hasPermission(permissions)) {
            Request mRequest = AndPermission.with(this);
            mRequest.requestCode(requsetCode);
            mRequest.permission(permissions.toArray(new String[]{}));
            //这里不进行null检查,如果是null那么用户有可能自己进行注解方式回调
            if (permissionListener!=null){
                mRequest.callback(permissionListener);
            }else {
                mRequest.callback(this.permissionListener);
            }
            //用户自定义接管对话框
            if (rationale!=null){
                mRequest.rationale(rationale);
                //使用我们自己默认的
            }else if (useDefaultRationale){
                mRequest.rationale(rationaleListener);
            }
            mRequest.start();
        }else if (permissionListener==null){
         permissionResult(true,requsetCode,permissions);
        }
    }
//-------------------权限请求-------------------
    /**
     * 检查是否有权限
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        return AndPermission.hasPermission(this, permissions);
    }
    /**
     * 检查是否有权限
     * @param permissions
     * @return
     */
    public boolean hasPermission(List permissions) {
        return AndPermission.hasPermission(this, permissions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==goToSetting){
            boolean b = hasPermission(new ArrayList(gotoSettingPermission.get(requestCode)));
            ArrayList arrayList = new ArrayList(gotoSettingPermission.get(requestCode));
            //移除
            gotoSettingPermission.remove(requestCode);
            if (b){
                permissionResult(true,requestCode,arrayList);
            }else {
                permissionResult(false,requestCode,arrayList);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
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
        permissionSet.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionSet.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        requestQueue = NoHttp.newRequestQueue();
        dLog(TAG + "-->onCreate()");
//        requstPermissions(MUSTPERMISSIONCODE,new ArrayList<String>(permissionList),true);
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
        T viewById = (T) super.findViewById(resId);
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
     protected abstract void widgetClick(View v);

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

}
