package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;
import com.yanzhenjie.statusview.StatusUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务费用
 */
public class ServiceFeeActivity extends StatusImmersedBaseActivity {

    @BindView(R.id.smrv_service_fee)
    SwipeMenuRecyclerView mSmrvServiceFee;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_fee);
        showUsetDefinedNav();
        setUseDefinedNavTitle(getString(R.string.service_fee));
        StatusUtils.setFullToStatusBar(this);
        getStatusView().setBackground(null);
        StatusUtils.setSystemBarColor(this,getResources().getColor(R.color.translucent_black_90),getResources().getColor(R.color.translucent_black_90));
        StatusUtils.setStatusBarColor(this,getResources().getColor(R.color.translucent_black_90));
        ButterKnife.bind(this);
         View head = getLayoutInflater().inflate(R.layout.head_service_fee, null);
        View footer = getLayoutInflater().inflate(R.layout.footer_add_service_fee, null);
        mSmrvServiceFee.addHeaderView(head);
       mSmrvServiceFee.addFooterView(footer);
        ArrayList<String> strings = new ArrayList<>();
        for(int i =0;i<15;i++){
            strings.add("挚爱永恒"+i);
        }
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("新增");
            }
        });
       BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mSmrvServiceFee, strings, R.layout.item_service_fee) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_service_name);
                view.setText(item);
                TextView tv_serial_number = holder.getView(R.id.tv_serial_number);
                tv_serial_number.setText(position+1+"");
            }
        };
        mSmrvServiceFee.setLayoutManager(new LinearLayoutManager(this));
        mSmrvServiceFee.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        mSmrvServiceFee.setAdapter(baseRecyclerAdapter);
    }

    @Override
    protected void widgetClick(View v) {

    }
}
