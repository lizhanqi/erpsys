package com.suxuantech.erpsys.nohttp;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;

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
 * @author Created by 李站旗 on 2017/11/7 18:01 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class DownLoad {
    DownloadListener dl=   new DownloadListener() {
        @Override
        public void onDownloadError(int what, Exception exception) {

        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {

        }

        @Override
        public void onFinish(int what, String filePath) {

        }

        @Override
        public void onCancel(int what) {
        }
    };
    public void Dolad(int what,String url, String fileFolder, boolean isDeleteOld) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url,fileFolder,isDeleteOld);
        NoHttp.getDownloadQueueInstance().add(what,downloadRequest, dl);
    }















}
