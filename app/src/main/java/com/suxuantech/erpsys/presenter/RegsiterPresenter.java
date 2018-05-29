package com.suxuantech.erpsys.presenter;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.nohttp.CallServer;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.Response;

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
 * @author Created by 李站旗 on 2018/5/29 0029 17:43 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */
public class RegsiterPresenter {
    RegisterSearchResult result;
    int pageSize = 20;
    int pageIndex = 0;
    String lastKey = "";
    String nowDate = "";
    RegisterSearchResult iSearchOrderPresenter;

    /**
     * 搜索接口
     */
    public interface RegisterSearchResult {
        void registerSearchOk(List<RegisterEntity.DataBean> data, boolean isFresh, boolean hasMore);

        void registerSearchFailed(Response<RegisterEntity> response, int pageIndex);
    }
    public void setiSearchOrderPresenter(RegisterSearchResult iSearchOrderPresenter) {
        this.iSearchOrderPresenter = iSearchOrderPresenter;
    }
    String jkId="";
    public void search(boolean isFresh, String date, String key) {
        if (isFresh) {
            pageIndex = 0;
        }
        lastKey = key;
        nowDate = date;
        if (!App.getApplication().hasPermission("K2")) {
            ToastUtils.snackbarShort("无权限查询");
            return;
        }
        String name1 = App.getApplication().getUserInfor().staffname;
        String name2 = App.getApplication().getUserInfor().staffname;
        if (App.getApplication().hasPermission("K14")) {
            name1 = "";
        }
        if (App.getApplication().hasPermission("K15")) {
            name2 = "";
        }
        String url = Contact.getFullUrl(Contact.INQUIRE_GUEST_INFO, Contact.TOKEN
                , nowDate, nowDate, lastKey,
                pageIndex, pageSize,
                App.getApplication().getUserInfor().shop_code, jkId, name1, name2
        );
        JavaBeanRequest<RegisterEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<RegisterEntity>(url, RegisterEntity.class);
        HttpListener<RegisterEntity> searchByCustmor = new HttpListener<RegisterEntity>() {
            @Override
            public void onSucceed(int what, Response<RegisterEntity> response) {
                if (response.get().isOK()) {
                    iSearchOrderPresenter.registerSearchOk(response.get().getData(), pageIndex == 0, response.get().getData().size() >= pageSize);
                    if (!(response.get().getData().size() < pageSize)) {//还有更多
                        pageIndex++;
                    }
                } else {
                    iSearchOrderPresenter.registerSearchFailed(response, pageIndex);
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterEntity> response) {
                iSearchOrderPresenter.registerSearchFailed(response, pageIndex);
            }
        };
        new CallServer().add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, false);
    }

    /**
     * 精确搜索,根据数据库唯一id
     * @param isFresh
     * @param date
     * @param key
     */
    public void exactsearch(boolean isFresh, String date, String key) {
        if (isFresh) {
            pageIndex = 0;
        }
        lastKey = key;
        nowDate = date;
        if (!App.getApplication().hasPermission("K2")) {
            ToastUtils.snackbarShort("无权限查询");
            return;
        }
        String name1 = App.getApplication().getUserInfor().staffname;
        String name2 = App.getApplication().getUserInfor().staffname;
        if (App.getApplication().hasPermission("K14")) {
            name1 = "";
        }
        if (App.getApplication().hasPermission("K15")) {
            name2 = "";
        }
        String url = Contact.getFullUrl(Contact.INQUIRE_GUEST_INFO, Contact.TOKEN
                , nowDate, nowDate, "",
                pageIndex, pageSize,
                App.getApplication().getUserInfor().shop_code, key, name1, name2
        );
        JavaBeanRequest<RegisterEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<RegisterEntity>(url, RegisterEntity.class);
        HttpListener<RegisterEntity> searchByCustmor = new HttpListener<RegisterEntity>() {
            @Override
            public void onSucceed(int what, Response<RegisterEntity> response) {
                if (response.get().isOK()) {
                    iSearchOrderPresenter.registerSearchOk(response.get().getData(), pageIndex == 0, response.get().getData().size() >= pageSize);
                    if (!(response.get().getData().size() < pageSize)) {//还有更多
                        pageIndex++;
                    }
                } else {
                    iSearchOrderPresenter.registerSearchFailed(response, pageIndex);
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterEntity> response) {
                iSearchOrderPresenter.registerSearchFailed(response, pageIndex);
            }
        };
        new CallServer().add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, false);
    }

    public void loadMore() {
        search(false, nowDate, lastKey);
    }


}
