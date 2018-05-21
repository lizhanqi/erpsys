package com.suxuantech.erpsys.ui.activity.base;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.fragment.ContactsFragment;

public class ContactsActivity extends TitleNavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ContactsFragment contactsFragment = new ContactsFragment();
        int type = getIntent().getIntExtra("type", 1);
        boolean isOption = getIntent().getBooleanExtra("isOption", false);
        String keyCode = getIntent().getStringExtra("keyCode");
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putBoolean("isOption", isOption);
        bundle.putString("keyCode", keyCode);
        contactsFragment.setArguments(bundle);
        loadRootFragment(R.id.fragment_contacts, contactsFragment);
        showUserDefinedNav();
        setTitle("通讯录");

    }

    @Override
    public void widgetClick(View v) {
        finish();
    }
}
