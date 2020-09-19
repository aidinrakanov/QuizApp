package com.example.quizapp.UI.quiz;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.Questions_ViewHolder> {


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
        View view = LayoutInflater.from
                (parent.getContext()).inflate
                (R.layout.quiz_list, parent, false);
        return new Questions_ViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull Questions_ViewHolder holder, int position) {
        holder.onBind(models.get(position));
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
        private Listener listener;


        public Questions_ViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            main = itemView.findViewById(R.id.quiz_main_text);
            first = itemView.findViewById(R.id.quiz_first_answer);
            second = itemView.findViewById(R.id.quiz_second_answer);
            third = itemView.findViewById(R.id.quiz_third_answer);
            fourth = itemView.findViewById(R.id.quiz_fourth_answer);
            b_true = itemView.findViewById(R.id.quiz_true);
            b_false = itemView.findViewById(R.id.quiz_false);
            multi_linear = itemView.findViewById(R.id.quiz_multi_choice);
            boolean_linear = itemView.findViewById(R.id.quiz_true_or_false);
            this.listener = listener;
            clickListener();

        }

        private void setButton(Boolean enabled) {
            first.setEnabled(enabled);
            second.setEnabled(enabled);
            third.setEnabled(enabled);
            fourth.setEnabled(enabled);
            b_true.setEnabled(enabled);
            b_false.setEnabled(enabled);
        }

        private void clickListener() {
            first.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
            second.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
            third.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
            fourth.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
            b_true.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
            b_false.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        }

        public void onBind(Questions questions) {
            reset();
            if (questions.getSelectAnswerPosition() == null) {
                setButton(true);
            } else {
                setButton(false);
            }
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
            if (questions.getSelectAnswerPosition() != null) {
                btn_state(questions);
            }
        }

        private void reset() {
            resetAnswerButtons(first, second, third, fourth, b_true, b_false);
        }

        private void resetAnswerButtons(Button... buttons) {
            for (Button button : buttons) {
                button.setBackgroundResource(R.drawable.button_rounded_quiz);
                button.setTextColor(itemView.getResources().getColor(R.color.Black));
            }
        }


        @SuppressLint("ResourceAsColor")
        public void btn_state(Questions questions) {
            if (questions.getSelectAnswerPosition() != null) {
                switch (questions.getSelectAnswerPosition()) {
                    case 0:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(0))) {
                            first.setBackgroundResource(R.drawable.quiz_green);
                            b_true.setBackgroundResource(R.drawable.quiz_green);
                            first.setTextColor(R.color.White);
                            b_true.setTextColor(R.color.White);
                        } else {
                            first.setBackgroundResource(R.drawable.quiz_red);
                            b_true.setBackgroundResource(R.drawable.quiz_red);
                            first.setTextColor(Color.WHITE);
                            b_true.setTextColor(Color.WHITE);
                        }
                        break;
                    case 1:
                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(1))) {
                            second.setBackgroundResource(R.drawable.quiz_green);
                            b_false.setBackgroundResource(R.drawable.quiz_green);
                            second.setTextColor(R.color.White);
                            b_false.setTextColor(R.color.White);
                        } else {
                            second.setBackgroundResource(R.drawable.quiz_red);
                            b_false.setBackgroundResource(R.drawable.quiz_red);
                            second.setTextColor(Color.WHITE);
                            b_false.setTextColor(Color.WHITE);
                        }
                        break;
                    case 2:
                        if (questions.getAnswers().get(2).equals(questions.getCorrectAnswers())) {
                            third.setBackgroundResource(R.drawable.quiz_green);
                            third.setTextColor(R.color.White);

                        } else {
                            third.setBackgroundResource(R.drawable.quiz_red);
                            third.setTextColor(Color.WHITE);
                        }
                        break;
                    case 3:
                        if (questions.getAnswers().get(3).equals(questions.getCorrectAnswers())) {
                            fourth.setBackgroundResource(R.drawable.quiz_green);
                            fourth.setTextColor(R.color.White);
                        } else {
                            fourth.setBackgroundResource(R.drawable.quiz_red);
                            fourth.setTextColor(Color.WHITE);
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
