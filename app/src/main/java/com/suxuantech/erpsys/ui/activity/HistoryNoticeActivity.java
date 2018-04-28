package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter;
import com.suxuantech.erpsys.ui.fragment.BorrowFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoryNoticeActivity extends TitleNavigationActivity {
//    @BindView(R.id.recycler_view)
//    SwipeMenuRecyclerView mRecyclerView;
//    @BindView(R.id.rotate_header_grid_view_frame)
//    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    ArrayList<BorrowFragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_control);
        showUserDefinedNav();
        useButterKnife();
        setTitle("公告");
        View view  = findViewById(R.id.view_tl);
        view.setVisibility(View.INVISIBLE);
        getLineView().setVisibility(View.INVISIBLE);
        fragments = new ArrayList();
        fragments.add(new BorrowFragment());
        fragments.add(new BorrowFragment());
         String[]   stringArray = getResources().getStringArray(R.array.notice_status);
        ArrayList title=   new ArrayList(Arrays.asList(stringArray));
        TabLayout thBorrow= findViewById(R.id.tablayout);
        ViewPager viewPager= findViewById(R.id.vp);
        DefaultFragmentAdapter  adapter = new DefaultFragmentAdapter(getSupportFragmentManager(), title, (p)->{return  fragments.get(p);} );
        viewPager.setAdapter(adapter);
        thBorrow.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            fragments.get(position).setText("公告"+position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
//    @Override
//    @OnClick({R.id.recycler_view, R.id.rotate_header_grid_view_frame})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_nav_left:
//                finish();
//                break;
//            default:
//            case R.id.recycler_view:
//                break;
//            case R.id.rotate_header_grid_view_frame:
//                break;
//        }
//    }

/*
    ArrayList<String> strings = new ArrayList<>();
        for (int i = 0;i<10;i++){
        strings.add("1111222");
    }
//        mRecyclerView.useDefaultLoadMore();
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            startActivity(NoticeDetailActivity.class);
        }
    });
        new BaseRecyclerAdapter<String>(mRecyclerView,strings , R.layout.item_notice) {
        @Override
        public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
            TextView textView = holder.getView(R.id.tv_notice_title);
            //这里换行标签是<br/>
            //font 的seize 无效 color可以
            //small有效
            //strike删除线有效
            //big有效 ，b也行
            //上标文本sup
            //下表文本sup
            textView.setText(
                    Html.fromHtml("<font size=\"60\" color='"+getResources().getColor(R.color.myValue_33)+"'>今天天气好吗？</font>" +
                            "<font size=\"12\"color='"+getResources().getColor(R.color.textHint_99)+"'><br/><small>挺好的</small></font>"));
        }
    };
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
*/

}
