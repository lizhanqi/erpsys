package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;

import me.yokeyword.fragmentation.SupportFragment;


public class ContactsContainFragment extends BaseSupportFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contancts_root, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportFragment contentFragment = findChildFragment(ContactDataFragment.class);
        if (contentFragment == null) {
            ContactDataFragment contactDataFragment = new ContactDataFragment();
            Bundle arguments = getArguments();
            contactDataFragment.setArguments(arguments);
            loadRootFragment(R.id.rl_fragment_contanct_root,contactDataFragment , true, false);
        }
    }
}


