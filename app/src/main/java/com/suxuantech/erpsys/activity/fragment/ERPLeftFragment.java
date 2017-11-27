package com.suxuantech.erpsys.activity.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.OrderDetailActivity;
import com.suxuantech.erpsys.activity.SearchOrderActivity;
import com.suxuantech.erpsys.views.WaveHelper;
import com.suxuantech.erpsys.views.WaveView;
import com.yanzhenjie.statusview.StatusUtils;


public class ERPLeftFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_erp_left, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WaveView taskWave= view.findViewById(R.id.task_wave);
        WaveView scheduleWave= view.findViewById(R.id.schedule_wave);
        taskWave.setWaveColor(getResources().getColor(R.color.wavel),getResources().getColor(R.color.wave),getResources().getColor(R.color.wavebg));
        scheduleWave.setWaveColor(getResources().getColor(R.color.wavel),getResources().getColor(R.color.wave),getResources().getColor(R.color.wavebg));
        WaveHelper taskWaveHelper = new WaveHelper(taskWave);
        taskWaveHelper.start();
        WaveHelper scheduleWaveHelper = new WaveHelper(scheduleWave);
        scheduleWaveHelper  .start();
        TextView viewById = view.findViewById(R.id.tv_info);
        viewById.setSelected(true);
        StatusUtils.setFullToStatusBar(getActivity());
        view.findViewById(R.id.outlets_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SearchOrderActivity.class));
            }
        });
        view.findViewById(R.id.tv_order_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),OrderDetailActivity  .class));
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
