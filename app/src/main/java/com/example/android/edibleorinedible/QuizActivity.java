package com.example.android.edibleorinedible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "player name";

    ArrayList<BerryObject> berryObjects = new ArrayList<>();
    String playerName;
    int correctScore;
    int inedibleScore;
    String textAnswer;

    RadioGroupQuestionView q1;
    RadioGroupQuestionView q2;
    CheckboxQuestionView q3;
    EditTextQuestionView q4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correctScore = 0;
        inedibleScore = 0;
        textAnswer = "";
        playerName = getIntent().getStringExtra(PLAYER_NAME);

        this.initQuestionDataArray();

        q1 = findViewById(R.id.question_1);
        q2 = findViewById(R.id.question_2);
        q3 = findViewById(R.id.question_3);
        q4 = findViewById(R.id.question_4);

        q1.initData(berryObjects.get(0));
        q2.initData(berryObjects.get(1));
        q3.initData(new BerryObject[]{berryObjects.get(2),
                berryObjects.get(3),
                berryObjects.get(4),
                berryObjects.get(5)});
        q4.initData(berryObjects.get(6));
    }

    public void checkAnswers(View view) {
        if (isAllAnswered()) {
            this.checkRadioButtonQuestion(q1);
            this.checkRadioButtonQuestion(q2);
            this.checkCheckboxQuestion(q3);
            this.checkEditTextQuestion(q4);

            this.displayResults();
        } else {
            Toast.makeText(this, R.string.answer_all_questions_notification, Toast.LENGTH_SHORT).show();
        }
    }

    public void resetQuiz(View view) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }

    private void checkRadioButtonQuestion(RadioGroupQuestionView question) {
        if (question.getResult() != 0) {
            correctScore++;
        } else inedibleScore++;
    }

    private void checkCheckboxQuestion(CheckboxQuestionView question) {
        for (int i = 0; i < 4; i++) {
            if (question.getResult(i) != 0)
                correctScore++;
            else inedibleScore++;
        }
    }

    private void checkEditTextQuestion(EditTextQuestionView question) {
        if (question.getResult(this) != 0) {
            correctScore++;
        } else {
            textAnswer = String.format(getString(R.string.text_question_notification), question.GetCorrectAnswer(this));
        }
    }

    private void displayResults() {
        Toast.makeText(this, this.createSummaryMessage(), Toast.LENGTH_LONG).show();
        findViewById(R.id.button_submit).setVisibility(View.GONE);
        findViewById(R.id.button_restart).setVisibility(View.VISIBLE);
    }

    private String createSummaryMessage() {
        String resultMessage = String.format("%s,", playerName);
        resultMessage += String.format(getString(R.string.correct_answers_summary), correctScore, 7);
        if (inedibleScore != 0) {
            resultMessage += String.format(getString(R.string.wrong_answers_summary), inedibleScore);
        }
        resultMessage += String.format("\n%s", textAnswer);
        return resultMessage;
    }

    private Boolean isAllAnswered() {
        return q1.isAnswered() && q2.isAnswered() && q4.isAnswered();
    }

    private void initQuestionDataArray() {
        berryObjects.add(new BerryObject(R.string.wolfberry, R.drawable.wolfberry, false));
        berryObjects.add(new BerryObject(R.string.lily_of_the_valley, R.drawable.lily_of_the_valley, false));
        berryObjects.add(new BerryObject(R.string.belladonna, R.drawable.belladonna, false));
        berryObjects.add(new BerryObject(R.string.blackberry, R.drawable.blackberry, true));
        berryObjects.add(new BerryObject(R.string.paris_herb, R.drawable.paris_herb, false));
        berryObjects.add(new BerryObject(R.string.crowberry, R.drawable.crowberry, true));
        berryObjects.add(new BerryObject(R.string.cloudberry, R.drawable.cloudberry, true));
        Collections.shuffle(berryObjects);
    }
}
