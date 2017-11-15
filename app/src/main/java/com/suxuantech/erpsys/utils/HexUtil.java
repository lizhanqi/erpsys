package com.suxuantech.erpsys.utils;
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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 十六进制转换工具
 */

import java.io.UnsupportedEncodingException;

public class HexUtil {
    private static String hexString = "0123456789ABCDEF";

    public static String encodeCN(String data) {
        byte[] bytes;
        try {
            bytes = data.getBytes("gbk");
            StringBuilder sb = new StringBuilder(bytes.length * 2);

            for (int i = 0; i < bytes.length; i++) {
                sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
                sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encodeStr(String data) {
        String result = "";
        byte[] bytes;
        try {
            bytes = data.getBytes("gbk");
            for (int i = 0; i < bytes.length; i++) {
                result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判定是否为中文汉字
     * @param data
     * @return
     */
    public static boolean isCN(String data) {
        boolean flag = false;
        String regex = "^[\u4e00-\u9fa5]*$";
        if (data.matches(regex)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 字符转为十六进制
     * @param targetStr
     * @return
     */
    public static String getHexStr(String targetStr) {
        if (targetStr == null) {
            return null;
        }
        if (targetStr.length() % 2 != 0) {
            targetStr += " ";
        }
        StringBuilder hexStr = new StringBuilder();
        int len = targetStr.length();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                char tempStr = targetStr.charAt(i);
                String data = String.valueOf(tempStr);
                if (isCN(data)) {
                    hexStr.append(encodeCN(data));
                } else {
                    hexStr.append(encodeStr(data));
                }
            }
        }
        return hexStr.toString().toUpperCase();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getHexStr("0731D0026226"));
        System.out.println(hexToStringGBK("4130303030323033"));
    }

    /**
     * 16进制字符转为汉字
     * @param targetHexStr  十六进制字符
     * @return
     */
    public static String hexToStringGBK(String targetHexStr) {
        byte[] baKeyword = new byte[targetHexStr.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(targetHexStr.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        try {
            targetHexStr = new String(baKeyword, "gb2312");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
            return "";
        }
        return targetHexStr;
    }
}
