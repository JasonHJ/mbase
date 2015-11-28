package com.mbase.monch.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.mbase.monch.anim.ActivityAnim;
import com.mbase.monch.anim.ActivityAnimType;
import com.mbase.monch.utils.StringUtils;
import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;

/**
 * Created by monch on 15/11/17.
 */
final class ActivityUtil {

    private static final Loggers logger = LogManager.getLogger(ActivityUtil.class);

    /**
     * 启动一个新的Activity
     *
     * @param context 上下文
     * @param intent  意图
     */
    static void startActivity(Context context, Intent intent) {
        startActivity(context, intent, false, ActivityAnimType.DEFAULT);
    }

    /**
     * 启动一个新的Activity
     *
     * @param context      上下文
     * @param intent       意图
     * @param jumpAnimType 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    static void startActivity(Context context, Intent intent, int jumpAnimType) {
        startActivity(context, intent, false, jumpAnimType);
    }

    /**
     * 启动一个新的Activity
     *
     * @param context  上下文
     * @param intent   意图
     * @param isFinish 是否销毁当前页面
     */
    static void startActivity(Context context, Intent intent, boolean isFinish) {
        startActivity(context, intent, isFinish, ActivityAnimType.DEFAULT);
    }

    /**
     * 启动一个新的Activity
     *
     * @param context          上下文
     * @param intent           意图
     * @param isFinish         是否销毁当前页面
     * @param activityAnimType 跳转动画类型{@link ActivityAnimType}
     */
    static void startActivity(Context context, Intent intent, boolean isFinish, int activityAnimType) {
        if (context == null || intent == null) {
            logger.error("跳转Activity时，context或intent不能为空");
            return;
        }
        if (intent.getComponent() == null
                && StringUtils.isEmpty(intent.getAction())) {
            logger.error("跳转Activity时，跳转目标不能为空");
            return;
        }
        context.startActivity(intent);
        if (isFinish && context instanceof Activity) ((Activity) context).finish();
        ActivityAnim.jumpActivityAnim(context, activityAnimType);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param context     上下文
     * @param intent      意图
     * @param requestCode 请求码
     */
    static void startActivityForResult(Context context, Intent intent, int requestCode) {
        startActivityForResult(context, intent, requestCode, ActivityAnimType.DEFAULT);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param context          上下文
     * @param intent           意图
     * @param requestCode      请求码
     * @param activityAnimType 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    static void startActivityForResult(Context context, Intent intent, int requestCode, int activityAnimType) {
        if (context == null || intent == null) {
            logger.error("跳转Activity时，context或intent不能为空");
            return;
        }
        if (!(context instanceof Activity)) {
            logger.error("跳转Activity并需要返回值时，context必须为Activity");
            return;
        }
        Activity activity = (Activity) context;
        ComponentName info = intent.getComponent();
        if (info == null) {
            logger.error("跳转Activity时，跳转目标不能为空");
            return;
        }
        activity.startActivityForResult(intent, requestCode);
        ActivityAnim.jumpActivityAnim(context, activityAnimType);
    }

    /**
     * 销毁一个Activity
     *
     * @param context 上下文
     */
    static void finishActivity(Context context) {
        finishActivity(context, ActivityAnimType.DEFAULT);
    }

    /**
     * 销毁一个Activity
     *
     * @param context      上下文
     * @param jumpAnimType 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    static void finishActivity(Context context, int jumpAnimType) {
        if (context == null || !(context instanceof Activity)) {
            logger.error("销毁Activity时，context不能为空或必须为Activity");
            return;
        }
        Activity activity = (Activity) context;
        activity.finish();
        ActivityAnim.finishActivityAnim(activity, jumpAnimType);
    }

}
