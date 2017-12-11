package com.suxuantech.erpsys.eventmsg;

import com.suxuantech.erpsys.OptionHelp;

import java.util.ArrayList;
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
 * @author Created by 李站旗 on 2017/12/8 0008 13:30 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: EventBus发送的消息（选择一些东西的时候发送这个，主要使用地方是OptionActivity）
 */

public class BaseMsg <T>{
    List<T> allData;
    List<? >  multiSelected ;
    Object singleChecked;
    String mTitle;
    String tag;
    String url;
    OptionHelp.UrlTag urlTag;

    public OptionHelp.UrlTag getUrlTag() {
        return urlTag;
    }

    public void setUrlTag(OptionHelp.UrlTag urlTag) {
        this.urlTag = urlTag;
    }

    /**
     *
      * @param allData  全部数据
     * @param checked  当前选中的
     * @param multiSelect  是否是多选
     */
    public BaseMsg(List<T> allData, ArrayList<T> checked, boolean multiSelect) {
        this. allData=allData;
        if (checked!=null&&checked.size()>0){
            if (multiSelect){
                this.multiSelected = checked;
            }else {
                singleChecked = checked.get(0);
            }
        }

    }

    public Object getSingleChecked() {
        return singleChecked;
    }

    public void setSingleChecked(Object singleChecked) {
        this.singleChecked = singleChecked;
    }

    public List<T> getAllData() {
        return allData;
    }

    public void setAllData(List<T> allData) {
        this.allData = allData;
    }

    public List<?> getMultiSelected() {
        return multiSelected;
    }

    public void setMultiSelected(ArrayList<?> multiSelected) {
        this.multiSelected = multiSelected;
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
