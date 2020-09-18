package com.example.quizapp.UI.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.quizapp.MainActivity;
import com.example.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    ResultViewModel resultViewModel;

    private static String EXTRA_QUIZ_ID = "result_id";
    TextView categoryR,
            difficultyR, correctAnswersR, resultR;
    private ImageView imageR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViewsResult();
        Integer id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        resultViewModel.getResult(id);
        setViewContent();
    }

    private void initViewsResult() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        categoryR = findViewById(R.id.result_text_mixed);
        difficultyR = findViewById(R.id.result_difficulty);
        correctAnswersR = findViewById(R.id.result_correct_answers);
        resultR = findViewById(R.id.result_result);
        imageR = findViewById(R.id.result_image);
    }

    @SuppressLint("SetTextI18n")
    private void setViewContent() {
        resultViewModel.quizResult.observe(this, quizResult -> {
            categoryR.setText("Category: " + quizResult.getCategory());
            difficultyR.setText(quizResult.getDifficulty());
            correctAnswersR.setText(quizResult.getCorrectAnswerResult() + "/" + quizResult.getQuestions().size());
            int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswerResult()
                    / quizResult.getQuestions().size() * 100);
            resultR.setText(correctAnswersPercent + " %");
            if (correctAnswersPercent == 0) {
                imageR.setImageResource(R.drawable.looser);
            }
            if (correctAnswersPercent > 0 && correctAnswersPercent <= 30) {
                imageR.setImageResource(R.drawable.bad);
            }
            if (correctAnswersPercent > 30 && correctAnswersPercent <= 50) {
                imageR.setImageResource(R.drawable.not_bad);
            }
            if (correctAnswersPercent > 50 && correctAnswersPercent <= 89) {
                imageR.setImageResource(R.drawable.cool);
            }
            if (correctAnswersPercent > 89 && correctAnswersPercent <= 100) {
                imageR.setImageResource(R.drawable.best_quality);
            }
        });
    }

    public static void start(Context context, Integer id) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, id));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void btnFinish(View view) {
//        startActivity(new Intent(ResultActivity.this, MainActivity.class));
        finish();
    }
}