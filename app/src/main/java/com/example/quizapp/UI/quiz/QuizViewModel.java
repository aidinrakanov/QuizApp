package com.example.quizapp.UI.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    private List<Questions> mQuestions;

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;


    void init(int amountCount, String category, String difficulty) {
        quizApiClient.getQuestions(amountCount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Questions> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void finishQuiz() {
    }

    void onSkipClick(){
    }

    void onBackpessed(){
    }

    public void onAnswerClick(int position, int selectAnswerPosition) {
        if (mQuestions.size() > position && position >= 0) {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            mQuestions.get(position).setSelectAnswerPosition(selectAnswerPosition);
            questions.setValue(mQuestions);

            if (position == mQuestions.size() - 1) {
                    finishQuiz();
            } else {
                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            }
        }

    }
}
