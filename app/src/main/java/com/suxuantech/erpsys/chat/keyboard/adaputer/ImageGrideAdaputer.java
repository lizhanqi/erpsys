package com.suxuantech.erpsys.chat.keyboard.adaputer;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.IdHelper;
import com.suxuantech.erpsys.chat.keyboard.entity.ImageBean;

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
 * @author Created by 李站旗 on 2018/3/13 0013 12:04 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class ImageGrideAdaputer  extends BaseAdapter {

    private Context context;
    private List<ImageBean> emotionNames;
    private int itemWidth;
    private int emotion_map_type;
    boolean useDelete;
    public ImageGrideAdaputer(Context context, List<ImageBean> emotionNames, int emotion_map_type) {
        this.context = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
        this.emotion_map_type=emotion_map_type;
        this.useDelete=useDelete;
    }


    @Override
    public int getCount() {
            return emotionNames.size() ;
    }

    @Override
    public ImageBean getItem(int position) {
        return emotionNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater1 = LayoutInflater.from(context);
        View view = inflater1.inflate(R.layout.item_image_emotion, null);
        ImageView img = view.findViewById(R.id.img_emotion);
        TextView textView = view.findViewById(R.id.tv_emotion_name);
        if (!TextUtils.isEmpty(emotionNames.get(position).name)) {
            textView.setText(emotionNames.get(position).name);
        }
        img.setImageDrawable(context.getResources().getDrawable(IdHelper.getDrawable(context,emotionNames.get(position).uri)));
        return view;
    }
}

