package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.CustomerPhotoEntity;
import com.suxuantech.erpsys.entity.FormEntity;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.utils.StringUtils;

import java.util.ArrayList;

/**
 * "orderId": "SY18012000007",
 * "phototime": "1300",
 * "cameraman": "",
 * "photoassistant": "",
 * "lighting_engineer": "杨过",
 * "designer": "",
 * "mentor": "",
 * "phototype": "拍照",
 * "photostate": "全未拍",
 * "dresser": "",
 * "photonote": "",
 * "photocount": "0",
 * "locationcount": 0,
 * "Interiorcount": 0,
 * "shop_name": "沈阳时尚经典婚纱店",
 * "shop_code": "ZX002",
 * "secretaireman": "王语嫣",
 * "topic": "",
 * "returndate": null,
 * "getdate": null,
 * "choosedate": "2018-02-24 00:00:00.000",
 * "dress_count": 0,
 * "photo_shop_code": "ZX002",
 * "shexiangshi": null,
 * "jianjishi": null,
 * "zhuozhuangshi": null,
 * "huazhuangzhuli": null,
 * "haijingshu": null,
 * "sheyingshiJB": null,
 * "huazhuangshiJB": null,
 * "dresschoosetime": "1000",
 * "dressgettime": "",
 * "dressreturtime": "",
 * "islast": 1,
 * "photostate1": "全未拍"
 */
public class PhotographicDataFragment extends BaseSupportFragment {
    private RecyclerView recyclerView;

    public PhotographicDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = new RecyclerView(getContext());
        return recyclerView;
    }


    //    tiaoxuanTimeStr,qvjianTimeStr,guihuanTimeStr],
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("orderId");
        CustomerPhotoEntity.DataBean data = getArguments().getParcelable("data");
        ArrayList<FormEntity> formEntities = new ArrayList<>();
        formEntities.add(new FormEntity("服务店面", StringUtils.safetyString(data.getShop_name())));
        FormEntity photoTime = new FormEntity("拍照时间",StringUtils.subDate(StringUtils.safetyString(data.getPhotodate())) );
        photoTime.setMarginTop(30);
        formEntities.add(photoTime);
        formEntities.add(new FormEntity("拍照状态", StringUtils.safetyString(data.getPhotostate())));
        FormEntity topic = new FormEntity("拍摄主题", StringUtils.safetyString(data.getTopic()));
        topic.setMarginTop(30);
        formEntities.add(topic);
        FormEntity photolevel = new FormEntity("摄影级别", StringUtils.safetyString(data.getHuazhuangshiJB()));
        photolevel.setMarginTop(30);
        formEntities.add(photolevel);
        formEntities.add(new FormEntity("摄影师", StringUtils.safetyString(data.getCameraman())));
        formEntities.add(new FormEntity("摄影助理", StringUtils.safetyString(data.getPhotoassistant())));
        formEntities.add(new FormEntity("化妆级别", StringUtils.safetyString(data.getHuazhuangshiJB())));
        formEntities.add(new FormEntity("化妆师", StringUtils.safetyString(data.getDresser())));
        formEntities.add(new FormEntity("化妆助理", StringUtils.safetyString(data.getHuazhuangzhuli())));
        formEntities.add(new FormEntity("摄像师", StringUtils.safetyString(data.getShexiangshi())));
        formEntities.add(new FormEntity("剪辑师", StringUtils.safetyString(data.getJianjishi())));//
        formEntities.add(new FormEntity("着装师", StringUtils.safetyString(data.getZhuozhuangshi())));
        FormEntity dressCount = new FormEntity("服装套数", StringUtils.safetyString(data.getDress_count()));
        dressCount.setMarginTop(30);
        formEntities.add(dressCount);
        formEntities.add(new FormEntity("内景数", StringUtils.safetyString(data.getInteriorcount())));
        formEntities.add(new FormEntity("外景数", StringUtils.safetyString(data.getLocationcount())));
        formEntities.add(new FormEntity("拍照张数", StringUtils.safetyString(data.getPhotocount())));
        formEntities.add(new FormEntity("海景数", StringUtils.safetyString(data.getHaijingshu())));
        FormEntity Secretaireman = new FormEntity("礼服秘书", StringUtils.safetyString(data.getSecretaireman()));
        Secretaireman.setMarginTop(30);
        formEntities.add(Secretaireman);
        formEntities.add(new FormEntity("挑选时间",StringUtils.subDate( StringUtils.safetyString(data.getChoosedate())) +"\u3000"+ StringUtils.safetyString(data.getDresschoosetime())));
        formEntities.add(new FormEntity("取件时间", StringUtils.subDate( StringUtils.safetyString(data.getGetdate()))+"\u3000"+ StringUtils.safetyString(data.getDressgettime())));
        formEntities.add(new FormEntity("归还时间",StringUtils.subDate(  StringUtils.safetyString(data.getReturndate())) +"\u3000"+  StringUtils.safetyString(data.getDressreturtime())));
        FormEntity remark = new FormEntity("客户备注", StringUtils.safetyString(data.getPhotonote()));
        remark.setMarginTop(30);
        formEntities.add(remark);
        QuickAdapter mapQuickAdapter = new QuickAdapter<FormEntity>(R.layout.item_form, formEntities) {
            @Override
            protected void convert(BaseViewHolder helper, FormEntity item) {

                View view1 = helper.getView(R.id.ll_form);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view1.getLayoutParams();
            layoutParams.setMargins(0, item.getMarginTop(), 0, 0);
                helper.setText(R.id.tv_form_key, item.getKey());
                helper.setText(R.id.tv_form_value, item.getValue());
            }
        };
        recyclerView.addItemDecoration(new DefaultItemDecoration(getActivity().getResources().getColor(R.color.mainNavline_e7)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mapQuickAdapter);
    }
}
