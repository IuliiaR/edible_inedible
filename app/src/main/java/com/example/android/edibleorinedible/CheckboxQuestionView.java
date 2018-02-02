package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckboxQuestionView extends LinearLayout {
    TextView question;
    CheckboxImageView[] answers;

    BerryObject[] berries;

    public CheckboxQuestionView(Context context) {
        super(context);
    }

    public CheckboxQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckboxQuestionView(Context context, AttributeSet attrs, int defStyle) {
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

    public void initData(BerryObject[] berries) {
        this.berries = berries;
        for (int i = 0; i < berries.length; i++) {
            this.SetImage(i, berries[i].getImageId());
        }
    }

    private void SetImage(int index, int imageId) {
        this.answers[index].image.setImageResource(imageId);
    }

    public int getResult(int optionIndex) {
        if (answers[optionIndex].checkBox.isChecked() && berries[optionIndex].isEdible())
            return 1;
        if (!answers[optionIndex].checkBox.isChecked() && !berries[optionIndex].isEdible())
            return 1;
        return 0;
    }

    public boolean IsChecked(int answerIndex) {
        return answers[answerIndex].checkBox.isChecked();
    }
}