package com.example.nba.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nba.R;
import com.example.nba.model.team.TeamsItem;
import com.example.nba.view.DetailTeamActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{
    public List<TeamsItem> teamList;

    public TeamAdapter(List<TeamsItem> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @NotNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View teamRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new ViewHolder(teamRow);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeam;
        TextView tvTeamName, tvTeamStadium;
        Button btnDetail;
        String stadiumName, combineStadium, locationName, combineLocation;
        String stadium = " Stadium located at ";

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivTeam = itemView.findViewById(R.id.img_team);
            tvTeamName = itemView.findViewById(R.id.team_name);

            tvTeamStadium = itemView.findViewById(R.id.team_from);
            btnDetail = itemView.findViewById(R.id.btn_preview);
        }
        void bind(TeamsItem item){
            Glide.with(itemView.getContext()).load(item.getStrTeamBadge()).into(ivTeam);
            tvTeamName.setText(item.getStrTeam());
            stadiumName = item.getStrStadium();
            locationName = item.getStrStadiumLocation();
            combineStadium = stadiumName.concat(stadium);
            combineLocation = combineStadium.concat(locationName);
//            Log.d("Hasil : ", combineStadium);
            tvTeamStadium.setText(combineLocation);

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(itemView.getContext(), DetailTeamActivity.class);
                    detail.putExtra("id", item.getIdTeam());
                    itemView.getContext().startActivity(detail);
                }
            });
        }
    }
}
