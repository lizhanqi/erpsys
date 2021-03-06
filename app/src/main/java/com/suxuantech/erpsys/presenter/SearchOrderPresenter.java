package com.suxuantech.erpsys.presenter;

import com.anye.greendao.gen.HistoryEntityDao;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.HistoryEntity;
import com.suxuantech.erpsys.entity.PhotoSchemeSearchEntity;
import com.suxuantech.erpsys.entity.SearchOptionPanelEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.nohttp.CallServer;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.connector.ISearchOptionPanelScheme;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchPhotoScheme;
import com.suxuantech.erpsys.ui.TypeFlag;
import com.suxuantech.erpsys.utils.DateUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Collections;
import java.util.Comparator;
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
 * @author Created by 李站旗 on 2017/12/11 0011 10:14 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description:搜索订单的逻辑类
 */

public class SearchOrderPresenter {
    ISearchOrderPresenter iSearchOrderPresenter;
    ISearchOptionPanelScheme iSearchOptionPanelScheme;
    ISearchPhotoScheme iSearchPhotoScheme;

    public void setiSearchOrderPresenter(ISearchOrderPresenter iSearchOrderPresenter) {
        this.iSearchOrderPresenter = iSearchOrderPresenter;
    }

    public void setiSearchPhotoScheme(ISearchPhotoScheme iSearchPhotoScheme) {
        this.iSearchPhotoScheme = iSearchPhotoScheme;
    }

    public void setiSearchOptionPanelScheme(ISearchOptionPanelScheme iSearchOptionPanelScheme) {
        this.iSearchOptionPanelScheme = iSearchOptionPanelScheme;
    }

    private HistoryEntityDao historyDao;
    private List<HistoryEntity> searchHosiery;

    public SearchOrderPresenter(ISearchOrderPresenter iSearchOrderPresenter) {
        this.iSearchOrderPresenter = iSearchOrderPresenter;
        initDB();
        searchHosiery = loadAllHistory();
    }

    RequestQueue requestQueue;

    public SearchOrderPresenter(ISearchOrderPresenter iOutletsOrderPresenter, RequestQueue requestQueue) {
        this.iSearchOrderPresenter = iSearchOrderPresenter;
        initDB();
        searchHosiery = loadAllHistory();
        this.requestQueue = requestQueue;
    }

    public SearchOrderPresenter() {
        initDB();
        searchHosiery = loadAllHistory();
    }

    private void initDB() {
        //历史表
        historyDao = App.getApplication().getDaoSession().getHistoryEntityDao();
    }

    public List<HistoryEntity> clear() {
        //删除表内数据
        historyDao.deleteAll();
        searchHosiery.clear();
        return searchHosiery;
    }

    /**
     * 插入搜索历史
     */
    public List<HistoryEntity> insert(String trim) {
        //判断是否存在数据库中，如果存在则不再存储
        for (HistoryEntity h : searchHosiery) {
            String name = h.getName();
            if (name.equals(trim)) {
                //更新时间
                h.setDate(System.currentTimeMillis());
                historyDao.update(h);
                Collections.sort(searchHosiery, new Comparator<HistoryEntity>() {
                    @Override
                    public int compare(HistoryEntity historyEntity, HistoryEntity t1) {
                        return historyEntity.getDate()>t1.getDate()?-1:1;
                    }
                });
                return searchHosiery;
            }
        }
        HistoryEntity studentMsgBean = new HistoryEntity();
        studentMsgBean.setName(trim);
      studentMsgBean.setDate(System.currentTimeMillis());
      //  studentMsgBean.setTime(DateUtil.getNowDate(DateUtil.DatePattern.YEAR_MONTHE_DAY_TEXT));
        historyDao.insert(studentMsgBean);
        return searchHosiery = loadAllHistory();
    }

    /**
     * 获取本地DB中搜索历史
     */
    private List<HistoryEntity> loadAllHistory() {

        searchHosiery = historyDao.loadAll();
        //倒序下，因为最新的要在上面显示
        Collections.sort(searchHosiery, new Comparator<HistoryEntity>() {
            @Override
            public int compare(HistoryEntity historyEntity, HistoryEntity t1) {
                return historyEntity.getDate()>t1.getDate()?-1:1;
            }
        });
      // Collections.reverse(searchHosiery);
        return searchHosiery;
    }

    /**
     * 获取内存中的本地数据
     */
    public List<HistoryEntity> getSearchHosiery() {
        return searchHosiery;
    }


    /**
     * 获取内存中的本地数据
     */
    public String[] getSearchHosieryArray() {
        String[] strings = null;
        if (searchHosiery != null) {
            strings = new String[searchHosiery.size()];
            for (int i = 0; i < searchHosiery.size(); i++) {
                strings[i] = searchHosiery.get(i).getName();
            }
        }
        return strings;
    }

    int pageIndex;
    int pageSize = 20;
    String lastStartDate, lastEndDate, lastSearchKey, lastShopKey;

    /**
     * 搜索网络订单
     *
     * @param key
     * @param startDate
     * @param endDate
     */

