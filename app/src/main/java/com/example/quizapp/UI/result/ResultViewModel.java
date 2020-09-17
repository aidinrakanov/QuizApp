package com.example.quizapp.UI.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.models.QuizResult;


public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResult.setValue(QuizApp.quizDatabase.quizDao().get(id));
    }
}
