package com.suxuantech.erpsys.chat.keyboard.weight;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
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
            Toast.makeText(getContext(), "删除", Toast.LENGTH_SHORT).show();
            // 获取文本框信息中所有的表情
            String text = editText.getText().toString();
            Spanned content = editText.getEditableText();
            ImageSpan[] spans = content.getSpans(0, content.length(),ImageSpan.class);
            // 取出最后一个表情
            if (spans.length >= 1) {
                ImageSpan span = spans[spans.length - 1];
                int start = content.getSpanStart(span);
                int end = content.getSpanEnd(span);
                if (end == text.length()) {
                    Editable edit = editText.getText();
                    edit.delete(start, end);
                    editText.invalidate();
                }
            }
//            String s = editText.getText().toString();
//            if (editText != null && s.length() > 0) {
//                int index = editText.getSelectionStart();
//                Editable editable = editText.getText();
//                editable.delete(index - 1, index);
//            }
        } else {
            if (editText != null) {
                ImageSpan imageSpan = new ImageSpan(getContext(), BitmapFactory.decodeResource(getResources(),emotion.get(position).icon));
                SpannableString spannableString = new SpannableString(emotion.get(position).emoji);
                spannableString.setSpan(imageSpan, 0, spannableString.length(),SpannableString.SPAN_MARK_MARK);
                editText.append(spannableString);
//                SpannableString ss = new SpannableString(" ");
//                ImageSpan is = new ImageSpan(getContext(),emotion.get(position).icon);
//                ss.setSpan(is, 0, 1, 0);
//                editText.append(ss);
            }
            Toast.makeText(getContext(), editText.getText(), Toast.LENGTH_SHORT).show();
        }
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
        setAdapter(new EmotionGridViewAdapter(getContext(),emotion,useDelete,150,150));
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
