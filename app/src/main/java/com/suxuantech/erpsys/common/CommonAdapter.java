package com.suxuantech.erpsys.common;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 * @author Created by 李站旗 on 2018/5/12 0012 12:04 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 通用适配器,recycleview和listview都可以
 */
public abstract class CommonAdapter <T> extends RecyclerView.Adapter<SmartViewHolder> implements ListAdapter {
    private final int mLayoutId;
    private final List<T> mList;
    private int mLastPosition = -1;
    private boolean mOpenAnimationEnable = true;
    private AdapterView.OnItemClickListener mListener;
    public CommonAdapter(@LayoutRes int layoutId) {
        setHasStableIds(false);
        this.mList = new ArrayList<>();
        this.mLayoutId = layoutId;
    }
    public CommonAdapter(Collection<T> collection, @LayoutRes int layoutId) {
        setHasStableIds(false);
        this.mList = new ArrayList<>(collection);
        this.mLayoutId = layoutId;
    }

    public CommonAdapter(Collection<T> collection, @LayoutRes int layoutId, AdapterView.OnItemClickListener listener) {
        setHasStableIds(false);
        setOnItemClickListener(listener);
        this.mList = new ArrayList<>(collection);
        this.mLayoutId = layoutId;
    }
    //</editor-fold>
    private void addAnimate(SmartViewHolder holder, int postion) {
        if (mOpenAnimationEnable && mLastPosition < postion) {
            holder.itemView.setAlpha(0);
            holder.itemView.animate().alpha(1).start();
            mLastPosition = postion;
        }
    }
    //<editor-fold desc="RecyclerAdapter">
    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmartViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false),mListener);
    }
    @Override
    public void onBindViewHolder(SmartViewHolder holder, int position) {
        onBindViewHolder(holder, position < mList.size() ? mList.get(position) : null, position);
    }
    protected abstract void onBindViewHolder(SmartViewHolder holder, T model, int position);
    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public void onViewAttachedToWindow(SmartViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        addAnimate(holder, holder.getLayoutPosition());
    }
    public void setOpenAnimationEnable(boolean enabled) {
        this.mOpenAnimationEnable = enabled;
    }

    //</editor-fold>

    //<editor-fold desc="API">

    public CommonAdapter<T> setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
        return this;
    }

    public CommonAdapter<T> refresh(Collection<T> collection) {
        mList.clear();
        mList.addAll(collection);
        notifyDataSetChanged();
        notifyListDataSetChanged();
        mLastPosition = -1;
        return this;
    }

    public CommonAdapter<T> loadMore(Collection<T> collection) {
        mList.addAll(collection);
        notifyDataSetChanged();
        notifyListDataSetChanged();
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="ListAdapter">
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

//    public boolean hasStableIds() {
//        return false;
//    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    public void notifyListDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    /**
     * Notifies the attached observers that the underlying data is no longer valid
     * or available. Once invoked this adapter is no longer valid and should
     * not report further data set changes.
     */
    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SmartViewHolder holder;
        if (convertView != null) {
            holder = (SmartViewHolder) convertView.getTag();
        } else {
            holder = onCreateViewHolder(parent, getItemViewType(position));
            convertView = holder.itemView;
            convertView.setTag(holder);
        }
        holder.setPosition(position);
        onBindViewHolder(holder, position);
        addAnimate(holder, position);
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public boolean isEmpty() {
        return mList==null ||getCount() == 0;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList==null ?0: mList.size();
    }
    //</editor-fold>
}
