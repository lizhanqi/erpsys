package com.suxuantech.erpsys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseNoFragment;
import com.suxuantech.erpsys.utils.ToastUtils;


public class MsgFragment extends BaseNoFragment {
    TextView mTvFm;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_msg, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tv_fm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("MSG");
            }
        });
        mTvFm =   view.findViewById(R.id.tv_fm);
        mTvFm.setText("MSG");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void widgetClick(View v) {

    }
}
