package com.mbase.monch.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.mbase.monch.BaseApp;
import com.mbase.monch.utils.log.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by monch on 15/11/12.
 */
public final class ResourceUtils {
    private ResourceUtils(){}

    private static Context getContext() {
        return BaseApp.getContext();
    }

    /**
     * 获取Drawable文件夹下的资源ID
     * @param resourceName
     * @return
     */
    public static int getDrawableResourceId(String resourceName) {
        return getResourceId(resourceName, "drawable");
    }

    /**
     * 获取Mipmap文件夹下的资源ID
     * @param resourceName
     * @return
     */
    public static int getMipmapResourceId(String resourceName) {
        return getResourceId(resourceName, "mipmap");
    }

    /**
     * 获取资源ID
     * @param resourceName 资源名称
     * @param packageName 资源所在文件夹的名称
     * @return
     */
    public static int getResourceId(String resourceName, String packageName) {
        if (StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(packageName)) return -1;
        Context context = getContext();
        return context.getResources().getIdentifier(resourceName, packageName, context.getPackageName());
    }

    /**
     * 获取Asset下文件的字符串
     * @param fileName
     * @return
     */
    public static String getAssetFileToString(String fileName) {
        if (StringUtils.isEmpty(fileName)) return null;
        Context context = getContext();
        AssetManager am = context.getAssets();
        InputStreamReader input = null;
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        try {
            input = new InputStreamReader(am.open(fileName), "UTF-8");
            reader = new BufferedReader(input);
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            LogManager.getLogger(ResourceUtils.class).warn("read " + fileName + " error!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return result.toString();
    }

}
