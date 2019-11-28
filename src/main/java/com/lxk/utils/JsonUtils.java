package com.lxk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 转换
 */
public final class JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 把Java对象转换成json字符串
     *
     * @param object 待转化为JSON字符串的Java对象
     * @return json 串 or null
     */
    public static String parseObjToJson(Object object) {
        String string = null;
        try {
            string = OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception ignored) {
        }
        return string;
    }

    /**
     * 将Json字符串信息转换成对应的Java对象
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return null;
    }
}
