package com.suxuantech.erpsys.chat;

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
 * @author Created by 李站旗 on 2018/4/9 0009 10:45 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */


import android.webkit.URLUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.task.DefaultAlbumLoader;

import java.io.File;

/**
 * Created by Yan Zhenjie on 2017/3/31.
 */
public class GlideAlbumLoader implements AlbumLoader {

    @Override
    public void loadAlbumFile(ImageView imageView, AlbumFile albumFile, int viewWidth, int viewHeight) {
        int mediaType = albumFile.getMediaType();
        if (mediaType == AlbumFile.TYPE_IMAGE) {
            Glide.with(imageView.getContext())
                    .load(albumFile.getPath())
                    .into(imageView);
        } else if (mediaType == AlbumFile.TYPE_VIDEO) {
            DefaultAlbumLoader.getInstance()
                    .loadAlbumFile(imageView, albumFile, viewWidth, viewHeight);
        }
    }

    @Override
    public void loadImage(ImageView imageView, String imagePath, int width, int height) {
        if (URLUtil.isNetworkUrl(imagePath)) {
            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext())
                    .load(new File(imagePath))
                    .into(imageView);
        }
    }

}