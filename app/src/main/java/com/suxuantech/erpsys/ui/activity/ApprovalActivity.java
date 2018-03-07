package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on ${DATE} ${TIME} .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  审批
 */
public class ApprovalActivity extends ImmersedBaseActivity {

    @BindView(R.id.tv_apply_my)
    TextView mTvApplyMy;
    @BindView(R.id.tv_approval_my)
    TextView mTvApprovalMy;
    @BindView(R.id.tv_cc_me)
    TextView mTvCcMe;
    @BindView(R.id.ll_apply_common)
    TextView mLlApplyCommon;
    @BindView(R.id.rv_apply_for)
    SwipeMenuRecyclerView mRvApplyFor;
    @BindView(R.id.rv_apply_other)
    SwipeMenuRecyclerView mRvApplyOther;
    @BindView(R.id.ll_apply_other)
    LinearLayout mLlApplyOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        useButterKnife();
        showUserDefinedNav();
        setTitle("审批");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("请假申请");
        strings.add("外出申请");
        strings.add("录用申请");
        strings.add("印章申请");
        strings.add("营业执照申请");
        strings.add("离职申请");
        ArrayList<String> string2 = new ArrayList<>();
        string2.add("其他申请1");
        string2.add("其他申请2");
        string2.add("其他申请3");
        mRvApplyFor.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                startActivity(PunchCardActivity.class);
            }
        });
        mRvApplyFor.setLayoutManager(new GridLayoutManager(this,3));
        new BaseRecyclerAdapter<String>(mRvApplyFor,strings,R.layout.item_approval){

            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_approval);
                view.setSelected(true);
                view.setText(item);
            }
        };
        mRvApplyOther.setLayoutManager(new GridLayoutManager(this,3));
        new BaseRecyclerAdapter<String>(mRvApplyOther,string2,R.layout.item_approval){

            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_approval);
                view.setSelected(true);
                view.setText(item);
            }
        };
    }

    @OnClick({R.id.tv_apply_my, R.id.tv_approval_my, R.id.tv_cc_me, R.id.ll_apply_common, R.id.rv_apply_for, R.id.rv_apply_other, R.id.ll_apply_other})
    public void onClicks(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_apply_my:
                startActivity(ApplyDetailsAcitivity.class);
                break;
            case R.id.tv_approval_my:
                startActivity(LeaveRequestActivity.class);
                break;
            case R.id.tv_cc_me:
                startActivity(MyApproveActivity.class);
                break;
            case R.id.ll_apply_common:
                break;
            case R.id.rv_apply_for:
                break;
            case R.id.rv_apply_other:
                break;
            case R.id.ll_apply_other:
                break;
        }
    }
}
