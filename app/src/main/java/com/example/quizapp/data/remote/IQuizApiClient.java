package com.example.quizapp.data.remote;

import com.example.quizapp.models.Questions;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions (QuestionsCallback callback);

   interface QuestionsCallback{
       void onSuccess(List<Questions> questions);
       void onFailure(Exception e);
   }
}
