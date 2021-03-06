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
 *
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mDividerWidth;
    private int mDividerHeight;
    private List<Integer> mViewTypeList = new ArrayList<>();
    int leftOffsetX = 0;

    /**
     * @param color decoration line color.
     */
    public DefaultItemDecoration(@ColorInt int color) {
        this(color, 2, 2, -1);
    }

    /**
     * @param color           line color.
     * @param dividerWidth    line width.
     * @param dividerHeight   line height.
     * @param excludeViewType don't need to draw the ViewType of the item of the split line.
     */
    public DefaultItemDecoration(@ColorInt int color, int dividerWidth, int dividerHeight, int... excludeViewType) {
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
    public DefaultItemDecoration offSetX(int offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    boolean lastRawDontDraw;

    public DefaultItemDecoration lastRawDontDraw(boolean lastRawDontDraw) {
        this.lastRawDontDraw = lastRawDontDraw;
        return this;
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

        int columnCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

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
                if(!lastRawDontDraw){
                    outRect.set(0, mDividerHeight / 2, 0, 0);
                    outRect.offset(offsetX, 0);
                }
            } else {
                outRect.set(0, mDividerHeight / 2, 0, mDividerHeight / 2);
                outRect.offset(offsetX, 0);
            }
        } else {
            if (firstRaw && firstColumn) { // right, bottom
                outRect.set(0, 0, mDividerWidth / 2, mDividerHeight / 2);
            } else if (firstRaw && lastColumn) { // left, right
                outRect.set(mDividerWidth / 2, 0, 0, mDividerHeight / 2);
            } else if (firstRaw) { // left, right, bottom
                outRect.set(mDividerWidth / 2, 0, mDividerWidth / 2, mDividerHeight / 2);
            } else if (lastRaw && firstColumn) { // top, right
                outRect.set(0, mDividerHeight / 2, mDividerWidth / 2, 0);
            } else if (lastRaw && lastColumn) { // left, top
                outRect.set(mDividerWidth / 2, mDividerHeight / 2, 0, 0);
            } else if (lastRaw) { // left, top, right
                outRect.set(mDividerWidth / 2, mDividerHeight / 2, mDividerWidth / 2, 0);
            } else if (firstColumn) { // top, right, bottom
                outRect.set(0, mDividerHeight / 2, mDividerWidth / 2, mDividerHeight / 2);
            } else if (lastColumn) { // left, top, bottom
                outRect.set(mDividerWidth / 2, mDividerHeight / 2, 0, mDividerHeight / 2);
            } else { // left, bottom.
                outRect.set(mDividerWidth / 2, mDividerHeight / 2, mDividerWidth / 2, mDividerHeight / 2);
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
     * @param leftOffsetX
     */
    public void setJustLeftOffsetX(int leftOffsetX) {
        this.leftOffsetX = leftOffsetX;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        c.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(child);
            if (childPosition < 0) {
                continue;
            }
            if (mViewTypeList.contains(parent.getAdapter().getItemViewType(childPosition))) {
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

    public void drawVertical(Canvas c, RecyclerView parent) {
        c.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(child);
            if (childPosition < 0) {
                continue;
            }
            if (mViewTypeList.contains(parent.getAdapter().getItemViewType(childPosition))) {
                continue;
            }
            if (child instanceof SwipeMenuRecyclerView.LoadMoreView) {
                continue;
            }
            final int left = child.getRight();
            final int top = child.getTop();
            final int right = left + mDividerWidth;
            final int bottom = child.getBottom();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
        c.restore();
    }

}
