package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 服务详情列表
 */
public class ServiceDetailListActivity extends StatusImmersedBaseActivity {

    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        ButterKnife.bind(this);
        init();
    }
    @Override
    protected void widgetClick(View v) {
    }
    private void init() {
        mRotateHeaderGridViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mRotateHeaderGridViewFrame.setDurationToClose(200);
        mRotateHeaderGridViewFrame.setDurationToCloseHeader(1000);
        // default is false
        mRotateHeaderGridViewFrame.setPullToRefresh(true);
        mRotateHeaderGridViewFrame.getHeader().setBackground(getResources().getDrawable(R.drawable.logo));
        // default is true
        mRotateHeaderGridViewFrame.setKeepHeaderWhenRefresh(true);
        //刷新完成    这么用才有效(不设置这个会吃掉子view)
        mRotateHeaderGridViewFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRotateHeaderGridViewFrame.refreshComplete();
                    }
                }, 3000);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        ArrayList<String> objects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.order)));
        objects.add("1111");        objects.add("1111");        objects.add("1111");        objects.add("1111");        objects.add("1111");
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, objects, R.layout.item_service_detail) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mRotateHeaderGridViewFrame.refreshComplete();//只要滑动就设置为刷新完成不然会吃掉recycleview的
            }
        });
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7),0,30));
    }
}
