package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suxuantech.erpsys.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 联系人的容器
 */
public class ContactsFragment extends BaseSupportFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contancts_root, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportFragment contactDataFragment = findChildFragment(ContactDataFragment.class);
        if (contactDataFragment == null) {
            contactDataFragment = new ContactDataFragment();
            Bundle arguments = getArguments();
            contactDataFragment.setArguments(arguments);
        } else {
            Bundle arguments = getArguments();
            contactDataFragment.setArguments(arguments);
        }
        loadRootFragment(R.id.rl_fragment_contanct_root, contactDataFragment, true, false);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        SupportFragment contactDataFragment = findChildFragment(ContactDataFragment.class);
        if (contactDataFragment!=null){
            Log.d("用户切换","更新2");
            Bundle arguments = getArguments();
            Log.d("用户切换","更新2type"+         getArguments().getInt("type", 100000) );
            Log.d("用户切换","更新2keyCode"+         getArguments().getString("keyCode", "") );
            setArguments(newBundle);
            contactDataFragment.putNewBundle(arguments);
        }
    }
}


