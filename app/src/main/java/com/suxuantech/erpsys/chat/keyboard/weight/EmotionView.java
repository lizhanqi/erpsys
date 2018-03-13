package com.suxuantech.erpsys.chat.keyboard.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.suxuantech.erpsys.chat.keyboard.adaputer.EmotionGridViewAdapter;
import com.suxuantech.erpsys.chat.keyboard.adaputer.EmotionPagerAdapter;
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

public class EmotionView extends LinearLayout  implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) parent.getTag();
        limt.get(tag);
        int sum = tag * columns * row + position;
        if(useDelete&&tag>0){
            sum= sum-tag;
        }
        if (emotionClick!=null){
            emotionClick.onClick(sum);
        }
        if (position== (limt.get(tag).size())){
            Toast.makeText(getContext(),"删除",Toast.LENGTH_SHORT).show();
            String s = editText.getText().toString();
            if (editText!=null&&s.length()>0){
                int index = editText.getSelectionStart();
                Editable editable = editText.getText();
                editable.delete(index-1, index);
            }
        }else {
            if (editText!=null){
                SpannableString ss = new SpannableString(" ");
                ImageSpan is = new ImageSpan(getContext(), limt.get(tag).get(position).icon);
                ss.setSpan(is,0,1,0);
                editText.append(ss);
            }
            Toast.makeText(getContext(),editText.getText().toString(),Toast.LENGTH_SHORT).show();
        }

    }
    EditText editText;
    public void withText(EditText editText){
        this.editText=editText;
    }
    private ViewPager gridView;
    private LinearLayout radioGroup;
    public int row=4;
    public int columns=7;
    public boolean useDelete=true;
    public EmotionView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        gridView = new ViewPager(context);
        radioGroup = new RadioGroup(context);
        addView(gridView);
        addView(radioGroup);
    }
    public void setLine(int columns) {
        if (columns>2){
            this.columns = columns;
        }
    }
    public void setRow(int row) {
        if (row>2){
            this.row = row;
        }
    }
    public void setUseDelete(boolean useDelete) {
        this.useDelete = useDelete;
    }
    public EmotionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        gridView = new ViewPager(context);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(layoutParams);
        addView(gridView);
    }


//    ArrayList pagerGrid =new    ArrayList<GridView>();
    ArrayList<ArrayList<EmotionBean>> limt =new       ArrayList<ArrayList<EmotionBean>>();
    int pages;//需要分多少页
    EmotionClick emotionClick;

    /**
     * 设置表情
     * @param emotion
     */
    public  void setEmotion(ArrayList<EmotionBean> emotion){
        if (useDelete){
            pages=  emotion.size()/(row * columns - 1);
        }else {
            pages=  emotion.size()/(row * columns );
        }
        ArrayList<GridView> ag =new ArrayList<>();
      for (int i=0;i<=pages;i++){
            GridView gridView = new GridView(getContext());
          ViewGroup.LayoutParams lp =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
          gridView.setLayoutParams(lp);
            gridView.setNumColumns(columns);
            ArrayList<EmotionBean> getbeans = getbeans(i, emotion);
            if (getbeans.size()>0){
            limt.add(getbeans);
            gridView.setTag(i);
            gridView.setGravity(HORIZONTAL);
            gridView.setAdapter(new EmotionGridViewAdapter(getContext(),limt.get(i),useDelete,150,150));
            gridView.setOnItemClickListener(this);
            ag.add(gridView);
             }
        }
        EmotionPagerAdapter emotionPagerGvAdapter = new EmotionPagerAdapter(ag);
        gridView.setAdapter(emotionPagerGvAdapter);
    }
    /**
     *
     */
    public void setOnEmotionClick(EmotionClick emotionClick){
        this.emotionClick=emotionClick;
    }
    /**
     * 计算分页
     * @param currentPage
     * @param emotion
     * @return
     */
    public ArrayList<EmotionBean>  getbeans(int currentPage,ArrayList<EmotionBean> emotion){
        int start;
        int end;
        if (useDelete){
          start = currentPage * (row * columns - 1);
          end = (currentPage+1) * (row * columns - 1);
        }else {
             start = currentPage * (row * columns );
             end = (currentPage+1) * (row * columns );
        }
        ArrayList<EmotionBean> objects = new ArrayList<EmotionBean>();
        for (int i=start;i<end;i++){
            if (emotion.size()>i){
                objects.add(  emotion.get(i));
            }else {
                break;
            }
        }
        return objects;
    }
    interface  EmotionClick{
        void onClick(int posstion );
    }
}
