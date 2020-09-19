package com.example.quizapp.UI.history;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<History> list;
    private Listener listener;

    public HistoryAdapter(Listener listener) {
        this.listener = listener;

    }

    public void updateHistory(List<History> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item_list, parent, false);
        return new ViewHolder(view, listener);
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
        private ImageView dots;
        private Listener listener;


        public ViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            this.listener = listener;
            history_category = itemView.findViewById(R.id.History_mixed_text);
            history_correctAnswers = itemView.findViewById(R.id.History_count_text);
            history_difficulty = itemView.findViewById(R.id.History_diff_type_text);
            history_time = itemView.findViewById(R.id.History_date);
            dots = itemView.findViewById(R.id.History_dots_image);
            dots.setOnClickListener(v -> listener.onClick(v, getAdapterPosition()));
        }

        @SuppressLint("SetTextI18n")
        public void historyBind(History history) {
            history_category.setText(history.getCategory());
            history_correctAnswers.setText(history.getCorrectAnswer() + "/" + history.getAmounts());
            history_difficulty.setText(history.getDifficulty());
            history_time.setText(history.getCreatedAt().toLocaleString());
        }
    }

    public interface Listener {
        void onClick(View view, int id);
    }
}

