package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.HistoryBeanDao;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.views.DefaultItemDecoration;
import com.suxuantech.erpsys.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.adapter.RecyclerHolder;
import com.suxuantech.erpsys.bean.HistoryBean;
import com.suxuantech.erpsys.utils.L;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.views.AdjustDrawableTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
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
 * @author Created by 李站旗 on 2017/11/18 0018 16:16 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  订单搜索页面
 */
public class SearchOrderActivity extends AppCompatActivity {

    @BindView(R.id.tv_nav_left)
    AdjustDrawableTextView mTvNavLeft;
    @BindView(R.id.tiet_nav_search)
    TextInputEditText mTietNavSearch;
    @BindView(R.id.tv_nav_right)
    AdjustDrawableTextView mTvNavRight;
    @BindView(R.id.smr_history)
    SwipeMenuRecyclerView mSmrHistory;
    @BindView(R.id.ptr_refresh)
    PtrClassicFrameLayout mPtrRefresh;

    @BindView(R.id.btn_clear_search_history)
    Button mBtn_clear;
    private HistoryBeanDao historyDao;
    private BaseRecyclerAdapter<HistoryBean> historyAdapter;
    private List<HistoryBean> searchHosiery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        ButterKnife.bind(this);
        initDB();
        mPtrRefresh.addPtrUIHandler(new PtrClassicDefaultHeader(this));
        mPtrRefresh.setPullToRefresh(false);
        searchHosiery = getSearchHosiery();
            if (searchHosiery.size()<=0){
                mBtn_clear.setVisibility(View.GONE);
            }
       mSmrHistory.setLayoutManager(new LinearLayoutManager(this));
        mSmrHistory.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ToastUtils.show(position+"");
            }
        });
         historyAdapter = new BaseRecyclerAdapter<HistoryBean>(mSmrHistory, searchHosiery, R.layout.item_search_history) {
            @Override
            public void convert(RecyclerHolder holder, HistoryBean item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_history);
                view.setText(item.getName());
            }
        };

//        historyAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, Object data, int position) {
//              ToastUtils.show("99==-->"+position);
//            }
//        });

            mSmrHistory.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7),0,3)    );
            mSmrHistory.setAdapter(historyAdapter);
            int height = mSmrHistory.getHeight();
              int i = mSmrHistory.computeVerticalScrollExtent();
             L.d(i+"======"+height);

    }
    private void initDB() {
        //初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "myhistory.db", null);
        //不清楚
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //表的集合
         DaoSession daoSession = daoMaster.newSession();
         //历史表
         historyDao = daoSession.getHistoryBeanDao();
    }
    @OnClick({R.id.btn_clear_search_history,R.id.tv_nav_left, R.id.tiet_nav_search, R.id.tv_nav_right, R.id.smr_history, R.id.ptr_refresh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear_search_history:
                diar();
                break;
            case R.id.tv_nav_left:
                finish();
                break;
            case R.id.tiet_nav_search:
                break;
            case R.id.tv_nav_right:
                search();
                break;
            case R.id.smr_history:
                break;
            case R.id.ptr_refresh:
                break;
                default:
        }
    }
    public void diar(){
        new AlertView("清除历史记录","确认清除历史记录", null, new String[]{"确定", "取消"}, null, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position==0){
                    //删除表内数据
                    historyDao.deleteAll();
                    mBtn_clear.setVisibility(View.GONE);
                    searchHosiery.clear();
                    historyAdapter.notifyDataSetChanged(searchHosiery);
                }
            }
        }).show();
    }

    /**
     * 搜索
     */
    private void search() {
        String trim = mTietNavSearch.getText().toString().trim();
        if (trim.isEmpty()){
            Snackbar.make(mTietNavSearch,"搜索内容空",Snackbar.LENGTH_SHORT)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();
            return;
        }
        insert(trim);
//        mPtrRefresh.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        mPtrRefresh.setPullToRefresh(true);
        //刷新
    }
    /**
     *    插入搜索历史
     */
    public void insert(String trim){
        //判断是否存在数据库中，如果存在则不再存储
            for(HistoryBean h:searchHosiery){
                    String name = h.getName();
                    if (name.equals(trim)){
                        return;
                    }
                }
        HistoryBean studentMsgBean = new HistoryBean();
        studentMsgBean.setName(trim);
        historyDao.insert(studentMsgBean);
        mBtn_clear.setVisibility(View.VISIBLE);
        searchHosiery =getSearchHosiery();
        historyAdapter.notifyDataSetChanged(searchHosiery);
    }

    /**
     * 获取搜索历史
     */
    public List<HistoryBean> getSearchHosiery(){
        List<HistoryBean> historyBeans = historyDao.loadAll();
        //倒序下，因为最新的要在上面显示
        Collections.reverse(historyBeans);
        return   historyBeans;
    }
}
