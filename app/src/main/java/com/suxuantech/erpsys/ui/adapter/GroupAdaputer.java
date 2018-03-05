package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;

import java.util.ArrayList;

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
 * @author Created by 李站旗 on 2017/11/28 0028 19:00 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 对排程分组的recycleview的适配器
 */

public class GroupAdaputer extends GroupedRecyclerViewAdapter  {
    ArrayList as;

    public GroupAdaputer(Context context) {
        super(context);
    }


    @Override
    public int getGroupCount() {
        return 5;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return 6;
    }
    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }
    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }
    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_schedule_group;
    }
    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }
    @Override
    public int getChildLayout(int viewType) {
        if (viewType==111){
            return R.layout.item_schedule;
        }
        return R.layout.item_scheduled;
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
            if (childPosition%3==0){
                return 111;
            }
        return super.getChildViewType(groupPosition, childPosition);
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

    }
}
