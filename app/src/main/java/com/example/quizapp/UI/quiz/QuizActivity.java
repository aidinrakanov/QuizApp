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
import android.renderscript.Sampler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.UI.result.ResultActivity;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.Listener {

    public static final String EXTRA_AMOUNT = "slider";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    RecyclerView recyclerView;

    String category, difficulty;
    int amountCount;
    private QuizViewModel viewModel;
    TextView category_text, quiz_amount;
    QuizAdapter adapter;
    ProgressBar progressBar;
    Button skip, finish_btn;
    ImageView onBack;
    LottieAnimationView lottie;
    List<Questions> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        getData();
        load();
        recyclerSets();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onSkipClick();
            }
        });
    }




    @SuppressLint("ClickableViewAccessibility")
    private void recyclerSets() {
        adapter = new QuizAdapter(list, this);
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
        skip = findViewById(R.id.quiz_skip);
        onBack = findViewById(R.id.quiz_back);
        lottie = findViewById(R.id.lottie_load);
        finish_btn = findViewById(R.id.quiz_finish);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(QuizActivity.this, ResultActivity.class));
                finish();

            }
        });

    }

    private void getData() {
        if (getIntent() != null) {
            amountCount = getIntent().getIntExtra(EXTRA_AMOUNT, 1);
            category = getIntent().getStringExtra(EXTRA_CATEGORY);
            difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
            if (amountCount == 0) {
                amountCount = 5;
            }
            if (category.equals("Any Category")) {
                category = null;
            }

            if (difficulty.equals("Any Difficulty")) {
                difficulty = null;
            }
            getQuestions();
        }
    }

    private void getPosition() {
        viewModel.currentQuestionPosition.observe(this, integer -> {
            if (integer != null) {
                quiz_amount.setText((integer + 1) + "/" + amountCount);
                recyclerView.smoothScrollToPosition(integer);
                progressBar.setProgress(integer + 1);
                progressBar.setMax(amountCount);
                category_text.setText(String.valueOf(category));
                if (category_text.getText().equals("null")){
                    category_text.setText("Any Category"); }
                if (integer + 1 == list.size()) {
                    skip.setVisibility(View.GONE);
                    finish_btn.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public static void start(Context context, int amountCount, String category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amountCount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        context.startActivity(intent);
    }

    private void getQuestions() {
        viewModel.init(amountCount, category, difficulty);
        viewModel.questions.observe(this, questions -> {
            list = questions;
            adapter.setQuestions(questions);
            getPosition();
        });
    }


    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        viewModel.onAnswerClick(position, selectAnswerPosition);
//        viewModel.getAnswers(position);
    }

    public void onBackPressed2(View view) {
        viewModel.onBackpessed();
    }

    public void load(){
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    lottie.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    quiz_amount.setVisibility(View.GONE);

                }else {
                    lottie.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    quiz_amount.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}