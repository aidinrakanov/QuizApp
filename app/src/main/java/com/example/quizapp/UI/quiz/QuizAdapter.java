package com.example.quizapp.UI.quiz;

import android.annotation.SuppressLint;
import android.text.Html;
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

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<Questions> models;
    private Listener listener;


    public QuizAdapter(List<Questions> models, Listener listener) {
        this.models = models;
        this.listener = listener;
        notifyDataSetChanged();
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
        if (holder instanceof Questions_ViewHolder) {
            ((Questions_ViewHolder) holder).onBind(models.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setQuestions(List<Questions> questions) {
        models.clear();
        models.addAll(questions);
        notifyDataSetChanged();
    }


    public static class Questions_ViewHolder extends RecyclerView.ViewHolder {
        TextView main;
        Button first, second, third, fourth, b_true, b_false;
        LinearLayout multi_linear, boolean_linear;
        private int position;
        private Listener listener;

        public Questions_ViewHolder(@NonNull View itemView, Listener listener) {
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
            clickListener();

        }


        public void onBind(Questions questions, int position) {
            reset();
            this.position = position;
            main.setText(Html.fromHtml(questions.getQuestion()));
            if (questions.getType().equals("multiple")) {
                multi_linear.setVisibility(View.VISIBLE);
                boolean_linear.setVisibility(View.GONE);
                first.setText(Html.fromHtml(questions.getAnswers().get(0)));
                second.setText(Html.fromHtml(questions.getAnswers().get(1)));
                third.setText(Html.fromHtml(questions.getAnswers().get(2)));
                fourth.setText(Html.fromHtml(questions.getAnswers().get(3)));
            } else {
                boolean_linear.setVisibility(View.VISIBLE);
                multi_linear.setVisibility(View.GONE);
                b_true.setText(Html.fromHtml(questions.getAnswers().get(0)));
                b_false.setText(Html.fromHtml(questions.getAnswers().get(1)));
            }
            btn_state(questions);
        }

        @SuppressLint("ResourceAsColor")
        public void reset() {
            first.setBackgroundResource(R.drawable.button_rounded_quiz);
            second.setBackgroundResource(R.drawable.button_rounded_quiz);
            third.setBackgroundResource(R.drawable.button_rounded_quiz);
            fourth.setBackgroundResource(R.drawable.button_rounded_quiz);
            b_true.setBackgroundResource(R.drawable.button_rounded_quiz);
            b_false.setBackgroundResource(R.drawable.button_rounded_quiz);

            first.setTextColor(itemView.getResources().getColor(R.color.Black));
            second.setTextColor(itemView.getResources().getColor(R.color.Black));
            third.setTextColor(itemView.getResources().getColor(R.color.Black));
            fourth.setTextColor(itemView.getResources().getColor(R.color.Black));
            b_true.setTextColor(itemView.getResources().getColor(R.color.Black));
            b_false.setTextColor(itemView.getResources().getColor(R.color.Black));
        }


        private void clickListener() {
            first.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
            second.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
            third.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
            fourth.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
            b_true.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
            b_false.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        }

        @SuppressLint("ResourceAsColor")
        public void btn_state(Questions questions) {
            if (questions.getSelectAnswerPosition() != null) {
                switch (questions.getSelectAnswerPosition()) {
                    case 0:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(0))) {
                            first.setBackgroundResource(R.color.Green);
                            b_true.setBackgroundResource(R.color.Green);
                            first.setTextColor(R.color.White);
                            b_true.setTextColor(R.color.White);
                        }
                        break;
                    case 1:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(1))) {
                            second.setBackgroundResource(R.color.Green);
                            b_false.setBackgroundResource(R.color.Green);
                            second.setTextColor(R.color.White);
                            b_false.setTextColor(R.color.White);
                        }
                        break;
                    case 2:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(2))) {
                            third.setBackgroundResource(R.color.Green);
                            third.setTextColor(R.color.White);

                        }
                        break;
                    case 3:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(3))) {
                            fourth.setBackgroundResource(R.color.Green);
                            fourth.setTextColor(R.color.White);
                        }
                        break;
                }
            }
        }
    }
    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
