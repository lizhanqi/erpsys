package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;

import me.yokeyword.fragmentation.SupportFragment;


public class ContactsFragment extends SupportFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_contacts, container, false);
        return view;
    }


}
