package com.suxuantech.erpsys.ui.activity.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.widget.AdjustDrawableTextView;

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
 * @author Created by 李站旗 on 2017/11/6 9:25 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 状态栏和导航栏沉浸式（注意不要使用协调者布局,以及约束布局）
 */

abstract public  class ImmersedBaseActivity extends BaseActivity {
    boolean immersionBarEnabled;
    private View rootViews;//填充的view
    //toolbar
    private Toolbar mToolbar;
    //自定义头部导航最大view
    private RelativeLayout mHeadNavUseDefinedRoot;
    //自定义导航
    private AdjustDrawableTextView mTvNavLeft;
    private AdjustDrawableTextView mTvNavTitle;
    private AdjustDrawableTextView mTvNavRight;
    //继承者setview都会添加到这个下面
    private FrameLayout mContentViewlayout;
    //当前Activity渲染用户的主要内容
    private View mContextView;
    private View lineView;
    protected ImmersionBar mImmersionBar;

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (v.getId()==R.id.tv_nav_left){
            onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        initImmersionBar();
        super.onRestart();
    }
    @Override
    public void onStart() {
        initImmersionBar();
        super.onStart();
    }

    @Override
    protected void onResume() {
        initImmersionBar();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
           //在销毁释放内存
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        super.onDestroy();
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.statusBarDarkFont(true,0.15f);
        mImmersionBar .statusBarColor(R.color.white) ;
        mImmersionBar.keyboardEnable(false);
        mImmersionBar .navigationBarColor(R.color.translucent_black_90) ;
        mImmersionBar.init();
    }

    @Override
    public void setTitle(CharSequence title) {
        if(mHeadNavUseDefinedRoot.getVisibility()==View.VISIBLE){
            setUseDefinedNavTitle(title);
        }
        super.setTitle(title);
    }
    @Override
    public void setTitle(int titleId) {
        if(mHeadNavUseDefinedRoot.getVisibility()==View.VISIBLE){
            setUseDefinedNavTitle(getResources().getString(titleId ));
        }
        super.setTitle(titleId);
    }

