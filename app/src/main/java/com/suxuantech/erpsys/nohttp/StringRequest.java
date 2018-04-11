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

import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;

/**
 * <p>自定义JavaBean请求。</p>
 * Created by Yan Zhenjie on 2016/10/15.
 */
public class StringRequest extends RestRequest {
    /**
     * 创建一个GetBean请求
     * @param url
     */
    public StringRequest(String url) {
        this(url, RequestMethod.GET);
    }
    public StringRequest(String url, RequestMethod requestMethod,Object... parameter) {
        super(url, requestMethod);
        addSignate();
    }
    public void addSignate(){
        Contact.SignateInfo signate = Contact.getSignate();
        addHeader("Content-Type", "application/json");
        addHeader("timestamp", signate.currentTimeMillis+"");
        addHeader("nonce",signate.random+"");
        addHeader("signate",signate.signate);
    }
    @Override
    public String parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String str = new String(responseBody);
        str = StringUtils.jsonStrClean(str);
        Logger.i(str);
        String response = com.yanzhenjie.nohttp.rest.StringRequest.parseResponseString(responseHeaders, str.getBytes());
        // 这里如果数据格式错误，或者解析失败，会在失败的回调方法中返回 ParseError 异常。
        return response;
    }

}
