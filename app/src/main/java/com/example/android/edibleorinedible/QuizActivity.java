package com.example.android.edibleorinedible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "player name";

    ArrayList<BerryObject> berryObjects = new ArrayList<>();
    String playerName;
    int correctScore;
    int inedibleScore;
    String textAnswer;

    RadioGroupQuestion question_1;
    RadioGroupQuestion question_2;
    CheckboxQuestion question_3;
    EditTextQuestion question_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correctScore = 0;
        inedibleScore = 0;
        textAnswer = "";
        playerName = getIntent().getStringExtra(PLAYER_NAME);

        this.initQuestionDataArray();

        question_1 = findViewById(R.id.question_1);
        question_2 = findViewById(R.id.question_2);
        question_3 = findViewById(R.id.question_3);
        question_4 = findViewById(R.id.question_4);

        question_1.initData(berryObjects.get(0));
        question_2.initData(berryObjects.get(1));
        question_3.initData(new BerryObject[]{berryObjects.get(2),
                berryObjects.get(3),
                berryObjects.get(4),
                berryObjects.get(5)});
        question_4.initData(berryObjects.get(6));
    }

    // Check user answers
    public void checkAnswers(View view) {
        if (isAllAnswered()) {
            this.checkRadioButtonQuestion(question_1);
            this.checkRadioButtonQuestion(question_2);
            this.checkCheckboxQuestion(question_3);
            this.checkEditTextQuestion(question_4);

            this.displayResults();
        } else {
            Toast.makeText(this, R.string.answer_all_questions_notification, Toast.LENGTH_SHORT).show();
        }
    }

    // Start MainActivity
    public void resetQuiz(View view) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }

    // Check answer of radio button question
    private void checkRadioButtonQuestion(RadioGroupQuestion question) {
        if (question.getResult() != 0) {
            correctScore++;
        } else inedibleScore++;
    }

    // Check answer of checkbox question
    private void checkCheckboxQuestion(CheckboxQuestion question) {
        for (int i = 0; i < 4; i++) {
            if (question.getResult(i) != 0)
                correctScore++;
            else inedibleScore++;
        }
    }

    // Check answer of edit text question
    private void checkEditTextQuestion(EditTextQuestion question) {
        if (question.getResult(this) != 0) {
            correctScore++;
        } else {
            textAnswer = String.format(getString(R.string.text_question_notification), question.GetCorrectAnswer(this));
        }
    }

    // Display toast message with quiz result
    private void displayResults() {
        Toast.makeText(this, this.createSummaryMessage(), Toast.LENGTH_LONG).show();
        findViewById(R.id.button_submit).setVisibility(View.GONE);
        findViewById(R.id.button_restart).setVisibility(View.VISIBLE);
    }

    // Create summary message with results
    private String createSummaryMessage() {
        String resultMessage = String.format("%s,", playerName);
        resultMessage += String.format(getString(R.string.correct_answers_summary), correctScore, berryObjects.size());
        if (inedibleScore != 0) {
            resultMessage += String.format(getString(R.string.wrong_answers_summary), inedibleScore);
        }
        resultMessage += String.format("\n%s", textAnswer);
        return resultMessage;
    }

    // Check that all questions are answered
    private Boolean isAllAnswered() {
        return question_1.isAnswered() && question_2.isAnswered() && question_3.isAnswered() && question_4.isAnswered();
    }

    // Initialize data array
    private void initQuestionDataArray() {
        berryObjects.add(new BerryObject(R.string.wolfberry, R.drawable.wolfberry, false));
        berryObjects.add(new BerryObject(R.string.lily_of_the_valley, R.drawable.lily_of_the_valley, false));
        berryObjects.add(new BerryObject(R.string.belladonna, R.drawable.belladonna, false));
        berryObjects.add(new BerryObject(R.string.blackberry, R.drawable.blackberry, true));
        berryObjects.add(new BerryObject(R.string.paris_herb, R.drawable.paris_herb, false));
        berryObjects.add(new BerryObject(R.string.crowberry, R.drawable.crowberry, true));
        berryObjects.add(new BerryObject(R.string.cloudberry, R.drawable.cloudberry, true));
    }
}
