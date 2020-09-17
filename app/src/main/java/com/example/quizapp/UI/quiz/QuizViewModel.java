package com.example.quizapp.UI.quiz;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.SingleLIveEvent;
import com.example.quizapp.QuizApp;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {


    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> finish = new MutableLiveData<>();
    MutableLiveData<Long> timeDown = new MutableLiveData<>();
    SingleLIveEvent<Boolean> loading = new SingleLIveEvent<>();
    SingleLIveEvent<Void> finishEvent = new SingleLIveEvent<>();
    SingleLIveEvent<Integer> openResultEvent = new SingleLIveEvent<>();

    private String mCategoryString;
    private String mDifficultyString;
    private int id;
    private CountDownTimer countDownTimer;
    private List<Questions> mQuestions;
    private List<String> mAnswer;
    private Integer count;
    private Boolean isClicked = true;

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    void init(int amountCount, int category, String difficulty) {
        loading.setValue(true);
        quizApiClient.getQuestions(amountCount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Questions> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                loading.setValue(false);
                try {
                    if (mQuestions.get(1).getCategory().equals(mQuestions.get(2).getCategory())) {
                        mCategoryString = mQuestions.get(1).getCategory();
                    } else {
                        mCategoryString = "Mixed";
                    }
                    if (difficulty != null) {
                        mDifficultyString = mQuestions.get(0).getDifficulty().toString();
                    } else {
                        mDifficultyString = "All";
                    }
                } catch (IndexOutOfBoundsException e) {
                    loading.setValue(false);
                    finishEvent.call();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void finishQuiz() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d.MMM.yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        QuizResult result = new QuizResult(
                id,
                mCategoryString,
                mDifficultyString,
                getCorrectAnswersAmount(),
                mQuestions,
                date
        );
        int resultId = QuizApp.historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }


    void onBackpessed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition != 0) {
                currentQuestionPosition.setValue(--count);
            } else {
                finishEvent.call();
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
                countDownTimer.cancel();
            } else {
                isClicked = true;
                currentQuestionPosition.setValue(++count);
            }
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionPosition.getValue(), -1);
        } else {
            finishQuiz();
        }

    }

    private int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i < mQuestions.size() - 1; i++) {
            if (mQuestions.get(i).getSelectAnswerPosition() != null) {
                String correctAnswer = mQuestions.get(i).getCorrectAnswers();
                String selectedAnswer = mQuestions.get(i).getAnswers()
                        .get(mQuestions.get(i).getSelectAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }

        return correctAnswersAmount;
    }


    void startTimeDown() {
        if (countDownTimer != null) countDownTimer.cancel();
        long i = 30000;
        countDownTimer = new CountDownTimer(i, 1000) {

            public void onTick(long millisUntilFinished) {
                timeDown.setValue(millisUntilFinished / 1000);
            }

            public void onFinish() {
                onSkipClick();
            }
        }.start();
    }

    void delayTime(RecyclerView recyclerView, Integer i) {
        if (isClicked) {
            new CountDownTimer(300, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    recyclerView.scrollToPosition(i);
                    cancel();
                }
            }.start();
        } else {
            recyclerView.scrollToPosition(i);
        }
    }
}
