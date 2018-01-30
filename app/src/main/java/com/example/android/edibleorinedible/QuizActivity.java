package com.example.android.edibleorinedible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    ArrayList<BerryObject> berryObjects = new ArrayList<>();
    public static final String PLAYER_NAME = "player name";

    String playerName;
    int correctScore;
    int wrongScore;
    String textAnswer;

    ImageView question1_image;
    ImageView question2_image;
    ImageView question3_image_1;
    ImageView question3_image_2;
    ImageView question3_image_3;
    ImageView question3_image_4;
    ImageView question4_image;

    RadioGroup question_1_radio_group;
    RadioGroup question_2_radio_group;

    CheckBox question_3_1;
    CheckBox question_3_2;
    CheckBox question_3_3;
    CheckBox question_3_4;

    EditText question_4_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_all);

        correctScore = 0;
        wrongScore = 0;
        textAnswer = "";
        playerName = getIntent().getStringExtra(PLAYER_NAME);

        this.initQuestionArray();

        this.initRadioButtonQuestion1(R.id.question_1, 0);
        this.initRadioButtonQuestion2(R.id.question_2, 1);
        this.initCheckboxQuestion(R.id.question_3);
        this.initEditTextQuestion(R.id.question_4, 6);
    }

    private void initRadioButtonQuestion1(int questionId, int index) {
        View question = findViewById(questionId);
        question1_image = question.findViewById(R.id.image_radio_question);
        question1_image.setImageResource(berryObjects.get(index).getImageId());
        question_1_radio_group = question.findViewById(R.id.radio_group);
    }

    private void initRadioButtonQuestion2(int questionId, int index) {
        View question = findViewById(questionId);
        question2_image = question.findViewById(R.id.image_radio_question);
        question2_image.setImageResource(berryObjects.get(index).getImageId());
        question_2_radio_group = question.findViewById(R.id.radio_group);
    }

    private void initCheckboxQuestion(int questionId) {
        View question = findViewById(questionId);
        question3_image_1 = question.findViewById(R.id.checkbox_option_1).findViewById(R.id.image_checkbox);
        question3_image_2 = question.findViewById(R.id.checkbox_option_2).findViewById(R.id.image_checkbox);
        question3_image_3 = question.findViewById(R.id.checkbox_option_3).findViewById(R.id.image_checkbox);
        question3_image_4 = question.findViewById(R.id.checkbox_option_4).findViewById(R.id.image_checkbox);
        question3_image_1.setImageResource(berryObjects.get(2).getImageId());
        question3_image_2.setImageResource(berryObjects.get(3).getImageId());
        question3_image_3.setImageResource(berryObjects.get(4).getImageId());
        question3_image_4.setImageResource(berryObjects.get(5).getImageId());
        question_3_1 = question.findViewById(R.id.checkbox_option_1).findViewById(R.id.checkbox);
        question_3_2 = question.findViewById(R.id.checkbox_option_2).findViewById(R.id.checkbox);
        question_3_3 = question.findViewById(R.id.checkbox_option_3).findViewById(R.id.checkbox);
        question_3_4 = question.findViewById(R.id.checkbox_option_4).findViewById(R.id.checkbox);
    }

    private void initEditTextQuestion(int questionId, int index){
        View question = findViewById(questionId);
        question4_image = question.findViewById(R.id.image_edit_text_question);
        question4_image.setImageResource(berryObjects.get(index).getImageId());
        question_4_answer = question.findViewById(R.id.answer_edit_text_question);
    }

    public void checkAnswers(View view) {
        // Check if all berryObjects are answered
        if (!isEmptyAnswers()) {

            if (((question_1_radio_group.getCheckedRadioButtonId() == R.id.radio_yes)
                    && berryObjects.get(0).isEdible())
                    || ((question_1_radio_group.getCheckedRadioButtonId() == R.id.radio_no)
                    && !berryObjects.get(0).isEdible())) {
                correctScore++;
            } else wrongScore++;
            if (((question_2_radio_group.getCheckedRadioButtonId() == R.id.radio_yes)
                    && berryObjects.get(1).isEdible())
                    || ((question_2_radio_group.getCheckedRadioButtonId() == R.id.radio_no)
                    && !berryObjects.get(1).isEdible())) {
                correctScore++;
            } else wrongScore++;

            checkCheckBoxQuestion(question_3_1, 2);
            checkCheckBoxQuestion(question_3_2, 3);
            checkCheckBoxQuestion(question_3_3, 4);
            checkCheckBoxQuestion(question_3_4, 5);

            checkEditTextQuestion(question_4_answer);

            // Display result
            this.displayResults();
        } else {
            Toast.makeText(this, "Please, answer all berryObjects", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkCheckBoxQuestion(CheckBox option, int optionNumber) {
        if (option.isChecked() && berryObjects.get(optionNumber).isEdible()) {
            correctScore++;
        } else if (option.isChecked() && !berryObjects.get(optionNumber).isEdible()) {
            wrongScore++;
        }
    }

    private void checkEditTextQuestion(EditText question) {
        String berryName = getString(berryObjects.get(6).getNameId());
        if (question.getText().toString().equalsIgnoreCase(berryName)) {
            correctScore++;
        } else {
            textAnswer = String.format("The last berry is called %s", berryName);
        }
    }

    private void displayResults() {
        Toast.makeText(this, this.createSummaryMessage(), Toast.LENGTH_LONG).show();
        findViewById(R.id.button_submit).setVisibility(View.GONE);
        findViewById(R.id.button_restart).setVisibility(View.VISIBLE);
    }

    private String createSummaryMessage() {
        String resultMessage = String.format("%s,", playerName);
        resultMessage += String.format(getString(R.string.correct_answers_summary), correctScore);
        if (wrongScore != 0) {
            resultMessage += String.format(getString(R.string.wrong_answers_summary), wrongScore);
        }
        resultMessage += String.format("\n%s", textAnswer);
        return resultMessage;
    }

    private Boolean isEmptyAnswers() {
        return question_1_radio_group.getCheckedRadioButtonId() == -1 ||
                question_2_radio_group.getCheckedRadioButtonId() == -1 ||
                question_4_answer.getText().toString().toLowerCase().isEmpty();
    }

    public void resetQuiz(View view) {
        // edibleScore = 0;
        // inedibleScore = 0;
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }

    private void initQuestionArray() {
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
