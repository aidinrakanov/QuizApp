package com.example.quizapp.UI.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Questions;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<QuizResult> list = new ArrayList<>();

    public HistoryAdapter(List<QuizResult> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
            from(parent.getContext()).inflate(R.layout.history_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.historyBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView history_category;
        private TextView history_correctAnswers;
        private TextView history_difficulty;
        private TextView history_time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            history_category = itemView.findViewById(R.id.History_mixed_text);
            history_correctAnswers = itemView.findViewById(R.id.History_count_text);
            history_difficulty = itemView.findViewById(R.id.History_diff_type_text);
            history_time = itemView.findViewById(R.id.History_date);
        }

        public void historyBind(QuizResult quizResult){
            history_category.setText(quizResult.getCategory());
            history_correctAnswers.setText(quizResult.getCorrectAnswerResult());
            history_difficulty.setText(quizResult.getDifficulty());
            history_time.setText(String.valueOf(quizResult.getCreatedAt()));

        }
    }
}
