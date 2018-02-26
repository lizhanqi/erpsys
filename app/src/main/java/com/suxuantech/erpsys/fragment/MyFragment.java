package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.views.DampView;

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
            ImmersionBar.with(getActivity()).reset().statusBarDarkFont(false).navigationBarColor(R.color.themeColor).init();
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DampView dampView =view.findViewById(R.id.dampView);
        dampView.setImageView((ImageView) view.findViewById(R.id.img_top));
        ImmersionBar.with(getActivity()).reset().statusBarDarkFont(false).titleBar(R.id.tv_mine).navigationBarColor(R.color.themeColor).init();
        view.findViewById(R.id.about_Us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("三生三世");
            }
        });
    }
//    @Override
//    protected void widgetClick(View v) {
//
//    }
}
