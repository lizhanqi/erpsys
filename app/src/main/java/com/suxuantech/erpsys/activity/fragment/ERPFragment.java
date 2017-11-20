package com.suxuantech.erpsys.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oragee.banners.BannerView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseNoFragment;
import com.suxuantech.erpsys.adapter.MyFragmentAdapter;

import java.util.ArrayList;


public class ERPFragment extends BaseNoFragment {

    private BannerView bannersView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_erp, container, false);

        return view;
    }
    private ArrayList<Fragment> fragmentrs;//视频故事界面,图片故事界面集合
View view;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         this.view=view;

    }

    @Override
    public void onPause() {
        for (Fragment fragment:fragmentrs){
            fragment.onPause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        fragmentrs = new ArrayList<>();
        ERPLeftFragment erpLeftFragment = new ERPLeftFragment();
        ERPRightFragment erpRightFragment = new ERPRightFragment();
        fragmentrs.add(erpLeftFragment);
        fragmentrs.add(erpRightFragment);
        ViewPager mViewPager = view.findViewById(R.id.view_pager);

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter( getChildFragmentManager(), fragmentrs);
        mViewPager.setAdapter(myFragmentAdapter);
        mViewPager.setCurrentItem(0);
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onStart() {


        super.onStart();
    }

    @Override
    protected void widgetClick(View v) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
