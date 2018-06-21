package com.suxuantech.erpsys.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.ConversationListFragment;
import com.suxuantech.erpsys.chat.GroupActivity;
import com.suxuantech.erpsys.chat.dummy.DummyContent;
import com.suxuantech.erpsys.rongim.RongConversationListFragment;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.ConversationListAdapterEx;
import com.suxuantech.erpsys.ui.dialog.LoadDialog;
import com.suxuantech.erpsys.ui.fragment.CRMFragment;
import com.suxuantech.erpsys.ui.fragment.ContactsFragment;
import com.suxuantech.erpsys.ui.fragment.ERPLeftFragment;
import com.suxuantech.erpsys.ui.fragment.JGConversationListFragment;
import com.suxuantech.erpsys.ui.fragment.MsgFragment;
import com.suxuantech.erpsys.ui.fragment.MyFragment;
import com.suxuantech.erpsys.ui.fragment.WorkFragment;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

public class MainActivity extends TitleNavigationActivity implements IUnReadMessageObserver, com.suxuantech.erpsys.chat.ConversationListFragment.OnListFragmentInteractionListener {
    private BottomNavigationBar bottomNavigationBar;
    private long mExitTime = 0;
    private TextBadgeItem badgeItem;
    private BottomNavigationItem msgItem;
    private ContactsFragment contactsFragment;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    // private MsgFragment msgFragment;
    private MyFragment myFragment;
    private ERPLeftFragment erpFragment;
    private WorkFragment workFragment;
    private CRMFragment crmFragment;
    private PopupWindow mMenuPopWindow;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    private SupportFragment[] mFragments = new SupportFragment[5];
    /**
     * 会话列表的fragment
     */
    private Fragment mConversationListFragment = null;
    boolean isRongIM = false;

