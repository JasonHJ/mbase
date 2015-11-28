package com.mbase.monch.utils.size;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.mbase.monch.BaseApp;

/**
 * Created by monch on 15/11/11.
 */
public final class Scale {
    private static Point displaySize;

    private static Context getContext() {
        return BaseApp.getContext();
    }

    private static Point getDisplaySize() {
        if (displaySize == null) {
            WindowManager windowManager =
                    (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            display.getSize(displaySize = new Point());
        }
        return displaySize;
    }

    private Scale() {}

    /**
     * 获取屏幕宽度：PX
     * @return
     */
    public static int getDisplayWidth() {
        return getDisplaySize().x;
    }

    /**
     * 获取屏幕高度：PX
     * @return
     */
    public static int getDisplayHeight() {
        return getDisplaySize().y;
    }

    /**
     * DIP转PX
     * @param value
     * @return
     */
    public static int dip2px(float value) {
        float dipScale = getContext().getResources().getDisplayMetrics().density;
        return (int) (value * dipScale + 0.5f);
    }

    /**
     * PX转DIP
     * @param value
     * @return
     */
    public static int px2dip(int value) {
        float dipScale = getContext().getResources().getDisplayMetrics().density;
        return (int) (value / dipScale + 0.5f);
    }

    /**
     * SP转PX
     * @param value
     * @return
     */
    public static int sp2px(float value) {
        float spScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * spScale + 0.5f);
    }

    /**
     * PX转SP
     * @param value
     * @return
     */
    public static int px2sp(int value) {
        float spScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / spScale + 0.5f);
    }

    /**
     * 计算屏幕宽的比例值 displayWidth / value
     * @param value
     * @return
     */
    public static int scaleWidth(int value) {
        if (value <= 0) value = 1;
        return getDisplayWidth() / value;
    }

    /**
     * 计算屏幕高的比例值 displayHeight / value
     * @param value
     * @return
     */
    public static int scaleHeight(int value) {
        if (value <= 0) value = 1;
        return getDisplayHeight() / value;
    }

}
