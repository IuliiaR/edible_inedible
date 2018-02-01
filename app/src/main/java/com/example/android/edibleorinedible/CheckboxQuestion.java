package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckboxQuestion extends LinearLayout {
    TextView question;
    CheckboxImageView[] answers;
    CheckboxImageView answer_1;
    CheckboxImageView answer_2;
    CheckboxImageView answer_3;
    CheckboxImageView answer_4;

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

    public void SetImage(int[] imageIds) {
        this.answers[0].image.setImageResource(imageIds[0]);
        this.answers[1].image.setImageResource(imageIds[1]);
        this.answers[2].image.setImageResource(imageIds[2]);
        this.answers[3].image.setImageResource(imageIds[3]);
    }

    public boolean IsChecked(int answerIndex) {
        return answers[answerIndex].checkBox.isChecked();
    }
}