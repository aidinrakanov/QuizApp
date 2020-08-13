package com.example.quizapp.UI.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quizapp.MainViewModel;
import com.example.quizapp.R;
import com.google.android.material.slider.Slider;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Slider slider;
    private Spinner spinnerCategory,spinnerDifficulty;
    private Button start;

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
        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slider = view.findViewById(R.id.slider);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerDifficulty = view.findViewById(R.id.spinner_difficulty);
        start = view.findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}