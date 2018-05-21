package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BusinssunitEntity;
import com.suxuantech.erpsys.entity.ContactEntity;
import com.suxuantech.erpsys.entity.DepartmentEntiy;
import com.suxuantech.erpsys.entity.StaffSearchEntity;
import com.suxuantech.erpsys.entity.StoreEntity;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.Text2Bitmap;

import java.util.List;


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
 * @author Created by 李站旗 on 2018/5/18 0018 17:21 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class ContanctsAdaputer extends GroupedRecyclerViewAdapter {
    ContactEntity contactEntity = new ContactEntity();
    boolean isOption;

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public ContanctsAdaputer(Context context) {
        super(context);
    }

    @Override
    public int getGroupCount() {
        return 4;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return contactEntity.getBusinssunitData() == null ? 0 : contactEntity.getBusinssunitData().size();
        } else if (groupPosition == 1) {
            return contactEntity.getStoreData() == null ? 0 : contactEntity.getStoreData().size();
        }
        if (groupPosition == 2) {
            return contactEntity.getDepartmentData() == null ? 0 : contactEntity.getDepartmentData().size();
        }
        if (groupPosition == 3) {
            return contactEntity.getStaffData() == null ? 0 : contactEntity.getStaffData().size();
        }
        return 0;
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return false;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return 0;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        //直接返回是哪个组的
        return groupPosition;
    }

    @Override
    public int getChildLayout(int viewType) {
        if (viewType == 3) {
            //如果是最后一个也就是联系人了
            return R.layout.item_contact;
        } else {
            //其他都是部门,或者店面
            return R.layout.item_department;
        }
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        if (groupPosition == 3) {
            List<StaffSearchEntity.DataBean> staffData = contactEntity.getStaffData();
            if (staffData == null) {
                return;
            }
            ImageView imgContactHead = holder.get(R.id.img_contact_head);
            TextView tvContactName = holder.get(R.id.tv_contact_name);
            CheckBox cbPerson = holder.get(R.id.cb_person);
            if (isOption) {
                cbPerson.setVisibility(View.VISIBLE);
            } else {
                cbPerson.setVisibility(View.GONE);
            }
            String string = StringUtils.safetyString(staffData.get(childPosition).getStaffname());
            tvContactName.setText(string);
            imgContactHead.setImageBitmap(Text2Bitmap.getNameBitMap(string, imgContactHead.getContext().getResources().getColor(R.color.white)));
        } else {
            TextView departTextview = holder.get(R.id.tv_department);
            if (groupPosition == 0) {
                List<BusinssunitEntity.DataBean> businssunitData = contactEntity.getBusinssunitData();
                if (businssunitData != null) {
                    BusinssunitEntity.DataBean dataBean = businssunitData.get(childPosition);
                    String string = StringUtils.safetyString(dataBean.getBrandclass());
                    departTextview.setText(string);
                }
            } else if (groupPosition == 1) {
                List<StoreEntity.DataBean> storeData = contactEntity.getStoreData();
                if (storeData != null) {
                    StoreEntity.DataBean dataBean = storeData.get(childPosition);
                    String string = StringUtils.safetyString(dataBean.getShop_name());
                    departTextview.setText(string);
                }

            } else if (groupPosition == 2) {
                List<DepartmentEntiy.DataBean> departmentData = contactEntity.getDepartmentData();
                if (departmentData != null) {
                    DepartmentEntiy.DataBean dataBean = departmentData.get(childPosition);
                    String string = StringUtils.safetyString(dataBean.getDepartment_name());
                    departTextview.setText(string);
                }
            }
        }
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }


}
