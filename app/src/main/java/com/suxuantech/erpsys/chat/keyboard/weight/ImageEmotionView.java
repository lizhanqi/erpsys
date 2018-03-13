package com.suxuantech.erpsys.chat.keyboard.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.suxuantech.erpsys.chat.keyboard.entity.ImageBean;
import com.suxuantech.erpsys.chat.keyboard.adaputer.ImageGrideAdaputer;
import com.suxuantech.erpsys.chat.keyboard.adaputer.EmotionPagerAdapter;

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
 * @author Created by 李站旗 on 2018/3/13 0013 12:00 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 图片表情View
 */

public class ImageEmotionView  extends LinearLayout implements AdapterView.OnItemClickListener {
    //    ArrayList pagerGrid =new    ArrayList<GridView>();
    ArrayList<ArrayList<ImageBean>> limt = new ArrayList<ArrayList<ImageBean>>();
    int pages;//需要分多少页
    EmotionClick emotionClick;
    private ViewPager gridView;
    private LinearLayout radioGroup;
    public int row = 4;
    public int columns = 7;
    ArrayList<ImageBean> emotion;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) parent.getTag();
        limt.get(tag);
        int posstion = tag * columns * row + position;
        if (emotionClick != null) {
            emotionClick.onClick(emotion,posstion);
        }
    }
    public ImageEmotionView (Context context) {
        super(context);
        setOrientation(VERTICAL);
        gridView = new ViewPager(context);
        radioGroup = new RadioGroup(context);
        addView(gridView);
        addView(radioGroup);
    }
    public void setLine(int columns) {
        if (columns > 2) {
            this.columns = columns;
        }
    }
    public void setRow(int row) {
        if (row > 2) {
            this.row = row;
        }
    }
    public ImageEmotionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        gridView = new ViewPager(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(layoutParams);
        addView(gridView);
    }

    /**
     * 设置表情
     *
     * @param emotion
     */
    public void setEmotion(ArrayList<ImageBean> emotion) {
        this.emotion=emotion;
        pages = emotion.size() / (row * columns);
        ArrayList<GridView> ag = new ArrayList<>();
        for (int i = 0; i <= pages; i++) {
            GridView gridView = new GridView(getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            gridView.setLayoutParams(lp);
            gridView.setNumColumns(columns);
            ArrayList<ImageBean> getbeans = getbeans(i, emotion);
            if (getbeans.size() > 0) {
                limt.add(getbeans);
                gridView.setTag(i);
                gridView.setGravity(HORIZONTAL);
                gridView.setAdapter(new ImageGrideAdaputer(getContext(), limt.get(i),10));
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
    public void setOnEmotionClick(EmotionClick emotionClick) {
        this.emotionClick = emotionClick;
    }

    /**
     * 计算分页
     *
     * @param currentPage
     * @param emotion
     * @return
     */
    public ArrayList<ImageBean> getbeans(int currentPage, ArrayList<ImageBean> emotion) {
        int start = currentPage * (row * columns);
        int end = (currentPage + 1) * (row * columns);
        ArrayList<ImageBean> objects = new ArrayList<ImageBean>();
        for (int i = start; i < end; i++) {
            if (emotion.size() > i) {
                objects.add(emotion.get(i));
            } else {
                break;
            }
        }
        return objects;
    }

    public interface EmotionClick {
        void onClick(ArrayList<ImageBean> emotion, int posstion);
    }
}

