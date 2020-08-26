package com.example.quizapp.data.remote;

import com.example.quizapp.core.IBaseCallback;
import com.example.quizapp.models.Questions;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions (
            int amountCount,
            String category,
            String difficulty,
            QuestionsCallback callback);

   interface QuestionsCallback extends IBaseCallback<List<Questions>> {
       @Override
       void onSuccess(List<Questions> result);

       @Override
       void onFailure(Exception e);
   }
}
