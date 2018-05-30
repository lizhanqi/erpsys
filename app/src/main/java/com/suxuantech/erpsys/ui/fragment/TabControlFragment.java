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
import com.suxuantech.erpsys.entity.PaymentDetailsEntity;
import com.suxuantech.erpsys.entity.SelectPictureEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.suxuantech.erpsys.ui.fragment.TabControlFragment.whichInFragement.OPTION_PANEL;

/**
 * QuickAdapter simple {@link Fragment} subclass.
 * 可以滑动切换fragment带有
 */

public class TabControlFragment extends BaseSupportFragment {

    private ArrayList<Fragment> fragments;
    whichInFragement witch;
    private ViewPager pager;
    private TabLayout tablayout;
    private View view;
    private FragmentManager childFragmentManager;
    private DefaultFragmentAdapter defaultFragmentAdapter;

    public enum whichInFragement implements Serializable {
        SHOOT("摄影资料", 1), OPTION_PANEL("选片资料", 2), PAY_DETAILS("付款明细", 3);
        private String name;
        private int index;

        private whichInFragement(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }
    //    设置参数确定子Fragment加载

    /**
     * 0是取件资料的
     * 1是摄影资料
     * 2是选片资料
     * 3是拍照礼服(礼服资料)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        witch = (whichInFragement) getArguments().getSerializable("witch");
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
        onNewBundle(getArguments());
    }

    /**
     * 选片
     */
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

    /**
     * 摄影资料
     */
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
            if (select_order_name.isEmpty()) {
                select_order_name = "未知";
            }
            strings.add(select_order_name);
        }
        resetpager();
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        for (int i = 0; i < strings.size(); i++) {
            PhotographicDataFragment selectedPictureInformationFragment = new PhotographicDataFragment();
            Bundle bundle = new Bundle();
            bundle.putString("orderid", getArguments().getString("orderId"));
            bundle.putParcelable("data", data.get(i));
            selectedPictureInformationFragment.setArguments(bundle);
            fragments.add(selectedPictureInformationFragment);
        }
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
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        for (int i = 0; i < strings.size(); i++) {
            SelectedPictureInformationFragment selectedPictureInformationFragment = new SelectedPictureInformationFragment();
            Bundle bundle = new Bundle();
            bundle.putString("orderid", getArguments().getString("orderId"));
            bundle.putParcelable("data", data.get(i));
            selectedPictureInformationFragment.setArguments(bundle);
            fragments.add(selectedPictureInformationFragment);
        }
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
        if (childFragmentManager == null) {
            childFragmentManager = getChildFragmentManager();
        }
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
        setArguments(newBundle);
        witch = (whichInFragement) newBundle.getSerializable("witch");
        if (witch == OPTION_PANEL) {
            getSelect();
        } else if (witch == whichInFragement.SHOOT) {
            getPhotoInfo();
        } else if (witch == whichInFragement.PAY_DETAILS) {
            getPayDetails();
        }
    }

    /**
     * 初始化网络控制Fragement
     *
     * @param data
     */
    private void initPaymentFragment(PaymentDetailsEntity.DataBean data) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("门市收款");
        strings.add("礼服收款");
        strings.add("化妆收款");
        resetpager();
        //选片资料
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        for (int i = 0; i < strings.size(); i++) {
            PaymentDetailFragment paymentDetailFragment = new PaymentDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("orderId", getArguments().getString("orderId"));
            bundle.putSerializable("data", data);
            paymentDetailFragment.setArguments(bundle);
            fragments.add(paymentDetailFragment);
        }
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
     * 订单付款详情
     */
    public void getPayDetails() {
        if (!App.getApplication().hasPermission("J2")) {
            ToastUtils.snackbarShort("无权限查看", "确定");
            return;
        }
        String url = Contact.getFullUrl(Contact.PAYMENT, Contact.TOKEN, getArguments().getString("orderId"), App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<PaymentDetailsEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PaymentDetailsEntity>(url, RequestMethod.POST, PaymentDetailsEntity.class);
        HttpListener<PaymentDetailsEntity> searchByCustmor = new HttpListener<PaymentDetailsEntity>() {
            @Override
            public void onSucceed(int what, Response<PaymentDetailsEntity> response) {
                if (response.get().isOK()) {
                    initPaymentFragment(response.get().getData());
                }
            }

            @Override
            public void onFailed(int what, Response<PaymentDetailsEntity> response) {
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
}
