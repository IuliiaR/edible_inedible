package com.example.android.edibleorinedible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PLAYER_NAME = "player name";
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view) {
        EditText text = findViewById(R.id.player_name);
        playerName = text.getText().toString();
        if (playerName.isEmpty()) {
            Toast.makeText(this, "Please, enter your name", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(MainActivity.this, QuizActivity.class);
            i.putExtra(PLAYER_NAME, playerName);
            startActivity(i);
        }
    }
}
