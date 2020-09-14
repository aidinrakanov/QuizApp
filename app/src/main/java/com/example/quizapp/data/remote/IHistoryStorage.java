package com.example.quizapp.data.remote;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuestionResult(int id);

    int saveQuestion(QuizResult questionResult);

    LiveData <List<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();



}
