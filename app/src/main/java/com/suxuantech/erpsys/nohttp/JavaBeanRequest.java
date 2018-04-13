/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suxuantech.erpsys.nohttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * <p>自定义JavaBean请求。</p>
 * Created by Yan Zhenjie on 2016/10/15.
 */
public class JavaBeanRequest<T> extends Request<T> {
    private Class<T> clazz;

    /**
     * 创建一个GetBean请求
     * @param url
     * @param clazz
     */
    public JavaBeanRequest(String url, Class<T> clazz) {
        this(url, RequestMethod.GET, clazz);
    }
    public JavaBeanRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        addSignate();
        this.clazz = clazz;
    }
    /*
       *添加自定义请求头进行所谓的加密
     */
    public void addSignate(){
        Contact.SignateInfo signature = Contact.getSignate();
        addHeader("Content-Type", "application/json");
        addHeader("timestamp", ""+signature.currentTimeMillis);
        addHeader("nonce",""+signature.random);
        addHeader("signature",signature.signature);
  //      signature
    }
    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String str = new String(responseBody);
        str = StringUtils.jsonStrClean(str);
        Logger.i(str);
        String response = StringRequest.parseResponseString(responseHeaders, str.getBytes());
        JSONObject jsonObject = JSON.parseObject(response);
        return JSON.parseObject(response, clazz);
    }
}
