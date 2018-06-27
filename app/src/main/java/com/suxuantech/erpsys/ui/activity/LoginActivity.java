package com.suxuantech.erpsys.ui.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.SuxuanAppIdKt;
import com.suxuantech.erpsys.chat.DialogCreator;
import com.suxuantech.erpsys.entity.CompanyDomainEntity;
import com.suxuantech.erpsys.entity.LoginEntity;
import com.suxuantech.erpsys.entity.PHPLoginEntity;
import com.suxuantech.erpsys.entity.PermissionEntity;
import com.suxuantech.erpsys.entity.UserEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.utils.AppUtil;
import com.suxuantech.erpsys.utils.FastJsonUtils;
import com.suxuantech.erpsys.utils.GroupByKt;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.utils.core.FingerprintCore;
import com.suxuantech.erpsys.utils.core.KeyguardLockScreenManager;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import io.rong.eventbus.EventBus;

import static android.Manifest.permission.READ_CONTACTS;


/**
 * http://www.freebuf.com/
 * _.._        ,------------.
 * ,'      `.    ( We want you! )
 * /  __) __` \    `-,----------'
 * (  (`-`(-')  ) _.-'
 * /)  \  = /  (
 * /'    |--' .  \
 * (  ,---|  `-.)__`
 * )(  `-.,--'   _`-.
 * '/,'          (  Uu",
 * (_       ,    `/,-' )
 * `.__,  : `-'/  /`--'
 * |     `--'  |
 * `   `-._   /
 * \        (
 * /\ .      \.  freebuf
 * / |` \     ,-\
 * /  \| .)   /   \
 * ( ,'|\    ,'     :
 * | \,`.`--"/      }
 * `,'    \  |,'    /
 * / "-._   `-/      |
 * "-.   "-.,'|     ;
 * /        _/["---'""]
 * :        /  |"-     '
 * '           |      /
 * `      |
 */

/**
 * QuickAdapter login screen that offers login via email/password.
 */
