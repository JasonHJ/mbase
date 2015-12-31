package com.mbase.monch;

import android.content.Context;

import com.mbase.monch.common.Preconditions;

/**
 * Created by monch on 15/11/11.
 */
public final class MBase {

    private MBase(){}

    // 初始化MBase
    public static void initialize(Context context, BaseConfig config) {
        BaseApp.init(
                Preconditions.checkNotNull(context,
                        "MBase的Context不允许为空，并且需要使用Application的上下文"),
                Preconditions.checkNotNull(config,
                        "MBase的BaseConfig不允许为空，请初始化必要参数"));
    }

}
