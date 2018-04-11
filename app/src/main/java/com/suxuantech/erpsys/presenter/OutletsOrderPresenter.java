package com.suxuantech.erpsys.presenter;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.entity.OpenOrderTempEntity;
import com.suxuantech.erpsys.entity.OpenWeddingOrderEntity;
import com.suxuantech.erpsys.entity.OrderNumberEntity;
import com.suxuantech.erpsys.nohttp.CallServer;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.connector.IOutletsOrderPresenter;
import com.suxuantech.erpsys.utils.DateUtil;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Date;

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
 * @author Created by 李站旗 on 2017/12/7 0007 14:51 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description:  门市开单
 */

public class OutletsOrderPresenter {
    IOutletsOrderPresenter iOutletsOrderPresenter;
    RequestQueue requestQueue;
    public OutletsOrderPresenter(IOutletsOrderPresenter iOutletsOrderPresenter){
        this.iOutletsOrderPresenter=iOutletsOrderPresenter;
    }

    public OutletsOrderPresenter(IOutletsOrderPresenter iOutletsOrderPresenter, RequestQueue requestQueue){
        this.iOutletsOrderPresenter=iOutletsOrderPresenter;
        this.requestQueue=requestQueue;
    }

    /**
     * 获取单号
     */
    public void getOrderNum  (){
        String url= Contact.getFullUrl(Contact.ORDER_NUMBER,Contact.TOKEN,"0");
        //请求实体
        JavaBeanRequest<OrderNumberEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OrderNumberEntity>(url,RequestMethod.POST,OrderNumberEntity.class);
       HttpListener<OrderNumberEntity> searchByCustmor = new HttpListener<OrderNumberEntity>(){
           @Override
           public void onSucceed(int what, Response<OrderNumberEntity> response) {
                if (iOutletsOrderPresenter!=null&&response.get().isOK()){
                    iOutletsOrderPresenter.getOrderNumberSucceed(what ,response.get().getData());
                }
           }
           @Override
           public void onFailed(int what, Response<OrderNumberEntity> response) {
               if (iOutletsOrderPresenter!=null){
                   OrderNumberEntity orderNumberBean = response.get();
                   if (orderNumberBean!=null){
                       iOutletsOrderPresenter.getNumberFailed(orderNumberBean.getMsg());
                   }else {
                       iOutletsOrderPresenter.getNumberFailed("获取订单号失败");
                   }
               }
           }
       };
        new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 0, false, true);
       // CallServer.getInstance().add(null, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }
    /*
            开婚纱单
     */
    public void OpenOrder(OpenOrderTempEntity tempBean) {
        String url= Contact.getFullUrl(Contact.OPEN_WEDDING_ORDER,Contact.TOKEN,tempBean.getPackageSetName(),"0","0","0","0");
        //请求实体
        JavaBeanRequest<OpenWeddingOrderEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OpenWeddingOrderEntity>(url,RequestMethod.POST,OpenWeddingOrderEntity.class);
        districtBeanJavaBeanRequest.set("OrderId",tempBean.getOrderId());
        if (tempBean.getSex().equals("女")){
            districtBeanJavaBeanRequest.set("wname",tempBean.getCustromerName());
            districtBeanJavaBeanRequest.set("wphone",tempBean.getPhoneNumber());
        }else {
            districtBeanJavaBeanRequest.set("mname",tempBean.getCustromerName());
            districtBeanJavaBeanRequest.set("mphone",tempBean.getPhoneNumber());
        }
        districtBeanJavaBeanRequest.set("Sex",tempBean.getSex());
        districtBeanJavaBeanRequest.set("Consumption_type",tempBean.getConsumptionType());
        districtBeanJavaBeanRequest.set("Acceptor_address",tempBean.getOrderReceivingSite());
        districtBeanJavaBeanRequest.set("area",tempBean.getCustomerZone());
        districtBeanJavaBeanRequest.set("Storeconsuitant2",tempBean.getReception());
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY);
        Date date = DateUtil.stringToDate(nowDate, DateUtil.DatePattern.ONLY_DAY);
        String s = DateUtil.dateToString(date, DateUtil.DatePattern.JUST_DAY_NUMBER);
        districtBeanJavaBeanRequest.set("weddingdate",s);
        districtBeanJavaBeanRequest.set("customernote",tempBean.getCustomerRemarks());

        HttpListener<OpenWeddingOrderEntity> searchByCustmor = new HttpListener<OpenWeddingOrderEntity>(){
            @Override
            public void onSucceed(int what, Response<OpenWeddingOrderEntity> response) {
                if (iOutletsOrderPresenter!=null&&response.get().isOK()){
                    iOutletsOrderPresenter.getOrderNumberSucceed(what ,response.get().getData());
                }
            }
            @Override
            public void onFailed(int what, Response<OpenWeddingOrderEntity> response) {
                if (iOutletsOrderPresenter!=null){
                    iOutletsOrderPresenter.getNumberFailed(response.getException().getCause().getMessage());
                }
            }
        };
        new CallServer().setQueue(requestQueue).add(App.getContext(), districtBeanJavaBeanRequest, searchByCustmor, 9, false, true);
    }
}
