package com.suxuantech.erpsys.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.adapter.ConversationListAdapterEx;
import com.suxuantech.erpsys.bean.DistrictBean;
import com.suxuantech.erpsys.dialog.LoadDialog;
import com.suxuantech.erpsys.fragment.CRMFragment;
import com.suxuantech.erpsys.fragment.ContactsFragment;
import com.suxuantech.erpsys.fragment.ERPFragment;
import com.suxuantech.erpsys.fragment.MsgFragment;
import com.suxuantech.erpsys.fragment.MyFragment;
import com.suxuantech.erpsys.fragment.WorkFragment;
import com.suxuantech.erpsys.nohttp.DownLoad;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.nohttp.StringRequest;
import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

public class MainActivity extends ImmersedBaseActivity implements IUnReadMessageObserver {
    private BottomNavigationBar bottomNavigationBar;
    private long mExitTime = 0;
    private ArrayList<Fragment> fragments =new ArrayList<>();
   // private MsgFragment msgFragment;
    private MyFragment myFragment;
    private ERPFragment erpFragment;
    private WorkFragment workFragment;
    private CRMFragment crmFragment;
    /**
     * 导航栏点击切换的事件
     */
    BottomNavigationBar.OnTabSelectedListener onTabSelectedListener=new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
        }
        @Override
        public void onTabUnselected(int position) {
            if(position==bottomNavigationBar.getCurrentSelectedPosition()){
            return;
            }
            dLog(position+"当前"+            bottomNavigationBar.getCurrentSelectedPosition());
            selectedFragment( bottomNavigationBar.getCurrentSelectedPosition());
          //  showHideFragment(mFragments[bottomNavigationBar.getCurrentSelectedPosition()]);
            if (   bottomNavigationBar.getCurrentSelectedPosition()!=2&&bottomNavigationBar.getCurrentSelectedPosition()!=4){
                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true,0.15f).fitsSystemWindows(true).statusBarColor(R.color.white).navigationBarColor(R.color.translucent_black_90).init();
            }
        }
        @Override
        public void onTabReselected(int position) {
            dLog("再选");
        }
    };
    private TextBadgeItem badgeItem;
    private BottomNavigationItem msgItem;
    private ContactsFragment contactsFragment;

    /**
     * 监听返回键 点击2次退出--
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closeActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 点击两次返回键退出APP
     */
    private void closeActivity() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showInCenter("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requstPermissions(0,Manifest.permission.SYSTEM_ALERT_WINDOW);
        super.onCreate(savedInstanceState);
       setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
        initMyBottomNavigation();
        selectedFragment(2);
        ImmersionBar.with(MainActivity.this).navigationBarColor(R.color.translucent_black_90).init();
       // initFragement();
        initData();
    }
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    //public static final int FIVE = 4;
    private SupportFragment[] mFragments = new SupportFragment[5];
       private  void initFragement(){
            SupportFragment firstFragment = SupportHelper.findFragment(getSupportFragmentManager(), ERPFragment.class);
//           initConversationList();
            if (firstFragment == null) {
                mFragments[FIRST] =new  MsgFragment();
                mFragments[SECOND] = new  WorkFragment();
                mFragments[THIRD] = new ERPFragment();
               // mFragments[FOUR] = new CRMFragment();
                mFragments[FOUR] = new MyFragment();
                loadMultipleRootFragment(R.id.main_content, FIRST,
                        mFragments[FIRST],
                        mFragments[SECOND],
                        mFragments[THIRD],
                        mFragments[FOUR]);
            } else {
                // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
                // 这里我们需要拿到mFragments的引用
                mFragments[FIRST] = firstFragment;
                mFragments[SECOND] =SupportHelper.findFragment(getSupportFragmentManager(),WorkFragment.class);
                mFragments[THIRD] = SupportHelper.findFragment(getSupportFragmentManager(),ERPFragment.class);
                //mFragments[FOUR] = SupportHelper.findFragment(getSupportFragmentManager(),CRMFragment.class);
                mFragments[FOUR] = SupportHelper.findFragment(getSupportFragmentManager(),MyFragment.class);
            }
            showHideFragment(mFragments[2]);
        }

    /**
     * 会话列表的fragment
     */
    private ConversationListFragment mConversationListFragment = null;
    private Conversation.ConversationType[] mConversationsTypes = null;
    private ConversationListFragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (App.ISDEBUG) {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    /**
     * 重写,禁用统一的
     */
    @Override
    protected void initImmersionBar() {
//        super.initImmersionBar();
    }

    //-----------方式一------
    @SuppressLint("ResourceAsColor")
    private void selectedFragment(int position) {
//        if (position!=0){
//            hideToolbar();
//        }
        if (position==2||position==4) {
            hideUserDefinedNav();
        }else {
            showUserDefinedNav();
            setUseDefinedNavLeftDrawable(null);
            getHeadNavUseDefinedRootView().setBackground(null);
        }
         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (mConversationListFragment == null) {
                    mConversationListFragment = initConversationList();
                    transaction.add(R.id.main_content, mConversationListFragment);
                } else {
                    transaction.show(mConversationListFragment);
                }

                setTitle("我的会话");
//                setSupportToolbar();
//                getToolbar().setTitle("我的会话");
//                getToolbar().setBackground(null);
                break;
            case 1:
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    transaction.add(R.id.main_content, workFragment);
                } else
                    transaction.show(workFragment);
                setTitle("OA办公");
                break;

            case 3:
                if ( contactsFragment== null) {
                contactsFragment = new ContactsFragment();
                transaction.add(R.id.main_content, contactsFragment);
                } else
                    transaction.show(contactsFragment);
                setTitle("联系人");
                break;

            case 2:
                if (erpFragment == null) {
                    erpFragment = new ERPFragment();
                    transaction.add(R.id.main_content, erpFragment);
                } else
                    transaction.show(erpFragment);
                break;
            case 10:
                if (crmFragment == null) {
                    crmFragment = new CRMFragment();
                    transaction.add(R.id.main_content, crmFragment);
                } else

                    transaction.show(crmFragment);
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.main_content, myFragment);
                } else
                    transaction.show(myFragment);
                break;

        }
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction) {
        if (mConversationListFragment != null)
            transaction.hide(mConversationListFragment);
        if (workFragment != null)
            transaction.hide(workFragment);
        if (erpFragment != null)
            transaction.hide(erpFragment);
        if (contactsFragment != null)
            transaction.hide(contactsFragment);
        if (myFragment != null)
            transaction.hide(myFragment);
    }
    //-----------方式一end------
    /**
     * 初始化页面的导航
     */
    private void initMyBottomNavigation() {
        badgeItem = new TextBadgeItem().setBorderWidth(1).setBackgroundColorResource(R.color.msgColor).setText("2").setHideOnSelect(true);
        msgItem = new BottomNavigationItem(R.drawable.icon_msg_pressed, getString(R.string.msg));
        msgItem.setBadgeItem(badgeItem);
        msgItem.setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_msg_normal));//非选中的图片
        msgItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        msgItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem workItem = new BottomNavigationItem(R.drawable.icon_work_pressed, getString(R.string.work));
        workItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_work_normal));//非选中的图片
        workItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        workItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem erpItem = new BottomNavigationItem(R.drawable.icon_erp_pressed, getString(R.string.erp));
        erpItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_erp_normal));//非选中的图片
           BottomNavigationItem contactItem = new BottomNavigationItem(R.drawable.icon_contact_pressed, getString(R.string.crm));
        contactItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_contact_normal));//非选中的图片
        BottomNavigationItem myItem = new BottomNavigationItem(R.drawable.icon_my_pressed, getString(R.string.my));
        myItem .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.icon_my_normal));//非选中的图片
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(msgItem)
                .addItem(workItem)
                .addItem(erpItem)
               .addItem(contactItem)
                .addItem(myItem)
                .setFirstSelectedPosition(2)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(onTabSelectedListener);
        //startFragment(ERPFragment.class);
    }
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            default:
        }
    }
    //请求权限后结果
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
    public  void netsBeanSample() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体         // STOPSHIP: 2018/2/23 0023
        JavaBeanRequest<DistrictBean> districtBeanJavaBeanRequest = new JavaBeanRequest<DistrictBean>(url,DistrictBean.class);
