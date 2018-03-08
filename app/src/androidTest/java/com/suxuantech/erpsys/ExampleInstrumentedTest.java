package com.suxuantech.erpsys;

import com.suxuantech.erpsys.beans.DistrictBean;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.utils.L;
import com.yanzhenjie.nohttp.rest.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    private  void Json(){
    }
    @Test
    public  void nets() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体
        JavaBeanRequest<DistrictBean> districtBeanJavaBeanRequest = new JavaBeanRequest<DistrictBean>(url,DistrictBean.class);
//        HttpResponseListener<DistrictBean> districtBeanHttpResponseListener = new HttpResponseListener<DistrictBean>(null);

        HttpListener<DistrictBean> searchByCustmor = new HttpListener<DistrictBean>(){
            @Override
            public void onSucceed(int what, Response<DistrictBean> response) {
                System.out.println("what = [" + what + "], response = [" + response + "]");
                assertEquals(response,null);
            }
            @Override
            public void onFailed(int what, Response<DistrictBean> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
//        CallServer.getInstance().add(null, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }


}
