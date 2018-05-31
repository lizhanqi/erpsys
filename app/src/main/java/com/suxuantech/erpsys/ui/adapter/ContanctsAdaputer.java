package com.suxuantech.erpsys.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BusinssunitEntity;
import com.suxuantech.erpsys.entity.ContactEntity;
import com.suxuantech.erpsys.entity.ContanctsFastEntranceEntity;
import com.suxuantech.erpsys.entity.DepartmentEntiy;
import com.suxuantech.erpsys.entity.StaffSearchEntity;
import com.suxuantech.erpsys.entity.StoreEntity;
import com.suxuantech.erpsys.ui.activity.base.ContactsActivity;
import com.suxuantech.erpsys.ui.fragment.ContactDataFragment;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.Text2Bitmap;

import org.greenrobot.eventbus.EventBus;

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
    private List<ContanctsFastEntranceEntity> contanctsFastEntranceEntities;

    public void setOption(boolean option) {
        isOption = option;
    }

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public ContanctsAdaputer(Context context) {
        super(context);
    }

    public void setContanctsFastEntranceEntities(List<ContanctsFastEntranceEntity> contanctsFastEntranceEntities) {
        this.contanctsFastEntranceEntities = contanctsFastEntranceEntities;
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
        } else if (groupPosition == 2) {
            return contactEntity.getDepartmentData() == null ? 0 : contactEntity.getDepartmentData().size();
        } else if (groupPosition == 3) {
            return contactEntity.getStaffData() == null ? 0 : contactEntity.getStaffData().size();
        }
        return 0;
    }

    @Override
    public boolean hasHeader(int groupPosition) {

//        if (groupPosition==3&&contactEntity.getStaffData()!=null&&contactEntity.getStaffData().size()>0){
//            return true;
//        }
        return false;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_textview;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    private boolean isAddedHome(int type, String key) {
        if (contanctsFastEntranceEntities != null) {
            for (ContanctsFastEntranceEntity d : contanctsFastEntranceEntities) {
                if (d.getType() == type && key.equals(d.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        if (groupPosition == 0) {
            //事业部
            List<BusinssunitEntity.DataBean> businssunitData = contactEntity.getBusinssunitData();
            if (businssunitData != null) {
                BusinssunitEntity.DataBean dataBean = businssunitData.get(childPosition);
                if (isAddedHome(ContactDataFragment.BUSINESS_UNIT_TYPE, dataBean.getId() + "")) {
                    return -5;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else if (groupPosition == 1) {
            //店面
            List<StoreEntity.DataBean> storeData = contactEntity.getStoreData();
            if (storeData != null) {
                if (isAddedHome(ContactDataFragment.STORE_TYPE, storeData.get(childPosition).getShop_code() + "")) {
                    return -6;
                } else {
                    return 2;
                }
            } else {
                return 2;
            }
        } else if (groupPosition == 2) {
            //部门
            List<DepartmentEntiy.DataBean> departmentData = contactEntity.getDepartmentData();
            if (departmentData != null) {
                if (isAddedHome(ContactDataFragment.DEPARTMENT_TYPE, departmentData.get(childPosition).getId() + "")) {
                    return -7;
                } else {
                    return 3;
                }
            } else {
                return 3;
            }
        } else {
            return 4;
        }
        //直接返回是哪个组的
        // return groupPosition;
    }

    @Override
    public int getChildLayout(int viewType) {
        if (viewType == 4) {
            //如果是最后一个也就是联系人了
            return R.layout.item_contact;
        } else {
            //其他都是部门,或者店面
            return R.layout.item_department;
        }
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition,
                                      int childPosition) {
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
                // cbPerson.setChecked(ContactsActivity.isChecked(staffData.get(childPosition)));
                tvContactName.setCompoundDrawables(null, null, null, null);
                cbPerson.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b) -> {
//                    if (b) {
//                        ContactsActivity.addChecke(staffData.get(childPosition));
//                    } else {
//                        ContactsActivity.remove(staffData.get(childPosition));
//                    }
                    EventBus.getDefault().post(ContactsActivity.KEY);
                });
            } else {
                cbPerson.setVisibility(View.GONE);
            }
            String string = StringUtils.safetyString(staffData.get(childPosition).getStaffname());
            String zhiwei = StringUtils.safetyString(staffData.get(childPosition).getMain_position_name());
            tvContactName.setText(string);
            tvContactName.append(new MyString("\n"+zhiwei).setSize(10).setColor(tvContactName.getResources().getColor(R.color.hintColor)));
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
//        TextView view = holder.get(R.id.tv_item);
//        List<StaffSearchEntity.DataBean> staffData = contactEntity.getStaffData();
//        view.setText( staffData.get(0).getDepartment_name());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }


}