    /**
     * 导航栏点击切换的事件
     */
    BottomNavigationBar.OnTabSelectedListener onTabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
        }

        @Override
        public void onTabUnselected(int position) {
            selectFragment(position);
        }

        @Override
        public void onTabReselected(int position) {
            final SupportFragment currentFragment = mFragments[position];
            int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
            // 如果不在该类别Fragment的主页,则回到主页;
            if (count > 1) {
                if (currentFragment instanceof MsgFragment) {
                    currentFragment.popToChild(MsgFragment.class, false);
                } else if (currentFragment instanceof ContactsFragment) {
                    for (int i = 0; i < count - 1; i++) {
                        currentFragment.popChild();
                    }
                } else if (currentFragment instanceof ERPLeftFragment) {
                    currentFragment.popToChild(ERPLeftFragment.class, false);
                } else if (currentFragment instanceof MyFragment) {
                    currentFragment.popToChild(MyFragment.class, false);
                }
                return;
            }
        }
    };

    private void selectFragment(int lastSelct) {
        int position = bottomNavigationBar.getCurrentSelectedPosition();
        switch (position) {
            case 0:
                supportToolbar(false);
                getToolbar().setBackgroundColor(getResources().getColor(R.color.white));
                setTitle("");
                setCenterTitle("我的会话");
                getLineView().setVisibility(View.VISIBLE);
                break;
            case 1:
                supportToolbar(false);
                setTitle("");
                getToolbar().setBackgroundColor(getResources().getColor(R.color.white));
                setCenterTitle("联系人");
                getLineView().setVisibility(View.GONE);
                break;
            default:
            case 2:
                getLineView().setVisibility(View.GONE);
                getToolbar().setVisibility(View.GONE);
                break;
            case 3:
                getLineView().setVisibility(View.GONE);
                getToolbar().setVisibility(View.GONE);
                break;
        }
        showHideFragment(mFragments[bottomNavigationBar.getCurrentSelectedPosition()], mFragments[lastSelct]);
        //这里如果是沉浸到顶部的就不要走这段了,不然,状态栏白色几百毫秒,体验差
        if (bottomNavigationBar.getCurrentSelectedPosition() != 2 && bottomNavigationBar.getCurrentSelectedPosition() != 3) {
            ImmersionBar.with(MainActivity.this).reset().init();
            ImmersionBar.with(MainActivity.this).fitsSystemWindows(true).barColor(R.color.white).statusBarDarkFont(true, 0.15f).navigationBarColor(R.color.mainNavline_e7).init();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dLog(System.currentTimeMillis() + "");
        // requstPermissions(0,Manifest.permission.SYSTEM_ALERT_WINDOW);
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
//       useEventBus();
        setContentView(R.layout.activity_main);
        initPop();
        initFragement();
        //   selectedFragment(2);
        initMyBottomNavigation();
        getLineView().setVisibility(View.GONE);
        initData();
    }

    /**
     * 初始化弹窗
     */
    private void initPop() {
        View mMenuView = getLayoutInflater().inflate(R.layout.drop_down_menu, null);
        mMenuView.findViewById(R.id.send_message_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("单聊");
            }
        });
        mMenuView.findViewById(R.id.create_group_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GroupActivity.class));
                mMenuPopWindow.dismiss();
                ToastUtils.showShort("群聊");
            }
        });
        mMenuView.findViewById(R.id.ll_saoYiSao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("扫一扫");
            }
        });
        mMenuPopWindow = new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);

    }

    private void initFragement() {
        SupportFragment firstFragment = SupportHelper.findFragment(getSupportFragmentManager(), ERPLeftFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = new ConversationListFragment();
            mFragments[SECOND] = new ContactsFragment();
            Bundle bundle = getBundle();
            mFragments[SECOND].setArguments(bundle);
            mFragments[THIRD] = new ERPLeftFragment();
            mFragments[FOUR] = new MyFragment();
            loadMultipleRootFragment(R.id.main_content, THIRD,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = SupportHelper.findFragment(getSupportFragmentManager(), MsgFragment.class);
            mFragments[SECOND] = SupportHelper.findFragment(getSupportFragmentManager(), ContactsFragment.class);
            Bundle bundle = getBundle();
            mFragments[SECOND].setArguments(bundle);
            mFragments[THIRD] = SupportHelper.findFragment(getSupportFragmentManager(), ERPLeftFragment.class);
            mFragments[FOUR] = SupportHelper.findFragment(getSupportFragmentManager(), MyFragment.class);
            showHideFragment(mFragments[2]);
        }

    }

    @NonNull
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOption", false);
        bundle.putBoolean("fastEntrance", true);
        bundle.putBoolean("showDepartmentName", true);
        if (StringUtils.empty(App.getApplication().getUserInfor().getDepartment_name())) {
            if (StringUtils.empty(App.getApplication().getUserInfor().getShop_name())) {
                if (StringUtils.empty(App.getApplication().getUserInfor().getBrandclass())) {
                    //仅仅获取集团联系人
                    bundle.putInt("type", 7);
                    bundle.putString("homeDepartmentName", "集团联系人");
                    bundle.putString("keyCode", "");
                } else {
                    //仅仅获取事业部联系人
                    bundle.putInt("type", 6);
                    bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getBrandclass());
                    bundle.putString("keyCode", App.getApplication().getUserInfor().getBrandclass_id() + "");
                }
            } else {
                //仅仅获取点面联系人
                bundle.putInt("type", 5);
                bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getShop_name());
                bundle.putString("keyCode", App.getApplication().getUserInfor().getShop_code() + "");
            }
        } else {
            //仅仅获取部门联系人
            bundle.putInt("type", 4);
            bundle.putString("homeDepartmentName", App.getApplication().getUserInfor().getDepartment_name());
            bundle.putString("keyCode", App.getApplication().getUserInfor().getDepartment_id() + "");
        }
        return bundle;
    }

    /**
     * 发起会话弹窗
     */
    public void showPopWindow() {
        mMenuPopWindow.setTouchable(true);
        mMenuPopWindow.setOutsideTouchable(true);
        mMenuPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (mMenuPopWindow.isShowing()) {
            mMenuPopWindow.dismiss();
        } else {
            mMenuPopWindow.showAsDropDown(getNavRightView(), -10, -5);
        }
    }

    private Fragment initConversationList() {
        if (isRongIM) {
            return initRongIMConversationListFragement();
        } else {
            return initJGIMConversationListFragement();
        }
    }

    private Fragment initJGIMConversationListFragement() {
        if (mConversationListFragment == null) {
            JGConversationListFragment listFrment = new JGConversationListFragment();
            mConversationListFragment = listFrment;
            return listFrment;
        } else {
            return mConversationListFragment;
        }
    }

    private Fragment initRongIMConversationListFragement() {
        if (mConversationListFragment == null) {
            RongConversationListFragment listFragment = new RongConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            Conversation.ConversationType[] mConversationsTypes = null;
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
    public void initImmersionBar() {
//        super.initImmersionBar();
    }

    //-----------方式一------
    private void selectedFragment(int position) {
        if (position != 0) {
            getNavRightView().setVisibility(View.GONE);
        }
        if (position == 2 || position == 4) {
            hideUserDefinedNav();
        } else {
            showUserDefinedNav();
            setUseDefinedNavLeftDrawable(null);
            getHeadNavUseDefinedRootView().setBackground(null);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            default:
                break;
            case 0:
                if (mConversationListFragment == null) {
                    mConversationListFragment = initConversationList();
                    transaction.add(R.id.main_content, mConversationListFragment);
                } else {
                    transaction.show(mConversationListFragment);
                }
                setTitle("我的会话");
                setUseDefinedNavRightDrawable(getResources().getDrawable(R.drawable.icon_add));
                getNavRightView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPopWindow();
                    }
                });
                supportToolbar();
                getToolbar().setTitle("我的会话");
                getToolbar().setBackground(null);
                break;
            case 1:
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    transaction.add(R.id.main_content, workFragment);
                } else {
                    transaction.show(workFragment);
                }
                setTitle("OA办公");
                break;

            case 3:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isOption", false);
                bundle.putInt("type", 4);
                bundle.putBoolean("fastEntrance", true);
                bundle.putString("keyCode", App.getApplication().getUserInfor().department_id + "");
                if (contactsFragment == null) {
                    contactsFragment = new ContactsFragment();
                    contactsFragment.setArguments(bundle);
                    transaction.add(R.id.main_content, contactsFragment);
                } else {
                    contactsFragment.setArguments(bundle);
                    transaction.show(contactsFragment);
                }
                setTitle("联系人");
                break;

            case 2:
                if (erpFragment == null) {
                    erpFragment = new ERPLeftFragment();
                    transaction.add(R.id.main_content, erpFragment);
                } else {
                    transaction.show(erpFragment);
                }
                break;
            case 10:
                if (crmFragment == null) {
                    crmFragment = new CRMFragment();
                    transaction.add(R.id.main_content, crmFragment);
                } else {
                    transaction.show(crmFragment);
                }
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.main_content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
//        if (mConversationListFragment != null) {
//            transaction.hide(mConversationListFragment);
//        }
//        if (workFragment != null) {
//            transaction.hide(workFragment);
//        }
//        if (erpFragment != null) {
//            transaction.hide(erpFragment);
//        }
//        if (contactsFragment != null) {
//            transaction.hide(contactsFragment);
//        }
//        if (myFragment != null) {
//            transaction.hide(myFragment);
//        }
    }
    //-----------方式一end------

    /**
     * 初始化页面的导航
     */
    private void initMyBottomNavigation() {
        badgeItem = new TextBadgeItem().setBorderWidth(1).setBackgroundColorResource(R.color.msgColor).setText("2").setHideOnSelect(true);
        msgItem = new BottomNavigationItem(R.drawable.icon_msg_pressed, getString(R.string.msg));
        msgItem.setBadgeItem(badgeItem);
        msgItem.setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.icon_msg_normal));//非选中的图片
        msgItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        msgItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem workItem = new BottomNavigationItem(R.drawable.icon_work_pressed, getString(R.string.work));
        workItem.setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.icon_work_normal));//非选中的图片
        workItem.setInActiveColor(getResources().getColor(R.color.mainNav_66));
        workItem.setActiveColor(getResources().getColor(R.color.themeColor));
        BottomNavigationItem erpItem = new BottomNavigationItem(R.drawable.icon_erp_pressed, getString(R.string.erp));
        erpItem.setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.icon_erp_normal));//非选中的图片
        BottomNavigationItem contactItem = new BottomNavigationItem(R.drawable.icon_contact_pressed, "通讯录");
        contactItem.setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.icon_contact_normal));//非选中的图片
        BottomNavigationItem myItem = new BottomNavigationItem(R.drawable.icon_my_pressed, getString(R.string.my));
        myItem.setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.icon_my_normal));//非选中的图片
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(msgItem)
                .addItem(contactItem)
                .addItem(erpItem)
                .addItem(myItem)
                .setFirstSelectedPosition(2)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(onTabSelectedListener);
        badgeItem.hide();
        //startFragment(ERPFragment.class);
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
        if (badgeItem != null) {
            if (i <= 0) {
                badgeItem.setText(i + "");
            } else if (i < 99) {
                badgeItem.setText("99+");
            } else {
                badgeItem.setText(null);
            }
        }
    }

    @Override
    public void onListFragmentInteraction(@NotNull DummyContent.DummyItem item) {
    }

    @Override
    public void onBackPressedSupport() {
        int old = bottomNavigationBar.getCurrentSelectedPosition();
        if (old == 2) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                pop();
            } else {
                closeActivity();
            }
        } else {
            bottomNavigationBar.selectTab(2);
            selectFragment(old);
        }
    }

    /**
     * 点击两次返回键退出APP
     */
    private void closeActivity() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showInCenterShort("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityCompat.finishAfterTransition(this);
            //App.getApplication().loginOut();
            App.appExit();
        }
    }
}

