package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.SelectPictureEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 选片
 */
public class SelectedPictureInformationFragment extends BaseSupportFragment {
    @BindView(R.id.tv_selected_pictrue_time)
    TextView mTvSelectedPictrueTime;
    @BindView(R.id.tv_selected_pictrue_status)
    TextView mTvSelectedPictrueStatus;
    @BindView(R.id.tv_selected_picture_staff)
    TextView mTvSelectedPictureStaff;
    @BindView(R.id.tv_selected_pictrue_finish_time)
    TextView mTvSelectedPictrueFinishTime;
    @BindView(R.id.tv_look_layout_time)
    TextView mTvLookLayoutTime;
    @BindView(R.id.tv_look_layout_status)
    TextView mTvLookLayoutStatus;
    @BindView(R.id.tv_look_layout_staff)
    TextView mTvLookLayoutStaff;
    @BindView(R.id.tv_look_layout_finish_time)
    TextView mTvLookLayoutFinishTime;
    @BindView(R.id.tv_design_staff)
    TextView mTvDesignStaff;
    @BindView(R.id.tv_sample_before_art)
    TextView mTvSampleBeforeArt;
    @BindView(R.id.tv_layout_designer_staff)
    TextView mTvLayoutDesignerStaff;
    @BindView(R.id.tv_Sample_after_finishing)
    TextView mTvSampleAfterFinishing;
    @BindView(R.id.tv_selected_pictrue_room)
    TextView mTvSelectedPictrueRoom;
    @BindView(R.id.tv_import_number)
    TextView mTvImportNumber;
    @BindView(R.id.tv_selected_number)
    TextView mTvSelectedNumber;
    @BindView(R.id.tv_entered_number)
    TextView mTvEnteredNumber;
    @BindView(R.id.tv_selected_remarks)
    TextView mTvSelectedRemarks;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.new_fragment_seleced_picture, container, false);
        unbinder = ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getArguments().getString("orderId");
        SelectPictureEntity.DataBean data = getArguments().getParcelable("data");
        if (data!=null){
            //选片
            mTvSelectedPictrueTime.setText(data.getStart_select_time());
            mTvSelectedPictrueStatus.setText(data.getSptstate());
            mTvSelectedPictureStaff.setText(data.getSelectman());
            mTvSelectedPictrueFinishTime.setText(data.getOver_select_time());


            //看板
            mTvLookLayoutTime.setText(data.getKanbanshijian());
            mTvLookLayoutStatus.setText(data.getKanbanstate());
            mTvDesignStaff.setText(data.getDesignerman());
            mTvLookLayoutFinishTime.setText(data.getKanbanriqi());



            //设计
            mTvLookLayoutTime.setText(data.getKanbanshijian());
            mTvSampleBeforeArt.setText(data.getAmericaneditorman());
            mTvLayoutDesignerStaff.setText(data.getArtistman());
            mTvSampleAfterFinishing.setText(data.getYhjxman());
            mTvSelectedPictrueRoom.setText(data.getSproom());
            //最后
            mTvImportNumber.setText(data.getSpImport_photocount()+"");
            mTvSelectedNumber.setText(data.getSpcount()+"");
            mTvEnteredNumber.setText(data.getSpbook_photocount()+"");
            mTvSelectedRemarks.setText(data.getSpremarks()+"");


        }
    }

    @OnClick({R.id.tv_selected_pictrue_time, R.id.tv_selected_pictrue_status, R.id.tv_selected_picture_staff, R.id.tv_selected_pictrue_finish_time, R.id.tv_look_layout_time, R.id.tv_look_layout_status, R.id.tv_look_layout_staff, R.id.tv_look_layout_finish_time, R.id.tv_design_staff, R.id.tv_sample_before_art, R.id.tv_layout_designer_staff, R.id.tv_Sample_after_finishing, R.id.tv_selected_pictrue_room, R.id.tv_import_number, R.id.tv_selected_number, R.id.tv_entered_number, R.id.tv_selected_remarks})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_selected_pictrue_time:
                break;
            case R.id.tv_selected_pictrue_status:
                break;
            case R.id.tv_selected_picture_staff:
                break;
            case R.id.tv_selected_pictrue_finish_time:
                break;
            case R.id.tv_look_layout_time:
                break;
            case R.id.tv_look_layout_status:
                break;
            case R.id.tv_look_layout_staff:
                break;
            case R.id.tv_look_layout_finish_time:
                break;
            case R.id.tv_design_staff:
                break;
            case R.id.tv_sample_before_art:
                break;
            case R.id.tv_layout_designer_staff:
                break;
            case R.id.tv_Sample_after_finishing:
                break;
            case R.id.tv_selected_pictrue_room:
                break;
            case R.id.tv_import_number:
                break;
            case R.id.tv_selected_number:
                break;
            case R.id.tv_entered_number:
                break;
            case R.id.tv_selected_remarks:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
