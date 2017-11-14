package com.suxuantech.erpsys.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

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
 * @author Created by 李站旗 on 2017/11/10 14:03 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 * Created by xiaxiao on 2017/9/13.
 * 用来解决文字和图片组合时造成的view层级过多的问题。
 * 比如上图标下文字，下图标上文字，尤其是在实现一组tab均匀平铺的效果时出现的大量view层级
 * 比如各app的底部栏，本类只要一层view既可。
 * 注意：必须设置drawable的宽高度。
 */

public class AdjustDrawableTextView extends AppCompatTextView {
    /**
     * 代码中设置drawable大小要确定是给那边的设置的
     */
    public  enum InWhere{
        LEFTONTEXT,TOPONTEXT,RIGHTONTEXT,BOTTOMONTEXT
    }
    int leftDrawableWidth=0;
    int leftDrawableHeight=0;
    int topDrawableWidth=0;
    int topDrawableHeight=0;
    int rightDrawableWidth=0;
    int rightDrawableHeight=0;
    int bottomDrawableWidth=0;
    int bottomDrawableHeight=0;
    Drawable left;
    Drawable top;
    Drawable right;
    Drawable bottom;
    public AdjustDrawableTextView(Context context) {
        this(context,null,0);
    }

    public AdjustDrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AdjustDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(context, attrs, defStyleAttr);
    }


    public void getAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AdjustDrawableTextView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.AdjustDrawableTextView_drawableWidth_left:
                    leftDrawableWidth = a.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.AdjustDrawableTextView_drawableHeight_left:
                    leftDrawableHeight = a.getDimensionPixelSize(attr, 0);
                    break;

                case R.styleable.AdjustDrawableTextView_drawableWidth_top:
                    topDrawableWidth = a.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.AdjustDrawableTextView_drawableHeight_top:
                    topDrawableHeight = a.getDimensionPixelSize(attr, 0);
                    break;

                case R.styleable.AdjustDrawableTextView_drawableWidth_right:
                    rightDrawableWidth = a.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.AdjustDrawableTextView_drawableHeight_right:
                    rightDrawableHeight = a.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.AdjustDrawableTextView_drawableWidth_bottom:
                    bottomDrawableWidth = a.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.AdjustDrawableTextView_drawableHeight_bottom:
                    bottomDrawableHeight = a.getDimensionPixelSize(attr, 0);
                    break;
