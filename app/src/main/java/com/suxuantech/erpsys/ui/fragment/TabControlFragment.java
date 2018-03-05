package com.suxuantech.erpsys.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 * 可以滑动切换fragment带有
 */

public class TabControlFragment extends SupportFragment {
    private ArrayList<Fragment> fragments;
    private ArrayList<Fragment> takeDataFragments;
    private ArrayList<Fragment> photographicDataFragments;
    private ArrayList<Fragment> selectedPictureInformationFragments;
    private ArrayList<Fragment> takePictureDressDataFragments;


    int witch;
    private ViewPager pager;
    private TabLayout tablayout;
    private View view;
    private FragmentManager childFragmentManager;
    private DefaultFragmentAdapter defaultFragmentAdapter;
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
        view = inflater.inflate(R.layout.fragment_tab_control, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragments = new ArrayList<>();
        childFragmentManager = getChildFragmentManager();
        tablayout = view.findViewById(R.id.tablayout);
        pager= view.findViewById(R.id.vp);
        initFragement();
    }

    // 在HomeFragment.class中：
    @Override
    public void onNewBundle(Bundle newBundle){
        witch = newBundle.getInt("witch");
        initFragement();
    }
    private void initFragement( ) {
        String[] stringArray = getResources().getStringArray(R.array.takedata_information_item);
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        for (Fragment f :   childFragmentManager.getFragments()) {
            ft.remove(f);
        }
        ft.commit();
        pager.removeAllViews();
        pager.removeAllViewsInLayout();
        pager.destroyDrawingCache();
       childFragmentManager.executePendingTransactions();
        switch (witch) {
            case 0:
                //取件资料
                if (takeDataFragments==null) {
                    takeDataFragments=new ArrayList<>();
                    takeDataFragments.add(new TakeDataFragment());
                    takeDataFragments.add(new TakeDataFragment());
                    takeDataFragments.add(new TakeDataFragment());
                }
                fragments=takeDataFragments;
                break;
            case 1:
                //摄影资料
                if (photographicDataFragments==null) {
                    photographicDataFragments=new ArrayList<>();
                    photographicDataFragments.add(new PhotographicDataFragment());
                    photographicDataFragments.add(new PhotographicDataFragment());
                    photographicDataFragments.add(new PhotographicDataFragment());
                }
                fragments=photographicDataFragments;
                break;
            case 2:
                //选片资料
                if (selectedPictureInformationFragments==null) {
                    selectedPictureInformationFragments=new ArrayList<>();
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                }
                fragments=selectedPictureInformationFragments;
                break;
            case 3:
                //拍照礼服(礼服资料)
                if (takePictureDressDataFragments==null){
                    takePictureDressDataFragments=new ArrayList<>();
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                }
                fragments=takePictureDressDataFragments;
                break;
            default:
        }
   defaultFragmentAdapter = new DefaultFragmentAdapter(childFragmentManager, new ArrayList<>(Arrays.asList(stringArray)), new DefaultFragmentAdapter.FragmentShow() {
          @Override
          public Fragment getItemFragment(int positon) {

              return fragments.get(positon);
          }
      });
        pager.setAdapter(defaultFragmentAdapter);
        pager.setOffscreenPageLimit(1);
        tablayout.setupWithViewPager(pager);
    }

}
