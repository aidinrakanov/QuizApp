package com.example.quizapp.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.db.QuizDao;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    private QuizDao quizDao;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage, QuizDao quizDao) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
        this.quizDao = quizDao;
    }

    private Questions shuffleAnswer(Questions questions) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(questions.getCorrectAnswers());
        answers.addAll(questions.getIncorrectAnswers());
        Collections.shuffle(answers);
        questions.setAnswers(answers);
        return questions;
    }

    @Override
    public QuizResult getQuestionResult(int id) {
        return null;
    }

    @Override
    public int saveQuestion(QuizResult questionResult) {
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void getQuestions(int amountCount, String category, String difficulty, QuestionsCallback callback) {
        quizApiClient.getQuestions(amountCount, category, difficulty, new QuestionsCallback() {
            @Override
            public void onSuccess(List<Questions> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, shuffleAnswer(result.get(i)));
                }
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }
}
