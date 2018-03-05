package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 服务费用
 */
public class ServiceFeeFragment extends SupportFragment {
    @BindView(R.id.smrv_service_fee)
    SwipeMenuRecyclerView mSmrvServiceFee;
    private View view;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_service_fee, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View head = getLayoutInflater().inflate(R.layout.head_service_fee, null);
        View footer = getLayoutInflater().inflate(R.layout.footer_add_service_fee, null);
        mSmrvServiceFee.addHeaderView(head);
        mSmrvServiceFee.addFooterView(footer);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            strings.add("挚爱永恒" + i);
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
                tv_serial_number.setText(position + 1 + "");
            }
        };
        mSmrvServiceFee.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
    }

    @OnClick(R.id.smrv_service_fee)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smrv_service_fee:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
