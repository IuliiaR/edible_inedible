package com.example.android.edibleorinedible;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckboxQuestion extends LinearLayout {
    private static final String STATE_ANSWER_1 = "stateAnswer1";
    private static final String STATE_ANSWER_2 = "stateAnswer2";
    private static final String STATE_ANSWER_3 = "stateAnswer3";
    private static final String STATE_ANSWER_4 = "stateAnswer4";
    TextView question;
    CheckboxImageView[] answers;
    BerryObject[] berries;


    public CheckboxQuestion(Context context) {
        super(context);
    }

    public CheckboxQuestion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckboxQuestion(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.question = findViewById(R.id.question);
        this.answers = new CheckboxImageView[]{findViewById(R.id.answer_1),
                findViewById(R.id.answer_2),
                findViewById(R.id.answer_3),
                findViewById(R.id.answer_4)};
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putBoolean(STATE_ANSWER_1, answers[0].checkBox.isChecked());
        bundle.putBoolean(STATE_ANSWER_2, answers[1].checkBox.isChecked());
        bundle.putBoolean(STATE_ANSWER_3, answers[2].checkBox.isChecked());
        bundle.putBoolean(STATE_ANSWER_4, answers[3].checkBox.isChecked());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        Boolean answerState = bundle.getBoolean(STATE_ANSWER_1);
        answers[0].checkBox.setChecked(answerState);
        answerState = bundle.getBoolean(STATE_ANSWER_2);
        answers[1].checkBox.setChecked(answerState);
        answerState = bundle.getBoolean(STATE_ANSWER_3);
        answers[2].checkBox.setChecked(answerState);
        answerState = bundle.getBoolean(STATE_ANSWER_4);
        answers[3].checkBox.setChecked(answerState);

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

    public void initData(BerryObject[] berries) {
        this.berries = berries;
        for (int i = 0; i < berries.length; i++) {
            this.setImage(i, berries[i].getImageId());
        }
    }

    private void setImage(int index, int imageId) {
        this.answers[index].image.setImageResource(imageId);
    }

    public int getResult(int optionIndex) {
        if (answers[optionIndex].checkBox.isChecked() && berries[optionIndex].isEdible())
            return 1;
        if (!answers[optionIndex].checkBox.isChecked() && !berries[optionIndex].isEdible())
            return 1;
        return 0;
    }

    public boolean isAnswered() {
        for (CheckboxImageView answer : answers) {
            if (answer.checkBox.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public boolean isChecked(int answerIndex) {
        return answers[answerIndex].checkBox.isChecked();
    }
}