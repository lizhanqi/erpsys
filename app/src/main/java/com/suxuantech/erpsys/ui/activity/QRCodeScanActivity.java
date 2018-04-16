package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.zbar.camera.CameraPreview;
import com.yanzhenjie.zbar.camera.ScanCallback;
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
 * @Description: zbar的二维码扫描
 */
public class QRCodeScanActivity extends AppCompatActivity {
    private CameraPreview mPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scan);
        mPreview = findViewById(R.id.capture_preview);
        mPreview.setScanCallback(callback);
        startScan();
    }
    /**
     * 打开相机并开始扫描。
     */
    private void startScan() {
        mPreview.start();
    }

    /**
     * 停止扫描并关闭相机。
     */
    private void stopScan() {
        mPreview.stop();
    }
    /**
     * 监听结果。
     */
    private ScanCallback callback = new ScanCallback() {
        @Override
        public void onScanResult(String content) {
            ToastUtils.showShort(content);
            // Successfully.
        }
    };

    @Override
    protected void onPause() {
        // 必须在这里停止扫描释放相机。
        stopScan();
        super.onPause();
    }
}
