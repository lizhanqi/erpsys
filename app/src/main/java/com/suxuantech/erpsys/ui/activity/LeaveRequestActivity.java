package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.Text2Bitmap;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 请假申请
 */
public class LeaveRequestActivity extends TitleNavigationActivity {
    @BindView(R.id.btn_submint)
    Button mBtnSubmint;
    @BindView(R.id.tv_leave_type)
    TextView mTvLeaveType;
    @BindView(R.id.ll_leave_type)
    LinearLayout mLlLeaveType;
    @BindView(R.id.tv_leave_start_time)
    TextView mTvLeaveStartTime;
    @BindView(R.id.tv_leave_end_time)
    TextView mTvLeaveEndTime;
    @BindView(R.id.tv_leave_sum_time)
    TextView mTvLeaveSumTime;
    @BindView(R.id.ll_leave_time)
    LinearLayout mLlLeaveTime;
    @BindView(R.id.tv_leave_reason)
    TextView mTvLeaveReason;
    @BindView(R.id.et_leave_reason)
    EditText mEtLeaveReason;
    @BindView(R.id.ll_leave_reason)
    LinearLayout mLlLeaveReason;
    @BindView(R.id.tv_leave_flow)
    TextView mTvLeaveFlow;
    @BindView(R.id.image_flow_head)
    ImageView mImageFlowHead;
    @BindView(R.id.ll_leave_flow)
    LinearLayout mLlLeaveFlow;
    @BindView(R.id.rv_cc)
    SwipeMenuRecyclerView mRvCc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        ButterKnife.bind(this);
        showUserDefinedNav();
        useButterKnife();
        setTitle("请假申请");
        initView();
        mImageFlowHead.setImageBitmap(Text2Bitmap.getNewBitMap("德华", getResources().getColor(R.color.white),100));
    }

    private void initView() {
        ArrayList<String> ccArray = new ArrayList<>();
        ccArray.add("李四");
        ccArray.add("王麻子");
        ccArray.add("巧妹子");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//横向滚动的RecycleView
        mRvCc.setLayoutManager(linearLayoutManager);
        new BaseRecyclerAdapter<String>(mRvCc, ccArray, R.layout.item_cc) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_cc);
                view.setSelected(true);
                ImageView ccImage = holder.getView(R.id.image_cc);
                ccImage.setImageBitmap(Text2Bitmap.getNameBitMap(item, getResources().getColor(R.color.white),150));
                view.setText(item);
            }
        };
    }

    @OnClick({R.id.btn_submint, R.id.tv_leave_type, R.id.ll_leave_type, R.id.tv_leave_start_time, R.id.tv_leave_end_time, R.id.tv_leave_sum_time, R.id.ll_leave_time, R.id.tv_leave_reason, R.id.et_leave_reason, R.id.ll_leave_reason, R.id.tv_leave_flow, R.id.image_flow_head, R.id.ll_leave_flow, R.id.rv_cc})
    public void onClicks(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_submint:
                break;
            case R.id.tv_leave_type:
                break;
            case R.id.ll_leave_type:
                break;
            case R.id.tv_leave_start_time:
                break;
            case R.id.tv_leave_end_time:
                break;
            case R.id.tv_leave_sum_time:
                break;
            case R.id.ll_leave_time:
                break;
            case R.id.tv_leave_reason:
                break;
            case R.id.et_leave_reason:
                break;
            case R.id.ll_leave_reason:
                break;
            case R.id.tv_leave_flow:
                break;
            case R.id.image_flow_head:
                break;
            case R.id.ll_leave_flow:
                break;
            case R.id.rv_cc:
                break;
        }
    }
}