    //-----------------------------顶部导航-------------------------------------
    //获取自定义导航左侧view
    public AdjustDrawableTextView getNavLeftView() {
        return mTvNavLeft;
    }
    //获取自定义导航标题
    public AdjustDrawableTextView getNavTitleView() {
        return mTvNavTitle;
    }
    //获取自定义导航右侧view
    public AdjustDrawableTextView getNavRightView() {
        return mTvNavRight;
    }
    /**
     * 获取自定义导航头最大父布局
     * @return
     */
    public RelativeLayout getHeadNavUseDefinedRootView() {
        return mHeadNavUseDefinedRoot;
    }
    /**
     * 获取提供的Toolbar
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 设置(内置)Toolbar支持
     * @return
     */
    public Toolbar setSupportToolbar() {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(mToolbar);
            return mToolbar;
        }
        return null;
    }

    /**
     * 隐藏(内置)Toolbar
     */
    public void hideToolbar() {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.GONE);
        }
    }
    /**
     * 显示用户自定义导航
     */
    public void showUserDefinedNav(){
        mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
    }
    /**
     * 隐藏用户自定义导航
     */
    public void hideUserDefinedNav(){
        mHeadNavUseDefinedRoot.setVisibility(View.GONE);
    }
    public View getLineView(){
        return lineView;
    }

    public void setUserDefinedLineViewBG(@IntegerRes int  lineColor) {
        lineView.setBackground(null);
        lineView.setBackgroundColor(lineColor);
    }
    //----------------自定义导航标题--------------
    /**
     *
     * @param title 自定义导航处标题
     */
    public void setUseDefinedNavTitle(CharSequence title) {
        if (mHeadNavUseDefinedRoot.getVisibility()!=View.VISIBLE){
            mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
        }
        if (mTvNavTitle.getVisibility()!=View.VISIBLE){
            mTvNavTitle.setVisibility(View.VISIBLE);
        }
        mTvNavTitle.setText(title);
    }
    /**
     * 标题文本大小
     * @param fontSize
     */
    public void setUseDefinedNavTitleTextSize(int fontSize){
        mTvNavTitle.setTextSize(fontSize);
    }
    /**
     * 标题文本颜色
     * @param mColor
     */
    public void setUseDefinedNavTitleTextColor(int mColor){
        mTvNavTitle.setTextColor(mColor);
    }
    //---------------------自定义导航左侧-------------
    /**
     * 设置自定义导航左侧文本
     * @param title
     */
    public void setUseDefinedNavLeftText(CharSequence title) {
        if (mHeadNavUseDefinedRoot.getVisibility()!=View.VISIBLE){
            mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
        }
        if (mTvNavLeft.getVisibility()!=View.VISIBLE){
            mTvNavLeft.setVisibility(View.VISIBLE);
        }
        mTvNavLeft.setText(title);
    }
    /**
     * 左侧图片
     * @param drawable
     */
    public void setUseDefinedNavLeftDrawable(Drawable drawable){
        if (drawable!=null){
            setUseDefinedNavLeftDrawable(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }else {
            setUseDefinedNavLeftDrawable(null, 0,0);

        }

    }
    /**
     * 左侧图片指定宽高
     * @param drawable
     * @param width
     * @param heigth
     */
    public void setUseDefinedNavLeftDrawable(Drawable drawable,int width,int heigth){
        if (mHeadNavUseDefinedRoot.getVisibility()!=View.VISIBLE){
            mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
        }
        if (mTvNavLeft.getVisibility()!=View.VISIBLE){
            mTvNavLeft.setVisibility(View.VISIBLE);
        }
        //先定义好宽度,在进行绘制,避免设置不生效
        mTvNavLeft.setDrawableSize(width,heigth, AdjustDrawableTextView.InWhere.LEFTONTEXT);
        //绘制
        mTvNavLeft.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }
    /**设置左侧标题和一个图标,并指定图标大小
     * @param title
     * @param drawable
     * @param drawableWith
     * @param drawableheight
     */
    public void setUseDefinedNavLeft(CharSequence title, Drawable drawable, int drawableWith, int drawableheight) {
        setUseDefinedNavLeftText(title);
        setUseDefinedNavLeftDrawable(drawable,drawableWith,drawableheight);
    }
    /**设置左侧标题和一个图标图标大小默认的
     * @param title
     * @param drawable
     */
    public void setUseDefinedNavLeft(CharSequence title, Drawable drawable)   {
        setUseDefinedNavLeftText(title);
        setUseDefinedNavLeftDrawable(drawable);
    }

    /**
     * 左侧文本大小
     * @param fontSize
     */
    public void setUseDefinedNavLeftTextSize(int fontSize){
        mTvNavLeft.setTextSize(fontSize);
    }

    /**
     * 左侧文本颜色
     * @param mColor
     */
    public void setUseDefinedNavLeftTextColor(int mColor){
        mTvNavLeft.setTextColor(mColor);
    }
    //---------------------自定义导航左侧end-------------

    //---------------------自定义导航右侧-------------

    /**
     * 设置自定义导航右侧文本
     * @param title
     */
    public void setUseDefinedNavRightText(CharSequence title) {
        if (mHeadNavUseDefinedRoot.getVisibility()!=View.VISIBLE){
            mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
        }
        if (mTvNavRight.getVisibility()!=View.VISIBLE){
            mTvNavRight.setVisibility(View.VISIBLE);
        }
        mTvNavRight.setText(title);
    }
    /**
     * 右侧图片
     * @param drawable
     */
    public void setUseDefinedNavRightDrawable(Drawable drawable){
        setUseDefinedNavRightDrawable(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
    /**
     * 右侧图片指定宽高
     * @param drawable
     * @param width
     * @param heigth
     */
    public void setUseDefinedNavRightDrawable(Drawable drawable,int width,int heigth){
        if (mHeadNavUseDefinedRoot.getVisibility()!=View.VISIBLE){
            mHeadNavUseDefinedRoot.setVisibility(View.VISIBLE);
        }
        if (mTvNavRight.getVisibility()!=View.VISIBLE){
            mTvNavRight.setVisibility(View.VISIBLE);
        }
        //先定义好宽度,在进行绘制,避免设置不生效
        mTvNavRight.setDrawableSize(width,heigth, AdjustDrawableTextView.InWhere.LEFTONTEXT);
        //绘制
        mTvNavRight.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    /**设置右侧标题和一个图标,并指定图标大小
     * @param title
     * @param drawable
     * @param drawableWith
     * @param drawableheight
     */
    public void setUseDefinedNavRight(CharSequence title, Drawable drawable, int drawableWith, int drawableheight) {
        setUseDefinedNavRightText(title);
        setUseDefinedNavRightDrawable(drawable,drawableWith,drawableheight);
    }
    /**设置右侧标题和一个图标图标大小默认的
     * @param title
     * @param drawable
     */
    public void setUseDefinedNavRight(CharSequence title, Drawable drawable)   {
        setUseDefinedNavRightText(title);
        setUseDefinedNavRightDrawable(drawable);
    }
    /**
     * 右侧文本大小
     * @param fontSize
     */
    public void setUseDefinedNavRightTextSize(int fontSize){
        mTvNavRight.setTextSize(fontSize);
    }
    /**
     * 右侧文本颜色
     * @param Color
     */
    public void setUseDefinedNavRightTextColor(int Color){
        mTvNavRight.setTextColor(Color);
    }
    //---------------------自定义导航右侧end-------------
    /**
     * 自定义导航控件的距离父view位置
     * @param whoView
     * @param letf
     * @param top
     * @param right
     * @param bottom
     */
    public void setUserDefinedNavMargin(WhichView whoView, int letf, int top, int right, int bottom ){
        RelativeLayout.LayoutParams re = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        re.setMargins(letf,top,right,bottom);
        if (whoView== WhichView.LEFTVIEW){
            mTvNavLeft.setLayoutParams(re);
        }else  if (whoView== WhichView.TITLEVIEW){
            mTvNavTitle.setLayoutParams(re);
        }else  if (whoView== WhichView.RIGHTVIEW){
            mTvNavRight.setLayoutParams(re);
        }
    }
    /**
     * 自定义导航控件的距离父view位置
     * @param whoView
     * @param letf
     * @param top
     * @param right
     * @param bottom
     */
    public void setUserDefinedNavPadding(WhichView whoView, int letf, int top, int right, int bottom ){
        if (whoView== WhichView.LEFTVIEW){
            mTvNavLeft.setPadding(letf,top,right,bottom);
        }else  if (whoView== WhichView.TITLEVIEW){
            mTvNavTitle.setPadding(letf,top,right,bottom);
        }else  if (whoView== WhichView.RIGHTVIEW){
            mTvNavRight.setPadding(letf,top,right,bottom);
        }
    }
    //-----------------------------顶部导航end-------------------------------------
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new UnsupportedOperationException("ImmersedBaseActivity:setContentView(view,params)该方法重写后被禁用");
    }
    /**
     * 将传入的LayoutID转换为View并添加到需要包裹view中
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        //转换为View
        mContextView = View.inflate(this, layoutResID, null);
        //添加到最外层的需要包裹的view
        setContentView(mContextView);
    }
    /**
     * 将View添加到自己的 最外层的包裹中
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
            createRootView();
            mContentViewlayout.addView(view);
            super.setContentView(rootViews);

    }
    /**
     * 加载外层
     *
     * @return
     */
   private void createRootView() {
       rootViews = View.inflate(this, R.layout.base_root_immersed, null);
       //toolbar
       mToolbar  = rootViews.findViewById(R.id.toolbar);
       //自定义
       mHeadNavUseDefinedRoot = rootViews.findViewById(R.id.head_nav_use_defined_root);
       mTvNavLeft =  rootViews. findViewById(R.id.tv_nav_left);
       mTvNavLeft.setOnClickListener(this);
       mTvNavTitle = rootViews.findViewById(R.id.tv_nav_title);
       mTvNavTitle.setOnClickListener(this);
       mTvNavRight = rootViews.findViewById(R.id.tv_nav_right);
       lineView = rootViews.findViewById(R.id.v_line);
       mTvNavRight.setOnClickListener(this);
       mContentViewlayout = rootViews.findViewById(R.id.content_view_layout);
   }
}
