package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.DressEntity;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 可展开收起的Adapter。他跟普通的{@link }基本是一样的。
 * 它只是利用了{@link GroupedRecyclerViewAdapter}的
 * 删除一组里的所有子项{@link GroupedRecyclerViewAdapter#removeChildren(int)} 和
 * 插入一组里的所有子项{@link GroupedRecyclerViewAdapter#insertChildren(int)}
 * 两个方法达到列表的展开和收起的效果。
 * 这种列表类似于{@link ExpandableListView}的效果。
 * 这里我把列表的组尾去掉是为了效果上更像ExpandableListView。
 */
public class ExpandableAdapter extends GroupedRecyclerViewAdapter {

    List<DressEntity.DataBean> mData;

    public ExpandableAdapter(Context context, List<DressEntity.DataBean> data) {
        super(context);
        if (data==null){
            data=new ArrayList<>();
        }
        mData = data;
    }
    public  void upData(List<DressEntity.DataBean> data){
        if (data==null){
           if (mData!=null){
               mData.clear();
           }else {
               mData=new ArrayList<>();
           }
        }else {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //如果当前组收起，就直接返回0，否则才返回子项数。这是实现列表展开和收起的关键。
        if (!isExpand(groupPosition)) {
            return 0;
        }
        return 1;
//        ArrayList<ChildEntity> children = mGroups.get(groupPosition).getChildren();
//        return children == null ? 0 : children.size();
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
        return R.layout.item_expandable_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.dress_material;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        DressEntity.DataBean dataBean = mData.get(groupPosition);
        holder.setText(R.id.tv_expandable_header,   dataBean.getDress_type());
        ImageView ivState = holder.get(R.id.iv_state);
        if (dataBean.isExpand()) {
            ivState.setRotation(90);
        } else {
            ivState.setRotation(0);
        }
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        DressEntity.DataBean dataBean = mData.get(groupPosition);
        TextView view1 = holder.get(R.id.tv_dress_material1);
        TextView view2 = holder.get(R.id.tv_dress_material2);
        TextView view3 = holder.get(R.id.tv_dress_material3);
        TextView view4 = holder.get(R.id.tv_dress_material4);
        TextView view5 = holder.get(R.id.tv_dress_material5);
        TextView view6 = holder.get(R.id.tv_dress_material6);
        TextView view7 = holder.get(R.id.tv_dress_material7);
        TextView view8 = holder.get(R.id.tv_dress_material8);
        TextView view9 = holder.get(R.id.tv_dress_material9);
        TextView view10 = holder.get(R.id.tv_dress_material10);
        view1.setText("礼服价格"+"\u3000");
        view1.append( new MyString("¥" + dataBean.getDress_price()).setColor(mContext.getResources().getColor(R.color.black)));
        view2.setText("礼服尺寸"+"\u3000");
        view2.append(new MyString(dataBean.getDress_size()).setColor(mContext.getResources().getColor(R.color.black)));
        view3.setText("挑选日期"+"\u3000");
        view3.append(new MyString(dataBean.getChoosedate()).setColor(mContext.getResources().getColor(R.color.black)));
        view4.setText("已选礼服"+"\u3000");
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.arrows_right_gray);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), (int) (drawable.getMinimumHeight()));
        view4.setCompoundDrawables(null, null,drawable, null);
        view5.setText("取件日期"+"\u3000");
        view5.append(new MyString(dataBean.getGetdate()).setColor(mContext.getResources().getColor(R.color.black)));
        view6.setText("礼服秘书"+"\u3000");
        view6.append(new MyString(dataBean.getSecretaireman()).setColor(mContext.getResources().getColor(R.color.black)));
        view7.setText("归还日期"+"\u3000");
        view7.append(new MyString(dataBean.getReturndate()).setColor(mContext.getResources().getColor(R.color.black)));
        view8.setText("回件人员"+"\u3000");
        view8.append(new MyString(dataBean.getReturnman()).setColor(mContext.getResources().getColor(R.color.black)));
        view9.setText("实际婚期"+"\u3000");
        view9.append(new MyString(dataBean.getSj_weddingdate()).setColor(mContext.getResources().getColor(R.color.black)));
        view10.setText("礼服备注"+"\u3000");
        view10.append(new MyString(dataBean.getDressremark()).setColor(mContext.getResources().getColor(R.color.black)));
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(groupPosition+"");
            }
        });
    }
    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        return mData.get(groupPosition).isExpand();
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        DressEntity.DataBean entity = mData.get(groupPosition);
        entity.setExpand(true);
        if (animate) {
            insertChildren(groupPosition);
        } else {
            changeDataSet();
        }
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        DressEntity.DataBean entity = mData.get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            removeChildren(groupPosition);
        } else {
            changeDataSet();
        }
    }
}
