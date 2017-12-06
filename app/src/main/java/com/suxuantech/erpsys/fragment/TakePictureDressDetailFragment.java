package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;
import com.yanzhenjie.fragment.NoFragment;

/**
 *拍照礼服(礼服资料)详情
 */
public class TakePictureDressDetailFragment extends NoFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_picture_dress_detail, container, false);
        return view;
    }
}
