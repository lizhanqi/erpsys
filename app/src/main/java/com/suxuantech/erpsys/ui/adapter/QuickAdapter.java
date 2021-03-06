package com.suxuantech.erpsys.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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
 * @author Created by 李站旗 on 2018/4/9 0009 17:47 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: kolin
 * var adapter = object : QuickAdapter<FormEntity>(R.layout.item_home_data, list) {
 * public override fun convert(helper: BaseViewHolder, item: FormEntity) {
 * <p>
 * }
 */

public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private RecyclerView recyclerView;

    public QuickAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this(null,layoutResId, data);
    }

    public QuickAdapter(RecyclerView recyclerView, @LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        this.recyclerView=recyclerView;
    }
    /**
     * 全部更新
     *
     * @param data
     */
    public void updateAll(List<T> data) {
        if (data != null) {
            mData = data;
            notifyDataSetChanged();
        } else {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 追加
     *
     * @param data
     */
    public void apped(List<T> data) {
        if (data != null) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 追加
     *
     * @param data
     */
    public void apped(T data) {
        if (data != null) {
            mData.add(data);
            notifyDataSetChanged();
        }
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public boolean isEnabled(int position) {
        return true;
    }
}
