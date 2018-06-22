package com.suxuantech.erpsys.ui.widget;

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
 * @author Created by 李站旗 on 2017/11/23 0023 10:02 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 通用的Recycleview分割线
 * 可以设置偏移量，也就是相当于padding，目前仅测试ListView形式其他的没测试（有问题后续再改）
 */


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *这里其实只做了两件事,1.移动条目 2.绘制
 *  条目移动即getItemOffsets完成
 *  绘制则由onDraw完成而已
 *   但是多条目绘制就需要计算准确性了
 */
public class DefaultItemDecoration2 extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mDividerWidth;
    private int mDividerHeight;
    private List<Integer> mViewTypeList = new ArrayList<>();
    int leftOffsetX = 0;

    /**
     * @param color decoration line color.
     */
    public DefaultItemDecoration2(@ColorInt int color) {
        this(color, 2, 2, -1);
    }

    /**
     * @param color           line color.
     * @param dividerWidth    line width.
     * @param dividerHeight   line height.
     * @param excludeViewType don't need to draw the ViewType of the item of the split line.
     */
    public DefaultItemDecoration2(@ColorInt int color, int dividerWidth, int dividerHeight, int... excludeViewType) {
        mDivider = new ColorDrawable(color);
        mDividerWidth = dividerWidth;
        mDividerHeight = dividerHeight;
        for (int i : excludeViewType) {
            mViewTypeList.add(i);
        }
    }

    int offsetX;

    /**
     * 设置偏移量（这里设置的偏移量相当于padingleft和padingright，包括内容都会缩进）
     *
     * @param offsetX
     * @return
     */
    public DefaultItemDecoration2 offSetX(int offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    boolean hasHead;
    boolean hasFooter;

    /**
     * 不可以滥用,真有一个header的时候才能用
     *
     * @param hasHead
     */
    public void setHasHead(boolean hasHead) {
        this.hasHead = hasHead;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position < 0) {
            return;
        }
        if (mViewTypeList.contains(parent.getAdapter().getItemViewType(position))) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        int childCount = parent.getAdapter().getItemCount();

        if (hasHead) {
            position--;
            childCount--;
        }
        if (hasFooter) {
            childCount--;
        }
        int columnCount = getSpanCount(parent);
        boolean firstRaw = isFirstRaw(position, columnCount);
        boolean lastRaw = isLastRaw(position, columnCount, childCount);
        boolean firstColumn = isFirstColumn(position, columnCount);
        boolean lastColumn = isLastColumn(position, columnCount);
        if (columnCount == 1) {
            if (firstRaw) {
                outRect.set(0, 0, 0, mDividerHeight / 2);
                //设置偏移
                outRect.offset(offsetX, 0);
            } else if (lastRaw) {
                outRect.set(0, mDividerHeight / 2, 0, 0);
                outRect.offset(offsetX, 0);
            } else {
                outRect.set(0, mDividerHeight / 2, 0, mDividerHeight / 2);
                outRect.offset(offsetX, 0);
            }
        } else {
            if (firstRaw && firstColumn) {//第一行第一列
                if (topDrawable) {
                    outRect.set(0, mDividerHeight, mDividerHeight / 2, mDividerHeight / 2);
                } else {
                    outRect.set(0, 0, mDividerHeight / 2, mDividerHeight / 2);
                }
            } else if (firstRaw && lastColumn) {//第一行最后一列
                if (topDrawable) {
                    outRect.set(mDividerHeight / 2, mDividerHeight, 0, mDividerHeight / 2);
                } else {
                    outRect.set(mDividerHeight / 2, 0, 0, mDividerHeight / 2);
                }
            } else if (firstRaw) { //第一行其他列
                if (topDrawable) {
                    outRect.set(mDividerHeight / 2, mDividerHeight, mDividerHeight / 2, mDividerHeight / 2);
                } else {
                    outRect.set(mDividerHeight / 2, mDividerHeight, mDividerHeight / 2, mDividerHeight / 2);
                }
            } else if (lastRaw && firstColumn) { //最后一行第一列
                outRect.set(0, mDividerHeight / 2, mDividerHeight / 2, 0);
            } else if (lastRaw && lastColumn) { // 最后一行最后一列
                outRect.set(mDividerHeight / 2, mDividerHeight / 2, 0, 0);
            } else if (lastRaw) { // 最后一行其他情况
                outRect.set(mDividerHeight / 2, mDividerHeight / 2, mDividerHeight / 2, 0);
            } else if (firstColumn) { // 中间的第一列
                outRect.set(0, mDividerHeight / 2, mDividerHeight / 2, mDividerHeight / 2);
            } else if (lastColumn) { // 中间最后一列
                outRect.set(mDividerHeight / 2, mDividerHeight / 2, 0, mDividerHeight / 2);
            } else { //中间的中间
                outRect.set(mDividerHeight / 2, mDividerHeight / 2, mDividerHeight / 2, mDividerHeight / 2);
            }
        }
    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    private boolean isFirstRaw(int position, int columnCount) {
        return position < columnCount;
    }

    private boolean isLastRaw(int position, int columnCount, int childCount) {
        if (columnCount == 1) {
            return position + 1 == childCount;
        } else {
            int lastRawItemCount = childCount % columnCount;
            int rawCount = (childCount - lastRawItemCount) / columnCount + (lastRawItemCount > 0 ? 1 : 0);
            int rawPositionJudge = (position + 1) % columnCount;
            if (rawPositionJudge == 0) {
                int rawPosition = (position + 1) / columnCount;
                return rawCount == rawPosition;
            } else {
                int rawPosition = (position + 1 - rawPositionJudge) / columnCount + 1;
                return rawCount == rawPosition;
            }
        }
    }

    private boolean isFirstColumn(int position, int columnCount) {
        if (columnCount == 1)
            return true;
        return position % columnCount == 0;
    }

    private boolean isLastColumn(int position, int columnCount) {
        if (columnCount == 1)
            return true;
        return (position + 1) % columnCount == 0;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    /**
     * 设置X轴左边一栋多少
     *
     * @param leftOffsetX
     */
    public void setJustLeftOffsetX(int leftOffsetX) {
        this.leftOffsetX = leftOffsetX;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        c.save();
        int columnCount = getSpanCount(parent);
        //todo 这里有问题
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            boolean lastRaw = isLastRaw(i, columnCount, childCount);
            final View child = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(child);
            if (childPosition < 0) {
                continue;
            }
            if (mViewTypeList.contains(parent.getAdapter().getItemViewType(childPosition)) || lastRaw) {
                continue;
            }
            if (child instanceof SwipeMenuRecyclerView.LoadMoreView) {
                continue;
            }
            final int left = child.getLeft();
            final int top = child.getBottom();
            final int right = child.getRight();
            final int bottom = top + mDividerHeight;
            mDivider.setBounds(left + leftOffsetX, top, right, bottom);
            mDivider.draw(c);
        }
        c.restore();
    }

    boolean topDrawable = true;

    public void drawVertical(Canvas c, RecyclerView parent) {
        c.save();
        //当前显示了多少个
        int childCount = parent.getChildCount();
        //总个数
        int childCounts = parent.getAdapter().getItemCount();
        //每列多少个
        int columnCount = getSpanCount(parent);
        for (int i = 0; i <= childCount; i++) {
            //获取当前的view
            View child = parent.getChildAt(i);
            //当前view在适配器中的坐标
            int childPosition = parent.getChildAdapterPosition(child);
            //如果有头布局那么就需要把头布局考虑进去
            if (hasHead) {
                //当有头布局的时候这时候就需要吧下标上调一个就能正确了
                childPosition++;
            }
            //是否是最后一列
            boolean lastColumn = isLastColumn(childPosition, columnCount);
            if (child instanceof SwipeMenuRecyclerView.LoadMoreView||childPosition < 0||mViewTypeList.contains(parent.getAdapter().getItemViewType(childPosition)) || lastColumn) {
                continue;
            }
            //是否是最后一行
            boolean lastRaw = isLastRaw(childPosition, columnCount, childCounts);
            int left = child.getRight();
            int top = child.getTop();
            int right = left + mDividerWidth;
            int bottom = child.getBottom();
            if(!lastRaw){
                bottom = bottom + mDividerHeight ;
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
        c.restore();
    }

}
