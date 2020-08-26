package com.example.quizapp.UI.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.models.Questions;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();


    void init(int amountCount, String category, String difficulty){
    }
    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        int count = 0;
    }
}
