package com.example.nba.service;

import com.example.nba.model.detail.DetailTeamResponse;
import com.example.nba.model.detail.DetailTeamItem;
import com.example.nba.model.match.EventsItem;
import com.example.nba.model.match.MatchResponse;
import com.example.nba.model.team.TeamResponse;
import com.example.nba.model.team.TeamsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NBAService {
    private Retrofit retrofit = null;

    public NBAAPI getNBAAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(NBAAPI.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(NBAAPI.class);
    }

    public void getMatch(final NBAListener<List<EventsItem>> listener) {
        getNBAAPI().getMatch().enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                MatchResponse data = response.body();
                if (data != null && data.getEvents() != null) {
                    listener.onSuccess(data.getEvents());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });

    }
    public void getTeam(final NBAListener<List<TeamsItem>> listener){
        getNBAAPI().getTeam().enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                TeamResponse data = response.body();
                if (data != null && data.getTeams() != null) {
                    listener.onSuccess(data.getTeams());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getDetailTeam(final NBAListener<List<DetailTeamItem>> listener, String id){
        getNBAAPI().getDetailTeams(id).enqueue(new Callback<DetailTeamResponse>() {
            @Override
            public void onResponse(Call<DetailTeamResponse> call, Response<DetailTeamResponse> response) {
                DetailTeamResponse data = response.body();
                if (data != null && data.getDetailTeams() != null) {
                    listener.onSuccess(data.getDetailTeams());
                }
            }

            @Override
            public void onFailure(Call<DetailTeamResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });

    }
}
