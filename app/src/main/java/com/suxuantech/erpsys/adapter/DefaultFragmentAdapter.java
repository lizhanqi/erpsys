package com.suxuantech.erpsys.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 * 通用的配合Viewpager使用的适配器，也可以和Viewpagerindeace搭配，不用管PageTitle
 */
public class DefaultFragmentAdapter extends FragmentPagerAdapter {
    FragmentShow fragmentShow;
    int mCount;
    ArrayList<String> titleArray;
    public  interface  FragmentShow{
        Fragment     getItemFragment(int positon);
    }

    /**
     *
     * @param fm 管理器
     * @param mCount 总共几个
     */
    public DefaultFragmentAdapter(FragmentManager fm, int mCount) {
        super(fm);
        this.  mCount=  mCount;
    }

    /**
     *
     * @param fm
     * @param mCount
     * @param fragmentShow
     */
    public DefaultFragmentAdapter(FragmentManager fm, int mCount, FragmentShow fragmentShow) {
        super(fm);
        this.  mCount=  mCount;
        this.fragmentShow=fragmentShow;
    }

    /**
     * 含有标题的Fragment适配器（配合Viewpagerindeace的）
     * @param fm  管理器
     * @param titleArray 标题集合
     */
    public DefaultFragmentAdapter(FragmentManager fm, ArrayList<String> titleArray) {
        super(fm);
        this.  titleArray=titleArray;
        mCount=    titleArray==null? 0:titleArray.size();
    }

    /**
     * 含有标题的Fragment适配器（（配合Viewpagerindeace的）
     * @param fm  管理器
     * @param titleArray  标题集合
     * @param fragmentShow Fragment页面回调
     */
    public DefaultFragmentAdapter(FragmentManager fm, ArrayList<String> titleArray, FragmentShow fragmentShow) {
        super(fm);
        this.fragmentShow=fragmentShow;
        this.  titleArray=titleArray;
        mCount=    titleArray==null? 0:titleArray.size();
    }


    /**
     * 设置Fragment
     * @param fragmentShow
     */
    public void setFragmentShow(FragmentShow fragmentShow) {
        this.fragmentShow = fragmentShow;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragmentShow==null){
            new IllegalArgumentException("FragmentShow 未设置，请调用setFragmentShow返回Fragment视图，或者传入FragmentShow");
        }
        return fragmentShow.getItemFragment(position);
    }
    @Override
    public int getCount() {
        return  mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray==null||position>titleArray.size()? "No Set titleArray":titleArray.get(position);
    }
}
