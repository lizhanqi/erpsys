package com.suxuantech.erpsys.activity;

import android.os.Bundle;
import android.view.View;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;
import com.suxuantech.erpsys.bean.DistrictBean;
import com.suxuantech.erpsys.nohttp.CallServer;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import java.util.List;

public class MainActivity extends StatusImmersedBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
    }


public void get(){
    /**
     * 客户产品搜索JavaBean响应。
     */
    HttpListener<DistrictBean> searchByCustmor = new HttpListener<DistrictBean>() {
        @Override
        public void onSucceed(int what, Response<DistrictBean> response) {
        }
        @Override
        public void onFailed(int what, Response<DistrictBean> response) {
        }
    };

    Request<DistrictBean> request = new JavaBeanRequest<DistrictBean>("", DistrictBean.class);
    CallServer.getInstance().add(this, request, searchByCustmor, 10, true, true);

}
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            default:
        }
    }

    //请求权限后结果
    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
/*    private void nets() {
        JavaBeanRequest<DistrictBean> districtBeanJavaBeanRequest = new JavaBeanRequest<DistrictBean>();
        HttpResponseListener<DistrictBean> districtBeanHttpResponseListener = new HttpResponseListener<DistrictBean>();
        CallServer.getInstance().add(this,districtBeanJavaBeanRequest,districtBeanHttpResponseListener,10,true,true);
    }*/

    public void send() {
        Request<String> stringRequest = NoHttp.createStringRequest("http://192.168.0.188:8883/DBServer/Add", RequestMethod.POST);
        // stringRequest.addHeader("Content-Type", "application/json");
        //stringRequest.setDefineRequestBodyForJson("{\"x\":1,\"y\":2}");
        stringRequest.add("x",1);
        stringRequest.add("y",4);
        RequestQueue requestQueueInstance = NoHttp.getRequestQueueInstance();

        requestQueueInstance.add(0, stringRequest, new SimpleResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        super.onStart(what);
                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                    }
                }
        );
    }
}

