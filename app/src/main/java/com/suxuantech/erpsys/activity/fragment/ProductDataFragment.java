package com.suxuantech.erpsys.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.OrderDetailActivity;
import com.suxuantech.erpsys.adapter.ProductGroupAdaputer;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.yanzhenjie.fragment.NoFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 产品资料
 */
public class ProductDataFragment extends NoFragment {
    @BindView(R.id.recycler_one_product)
    SwipeMenuRecyclerView mRecyclerOneProduct;
    @BindView(R.id.recycler_two_product)
    SwipeMenuRecyclerView mRecyclerTwoProduct;
    @BindView(R.id.cb_one_all)
    CheckBox mCbOneAll;
    @BindView(R.id.cb_two_all)
    CheckBox mCbTwoAll;
    @BindView(R.id.cb_all)
    CheckBox mCbAll;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.rl_delete)
    RelativeLayout mRlDelete;
    private Unbinder unbinder;
    private ProductGroupAdaputer productGroupAdaputer2;
    private ProductGroupAdaputer productGroupAdaputer;
    public ProductDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((OrderDetailActivity) getActivity()).setUseDefinedNavRightText("1111");
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productGroupAdaputer = new ProductGroupAdaputer(getActivity(), true);
        mRecyclerOneProduct.setAdapter(productGroupAdaputer);
        //recycleview和其他view滑动冲突可以禁用
        mRecyclerOneProduct.setLayoutManager(new LinearLayoutManager(getActivity()) {
                                                 @Override
                                                 public boolean canScrollVertically() {
                                                     // 直接禁止垂直滑动
                                                     return false;
                                                 }
                                             }
        );
    productGroupAdaputer2 = new ProductGroupAdaputer(getActivity(), false);
        mRecyclerTwoProduct.setLayoutManager(new LinearLayoutManager(getActivity()) {
                                                 @Override
                                                 public boolean canScrollVertically() {
                                                     // 直接禁止垂直滑动
                                                     return false;
                                                 }
                                             }
        );
        mRecyclerTwoProduct.setAdapter(productGroupAdaputer2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    boolean isShowCheckBox;

    public void change() {
        addPackageOrProductWindow();
        isShowCheckBox = !isShowCheckBox;

        if (isShowCheckBox) {
            mRlDelete.setVisibility(View.VISIBLE);
            mCbOneAll.setVisibility(View.VISIBLE);
            mCbTwoAll.setVisibility(View.VISIBLE);
        } else {
            mRlDelete.setVisibility(View.GONE);
            mCbOneAll.setVisibility(View.GONE);
            mCbTwoAll.setVisibility(View.GONE);
        }
        productGroupAdaputer.setShowCheckBox(isShowCheckBox);
        productGroupAdaputer2.setShowCheckBox(isShowCheckBox);
    }

    private void initView(View view) {
        mCbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCbOneAll.setChecked(isChecked);
                mCbTwoAll.setChecked(isChecked);
            }
        });
        mCbOneAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productGroupAdaputer.setCheckAll(isChecked);
                productGroupAdaputer.notifyDataSetChanged();
            }
        });
        mCbTwoAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productGroupAdaputer2.setCheckAll(isChecked);
                productGroupAdaputer2.notifyDataSetChanged();
            }
        });

    }
    public  void  addPackageOrProductWindow(){
        AlertView alertView = new AlertView(null, null, null, null, null, getContext(), AlertView.Style.ACTIONSHEET, null);
        View views = getLayoutInflater().inflate(R.layout.pop_package_product_addbutton, null);
        Button bn = (Button) views.findViewById(R.id.btn_add_package);
        bn.setClickable(true);
        alertView.addExtView(views);
        alertView.setCancelable(true);
        alertView.setContentContainerMargins(0,0,0,0);
        if( ScreenUtils.checkDeviceHasNavigationBar(getContext())){
            alertView.setRootViewMarginBootom(ScreenUtils.getNavigationBarHeight(getContext()));
            alertView.show();
    }
    }


}
