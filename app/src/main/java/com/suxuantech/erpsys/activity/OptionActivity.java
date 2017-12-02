package com.suxuantech.erpsys.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.SimpleStatusActivity;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 跳转选择的页面这里
 */
public class OptionActivity extends SimpleStatusActivity {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    /**
     * checkedData当前选中的，单选的话只认下表为0的那个才是
     * mAllData全部选项
     */
    ArrayList<String > checkedData,mAllData, currentChecked ;
    /**
     * 网络的数据，暂时没有留在
     */
    String Url;
    /**
     * 是否是多选
     */
    private boolean mMultiple;
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tv_nav_right://多选确定时候关闭页面的
                StringBuffer sb = new StringBuffer();
             for(String f:currentChecked){
               sb.append(f+"\n");
                 }
                 toast(sb.toString());
               // finish();
                break;
            case R.id.tv_nav_left:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        useButterKnife();
        showUsetDefinedNav();
        Intent intent = getIntent();
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (!mMultiple){
                    Intent intent1 = new Intent();
                    intent1.putExtra("result",mAllData.get(position));
                    toast(mAllData.get(position));
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            }

        });
        if (intent != null) {
                mMultiple = intent.getBooleanExtra("Multiple",false);
                //给个地址
                Url = intent.getStringExtra("Url");
                mAllData = intent.getStringArrayListExtra("All");
                checkedData = intent.getStringArrayListExtra("Checked");
                currentChecked= (ArrayList<String>) checkedData.clone();
                setTitle( intent.getStringExtra("Title"));
                if (Url == null) {
                    setData();
                }
                if (mMultiple){
                    setUseDefinedNavRightText(getString(R.string.complete));
                }

         }

        mRotateHeaderGridViewFrame.setLastUpdateTimeRelateObject(this);
        mRotateHeaderGridViewFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRotateHeaderGridViewFrame.refreshComplete();
                        if (Url==null){
                            stringBaseRecyclerAdapter.notifyDataSetChanged();
                            currentChecked= (ArrayList<String>) checkedData.clone();
                        }
                    }
                }, 2000);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mRotateHeaderGridViewFrame.setResistance(1.7f);
        mRotateHeaderGridViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mRotateHeaderGridViewFrame.setDurationToClose(200);
        mRotateHeaderGridViewFrame.setDurationToCloseHeader(1000);
        // default is false
        mRotateHeaderGridViewFrame.setPullToRefresh(false);
        // default is true
        mRotateHeaderGridViewFrame.setKeepHeaderWhenRefresh(true);
        if (Url!=null){
            mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRotateHeaderGridViewFrame.autoRefresh();
                }
            }, 100);
        }
        if (Url==null){
            mRecyclerView.loadMoreFinish(false, false);
        }
    }
    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 数据完更多数据，一定要掉用这个方法。
                    // 第一个参数：表示此次数据是否为空。
                    // 第二个参数：表示是否还有更多数据。
                    mRecyclerView.loadMoreFinish(false, true);
                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
                    // mRecyclerView.loadMoreError(0, "请求网络失败");
                }
            }, 1000);
        }
    };
    BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;
    private void setData() {
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        mRecyclerView.useDefaultLoadMore();
        mRecyclerView.setLoadMoreListener(mLoadMoreListener);
        if (!mMultiple){
            stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, mAllData, R.layout.item_textview) {
                @Override
                public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                    TextView view = holder.getView(R.id.tv_item);
                    view.setText(item);
                    if (checkedData!=null&&checkedData.size()>0){
                        String s = checkedData.get(0);
                        if(item.equals(s)){
                            view.setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                        }
                    }

                }
            };
        }else {
            stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, mAllData, R.layout.item_checktextview) {
                @Override
                public void convert(RecyclerHolder holder, String item, final int position, boolean isScrolling) {
                    CheckedTextView view = holder.getView(R.id.tv_checked);
                    view.setText(item);
                    view.setChecked(false);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckedTextView checkedTextView = (CheckedTextView)v;
                            checkedTextView.toggle();
                            if (checkedTextView.isChecked()){
                              currentChecked.add( mAllData.get(position)) ;
                            }else {
                                currentChecked.remove( mAllData.get(position)) ;
                            }
                        }
                    });
                    if (currentChecked!=null){
                        if (currentChecked.contains(item)){
                            view.setChecked(true);
                        }else {
                            view.setChecked(false);
                        }
                    }
                }
            };
        }

    }

}
