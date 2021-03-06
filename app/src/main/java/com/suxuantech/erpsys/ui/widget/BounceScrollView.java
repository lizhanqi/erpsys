package com.suxuantech.erpsys.ui.widget;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;

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
 * @author Created by 李站旗 on 2018/2/26 0026 17:30 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 阻尼效果的scrollview
 */
public class BounceScrollView extends ScrollView {
    private static final int LEN = 0xc8;
    private static final int DURATION = 500;
    private static final int MAX_DY = 500;
    private Scroller mScroller;
    TouchTool tool;
    int left, top;
    float startX, startY, currentX, currentY;
    int imageViewH;
    int rootW, rootH;
    ImageView imageView;
    boolean scrollerType;

    public BounceScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public BounceScrollView(Context context) {
        super(context);

    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {

        return 0;
    }

    private int[] li = new int[2];
    private int[] li2 = new int[2];
    private float lastLy;
    private boolean startIsTop = true;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        int action = event.getAction();
        if (!mScroller.isFinished()) {
            return super.onTouchEvent(event);
        }
        currentX = event.getX();
        currentY = event.getY();
        imageView.getLocationInWindow(li);
        getLocationOnScreen(li2);
        imageView.getTop();
        switch (action) {
            default:
                break;
            case MotionEvent.ACTION_DOWN:
                if (li[1] != li2[1]) {// 判断开始触摸时，imageview和窗口顶部对齐没
                    startIsTop = false;
                }
                left = imageView.getLeft();
                top = imageView.getBottom();
                rootW = getWidth();
                rootH = getHeight();
                imageViewH = imageView.getHeight();
                startX = currentX;
                startY = currentY;
                tool = new TouchTool(imageView.getLeft(), imageView.getBottom(), imageView.getLeft(),
                        imageView.getBottom() + LEN);
                break;
            case MotionEvent.ACTION_MOVE:
                if (startY<currentY) {
                    if (!startIsTop && li[1] == li2[1]) {
                        startY = currentY;
                        startIsTop = true;
                    }
                    if (imageView.isShown() && imageView.getTop() >= 0) {
                        if (tool != null) {
                            int t = tool.getScrollY(currentY - startY);
                            if (!scrollerType && currentY < lastLy && imageView.getHeight() > imageViewH) {
                                scrollTo(0, 0);
                                imageView.getLocationInWindow(li);
                                getLocationOnScreen(li2);
                                android.view.ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.height = t;
                                imageView.setLayoutParams(params);
                                if (imageView.getHeight() == imageViewH && li[1] == li2[1]) {
                                    scrollerType = true;
                                }
                                if (startIsTop && li[1] != li2[1]) {
                                    startIsTop = false;
                                }
                            }
                            if (t >= top && t <= imageView.getBottom() + LEN && li[1] == li2[1] && currentY > lastLy) {
                                android.view.ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.height = t;
                                imageView.setLayoutParams(params);
                            }
                        }
                        scrollerType = false;
                    }
                    lastLy = currentY;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (li[1] == li2[1]) {
                    scrollerType = true;
                    mScroller.startScroll(imageView.getLeft(), imageView.getBottom(), 0 - imageView.getLeft(),
                            imageViewH - imageView.getBottom(), DURATION);
                    invalidate();
                }
                startIsTop = true;
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            imageView.layout(0, 0, x + imageView.getWidth(), y);
            invalidate();
            if (!mScroller.isFinished() && scrollerType && y > MAX_DY) {
                android.view.ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = y;
                imageView.setLayoutParams(params);
            }
        }
    }
    View view;
    public   void setView(View view) {
        this.view = view;
    }

    public class TouchTool {

        private int startX, startY;

        public TouchTool(int startX, int startY, int endX, int endY) {
            super();
            this.startX = startX;
            this.startY = startY;
        }

        public int getScrollX(float dx) {
            int xx = (int) (startX + dx / 2.5F);
            return xx;
        }

        public int getScrollY(float dy) {
            int yy = (int) (startY + dy / 2.5F);
            return yy;
        }
    }
}