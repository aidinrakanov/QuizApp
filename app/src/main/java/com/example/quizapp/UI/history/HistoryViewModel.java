package com.example.quizapp.UI.history;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.SingleLIveEvent;
import com.example.quizapp.QuizApp;
import com.example.quizapp.models.History;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<History>> history = QuizApp.historyStorage.getAllHistory();
    public SingleLIveEvent<Void> deleteById = new SingleLIveEvent<>();

    public void delete() {
        deleteById.call();
    }

}