package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.Text2Bitmap;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class MyApproveActivity extends ImmersedBaseActivity {

    @BindView(R.id.tiet_nav_search)
    OneKeyClearAutoCompleteText mTietNavSearch;
    @BindView(R.id.spinner_approve_status)
    Spinner mSpinnerApproveStatus;
    @BindView(R.id.spinner_type)
    Spinner mSpinnerType;
    @BindView(R.id.rv_applys)
    SwipeMenuRecyclerView mRvApplys;
    private static final String[] m={"A型","B型","O型","AB型","其他"};
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_approve);
        useButterKnife();
        showUserDefinedNav();
        setTitle("我的审批");
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        mSpinnerType.setAdapter(adapter);

        //添加事件Spinner事件监听
        mSpinnerType.setOnItemSelectedListener(new SpinnerSelectedListener());
        ArrayList<String> objects = new ArrayList<String>();
        for (int i=0;i<10;i++){
            objects.add("ii"+i);
        }
        BaseRecyclerAdapter<String>      你吃          = new BaseRecyclerAdapter<String>(mRvApplys,objects,R.layout.item_approve){
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
              ImageView imageView = holder.getView(R.id.image_head_approve);
                imageView.setImageBitmap(Text2Bitmap.getNameBitMap(item,getResources().getColor(R.color.white)));
            }
        };

    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
