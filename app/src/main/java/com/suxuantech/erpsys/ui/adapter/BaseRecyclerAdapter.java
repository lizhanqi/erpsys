package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * @author Created by 李站旗 on 2017/11/8 22:09 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:RecyclerView的万能适配器，适配任何一个RecyclerView
 * 并且可不用再调用RecyclerView的 setAdapter
 * 以及RecyclerView的.setLayoutManager(new LinearLayoutManager(v.getContext()));
 * 我这里默认就给你代理了我这使用的是LinearLayoutManager,如果你想用其他的那么你自己再重新设置就好
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder>   {

    protected List<T> realDatas;
    protected final int mItemLayoutId;
    protected boolean isScrolling;
    protected Context cxt;
    private OnItemClickListener listener;
    private boolean useListener=false;//是否使用这个的监听（如果不调用setListenername就不会有回调避免了跟SwipeMenuRecyclerView的点击事件冲突）
    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

    public BaseRecyclerAdapter(RecyclerView recyclerView, Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            realDatas = new ArrayList<>();
        } else if (datas instanceof List) {
            realDatas = (List<T>) datas;
        } else {
            realDatas = new ArrayList<>(datas);
        }
        mItemLayoutId = itemLayoutId;
        cxt = recyclerView.getContext();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling = !(newState == RecyclerView.SCROLL_STATE_IDLE);
                if (!isScrolling) {
                    notifyDataSetChanged();
                }
            }
        });
        if (recyclerView.getAdapter()==null){
            recyclerView.setAdapter(this);
        }
        if (recyclerView.getLayoutManager()==null){
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
    }
    /**
     * Recycler适配器填充方法
     *
     * @param holder      viewholder
     * @param item        javabean
     * @param isScrolling RecyclerView是否正在滚动
     */
    public abstract void convert(RecyclerHolder holder, T item, int position, boolean isScrolling);

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cxt);
        View root = inflater.inflate(mItemLayoutId, parent, false);
        return new RecyclerHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, realDatas.get(position), position, isScrolling);
        if (useListener){
            holder.itemView.setOnClickListener(getOnClickListener(position));
        }
    }

    @Override
    public int getItemCount() {
        return realDatas.size();
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        useListener=true;
        listener = l;
    }

    public View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(@Nullable View v) {
                if (listener != null && v != null) {
                    listener.onItemClick(v, realDatas.get(position), position);
                }
            }
        };
    }
    public void clear(){
        if (realDatas!=null) {
            realDatas.clear();
        }
    }

    /**
     * 更新所有数据（非追加）
     * @param datas
     * @return
     */
    public BaseRecyclerAdapter<T> refresh(Collection<T> datas) {
        if (datas == null) {
            realDatas = new ArrayList<>();
        } else if (datas instanceof List) {
            realDatas = (List<T>) datas;
        } else {
            realDatas = new ArrayList<>(datas);
        }
        return this;
    }
    /**
     * 更新数据(追加)
     * @param datas
     */
    public void  notifyAppendDataSetChanged(Collection<T> datas){
        notifyAppendDataSetChanged(datas,false);
    }

    /**
     * 更新数据(追加是否去重复)
     * @param datas
     */
    public void  notifyAppendDataSetChanged(Collection<T> datas,boolean checkRepetition){
        if (realDatas==null) {
            if (datas == null) {
                realDatas = new ArrayList<>();
            } else if (datas instanceof List) {

                realDatas = (List<T>) datas;
            } else {
                realDatas = new ArrayList<>(datas);
            }
        }else {
            if (checkRepetition){
            for (T t:datas){
                if (!realDatas.contains(t)){
                    realDatas.add(t);
                }

            }
            }else {
                realDatas.addAll(datas);
            }
        }
        notifyDataSetChanged();
    }
    public boolean areAllItemsEnabled() {
        return true;
    }
    public boolean isEnabled(int position) {
        return true;
    }


}