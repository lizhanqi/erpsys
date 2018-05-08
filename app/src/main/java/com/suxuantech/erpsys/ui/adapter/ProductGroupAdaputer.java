package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.CustomerProductEntity;

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

public class ProductGroupAdaputer extends GroupedRecyclerViewAdapter {
    boolean isOnePackageProduct;//是否是一销包套（因为一销包套和二）
    boolean isShowCheckBox;//是否展示多选框
    CustomerProductEntity.DataBean dataBean;

    public ProductGroupAdaputer(Context context, boolean isOnePackageProduct, CustomerProductEntity.DataBean dataBean) {
        super(context);
        this.isOnePackageProduct = isOnePackageProduct;
        this.dataBean = dataBean;
    }

    public void setShowCheckBox(boolean b) {
        isShowCheckBox = b;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count = 0;
        if (groupPosition == 0) {
            count = dataBean.getYx() == null || dataBean.getYx().getYxf() == null ? 0 : dataBean.getYx().getYxf().size();
        } else if (groupPosition == 1) {
            count = dataBean.getEx() == null || dataBean.getEx().getExf() == null ? 0 : dataBean.getEx().getExf().size();
        }

        return count;
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
        return R.layout.item_product_group;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }
    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_new_product;
    }


    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        TextView view = holder.get(R.id.tv_package_group_name);
        if (groupPosition == 0) {
            view.setText("一销产品");
        } else if (groupPosition == 1) {
            view.setText("二销产品");
        }
    }
    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
    }
    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        TextView view1 = holder.get(R.id.tv_product_info);
        TextView view2 = holder.get(R.id.tv_product_number);
        if (groupPosition == 0) {
            CustomerProductEntity.DataBean.YxBean.YxfBean yxfBean = dataBean.getYx().getYxf().get(childPosition);
            view1.setText(yxfBean.getConsumption_name() + "\n\n产品类型:" + yxfBean.getCategories());
            view2.setText("¥" + yxfBean.getPrice() + "/件\n\nx1:" + yxfBean.getTotal());
        } else if (groupPosition == 1) {
            CustomerProductEntity.DataBean.ExBean.ExfBean exfBean = dataBean.getEx().getExf().get(childPosition);
            view1.setText(exfBean.getConsumption_name() + "\n\n产品类型:" + exfBean.getCategories());
            view2.setText("¥" + exfBean.getPrice() + "/件\n\nx1:" + exfBean.getTotal());
        }
        CheckBox view = holder.get(R.id.cb_product);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        if (isShowCheckBox) {
            view.setVisibility(View.VISIBLE);
            view.setChecked(checkAll);
        } else {
            view.setVisibility(View.GONE);
        }
    }
    boolean checkAll;
    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }
}
