package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.LoginActivity;
import com.suxuantech.erpsys.ui.widget.BounceScrollView;
import com.suxuantech.erpsys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends BaseSupportFragment {
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
    @BindView(R.id.btn_login_out)
    Button mBtnLoginOut;
    @BindView(R.id.content_view_linearlayout)
    LinearLayout mContentViewLinearlayout;
    @BindView(R.id.root_layout_status_immersed)
    RelativeLayout mRootLayoutStatusImmersed;
    @BindView(R.id.dampView)
    BounceScrollView mDampView;
    private View view;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ImmersionBar.with(getActivity()).reset().navigationBarColor(R.color.translucent_black_90).statusBarDarkFont(false).init();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BounceScrollView dampView = view.findViewById(R.id.dampView);
        dampView.setImageView(view.findViewById(R.id.img_top));
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
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        String appVersionName = AppUtils.getAppVersionName();
        mTvVerstion.setText(appVersionName);
        mTvDepartment.setText(App.getApplication().getUserInfor().getDepartment_name());
        mTvStore.setText(App.getApplication().getUserInfor().getShop_name());
        mTvStaffSerialNumber.setText(App.getApplication().getUserInfor().getStaffnumber());
        mTvStaffPhone.setText("id:"+App.getApplication().getUserInfor().getStaff_id());
         mTvStaffId.setText(App.getApplication().getUserInfor().getStaffname());
    }

    @OnClick({R.id.switch_theme,R.id.img_top, R.id.tv_mine, R.id.img_user_head, R.id.tv_department, R.id.tv_store, R.id.tv_staff_serial_number, R.id.tv_staff_phone, R.id.tv_staff_id, R.id.about_Us, R.id.tv_verstion, R.id.btn_login_out, R.id.content_view_linearlayout, R.id.root_layout_status_immersed, R.id.dampView})
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
            case R.id.content_view_linearlayout:
                break;
            case R.id.root_layout_status_immersed:
                break;
            case R.id.dampView:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
