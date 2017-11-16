package com.suxuantech.erpsys.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
 * @author Created by 李站旗 on 2017/11/16 0016 12:36 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: DPI工具可以计算密度，也可以px转为dp工具
 */

public class DPI {
    public static void main(String[] args) {
    //    calc(1080,1920,480);
        generateXmlFile(1080,3);
    }

    /**
     * 计算密度或者计算英寸
     * @param w 宽度
     * @param h 高度
     * @param inchordpi  英寸或者密度
     */
    public static void calc(int w,int h ,double inchordpi){
        System.out.println( Math.sqrt(  w*w+h*h)/inchordpi);
    }
    //px转dp模板
    private final static String DP_TEMPLATE = "<dimen name=\"px{0}\">{1}dp</dimen>\n";
    //  px转sp模板
    private final static String SP_TEMPLATE = "<dimen name=\"xs{0}\">{1}sp</dimen>\n";
    /**
     * 创建xml文件
     * @param w 最大宽度，也就是可能生成最大是多少像素的dp这里我给的是
     * @param cell 缩放多少倍，
     *             例如
     *             720宽度的需要缩放2倍，那么 w就穿入720，cell就是2
     *             1080宽度的需要缩放3倍，那么 w就穿入1080，cell就是3
     *             1440宽度的需要缩放4倍，那么 w就穿入1440，cell就是4
     *
     */
    private static void generateXmlFile(int w,float cell) {
        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>\n");
        //float cellw = w * 1.0f / baseW;
        System.out.println("开始生成width : " + w + ",缩放："+ cell+"倍");
        //计算dp（从负数的到）
        for (int i = -w; i <= w; i++) {
            if (i<0){
                sbForWidth.append(DP_TEMPLATE.replace("{0}", "_"+ Math.abs(i) ).replace("{1}",i/cell + ""));
            }else {
                sbForWidth.append(DP_TEMPLATE.replace("{0}", i + "").replace("{1}", i / cell + ""));
            }
        }
        //计算sp字体最大估计不会用到300个像素，也不会有负数所以这里目前就写死了，
        for (int i = 0; i < 300; i++) {
            sbForWidth.append(SP_TEMPLATE.replace("{0}", i + "").replace("{1}", i/cell + ""));
        }
        sbForWidth.append("</resources>");
        String dirStr = "./res";
        File fileDir = new File(dirStr + File.separator +"values");
        fileDir.mkdir();
        File layxFile = new File(fileDir.getAbsolutePath(), "dpi_and_sp.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
            System.out.println("生成: " + w + "完毕");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
