package com.suxuantech.erpsys.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图书管理
 */
public class BookManageActivity extends ImmersedBaseActivity {

    @BindView(R.id.tiet_nav_search)
    OneKeyClearAutoCompleteText mTietNavSearch;
    @BindView(R.id.rv_book_recently_on)
    SwipeMenuRecyclerView mRvBookRecentlyOn;
    @BindView(R.id.rv_book_type)
    SwipeMenuRecyclerView mRvBookType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);
        useButterKnife();
        showUserDefinedNav();
        setTitle("图书管理");
        setUseDefinedNavRightText("我的借阅");
        initView();
    }

    private void initView() {
        ArrayList<String> Strings = new ArrayList<>();
        Strings.add("类别1");
        Strings.add("类别2");
        Strings.add("类别3");
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.line));
        defaultItemDecoration.setJustLeftOffsetX(30);
        mRvBookType.addItemDecoration(defaultItemDecoration);
        new BaseRecyclerAdapter<String>(mRvBookType,Strings,R.layout.item_textview){
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_item);
                Drawable drawable = getResources().getDrawable(R.drawable.arrows_right_gray);
                drawable.setBounds(0,0,30,30);
                view.setCompoundDrawablesRelative(null,null,drawable,null);
                view.setText(item);
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRvBookRecentlyOn.setLayoutManager(gridLayoutManager);
        DefaultItemDecoration defaultItemDecoration1 = new DefaultItemDecoration(getResources().getColor(R.color.line));
        mRvBookRecentlyOn.addItemDecoration(defaultItemDecoration1);
        new BaseRecyclerAdapter<String>(mRvBookRecentlyOn,Strings,R.layout.item_book){
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
            }
        };

    }

    @OnClick({R.id.tiet_nav_search, R.id.rv_book_recently_on, R.id.rv_book_type})
    public void onClicks(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tiet_nav_search:
                break;
            case R.id.rv_book_recently_on:
                break;
            case R.id.rv_book_type:
                break;
        }
    }
}
