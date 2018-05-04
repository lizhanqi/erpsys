package com.suxuantech.erpsys.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.TakeDataEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.TakeDataDetailsActivity;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.utils.MyString;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 取件资料
 */
public class TakeDataFragment extends BaseSupportFragment {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
//    @BindView(R.id.rotate_header_grid_view_frame)
//    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    @BindView(R.id.srl_fresh)
    SmartRefreshLayout smartRefreshLayout;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //这里一定要ButterKnife和返回的view是同一个,不然布局显示不出来
        View inflate = inflater.inflate(R.layout.smartrefresh_and_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefresh();
      mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        mRotateHeaderGridViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        mRotateHeaderGridViewFrame.setDurationToClose(200);
//        mRotateHeaderGridViewFrame.setDurationToCloseHeader(1000);
//        // default is false
//        mRotateHeaderGridViewFrame.setPullToRefresh(true);
//        mRotateHeaderGridViewFrame.getHeader().setBackground(getResources().getDrawable(R.drawable.logo));
//        // default is true
//        mRotateHeaderGridViewFrame.setKeepHeaderWhenRefresh(true);
//      //刷新完成    这么用才有效(不设置这个会吃掉子view)
//      mRotateHeaderGridViewFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRotateHeaderGridViewFrame.refreshComplete();
//                    }
//                }, 3000);
//            }
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//        });
//        ArrayList<String> objects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.order)));
//        objects.add("1111");        objects.add("1111");        objects.add("1111");        objects.add("1111");        objects.add("1111");
//        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, objects, R.layout.item_takedata) {
//            @Override
//            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
//                TextView view1 = holder.getView(R.id.tv_commodity);
//                view1.setText(item);
//            }
//        };
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                mRotateHeaderGridViewFrame.refreshComplete();//只要滑动就设置为刷新完成不然会吃掉recycleview的
//            }
//        });
//          mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
    }

    private void initRefresh() {
        smartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                        requestTakeData();
            }
        });

        //触发自动刷新
        smartRefreshLayout.autoRefresh();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void requestTakeData( ) {
        String takedata = Contact.getFullUrl(Contact.CUSTOMER_TAKEDATA, Contact.TOKEN, getArguments().get("orderId"), App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TakeDataEntity> TakeDataEntityJavaBeanRequest = new JavaBeanRequest<TakeDataEntity>(takedata, RequestMethod.POST, TakeDataEntity.class);
        HttpListener<TakeDataEntity> searchByCustmor = new HttpListener<TakeDataEntity>() {
            @Override
            public void onSucceed(int what, Response<TakeDataEntity> response) {
                if (response.get().isOK()){
                    setAdaputer(response.get().getData());
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.setNoMoreData(false);
                }else {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.setNoMoreData(true);
                }
            }
                @Override
            public void onFailed(int what, Response<TakeDataEntity> response) {
            }
        };
        request(0, TakeDataEntityJavaBeanRequest, searchByCustmor, false, false);
    }

    private void setAdaputer(List<TakeDataEntity.DataBean> data) {
        if (data!=null){
            QuickAdapter<TakeDataEntity.DataBean> dataBeanQuickAdapter = new QuickAdapter<TakeDataEntity.DataBean>(R.layout.item_takedata, data) {
                @Override
                protected void convert(BaseViewHolder helper, TakeDataEntity.DataBean item) {
                    TextView view = helper.getView(R.id.tv_take_info);
                    TextView productName = helper.getView(R.id.tv_product_name);
                    TextView productStatus = helper.getView(R.id.tv_product_status);
                    productName.setText(item.getConsumption_name());
                    productStatus.setText(item.getWhether_already_take()+"\n");
                    productStatus.append(new MyString("x "+item.getAmount()));
                    view.setText("整件日期:"+item.getTake_can_date());
                    view.append("\n取件日期:"+item.getTake_away_date() );
                }
            };
            mRecyclerView.setAdapter(dataBeanQuickAdapter);
            dataBeanQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    dataBeanQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            TakeDataEntity.DataBean dataBean = data.get(position);
                            Intent intent = new Intent(getActivity(), TakeDataDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("data",dataBean);
                            bundle.putString("dad","你妹");
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                            //startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}