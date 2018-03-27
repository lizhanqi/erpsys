package com.suxuantech.erpsys.chat;

import android.view.View;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;

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
 * @author Created by 李站旗 on 2018/3/27 0027 19:54 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 百度地图附近建筑
 */

public class BaiduMapAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {
    public BaiduMapAdapter(int layoutResId, List<PoiInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {

        helper.setText(R.id.title,item.name);
        helper.setText(R.id.subtitle, item.address);
        if (posstionChecked==mData.lastIndexOf(item)){
          helper.getView(R.id.iconView).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.iconView).setVisibility(View.GONE);
        }
    }
    public void upData(List<PoiInfo>  poiList) {
        mData=poiList;
        notifyDataSetChanged();
    }
    int  posstionChecked ;
    public void onChecked(int  posstion) {
        int temp=posstionChecked;
        this.posstionChecked=posstion;
        notifyItemChanged(posstion);
        notifyItemChanged(temp);
    }
}

