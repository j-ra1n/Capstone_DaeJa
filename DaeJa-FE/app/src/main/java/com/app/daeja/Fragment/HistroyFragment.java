package com.app.daeja.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.History;
import com.app.daeja.Adapter.HistoryAdapter;
import com.app.daeja.R;

import java.util.ArrayList;
import java.util.List;

public class HistroyFragment extends Fragment {
    private View view;
    private Context ct;
    protected RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    public static List<History> histories = new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_histroy, container, false);
        ct = container.getContext();

        recyclerViewHistory();

        return view;
    }

    private void recyclerViewHistory() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ct, LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.view);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<History> historyArrayList = new ArrayList<>();

        for(History history : histories) {
            historyArrayList.add(history);
        }

        adapter = new HistoryAdapter(historyArrayList);
        recyclerView.setAdapter(adapter);
    }

}
