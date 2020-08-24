package com.example.quizapp.UI.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    String category, difficulty;
    int amountCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);
        getData();
        getQuestions();
    }

    private void getData() {
        if (getIntent() != null) {
            amountCount = getIntent().getIntExtra("slider", 1);
            category = getIntent().getStringExtra("category");
            difficulty = getIntent().getStringExtra("difficulty");

            if (category.equals("Any Category")) {
                category = null;
            }
        }
    }

    private void getQuestions() {
        QuizApp.quizApiClient.getQuestions(amountCount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Questions> questions) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}