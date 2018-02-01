package com.example.android.edibleorinedible;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class EditTextQuestion extends LinearLayout {
    TextView question;
    ImageView image;
    EditText answer;

    public EditTextQuestion(Context context) {
        super(context);
    }

    public EditTextQuestion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextQuestion(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.question = findViewById(R.id.question);
        this.image = findViewById(R.id.image);
        this.answer = findViewById(R.id.answer);
    }

    public void SetImage(int imageId) {
        this.image.setImageResource(imageId);
    }

    public boolean isAnswered() {
        return !this.GetAnswer().isEmpty();
    }

    public String GetAnswer() {
        return answer.getText().toString().toLowerCase();
    }
}