    public void sosoNetOrder(String key, String startDate, String endDate, boolean isFresh, boolean showWindow) {
        if (isFresh) {
            pageIndex = 0;
        }
        if (key == null) {
            key = "";
            lastSearchKey = key;
        }
        if (startDate.equals(App.getContext().getResources().getString(R.string.start_time))) {
            startDate = "20000101";
            lastStartDate = startDate;
        }
        if (endDate.equals(App.getContext().getResources().getString(R.string.end_time))) {
            endDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
            lastEndDate = endDate;
        }
        String url = Contact.getFullUrl(Contact.SEARCH_ORDER, Contact.TOKEN, startDate, endDate, key, pageIndex, pageSize, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<SearchOrderEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SearchOrderEntity>(url, RequestMethod.POST, SearchOrderEntity.class);
        HttpListener<SearchOrderEntity> searchByCustmor = new HttpListener<SearchOrderEntity>() {
            @Override
            public void onSucceed(int what, Response<SearchOrderEntity> response) {
                if (response.get().isOK()) {
                    iSearchOrderPresenter.searchSucceed(response.get().getData(), pageIndex == 0, response.get().getData().size() >= pageSize);
                    if (!(response.get().getData().size() < pageSize)) {//还有更多
                        pageIndex++;
                    }
                } else {
                    iSearchOrderPresenter.searchFailed(response, pageIndex);
                }
            }

            @Override
            public void onFailed(int what, Response<SearchOrderEntity> response) {
                iSearchOrderPresenter.searchFailed(response, pageIndex);
            }
        };
        new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, showWindow);
    }

    public void sosoNetLoadmore(TypeFlag Type) {
        switch (Type){
            default:
            case NOMAL:
                sosoNetOrder(lastSearchKey, lastStartDate, lastEndDate, false, false);
                break;
            case PHOTOGRAPH:
                sosoPhotoScheme(lastSearchKey, lastStartDate, lastEndDate, false, false);
                break;
            case OPTION_PANEL:
                sosoOptionPanelScheme(lastSearchKey, lastStartDate, lastEndDate, false, false);
                break;

        }

    }

    /**
     * 选片排程
     */
    public void sosoOptionPanelScheme(String key, String startDate, String endDate, boolean isFresh, boolean showWindow) {
        if (isFresh) {
            pageIndex = 0;
        }
        if (key == null) {
            key = "";
            lastSearchKey = key;
        }
        if (startDate.equals(App.getContext().getResources().getString(R.string.start_time))) {
            startDate = "20000101";
            lastStartDate = startDate;
        }
        if (endDate.equals(App.getContext().getResources().getString(R.string.end_time))) {
            endDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
            lastEndDate = endDate;
        }
        String url = Contact.getFullUrl(Contact.OPTION_PANEL_SCHEME, Contact.TOKEN, startDate, endDate, key, pageIndex, pageSize, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<SearchOptionPanelEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SearchOptionPanelEntity>(url, SearchOptionPanelEntity.class);
        HttpListener<SearchOptionPanelEntity> searchByCustmor = new HttpListener<SearchOptionPanelEntity>() {
            @Override
            public void onSucceed(int what, Response<SearchOptionPanelEntity> response) {
                if (response.get().isOK()) {
                 iSearchOptionPanelScheme.searchOptionPaneSucceed( response.get().getData(),pageIndex==0, response.get().getData().size() >=pageSize);
                    if (!(response.get().getData().size() < pageSize)) {//还有更多
                        pageIndex++;
                    }
                } else {
                    iSearchOptionPanelScheme.searchOptionPaneFailed(response,pageIndex);
                }
            }

            @Override
            public void onFailed(int what, Response<SearchOptionPanelEntity> response) {
                iSearchOptionPanelScheme.searchOptionPaneFailed(response,pageIndex);
            }
        };
        new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, showWindow);
    }

    /**
     * 拍照排程
     */
    public void sosoPhotoScheme(String key, String startDate, String endDate, boolean isFresh, boolean showWindow) {
        if (isFresh) {
            pageIndex = 0;
        }
        if (key == null) {
            key = "";
            lastSearchKey = key;
        }
        if (startDate.equals(App.getContext().getResources().getString(R.string.start_time))) {
            startDate = "20000101";
            lastStartDate = startDate;
        }
        if (endDate.equals(App.getContext().getResources().getString(R.string.end_time))) {
            endDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
            lastEndDate = endDate;
        }
        String url = Contact.getFullUrl(Contact.PHOTO_SCHEME_SEARCH, Contact.TOKEN, startDate, endDate, key, pageIndex, pageSize, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<PhotoSchemeSearchEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoSchemeSearchEntity>(url, PhotoSchemeSearchEntity.class);
        HttpListener<PhotoSchemeSearchEntity> searchByCustmor = new HttpListener<PhotoSchemeSearchEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoSchemeSearchEntity> response) {
                if (response.get().isOK()) {
                    iSearchPhotoScheme.searchPhotoSchemeSucceed(response.get().getData(), pageIndex == 0, response.get().getData().size() >= pageSize);
                    if (!(response.get().getData().size() < pageSize)) {//还有更多
                        pageIndex++;
                    }
                } else {
                    iSearchPhotoScheme.searchPhotoSchemeFailed(response, pageIndex);
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoSchemeSearchEntity> response) {
                iSearchPhotoScheme.searchPhotoSchemeFailed(response, pageIndex);
            }
        };
        new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 11, false, showWindow);
    }

}
