package com.suxuantech.erpsys.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.suxuantech.erpsys.R;
import java.util.ArrayList;
import java.util.List;
/**
 * 日期：16/6/22 14:15
 * <p/>
 * 描述：StepsViewIndicator 指示器
 */
public class HorizontalStepsViewIndicator extends View
{

    //定义默认的高度   definition default height
    private int defaultStepIndicatorNum = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
    private float mCompletedLineHeight;//完成线的高度     definition completed line height
    private float mCircleRadius;//圆的半径  definition circle radius
    private Drawable mCompleteIcon;//完成的默认图片    definition default completed icon
    private Drawable mAttentionIcon;//正在进行的默认图片     definition default underway icon
    private Drawable mDefaultIcon;//默认的背景图  definition default unCompleted icon
    private float mCenterY;//该view的Y轴中间位置     definition view centerY position
    private float mLeftY;//左上方的Y位置  definition rectangle LeftY position
    private float mRightY;//右下方的位置  definition rectangle RightY position

    private int mStepNum = 0;//当前有几部流程    there are currently few step
    private float mLinePadding;//两条连线之间的间距  definition the spacing between the two circles

    private List<Float> mCircleCenterPointPositionList;//定义所有圆的圆心点位置的集合 definition all of circles center point list
    private Paint mUnCompletedPaint;//未完成Paint  definition mUnCompletedPaint
    private Paint mCompletedPaint;//完成paint      definition mCompletedPaint
    private int mUnCompletedLineColor = Color.parseColor("#A3E0D9");//定义默认未完成线的颜色  definition mUnCompletedLineColor
    private int mCompletedLineColor = Color.WHITE;//定义默认完成线的颜色      definition mCompletedLineColor
    private PathEffect mEffects;
    private int mComplectingPosition=-1;//正在进行position   underway position
    private Path mPath;

    private OnDrawIndicatorListener mOnDrawListener;

    /**
     * 设置监听
     *
     * @param onDrawListener
     */
    public void setOnDrawListener(OnDrawIndicatorListener onDrawListener)
    {
        mOnDrawListener = onDrawListener;
    }

    /**
     * get圆的半径  get circle radius
     *
     * @return
     */
    public float getCircleRadius()
    {
        return mCircleRadius;
    }
    ArrayList<Integer>  steps =new ArrayList<>();//跳跃性，完成
    public void setFinishStep(ArrayList<Integer> steps)
    {
    this.steps=steps;
        invalidate();

    }    public HorizontalStepsViewIndicator(Context context)
    {
        this(context, null);
    }

