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
        loadRootFragment(R.id.fragment_contacts,new ContactsFragment());
        showUserDefinedNav();
        setTitle("通讯录");
    }

    @Override
    public void widgetClick(View v) {
        finish();
    }
}
