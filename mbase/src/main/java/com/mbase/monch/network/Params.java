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
    private Map<String, String> headerParams;

    public boolean put(String key, String value) {
        if (StringUtils.isEmpty(key) || value == null) return false;
        if (stringParams == null) stringParams = new LinkedHashMap<>();
        stringParams.put(key, value);
        return true;
    }

    public boolean put(String name, File file) {
        if (StringUtils.isEmpty(name) || file == null || !file.exists()) return false;
        if (fileParams == null) fileParams = new LinkedHashMap<>();
        fileParams.put(name, file);
        return true;
    }

    public boolean putHeader(String name, String value) {
        if (StringUtils.isEmpty(name) || value == null) return false;
        if (headerParams == null) headerParams = new LinkedHashMap<>();
        headerParams.put(name, value);
        return true;
    }

    public Map<String, String> getParams() {
        return stringParams != null ? stringParams : (stringParams = new LinkedHashMap<>());
    }

    public Map<String, File> getFileParams() {
        return fileParams != null ? fileParams : (fileParams = new LinkedHashMap<>());
    }

    public Map<String, String> getHeaderParams() {
        return headerParams != null ? headerParams : (headerParams = new LinkedHashMap<>());
    }

    public boolean hasParams() {
        if (stringParams == null) stringParams = new LinkedHashMap<>();
        return !stringParams.isEmpty();
    }

    public boolean hasFileParams() {
        if (fileParams == null) fileParams = new LinkedHashMap<>();
        return !fileParams.isEmpty();
    }

    public boolean hasHeaderParams() {
        if (headerParams == null) headerParams = new LinkedHashMap<>();
        return !headerParams.isEmpty();
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
        if (stringParams != null) stringParams.clear();
        if (fileParams != null) fileParams.clear();
        if (headerParams != null) headerParams.clear();
        System.gc();
    }

    @Override
    public String toString() {
        return "Params{" +
                "stringParams=" + stringParams +
                ", fileParams=" + fileParams +
                ", headerParams=" + headerParams +
                '}';
    }
}
