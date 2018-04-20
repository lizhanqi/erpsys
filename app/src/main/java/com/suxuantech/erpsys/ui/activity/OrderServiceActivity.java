package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单服务
 */
public class OrderServiceActivity extends ImmersedBaseActivity {

    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.customer_id)
    TextView mCustomerId;
    @BindView(R.id.tv_order_type)
    TextView mTvOrderType;
    @BindView(R.id.tv_subscribe_date)
    TextView mTvSubscribeDate;
    @BindView(R.id.tv_consume_type)
    TextView mTvConsumeType;
    @BindView(R.id.et_man_name)
    EditText mEtManName;
    @BindView(R.id.et_woman_name)
    EditText mEtWomanName;
    @BindView(R.id.tv_customer_source)
    TextView mTvCustomerSource;
    @BindView(R.id.tv_customer_zone)
    TextView mTvCustomerZone;
    @BindView(R.id.tv_order_receiving_site)
    TextView mTvOrderReceivingSite;
    @BindView(R.id.tv_outlets_reception)
    TextView mTvOutletsReception;
    @BindView(R.id.tv_reception)
    TextView mTvReception;
    @BindView(R.id.et_outlets_for_three)
    EditText mEtOutletsForThree;
    @BindView(R.id.tv_card_number)
    TextView mTvCardNumber;
    @BindView(R.id.tv_member_type)
    TextView mTvMemberType;
    @BindView(R.id.et_man_phone)
    EditText mEtManPhone;
    @BindView(R.id.et_woman_phone)
    EditText mEtWomanPhone;
    @BindView(R.id.tv_manager)
    TextView mTvManager;
    @BindView(R.id.tv_support_staff)
    TextView mTvSupportStaff;
    @BindView(R.id.tv_engagement_date)
    TextView mTvEngagementDate;
    @BindView(R.id.tv_marriage_date)
    TextView mTvMarriageDate;
    @BindView(R.id.et_customer_remarks)
    EditText mEtCustomerRemarks;
    @BindView(R.id.img_fold)
    ImageView mImgFold;
    @BindView(R.id.et_say_something)
    EditText mEtSaySomething;
    @BindView(R.id.smrv_say_something)
    SwipeMenuRecyclerView mSmrvSaySomething;
    @BindView(R.id.btn_start_service)
    Button mBtnStartService;
    @BindView(R.id.service_complete)
    Button mServiceComplete;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    private ArrayList<AlbumFile> pics;
    private BaseRecyclerAdapter<AlbumFile> baseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_service);
        ButterKnife.bind(this);
        showUserDefinedNav();
         setTitle("订单服务");
        getNavTitleView().setTextColor(getResources().getColor(R.color.myValue_33));
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_bg_gray));
        mSmrvSaySomething.setHasFixedSize(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPic();
            }
        });
        mSmrvSaySomething.addFooterView(imageView);
      //  mSmrvSaySomething.addFooterView(getLayoutInflater().inflate(R.layout.image,null));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSmrvSaySomething.setLayoutManager(linearLayoutManager);
        pics = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<AlbumFile>(mSmrvSaySomething, pics, R.layout.image) {
            @Override
            public void convert(RecyclerHolder holder, AlbumFile item, int position, boolean isScrolling) {
                String path = item.getPath();
                Glide.with(OrderServiceActivity.this).load(item.getPath()).into((ImageView) holder.getView(R.id.img));
            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                gallery(position);
            }
        });
    }

    @Override
    @OnClick({R.id.tv_store_name, R.id.customer_id, R.id.tv_order_type, R.id.tv_subscribe_date, R.id.tv_consume_type, R.id.et_man_name, R.id.et_woman_name, R.id.tv_customer_source, R.id.tv_customer_zone, R.id.tv_order_receiving_site, R.id.tv_outlets_reception, R.id.tv_reception, R.id.et_outlets_for_three, R.id.tv_card_number, R.id.tv_member_type, R.id.et_man_phone, R.id.et_woman_phone, R.id.tv_manager, R.id.tv_support_staff, R.id.tv_engagement_date, R.id.tv_marriage_date, R.id.et_customer_remarks, R.id.img_fold, R.id.et_say_something,  R.id.btn_start_service, R.id.service_complete})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.tv_store_name:
                break;
            case R.id.customer_id:
                break;
            case R.id.tv_order_type:
                break;
            case R.id.tv_subscribe_date:
                break;
            case R.id.tv_consume_type:
                break;
            case R.id.et_man_name:
                break;
            case R.id.et_woman_name:
                break;
            case R.id.tv_customer_source:
                break;
            case R.id.tv_customer_zone:
                break;
            case R.id.tv_order_receiving_site:
                break;
            case R.id.tv_outlets_reception:
                break;
            case R.id.tv_reception:
                break;
            case R.id.et_outlets_for_three:
                break;
            case R.id.tv_card_number:
                break;
            case R.id.tv_member_type:
                break;
            case R.id.et_man_phone:
                break;
            case R.id.et_woman_phone:
                break;
            case R.id.tv_manager:
                break;
            case R.id.tv_support_staff:
                break;
            case R.id.tv_engagement_date:
                break;
            case R.id.tv_marriage_date:
                break;
            case R.id.et_customer_remarks:
                break;
            case R.id.img_fold:
                mImgFold.setSelected(!mImgFold.isSelected());
                if ( mEtSaySomething.getVisibility()==View.VISIBLE){
                    mEtSaySomething.setVisibility(View.GONE);
                    mSmrvSaySomething.setVisibility(View.GONE);
                }else {
                    mEtSaySomething.setVisibility(View.VISIBLE);
                    mSmrvSaySomething.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.et_say_something:
                break;
            case R.id.btn_start_service:
                mBtnStartService.setClickable(true);
                mTvTime.setText("开始服务:2017年11月24日");
                break;
            case R.id.service_complete:
                break;
        }
    }
    public void gallery(int posi){
    ArrayList<String> stirngs = new ArrayList<String>();
                for (AlbumFile ab:pics){
        stirngs.add(ab.getPath());
    }
    // Preview AlbumFile:
                Album.galleryAlbum(OrderServiceActivity.this);
// Preview path:
                Album.gallery(OrderServiceActivity.this)
            .requestCode(2).currentPosition(posi)
                        .checkedList(stirngs) // List of image to view: ArrayList<String>.
                        .navigationAlpha(80) // Virtual NavigationBar alpha of Android5.0+.
                        .checkable(true) // Whether there is a selection function.
                        .onResult(new Action<ArrayList<String>>() { // If checkable(false), action not required.
        @Override
        public void onAction(int requestCode,  ArrayList<String> result) {
            ArrayList<AlbumFile> temp = new ArrayList<>();
            for (AlbumFile albumFile : pics){
                String path = albumFile.getPath();
             if (!result.contains(path)) {
                 temp.add(albumFile);
                }
            }
            pics.removeAll(temp);
            baseRecyclerAdapter.refresh(pics);
        }
    })
            .onCancel(new Action<String>() {
        @Override
        public void onAction(int requestCode,  String result) {
        }
    })
            .start();
    }
    public void selectPic(){
        Album.image(this) // Image selection.
                .multipleChoice()
                .requestCode(1000)
                .columnCount(3)
                .checkedList(pics)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode,  ArrayList<AlbumFile> result) {
                        pics=result;
                        baseRecyclerAdapter.refresh(pics);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode,  String result) {

                    }
                })
                .start();
    }
}
