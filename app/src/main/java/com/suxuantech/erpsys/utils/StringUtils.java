package com.suxuantech.erpsys.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
    public static boolean strIsNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) str);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * 判断字符串是否是null
     * @param str
     * @return
     */
    public static boolean strIsNull(String str) {
        return str==null;
    }
    /**
     * 改变字符串为null的值为空字符串
     * @param str
     * @return
     */
    public static String strChangeNull(String str) {
        if (str==null){
            return  str="";
        }
        return str ;
    }



    /**
     * 判断是否为合法IP
     * @return the ipbuild.gradle
     */
    public static boolean isIp(String ipAddress){
                String ip = "([1-9]|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])(//.(//d|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
}
