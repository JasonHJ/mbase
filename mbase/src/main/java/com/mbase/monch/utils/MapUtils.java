package com.mbase.monch.utils;

import java.util.Map;

/**
 * Created by monch on 15/11/17.
 */
public final class MapUtils {

    private MapUtils(){}

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static <K, V> Map<K, V> addElement(Map<K, V> map, K key, V value) {
        if (map != null) {
            map.put(key, value);
        }
        return map;
    }

    public static <K, V> V removeElement(Map<K, V> map, K key) {
        if (containsKey(map, key)) {
             return map.remove(key);
        }
        return null;
    }

    public static <K, V> V get(Map<K, V> map, K key) {
        if (containsKey(map, key)) {
            return map.get(key);
        }
        return null;
    }

    public static <K, V> boolean containsKey(Map<K, V> map, K key) {
        return !isEmpty(map) && map.containsKey(key);
    }

    public static <K, V> boolean containsValue(Map<K, V> map, V value) {
        return !isEmpty(map) && map.containsValue(value);
    }

}
