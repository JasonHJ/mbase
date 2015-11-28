package com.mbase.monch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbase.monch.anim.ActivityAnimType;

/**
 * Created by monch on 15/11/17.
 */
public class MFragment extends Fragment {

    /**
     * Fragment的所属Activity
     */
    protected Activity activity;
    // 当前的显示状态
    private boolean isShowing = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        isShowing = true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isShowing = hidden;
    }

    @Override
    public void onPause() {
        super.onPause();
        isShowing = false;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取当前页面是否显示状态
     *
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
        if (activity == null) return;
        ActivityUtil.startActivity(activity, intent);
    }

    /**
     * 启动新的Activity
     *
     * @param intent        意图
     * @param finishCurrent 是否销毁当前页面
     */
    public void startActivity(Intent intent, boolean finishCurrent) {
        if (activity == null) return;
        ActivityUtil.startActivity(activity, intent, finishCurrent);
    }

    /**
     * 启动新的Activity
     *
     * @param intent 意图
     * @param anim   动画类型 {@link ActivityAnimType}
     */
    public void startActivity(Intent intent, int anim) {
        if (activity == null) return;
        ActivityUtil.startActivity(activity, intent, anim);
    }

    /**
     * 启动新的Activity
     *
     * @param intent        意图
     * @param finishCurrent 是否销毁当前页面
     * @param anim          动画类型 {@link ActivityAnimType}
     */
    public void startActivity(Intent intent, boolean finishCurrent, int anim) {
        if (activity == null) return;
        ActivityUtil.startActivity(activity, intent, finishCurrent, anim);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param intent      意图
     * @param requestCode 请求码
     */
    public void startActivityForResult(Intent intent, int requestCode) {
        if (activity == null) return;
        ActivityUtil.startActivityForResult(activity, intent, requestCode);
    }

    /**
     * 启动一个新的Activity，返回时并需要带返回值
     *
     * @param intent      意图
     * @param requestCode 请求码
     * @param anim        跳转动画，类型使用{@link ActivityAnimType}下的常量类型
     */
    public void startActivityForResult(Intent intent, int requestCode, int anim) {
        if (activity == null) return;
        ActivityUtil.startActivityForResult(activity, intent, requestCode, anim);
    }

}
