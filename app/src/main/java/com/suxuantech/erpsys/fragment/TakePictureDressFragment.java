package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suxuantech.erpsys.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 *  拍照礼服(礼服资料)
 */
public class TakePictureDressFragment extends SupportFragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_picture_dress, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @OnClick({R.id.tv_take_picture_dress, R.id.ll_take_picture_dress, R.id.tv_single_rent_dress, R.id.ll_single_rent_dress, R.id.tv_engagement_dress, R.id.ll_engagement_dress, R.id.tv_marriage_dress, R.id.ll_marriage_dress})
    public void onClick(View v) {
        switch (v.getId()) {
                case R.id.tv_nav_left:
                    break;
            case R.id.tv_take_picture_dress:
                break;
            case R.id.ll_take_picture_dress:
              //  (( OrderDetailActivity)getActivity()).startFragment(TakePictureDressDetailFragment.class);
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
