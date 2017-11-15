package com.suxuantech.erpsys.activity.base;

import android.Manifest;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener{
    /**
     * 是否输出日志信息
     **/
    public int fastClickTime=1000;
    private final int MUSTPERMISSIONCODE = 2626;
    protected final String TAG = this.getClass().getSimpleName();
    //本页面需要的权限(hash的目的是去除重复添加的,没必要多次重复添加)
    private HashSet<String> permissionList = new HashSet<String>();
    //网络队列,默认每个Activity都应该有一个队列,用这个队列在页面销毁可以全部取消
    private RequestQueue requestQueue;
    private HashMap<Integer ,HashSet<String>> rqse=new HashMap<>();
//-------------------权限请求-------------------
/**
     * 添加必须的权限
     * @param permission
     */
    public void addMustPermission(String... permission) {
        for (String per : permission) {
            permissionList.add(per);
        }
    }
    PermissionListener permissionListener=new PermissionListener(){
    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
    }
    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
    }
};
    RationaleListener  rationaleListener = new RationaleListener() {
    @Override
    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
         }
    };
   // public  abstract void    permissionResult(boolean hasPermission,int requseCode,List<String> permissions);
    /**
     * 请求的权限
     * 默认呢没有Rotate
     */
    public void requstPermissions(int requsetCode,List<String> permissions) {
        requstPermissions(requsetCode,permissions,false,null,null);
    }
    /**
     * 请求权限默认没有Rationale提示
     * @param requsetCode
     * @param permissions
     */
    public void requstPermissions(int requsetCode,String... permissions) {
        requstPermissions( requsetCode,false, permissions);
    }
    /**
     * 请求权限
     * @param requsetCode
     * @param useRationale 如果要使用自定义的Rationale弹窗请重写showRequestPermissionRationale方法
     * @param permissions
     */
    public void requstPermissions(int requsetCode,boolean useRationale,String... permissions) {
        HashSet<String> objects = new HashSet<>();//添加使用Hash过滤一遍重复的
        for (String per : permissions) {
            objects.add(per);
        }
        requstPermissions(requsetCode,new ArrayList<String>(objects){},useRationale,null,null);
    }
    /**
     * 请求的权限
     */
    public void requstPermissions(int requsetCode,List<String> permissions,boolean useDefaultRationale,RationaleListener rationale,PermissionListener callBack) {
//        // 先将set集合转为Integer型数组
//        String[] tempPermission = permissionList.toArray(new String[]{});//关键语句
        if (!hasPermission(permissions)) {//没有权限在再进行请求
            // 申请多个权限。
            Request mRequest = AndPermission.with(this);
            mRequest.requestCode(requsetCode);
            mRequest.permission(permissions.toArray(new String[]{}));
            if (callBack!=null){
                mRequest.callback(callBack);
            }else {
                mRequest.callback(permissionListener);
            }
            if (rationale!=null){
                mRequest.rationale(rationale);
            }else if (useDefaultRationale){
                mRequest.rationale(rationaleListener);
            }
            mRequest.start();
        }else {
//            onSucceed(requsetCode,permissions);
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
    /**
     *总是拒绝弹窗
     */
    public  void hasAlwaysDeniedPermissionDiaLog(int requestSettingCode, List<String> deniedPermissions) {
// 是否有不再提示并拒绝的权限。
            // 第一种：用AndPermission默认的提示语。
            AndPermission.defaultSettingDialog(this, requestSettingCode).show();
//            // 第二种：用自定义的提示语。
//            AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                    .setTitle("权限申请失败")
//                    .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
//                    .setPositiveButton("好，去设置")
//                    .show();
//            // 第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            // 你的dialog点击了确定调用：
//            settingService.execute();
//            // 你的dialog点击了取消调用：
//            settingService.cancel();
    }
//    /**
//     * 所有的权限请求成功都会在这里
//     * @param requestCode
//     * @param grantedPermissions
//     */
//   @Override
//    public void onSucceed(int requestCode, List<String> grantedPermissions) {
//
//    }
    /**
     * 权限获取失败
     * @param requestCode
     * @param deniedPermissions
     */
//  @Override
//    public void onFailed(int requestCode, List<String> deniedPermissions) {
//      // 是否有不再提示并拒绝的权限。
//      if (AndPermission.hasAlwaysDeniedPermission(this,deniedPermissions)) {
//          hasAlwaysDeniedPermissionDiaLog(requestCode,deniedPermissions);
//      }
//    }





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
//        //防止是侵入式需要隐藏一下
//        if (mStatusView != null)
//            mStatusView.setVisibility(View.GONE);
    }
    /**
     * 显示状态栏
     */
    public void showStatus() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
/*        //防止是侵入式需要显示出来一下
        if (mStatusView != null)
            mStatusView.setVisibility(View.VISIBLE);*/
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
        if (forceHorizontalScreen) {//横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
         /*   int screenWidth = ScreenUtils.getScreenWidth(this);
            try {
                ReflexUtils.setNotAccessibleProperty(mStatusView, "screenWidth", screenWidth);
                ReflexUtils.setNotAccessibleProperty(mNavigationView, "screenWidth", screenWidth);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        } else {//竖屏
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

    public void toast(String str) {
        ToastUtils.show(str);
    }
    public void toast(@StringRes int str) {
        ToastUtils.show(str);
    }
    //----------------------页面管理------------------------------
    //-------------网络队列--------------
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    //------------------------------------生命周期---------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
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
        requestQueue.cancelAll();
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

    //    @SuppressWarnings("unchecked")
    //找view
    public <T extends View> T idGetView(@IdRes int resId) {
        return (T) super.findViewById(resId);
    }

    //找view并设置点击事件
    public <T extends View> T idSetOnClick(@IdRes int resId) {
        T viewById = (T) super.findViewById(resId);
        viewById.setOnClickListener(this);
        return viewById;
    }

    public View findViewSetOnClick(@IdRes int id) {
        return idSetOnClick(id);
    }
//  ------------------------------页面跳转---------------
    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
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
     *
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
     * View点击
     **/
    public abstract void widgetClick(View v);
    /**
     * [防止快速点击]
     *
     * @return
     */
    long lastClick = 0;
    View lastView;

    /**
     * 500毫秒以内的点击都是快速点击
     *
     * @return
     */
    private boolean fastClick(View v) {
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
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (fastClick(v)) {
            widgetClick(v);
        }
    }
    //--------------------------------------View点击end------------------
}
