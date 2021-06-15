package com.example.nba.service;

import com.example.nba.model.detail.DetailTeamResponse;
import com.example.nba.model.match.MatchResponse;
import com.example.nba.model.team.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NBAAPI {
    String URL_BASE = "https://www.thesportsdb.com";

    @GET("/api/v1/json/1/eventspastleague.php?id=4387")
    Call<MatchResponse> getMatch();

    @GET("/api/v1/json/1/lookup_all_teams.php?id=4387")
    Call<TeamResponse> getTeam();

    @GET("/api/v1/json/1/lookupteam.php?")
    Call<DetailTeamResponse> getDetailTeams(@Query("id") String teamID);
}
