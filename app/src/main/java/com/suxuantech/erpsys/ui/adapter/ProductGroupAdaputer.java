package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.CustomerProductEntity;
import com.suxuantech.erpsys.utils.GlideRoundTransform;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
 * @author Created by 李站旗 on 2017/11/28 0028 19:00 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 对排程分组的recycleview的适配器
 */

public class ProductGroupAdaputer extends GroupedRecyclerViewAdapter {
    boolean isOnePackageProduct;//是否是一销包套（因为一销包套和二）
    boolean isShowCheckBox;//是否展示多选框
    CustomerProductEntity.DataBean dataBean;
    ArrayList<Drawable> drawables = new ArrayList<>();

    public ProductGroupAdaputer(Context context, boolean isOnePackageProduct, CustomerProductEntity.DataBean dataBean) {
        super(context);
        this.isOnePackageProduct = isOnePackageProduct;
        this.dataBean = dataBean;
//        mContext.getResources().getDrawable(R.drawable.image_1);
//        mContext.getResources().getDrawable(R.drawable.image_2);
//        mContext.getResources().getDrawable(R.drawable.image_3);
//        mContext.getResources().getDrawable(R.drawable.image_4);
        drawables.add(mContext.getResources().getDrawable(R.drawable.product_1));
        drawables.add(mContext.getResources().getDrawable(R.drawable.product_2));
        drawables.add(mContext.getResources().getDrawable(R.drawable.product_3));
        drawables.add(mContext.getResources().getDrawable(R.drawable.product_4));
    }
    public void setShowCheckBox(boolean showCheckBox) {
        setCheckAll(false, true);
        isShowCheckBox = showCheckBox;
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
        holder.get(R.id.ll_produc_batch).setVisibility(View.VISIBLE);
        TextView view = holder.get(R.id.tv_package_group_name);
        if (groupPosition == 0) {
            view.setText("一销产品");
        } else if (groupPosition == 1) {
            if (dataBean.getEx() == null || dataBean.getEx().getExf() == null || dataBean.getEx().getExf().size() <= 0) {
                holder.get(R.id.ll_produc_batch).setVisibility(View.GONE);
            } else {
                view.setText("二销产品");
            }
        }
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
    }

