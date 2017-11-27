package com.suxuantech.erpsys.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.yanzhenjie.fragment.NoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 选片
 */
public class TakePictureDressFragment extends NoFragment {
    @BindView(R.id.tv_take_picture_dress)
    TextView mTvTakePictureDress;
    @BindView(R.id.ll_take_picture_dress)
    LinearLayout mLlTakePictureDress;
    @BindView(R.id.tv_single_rent_dress)
    TextView mTvSingleRentDress;
    @BindView(R.id.ll_single_rent_dress)
    LinearLayout mLlSingleRentDress;
    @BindView(R.id.tv_engagement_dress)
    TextView mTvEngagementDress;
    @BindView(R.id.ll_engagement_dress)
    LinearLayout mLlEngagementDress;
    @BindView(R.id.tv_marriage_dress)
    TextView mTvMarriageDress;
    @BindView(R.id.ll_marriage_dress)
    LinearLayout mLlMarriageDress;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_picture_dress, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_take_picture_dress, R.id.ll_take_picture_dress, R.id.tv_single_rent_dress, R.id.ll_single_rent_dress, R.id.tv_engagement_dress, R.id.ll_engagement_dress, R.id.tv_marriage_dress, R.id.ll_marriage_dress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_picture_dress:
                break;
            case R.id.ll_take_picture_dress:
                break;
            case R.id.tv_single_rent_dress:
                break;
            case R.id.ll_single_rent_dress:
                break;
            case R.id.tv_engagement_dress:
                break;
            case R.id.ll_engagement_dress:
                break;
            case R.id.tv_marriage_dress:
                break;
            case R.id.ll_marriage_dress:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
