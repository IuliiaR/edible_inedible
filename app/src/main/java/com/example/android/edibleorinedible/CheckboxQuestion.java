package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CheckboxQuestion extends LinearLayout {
    private CheckboxImageView[] mAnswers;
    private BerryObject[] mBerries;

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
        this.mAnswers = new CheckboxImageView[]{findViewById(R.id.answer_1),
                findViewById(R.id.answer_2),
                findViewById(R.id.answer_3),
                findViewById(R.id.answer_4)};
    }

    public void initData(BerryObject[] berries) {
        this.mBerries = berries;
        for (int i = 0; i < berries.length; i++) {
            this.setImage(i, berries[i].getImageId());
        }
    }

    private void setImage(int index, int imageId) {
        this.mAnswers[index].setImage(imageId);
    }

    public int getResult(int optionIndex) {
        // If user gave correct answer return 1
        if (mAnswers[optionIndex].isChecked() && mBerries[optionIndex].isEdible())
            return 1;
        if (!mAnswers[optionIndex].isChecked() && !mBerries[optionIndex].isEdible())
            return 1;

        // If user checked inedible berry return -1
        if (mAnswers[optionIndex].isChecked() && !mBerries[optionIndex].isEdible())
            return -1;

        // If user didn't check edible berry return 0
        return 0;
    }

    public boolean isAnswered() {
        for (CheckboxImageView answer : mAnswers) {
            if (answer.isChecked()) {
                return true;
            }
        }
        return false;
    }
}