    public HorizontalStepsViewIndicator(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HorizontalStepsViewIndicator(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }
boolean imaginaryLine;
    /**
     * 未完成的线是否使用虚线
     * @param imaginaryLine
     * @return
     */
public void imaginaryLine(boolean imaginaryLine){
    this.imaginaryLine=imaginaryLine;
}

    /**
     * init
     */
    private void init()
    {
        mPath = new Path();
        mEffects = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);

        mCircleCenterPointPositionList = new ArrayList<>();//初始化

        mUnCompletedPaint = new Paint();
        mUnCompletedPaint.setAntiAlias(true);
        mUnCompletedPaint.setColor(mUnCompletedLineColor);
        mUnCompletedPaint.setStyle(Paint.Style.STROKE);
        mUnCompletedPaint.setStrokeWidth(2);

        mCompletedPaint = new Paint();
        mCompletedPaint.setAntiAlias(true);
        mCompletedPaint.setColor(mCompletedLineColor);
        mCompletedPaint.setStyle(Paint.Style.STROKE);
        mCompletedPaint.setStrokeWidth(2);
        mCompletedPaint.setStyle(Paint.Style.FILL);

        //已经完成线的宽高 set mCompletedLineHeight
        mCompletedLineHeight = 0.05f * defaultStepIndicatorNum;
        //圆的半径  set mCircleRadius
        mCircleRadius = 0.28f * defaultStepIndicatorNum;
        //线与线之间的间距    set mLinePadding
        mLinePadding = 0.85f * defaultStepIndicatorNum;

        mCompleteIcon = ContextCompat.getDrawable(getContext(), R.drawable.complted);//已经完成的icon
        mAttentionIcon = ContextCompat.getDrawable(getContext(), R.drawable.attention);//正在进行的icon
        mDefaultIcon = ContextCompat.getDrawable(getContext(), R.drawable.default_icon);//未完成的icon
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //     MeasureSpec.AT_MOST填充父窗体
//        MeasureSpec.EXACTLY 精确到
//        MeasureSpec.UNSPECIFIED
        int width = defaultStepIndicatorNum * 2;
        //未指明的
        if(MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec))
        {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        //防止就一步，除数为0，结果是无穷大
        if(mStepNum==1){
            mLinePadding=0;
        }else {
            if (!fixPointPadding){
                //宽    -  预留区防止字体过大显示不全-圆*个数     /   线数
                mLinePadding  =(width-mCircleRadius-mCircleRadius*2*mStepNum)/(mStepNum-1);
            }
        }
        int height = defaultStepIndicatorNum;
        if(MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(heightMeasureSpec))
        {
            height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width, height);
    }
    int reservedLeft;
    public void setReservedPaddingLeft(int reservedLeft){
        this.reservedLeft=Math.abs(reservedLeft);
    }
    int reservedRigth;
    public void setReservedPaddingRight(int reservedRigth){
        this.reservedRigth=reservedRigth;;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        if(mStepNum==1){
            mLinePadding=0;
        }else {
            //宽    -  预留区防止字体过大显示不全-圆*个数     /   线数
            if (!fixPointPadding) {
                mLinePadding = (w - reservedRigth - reservedLeft - mCircleRadius * 2 * mStepNum) / (mStepNum - 1);
            }
        }
        super.onSizeChanged(w, h, oldw, oldh);
        //获取中间的高度,目的是为了让该view绘制的线和圆在该view垂直居中   get view centerY，keep current stepview center vertical
        mCenterY = 0.5f * getHeight();
        //获取左上方Y的位置，获取该点的意义是为了方便画矩形左上的Y位置
        mLeftY = mCenterY - (mCompletedLineHeight / 2);
        //获取右下方Y的位置，获取该点的意义是为了方便画矩形右下的Y位置
        mRightY = mCenterY + mCompletedLineHeight / 2;
        for(int i = 0; i < mStepNum; i++)
        {
            float paddingLeft;
            //先计算全部最左边的padding值（getWidth()-（圆形直径+两圆之间距离）*2）
            if (mStepNum==1){//就一个步骤就在中间吧
                paddingLeft=w/2-mCircleRadius;
            }else {
             if (i==mStepNum-1){
                 paddingLeft =reservedLeft+reservedRigth;//这里有一点预留区域是左边的
             }else {
                 paddingLeft =reservedLeft;
             }
            }

            //add to list                           0   +圆半径      半径        +其他圆大小            +其他线
            mCircleCenterPointPositionList.add(paddingLeft + mCircleRadius + i * mCircleRadius * 2 + i * mLinePadding);
        }

        /**
         * set listener
         */
        if(mOnDrawListener!=null)
        {
            mOnDrawListener.ondrawIndicator();
        }
    }

