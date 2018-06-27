package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.WebActivityConfig;
import com.suxuantech.erpsys.ui.activity.FeedbackActivity;
import com.suxuantech.erpsys.ui.activity.base.BaseLazyFragment;
import com.suxuantech.erpsys.ui.widget.BounceScrollView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseLazyFragment {
    @BindView(R.id.img_top)
    ImageView mImgTop;
    @BindView(R.id.tv_mine)
    TextView mTvMine;
    @BindView(R.id.img_user_head)
    ImageView mImgUserHead;
    @BindView(R.id.tv_department)
    TextView mTvDepartment;
    @BindView(R.id.tv_store)
    TextView mTvStore;
    @BindView(R.id.tv_staff_serial_number)
    TextView mTvStaffSerialNumber;
    @BindView(R.id.tv_staff_phone)
    TextView mTvStaffPhone;
    @BindView(R.id.tv_staff_id)
    TextView mTvStaffId;
    @BindView(R.id.about_Us)
    RelativeLayout mAboutUs;
    @BindView(R.id.tv_verstion)
    TextView mTvVerstion;
    @BindView(R.id.tv_name_and_post)
    TextView mTvNameAndPost;
    @BindView(R.id.btn_login_out)
    Button mBtnLoginOut;
    @BindView(R.id.rv_other_info)
    RelativeLayout otherInfo;
    @BindView(R.id.feedback)
    RelativeLayout feedback;
    private long lastClickTime = 0;
    private int clickCount = 0;
    //    @BindView(R.id.dampView)
//    BounceScrollView mDampView;
    private View view;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    @MainThread
    public void EventBus(String key) {
        if (key.equals("changeShop") || key.equals("reLogin")) {
            data2View();
        }
    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_my, container, false);
//
//        return view;
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initImmersionBar();
            //  ImmersionBar.with(getActivity()).reset().navigationBarColor(R.color.translucent_black_90).statusBarDarkFont(false).init();
        }
    }

    @Override
    public void initImmersionBar() {
        if (getActivity() != null) {
            super.initImmersionBar();
            mImmersionBar.statusBarDarkFont(false).init();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BounceScrollView dampView = view.findViewById(R.id.dampView);
        dampView.setImageView(view.findViewById(R.id.img_top));
        dampView.setView(view.findViewById(R.id.ll_cao));

        ImmersionBar.with(getActivity()).reset().statusBarDarkFont(false).titleBar(R.id.tv_mine).init();
//        view.findViewById(R.id.about_Us).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showShort("三生三世");
//            }
//        });
        view.findViewById(R.id.btn_login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getApplication().loginOut();
                getActivity().finish();
            }
        });
        data2View();
    }

    @Override
    protected int setLayoutId() {
        useEventBus();
        return R.layout.fragment_my;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void data2View() {
        String appVersionName = AppUtils.getAppVersionName();
        mTvVerstion.setText(appVersionName);
        mTvDepartment.setText(App.getApplication().getUserInfor().getDepartment_name());
        mTvStore.setText(App.getApplication().getUserInfor().getShop_name());
        mTvStaffSerialNumber.setText(App.getApplication().getUserInfor().getStaffnumber());
        if (App.ISDEBUG) {
            mTvStaffPhone.setText("员工id:" + App.getApplication().getUserInfor().getStaff_id() + "店面code:" + App.getApplication().getUserInfor().getShop_code());
            otherInfo.setVisibility(View.VISIBLE);
        } else {
            otherInfo.setVisibility(View.GONE);
        }
        mTvStaffId.setText(App.getApplication().getUserInfor().getStaffname());
        mTvNameAndPost.setText(App.getApplication().getUserInfor().getStaffname() + "\n" + App.getApplication().getUserInfor().getMain_position_name());
    }

    @OnClick({R.id.switch_theme, R.id.feedback, R.id.img_top, R.id.tv_mine, R.id.img_user_head, R.id.tv_department, R.id.tv_store, R.id.tv_staff_serial_number, R.id.tv_staff_phone, R.id.tv_staff_id, R.id.about_Us, R.id.tv_verstion, R.id.btn_login_out,})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.switch_theme:
//               if( SkinConfig.isDefaultSkin(getContext()) ){
//                   SkinManager.getInstance().loadSkin("darktheme.skin",
//                           new SkinLoaderListener() {
//                               @Override
//                               public void onStart() {
//                                   Log.i("SkinLoaderListener", "正在切换中");
//                               }
//                               @Override
//                               public void onSuccess() {
//                                   Log.i("SkinLoaderListener", "切换成功");
//                               }
//
//                               @Override
//                               public void onFailed(String errMsg) {
//                                   Log.i("SkinLoaderListener", "切换失败:" + errMsg);
//                               }
//
//                               @Override
//                               public void onProgress(int progress) {
//                                   Log.i("SkinLoaderListener", "皮肤文件下载中:" + progress);
//
//                               }
//                           });
//
//               }else {
//                   SkinManager.getInstance().restoreDefaultTheme();
//               }

                break;
            case R.id.img_top:
                break;
            case R.id.tv_mine:
                break;
            case R.id.img_user_head:
                clickCount++;
                if (clickCount == 5) {
                    App.ISDEBUG = !App.ISDEBUG;
                    if (App.ISDEBUG) {
                        SPUtils.getInstance().put(App.APP_LOG_NAME, true);
                        ToastUtils.showShort("开启Debug(重启应用生效)");
                    } else {
                        SPUtils.getInstance().put(App.APP_LOG_NAME, false);
                        ToastUtils.showShort("关闭Debug(重启应用生效)");
                    }
                    App.getApplication().debug();
                    lastClickTime = 0;
                    clickCount = 0;
                } else {
                    long now = System.currentTimeMillis();
                    if (lastClickTime - now < 1000) {
                        lastClickTime = now;
                        if (clickCount >= 3 && clickCount <= 4) {
                            if (App.ISDEBUG) {
                                ToastUtils.showLong("再点击" + (5 - clickCount) + "关闭Debug");
                            } else {
                                ToastUtils.showLong("再点击" + (5 - clickCount) + "开启Debug");
                            }
                        }
                    } else {
                        lastClickTime = now;
                        clickCount = 1;
                    }
                }
                break;
            case R.id.tv_department:
                break;
            case R.id.tv_store:
                break;
            case R.id.tv_staff_serial_number:
                break;
            case R.id.tv_staff_phone:
                break;
            case R.id.tv_staff_id:
                break;
            case R.id.about_Us:
                WebActivityConfig webActivityConfig = new WebActivityConfig(getContext());
                String url = "http://www.suxuantech.com/col.jsp?id=106";
                url = "http://demo.rockoa.com/?m=index&d=we";
                webActivityConfig.loadUrl(url).showToolbar(false);
                webActivityConfig.start();
                break;
            case R.id.tv_verstion:
                break;
            case R.id.btn_login_out:
                break;
        }
    }
}
