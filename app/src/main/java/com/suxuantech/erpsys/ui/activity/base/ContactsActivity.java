package com.suxuantech.erpsys.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.StaffSearchEntity;
import com.suxuantech.erpsys.ui.fragment.ContactsFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashSet;

public class ContactsActivity extends TitleNavigationActivity   {
    private static HashSet<StaffSearchEntity.DataBean> staffData = new HashSet<StaffSearchEntity.DataBean>();
    public static String KEY = "FLASH_MENU";
   public static void  addChecke(StaffSearchEntity.DataBean da){
       if (da== null) {
           return  ;
       }
       staffData.add(da);
    }
    public static boolean isChecked(  StaffSearchEntity.DataBean da){
        if (da== null) {
            return false;
        }
        for (StaffSearchEntity.DataBean data:staffData){
            if (   da.getId() ==data.getId() ) {
                return true;
            }
        }
        return false;
    }
    public static void   remove(@NonNull StaffSearchEntity.DataBean da){
        for (StaffSearchEntity.DataBean data:staffData){
            if (   da.getId() ==data.getId() ) {
            remove(data);
            }
        }
    }
    @Subscribe
    public void menuManage(String key) {
        if (key.equals(KEY)) {
            Bundle extras = getIntent().getExtras();
            boolean isOption = extras.getBoolean("isOption", false);
            if (isOption) {
                MenuItem clean = menu.findItem(0);
                MenuItem finsh = menu.findItem(1);
                if (finsh == null) {
                    menu.add(Menu.NONE, 1, 0, "完成")
                            .setOnMenuItemClickListener(menuItem -> {
                                finish();
                                return true;
                            })
                            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
                if (clean == null && staffData.size() > 0) {
                    menu.add(Menu.NONE, 0, 0, "清空已选")
                            .setOnMenuItemClickListener(menuItem -> {
                                return true;
                            })
                            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
                if (staffData.size() == 0) {
                    menu.removeItem(0);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ContactsFragment contactsFragment = new ContactsFragment();
        useEventBus();
        Bundle extras = getIntent().getExtras();
//        int type = getIntent().getIntExtra("type", 1);
//        boolean isOption = getIntent().getBooleanExtra("isOption", false);
//        boolean fastEntrance = getIntent().getBooleanExtra("fastEntrance", false);
//        String keyCode = getIntent().getStringExtra("keyCode");
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", type);
//        bundle.putBoolean("isOption", isOption);
//        bundle.putString("keyCode", keyCode);
//        bundle.putBoolean("fastEntrance", fastEntrance);
        contactsFragment.setArguments(extras);
        loadRootFragment(R.id.fragment_contacts, contactsFragment);
        // showUserDefinedNav();
        supportToolbar();
        setTitle("通讯录");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menuManage(KEY);
        return  true;
    }

    @Override
    public void widgetClick(View v) {
        finish();
    }
}
