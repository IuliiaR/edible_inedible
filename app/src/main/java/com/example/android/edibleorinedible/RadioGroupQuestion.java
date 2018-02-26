package com.example.android.edibleorinedible;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioGroupQuestion extends LinearLayout {
    private static final String STATE_ANSWER = "stateAnswer";
    private ImageView mImage;
    private RadioGroup mAnswersgroup;
    private RadioButton[] mAnswers;
    private BerryObject mBerry;

    public RadioGroupQuestion(Context context) {
        super(context);
    }

    public RadioGroupQuestion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioGroupQuestion(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImage = findViewById(R.id.image);
        this.mAnswersgroup = findViewById(R.id.answers_block);
        this.mAnswers = new RadioButton[]{this.mAnswersgroup.findViewById(R.id.yes),
                this.mAnswersgroup.findViewById(R.id.no)};
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putInt(STATE_ANSWER, mAnswersgroup.getCheckedRadioButtonId());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        int answerState = bundle.getInt(STATE_ANSWER);
        mAnswersgroup.check(answerState);
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

    public void initData(BerryObject berry) {
        this.mBerry = berry;
        this.SetImage(berry.getImageId());
    }

    private void SetImage(int imageId) {
        this.mImage.setImageResource(imageId);
    }

    public boolean isAnswered() {
        return this.mAnswersgroup.getCheckedRadioButtonId() != -1;
    }

    public int getResult() {
        // If user gave correct answer return 1
        if (mAnswers[0].isChecked() && mBerry.isEdible() || mAnswers[1].isChecked() && !mBerry.isEdible())
            return 1;

            // If user checked inedible berry return -1
        else if (mAnswers[0].isChecked() && !mBerry.isEdible())
            return -1;

        // If user didn't check edible berry return 0
        return 0;
    }
}