//                case R.styleable.XXDrawableTextView_testnumber:
//                    System.out.println("啦啦啦啦啦啦啦TextView2_testnumber:"+a.getDimensionPixelSize(attr,10));
//                    break;
//                case R.styleable.XXDrawableTextView_teststring:
//                    System.out.println("啦啦啦啦啦啦啦TextView2_teststring:"+a.getString(attr));
            }
        }
        a.recycle();
        /*
        * setCompoundDrawablesWithIntrinsicBounds方法会首先在父类的构造方法中执行，
        * 彼时执行时drawable的大小还都没有开始获取，都是0,
        * 这里获取完自定义的宽高属性后再次调用这个方法，插入drawable的大小
        * */
        setCompoundDrawablesWithIntrinsicBounds( left,top,right,bottom);

    }


    /**
     * Sets the Drawables (if any) to appear to the left of, above, to the
     * right of, and below the text. Use {@code null} if you do not want a
     * Drawable there. The Drawables' bounds will be set to their intrinsic
     * bounds.
     * <p>
     * Calling this method will overwrite any Drawables previously set using
     * {@link #setCompoundDrawablesRelative} or related methods.
     * 这里重写这个方法，来设置上下左右的drawable的大小
     *
     * @attr ref android.R.styleable#TextView_drawableLeft
     * @attr ref android.R.styleable#TextView_drawableTop
     * @attr ref android.R.styleable#TextView_drawableRight
     * @attr ref android.R.styleable#TextView_drawableBottom
     */
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left,  @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        if (left != null) {
            if (leftDrawableWidth<=0||leftDrawableHeight<=0){//如果设置的值小于等于0则是原来的图片的高度
                leftDrawableHeight= left.getIntrinsicHeight();
                leftDrawableWidth= left.getIntrinsicWidth();
            }
            left.setBounds(0, 0, leftDrawableWidth,leftDrawableHeight);
        }
        if (right != null) {
            if (rightDrawableWidth<=0||rightDrawableHeight<=0){//如果设置的值小于等于0则是原来的图片的高度
                rightDrawableHeight= right.getIntrinsicHeight();
                rightDrawableWidth= right.getIntrinsicWidth();
            }
            right.setBounds(0, 0, rightDrawableWidth,rightDrawableHeight);
        }
        if (top != null) {
            if (topDrawableWidth<=0||topDrawableHeight<=0){//如果设置的值小于等于0则是原来的图片的高度
                topDrawableHeight= top.getIntrinsicHeight();
                topDrawableWidth= top.getIntrinsicWidth();
            }
            top.setBounds(0, 0, topDrawableWidth,topDrawableHeight);
        }
        if (bottom != null) {
            if (bottomDrawableWidth<=0||bottomDrawableHeight<=0){//如果设置的值小于等于0则是原来的图片的高度
                bottomDrawableHeight= bottom.getIntrinsicHeight();
                bottomDrawableWidth= bottom.getIntrinsicWidth();
            }
            bottom.setBounds(0, 0, bottomDrawableWidth,bottomDrawableHeight);
        }

        setCompoundDrawables(left, top, right, bottom);
    }

    /*
    * 代码中动态设置drawable的宽高度
    * */
    public void setDrawableSize(int width, int height,AdjustDrawableTextView.InWhere whereDrawable) {
        if (whereDrawable==InWhere.LEFTONTEXT) {
            leftDrawableWidth = width;
            leftDrawableHeight = height;
        }
        if (whereDrawable==InWhere.TOPONTEXT) {
            topDrawableWidth = width;
            topDrawableHeight = height;
        }
        if (whereDrawable==InWhere.RIGHTONTEXT) {
            rightDrawableWidth = width;
            rightDrawableHeight = height;
        }
        if (whereDrawable==InWhere.BOTTOMONTEXT) {
            bottomDrawableWidth = width;
            bottomDrawableHeight = height;
        }
        setCompoundDrawablesWithIntrinsicBounds(left,top,right,bottom);
        setCompoundDrawablePadding(20);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the background for this view
        super.onDraw(canvas);
       /*
       测试圆角的
       Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);

        super.onDraw(canvas2);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        //16种状态
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setXfermode(null);

        int radius=100;
        Path path = new Path();
        path.moveTo(0, radius);
        path.lineTo(0, 0);
        path.lineTo(radius, 0);
        //arcTo的第二个参数是以多少度为开始点，第三个参数-90度表示逆时针画弧，正数表示顺时针
        path.arcTo(new RectF(0, 0, radius * 2, radius * 2), -90, -90);
        path.close();
        canvas2.drawPath(path, mPaint);

        canvas.drawBitmap(bitmap, 0, 0, mPaint2);
        bitmap.recycle();*/

/*
        final int compoundPaddingLeft = getCompoundPaddingLeft();
        final int compoundPaddingTop = getCompoundPaddingTop();
        final int compoundPaddingRight = getCompoundPaddingRight();
        final int compoundPaddingBottom = getCompoundPaddingBottom();
        final int scrollX = getScrollX();
        final int scrollY = getScrollY();
        final int right = getRight();
        final int left = getLeft();
        final int bottom = getBottom();
        final int top = getTop();
        final int offset =0;
        final int leftOffset = 0;
        final int rightOffset =0;


        *//*
        * 0-1-2-3
        * left-top-right-bottom
        * *//*
        Drawable[] drawables = getCompoundDrawables();


            *//*
             * Compound, not extended, because the icon is not clipped
             * if the text height is smaller.
             *//*

            int vspace = bottom - top - compoundPaddingBottom - compoundPaddingTop;
            int hspace = right - left - compoundPaddingRight - compoundPaddingLeft;

            // IMPORTANT: The coordinates computed are also used in invalidateDrawable()
            // Make sure to update invalidateDrawable() when changing this code.
            if (drawables[0] != null) {
                canvas.save();
                canvas.translate(scrollX + getPaddingLeft() + leftOffset,
                        scrollY + compoundPaddingTop +
                                (vspace - leftDrawableHeight) / 2);
                drawables[0].draw(canvas);
                canvas.restore();
            }

            // IMPORTANT: The coordinates computed are also used in invalidateDrawable()
            // Make sure to update invalidateDrawable() when changing this code.
            if (dr.mShowing[Drawables.RIGHT] != null) {
                canvas.save();
                canvas.translate(scrollX + right - left - mPaddingRight
                                - dr.mDrawableSizeRight - rightOffset,
                        scrollY + compoundPaddingTop + (vspace - dr.mDrawableHeightRight) / 2);
                dr.mShowing[Drawables.RIGHT].draw(canvas);
                canvas.restore();
            }

            // IMPORTANT: The coordinates computed are also used in invalidateDrawable()
            // Make sure to update invalidateDrawable() when changing this code.
            if (dr.mShowing[Drawables.TOP] != null) {
                canvas.save();
                canvas.translate(scrollX + compoundPaddingLeft +
                        (hspace - dr.mDrawableWidthTop) / 2, scrollY + mPaddingTop);
                dr.mShowing[Drawables.TOP].draw(canvas);
                canvas.restore();
            }

            // IMPORTANT: The coordinates computed are also used in invalidateDrawable()
            // Make sure to update invalidateDrawable() when changing this code.
            if (dr.mShowing[Drawables.BOTTOM] != null) {
                canvas.save();
                canvas.translate(scrollX + compoundPaddingLeft +
                                (hspace - dr.mDrawableWidthBottom) / 2,
                        scrollY + bottom - top - mPaddingBottom - dr.mDrawableSizeBottom);
                dr.mShowing[Drawables.BOTTOM].draw(canvas);
                canvas.restore();
            }




        canvas.restore();*/
    }
}
