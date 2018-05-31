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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.DialogCreator;
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
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.utils.core.FingerprintCore;
import com.suxuantech.erpsys.utils.core.KeyguardLockScreenManager;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

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
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * QuickAdapter dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView copyRight;
    private EditText mCompanyID;
    private Button mEmailSignInButton;
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
                    loginJG(App.getApplication().getUserInfor().getStaffname(), App.getApplication().getUserInfor().getStaffnumber());
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

    public void LoginSucceed() {
        loadingDialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    Dialog loadingDialog;

    void login(String name, String password) {
        if (name.isEmpty()){
            mEmailView.setError("不能为空");
            ToastUtils.snackbarShort("请输入账号","确定");
            return;
        }
        if (password.isEmpty()){
            mPasswordView.setError("请输入密码");
            ToastUtils.snackbarShort("请输入密码","确定");
            return;
        }
        loadingDialog.setCancelable(false);
        loadingDialog.show();
        JavaBeanRequest stringRequest = new JavaBeanRequest(Contact.getFullUrl(Contact.LOGIN, Contact.TOKEN, name, password), RequestMethod.POST, LoginEntity.class);
        HttpListener<LoginEntity> httpListener = new HttpListener<LoginEntity>() {
            @Override
            public void onSucceed(int what, Response<LoginEntity> response) {
                if (response.get().isOK()) {
                    //保存登录信息
                    CacheUtils.getInstance().put(App.LOGIN_FILE_NAME, FastJsonUtils.toJSONString(response.get()));
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

    /*
      登录失败
     */
    public void loginFailed() {
        loadingDialog.dismiss();
        JMessageClient.logout();
    }

    public void loginJG(String jgName, String jgPassWord) {
        jgPassWord = EncryptUtils.encryptMD5ToString(jgPassWord).toLowerCase();
        jgName = EncodeUtils.base64Encode2String(jgName.getBytes());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingDialog = DialogCreator.createLoadingDialog(LoginActivity.this, "登录中ing...");
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        initFingerprintCore();
        setContentView(R.layout.activity_login);
        copyRight = idGetView(R.id.copyright);
        copyRight.setText(getString(R.string.copyright) + " V" + AppUtil.getVersionName(this));
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = findViewById(R.id.password);
        mCompanyID = findViewById(R.id.company_id);
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
               login(mEmailView.getText().toString().trim(), mPasswordView.getText().toString().trim());
            }
        });
    }

    private void phpLogin(String name, String key) {
        loadingDialog.show();
        String fullUrl = Contact.getFullUrl(Contact.PHP_LOGIN, Contact.PHP_PREFIX);
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

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this).navigationBarColor(R.color.mainNavline_e7).titleBar(findViewById(R.id.rl_root_login)).statusBarDarkFont(true).init();
    }

    FingerprintCore mFingerprintCore;
    KeyguardLockScreenManager mKeyguardLockScreenManager;
    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            mCompanyID.setText("111");
            mEmailView.setText("小飞");
            login("小飞", "0");
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

    /**
     * 初始化指纹识别传感器
     */
    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
        startFingerprintRecognition();
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

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
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
     } //else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }



    /**
     * 密码长度判断
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });


        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            //       mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

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
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
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
}

