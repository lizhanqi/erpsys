package com.suxuantech.erpsys.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;
import com.yanzhenjie.fragment.NoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInformationFragment extends NoFragment {


    public CustomerInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_information, container, false);
    }

}
