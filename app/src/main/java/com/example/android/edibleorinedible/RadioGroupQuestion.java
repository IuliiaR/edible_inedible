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
import android.widget.TextView;

public class RadioGroupQuestion extends LinearLayout {
    private static final String STATE_ANSWER = "stateAnswer";
    TextView question;
    ImageView image;
    RadioGroup answersgroup;
    RadioButton[] answers;
    BerryObject berry;

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
        this.question = findViewById(R.id.question);
        this.image = findViewById(R.id.image);
        this.answersgroup = findViewById(R.id.answers_block);
        this.answers = new RadioButton[]{this.answersgroup.findViewById(R.id.yes),
                this.answersgroup.findViewById(R.id.no)};
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putInt(STATE_ANSWER, answersgroup.getCheckedRadioButtonId());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        int answerState = bundle.getInt(STATE_ANSWER);
        answersgroup.check(answerState);
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
        this.berry = berry;
        this.SetImage(berry.getImageId());
    }

    private void SetImage(int imageId) {
        this.image.setImageResource(imageId);
    }

    public boolean isAnswered() {
        return this.answersgroup.getCheckedRadioButtonId() != -1;
    }

    public int getResult() {
        if (answers[0].isChecked() && berry.isEdible() || answers[1].isChecked() && !berry.isEdible())
            return 1;
        return 0;
    }
}
