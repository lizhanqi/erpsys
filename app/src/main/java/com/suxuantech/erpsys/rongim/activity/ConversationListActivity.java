package com.suxuantech.erpsys.rongim.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.suxuantech.erpsys.ui.activity.LoginActivity;
import com.suxuantech.erpsys.ui.activity.MainActivity;
import com.suxuantech.erpsys.ui.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.push.RongPushClient;

/**
 * Created by Bob on 15/11/3.
 * 会话列表，需要做 2 件事
 * 1，push 重连，收到 push 消息的时候，做一下 connect 操作
 */
public class ConversationListActivity extends Activity {

    private static final String TAG = ConversationListActivity.class.getSimpleName();
    private LoadingDialog mDialog;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        mDialog = new LoadingDialog(this);
        Intent intent = getIntent();
        //push
        if (intent.getData().getScheme().equals("rong") && intent.getData().getQueryParameter("isFromPush") != null
                && intent.getData().getQueryParameter("isFromPush").equals("true")) {
            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            String options = getIntent().getStringExtra("options");
            if (options != null) {
                try {
                    JSONObject jsonObject = new JSONObject(options);
                    if (jsonObject.has("appData")) {
                    }
                    if (jsonObject.has("rc")) {
                        JSONObject rc = jsonObject.getJSONObject("rc");
                        String targetId = rc.getString("tId");
                        String pushId = rc.getString("id");
                        if (!TextUtils.isEmpty(pushId)) {
                            RongPushClient.recordNotificationEvent(pushId);
                        }
                        if (rc.has("ext") && rc.getJSONObject("ext") != null) {
                            String ext = rc.getJSONObject("ext").toString();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            enterActivity();
        } else {//通知过来
            //程序切到后台，收到消息后点击进入,会执行这里
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                enterActivity();
            } else {
                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
                finish();
            }
        }
    }

    /**
     * 收到 push 消息后，选择进入哪个 Activity
     * 如果程序缓存未被清理，进入 MainActivity
     * 程序缓存被清理，进入 LoginActivity，重新获取token
     * <p/>
     * 作用：由于在 manifest 中 intent-filter 是配置在 ConversationListActivity 下面，所以收到消息后点击notifacition 会跳转到 DemoActivity。
     * 以跳到 MainActivity 为例：
     * 在 ConversationListActivity 收到消息后，选择进入 MainActivity，这样就把 MainActivity 激活了，当你读完收到的消息点击 返回键 时，程序会退到
     * MainActivity 页面，而不是直接退回到 桌面。
     */
    private void enterActivity() {
        String token = sp.getString("loginToken", "");
        if (token.equals("default")) {
            startActivity(new Intent(ConversationListActivity.this, LoginActivity.class));
            finish();
        } else {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
            reconnect(token);
        }
    }


    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "---onTokenIncorrect--");
            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "---onSuccess--" + s);
                if (mDialog != null){
                    mDialog.dismiss();
                }
                startActivity(new Intent(ConversationListActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Log.e(TAG, "---onError--" + e);
            }
        });

    }

}

