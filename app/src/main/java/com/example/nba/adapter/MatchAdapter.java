package com.example.nba.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nba.R;
import com.example.nba.model.detail.DetailTeamItem;
import com.example.nba.model.match.EventsItem;
import com.example.nba.service.NBAListener;
import com.example.nba.service.NBAService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    public List<EventsItem> matchList;

    public MatchAdapter(List<EventsItem> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @NotNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View matchRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new ViewHolder(matchRow);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MatchAdapter.ViewHolder holder, int position) {
        holder.bind(matchList.get(position));
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeamHome, ivTeamAway;
        TextView tvHomeName, tvHomeScore, tvAwayName, tvAwayScore, tvDate;
        String idHomeTeam, idAwayTeam;
        int homeScore, awayScore;
        String sHomeScore, sAwayScore;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ivTeamHome = itemView.findViewById(R.id.img_team1);
            ivTeamAway = itemView.findViewById(R.id.img_team2);
            tvHomeName = itemView.findViewById(R.id.team_name1);
            tvHomeScore = itemView.findViewById(R.id.score_team1);
            tvAwayName = itemView.findViewById(R.id.team_name2);
            tvAwayScore = itemView.findViewById(R.id.score_team2);
        }

        void bind(EventsItem item){
            idHomeTeam = item.getIdHomeTeam();
            idAwayTeam = item.getIdAwayTeam();
//            Log.d("Hasil : ", item.getStrAwayTeam());
            new NBAService().getDetailTeam(detailHomeListener, idHomeTeam);
            new NBAService().getDetailTeam(detailAwayListener, idAwayTeam);

            tvHomeName.setText(item.getStrHomeTeam());
            tvHomeScore.setText(item.getIntHomeScore());
            tvAwayName.setText(item.getStrAwayTeam());
            tvAwayScore.setText(item.getIntAwayScore());

            sHomeScore = item.getIntHomeScore();
            sAwayScore = item.getIntAwayScore();
            if(sHomeScore!=null && sAwayScore!=null){
                homeScore = Integer.parseInt(sHomeScore);
                awayScore = Integer.parseInt(sAwayScore);
                if(homeScore > awayScore){
                    tvHomeScore.setTextColor(Color.parseColor("#537a50"));
                    tvAwayScore.setTextColor(Color.parseColor("#bf5c5c"));
                } else {
                    tvHomeScore.setTextColor(Color.parseColor("#bf5c5c"));
                    tvAwayScore.setTextColor(Color.parseColor("#537a50"));
                }
            }

        }

        NBAListener<List<DetailTeamItem>> detailHomeListener = new NBAListener<List<DetailTeamItem>>() {
            @Override
            public void onSuccess(List<DetailTeamItem> items) {
                Glide.with(itemView.getContext()).load(items.get(0).getStrTeamBadge()).into(ivTeamHome);
            }

            @Override
            public void onFailed(String msg) {
                Log.d("Error : ", msg);
            }
        };

        NBAListener<List<DetailTeamItem>> detailAwayListener = new NBAListener<List<DetailTeamItem>>() {
            @Override
            public void onSuccess(List<DetailTeamItem> items) {
                Glide.with(itemView.getContext()).load(items.get(0).getStrTeamBadge()).into(ivTeamAway);
            }

            @Override
            public void onFailed(String msg) {
                Log.d("Error : ", msg);
            }
        };
    }
}
