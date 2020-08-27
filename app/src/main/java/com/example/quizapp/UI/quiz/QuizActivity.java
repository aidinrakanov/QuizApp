package com.example.quizapp.UI.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResponse;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.Listener {

    public static final String EXTRA_AMOUNT = "slider";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    RecyclerView recyclerView;

    String category, difficulty;
    int amountCount;
    private QuizViewModel viewModel;
    TextView category_text , quiz_amount;
    QuizAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initViews();
        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        recyclerSets();
        getData();
        getQuestions();
        getPosition();
        viewModel.init(amountCount , category , difficulty);
        viewModel.questions.observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                adapter.setQuestions(questions);
            }
        });
    }

    private void recyclerSets() {
        adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, RecyclerView.HORIZONTAL, false));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }


    private void initViews() {
        recyclerView = findViewById(R.id.quiz_recycler);
        category_text = findViewById(R.id.quiz_category_text);
        progressBar = findViewById(R.id.progress_bar);
        quiz_amount = findViewById(R.id.quiz_amount);


    }

    private void getPosition() {
        viewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer integer) {
                recyclerView.smoothScrollToPosition(integer);
                progressBar.setProgress(integer + 1 );
                progressBar.setMax(amountCount);
                quiz_amount.setText((integer + 1) + "/" + amountCount);
                category_text.setText(category);
            }
        });

    }

    public static void start(Context context, int amountCount, String category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, category);
        intent.putExtra(EXTRA_CATEGORY, difficulty);
        intent.putExtra(EXTRA_DIFFICULTY, amountCount);
        context.startActivity(intent);
    }

    private void getData() {
        if (getIntent() != null) {
            amountCount = getIntent().getIntExtra(EXTRA_AMOUNT, 1);
            category = getIntent().getStringExtra(EXTRA_CATEGORY);
            difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

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


    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        viewModel.onAnswerClick(position,selectAnswerPosition);
    }
}