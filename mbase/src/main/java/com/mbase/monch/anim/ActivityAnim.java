package com.mbase.monch.anim;

import android.app.Activity;
import android.content.Context;

import com.mbase.monch.R;

/**
 * Created by monch on 15/11/17.
 */
public class ActivityAnim {

    /**
     * 跳转Activity动画
     *
     * @param context      上下文
     * @param jumpAnimType 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    public static void jumpActivityAnim(Context context, int jumpAnimType) {
        Activity activity = null;
        if (context instanceof Activity) activity = (Activity) context;
        if (activity == null) return;
        switch (jumpAnimType) {
            case ActivityAnimType.NONE:
                activity.overridePendingTransition(0, 0);
                break;
            case ActivityAnimType.UP_GLIDE:
                activity.overridePendingTransition(R.anim.activity_new_enter_up_glide, R.anim.activity_old_exit_up_glide);
                break;
            case ActivityAnimType.ALPHA:
                activity.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                break;
            case ActivityAnimType.SCALE:
                activity.overridePendingTransition(R.anim.activity_scale_new_open, R.anim.activity_scale_old_close);
                break;
            default:
                activity.overridePendingTransition(R.anim.activity_new_enter_default, R.anim.activity_old_exit_default);
                break;
        }
    }

    /**
     * 销毁Activity动画
     *
     * @param activity     上下文
     * @param jumpAnimType 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    public static void finishActivityAnim(Activity activity, int jumpAnimType) {
        switch (jumpAnimType) {
            case ActivityAnimType.NONE:
                activity.overridePendingTransition(0, 0);
                break;
            case ActivityAnimType.UP_GLIDE:
                activity.overridePendingTransition(R.anim.activity_old_enter_up_glide, R.anim.activity_new_exit_up_glide);
                break;
            case ActivityAnimType.ALPHA:
                activity.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                break;
            case ActivityAnimType.SCALE:
                activity.overridePendingTransition(R.anim.activity_scale_old_open, R.anim.activity_scale_new_close);
                break;
            default:
                activity.overridePendingTransition(R.anim.activity_old_enter_default, R.anim.activity_new_exit_default);
                break;
        }
    }

}
