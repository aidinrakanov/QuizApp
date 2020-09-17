package com.example.quizapp.UI.settings;

import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;

public class SettingsViewModel extends ViewModel {

    public void clearAllHistory() {
        QuizApp.historyStorage.deleteAll();
    }

}
