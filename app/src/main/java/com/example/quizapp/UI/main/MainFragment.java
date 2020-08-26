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
    String category, difficulty;
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
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        spinner();
        sliderInit();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                QuizActivity.start(getContext(),amountCount, category, difficulty);
            }
        });
    }

    private void sliderInit() {
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                amountCount = (int) slider.getValue();
                amount.setText(String.valueOf(amountCount));
            }
        });
    }

    private void spinner() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getSelectedItem().toString();
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