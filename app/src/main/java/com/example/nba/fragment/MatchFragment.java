package com.example.nba.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nba.R;
import com.example.nba.adapter.MatchAdapter;
import com.example.nba.model.match.EventsItem;
import com.example.nba.service.NBAListener;
import com.example.nba.service.NBAService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatchFragment extends Fragment {

    public MatchFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false);
    }
    public RecyclerView rvMatch;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMatch = view.findViewById(R.id.rv_match);
        rvMatch.setHasFixedSize(true);

        new NBAService().getMatch(matchListener);
    }

    NBAListener<List<EventsItem>> matchListener = new NBAListener<List<EventsItem>>(){

        @Override
        public void onSuccess(List<EventsItem> item) {
            Log.d("Hasil : ", item.toString());
            rvMatch.setLayoutManager(new LinearLayoutManager(getContext()));
            MatchAdapter matchAdapter = new MatchAdapter(item);
            rvMatch.setAdapter(matchAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };
}