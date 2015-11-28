package com.mbase.monch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.mbase.monch.anim.ActivityAnimType;

/**
 * Created by monch on 15/11/17.
 */
public class MActivity extends FragmentActivity {

    // Activity当前是否显示
    private boolean isShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShowing = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShowing = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取当前Activity实例
     *
     * @return
     */
    public MActivity getActivity() {
        return this;
    }

    /**
     * 获取当前页面的父View
     *
     * @return
     */
    public View getView() {
        return getWindow().getDecorView();
    }

    /**
     * 获取当前页面是否显示状态
     * @return
     */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * 启动新的Activity
     *
     * @param intent 意图
     */
    @Override
    public void startActivity(Intent intent) {
        ActivityUtil.startActivity(this, intent);
    }

    /**
     * 启动新的Activity
     *
     * @param intent        意图
     * @param finishCurrent 是否销毁当前页面
     */
    public void startActivity(Intent intent, boolean finishCurrent) {
        ActivityUtil.startActivity(this, intent, finishCurrent);
    }

    /**
     * 启动新的Activity
     *
     * @param intent 意图
     * @param anim   动画类型 {@link ActivityAnimType}
     */
    public void startActivity(Intent intent, int anim) {
        ActivityUtil.startActivity(this, intent, anim);
    }

    /**
     * 启动新的Activity
     *
     * @param intent        意图
     * @param finishCurrent 是否销毁当前页面
     * @param anim          动画类型 {@link ActivityAnimType}
     */
    public void startActivity(Intent intent, boolean finishCurrent, int anim) {
        ActivityUtil.startActivity(this, intent, finishCurrent, anim);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param intent      意图
     * @param requestCode 请求码
     */
    public void startActivityForResult(Intent intent, int requestCode) {
        ActivityUtil.startActivityForResult(this, intent, requestCode);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param intent      意图
     * @param requestCode 请求码
     * @param anim        跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    public void startActivityForResult(Intent intent, int requestCode, int anim) {
        ActivityUtil.startActivityForResult(this, intent, requestCode, anim);
    }

    /**
     * 销毁一个Activity
     */
    public void finish() {
        ActivityUtil.finishActivity(this);
    }

    /**
     * 销毁一个Activity
     *
     * @param anim 跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    public void finish(int anim) {
        ActivityUtil.finishActivity(this, anim);
    }

}
