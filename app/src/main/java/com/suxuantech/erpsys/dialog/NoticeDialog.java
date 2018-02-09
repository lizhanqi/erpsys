package com.suxuantech.erpsys.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.suxuantech.erpsys.R;

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
 * @author Created by 李站旗 on 2018/1/5 0005 17:28 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 通知弹窗
 */

public class NoticeDialog {
    Drawable titleLeftDrawable;
    private NoticeDialog(Context context,Drawable titleLeftDrawable, String titleText, String digestText, final OnClickDetails onClickDetails) {
        this.titleLeftDrawable = titleLeftDrawable;
        this.titleText = titleText;
        this.digestText = digestText;
        this.onClickDetails = onClickDetails;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_notice, null);
        TextView title = inflate.findViewById(R.id.tv_msg_notice_title);
        title.setText(titleText);
        titleLeftDrawable.setBounds(0, 0, titleLeftDrawable.getMinimumWidth(), titleLeftDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
        title.setCompoundDrawables(titleLeftDrawable,null,null,null);
        TextView details = inflate.findViewById(R.id.tv_msg_notice_details);
        details.setText(digestText);
        inflate.findViewById(R.id.tv_notice_msg_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickDetails!=null){
                    dismiss();
                    onClickDetails.onClickDetails();
                }
            }
        });
        alertView = new AlertView.Builder(context).setStyle(AlertView.Style.SYSTEMTOP).build();
        alertView.setCancelable(true);
        alertView.autoDismiss(5000);
        alertView.setRootBackgroundResource(0);
        alertView.setSystemDialogHeight(300);
        alertView.setContentContainerPadding(0, 0,0,0);
        alertView.addExtView(inflate);
    }
    String titleText;
    String digestText;

    private final AlertView alertView;
    public void show(){
        if (alertView!=null){
            alertView  .show();
        }
 }
    public void dismiss(){
        if (alertView!=null) {
            alertView.dismiss();
        }
    }

    OnClickDetails onClickDetails;
    public interface  OnClickDetails{
        void onClickDetails();
    }

   public static class  Builder{
        Drawable titleLeftDrawable;
        String titleText;
        String digestText;
        OnClickDetails onClickDetails;
        Context context;
        public Builder(Context mContext) {
            context =mContext;
        }

        public void setTitleLeftDrawable(Drawable titleLeftDrawable) {
            this.titleLeftDrawable = titleLeftDrawable;
        }

        public void setTitleText(String titleText) {
            this.titleText = titleText;
        }

        public void setDigestText(String digestText) {
            this.digestText = digestText;
        }

        public void setOnClickDetails(OnClickDetails onClickDetails) {
            this.onClickDetails = onClickDetails;
        }

        public  NoticeDialog build(){
            return  new NoticeDialog(context,titleLeftDrawable,titleText,digestText,onClickDetails);
        }
    }

}
