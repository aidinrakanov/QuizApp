package com.example.quizapp.UI.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.core.SingleLIveEvent;
import com.example.quizapp.QuizApp;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;

import java.util.List;

public class QuizViewModel extends ViewModel {


    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> finish = new MutableLiveData<>();
    SingleLIveEvent<Boolean> loading = new SingleLIveEvent<>();
    SingleLIveEvent<Integer> startResult = new SingleLIveEvent<>();




    private List<Questions> mQuestions;
    private List<String> mAnswer;
    private Integer count;

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    void init(int amountCount, String category, String difficulty) {
        loading.setValue(true);
        quizApiClient.getQuestions(amountCount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Questions> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                loading.setValue(false);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void finishQuiz() {
        finish.setValue(true);
    }


    void onBackpessed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition != 0) {
                currentQuestionPosition.setValue(--count);
            } else {
                finishQuiz();
            }

        }
    }

    public void onAnswerClick(int position, int selectAnswerPosition) {
        if (mQuestions.size() > position && position >= 0) {
            if (mQuestions.get(position).getSelectAnswerPosition() == null) {
                mQuestions.get(position).setSelectAnswerPosition(selectAnswerPosition);
                questions.setValue(mQuestions);
            }

            if (position + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }


    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionPosition.getValue(), -1);
        }

    }
//    public void getAnswers(int position) {
//        mAnswer = mQuestions.get(position).getIncorrectAnswers();
//        mAnswer.add(mQuestions.get(position).getCorrectAnswers());
//        Collections.shuffle(mAnswer);
//        mQuestions.get(position).setAnswers(mAnswer);
//    }
}
