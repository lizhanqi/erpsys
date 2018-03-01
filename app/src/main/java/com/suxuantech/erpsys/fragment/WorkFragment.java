package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.ToastUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.yokeyword.fragmentation.SupportFragment;


public class WorkFragment extends SupportFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    TextView mTvFm;

    String Token1="HS0sBzcCNqRN+FtC+Z/l7aOtPr0xDPib4mZ6Md+R+xcOF8p48FtRg1Wqk1JAYqZisvLswk7iq0w=";
    String Token2="S5GHq2KiNmRosErpLpiK4QbtAtF8Cav5jNwituHVAUCumboJ9ztv4ocvldKwflJ6T875ROVUBnjRQx+HYKssag==";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_work, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv_fm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(Token1);
            }
        });
        mTvFm =   view.findViewById(R.id.tv_fm);
        mTvFm.setText("链接1");
        view.findViewById(R.id.tv_fm2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(Token2);
            }
        });
    }
    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link #init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    ToastUtils.show("链接成功"+userid);
                 //   RongIM.getInstance().startConversationList(getActivity());
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
    }
/*    @Override
    protected void widgetClick(View v) {

    }*/
}
