package com.example.nba.adapter;

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

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ivTeamHome = itemView.findViewById(R.id.img_team1);
            ivTeamAway = itemView.findViewById(R.id.img_team2);
            tvHomeName = itemView.findViewById(R.id.team_name1);
            tvHomeScore = itemView.findViewById(R.id.team_name2);
            tvAwayName = itemView.findViewById(R.id.score_team1);
            tvAwayScore = itemView.findViewById(R.id.score_team2);
        }

        void bind(EventsItem item){
            tvHomeName.setText(item.getStrHomeTeam());
            tvHomeScore.setText(item.getIntHomeScore());
            tvAwayName.setText(item.getStrAwayTeam());
            tvAwayScore.setText(item.getIntAwayScore());

            idHomeTeam = item.getIdHomeTeam();
            idAwayTeam = item.getIdAwayTeam();

            Log.d("Hasil : ", item.getStrAwayTeam());

            new NBAService().getDetailTeam(detailHomeListener, idHomeTeam);
            new NBAService().getDetailTeam(detailAwayListener, idAwayTeam);
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
