package com.example.quizapp.data.remote;

import com.example.quizapp.models.QuizResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit  = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
        Call<QuizResponse> call = client.getQuestions(10,
                null, null);
        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() !=null){
                        callback.onSuccess(response.body().getResults());
                    }else {
                        callback.onFailure(new Exception ("Response is empty" + response.code()));
                    }
                }else {
                        callback.onFailure(new Exception ("Response is empty" + response.code()));
                    }
                }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {

            }
        });
    }

    interface QuizApi{

        @GET("api.php")
        Call<QuizResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty

        );
    }
}
