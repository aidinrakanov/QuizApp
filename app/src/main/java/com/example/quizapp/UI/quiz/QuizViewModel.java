package com.example.quizapp.UI.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;

import java.util.Collections;
import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean>finish = new MutableLiveData<>();
    private List<Questions> mQuestions;
    private List<String> mAnswer;

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
        finish.setValue(true);
    }

    public void getAnswers(int position){
        mAnswer = mQuestions.get(position).getIncorrectAnswers();
        mAnswer.add(mQuestions.get(position).getCorrectAnswers());
        Collections.shuffle(mAnswer);
        mQuestions.get(position).setAnswers(mAnswer);
    }

    void onSkipClick(){
        currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
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
