package com.example.nba.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nba.R;
import com.example.nba.model.detail.DetailTeamItem;
import com.example.nba.service.NBAListener;
import com.example.nba.service.NBAService;

import java.util.List;

public class DetailTeamActivity extends AppCompatActivity {
    ImageView ivPict;
    TextView tvName, tvDescription;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);
        ivPict = findViewById(R.id.team_logo);
        tvName = findViewById(R.id.team_name);
        tvDescription = findViewById(R.id.tx_detail);

        Boolean check = checkIncoming();
        if(check.equals(true)){
            id = getIntent().getStringExtra("id");
            new NBAService().getDetailTeam(detailListener, id);
        }
    }

    private Boolean checkIncoming(){
        Boolean check = false;
        if(getIntent().hasExtra("id")){
            check = true;
        }
        return check;
    }


    NBAListener<List<DetailTeamItem>> detailListener = new NBAListener<List<DetailTeamItem>>() {
        @Override
        public void onSuccess(List<DetailTeamItem> items) {
            String stadiumName, combineStadium, locationName, combineLocation;
            String stadium = " Stadium located at ";

            Glide.with(getApplicationContext()).load(items.get(0).getStrTeamBadge()).into(ivPict);
            tvName.setText(items.get(0).getStrTeam());
            tvDescription.setText(items.get(0).getStrDescriptionEN());

        }


        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };
}