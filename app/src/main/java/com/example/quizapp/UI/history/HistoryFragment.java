package com.example.quizapp.UI.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.models.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements HistoryAdapter.Listener {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private int position;
    private List<History> historyList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        mViewModel.history.observe(getActivity(), histories -> {
            historyList = histories;
            if (histories != null) {
                adapter.updateHistory(historyList);
            }
        });
        mViewModel.deleteById.observe(getActivity(), aVoid ->
                QuizApp.historyStorage.deleteById(historyList.get(position).getId()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new HistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.updateHistory(new ArrayList<>());
    }

    private void showPopupMenu(View view, int position) {
        this.position = position;
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.menu_popup);

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.popup_delete) {
                mViewModel.deleteById.call();
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
        popupMenu.show();
    }


    @Override
    public void onClick(View view, int id) {
        showPopupMenu(view, position);
    }
}