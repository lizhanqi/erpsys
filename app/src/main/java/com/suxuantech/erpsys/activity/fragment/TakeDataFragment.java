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

/**
 * A simple {@link Fragment} subclass.
 * 取件资料
 */
public class TakeDataFragment extends NoFragment {


    private ArrayList<Fragment> fragments;

    public TakeDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_data, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);


        final ViewPager pager = (ViewPager) view.findViewById(R.id.vp);
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        fragments = new ArrayList<>();
        fragments.add(new TakeDataFragment1Fragment());
        fragments.add(new TakeDataFragment2Fragment());
        fragments.add(new TakeDataFragment2Fragment());
        pager.setAdapter(new DefaultFragmentAdapter(getChildFragmentManager(), list, new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {
                return fragments.get(positon);
            }
        }));
        tablayout.setupWithViewPager(pager);
    }
}
