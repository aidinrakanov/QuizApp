package com.example.quizapp.data;

import androidx.lifecycle.LiveData;
import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.db.QuizDao;
import com.example.quizapp.models.History;
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
    public void getQuestions(int amountCount, int category, String difficulty, QuestionsCallback callback) {
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

    @Override
    public QuizResult getQuizResult(int id) {
        return historyStorage.getQuizResult(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return historyStorage.getAllHistory();
    }

    @Override
    public void delete(QuizResult quizResult) {
        historyStorage.delete(quizResult);
    }

    @Override
    public void deleteById(int id) {
        historyStorage.deleteById(id);
    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }
}
