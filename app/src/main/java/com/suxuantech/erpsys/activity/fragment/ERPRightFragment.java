package com.suxuantech.erpsys.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.yanzhenjie.statusview.StatusUtils;

import java.util.ArrayList;


public class ERPRightFragment extends Fragment {
    TextView mTvFm;
    ArrayList<Fragment> fragmentrs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_erp_right, container, false);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StatusUtils.setStatusBarColor(getActivity(),getResources().getColor(R.color.translucent_black_80));
        StatusUtils.setStatusBarDarkFont(getActivity(),true);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
