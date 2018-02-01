package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CheckboxImageView extends RelativeLayout {
    ImageView image;
    CheckBox checkBox;

    public CheckboxImageView(Context context) {
        super(context);
    }

    public CheckboxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckboxImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.image = findViewById(R.id.image);
        this.checkBox = findViewById(R.id.checkbox);
    }
}
