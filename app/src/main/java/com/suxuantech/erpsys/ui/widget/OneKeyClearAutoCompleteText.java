package com.suxuantech.erpsys.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.suxuantech.erpsys.R;

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
 * @author Created by 李站旗 on 2017/12/11 0011 12:21 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description:  一键清空的自动完成EdittextView
 */
public class OneKeyClearAutoCompleteText extends android.support.v7.widget.AppCompatAutoCompleteTextView implements View.OnFocusChangeListener,TextWatcher {
    public OneKeyClearAutoCompleteText(Context context) {
        this(context, null);
    }

    public OneKeyClearAutoCompleteText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }
    private Drawable mClearDrawable,// 一键删除的按钮
            mLeftDrawable;//左边的点击
    private int colorAccent;//获得主题的颜色
    public OneKeyClearAutoCompleteText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme()
                .obtainStyledAttributes(new int[] {android.R.attr.colorAccent});
        colorAccent = array.getColor(0, getResources().getColor(R.color.textHint_99));
        array.recycle();
        initClearDrawable(context);
    }
    @SuppressLint("NewApi")
    private void initClearDrawable(Context context) {
        mLeftDrawable = getCompoundDrawables()[0];
        mClearDrawable = getCompoundDrawables()[2];// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        if (mClearDrawable == null) {
            mClearDrawable =  ContextCompat.getDrawable(context,R.drawable.icon_clear);
        }
        DrawableCompat.setTint(mClearDrawable, colorAccent);//设置删除按钮的颜色和TextColor的颜色一致
        mClearDrawable.setBounds(0, 0, (int) getTextSize(), (int) getTextSize());//设置Drawable的宽高和TextSize的大小一致
        setClearIconVisible(true);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

  /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible 是否可见
     */
    private void setClearIconVisible(boolean visible) {
        if (visible){
            DrawableCompat.setTint(mLeftDrawable, colorAccent);//有字的时候颜色彩色
        }else {
            DrawableCompat.setTint(mLeftDrawable,getResources().getColor(R.color.textHint_99));
        }
        Drawable right = visible ? mClearDrawable : null;

        setCompoundDrawables(mLeftDrawable, getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mClearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            // 判断触摸点是否在水平范围内
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight()))
                    && (x < (getWidth() - getPaddingRight()));
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = mClearDrawable.getBounds();
            // 获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerHeight && isInnerWidth) {
                this.setText("");
//                Toast.makeText(getContext(), "一键清除", Toast.LENGTH_SHORT).show();//为了看清效果，测试
            }
        }
        if (mLeftDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            // 判断触摸点是否在水平范围内
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = mLeftDrawable.getBounds();
            boolean isInnerWidth= x>getPaddingLeft()&&(x< rect.width()+getCompoundDrawablePadding()+getPaddingLeft());
            // 获取图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
                boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerHeight && isInnerWidth) {
              if (leftDrawableClickListen!=null){
                  leftDrawableClickListen.onClickLeftDrawable();
                  return true;
              }
             //ToastUtils.show("左边");//为了看清效果，测试
            }
        }
        return super.onTouchEvent(event);
    }


    private boolean hasFocus;// 控件是否有焦点

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }
    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            setClearIconVisible(text.length() > 0);
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }
    LeftDrawableClickListen leftDrawableClickListen;
    public void setLeftDrawableClickListen( LeftDrawableClickListen leftDrawableClickListen){
        this.leftDrawableClickListen=leftDrawableClickListen;
    }
    public interface  LeftDrawableClickListen{
      void  onClickLeftDrawable();
    }
}