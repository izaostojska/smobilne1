package com.example.zad2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton ;
    private Button promptButton ;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int currentIndex = 0;
    private int score = 0;
    private boolean answerWasShown;

    public static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.exmaple.myapplication.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener,  true),
            new Question(R.string.q_resources,  true),
            new Question(R.string.q_version, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QUIZ_TAG", "Wywołanie onCreate");
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        promptButton = findViewById(R.id.prompt_button);
        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView = findViewById(R.id.score_text_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz");

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                currentIndex = (currentIndex + 1)%questions.length;
                answerWasShown = false;
                setNextQuestion();
            }
        });
        setNextQuestion();

        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intet = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intet.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intet, REQUEST_CODE_PROMPT);
            }
        });
    }
    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId;
        if(answerWasShown) {
            resultMessageId = R.string.answer_was_shown;
        } else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
                score++;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(MainActivity.this, resultMessageId, Toast.LENGTH_SHORT).show();

        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
        answerWasShown = true;
    }


    private void setNextQuestion() {
        int questionId = questions[currentIndex].getQuestionId();
        questionTextView.setText(getString(questionId));
        scoreTextView.setText("Score: " + score);
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
        answerWasShown =false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("QUIZ_TAG", "Wywołanie onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("QUIZ_TAG", "Wywołanie onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("QUIZ_TAG", "Wywołanie onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("QUIZ_TAG", "Wywołanie onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("QUIZ_TAG", "Wywołanie onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("QUIZ_TAG", "Wywołanie onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    // W MainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE_PROMPT) {
            if (data == null)
                return;
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
            boolean goToNextQuestion = data.getBooleanExtra("goToNextQuestion", false);
            if (goToNextQuestion) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        }
    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE_PROMPT) {
            if(data == null)
                return;
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }*/


}