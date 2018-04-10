package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.beans.ContactBean;
import com.suxuantech.erpsys.chat.ConversationActivity;
import com.suxuantech.erpsys.ui.activity.base.ContactsActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.utils.GlideRoundTransform;
import com.suxuantech.erpsys.utils.Text2Bitmap;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.im.android.api.JMessageClient;
import me.yokeyword.fragmentation.SupportFragment;


public class ContactsFragment extends SupportFragment {
    @BindView(R.id.rl_organization)
    SwipeMenuRecyclerView mRlOrganization;
    @BindView(R.id.rl_recent_contact)
    SwipeMenuRecyclerView mRlRecentContact;
    @BindView(R.id.ll_recent_contact)
    LinearLayout mLlRecentContact;
    private View view;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<ContactBean> objects = new ArrayList<ContactBean>();
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i <30; i++) {
            if (i<10){
                strings.add("部门"+i);
            }
            if (i % 5 != 0) {
                objects.add(new ContactBean("u" + i, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2511196149,1814813928&fm=111&gp=0.jpg", "", i + "德玛"));
            } else if (i/8==0){
                objects.add(new ContactBean("u" + i, "", "", ""));
            }else {
                objects.add(new ContactBean("u" + i, "", "", i + "孙"));
            }
        }
        mRlOrganization. setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                start(new ContactsFragment());
               startActivityForResult(          new Intent(getActivity(), ContactsActivity.class),15);
            }
        });

        mRlRecentContact.setNestedScrollingEnabled(false);
        mRlOrganization.setNestedScrollingEnabled(false);
        mRlRecentContact.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position==0){
                    if (!JMessageClient.getMyInfo().getUserName().equals("10086")){
                    Intent intent = new Intent(getActivity(), ConversationActivity.class);
                    intent.putExtra("name", "10086");
                    startActivity(intent);
                    }
                }else {
                    if (!JMessageClient.getMyInfo().getUserName().equals("123456")) {
                        Intent intent = new Intent(getActivity(), ConversationActivity.class);
                        intent.putExtra("name", "123456");
                        startActivity(intent);
                    }
                }
            }
        });
        new BaseRecyclerAdapter<ContactBean>(mRlRecentContact, objects, R.layout.item_contact) {
            @Override
            public void convert(RecyclerHolder holder, ContactBean item, int position, boolean isScrolling) {
                ImageView imagHead = holder.getView(R.id.img_contact_head);
                TextView contanTextView = holder.getView(R.id.tv_contact_name);
                contanTextView.setText(item.getName());
                if (android.text.TextUtils.isEmpty(item.getHead())&&!android.text.TextUtils.isEmpty(item.getName())) {
                    imagHead.setImageBitmap(Text2Bitmap.getNameBitMap(item.getName(),getResources().getColor(R.color.white)));
                }else  if (android.text.TextUtils.isEmpty(item.getHead())&&android.text.TextUtils.isEmpty(item.getName())){
                    imagHead.setImageBitmap(Text2Bitmap.getNewBitMap("未知",getResources().getColor(R.color.white)));
                }else {
                    RequestOptions options2 = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher)//预加载图片
                            .error(R.mipmap.ic_launcher)//加载失败显示图片
                            .priority(Priority.HIGH)//优先级
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                            .transform(new GlideRoundTransform(10));//转化为圆形
                    Glide.with(getActivity()).load(item.getHead()).apply(options2).into(imagHead);
                }
            }
        };
        new BaseRecyclerAdapter<String>(mRlOrganization, strings, R.layout.item_department) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
            TextView tv=    holder.getView(R.id.tv_department);
            tv.setText(item);
            }
        };

    }
}


