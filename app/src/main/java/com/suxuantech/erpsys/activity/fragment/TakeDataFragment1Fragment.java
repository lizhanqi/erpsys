package com.suxuantech.erpsys.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeDataFragment1Fragment extends Fragment {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //这里一定要ButterKnife和返回的view是同一个,不然布局显示不出来
        View inflate = inflater.inflate(R.layout.refresh_and_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, objects, R.layout.item_takedata) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view1 = holder.getView(R.id.tv_commodity);
                view1.setText(item);
            }
        };
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mRotateHeaderGridViewFrame.refreshComplete();//只要滑动就设置为刷新完成不然会吃掉recycleview的
            }
        });
          mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
