package com.mbase.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mbase.monch.utils.StringUtils;

/**
 * Created by monch on 15/11/17.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String titleText = getIntent().getStringExtra("titleText");
        if (StringUtils.isNotEmpty(titleText)) {
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(titleText);
        }
        LinearLayout parentView = (LinearLayout) findViewById(R.id.parent_view);
        String[] texts = getButtonTexts();
        int id = 0;
        for (String text : texts) {
            Button button = new Button(this);
            button.setId(id++);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(text);
            button.setOnClickListener(this);
            parentView.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        click(v.getId());
    }

    protected abstract String[] getButtonTexts();

    protected abstract void click(int id);

}
