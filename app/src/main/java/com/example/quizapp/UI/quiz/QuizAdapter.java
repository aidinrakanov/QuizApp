package com.example.quizapp.UI.quiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<Questions> models;
    private Listener listener;


    public QuizAdapter(List<Questions> models,Listener listener) {
        this.listener = listener;
        this.models = models;
    }


    @NonNull
    @Override
    public Questions_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.quiz_list, parent, false);
        return new Questions_ViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Questions_ViewHolder){
            ((Questions_ViewHolder)holder).onBind(models.get(position));

        }
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setQuestions(List<Questions> questions){
        models.clear();
        models.addAll(questions);
        notifyDataSetChanged();
    }


    public class Questions_ViewHolder extends RecyclerView.ViewHolder {
        TextView main;
        Button first, second, third, fourth, b_true, b_false;
        LinearLayout multi_linear, boolean_linear;

        private Listener listener;

        public Questions_ViewHolder(@NonNull View itemView,Listener listener) {
            super(itemView);
            this.listener = listener;
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

        public void onBind(Questions questions) {
            main.setText(questions.getQuestion());
            if (questions.getType().equals("multiple")) {
                multi_linear.setVisibility(View.VISIBLE);
                boolean_linear.setVisibility(View.GONE);
                first.setText(questions.getAnswers().get(0));
                second.setText(questions.getAnswers().get(1));
                third.setText(questions.getAnswers().get(2));
                fourth.setText(questions.getAnswers().get(3));
            } else if (questions.getType().equals("boolean")) {
                boolean_linear.setVisibility(View.VISIBLE);
                multi_linear.setVisibility(View.GONE);
                b_true.setText(questions.getAnswers().get(0));
                b_false.setText(questions.getAnswers().get(1));
            }
            Log.d("ololo", questions.getAnswers().toString());
        }

    }

    public interface Listener{
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
