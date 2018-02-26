package com.example.android.edibleorinedible;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CheckboxImageView extends RelativeLayout {
    private static final String STATE_ANSWER = "stateAnswer";
    private ImageView mImage;
    private CheckBox mCheckBox;

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
        this.mImage = findViewById(R.id.image);
        this.mCheckBox = findViewById(R.id.checkbox);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        boolean currentState = mCheckBox.isChecked();
        bundle.putBoolean(STATE_ANSWER, currentState);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        boolean answerState = bundle.getBoolean(STATE_ANSWER);
        mCheckBox.setChecked(answerState);

        state = bundle.getParcelable("superState");
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray container) {
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray container) {
        super.dispatchThawSelfOnly(container);
    }

    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    public void setImage(int imageId) {
        this.mImage.setImageResource(imageId);
    }
}
