package com.suxuantech.erpsys.fragment;


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
    private ArrayList<NoFragment> fragments;
    int witch;
    //    设置参数确定子Fragment加载
    /**
     *     0是取件资料的
     *     1是摄影资料
     *     2是选片资料
     *     3是拍照礼服(礼服资料)
     */
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
            //取件资料
            fragments.add(new TakeDataFragment1Fragment());
            fragments.add(new TakeDataFragment1Fragment());
            fragments.add(new TakeDataFragment1Fragment());
        } else if (witch==1){
            //摄影资料
            fragments.add(new PhotographicDataFragment());
            fragments.add(new PhotographicDataFragment());
            fragments.add(new PhotographicDataFragment());
        } else if (witch==2){
            //选片资料
            fragments.add(new SelectedPictureInformationFragment());
            fragments.add(new SelectedPictureInformationFragment());
            fragments.add(new SelectedPictureInformationFragment());
        }else if (witch==3){
//            拍照礼服(礼服资料)
            fragments.add(new TakePictureDressFragment());
            fragments.add(new TakePictureDressFragment());
            fragments.add(new TakePictureDressFragment());
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
