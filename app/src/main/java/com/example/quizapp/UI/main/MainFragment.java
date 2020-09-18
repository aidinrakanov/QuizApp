package com.example.quizapp.UI.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quizapp.UI.quiz.QuizActivity;
import com.example.quizapp.R;
import com.google.android.material.slider.Slider;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Slider slider;
    private Spinner spinnerCategory, spinnerDifficulty;
    private Button start;
    private TextView amount;
    String difficulty;
    int category;
    int amountCount;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.message.observe(getViewLifecycleOwner(), s -> {
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        spinner();
        sliderInit();
        start.setOnClickListener(view1 -> QuizActivity.start(getContext(), amountCount, category, difficulty));
    }

    private void sliderInit() {
        slider.setValue(5);
        slider.addOnChangeListener((slider, value, fromUser) -> {
            amountCount = (int) slider.getValue();
            amount.setText(String.valueOf(amountCount));
        });
    }

    private void spinner() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getSelectedItemPosition() + 8;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                difficulty = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void initViews(View view) {
        slider = view.findViewById(R.id.slider);
        amount = view.findViewById(R.id.text_amount);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerDifficulty = view.findViewById(R.id.spinner_difficulty);
        start = view.findViewById(R.id.button_start);
    }

}