//        HttpResponseListener<DistrictBean> districtBeanHttpResponseListener = new HttpResponseListener<DistrictBean>(null);
        HttpListener<DistrictBean> searchByCustmor = new HttpListener<DistrictBean>(){
            @Override
            public void onSucceed(int what, Response<DistrictBean> response) {
                L.i("what = [" + what + "], response = [" + response + "]");
            }
            @Override
            public void onFailed(int what, Response<DistrictBean> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
        //CallServer.getInstance().add(this, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }
    public void Down(){
        String u="http://sw.bos.baidu.com/sw-search-sp/software/e25c4cc36a934/QQ_8.9.6.22427_setup.exe";
        DownLoad downLoad = new DownLoad(10, u);

    }
        public  void netsStringSample() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体
       StringRequest districtBeanJavaBeanRequest = new StringRequest(url,RequestMethod.POST);
        HttpListener<String> searchByCustmor = new HttpListener<String>(){
            @Override
            public void onSucceed(int what, Response<String> response) {
                L.i("what = [" + what + "], response = [" + response + "]");
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
//        new  CallServer().setQueue();
     //   CallServer.getInstance().add(this, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }


    public void send() {
        Request<String> stringRequest = NoHttp.createStringRequest("http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0", RequestMethod.POST);
        // stringRequest.addHeader("Content-Type", "application/json");
        //stringRequest.setDefineRequestBodyForJson("{\"x\":1,\"y\":2}");
/*        stringRequest.add("x",1);
        stringRequest.add("y",4);*/
        RequestQueue requestQueueInstance = NoHttp.getRequestQueueInstance();
        requestQueueInstance.add(0, stringRequest, new SimpleResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        super.onStart(what);
                    }
                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                        L.d("NoHttpSample",response.get());
                    }
                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                    }
                }
        );
    }

    protected void initData() {
        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
    }

    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {
            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");
            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {
                    if (conversation != null) {
                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
                            //startActivity(new Intent(MainActivity.this, NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {
                }
            });
        }
    }

    /**
     * 得到不落地 push 消息
     */
    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        LoadDialog.show(this);
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                LoadDialog.dismiss(MainActivity.this);
                            }

                            @Override
                            public void onSuccess(String s) {
                                LoadDialog.dismiss(MainActivity.this);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                LoadDialog.dismiss(MainActivity.this);
                            }
                        });
                    }
                }
            }
        }
    }
    @Override
    public void onCountChanged(int i) {
        if (badgeItem!=null){
            if (i<=0){
                badgeItem.setText(i+"");
            }else if (i<99){
                badgeItem.setText("99+");
            }else {
                badgeItem.setText(null);
            }
        }
    }
}

