package com.suxuantech.erpsys.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期：16/6/22 15:47
 * <p>
 * 描述：StepView
 */
public class HorizontalStepView extends LinearLayout implements HorizontalStepsViewIndicator.OnDrawIndicatorListener
{
    private RelativeLayout mTextContainer;
    private HorizontalStepsViewIndicator mStepsViewIndicator;
    private List<String> mTexts;
    private int mComplectingPosition=-1;
    private int mUnComplectedTextColor = Color.parseColor("#F8F8F8");//定义默认未完成文字的颜色;
    private int mComplectedTextColor =   ContextCompat.getColor(getContext(), android.R.color.white);//定义默认完成文字的颜色;

    public HorizontalStepView(Context context)
    {
        this(context, null);
    }

    public HorizontalStepView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HorizontalStepView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }
   public interface ItemClick{
       void onItemClick(int position, boolean isfinish, String text);
    }
    ItemClick   mItemClick ;
    public void setOnItemClickList(ItemClick mItemClick){
       this.mItemClick=mItemClick;
    }
    private void init()
    {
         mStepsViewIndicator = new HorizontalStepsViewIndicator(getContext());
        mStepsViewIndicator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mStepsViewIndicator.setOnDrawListener(this);
        mTextContainer = new RelativeLayout(getContext());
        mTextContainer.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        mTextContainer.removeAllViews();
        setOrientation(VERTICAL);
        addView(mStepsViewIndicator);
        addView(mTextContainer);
/*       View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_horizontal_stepsview, this);
       mStepsViewIndicator = (HorizontalStepsViewIndicator) rootView.findViewById(R.id.steps_indicator);
       mStepsViewIndicator.setOnDrawListener(this);
       mTextContainer = (RelativeLayout) rootView.findViewById(R.id.rl_text_container);
       mTextContainer.removeAllViews();*/
    }
    public HorizontalStepView setTextMarginTop(int top){
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,top,0,0);
        mTextContainer.setLayoutParams(layoutParams);
        return this;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //点前点击位置
        float y = ev.getY();
        //显示文本textView的容器在Y轴的位置
        float y1 = mTextContainer.getY();
        //判断点击的y轴位置，如果当前点击的，大于textView文本容器在Y轴位置，那么点击的就是Textview
        //这里就不需要拦截，让Textview处理就好
        if (y >=y1){
            return super.onInterceptTouchEvent(ev);
        }
        //小于文本容器，那么就是步骤VIew的，这里拦截，给下面的onTouchEvent做准备使用
        return true;
    }

    /**
     * 未完成的线是否使用虚线
     * @param imaginaryLine
     * @return
     */
    public HorizontalStepView imaginaryLine(boolean imaginaryLine){
        mStepsViewIndicator.imaginaryLine(imaginaryLine);
        return this;
        }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //抬起手的时候
        if (ev.getAction()==MotionEvent.ACTION_UP&&mItemClick != null) {
                float x = ev.getX();
                //获得每个步骤的X轴坐标点
                List<Float> complectedXPosition = mStepsViewIndicator.getCircleCenterPointPositionList();
                //遍历每个坐标点
                for (int i = 0; i < complectedXPosition.size(); i++) {
                    //一个坐标点
                    Float aFloat = complectedXPosition.get(i);
                    //如果这个坐标点x轴浮动左右30，并且当前点击的x轴在这个区间，那么肯定点击的就是这个点
                    if (aFloat - 30 < x && aFloat + 30 > x) {
                        //回调不为空，进行回调
                        callback(i);
                        break;
                    }
                }
                return true;
        }
        return true;
    }

    /**
     * 计算当前点击的步骤是否完成，并回调接口
     * @param position 坐标
     */
    private void callback(int position) {
        if (completeSteps.indexOf(position)<0){//不存在
            if (mComplectingPosition<position){
                mItemClick.onItemClick(position,false, mTexts.get(position));
            }else {
                mItemClick.onItemClick(position,true, mTexts.get(position));
            }
        }else {//存在
            mItemClick.onItemClick(position,true, mTexts.get(position));
        }
    }

    /**
     * 设置显示的文字
     *
     * @param texts
     * @return
     */
    public HorizontalStepView setStepViewTexts(List<String> texts)
    {
        mTexts = texts;
        mStepsViewIndicator.setReservedPaddingRight((int) (texts.get(texts.size()-1).length()*textSize-mStepsViewIndicator.getCircleRadius()));
        mStepsViewIndicator.setReservedPaddingLeft((int) (texts.get(0).length()*textSize-mStepsViewIndicator.getCircleRadius()));
        mStepsViewIndicator.setStepNum(mTexts.size());
        return this;
    }

    /**
     * 设置正在进行的position
     *
     * @param complectingPosition
     * @return
     */
    public HorizontalStepView setStepsViewIndicatorComplectingPosition(int complectingPosition)
    {
        if (completeSteps!=null){
            completeSteps.clear();
        }
        mComplectingPosition = complectingPosition;
        mStepsViewIndicator.setComplectingPosition(complectingPosition);
        return this;
    }
    ArrayList<Integer>  completeSteps=new ArrayList<>();

    /**
     * 设置都哪几点完成了
     * @param step
     */
    public void setComplete(int... step){
        if (completeSteps!=null){
            completeSteps.clear();
        }else {
            completeSteps=new ArrayList<>();
        }
        for (int i =0;i<step.length;i++){
            completeSteps.add(step[i]);
        }
        mComplectingPosition=-1;
        mStepsViewIndicator.setFinishStep(completeSteps);

    }
    /**
     * 设置都哪几点完成了
     * @param steps
     */
    public void setComplete(ArrayList<Integer> steps){
        steps.clear();
        completeSteps=steps;
        mComplectingPosition=-1;
        mStepsViewIndicator.setFinishStep(steps);
    }
    /**
     * 设置未完成文字的颜色
     *
     * @param unComplectedTextColor
     * @return
     */
    public HorizontalStepView setStepViewUnComplectedTextColor(int unComplectedTextColor)
    {
        mUnComplectedTextColor = unComplectedTextColor;
        return this;
    }
    /**
     * 设置完成文字的颜色
     *
     * @param complectedTextColor
     * @return
     */
    public HorizontalStepView setStepViewComplectedTextColor(int complectedTextColor)
    {
        this.mComplectedTextColor = complectedTextColor;
        return this;
    }
    /**
     * 设置StepsViewIndicator未完成线的颜色
     *
     * @param unCompletedLineColor
     * @return
     */
    public HorizontalStepView setStepsViewIndicatorUnCompletedLineColor(int unCompletedLineColor)
    {
        mStepsViewIndicator.setUnCompletedLineColor(unCompletedLineColor);
        return this;
    }
    /**
     * 设置StepsViewIndicator完成线的颜色
     *
     * @param completedLineColor
     * @return
     */
    public HorizontalStepView setStepsViewIndicatorCompletedLineColor(int completedLineColor)
    {
        mStepsViewIndicator.setCompletedLineColor(completedLineColor);
        return this;
    }
    public HorizontalStepView setmCircleRadius(float f){
        mStepsViewIndicator.setReservedPaddingRight((int) (mTexts.get(mTexts.size()-1).length()*textSize-mStepsViewIndicator.getCircleRadius()));
        mStepsViewIndicator.setReservedPaddingLeft(((int) (mTexts.get(0).length()*textSize-mStepsViewIndicator.getCircleRadius())));
        mStepsViewIndicator.setmCircleRadius(f);
        return this;
    }

    /**
     * 设置StepsViewIndicator默认图片
     *
     * @param defaultIcon
     */
    public HorizontalStepView setStepsViewIndicatorDefaultIcon(Drawable defaultIcon)
    {
        mStepsViewIndicator.setDefaultIcon(defaultIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator已完成图片
     *
     * @param completeIcon
     */
    public HorizontalStepView setStepsViewIndicatorCompleteIcon(Drawable completeIcon)
    {
        mStepsViewIndicator.setCompleteIcon(completeIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator正在进行中的图片
     *
     * @param attentionIcon
     */
    public HorizontalStepView setStepsViewIndicatorAttentionIcon(Drawable attentionIcon)
    {
        mStepsViewIndicator.setAttentionIcon(attentionIcon);
        return this;
    }

    boolean finshTextIsBold;

    public void setFinshTextIsBold(boolean finshTextIsBold) {
        this.finshTextIsBold = finshTextIsBold;
    }
    int textSize =10;
    public void setTextSize(int size){
        textSize=size;
    }

    /**
     * 绘制文字内容
     */
    @Override
    public void ondrawIndicator()
    {
        List<Float> complectedXPosition = mStepsViewIndicator.getCircleCenterPointPositionList();
        if(mTexts != null)
        {
            for(int i = 0; i < mTexts.size(); i++)
            {
                TextView textView = new TextView(getContext());
                textView.setTextSize(textSize);
                    if (i==0){
                        textView.setX(complectedXPosition.get(i) - mStepsViewIndicator.getCircleRadius()  );//这里的-10是将文字进行调整居中，稍后再动态修改
                    }else if (i==mTexts.size()-1){
                        textView.setX(getWidth() - (mTexts.get(i).length()* textView.getTextSize()));
                    }else{
                        textView.setX(complectedXPosition.get(i)- (mTexts.get(i).length()* textView.getTextSize()/2) );//这里的-10是将文字进行调整居中，稍后再动态修改
                    }
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //如果将来想抽取出来让使用者绘制，改变某些东西可以把下面的东西抽象出来
                textView.setText(mTexts.get(i));
                if(i <= mComplectingPosition)
                {
                    if (finshTextIsBold){
                        textView.setTypeface(null, Typeface.BOLD);
                    }
                    textView.setTextColor(mComplectedTextColor);
                } else
                {
                    textView.setTextColor(mUnComplectedTextColor);
                }
                mTextContainer.addView(textView);
                //点击的监听
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mItemClick!=null){
                        for (int i=0;i<mTexts.size();i++){
                            if (mTexts.get(i).equals( ((TextView)view).getText())){
                                callback(i);
                                }
                                break;
                            }
                        }
                    }
                });
            }
        }
    }
}
