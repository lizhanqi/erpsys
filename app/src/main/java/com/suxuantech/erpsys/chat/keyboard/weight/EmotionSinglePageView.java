package com.suxuantech.erpsys.chat.keyboard.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.suxuantech.erpsys.chat.keyboard.adaputer.EmotionGridViewAdapter;
import com.suxuantech.erpsys.chat.keyboard.entity.EmotionBean;

import java.util.ArrayList;

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
 * @author Created by 李站旗 on 2018/3/12 0012 14:15 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 表情View
 */

public class EmotionSinglePageView extends GridView implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (emotionClick != null) {
            emotionClick.onClick(position);
        }
            if (position == parent.getCount()-1) {
            if (editText!=null) {
                editText.dispatchKeyEvent(new KeyEvent(  KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            }
        } else {

            if (editText != null) {
                //修改大小,不然输入上的表情过大,输入文字后边直接缩小问题
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), emotion.get(position).icon);
                int fontHeight = getFontHeight(editText);
                bitmap.copy(Bitmap.Config.ARGB_8888, true);
                if (fontHeight/3>0&&  bitmap.isMutable()){
                    bitmap.setHeight(fontHeight/3);
                    bitmap.setWidth(fontHeight/3);
                }
                ImageSpan imageSpan = new ImageSpan(getContext(),bitmap);
                SpannableString spannableString = new SpannableString(emotion.get(position).emoji);
                spannableString.setSpan(imageSpan, 0, spannableString.length(),SpannableString.SPAN_MARK_MARK);
                editText.append(spannableString);
            }
            Toast.makeText(getContext(), editText.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public static int getFontHeight(EditText textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int)Math.ceil((double)(fm.bottom - fm.top));
    }
    EditText editText;
    EmotionClick emotionClick;
    ArrayList<EmotionBean> emotion;
    public void withText(EditText editText) {
        this.editText = editText;
    }
    public int columns = 7;
    public boolean useDelete = true;
    public EmotionSinglePageView(Context context) {
        super(context);
    }
    public void setColumns(int columns) {
        if (columns > 2) {
            this.columns = columns;
        }
    }
    public void setUseDelete(boolean useDelete) {
        this.useDelete = useDelete;
    }
    public EmotionSinglePageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置表情
     *
     * @param emotion
     */
    public void setEmotion(ArrayList<EmotionBean> emotion) {
        this.emotion=emotion;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);
        setNumColumns(columns);
        setAdapter(new EmotionGridViewAdapter(getContext(),emotion,useDelete,80,150));
        setOnItemClickListener(this);
    }

    /**
     *
     */
    public void setOnEmotionClick(EmotionClick emotionClick) {
        this.emotionClick = emotionClick;
    }

    interface EmotionClick {
        void onClick(int posstion);
    }
}
