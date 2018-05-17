package com.suxuantech.erpsys.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @author Created by 李站旗 on 2017/11/2 20:30 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 处理字符串工具
 */
public class StringUtils {
    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     * 清理接送中多余的\和"
     *
     * @param jsonStr 需要清理的json字符串
     * @return 返回一个干净的json串
     */
    public static String jsonStrClean(String jsonStr) {
        String newStr = jsonStr;
        if (jsonStr != null && jsonStr.length() > 0) {
            if (jsonStr.startsWith("\"") && jsonStr.indexOf("\"") == 1) {
                jsonStr = jsonStr.replaceAll("\\\\", "");
                newStr = jsonStr.substring(1, jsonStr.lastIndexOf("\""));
            } else if (jsonStr.indexOf("\\") == 2) {
                jsonStr = jsonStr.replaceAll("\\\\", "");
                newStr = jsonStr.substring(1, jsonStr.lastIndexOf("\""));
            }
        }
        return newStr;
    }
    /**
     * 判断字符串是否为数字
     *  包括整数和小数,以及负数
     *  可以校验 +12  ,12 ,-12,12.0,-12.0,12.0,+12.0 , 012类似这些的都是true
     * @param str
     * @return
     */
    public static boolean strIsNumber(String str) {
        if (str==null||str.isEmpty()){
            return  false;
        }
        final String compile = "^[+-]?[0-9]+.?[0-9]+$";
            Pattern pattern = Pattern.compile(compile);
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
    }
    /**
     * 判断是否为整数(包含负数)
     * 可以校验 +12  ,12 ,-12, 012  类似这些的都是true
     * 中间夹杂有一处分隔符,字符检测失败,两处就正常了
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        final String compile = "^[+-]?[0-9]+$";
        Pattern pattern = Pattern.compile(compile);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
    /**
     * 改变字符串为null的值为空字符串
     *
     * @param str
     * @return
     */
    public static String strChangeNull(String str) {
        if (str == null) {
            return str = "";
        }
        return str;
    }

    /**
     * 小数格式化
     * @param data
     * @return
     */
    public static String moneyFormat(float data){
        return moneyFormat(data+"");
    }

    public static String moneyFormat(float data,int retain ){
        return moneyFormat(data+"",-1);
    }
    /**
     *
     * @param data
     * @return
     */
    public static String moneyFormat(int data){
        return moneyFormat(data+"");
    }

    /**
     * 智能判断返回格式是要有小数点
     * @param data
     * @return
     */
    public static String moneyFormat(String  data){
      return   moneyFormat(data,-1);
    }

    /**
     * 智能判断返回格式是要有小数点
     * @param data 原始数据
     * @param retain  如果是小数保留几位,如果小于等于0则取整,小于0则全部保留,如果大于零保留指定位数
     * @return
     */
    public static String moneyFormat(String  data,int retain){
        if (!strIsNumber(data)){
            return  data;
        }else {
            if (isInteger(data)){
                DecimalFormat df = new DecimalFormat("#,###");
                return df.format(Integer.parseInt(data));
            }else {
                StringBuffer sb =new StringBuffer("#,###");
                if (retain>0){
                    sb.append(".");
                    for (int i=0;i<retain;i++){
                        sb.append("0");
                    }
                }else if (retain<0){
                    sb.append(".");
                    int posstion = data.lastIndexOf(".");
                    int length = data.length();
                    retain= length -posstion ;
                    retain--;
                    for (int i=0;i<retain;i++){
                        sb.append("0");
                    }
                }
                DecimalFormat df = new DecimalFormat(sb.toString());
                return df.format(Double.parseDouble(data));
            }
        }
    }

    /**
     * 判断是否为合法IP
     *
     * @return the ipbuild.gradle
     */
    public static boolean isIp(String ipAddress) {
        String ip = "([1-9]|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])(//.(//d|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
    public static String  subDate(String  str){
        str =StringUtils.safetyString(str);
        if (str.length() > 10) {
            str = str.substring(0, 10);
        }
        return str;
    }
    public static String safetyString(String content) {
        if (empty(content)){
            return "";
        }else {
            return content;
        }
    }
    public static boolean empty(String content) {
        return content==null||content.length()==0;
    }

}