    public Drawable randomDrawable(){
        int x=(int)(Math.random()*50);
        int i = x % drawables.size();
     return   drawables.get(i);
    }
    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        TextView view1 = holder.get(R.id.tv_product_info);
        TextView view2 = holder.get(R.id.tv_product_number);
        CheckBox view = holder.get(R.id.cb_product);
        ImageView product = holder.get(R.id.img_product);
//        product.setImageDrawable(randomDrawable());
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)//预加载图片
                .error(R.mipmap.ic_launcher)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
                .transform(new GlideRoundTransform(10));//转化为圆形
        Glide.with(product).load(randomDrawable()).apply(options2).into(product);
        //这里一定要在赋值前不然复用问题勾选的显示会乱
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (groupPosition == 0) {
                    CustomerProductEntity.DataBean.YxBean.YxfBean yxfBean = dataBean.getYx().getYxf().get(childPosition);
                    yxfBean.setChecked(b);
                } else if (groupPosition == 1) {
                    CustomerProductEntity.DataBean.ExBean.ExfBean exfBean = dataBean.getEx().getExf().get(childPosition);
                    exfBean.setChecked(b);
                }
                if (isCheckAll()) {
                    EventBus.getDefault().post("checkAll");
                } else {
                    EventBus.getDefault().post("checkNone");
                }
            }
        });
        if (groupPosition == 0) {
            CustomerProductEntity.DataBean.YxBean.YxfBean yxfBean = dataBean.getYx().getYxf().get(childPosition);
            view1.setText(new MyString(StringUtils.safetyString(yxfBean.getConsumption_name())).setColor(mContext.getResources().getColor(R.color.textColor)));
            view1.append(new MyString(StringUtils.safetyString("\n\n产品类型:")).setColor(mContext.getResources().getColor(R.color.hintColor)));
            view1.append(new MyString(StringUtils.safetyString(yxfBean.getCategories())).setColor(mContext.getResources().getColor(R.color.textColor)));
            view2.setText(new MyString("¥" + StringUtils.safetyString(yxfBean.getPrice())).setColor(mContext.getResources().getColor(R.color.colorAccent)));
            view2.append(new MyString("/件\n\nx:").setColor(mContext.getResources().getColor(R.color.textColor)));
            view2.append(new MyString(StringUtils.safetyString(yxfBean.getAmount())).setColor(mContext.getResources().getColor(R.color.colorAccent)));
            if (isShowCheckBox) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
            view.setChecked(yxfBean.isChecked());
        } else if (groupPosition == 1) {
            CustomerProductEntity.DataBean.ExBean.ExfBean exfBean = dataBean.getEx().getExf().get(childPosition);
            view1.setText(new MyString(StringUtils.safetyString(exfBean.getConsumption_name())).setColor(mContext.getResources().getColor(R.color.textColor)));
            view1.append(new MyString(StringUtils.safetyString("\n\n产品类型:")).setColor(mContext.getResources().getColor(R.color.hintColor)));
            view1.append(new MyString(StringUtils.safetyString(exfBean.getCategories())).setColor(mContext.getResources().getColor(R.color.textColor)));
            view2.setText(new MyString("¥" + StringUtils.safetyString(exfBean.getPrice())).setColor(mContext.getResources().getColor(R.color.colorAccent)));
            view2.append(new MyString("/件\n\nx:").setColor(mContext.getResources().getColor(R.color.textColor)));
            view2.append(new MyString(StringUtils.safetyString(exfBean.getAmount())).setColor(mContext.getResources().getColor(R.color.colorAccent)));
            if (isShowCheckBox) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
            view.setChecked(exfBean.isChecked());
        }
        view.setEnabled(editBox);
    }

    public boolean isCheckAll() {
        boolean one = true, two = true;
        if (dataBean.getYx() != null && dataBean.getYx().getYxf() != null) {
            List<CustomerProductEntity.DataBean.YxBean.YxfBean> yxf = dataBean.getYx().getYxf();
            for (CustomerProductEntity.DataBean.YxBean.YxfBean y : yxf) {
                if (y.isChecked()) {
                    one = true;
                } else {
                    return one = false;
                }
            }
        }
        if (dataBean.getEx() != null && dataBean.getEx().getExf() != null) {
            List<CustomerProductEntity.DataBean.ExBean.ExfBean> exf = dataBean.getEx().getExf();
            for (CustomerProductEntity.DataBean.ExBean.ExfBean e : exf) {
                if (e.isChecked()) {
                    two = true;
                } else {
                    return two = false;
                }
            }
        }
        return one && two;
    }

    private boolean editBox = true;

    public void setCheckAll(boolean checkAll, boolean editBox) {
        this.editBox = editBox;
        if (dataBean.getYx() != null && dataBean.getYx().getYxf() != null) {
            List<CustomerProductEntity.DataBean.YxBean.YxfBean> yxf = dataBean.getYx().getYxf();
            for (CustomerProductEntity.DataBean.YxBean.YxfBean y : yxf) {
                y.setChecked(checkAll);
            }
            dataBean.getYx().setCheckedAll(checkAll);
        }
        if (dataBean.getEx() != null && dataBean.getEx().getExf() != null) {
            List<CustomerProductEntity.DataBean.ExBean.ExfBean> exf = dataBean.getEx().getExf();
            for (CustomerProductEntity.DataBean.ExBean.ExfBean e : exf) {
                e.setChecked(checkAll);
            }
            dataBean.getEx().setCheckedAll(checkAll);
        }
        notifyDataSetChanged();
    }
}
