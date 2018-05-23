package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.JLoginActivity;
import com.suxuantech.erpsys.ui.activity.BookManageActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;


public class WorkFragment extends BaseSupportFragment {


    private View view;
    private SwipeMenuRecyclerView mRvWorks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    String Token1 = "HS0sBzcCNqRN+FtC+Z/l7aOtPr0xDPib4mZ6Md+R+xcOF8p48FtRg1Wqk1JAYqZisvLswk7iq0w=";
    String Token2 = "S5GHq2KiNmRosErpLpiK4QbtAtF8Cav5jNwituHVAUCumboJ9ztv4ocvldKwflJ6T875ROVUBnjRQx+HYKssag==";
    private String Token3 = "V2vAKbusGpJHy8x3J0B5vtIVJFX/4Dd2n9hW0HoS1qtWaozqC5QxRq8vCzdmuQXU1++2oTxW2y6K1h7R49pa2w==";
    String Head2 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2331346086,286899446&fm=111&gp=0.jpg";
    String Head1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2263453617,2764418059&fm=27&gp=0.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
 ///Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png")
    }

    private void action2() {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                //去服务器查询(这里中需要后台配合)
                if (s.equals("u1")) {
                    return new UserInfo(s, s, Uri.parse(Head1));
                } else {
                    return new UserInfo(s, s, Uri.parse(Head2));
                }
            }
        }, true);
    }

    private void nameandhead() {
//        消息携带需要ios配合(只能是二选一)
        /**
         * 设置当前用户信息，
         *
         * @param userInfo 当前用户信息
         */
        RongIM.getInstance().setCurrentUserInfo(new UserInfo("u1", "用户1", Uri.parse(Head1)));
        /**
         * 发送消息时携带发送者对信息
         */
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        /**
         * 刷新用户缓存数据。(zhix)
         * @param userInfo 需要更新的用户缓存数据。
         */
        RongIM.getInstance().refreshUserInfoCache(new UserInfo("u1", "用户1", Uri.parse(Head1)));
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link # init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token 从服务端获取的用户身份令牌（Token）。
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

                ToastUtils.showShort("链接成功" + userid);
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

    private void initView(View view) {
        Map<String, Integer> strings = new LinkedHashMap<String, Integer>();
        strings.put("登录融云1", 1);
        strings.put("登录融云2", 1);
        strings.put("与融云1对话", 1);
        strings.put("与融云2对话", 1);
        strings.put("图书", 1);
        strings.put("极光IM", 1);
        strings.put("审批", 1);
        strings.put("考勤", 1);
        strings.put("任务", 1);


        mRvWorks = view.findViewById(R.id.rv_works);
        Set<String> strings1 = strings.keySet();
        ArrayList<String> strings2 = new ArrayList<>(strings1);
        BaseRecyclerAdapter baseRecyclerAdapter=  new BaseRecyclerAdapter<String>(mRvWorks, strings2, R.layout.item_work) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                CardView cardView = holder.getView(R.id.cardview);
                cardView   .setCardBackgroundColor(getResources().getColor(R.color.Orange));
                TextView textView = holder.getView(R.id.tv_work);
                textView.setText(item);
            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                if(position==0){
                    connect(Token1);
                    //nameandhead();
                }else if(position==1){
                    connect(Token2);
                }else   if(position==2){
                    RongIM.getInstance().startPrivateChat(getActivity(), "u1", "名字");
                }else if(position==3){
                    RongIM.getInstance().startPrivateChat(getActivity(), "u2", "标题u2");
                }else   if (position==4){
                    getActivity().startActivity(new Intent(getActivity(), BookManageActivity.class));
                }else    if (position==5){
                    getActivity().startActivity(new Intent(getActivity(), JLoginActivity.class));
                }else{
                    Intent intent = new Intent();
                    intent.setAction("com.suxuantech.action.VIEW"); //对应于action
//                intent.addCategory("android.intent.category.DEFAULT");//对应于category
//                intent.setData(Uri.parse("suxuantech:"));//对应于data下的scheme(rocky)
                    getActivity().startActivity(intent);
                }
            }
        });
    }
/*    @Override
    protected void widgetClick(View v) {

    }*/
}
