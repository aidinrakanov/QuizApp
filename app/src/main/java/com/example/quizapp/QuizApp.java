package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.data.remote.QuizApiClient;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        quizApiClient = new QuizApiClient();

    }
}
