package com.suxuantech.erpsys.common;

import android.content.Context;
import android.content.Intent;

import com.suxuantech.erpsys.ui.activity.OptionActivity;

import java.io.Serializable;
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
 * @author Created by 李站旗 on 2017/12/2 0002 13:42 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 跳转到Option页面的帮助类帮助生成跳转意图并且携带一些参数意图
 */
public class OptionHelp {
    /**
     * 网络接口标志
     */
    public  enum UrlTag implements Serializable {
        CONSUMPTION_TYPE,OUTLETS_RECEPTION,ORDER_RECEIVING_SITE,CUSTOMER_ZONE,PACKAGE,PRODUCT,CUSTOMER_SOURCE,CUSTOMER_INTENTION
    }
    private UrlTag urlTag;
    boolean isMultiple;
    ArrayList<String> checkedDatas;
    Context context;
    String  tag;
    String Title;
    ArrayList<String> allData;
    public OptionHelp(Context context){
         if (context==null){
            throw  new IllegalArgumentException("Context不能为空");
        }
        this.context=context;
    }

    public Intent creat(){
        Intent intent = new Intent(context, OptionActivity.class);
        if (urlTag==null&&allData==null){
            throw  new IllegalArgumentException("urlTag和allData必须有一个");
        }
        if (urlTag!=null){
            intent.putExtra("UrlTag",urlTag);
        }
        if (tag!=null){
            intent.putExtra("Tag",tag);
        }
        intent.putExtra("Multiple",isMultiple);
        if (Title!=null){
            intent.putExtra("Title",Title);
        }
        if (checkedDatas!=null){
            intent.putExtra("Checked",checkedDatas);
        }
        if (allData!=null){
            intent.putExtra("All",allData);
        }
        return intent;
    }


    /**
     * 设置url标志，这里会根据这个标志进行或相应的网络地址
     * @param urlTag
     */
    public void setUrlTag(UrlTag urlTag) {
        this.urlTag = urlTag;
    }
    /**
     * 是否多选
     * @param multiple
     * @return
     */
    public OptionHelp setMultiple(boolean multiple) {
        isMultiple = multiple;
        return  this;
    }
    /**
     * 可以设置一个自己的标志
     * @param tag
     * @return
     */
    public   OptionHelp setTag(String tag){
        this.tag=tag;
        allData=null;
        return  this;
    }

    /**
     *    已经选中的数据（多选）
     * @param checkedData
     * @return
     */
    public   OptionHelp setCheckedData(ArrayList<String> checkedData){
        checkedDatas=checkedData;
        return  this;
    }

    /**
     * 设置当前选中的数据（单选的）
     * @param checkedData
     * @return
     */
    public   OptionHelp setCheckedData(String checkedData) {
        if (checkedDatas == null) {
            checkedDatas = new ArrayList<>();
        }
        checkedDatas.clear();
        checkedDatas.add(checkedData);
        return this;
    }

    /**
     * 设置全部数据(本地的非网络)
     * @param allData
     * @return
     */
    public OptionHelp setAllData(ArrayList<String > allData){
        this.allData=allData;
        urlTag=null;
        return this;
    }

    /**
     * 设置标题（顶部中间的）
     * @param Title
     * @return
     */
    public OptionHelp setTitle(String Title){
        this.Title=Title;
        return this;
    }

}
