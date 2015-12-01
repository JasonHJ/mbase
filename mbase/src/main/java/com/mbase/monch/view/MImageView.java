package com.mbase.monch.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mbase.monch.BaseApp;
import com.mbase.monch.utils.StringUtils;

import java.io.File;

/**
 * Created by monch on 15/12/1.
 */
public class MImageView extends SimpleDraweeView {

    public MImageView(Context context) {
        super(context);
    }

    public MImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public void setUrl(String url) {
        if (StringUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);
        setImageURI(uri);
    }

    public void setResource(int resId) {
        Uri uri = Uri.parse("res://" + BaseApp.getPackageName() + "/" + resId);
        setImageURI(uri);
    }

    public void setAsset(String fileName) {
        Uri uri = Uri.parse("asset://" + BaseApp.getPackageName() + "/" + fileName);
        setImageURI(uri);
    }

    public void setFile(File file) {
        if (file == null || !file.exists()) return;
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        setImageURI(uri);
    }

}
