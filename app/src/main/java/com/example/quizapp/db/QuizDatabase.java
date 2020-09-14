package com.example.quizapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.models.QuizResult;


@Database(entities = {QuizResult.class}, version = 1)
 public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
