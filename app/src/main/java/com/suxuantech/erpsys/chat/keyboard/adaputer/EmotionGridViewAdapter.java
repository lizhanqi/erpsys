package com.suxuantech.erpsys.chat.keyboard.adaputer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.chat.keyboard.entity.EmotionBean;
import com.suxuantech.erpsys.utils.DensityUtils;

import java.util.List;

/**
 * Created by zejian
 * Time  16/1/7 下午4:46
 * Email shinezejian@163.com
 * Description:
 */
public class EmotionGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<EmotionBean> emotionNames;
    private int itemWidth;
    private int emotion_map_type;
    boolean useDelete;

    public EmotionGridViewAdapter(Context context, List<EmotionBean> emotionNames, boolean useDelete, int itemWidth, int emotion_map_type) {
        this.context = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
        this.emotion_map_type = emotion_map_type;
        this.useDelete = useDelete;
    }

    @Override
    public int getCount() {
        // +1 最后一个为删除按钮
        if (useDelete) {
            return emotionNames.size() + 1;
        } else {
            return emotionNames.size();
        }
    }

    @Override
    public EmotionBean getItem(int position) {
        return emotionNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_emotion = new ImageView(context);
        int px = DensityUtils.dp2px(context, itemWidth);
        // 设置内边距
        int pad=px / 10;
        iv_emotion.setPadding(pad, pad, pad, pad);
        LayoutParams params = new LayoutParams(px, px);
        iv_emotion.setLayoutParams(params);
        //判断是否为最后一个item
        if (position == getCount() - 1) {
            iv_emotion.setImageResource(R.drawable.icon_backspace);
        } else {
            iv_emotion.setImageDrawable(context.getResources().getDrawable(emotionNames.get(position).icon));
            //Glide.with(context).load(emotionNames.get(position).emoji).into(iv_emotion);
            //String emotionName = emotionNames.get(position);
            //	iv_emotion.setImageResource(EmotionUtils.getImgByName(emotion_map_type,emotionName));
        }
        return iv_emotion;
    }
}
