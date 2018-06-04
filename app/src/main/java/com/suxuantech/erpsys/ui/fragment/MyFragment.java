package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.BaseLazyFragment;
import com.suxuantech.erpsys.ui.widget.BounceScrollView;
import com.suxuantech.erpsys.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

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
    //    @BindView(R.id.dampView)
//    BounceScrollView mDampView;
    private View view;

    @Subscribe()
    @MainThread
    public void EventBus(String key) {
        if (key.equals("changeUser")) {
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
        useEventBus();
        BounceScrollView dampView = view.findViewById(R.id.dampView);
        dampView.setImageView(view.findViewById(R.id.img_top));
        dampView.setView(view.findViewById(R.id.ll_cao));

        ImmersionBar.with(getActivity()).reset().statusBarDarkFont(false).titleBar(R.id.tv_mine).init();
        view.findViewById(R.id.about_Us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("三生三世");
            }
        });
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
        return R.layout.fragment_my;
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

    @OnClick({R.id.switch_theme, R.id.img_top, R.id.tv_mine, R.id.img_user_head, R.id.tv_department, R.id.tv_store, R.id.tv_staff_serial_number, R.id.tv_staff_phone, R.id.tv_staff_id, R.id.about_Us, R.id.tv_verstion, R.id.btn_login_out,})
    public void onClick(View v) {
        switch (v.getId()) {

            default:
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
                break;
            case R.id.tv_verstion:
                break;
            case R.id.btn_login_out:
                break;
        }
    }
}
