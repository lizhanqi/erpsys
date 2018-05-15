package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BaseScheme;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.ui.TypeFlag;
import com.suxuantech.erpsys.ui.activity.SearchOrderActivity;
import com.suxuantech.erpsys.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 对排程分组的recycleview的适配器
 */

public class GroupAdaputer extends GroupedRecyclerViewAdapter {
    Map<String, List<BaseScheme>> data;
    public Map<String, List<BaseScheme>> getdata() {
        return data;
    }
    TypeFlag schemeType = TypeFlag.PHOTOGRAPH;
    public void setSchemeType(TypeFlag schemeType) {
        this.schemeType = schemeType;
    }
    public GroupAdaputer(Context context) {
        super(context);
    }
    @Override
    public int getGroupCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Set<String> strings = data.keySet();
        List<String> list = new ArrayList<String>(strings);//B是set型的
        return data.get(list.get(groupPosition)).size();
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
        return R.layout.item_schedule;
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        Set<String> strings = data.keySet();
        List<String> list = new ArrayList<String>(strings);//B是set型的
        BaseScheme baseScheme = data.get(list.get(groupPosition)).get(childPosition);
        String xingming = baseScheme.getXingming();
        String orderId = baseScheme.getOrderId();
        if (!StringUtils.empty(orderId) || !StringUtils.empty(xingming)) {
            return 111;
        }
        return super.getChildViewType(groupPosition, childPosition);
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        Set<String> strings = data.keySet();
        List<String> list = new ArrayList<String>(strings);//B是set型的
        holder.setText(R.id.tv_count, data.get(list.get(groupPosition)).size() + "");
        StringBuffer pctime = new StringBuffer(data.get(list.get(groupPosition)).get(0).getPctime());
        pctime.insert(2, ":");
        holder.setText(R.id.tv_time, pctime.toString());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        TextView tvCustmoer = (TextView) holder.get(R.id.tv_custmoer);
        LinearLayout llScheme = (LinearLayout) holder.get(R.id.ll_scheme);
        ImageView imgScheme = (ImageView) holder.get(R.id.img_scheme);
        ImageView imgPlaceholder = (ImageView) holder.get(R.id.img_placeholder);
        Set<String> strings = data.keySet();
        List<String> list = new ArrayList<String>(strings);//B是set型的
        TextView tvZone = (TextView) holder.get(R.id.tv_zone);
        BaseScheme baseScheme = data.get(list.get(groupPosition)).get(childPosition);
        tvZone.setText(baseScheme.getArea());
        String xingming = baseScheme.getXingming();
        String orderId = baseScheme.getOrderId();
        if (!StringUtils.empty(orderId) || !StringUtils.empty(xingming)) {
            llScheme.setVisibility(View.GONE);
            tvCustmoer.setVisibility(View.VISIBLE);
            tvCustmoer.setText(StringUtils.safetyString(orderId) + StringUtils.safetyString(xingming));
        } else {
            llScheme.setVisibility(View.VISIBLE);
            tvCustmoer.setVisibility(View.GONE);
            int islock = baseScheme.getIslock();
            if (islock != 0) {
                imgPlaceholder.setImageResource(R.drawable.icon_lock);
                imgScheme.setVisibility(View.GONE);
                imgPlaceholder.setOnClickListener(cl -> {
                    placeHolder(baseScheme, true);
                });
            } else {
                imgPlaceholder.setImageResource(R.drawable.icon_unlock);
                imgScheme.setVisibility(View.VISIBLE);
                imgPlaceholder.setOnClickListener(cl -> {
                    placeHolder(baseScheme, false);
                });
                imgScheme.setOnClickListener(cl -> {
                    Intent intent = new Intent(mContext, SearchOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("type", schemeType);
                    bundle.putParcelable("data", baseScheme);
                    bundle.putParcelable("allSchemeData", baseScheme.getBundle().getParcelable("allData"));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                });
            }
        }
    }

    /**
     * @param baseScheme
     * @param unlock     是否是解锁
     */
    public void placeHolder(BaseScheme baseScheme, boolean unlock) {
        String url = "";
        if (schemeType == TypeFlag.PHOTOGRAPH) {
            if (unlock) {
                url = Contact.getFullUrl(Contact.UNLOCK_PHOTOGRAPH_SCHEME, Contact.TOKEN, baseScheme.getPcid(), App.getApplication().getUserInfor().getShop_code());
            } else {
                url = Contact.getFullUrl(Contact.LOCK_PHOTOGRAPH_SCHEME, Contact.TOKEN, baseScheme.getPcid(), App.getApplication().getUserInfor().getShop_code());
            }
        } else {
            if (unlock) {
                url = Contact.getFullUrl(Contact.UNLOCK_OPTION_PANEL_SCHEME, Contact.TOKEN, baseScheme.getPcid(), App.getApplication().getUserInfor().getShop_code());
            } else {
                url = Contact.getFullUrl(Contact.LOCK_OPTION_PANEL_SCHEME, Contact.TOKEN, baseScheme.getPcid(), App.getApplication().getUserInfor().getShop_code());
            }
        }
        EventBus.getDefault().post(url);
    }

    public void fresh(Map<String, List<BaseScheme>> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
