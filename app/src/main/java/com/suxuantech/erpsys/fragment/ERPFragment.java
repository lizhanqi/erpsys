package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.oragee.banners.BannerView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseNoFragment;
import com.suxuantech.erpsys.adapter.DefaultFragmentAdapter;

import java.util.ArrayList;


public class ERPFragment extends BaseNoFragment {

    private BannerView bannersView;
    private RadioGroup mRadioGroup;

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
        fragmentrs = new ArrayList<>();
        ERPLeftFragment erpLeftFragment = new ERPLeftFragment();
        ERPRightFragment erpRightFragment = new ERPRightFragment();
        fragmentrs.add(erpLeftFragment);
        fragmentrs.add(erpRightFragment);
        final ViewPager mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    ImmersionBar.with(ERPFragment.this).reset().fitsSystemWindows(false).statusBarDarkFont(true).navigationBarColor(R.color.themeColor).init();
                }else {
                    ImmersionBar.with(ERPFragment.this).reset().navigationBarColor(R.color.themeColor).fitsSystemWindows(true).statusBarDarkFont(true, 0.1f).init();
                    //ImmersionBar.with(ERPFragment.this).reset().fitsSystemWindows(true).statusBarDarkFont(true).navigationBarColor(R.color.themeColor).init();
                }
            }
            @Override
            public void onPageSelected(int position) {
               for (int i=0;i<mRadioGroup.getChildCount();i++){
                   RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(i);
                   if (i==position){
                       childAt.setChecked(true);
                   }else {
                       childAt.setChecked(false);
                   }
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        DefaultFragmentAdapter myFragmentAdapter = new DefaultFragmentAdapter(getChildFragmentManager(), 2, new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {
               return fragmentrs.get(positon);
            }
        });
        mViewPager.setAdapter(myFragmentAdapter);
        mViewPager.setCurrentItem(0);
        ImmersionBar.with(ERPFragment.this).reset().statusBarDarkFont(false).init();
        mRadioGroup = view.findViewById(R.id.rg_vp);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radio_1){
                    mViewPager.setCurrentItem(0);
                 ImmersionBar.with(ERPFragment.this).reset().statusBarColorTransformEnable(true).init();
                }else {
                    mViewPager.setCurrentItem(1);
                   ImmersionBar.with(ERPFragment.this).reset().navigationBarColor(R.color.themeColor).fitsSystemWindows(true).statusBarDarkFont(true, 0.5f).init();
                }
            }
        });
    }

    @Override
    public void onPause() {
        for (Fragment fragment:fragmentrs){
            fragment.onPause();
        }
        super.onPause();
    }



    @Override
    protected void widgetClick(View v) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
