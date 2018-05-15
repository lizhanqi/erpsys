package com.suxuantech.erpsys.nohttp.ext;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.utils.FastJsonUtils;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.HashMap;
import java.util.Map;

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
 * @author Created by 李站旗 on 2018/3/9 0009 15:06 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: 对于基础的请求扩展，主要实现 请求时统一签名 参数转换为json
 */

public abstract class BaseRequest<Result> extends Request<Result> {
    public BaseRequest(String url) {
        super(url);
    }
    Map<String ,Object> jsonParam;
    public BaseRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
        addSignate();
        jsonParam=new HashMap<>();
    }
    /**
     * 请求头签名
     */
    public void addSignate(){
        Contact.SignateInfo signature = Contact.getSignate();
        addHeader("Content-TypeFlag", "application/json");
        addHeader("timestamp", signature.currentTimeMillis+"");
        addHeader("nonce",signature.random+"");
        addHeader("signature",signature.signature);
    }
    public void addBodyJson(String key ,String value){
        jsonParam.put(key,value);
    }
    public void addBodyJson(String key ,int value){
        jsonParam.put(key,value);
    }

    public void addBodyJson(String key ,boolean value){
        jsonParam.put(key,value);
    }

    /**
     * 参数转化为json
     */
    public void  param2Json(){
      //  MultiValueMap paramKeyValues = getParamKeyValues();
        addHeader("Content-TypeFlag", "application/json");
        String jsonString = FastJsonUtils.toJSONString(jsonParam);
//        if (App.ISDEBUG){
//            Logger.d(jsonString);
//        }
        setDefineRequestBodyForJson(jsonString);
    }

    @Override
    public Request setDefineRequestBodyForJson(String jsonBody) {
        if (App.ISDEBUG){
            Logger.d(jsonBody);
        }
        return super.setDefineRequestBodyForJson(jsonBody);
    }
}
