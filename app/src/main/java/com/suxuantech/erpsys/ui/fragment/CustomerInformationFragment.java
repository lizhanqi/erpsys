package com.suxuantech.erpsys.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.SearchOrderEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * QuickAdapter simple {@link Fragment} subclass.
 */
public class CustomerInformationFragment extends BaseSupportFragment {
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.customer_id)
    TextView mCustomerId;
    @BindView(R.id.tv_subscribe_date)
    TextView mTvSubscribeDate;
    @BindView(R.id.tv_consume_type)
    TextView mTvConsumeType;
    @BindView(R.id.tv_neworder_type)
    TextView mTvNeworderType;
    @BindView(R.id.tv_woman_name)
    TextView mTvWomanName;
    @BindView(R.id.tv_woman_phone)
    TextView mTvWomanPhone;
    @BindView(R.id.tv_woman_wechat)
    TextView mTvWomanWechat;
    @BindView(R.id.tv_woman_birthday)
    TextView mTvWomanBirthday;
    @BindView(R.id.tv_man_name)
    TextView mTvManName;
    @BindView(R.id.tv_man_phone)
    TextView mTvManPhone;
    @BindView(R.id.tv_man_wechat)
    TextView mTvManWechat;
    @BindView(R.id.tv_man_birthday)
    TextView mTvManBirthday;
    @BindView(R.id.tv_marry_date)
    TextView mTvMarryDate;
    @BindView(R.id.tv_reception)
    TextView mTvReception;
    @BindView(R.id.tv_reception_market)
    TextView mTvReceptionMarket;
    @BindView(R.id.tv_reception_governor)
    TextView mTvReceptionGovernor;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_remarks)
    TextView mTvRemarks;
    @BindView(R.id.btn_submint)
    Button mBtnSubmint;
    private View view;
    private Unbinder unbinder;

    public CustomerInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_customer_information, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchOrderEntity.DataBean data = getArguments().getParcelable("customerAllInfor");
        mTvStoreName.setText(data.getShop_name());

        mCustomerId.setText(data.getOrderId());
        mTvSubscribeDate.setText(data.getTargetdate());
        mTvConsumeType.setText(data.getConsumption_type());
        mTvNeworderType.setText(data.getOeder_newclass());

        mTvManName.setText(data.getMname());
        mTvManPhone.setText(data.getMphone());
        mTvManWechat.setText(data.getMwechat());
        mTvManBirthday.setText(data.getMbirthdate());

        mTvWomanName.setText(data.getWname());
        mTvWomanPhone.setText(data.getWphone());
        mTvWomanWechat.setText(data.getWwechat());
        mTvWomanBirthday.setText(data.getWbirthdate());

        mTvMarryDate.setText(data.getWeddingdate());

        mTvReception.setText(data.getStoreconsuitant1());
        mTvReceptionMarket.setText(data.getStoreconsuitant2());
        mTvReceptionGovernor.setText(data.getStoreconsuitant3());
        mTvEmail.setText(data.getEmail());
        mTvAddress.setText(data.getAddress());
        mTvRemarks.setText(data.getCustomernote());
    }

    @OnClick({R.id.tv_store_name, R.id.customer_id, R.id.tv_subscribe_date, R.id.tv_consume_type, R.id.tv_neworder_type, R.id.tv_woman_name, R.id.tv_woman_phone, R.id.tv_woman_wechat, R.id.tv_woman_birthday, R.id.tv_man_name, R.id.tv_man_phone, R.id.tv_man_wechat, R.id.tv_man_birthday, R.id.tv_marry_date, R.id.tv_reception, R.id.tv_reception_market, R.id.tv_reception_governor, R.id.tv_email, R.id.tv_address, R.id.tv_remarks, R.id.btn_submint})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_store_name:
                break;
            case R.id.customer_id:
                break;
            case R.id.tv_subscribe_date:
                break;
            case R.id.tv_consume_type:
                break;
            case R.id.tv_neworder_type:
                break;
            case R.id.tv_woman_name:
                break;
            case R.id.tv_woman_phone:
                break;
            case R.id.tv_woman_wechat:
                break;
            case R.id.tv_woman_birthday:
                break;
            case R.id.tv_man_name:
                break;
            case R.id.tv_man_phone:
                break;
            case R.id.tv_man_wechat:
                break;
            case R.id.tv_man_birthday:
                break;
            case R.id.tv_marry_date:
                break;
            case R.id.tv_reception:
                break;
            case R.id.tv_reception_market:
                break;
            case R.id.tv_reception_governor:
                break;
            case R.id.tv_email:
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_remarks:
                break;
            case R.id.btn_submint:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
