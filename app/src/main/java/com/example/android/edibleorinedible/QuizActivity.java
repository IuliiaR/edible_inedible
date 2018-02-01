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
    int wrongScore;
    String textAnswer;

    RadioGroupQuestion q1;
    RadioGroupQuestion q2;
    CheckboxQuestion q3;
    EditTextQuestion q4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correctScore = 0;
        wrongScore = 0;
        textAnswer = "";
        playerName = getIntent().getStringExtra(PLAYER_NAME);

        this.initQuestionDataArray();

        q1 = findViewById(R.id.question_1);
        q2 = findViewById(R.id.question_2);
        q3 = findViewById(R.id.question_3);
        q4 = findViewById(R.id.question_4);

        q1.SetImage(berryObjects.get(0).getImageId());
        q2.SetImage(berryObjects.get(1).getImageId());
        q3.SetImage(new int[]{berryObjects.get(2).getImageId(),
                berryObjects.get(3).getImageId(),
                berryObjects.get(4).getImageId(),
                berryObjects.get(5).getImageId()});
        q4.SetImage(berryObjects.get(6).getImageId());
    }

    public void checkAnswers(View view) {
        if (isAllAnswered()) {
            this.checkRadioButtonQuestion(q1, 0);
            this.checkRadioButtonQuestion(q2, 1);
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

    private void checkRadioButtonQuestion(RadioGroupQuestion question, int index) {
        if (((question.GetAnswer() == R.id.yes)
                && berryObjects.get(0).isEdible())
                || ((question.GetAnswer() == R.id.no)
                && !berryObjects.get(0).isEdible())) {
            correctScore++;
        } else wrongScore++;
    }

    private void checkCheckboxQuestion(CheckboxQuestion question) {
        for (int i = 0; i < 4; i++) {
            if (question.IsChecked(i) && berryObjects.get(i + 2).isEdible())
                correctScore++;
            if (question.IsChecked(i) && !berryObjects.get(i + 2).isEdible())
                wrongScore++;
        }
    }

    private void checkEditTextQuestion(EditTextQuestion question) {
        String berryName = getString(berryObjects.get(6).getNameId());
        if (question.GetAnswer().equalsIgnoreCase(berryName)) {
            correctScore++;
        } else {
            textAnswer = String.format(getString(R.string.text_question_notification), berryName);
        }
    }

    private void displayResults() {
        Toast.makeText(this, this.createSummaryMessage(), Toast.LENGTH_LONG).show();
        findViewById(R.id.button_submit).setVisibility(View.GONE);
        findViewById(R.id.button_restart).setVisibility(View.VISIBLE);
    }

    private String createSummaryMessage() {
        String resultMessage = String.format("%s,", playerName);
        resultMessage += String.format(getString(R.string.correct_answers_summary), correctScore, 3);
        if (wrongScore != 0) {
            resultMessage += String.format(getString(R.string.wrong_answers_summary), wrongScore);
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
        berryObjects.add(new BerryObject(R.string.crow_eye, R.drawable.croweye, false));
        berryObjects.add(new BerryObject(R.string.crowberry, R.drawable.crowberry, true));
        berryObjects.add(new BerryObject(R.string.cloudberry, R.drawable.cloudberry, true));
        Collections.shuffle(berryObjects);
    }
}
