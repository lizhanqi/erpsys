package com.suxuantech.erpsys.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.dialog.TimeDialog;
import com.suxuantech.erpsys.utils.DateUtil;
import com.yanzhenjie.fragment.NoFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInformationFragment extends NoFragment {
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.customer_id)
    TextView mCustomerId;
    @BindView(R.id.tv_order_type)
    TextView mTvOrderType;
    @BindView(R.id.tv_subscribe_date)
    TextView mTvSubscribeDate;
    @BindView(R.id.tv_consume_type)
    TextView mTvConsumeType;
    @BindView(R.id.et_man_name)
    EditText mEtManName;
    @BindView(R.id.et_woman_name)
    EditText mEtWomanName;
    @BindView(R.id.tv_customer_source)
    TextView mTvCustomerSource;
    @BindView(R.id.tv_customer_zone)
    TextView mTvCustomerZone;
    @BindView(R.id.tv_order_receiving_site)
    TextView mTvOrderReceivingSite;
    @BindView(R.id.tv_outlets_reception)
    TextView mTvOutletsReception;
    @BindView(R.id.tv_reception)
    TextView mTvReception;
    @BindView(R.id.et_outlets_for_three)
    EditText mEtOutletsForThree;
    @BindView(R.id.tv_card_number)
    TextView mTvCardNumber;
    @BindView(R.id.tv_member_type)
    TextView mTvMemberType;
    @BindView(R.id.et_man_phone)
    EditText mEtManPhone;
    @BindView(R.id.et_woman_phone)
    EditText mEtWomanPhone;
    @BindView(R.id.tv_manager)
    TextView mTvManager;
    @BindView(R.id.tv_support_staff)
    TextView mTvSupportStaff;
    @BindView(R.id.tv_engagement_date)
    TextView mTvEngagementDate;
    @BindView(R.id.tv_marriage_date)
    TextView mTvMarriageDate;
    @BindView(R.id.et_customer_remarks)
    EditText mEtCustomerRemarks;
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
        mTvCardNumber.setText("111111111111");
        return inflate;
    }

    @OnClick({R.id.tv_store_name, R.id.customer_id, R.id.tv_order_type, R.id.tv_subscribe_date, R.id.tv_consume_type, R.id.et_man_name, R.id.et_woman_name, R.id.tv_customer_source, R.id.tv_customer_zone, R.id.tv_order_receiving_site, R.id.tv_outlets_reception, R.id.tv_reception, R.id.et_outlets_for_three, R.id.tv_card_number, R.id.tv_member_type, R.id.et_man_phone, R.id.et_woman_phone, R.id.tv_manager, R.id.tv_support_staff, R.id.tv_engagement_date, R.id.tv_marriage_date, R.id.et_customer_remarks})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_store_name:
                break;
            case R.id.customer_id:
                break;
            case R.id.tv_order_type:
                break;
            case R.id.tv_subscribe_date:
                TimeDialog.showDateSelectDay(getActivity(), R.string.subscribe_date, R.color.msgColor,new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvSubscribeDate.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
                    }
                });
                break;
            case R.id.tv_consume_type:
                break;
            case R.id.et_man_name:
                break;
            case R.id.et_woman_name:
                break;
            case R.id.tv_customer_source:
                break;
            case R.id.tv_customer_zone:
                break;
            case R.id.tv_order_receiving_site:
                break;
            case R.id.tv_outlets_reception:
                break;
            case R.id.tv_reception:
                break;
            case R.id.et_outlets_for_three:
                break;
            case R.id.tv_card_number:
                break;
            case R.id.tv_member_type:
                break;
            case R.id.et_man_phone:
                break;
            case R.id.et_woman_phone:
                break;
            case R.id.tv_manager:
                break;
            case R.id.tv_support_staff:
                break;
            case R.id.tv_engagement_date:
                TimeDialog.showDateSelectDay(getActivity(), R.string.engagement_date,R.color.msgColor, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvEngagementDate.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
                    }
                });
                break;
            case R.id.tv_marriage_date:
                TimeDialog.showDateSelectDay(getActivity(), R.string.marriage_date, R.color.msgColor,new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTvMarriageDate.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
                    }
                });
                break;
            case R.id.et_customer_remarks:
                break;
                default:
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
