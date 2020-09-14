package com.example.quizapp.UI.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizapp.MainActivity;
import com.example.quizapp.R;
import com.example.quizapp.UI.quiz.QuizActivity;

public class ResultActivity extends AppCompatActivity {

    TextView categoryR,
    difficultyR, correctAnswersR, resultR;
    ImageView imageR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViewsResult();
    }

    private void initViewsResult() {
        categoryR = findViewById(R.id.result_text_mixed);
        difficultyR = findViewById(R.id.result_difficulty);
        correctAnswersR = findViewById(R.id.result_correct_answers);
        resultR = findViewById(R.id.result_result);
        imageR = findViewById(R.id.result_image);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}