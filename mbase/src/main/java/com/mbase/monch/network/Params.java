package com.mbase.monch.network;

import com.mbase.monch.utils.StringUtils;
import com.mbase.monch.utils.encrypt.URLEncoderUtil;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by monch on 15/11/14.
 */
public class Params {

    private Map<String, String> stringParams;
    private Map<String, File> fileParams;

    public Params() {
        stringParams = new LinkedHashMap<>();
        fileParams = new LinkedHashMap<>();
    }

    public boolean put(String key, String value) {
        if (StringUtils.isEmpty(key) || value == null) return false;
        stringParams.put(key, value);
        return true;
    }

    public boolean put(String name, File file) {
        if (StringUtils.isEmpty(name) || file == null || !file.exists()) return false;
        fileParams.put(name, file);
        return true;
    }

    public Map<String, String> getParams() {
        return stringParams;
    }

    public Map<String, File> getFileParams() {
        return fileParams;
    }

    public boolean hasParams() {
        return stringParams.size() > 0;
    }

    public boolean hasFileParams() {
        return fileParams.size() > 0;
    }

    /**
     * 获取组装后的URL
     * @param url
     * @return
     */
    public String getAssembledUrl(String url) {
        if (hasParams()) {
            StringBuilder sb = new StringBuilder();
            if (url.contains("?")) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            Map<String, String> mapParams = getParams();
            for (Map.Entry<String, String> entry : mapParams.entrySet()) {
                String key = URLEncoderUtil.encoder(entry.getKey());
                String value = URLEncoderUtil.encoder(entry.getValue());
                sb.append(key).append("=").append(value).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            url += sb.toString();
        }
        return url;
    }

    public void clear() {
        stringParams.clear();
        fileParams.clear();
        System.gc();
    }

    @Override
    public String toString() {
        return "Params{" +
                "stringParams=" + stringParams +
                ", fileParams=" + fileParams +
                '}';
    }
}
