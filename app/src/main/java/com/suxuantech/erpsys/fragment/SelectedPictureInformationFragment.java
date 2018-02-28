package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 *选片
 */
public class SelectedPictureInformationFragment extends SupportFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seleced_picture, container, false);
    }
}
