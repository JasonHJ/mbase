package com.mbase.monch.anim;

/**
 * Created by monch on 15/11/17.
 */
public interface ActivityAnimType {

    /**
     * 跳转动画类型，无
     */
    int NONE = 0x0;
    /**
     * 跳转动画类型，默认为从右至左方式
     */
    int DEFAULT = 0x1;
    /**
     * 跳转动画类型，渐变方式
     */
    int ALPHA = 0x2;
    /**
     * 跳转动画类型，上滑方式
     */
    int UP_GLIDE = 0x3;
    /**
     * 跳转动画类型，缩放
     */
    int SCALE = 0x4;

}
