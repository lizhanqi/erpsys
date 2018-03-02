package com.suxuantech.erpsys.activity.base;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.fragment.ContactsFragment;

import java.util.List;

public class ContactsActivity extends ImmersedBaseActivity {

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        loadRootFragment(R.id.fragment_contacts,new ContactsFragment());
        showUserDefinedNav();
        setTitle("通讯录");
    }

    @Override
    protected void widgetClick(View v) {
        finish();
    }
}