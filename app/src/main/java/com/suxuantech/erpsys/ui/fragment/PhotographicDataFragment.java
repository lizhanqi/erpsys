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
import com.suxuantech.erpsys.ui.adapter.MapQuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;

import java.util.HashMap;

/**
 * 		"orderId": "SY18012000007",
 "phototime": "1300",
 "cameraman": "",
 "photoassistant": "",
 "lighting_engineer": "杨过",
 "designer": "",
 "mentor": "",
 "phototype": "拍照",
 "photostate": "全未拍",
 "dresser": "",
 "photonote": "",
 "photocount": "0",
 "locationcount": 0,
 "Interiorcount": 0,
 "shop_name": "沈阳时尚经典婚纱店",
 "shop_code": "ZX002",
 "secretaireman": "王语嫣",
 "topic": "",
 "returndate": null,
 "getdate": null,
 "choosedate": "2018-02-24 00:00:00.000",
 "dress_count": 0,
 "photo_shop_code": "ZX002",
 "shexiangshi": null,
 "jianjishi": null,
 "zhuozhuangshi": null,
 "huazhuangzhuli": null,
 "haijingshu": null,
 "sheyingshiJB": null,
 "huazhuangshiJB": null,
 "dresschoosetime": "1000",
 "dressgettime": "",
 "dressreturtime": "",
 "islast": 1,
 "photostate1": "全未拍"
 */
public class PhotographicDataFragment extends BaseSupportFragment {

    private RecyclerView recyclerView;

    public PhotographicDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        recyclerView = new RecyclerView(getContext());
        return recyclerView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id= getArguments().getString("orderId");
        CustomerPhotoEntity.DataBean data= getArguments().getParcelable("data");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("服务店面",data.getShop_name());
        stringStringHashMap.put("拍照时间",data.getPhotodate());
        stringStringHashMap.put("拍照状态",data.getPhotostate());
        stringStringHashMap.put("拍摄主题",data.getTopic());
        stringStringHashMap.put("摄影级别","");
        stringStringHashMap.put("摄影师",data.getLighting_engineer());
        stringStringHashMap.put("摄影助理",data.getPhotoassistant());
        stringStringHashMap.put("化妆级别",data.getHuazhuangshiJB());
        stringStringHashMap.put("化妆师","");
        stringStringHashMap.put("化妆助理",data.getHuazhuangzhuli());
        stringStringHashMap.put("摄像师",data.getCameraman());
        stringStringHashMap.put("剪辑师","");
        stringStringHashMap.put("着装师",data.getZhuozhuangshi());
        stringStringHashMap.put("服装套数","");
        stringStringHashMap.put("内景数",data.getInteriorcount());
        stringStringHashMap.put("外景数","");
        stringStringHashMap.put("拍照张数",data.getPhotocount());
        stringStringHashMap.put("海景数","");

        stringStringHashMap.put("礼服秘书",data.getSecretaireman());
        stringStringHashMap.put("挑选时间","");
        stringStringHashMap.put("取件时间",data.getDressgettime());
        stringStringHashMap.put("归还时间",data.getDressreturtime());
        stringStringHashMap.put("客户备注","");

        MapQuickAdapter  mapQuickAdapter = new MapQuickAdapter<String>    (R.layout.item_form, stringStringHashMap ) {
            @Override
            protected void convert(BaseViewHolder helper, int posstion, String key, String value) {
                super.convert(helper, posstion, key, value);
                if (key.equals("摄影级别")){
                    View view1 = helper.getView(R.id.ll_form);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view1.getLayoutParams();
                    layoutParams.setMargins(0,30,0,0);
                }
                helper.setText(R.id.tv_form_key,key);
                helper.setText(R.id.tv_form_value,value);
            }
        };
        recyclerView.addItemDecoration(new DefaultItemDecoration(getActivity().getResources().getColor( R.color.mainNavline_e7)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mapQuickAdapter);
    }
}
