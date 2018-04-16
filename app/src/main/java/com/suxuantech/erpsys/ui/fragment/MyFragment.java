package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.LoginActivity;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.ui.widget.BounceScrollView;

import me.yokeyword.fragmentation.SupportFragment;

public class MyFragment extends SupportFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ImmersionBar.with(getActivity()).reset().navigationBarColor(R.color.translucent_black_90).statusBarDarkFont(false).init();
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BounceScrollView dampView =view.findViewById(R.id.dampView);
        dampView.setImageView(view.findViewById(R.id.img_top));
        ImmersionBar.with(getActivity()).reset().statusBarDarkFont(false).titleBar(R.id.tv_mine).navigationBarColor(R.color.themeColor).init();
        view.findViewById(R.id.about_Us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("三生三世");
            }
        });
        view.findViewById(R.id.btn_login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
//    @Override
//    protected void widgetClick(View v) {
//
//    }
}
