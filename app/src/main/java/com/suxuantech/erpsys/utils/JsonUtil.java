/*
 * AUTHOR：YOLANDA
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © ZhiMore. All Rights Reserved
 *
 */
package com.suxuantech.erpsys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * </br>
 * Created in Sep 4, 2015 7:49:26 PM
 *
 * @author YOLANDA
 */
public class JsonUtil {
    private JsonUtil() {
             /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T parseJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Throwable e) {
            e.printStackTrace();
            L.e("解析发生异常：" + e.getMessage());
        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> parseJsonList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            L.i("解析数据：" + jsonString);
            List<T> tempList = JSON.parseArray(jsonString, cls);
            if (tempList != null) {
                list.addAll(tempList);
            }
        } catch (Exception e) {
            L.e("解析发生异常：" + e.getMessage());
        }
        return list;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> parseJsonListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());
            List<Map<String, Object>> tempList = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
            if (tempList != null) {
                list.addAll(tempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析为Map
     */
    public static Map<String, Object> parseJsonMap(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());
            Map<String, Object> tempMap = JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
            });
            if (tempMap != null) {
                map.putAll(tempMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
