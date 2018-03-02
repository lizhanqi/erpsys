package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class HistoryNoticeActivity extends ImmersedBaseActivity {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        showUserDefinedNav();
        useButterKnife();
        setTitle(getString(R.string.history_notice));
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0;i<10;i++){
            strings.add("1111222");
        }
//        mRecyclerView.useDefaultLoadMore();
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                startActivity(NoticeDetailActivity.class);
            }
        });
        new BaseRecyclerAdapter<String>(mRecyclerView,strings , R.layout.item_notice) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView textView = holder.getView(R.id.tv_notice_title);
                //这里换行标签是<br/>
                //font 的seize 无效 color可以
                //small有效
                //strike删除线有效
                //big有效 ，b也行
                //上标文本sup
                //下表文本sup
      textView.setText(
              Html.fromHtml("<font size=\"60\" color='"+getResources().getColor(R.color.myValue_33)+"'>今天天气好吗？</font>" +
              "<font size=\"12\"color='"+getResources().getColor(R.color.textHint_99)+"'><br/><small>挺好的</small></font>"));
            }
        };
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));

    }
    @Override
    @OnClick({R.id.recycler_view, R.id.rotate_header_grid_view_frame})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                finish();
                break;
            default:
            case R.id.recycler_view:
                break;
            case R.id.rotate_header_grid_view_frame:
                break;
        }
    }
}
