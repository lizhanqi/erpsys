package com.suxuantech.erpsys.presenter;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.HistoryBeanDao;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.bean.HistoryBean;
import com.suxuantech.erpsys.bean.SearchOrderBean;
import com.suxuantech.erpsys.nohttp.CallServer;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.utils.DateUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Collections;
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
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:搜索订单的逻辑类
 */

public class SearchOrderPresenter {
    ISearchOrderPresenter iSearchOrderPresenter;
    private HistoryBeanDao historyDao;
    private List<HistoryBean> searchHosiery;
    public SearchOrderPresenter(ISearchOrderPresenter iSearchOrderPresenter) {
        this.iSearchOrderPresenter = iSearchOrderPresenter;
        initDB();
        searchHosiery=loadAllHistory();
    }
    RequestQueue     requestQueue;
    public SearchOrderPresenter(ISearchOrderPresenter iOutletsOrderPresenter, RequestQueue requestQueue){
        this.iSearchOrderPresenter = iSearchOrderPresenter;
        initDB();
        searchHosiery=loadAllHistory();
        this.requestQueue=requestQueue;
    }

    private void initDB() {
        //初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "myhistory.db", null);
        //不清楚
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //表的集合
        DaoSession daoSession = daoMaster.newSession();
        //历史表
        historyDao = daoSession.getHistoryBeanDao();
    }

    public List<HistoryBean> clear(){
        //删除表内数据
        historyDao.deleteAll();
        searchHosiery.clear();
        return searchHosiery;
    }
    /**
     *    插入搜索历史
     */
    public  List<HistoryBean> insert(String trim){
        //判断是否存在数据库中，如果存在则不再存储
        for(HistoryBean h:searchHosiery){
            String name = h.getName();
            if (name.equals(trim)){
                return searchHosiery;
            }
        }
        HistoryBean studentMsgBean = new HistoryBean();
        studentMsgBean.setName(trim);
        historyDao.insert(studentMsgBean);
      return   searchHosiery =loadAllHistory();
    }
    /**
     * 获取本地DB中搜索历史
     */
    private List<HistoryBean> loadAllHistory(){
        searchHosiery = historyDao.loadAll();
        //倒序下，因为最新的要在上面显示
        Collections.reverse(searchHosiery);
        return   searchHosiery;
    }
    /**
     * 获取内存中的本地数据
     */
    public List<HistoryBean> getSearchHosiery(){
        return   searchHosiery;
    }


    /**
     * 获取内存中的本地数据
     */
    public String [] getSearchHosieryArray(){
        String[] strings=null;
        if (searchHosiery!=null){
          strings = new String[searchHosiery.size()];
            for (int i =0;i< searchHosiery.size();i++){
                strings[i]=searchHosiery.get(i).getName();
            }
        }
        return   strings;
    }
    int pageIndex;
    int pageSize=20;
    String lastStartDate,lastEndDate,lastSearchKey,lastShopKey;
    /**
     * 搜索网络订单
     * @param trim
     * @param mTvSosoStorefront cky
     * @param startDate
     * @param endDate
     */

    public void sosoNetOrder(String trim, String mTvSosoStorefront, String startDate, String endDate,boolean isFresh) {
       if (isFresh){
           pageIndex=0;
       }
        if (trim == null) {
            trim = "";
            lastSearchKey=trim;
        }
        if (startDate.equals(App.getContext().getResources().getString(R.string.start_time))){
            startDate = "20000101";
            lastStartDate=startDate;
        }
        if (endDate.equals(App.getContext().getResources().getString(R.string.end_time))){
            endDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
            lastEndDate=endDate;
        }
        String url= Contact.getFullUrl(Contact.SEARCH_ORDER,Contact.TOKEN,
                trim,Contact.TOKEN,startDate,endDate,pageIndex,pageSize);
            //请求实体
            JavaBeanRequest<SearchOrderBean> districtBeanJavaBeanRequest = new JavaBeanRequest<SearchOrderBean>(url, RequestMethod.POST,SearchOrderBean.class);
            HttpListener<SearchOrderBean> searchByCustmor = new HttpListener<SearchOrderBean>(){
                @Override
                public void onSucceed(int what, Response<SearchOrderBean> response) {
                    if(response.get().isOK()){
                        iSearchOrderPresenter.searchSucceed( response.get().getData(),pageIndex==0, response.get().getData().size() >=pageSize);
                        if (!(response.get().getData().size() <pageSize)){//还有更多
                            pageIndex++;
                        }
                    }else {
                        iSearchOrderPresenter.searchFailed(response,pageIndex);
                    }
               }
                @Override
                public void onFailed(int what, Response<SearchOrderBean> response) {
                    iSearchOrderPresenter.searchFailed(response,pageIndex);
                }
            };
            new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, true);
        }
    public void sosoNetLoadmore() {
        sosoNetOrder(lastSearchKey,lastShopKey,lastStartDate,lastEndDate,false);
    }
}
