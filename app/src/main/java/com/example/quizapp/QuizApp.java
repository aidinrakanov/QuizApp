package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.UI.history.HistoryStorage;
import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.db.QuizDatabase;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository repository;
    public static QuizDatabase quizDatabase;



    @Override
    public void onCreate() {
        super.onCreate();


        quizDatabase = Room.databaseBuilder(
                this, QuizDatabase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        repository = new  QuizRepository(new QuizApiClient(), new HistoryStorage(), quizDatabase.quizDao());
        quizApiClient = repository;
        historyStorage = repository;

        quizDatabase.quizDao();


    }

}
