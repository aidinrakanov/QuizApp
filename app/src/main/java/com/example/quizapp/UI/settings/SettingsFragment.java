package com.example.quizapp.UI.settings;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    TextView clearhistory;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clearhistory = view.findViewById(R.id.clear_history);
        clearhistory.setOnClickListener(view1 -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setTitle("Внимание!!!");
            alertBuilder.setMessage("Вы точно хотите очистить историю?");
            alertBuilder.setPositiveButton("Да", (dialog, which) ->
                    QuizApp.quizDatabase.quizDao().deleteAll());
            alertBuilder.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
//

        });
    }
}