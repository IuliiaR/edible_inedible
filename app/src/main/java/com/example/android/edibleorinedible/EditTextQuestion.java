package com.example.android.edibleorinedible;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditTextQuestion extends LinearLayout {
    private static final String STATE_ANSWER = "stateAnswer";
    TextView question;
    ImageView image;
    EditText answer;
    BerryObject berry;
    private String selectedAnswer;

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

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putString(STATE_ANSWER, answer.getText().toString());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        String answerState = bundle.getString(STATE_ANSWER);
        answer.setText(answerState);

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
        return !this.GetAnswer().isEmpty();
    }

    public int getResult(Context context) {
        String name = GetCorrectAnswer(context);
        if (this.GetAnswer().equalsIgnoreCase(name))
            return 1;
        return 0;
    }

    private String GetAnswer() {
        return answer.getText().toString().toLowerCase();
    }

    public String GetCorrectAnswer(Context context) {
        return context.getResources().getString(this.berry.getNameId());
    }
}
