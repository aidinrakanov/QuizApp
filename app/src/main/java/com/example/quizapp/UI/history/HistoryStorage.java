package com.example.quizapp.UI.history;

import androidx.lifecycle.LiveData;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.models.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {



    @Override
    public QuizResult getQuestionResult(int id) { return null; }

    @Override
    public int saveQuestion(QuizResult questionResult) { return 0; }

    @Override
    public LiveData<List<QuizResult>> getAll() { return null; }

    @Override
    public void delete(int id) { }

    @Override
    public void deleteAll() { }
}
