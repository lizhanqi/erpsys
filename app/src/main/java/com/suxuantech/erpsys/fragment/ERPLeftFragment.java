package com.suxuantech.erpsys.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.HistoryNoticeActivity;
import com.suxuantech.erpsys.activity.NoticeDetailActivity;
import com.suxuantech.erpsys.activity.OutletsOrderActivity;
import com.suxuantech.erpsys.activity.SearchOrderActivity;
import com.suxuantech.erpsys.activity.base.BaseLazyFragment;
import com.suxuantech.erpsys.dialog.NoticeDialog;
import com.suxuantech.erpsys.utils.ScreenUtils;
import com.suxuantech.erpsys.views.WaveHelper;
import com.suxuantech.erpsys.views.WaveView;
import com.yanzhenjie.statusview.StatusUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ERPLeftFragment extends BaseLazyFragment {

    @BindView(R.id.tv_msg_number)
    TextView mTvMsgNumber;
    @BindView(R.id.rl_msg)
    RelativeLayout mRlMsg;
    @BindView(R.id.tv_notice_details)
    TextView mTvNoticeDetails;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_erp_left;
    }
    @Override
    public void initImmersionBar() {
        if (getActivity()!=null){
            super.initImmersionBar();
            mImmersionBar.statusBarDarkFont(false).navigationBarColor(R.color.themeColor).init();
        }
    }

    public void alertShow4() {
        AlertView alertView = new AlertView("标题", null, "取消",
                new String[]{"高亮按钮1"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3"},
                getActivity(), AlertView.Style.ACTIONSHEET, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {

            }
        });
        if (ScreenUtils.checkDeviceHasNavigationBar(getContext())) {
            alertView.setPaddingBottom(ScreenUtils.getNavigationBarHeight(getContext()));
        }
        alertView.show();
    }

    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NoticeDialog.Builder nb = new NoticeDialog.Builder(getContext());

            nb.setTitleLeftDrawable(getResources().getDrawable(R.drawable.icon_msg_task));
            nb.setDigestText("消息简介\n日期那年月日");
            nb.setTitleText("新任务:");
            nb.setOnClickDetails(new NoticeDialog.OnClickDetails() {
                @Override
                public void onClickDetails() {
                    startActivity(new Intent(getActivity(), com.suxuantech.erpsys.activity.NoticeDetailActivity.class));
                }
            });
            nb.build().show();

        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setStatusBarView(getActivity(), mRootView.findViewById(R.id.tv_company_name));
        WaveView taskWave = view.findViewById(R.id.task_wave);
        WaveView scheduleWave = view.findViewById(R.id.schedule_wave);
        taskWave.setWaveColor(getResources().getColor(R.color.wavel), getResources().getColor(R.color.wave), getResources().getColor(R.color.wavebg));
        scheduleWave.setWaveColor(getResources().getColor(R.color.wavel), getResources().getColor(R.color.wave), getResources().getColor(R.color.wavebg));
        WaveHelper taskWaveHelper = new WaveHelper(taskWave);
        taskWaveHelper.start();
        WaveHelper scheduleWaveHelper = new WaveHelper(scheduleWave);
        scheduleWaveHelper.start();
        StatusUtils.setFullToStatusBar(getActivity());
        taskWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hd.sendEmptyMessageDelayed(0, 5000);
            }
        });
        view.findViewById(R.id.outlets_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            alertShow4();
//              new AlertView("标题", "内容", "取消", new String[]{"确定"}, null, getContext(), AlertView.Style.ALERT, new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(Object o, int position) {
//
//                    }
//                }).setCancelable(true).setOnDismissListener(new OnDismissListener() {
//                    @Override
//                    public void onDismiss(Object o) {
//
//                    }
//                }).setDivierMargin(30).show();

                startActivity(new Intent(getActivity(), OutletsOrderActivity.class));
            }
        });

        view.findViewById(R.id.tv_order_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchOrderActivity.class));
//                Intent intent = new Intent(getActivity(), OptionActivity.class);
//                OptionHelp multiple = new OptionHelp(getActivity()).setMultiple(true);
//                multiple.setAllData(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                multiple.setCheckedData(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                multiple.setCheckedData("礼服");
//                multiple.setTitle("126");
////                multiple.setUrl("11111");
//
//                intent.putExtra("All",new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                intent.putExtra("Checked",new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))));
//                intent.putExtra("Title","选择");
//                intent.putExtra("Multiple",true);
//                startActivity(multiple.creat());
            }
        });
    }
    @OnClick({R.id.tv_msg_number, R.id.rl_msg, R.id.tv_notice_details})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_msg_number:
                break;
            case R.id.rl_msg:
                startActivity(new Intent(getActivity(), HistoryNoticeActivity.class));
                break;
            case R.id.tv_notice_details:
                startActivity(new Intent(getActivity(), NoticeDetailActivity.class));
                break;
        }
    }
}
