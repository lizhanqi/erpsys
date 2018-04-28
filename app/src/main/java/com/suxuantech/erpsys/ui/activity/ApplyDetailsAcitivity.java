package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.Text2Bitmap;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请详情
 */
public class ApplyDetailsAcitivity extends TitleNavigationActivity {
    @BindView(R.id.tv_approvals)
    TextView mTvApprovals;
    @BindView(R.id.rv_cc)
    SwipeMenuRecyclerView mRvCc;
    @BindView(R.id.rv_schedule)
    SwipeMenuRecyclerView mRvSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_details);
        showUserDefinedNav();
        useButterKnife();
        setTitle("申请详情");
        initView();
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
                ccImage.setImageBitmap(Text2Bitmap.getNameBitMap(item, getResources().getColor(R.color.white)));
                view.setText(item);
            }
        };

        new BaseRecyclerAdapter<String>(mRvSchedule, ccArray, R.layout.item_apply_schedule) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {

                ImageView view = holder.getView(R.id.image_head_schedule);
                View viewt = holder.getView(R.id.view_top);
                View viewb = holder.getView(R.id.view_bottom);
                view.setImageBitmap(Text2Bitmap.getNameBitMap(item, getResources().getColor(R.color.white)));
                if (position == 0) {
                    viewt.setVisibility(View.INVISIBLE);
                } else {
                    viewt.setVisibility(View.VISIBLE);
                }
                if (mRvSchedule.getAdapter().getItemCount() - 1 == position) {
                    viewb.setVisibility(View.INVISIBLE);
                } else {
                    viewb.setVisibility(View.VISIBLE);
                }

            }
        };
    }

    @OnClick({R.id.tv_approvals, R.id.rv_cc})
    public void onClicks(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_approvals:
                break;
            case R.id.rv_cc:
                break;
        }
    }
}
