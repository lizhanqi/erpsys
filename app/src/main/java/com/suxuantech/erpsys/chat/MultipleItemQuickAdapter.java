package com.suxuantech.erpsys.chat;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.R;

import java.util.List;

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
 * @author Created by 李站旗 on 2018/3/13 0013 16:28 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MessageEntity, BaseViewHolder> {



    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MessageEntity> data) {
        super(data);
        addItemType(MessageEntity.TEXT, R.layout.msg_text);
   //     addItemType(MessageEntity.IMG, R.layout.image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        switch (helper.getItemViewType()) {
            case MessageEntity.TEXT:
                TextView view = helper.getView(R.id.tv_msg);
                view.setText(item.getMsag());
                //  helper.setImageUrl(R.id.tv, item.getContent());
                break;
            case MessageEntity.IMG:
               // helper.setImageUrl(R.id.iv, item.getContent());
                break;
        }
    }
}

