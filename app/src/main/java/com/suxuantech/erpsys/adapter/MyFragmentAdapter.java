package com.suxuantech.erpsys.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    FragmentManager fm;
    List<Fragment> fragmentrs;
    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentrs) {
        super(fm);
        this.fm=fm;
        this.fragmentrs=fragmentrs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return fragmentrs.get(position);
        }else if(position==1){
            return fragmentrs.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentrs.size();
    }
}
