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
 * @author Created by 李站旗 on 2018/3/20 0020 11:07 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;

import com.suxuantech.erpsys.App;

/**
 * ================================================
 * 作    者：JayGoo
 * 版    本：1.1.0
 * 创建日期：2016/11/24
 * 描    述:音频听筒扬声器切换控制器
 * ================================================
 */
public class AudioModeManger {

    private AudioManager audioManager;
    private SensorManager sensorManager;
    private Sensor mProximiny;
    private onSpeakerListener mOnSpeakerListener;

    /**
     * 扬声器状态监听器
     * 如果要做成类似微信那种切换后重新播放音频的效果，需要这个监听回调
     * isSpeakerOn 扬声器是否打开
     */
    public interface onSpeakerListener{
        void onSpeakerChanged(boolean isSpeakerOn);
    }


    public void setOnSpeakerListener(onSpeakerListener listener){
        if (listener != null){
            mOnSpeakerListener = listener;
        }
    }

    public AudioModeManger(){

    }

    /**
     * 距离传感器监听者
     */
    private SensorEventListener mDistanceSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float f_proximiny = event.values[0];
            //扬声器模式
            //魅蓝E传感器得到的值竟然比最大值都要大？what fuck ？
            if (f_proximiny >= mProximiny.getMaximumRange()) {

                setSpeakerPhoneOn(true);
                if (mOnSpeakerListener != null){
                    mOnSpeakerListener.onSpeakerChanged(true);
                }

            } else {//听筒模式

                setSpeakerPhoneOn(false);

                if (mOnSpeakerListener != null){
                    mOnSpeakerListener.onSpeakerChanged(false);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };



    /**
     * 注册距离传感器监听
     */
    public void register(){
        audioManager = (AudioManager) App.getApplication()
                .getSystemService(Context.AUDIO_SERVICE);
        sensorManager = (SensorManager) App.getApplication()
                .getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null && mDistanceSensorListener != null) {
            mProximiny = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(mDistanceSensorListener, mProximiny,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    /**
     * 取消注册距离传感器监听
     */
    public void unregister(){

        if (sensorManager != null &&mDistanceSensorListener != null ) {
            sensorManager.unregisterListener(mDistanceSensorListener);
        }

    }


    /**
     * 听筒、扬声器切换
     *
     * 注释： 敬那些年踩过的坑和那些网上各种千奇百怪坑比方案！！
     *
     * AudioManager设置声音类型有以下几种类型（调节音量用的是这个）:
     *
     * STREAM_ALARM 警报
     * STREAM_MUSIC 音乐回放即媒体音量
     * STREAM_NOTIFICATION 窗口顶部状态栏Notification,
     * STREAM_RING 铃声
     * STREAM_SYSTEM 系统
     * STREAM_VOICE_CALL 通话
     * STREAM_DTMF 双音多频,不是很明白什么东西
     *
     * ------------------------------------------
     *
     * AudioManager设置声音模式有以下几个模式（切换听筒和扬声器时setMode用的是这个）
     *
     * MODE_NORMAL 正常模式，即在没有铃音与电话的情况
     * MODE_RINGTONE 铃响模式
     * MODE_IN_CALL 接通电话模式 5.0以下
     * MODE_IN_COMMUNICATION 通话模式 5.0及其以上
     *
     * @param on
     */
    private void setSpeakerPhoneOn(boolean on) {

        if (on) {
            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(AudioManager.MODE_NORMAL);
            //设置音量，解决有些机型切换后没声音或者声音突然变大的问题
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), AudioManager.FX_KEY_CLICK);

        } else {
            audioManager.setSpeakerphoneOn(false);

            //5.0以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                //设置音量，解决有些机型切换后没声音或者声音突然变大的问题
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), AudioManager.FX_KEY_CLICK);

            } else {
                audioManager.setMode(AudioManager.MODE_IN_CALL);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), AudioManager.FX_KEY_CLICK);
            }
        }

    }

}