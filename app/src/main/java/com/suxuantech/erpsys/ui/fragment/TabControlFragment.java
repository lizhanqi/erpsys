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

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.CustomerPhotoEntity;
import com.suxuantech.erpsys.entity.SelectPictureEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * QuickAdapter simple {@link Fragment} subclass.
 * 可以滑动切换fragment带有
 */

public class TabControlFragment extends BaseSupportFragment {
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
     * 0是取件资料的
     * 1是摄影资料
     * 2是选片资料
     * 3是拍照礼服(礼服资料)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
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
        pager = view.findViewById(R.id.vp);
        if (witch == 2) {
            getSelect();
        } else if (witch == 1) {
            getPhotoInfo();
        } else {
            initFragement();
        }
    }

    private void getSelect() {
        String url = Contact.getFullUrl(Contact.CUSTOMER_SELECTED_PICTURE, Contact.TOKEN, getArguments().getString("orderId"), App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<SelectPictureEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SelectPictureEntity>(url, RequestMethod.POST, SelectPictureEntity.class);
        HttpListener<SelectPictureEntity> searchByCustmor = new HttpListener<SelectPictureEntity>() {
            @Override
            public void onSucceed(int what, Response<SelectPictureEntity> response) {
                if (response.get().isOK()) {
                    List<SelectPictureEntity.DataBean> data = response.get().getData();
                    if (data != null) {
                        initSelectFragment(data);
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<SelectPictureEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }


    private void getPhotoInfo() {
        String url = Contact.getFullUrl(Contact.CUSTOMER_PHOTO_INFO, Contact.TOKEN, getArguments().getString("orderId"), App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<CustomerPhotoEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerPhotoEntity>(url, RequestMethod.POST, CustomerPhotoEntity.class);
        HttpListener<CustomerPhotoEntity> searchByCustmor = new HttpListener<CustomerPhotoEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerPhotoEntity> response) {
                if (response.get().isOK()) {
                    List<CustomerPhotoEntity.DataBean> data = response.get().getData();
                    if (data != null) {
                        initPhotoFragement(data);
                    }
                }
            }



            @Override
            public void onFailed(int what, Response<CustomerPhotoEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
    private void initPhotoFragement(List<CustomerPhotoEntity.DataBean> data) {
        ArrayList<String> strings = new ArrayList<>();
        for (CustomerPhotoEntity.DataBean da : data) {
            String select_order_name = da.getPhototype();
            if (select_order_name.isEmpty()){
                select_order_name="未知";
            }
            strings.add(select_order_name);
        }
        resetpager();
        if (photographicDataFragments == null) {
            photographicDataFragments = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++) {
                PhotographicDataFragment selectedPictureInformationFragment = new PhotographicDataFragment();
                Bundle bundle = new Bundle();
                bundle.putString("orderid", getArguments().getString("orderId"));
                bundle.putParcelable("data", data.get(i));
                selectedPictureInformationFragment.setArguments(bundle);
                photographicDataFragments.add(selectedPictureInformationFragment);
            }
        }
        fragments = photographicDataFragments;
        defaultFragmentAdapter = new DefaultFragmentAdapter(childFragmentManager, strings, new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {

                return fragments.get(positon);
            }
        });
        pager.setAdapter(defaultFragmentAdapter);
        tablayout.setupWithViewPager(pager);
        if (fragments.size() > 3) {
            tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
    /**
     * 初始化网络控制Fragement
     *
     * @param data
     */
    private void initSelectFragment(List<SelectPictureEntity.DataBean> data) {
        ArrayList<String> strings = new ArrayList<>();
        for (SelectPictureEntity.DataBean da : data) {
            String select_order_name = da.getSelect_order_name();
            strings.add(select_order_name);
        }
        resetpager();
        //选片资料
        if (selectedPictureInformationFragments == null) {
            selectedPictureInformationFragments = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++) {
                SelectedPictureInformationFragment selectedPictureInformationFragment = new SelectedPictureInformationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("orderid", getArguments().getString("orderId"));
                bundle.putParcelable("data", data.get(i));
                selectedPictureInformationFragment.setArguments(bundle);
                selectedPictureInformationFragments.add(selectedPictureInformationFragment);
            }
        }
        fragments = selectedPictureInformationFragments;
        defaultFragmentAdapter = new DefaultFragmentAdapter(childFragmentManager, strings, new DefaultFragmentAdapter.FragmentShow() {
            @Override
            public Fragment getItemFragment(int positon) {

                return fragments.get(positon);
            }
        });
        pager.setAdapter(defaultFragmentAdapter);
        tablayout.setupWithViewPager(pager);
        if (fragments.size() > 4) {
            tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    /**
     * 重置状态
     */
    private void resetpager() {
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        for (Fragment f : childFragmentManager.getFragments()) {
            ft.remove(f);
        }
        ft.commit();
        pager.removeAllViews();
        pager.removeAllViewsInLayout();
        pager.destroyDrawingCache();
        childFragmentManager.executePendingTransactions();
    }

    // 在HomeFragment.class中：
    @Override
    public void onNewBundle(Bundle newBundle) {
        witch = newBundle.getInt("witch");
        if (witch == 2) {
            getSelect();
        } else if (witch == 1) {
            getPhotoInfo();
        } else {
            initFragement();
        }
    }

    /**
     * 初始化固定的
     */
    private void initFragement() {
        String[] stringArray = getResources().getStringArray(R.array.takedata_information_item);
        resetpager();
        switch (witch) {
            case 0:
                //取件资料
                if (takeDataFragments == null) {
                    takeDataFragments = new ArrayList<>();
                    takeDataFragments.add(new TakeDataFragment());
                    takeDataFragments.add(new TakeDataFragment());
                    takeDataFragments.add(new TakeDataFragment());
                }
                fragments = takeDataFragments;
                break;
            case 1:
                //摄影资料
                if (photographicDataFragments == null) {
                    photographicDataFragments = new ArrayList<>();
                    photographicDataFragments.add(new PhotographicDataFragment());
                    photographicDataFragments.add(new PhotographicDataFragment());
                    photographicDataFragments.add(new PhotographicDataFragment());
                }
                fragments = photographicDataFragments;
                break;
            case 2:
                //选片资料
                if (selectedPictureInformationFragments == null) {
                    selectedPictureInformationFragments = new ArrayList<>();
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                    selectedPictureInformationFragments.add(new SelectedPictureInformationFragment());
                }
                fragments = selectedPictureInformationFragments;
                break;
            case 3:
                //拍照礼服(礼服资料)
                if (takePictureDressDataFragments == null) {
                    takePictureDressDataFragments = new ArrayList<>();
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                    takePictureDressDataFragments.add(new TakePictureDressFragment());
                }
                fragments = takePictureDressDataFragments;
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
