package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioGroupQuestion extends LinearLayout {
    TextView question;
    ImageView image;
    RadioGroup answers;

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
        this.answers = findViewById(R.id.answers_block);
    }

    public void SetImage(int imageId) {
        this.image.setImageResource(imageId);
    }

    public boolean isAnswered() {
        return this.GetAnswer() != -1;
    }

    public int GetAnswer() {
        return answers.getCheckedRadioButtonId();
    }
}