    public void setmCircleRadius(float mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }
    boolean fixPointPadding;
    /**
     * 使用固定的距离（线的长度固定的）
     * @param fixPointPadding
     * @return
     */
    public void fixPointPadding(boolean fixPointPadding){
        this.fixPointPadding=fixPointPadding;
    }
    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mOnDrawListener!=null)
        {
            mOnDrawListener.ondrawIndicator();
        }
        mUnCompletedPaint.setColor(mUnCompletedLineColor);
        mCompletedPaint.setColor(mCompletedLineColor);

        //-----------------------画线-------draw line-----------------------------------------------
        for(int i = 0; i < mCircleCenterPointPositionList.size() - 1; i++)
        {
            //前一个ComplectedXPosition
            final float preComplectedXPosition = mCircleCenterPointPositionList.get(i);
            //后一个ComplectedXPosition
            final float afterComplectedXPosition = mCircleCenterPointPositionList.get(i + 1);
            // TODO: 2017/11/30 0030 这里要绘制连续性的
            if (steps==null||steps.size()<=0){
            if(i < mComplectingPosition)//判断在完成之前的所有点(已完成的)
            {
                //判断在完成之前的所有点，画完成的线，这里是矩形,很细的矩形，类似线，为了做区分，好看些

                canvas.drawRect(preComplectedXPosition + mCircleRadius - 10, mLeftY, afterComplectedXPosition - mCircleRadius + 10, mRightY, mCompletedPaint);
            } else//未完成
            {
                //虚线效果
                if (imaginaryLine){
                    mUnCompletedPaint.setPathEffect(mEffects);
                }
                mPath.moveTo(preComplectedXPosition + mCircleRadius, mCenterY);
                mPath.lineTo(afterComplectedXPosition - mCircleRadius, mCenterY);
                canvas.drawPath(mPath, mUnCompletedPaint);
            }
            }else {       // TODO: 2017/11/30 0030 这里要绘制跳跃性的
                //遍历所有的已完成 的
                for (int st=0;st<steps.size();st++){
                        if ((steps.get(st)-1)==i){//获取的数据
                            //判断在完成之前的所有点，画完成的线，这里是矩形,很细的矩形，类似线，为了做区分，好看些
                            canvas.drawRect(preComplectedXPosition + mCircleRadius - 10, mLeftY, afterComplectedXPosition - mCircleRadius + 10, mRightY, mCompletedPaint);
                            break;
                        }else {
                            mPath.moveTo(preComplectedXPosition + mCircleRadius, mCenterY);
                            mPath.lineTo(afterComplectedXPosition - mCircleRadius, mCenterY);
                            canvas.drawPath(mPath, mUnCompletedPaint);
                        }
              }
            }
                
        }
        //-----------------------画线-------draw line-----------------------------------------------
        //-----------------------画图标-----draw icon-----------------------------------------------
        for(int i = 0; i < mCircleCenterPointPositionList.size(); i++)
        {
            final float currentComplectedXPosition = mCircleCenterPointPositionList.get(i);
            Rect rect = new Rect((int) (currentComplectedXPosition - mCircleRadius), (int) (mCenterY - mCircleRadius), (int) (currentComplectedXPosition + mCircleRadius), (int) (mCenterY + mCircleRadius));
            // TODO: 2017/11/30 0030 连续性 
            if (steps==null||steps.size()<=0){
            if(i < mComplectingPosition)//已完成
            {
                mCompleteIcon.setBounds(rect);
                mCompleteIcon.draw(canvas);
            } else if(i == mComplectingPosition && mCircleCenterPointPositionList.size() != 1)//当前的
            {
                mCompletedPaint.setColor(Color.WHITE);
                canvas.drawCircle(currentComplectedXPosition, mCenterY, mCircleRadius * 1.1f, mCompletedPaint);
                mAttentionIcon.setBounds(rect);
                mAttentionIcon.draw(canvas);
            } else//未完成的
            {
                mDefaultIcon.setBounds(rect);
                mDefaultIcon.draw(canvas);
            }
            }else{// TODO: 2017/11/30 0030 跳跃性
                //查找当前是否在数组中，如果不在返回-1，存在则就是大于等于0的值
                if (steps.indexOf(i)<0){
                    mDefaultIcon.setBounds(rect);
                    mDefaultIcon.draw(canvas);
                }else {//存在
                    mCompleteIcon.setBounds(rect);
                    mCompleteIcon.draw(canvas);
                }
            }
        }
        //-----------------------画图标-----draw icon-----------------------------------------------
    }

    /**
     * 得到所有圆点所在的位置
     *
     * @return
     */
    public List<Float> getCircleCenterPointPositionList()
    {
        return mCircleCenterPointPositionList;
    }

    /**
     * 设置流程步数
     *
     * @param stepNum 流程步数
     */
    public void setStepNum(int stepNum)
    {
        this.mStepNum = stepNum;
        invalidate();
    }

    /**
     * 设置连续完成的步数
     * @param complectingPosition
     */
    public void setComplectingPosition(int complectingPosition)
    {
        this.mComplectingPosition = complectingPosition;
        invalidate();
    }

    /**
     * 设置未完成线的颜色
     *
     * @param unCompletedLineColor
     */
    public void setUnCompletedLineColor(int unCompletedLineColor)
    {
        this.mUnCompletedLineColor = unCompletedLineColor;
    }

    /**
     * 设置已完成线的颜色
     *
     * @param completedLineColor
     */
    public void setCompletedLineColor(int completedLineColor)
    {
        this.mCompletedLineColor = completedLineColor;
    }

    /**
     * 设置默认图片
     *
     * @param defaultIcon
     */
    public void setDefaultIcon(Drawable defaultIcon)
    {
        this.mDefaultIcon = defaultIcon;
    }

    /**
     * 设置已完成图片
     *
     * @param completeIcon
     */
    public void setCompleteIcon(Drawable completeIcon)
    {
        this.mCompleteIcon = completeIcon;
    }

    /**
     * 设置正在进行中的图片
     *
     * @param attentionIcon
     */
    public void setAttentionIcon(Drawable attentionIcon)
    {
        this.mAttentionIcon = attentionIcon;
    }


    /**
     * 设置对view监听
     */
    public interface OnDrawIndicatorListener
    {
        void ondrawIndicator();
    }
}
