package com.suxuantech.erpsys.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.adapter.DefaultFragmentAdapter;
import com.yanzhenjie.fragment.NoFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * 取件资料
 *
 */

public class TakeDataFragment extends NoFragment {
    private ArrayList<Fragment> fragments;
    int witch;
    //    设置参数确定子Fragment加载
    /**
     *     0是取件资料的
     *     1是摄影资料
     */
    public TakeDataFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        witch = getArguments().getInt("witch");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_data, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        final ViewPager pager = (ViewPager) view.findViewById(R.id.vp);
        String[] stringArray = getResources().getStringArray(R.array.takedata_information_item);
        fragments = new ArrayList<>();
        if (witch==0) {
            fragments.add(new TakeDataFragment1Fragment());
            fragments.add(new TakeDataFragment1Fragment());
            fragments.add(new TakeDataFragment1Fragment());
        } else if (witch==1){
            fragments.add(new PhotographicDataFragment());
            fragments.add(new PhotographicDataFragment());
            fragments.add(new PhotographicDataFragment());
        }

        pager.setAdapter(new DefaultFragmentAdapter(getChildFragmentManager(), new  ArrayList<>(Arrays.asList(stringArray)), new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {
                return fragments.get(positon);
            }
        }));
        tablayout.setupWithViewPager(pager);
    }
}