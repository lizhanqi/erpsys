package com.suxuantech.erpsys.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

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
 * @author Created by 李站旗 on 2018/3/2 0002 13:43 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: glide加载图片圆角
 */

public class GlideRoundTransform extends BitmapTransformation {

    private static float radius=0f;
    public GlideRoundTransform(){
        this(4);
    }
    public GlideRoundTransform(int dp){
        super();
        radius= Resources.getSystem().getDisplayMetrics().density*dp;
    }


    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool,toTransform);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }


    private static Bitmap roundCrop(BitmapPool pool,Bitmap source){
        if(source==null){
            return null;
        }
        Bitmap result=pool.get(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        if(result==null){
            result=Bitmap.createBitmap(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas=new Canvas(result);
        Paint paint=new Paint();
        paint.setShader(new BitmapShader(source,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF=new RectF(0f,0f,source.getWidth(),source.getHeight());
        canvas.drawRoundRect(rectF,radius,radius,paint);

        return result;
    }
}
