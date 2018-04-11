package com.suxuantech.erpsys;

import com.suxuantech.erpsys.entity.DistrictEntity;
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
    @Test
    public  void nets() {
        String url="http://47.93.81.122:8288/WebAppErpStaff/Cus_LoginCheck?Token=000000⊱左岸摄影⊱ZX0118&userName=wendy&userPwd=0&Cid=0";
        //请求实体
        JavaBeanRequest<DistrictEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<DistrictEntity>(url,DistrictEntity.class);
//        HttpResponseListener<DistrictEntity> districtBeanHttpResponseListener = new HttpResponseListener<DistrictEntity>(null);

        HttpListener<DistrictEntity> searchByCustmor = new HttpListener<DistrictEntity>(){
            @Override
            public void onSucceed(int what, Response<DistrictEntity> response) {
                System.out.println("what = [" + what + "], response = [" + response + "]");
                assertEquals(response,null);
            }
            @Override
            public void onFailed(int what, Response<DistrictEntity> response) {
                L.i("失败"+what+"\n"+response.get());
                System.out.println("失败what = [" + what + "], response = [" + response + "]");
            }
        };
//        CallServer.getInstance().add(null, districtBeanJavaBeanRequest, searchByCustmor, 0, true, true);
    }


}
