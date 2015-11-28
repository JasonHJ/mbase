package com.mbase.monch.utils.toast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mbase.monch.BaseApp;
import com.mbase.monch.common.Preconditions;
import com.mbase.monch.utils.size.Scale;

/**
 * Created by monch on 15/11/11.
 */
public final class T {

    private static Toast toast;
    private static ToastView toastView;

    /**
     * 获取上下文
     **/
    private static Context getContext() {
        return BaseApp.getContext();
    }

    /**
     * 获取Toast View
     **/
    private static ToastView getToastView() {
        if (toastView == null) {
            toastView = new ToastView();
        }
        return toastView;
    }

    private T() {
    }

    public static void ss(CharSequence text) {
        Context context = getContext();
        s(context, text, Toast.LENGTH_SHORT);
    }

    public static void ss(int resId) {
        Context context = getContext();
        CharSequence text = getText(context, resId);
        s(context, text, Toast.LENGTH_SHORT);
    }

    public static void ss(Context context, CharSequence text) {
        s(context, text, Toast.LENGTH_SHORT);
    }

    public static void ss(Context context, int resId) {
        CharSequence text = getText(context, resId);
        s(context, text, Toast.LENGTH_SHORT);
    }

    public static void sl(CharSequence text) {
        Context context = getContext();
        s(context, text, Toast.LENGTH_LONG);
    }

    public static void sl(int resId) {
        Context context = getContext();
        CharSequence text = getText(context, resId);
        s(context, text, Toast.LENGTH_LONG);
    }

    public static void sl(Context context, CharSequence text) {
        s(context, text, Toast.LENGTH_LONG);
    }

    public static void sl(Context context, int resId) {
        CharSequence text = getText(context, resId);
        s(context, text, Toast.LENGTH_LONG);
    }

    public static void s(Context context, CharSequence text, int duration) {
        if (Preconditions.isNotNull(text)) {
            if (toast != null) toast.cancel();
            getToast(context, text, duration).show();
        }
    }

    private static CharSequence getText(Context context, int resId) {
        Resources resources = context.getResources();
        try {
            return resources.getText(resId);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    private static Toast getToast(Context context, CharSequence text, int duration) {
        toast = new Toast(context);
        View view = getToastView().getView(context, text);
        toast.setView(view);
        toast.setDuration(duration);
        return toast;
    }

    private static class ToastView {

        // Toast文字的默认颜色
        private static final int DEFAULT_TEXT_COLOR = Color.rgb(250, 250, 250);
        // Toast文字的默认大小
        private static final int DEFAULT_TEXT_SIZE = 16;

        private ViewGroup.LayoutParams params;
        private int padding;
        // View的背景样式
        private GradientDrawable background;

        ToastView() {
            padding = Scale.dip2px(10);
            int displayWidth = Scale.getDisplayWidth();
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.width = displayWidth - (padding * 2);
            background = new GradientDrawable();
            background.setColor(Color.argb(180, 0, 0, 0));
            background.setCornerRadius(padding / 2);
        }

        // 获取Toast的View
        View getView(Context context, CharSequence text) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setPadding(padding, padding, padding, padding);
            textView.setBackgroundDrawable(background);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE);
            textView.setTextColor(DEFAULT_TEXT_COLOR);
            textView.setGravity(Gravity.CENTER);
            textView.setText(text);
            return textView;
        }

    }

}
