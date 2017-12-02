package com.suxuantech.erpsys;

import android.content.Context;
import android.content.Intent;

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
    boolean isMultiple;
    ArrayList<String> checkedDatas;
    Class targetActivity;
    Context context;
    String  url;
    String Title;
    ArrayList<String> allData;
    public OptionHelp(Context context,Class  targetActivity){
        this.context=context;
        this.targetActivity=targetActivity;
    }
    public   OptionHelp isMultiple(boolean isMultiple){
        this.isMultiple=isMultiple;
        return  this;
    }
    public   OptionHelp setUrl(String url){
        this.url=url;
        allData=null;
        return  this;
    }
    public   OptionHelp setCheckedData(ArrayList<String> checkedData){
        checkedDatas=checkedData;
        return  this;
    }
    public   OptionHelp setCheckedData(String checkedData) {

        if (checkedDatas == null) {
            checkedDatas = new ArrayList<>();
        }

        checkedDatas.clear();
        checkedDatas.add(checkedData);
        return this;
    }
    public OptionHelp setAllData(ArrayList<String > allData){
        this.allData=allData;
        url=null;
        return this;
    }
    public OptionHelp setTitle(String Title){
        this.Title=Title;
        return this;
    }
    public Intent Start(){
        Intent intent = new Intent(context, targetActivity);
        if (url!=null){
            intent.putExtra("Url",url);
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

}
