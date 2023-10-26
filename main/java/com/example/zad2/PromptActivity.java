package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

    private boolean correctAnswer;

    private TextView answerTextView;

    private Button showAnswerCorrectButton;
    private Button nextButtonprompt;

    public static final String KEY_EXTRA_ANSWER_SHOWN = "pb.edu.pl.wi.quiz.answerShown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerCorrectButton = findViewById(R.id.show_answer_button);
        nextButtonprompt = findViewById(R.id.next_button_prompt);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, false);

        showAnswerCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });

        nextButtonprompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("goToNextQuestion", true);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
    private void setAnswerShownResult(boolean answerWasShown) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }





}
