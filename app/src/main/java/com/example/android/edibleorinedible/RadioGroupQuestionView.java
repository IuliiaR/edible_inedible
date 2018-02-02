package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioGroupQuestionView extends LinearLayout {
    TextView question;
    ImageView image;
    RadioGroup answersgroup;
    RadioButton[] answers;

    BerryObject berry;

    public RadioGroupQuestionView(Context context) {
        super(context);
    }

    public RadioGroupQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioGroupQuestionView(Context context, AttributeSet attrs, int defStyle) {
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