public class LoginActivity extends TitleNavigationActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    /**
     * 企业id
     */
    public static final String COMPANY_ID = "company_id";
    /**
     * 登录的用户名
     */
    public static final String LOGIN_NAME = "name";
    /**
     * 用户密码
     */
    public static final String LOGIN_PASSWORD = "word";
    /**
     * Id身份读取联系人请求许可。;
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView copyRight;
    private EditText mCompanyID;
    private Button mEmailSignInButton;
    Dialog loadingDialog;
    FingerprintCore mFingerprintCore;
    KeyguardLockScreenManager mKeyguardLockScreenManager;
    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            mCompanyID.setText("111");
            mEmailView.setText("小飞");
            //    login("小飞", "0");
            toastShort("指纹识别成功");
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            toastShort("指纹识别失败,请重试" + helpId);
            startFingerprintRecognition();
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            toastShort("指纹识别错误" + errMsgId);
            startFingerprintRecognition();
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {
            toastShort("指纹识别结果" + isSuccess);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingDialog = DialogCreator.createLoadingDialog(LoginActivity.this, "登录中ing...");
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        // initFingerprintCore();
        setContentView(R.layout.activity_login);
        copyRight = idGetView(R.id.copyright);
        copyRight.setText(getString(R.string.copyright) + " V" + AppUtil.getVersionName(this));
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = findViewById(R.id.password);
        mCompanyID = findViewById(R.id.company_id);
        mCompanyID.setOnTouchListener((view, event) -> {
            // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
            Drawable drawable = mCompanyID.getCompoundDrawables()[2];
            //如果右边没有图片，不再处理
            if (drawable == null) {
                return false;
            }
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP) {
                return false;
            }
            if (event.getX() > mCompanyID.getWidth() - mCompanyID.getPaddingRight() - drawable.getIntrinsicWidth()) {
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 222);
//                Intent intent = new Intent(this, QRCodeScanActivity.class);
//                startActivityForResult(intent, 222);
            }
            return false;
        });
        String cacheID = SPUtils.getInstance().getString(COMPANY_ID);
        if (!StringUtils.empty(cacheID)) {
            mCompanyID.setText(cacheID);
            mCompanyID.setSelection(cacheID.length());
            mEmailView.requestFocus();
            getCompanyDomainById(cacheID, true);
        } else {
            mCompanyID.requestFocus();
        }
        mCompanyID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 10) {
                    getCompanyDomainById(editable.toString(), false);
                }
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WebActivityConfig webActivityConfig = new WebActivityConfig(LoginActivity.this);
//                String url = "http://www.suxuantech.com/col.jsp?id=106";
//                url = "http://demo.rockoa.com/?m=index&d=we";
//                webActivityConfig.loadUrl(url).showToolbar(false);
//                webActivityConfig.start();
                attemptLogin();
            }
        });
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).navigationBarColor(R.color.mainNavline_e7).titleBar(findViewById(R.id.rl_root_login)).statusBarDarkFont(true).init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横向
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = 20;
            layoutParams.bottomMargin = 20;
            copyRight.setLayoutParams(layoutParams);
            copyRight.getParent().requestLayout();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = (int) getResources().getDimension(R.dimen.px145);
            copyRight.setLayoutParams(layoutParams);
            copyRight.getParent().requestLayout();
        }

    }

    /**
     * 初始化指纹识别传感器
     */
    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
        startFingerprintRecognition();
    }

    /**
     * 设置域名
     *
     * @param data
     */
    public static void domainAssignment(List<CompanyDomainEntity.DataBean> data) {
        Map<String, List<CompanyDomainEntity.DataBean>> domains = GroupByKt.groupDomain(data);
        Set<String> strings = domains.keySet();
        for (String key : strings) {
            String domain = "";
            List<CompanyDomainEntity.DataBean> dataBeans = domains.get(key);
            for (CompanyDomainEntity.DataBean dataBean : dataBeans) {
                String api_url = dataBean.getApi_url();
                if (!StringUtils.empty(api_url)) {
                    domain = api_url;
                    break;
                }
            }
            if (domain.endsWith("/")) {
                domain = domain.substring(0, domain.length() - 1);
            }
            if (key.equals(SuxuanAppIdKt.getMC())) {
                Contact.MC = domain;
            } else if (key.equals(SuxuanAppIdKt.getCRM())) {
                Contact.CRM = domain;
            } else if (key.equals(SuxuanAppIdKt.getERP())) {
                Contact.ERP = domain;
            } else if (key.equals(SuxuanAppIdKt.getOA())) {
                Contact.OA = domain;
            }
        }
    }

    /**
     * 获取客户的域名地址
     *
     * @param companyID 客户企业id
     * @param isCache   是否从缓存去拿
     */
    public void getCompanyDomainById(String companyID, boolean isCache) {
        JavaBeanRequest stringRequest = new JavaBeanRequest(Contact.COMPANY_DOMAIN, CompanyDomainEntity.class);
        stringRequest.add("app_code", companyID);
        String key = "app_code=" + companyID + Contact.CONTACT_KEY;
        key = EncryptUtils.encryptMD5ToString(key).toLowerCase();
        stringRequest.addHeader("ACCESS-TOKEN", key);
        if (isCache) {
            stringRequest.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        } else {
            stringRequest.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        }
        HttpListener<CompanyDomainEntity> httpListener = new HttpListener<CompanyDomainEntity>() {
            @Override
            public void onSucceed(int what, Response<CompanyDomainEntity> response) {
                if (response.get().isOK()) {
                    SPUtils.getInstance().put(COMPANY_ID, companyID);
                    mEmailView.requestFocus();
                    domainAssignment(response.get().getData());
                }
            }

            @Override
            public void onFailed(int what, Response<CompanyDomainEntity> response) {
            }
        };
        request(0, stringRequest, httpListener, false, false);
    }

    /**
     * 获取登录人权限
     */
    public void getPermisstion() {
        App.getApplication().releaseLoginData();
        if (App.getApplication().getUserInfor().getMain_work_type().isEmpty()) {
            ToastUtils.snackbarShort("未设置工作类型!无法获取权限");
            loginFailed();
            return;
        }
        if (App.getApplication().getUserInfor().getMain_position_code().isEmpty()) {
            ToastUtils.snackbarShort("未设置主岗位,无法获取权限");
            loginFailed();
            return;
        }
        String string = Contact.getFullUrl(Contact.LOGIN_PERMISSION, Contact.TOKEN, App.getApplication().getUserInfor().getMain_work_type() + "," + App.getApplication().getUserInfor().getWork_type(),
                App.getApplication().getUserInfor().getMain_position_code() + "," + App.getApplication().getUserInfor().getPosition_code(), App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<PermissionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PermissionEntity>(string, RequestMethod.POST, PermissionEntity.class);
        HttpListener<PermissionEntity> searchByCustmor = new HttpListener<PermissionEntity>() {
            @Override
            public void onSucceed(int what, Response<PermissionEntity> response) {
                if (response.get().isOK() || response.get().getData() != null) {
                    List<String> data = response.get().getData();
                    App.getApplication().setUserPermission(data);
                    loginJG(App.getApplication().getUserInfor().getJg_username(), App.getApplication().getUserInfor().getStaffnumber());
                } else {
                    loginFailed();
                }
            }

            @Override
            public void onFailed(int what, Response<PermissionEntity> response) {
                loginFailed();
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 登录C#的
     *
     * @param name
     * @param password
     */
    void login(String name, String password) {
        loadingDialog.setCancelable(false);
        loadingDialog.show();
        JavaBeanRequest stringRequest = new JavaBeanRequest(Contact.getFullUrl(Contact.LOGIN, Contact.TOKEN, name, password), RequestMethod.POST, LoginEntity.class);
        HttpListener<LoginEntity> httpListener = new HttpListener<LoginEntity>() {
            @Override
            public void onSucceed(int what, Response<LoginEntity> response) {
                if (response.get().isOK()) {
                    //保存登录信息
                    CacheUtils.getInstance().put(App.LOGIN_FILE_NAME, FastJsonUtils.toJSONString(response.get()));
                    SPUtils.getInstance().put(LOGIN_NAME, name);
                    SPUtils.getInstance().put(LOGIN_PASSWORD, password);
                    getPermisstion();
                } else {
                    loginFailed();
                    toastShort(response.get().getMsg());
                }
            }

            @Override
            public void onFailed(int what, Response<LoginEntity> response) {
                loginFailed();
            }
        };
        request(0, stringRequest, httpListener, false, false);
    }

    /**
     * 登录成功
     */
    public void LoginSucceed() {
        loadingDialog.dismiss();
        if (App.getmActivitys().size() > 1) {
            App.getApplication().finishActivity(LoginActivity.class);
            EventBus.getDefault().postSticky("reLogin");
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /*
      登录失败
     */
    public void loginFailed() {
        loadingDialog.dismiss();
        CacheUtils.getInstance().remove(App.LOGIN_FILE_NAME);
        SPUtils.getInstance().remove(LOGIN_NAME);
        SPUtils.getInstance().remove(LOGIN_PASSWORD);
        JMessageClient.logout();
    }

    /*
    登录极光聊天的服务器
     */
    public void loginJG(String jgName, String jgPassWord) {
        jgPassWord = EncryptUtils.encryptMD5ToString(jgPassWord).toLowerCase();
        eLog("极光:" + jgName + "极光密码:" + jgPassWord);
        JMessageClient.login(jgName, jgPassWord, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    LoginSucceed();
                } else {
                    loginFailed();
                    ToastUtils.showShort(s);
                }
            }
        });
    }

    /**
     * php登录
     *
     * @param name
     * @param key
     */
    private void phpLogin(String name, String key) {
        loadingDialog.show();
        String fullUrl = Contact.getFullUrl(Contact.PHP_LOGIN, Contact.OA);
        JavaBeanRequest<PHPLoginEntity> login = new JavaBeanRequest<PHPLoginEntity>(fullUrl, PHPLoginEntity.class);
        login.add("staffname", name);
        login.add("password", key);
        HttpListener<PHPLoginEntity> searchByCustmor = new HttpListener<PHPLoginEntity>() {
            @Override
            public void onSucceed(int what, Response<PHPLoginEntity> response) {
                if (response.get().isOK()) {
                    LoginEntity loginEntity = new LoginEntity();
                    ArrayList<UserEntity> userData = new ArrayList<>();
                    userData.add(response.get().getData());
                    loginEntity.setData(userData);
                    loginEntity.setMsg(response.get().getMsg());
                    loginEntity.setCode(response.get().getCode());
                    //保存登录信息
                    CacheUtils.getInstance().put(App.LOGIN_FILE_NAME, FastJsonUtils.toJSONString(loginEntity));
                    getPermisstion();
                } else {
                    loginFailed();
                    toastShort(response.get().getMsg());
                }
            }

            @Override
            public void onFailed(int what, Response<PHPLoginEntity> response) {
                loginFailed();
            }
        };
        request(0, login, searchByCustmor, false, false);
    }

    /**
     * 自动填充的前提是需要读取通讯录
     */
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

    /**
     * 请求通讯
     *
     * @return
     */
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     * 回调收到权限请求时已经完成。;
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * 试图登录或注册帐户指定的登录表单。如果有错误(无效的电子邮件,缺少字段等),并给出了错误,没有实际的登录尝试。;
     */
    private void attemptLogin() {
        //重置错误
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mCompanyID.setError(null);
        String comID = mCompanyID.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (comID.isEmpty()) {
            mCompanyID.setError("企业id错误");
            ToastUtils.snackbarShort("请填写正确的企业id", "确定");
            mCompanyID.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            mEmailView.setError("不能为空");
            ToastUtils.snackbarShort("请输入账号", "确定");
            mEmailView.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mPasswordView.setError("请输入密码");
            ToastUtils.snackbarShort("请输入密码", "确定");
            mPasswordView.requestFocus();
            return;
        }
        //校验完成进行登录
        login(email, password);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                //为设备用户的“概要”联系人检索数据行。
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,
                // Select only email addresses.
                //选择唯一的电子邮件地址。
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},
                //显示主电子邮件地址。注意,不会有一个主电子邮件地址如果用户没有指定一个
                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //创建适配器以告诉AutoCompleteTextView在其下拉列表中显示什么。
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        mEmailView.setAdapter(adapter);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }

    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {
                toastShort("请先到系统录入指纹");
                Intent intent = new Intent("android.settings.SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                }
                return;
            }
            if (mFingerprintCore.isAuthenticating()) {
                toastShort("指纹识别已开启");
            } else {
                mFingerprintCore.startAuthenticate();
                toastShort("已启动指纹识别");
            }
        } else {
            toastShort("设备不支持指纹识别");
        }
    }

    @Override
    protected void onDestroy() {
        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
        mResultListener = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressedSupport() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 222 && resultCode == RESULT_OK) {
            if (data.getStringExtra("result") != null) {
                String result = data.getStringExtra("result");
                if (StringUtils.strIsNumber(result) && result.length() == 10) {
                    mCompanyID.setText(result);
                } else {
                    ToastUtils.snackbarShort(this,"格式错误!", "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

