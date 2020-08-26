package com.example.quizapp.UI.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {


    List<QuizResponse> models = new ArrayList<>();

    public QuizAdapter(List<QuizResponse> models) {
        this.models = models;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.quiz_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount, main;
        Button first, second, third, fourth, b_true, b_false;
        LinearLayout multi_linear, boolean_linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.quiz_amount);

            main = itemView.findViewById(R.id.quiz_main_text);
            first = itemView.findViewById(R.id.quiz_first_answer);
            second = itemView.findViewById(R.id.quiz_second_answer);
            third = itemView.findViewById(R.id.quiz_third_answer);
            fourth = itemView.findViewById(R.id.quiz_fourth_answer);
            b_true = itemView.findViewById(R.id.quiz_true);
            b_false = itemView.findViewById(R.id.quiz_false);
            multi_linear = itemView.findViewById(R.id.quiz_multi_choice);
            boolean_linear = itemView.findViewById(R.id.quiz_true_or_false);

        }

        public void bind(QuizResponse questions) {
            main.setText(questions.getResults().get(0).getQuestion());
            amount.setText(getAdapterPosition());
        }
    }
}
