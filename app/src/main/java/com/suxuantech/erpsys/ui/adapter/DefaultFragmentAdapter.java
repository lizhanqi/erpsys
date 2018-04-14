package com.suxuantech.erpsys.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 * 通用的配合Viewpager使用的适配器，也可以和Viewpagerindeace搭配，不用管PageTitle
 */
public class DefaultFragmentAdapter extends FragmentStatePagerAdapter {
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
     *防止频繁的销毁视图的解决方案
     * setOffscreenPageLimit(2)
     * 或者重写PagerAdaper的destroyItem方法为空即可   笔者此处使用的是方案2
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
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
        notifyDataSetChanged();
    }

    public void notifyFragementsSetChanged(FragmentManager fm, ArrayList<String> titleArray, Fragment mFragments) {
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
