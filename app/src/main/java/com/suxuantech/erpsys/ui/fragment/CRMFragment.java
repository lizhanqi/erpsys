package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;

public class CRMFragment extends SupportFragment {
    TextView mTvFm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_crm, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.with(getActivity()).fitsSystemWindows(true).barColor(R.color.themeColor).fullScreen(false).init();
        view.findViewById(R.id.tv_fm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("CRM");
            }
        });
        mTvFm =   view.findViewById(R.id.tv_fm);
        mTvFm.setText("CRM");
    }
    public static CRMFragment newInstance() {
        Bundle args = new Bundle();
        CRMFragment fragment = new CRMFragment();
        fragment.setArguments(args);
        return fragment;
    }

/*    @Override
    protected int setLayoutId() {
        return R.layout.fragment_crm;
    }*/

/*    @Override
    protected void widgetClick(View v) {

    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
