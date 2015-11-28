package com.mbase.monch.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monch on 15/11/14.
 */
public class ApiResult {

    private static final String tag = ApiResult.class.getSimpleName();

    // 信息
    public ApiMessage message;
    // 数据集合
    private Map<String, Object> map;

    public ApiResult() {
        map = new HashMap<>();
    }

    // 添加参数
    public <T> T add(int position, T value) {
        return add(getStringPosition(position), value);
    }

    // 添加参数
    public <T> T add(String key, T value) {
        map.put(key, value);
        return value;
    }

    // 删除参数
    public <T> T remove(int position) {
        return remove(getStringPosition(position));
    }

    // 删除参数
    public <T> T remove(String key) {
        if (map.containsKey(key)) {
            return getValue(map.remove(key));
        }
        return null;
    }

    // 获取参数
    public <T> T get(int position) {
        return get(getStringPosition(position));
    }

    // 获取参数
    public <T> T get(String key) {
        if (map.containsKey(key)) {
            return getValue(map.get(key));
        }
        return null;
    }

    // 清除所有数据
    public void clear() {
        message = null;
        map.clear();
        System.gc();
    }

    // 返回结果异常
    public boolean isNotError() {
        return message == null || message.code == 0;
    }

    // 获取字符串类型的下标组装Key
    private static String getStringPosition(int position) {
        return tag + "_" + position;
    }

    // 使用Object获取泛型
    private static <T> T getValue(Object value) {
        T result;
        try {
            result = (T) value;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "message=" + message +
                ", map=" + map +
                '}';
    }
}
