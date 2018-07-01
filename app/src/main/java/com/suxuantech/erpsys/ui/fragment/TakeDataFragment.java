package com.suxuantech.erpsys.ui.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
import com.suxuantech.erpsys.utils.GlideRoundTransform;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.util.ArrayList;
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
    ArrayList<Drawable> drawables = new ArrayList<>();

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
        drawables.add(getResources().getDrawable(R.drawable.product_1));
        drawables.add(getResources().getDrawable(R.drawable.product_2));
        drawables.add(getResources().getDrawable(R.drawable.product_3));
        drawables.add(getResources().getDrawable(R.drawable.product_4));
        initRefresh();
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initRefresh() {
        smartRefreshLayout.setEnableAutoLoadMore(false);//开启自动加载功能（非必须）
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

    public void requestTakeData() {
        String takedata = Contact.getFullUrl(Contact.CUSTOMER_TAKEDATA, Contact.TOKEN, getArguments().get("orderId"), App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TakeDataEntity> TakeDataEntityJavaBeanRequest = new JavaBeanRequest<TakeDataEntity>(takedata, RequestMethod.POST, TakeDataEntity.class);
        HttpListener<TakeDataEntity> searchByCustmor = new HttpListener<TakeDataEntity>() {
            @Override
            public void onSucceed(int what, Response<TakeDataEntity> response) {
                if (response.get().isOK()) {
                    setAdaputer(response.get().getData());
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.setNoMoreData(true);
                } else {
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
    public Drawable randomDrawable(){
        int x=(int)(Math.random()*50);
        int i = x % drawables.size();
        return   drawables.get(i);
    }
    private void setAdaputer(List<TakeDataEntity.DataBean> data) {
        if (data != null) {
            QuickAdapter<TakeDataEntity.DataBean> dataBeanQuickAdapter = new QuickAdapter<TakeDataEntity.DataBean>(R.layout.item_takedata, data) {
                @Override
                protected void convert(BaseViewHolder helper, TakeDataEntity.DataBean item) {
                    TextView view = helper.getView(R.id.tv_take_info);
                    ImageView Image = helper.getView(R.id.img_product);
                    TextView productName = helper.getView(R.id.tv_product_name);
                    TextView productStatus = helper.getView(R.id.tv_product_status);
                    RequestOptions options2 = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher)//预加载图片
                            .error(R.mipmap.ic_launcher)//加载失败显示图片
                            .priority(Priority.HIGH)//优先级
                            .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
                            .transform(new GlideRoundTransform(10));//转化为圆形
                    Glide.with(Image).load(randomDrawable()).apply(options2).into(Image);
                    productName.setText(new MyString(StringUtils.safetyString(item.getConsumption_name())).setColor(getResources().getColor(R.color.textColor)));
                    productStatus.setText(item.getWhether_already_take() + "\n");
                    productStatus.append(new MyString("x " + item.getAmount()));
                    String string = StringUtils.safetyString(item.getTake_can_date());
                    if (string.length() > 10) {
                        string = string.substring(0, 10);
                    }
                    String string2 = StringUtils.safetyString(item.getTake_away_date());
                    if (string2.length() > 10) {
                        string2 = string2.substring(0, 10);
                    }
                    view.setText("整件日期:" + string);
                    view.append("\n取件日期:" + string2);
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
                            bundle.putParcelable("data", dataBean);
                            bundle.putString("dad", "你妹");
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