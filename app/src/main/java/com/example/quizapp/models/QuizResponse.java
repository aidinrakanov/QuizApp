package com.example.quizapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {

    @SerializedName("response_code")
    private int response_code;
    private List<Questions> results;

    public int getResponse_code() { return response_code; }

    public void setResponse_code(int response_code) { this.response_code = response_code; }

    public List<Questions> getResults() { return results; }

    public void setResults(List<Questions> results) { this.results = results; }
}
