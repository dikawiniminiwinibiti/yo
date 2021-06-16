package com.example.nba.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nba.R;
import com.example.nba.fragment.MatchFragment;
import com.example.nba.fragment.ProfileFragment;
import com.example.nba.fragment.TeamFragment;
import com.example.nba.service.NBAService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navbar;
    String username, fullname, password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navbar = findViewById(R.id.main_navbar);
        navbar.setOnNavigationItemSelectedListener(this);

        Boolean check = checkIncoming();
        if(check.equals(true)){
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            username = getIntent().getStringExtra("username");
            fullname = getIntent().getStringExtra("fullname");
            password = getIntent().getStringExtra("password");
        }

        loadFragment(new MatchFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
            Log.d("Hasil fragment", fragment.toString());
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.icMatch:
                fragment = new MatchFragment();
                break;

            case R.id.ic_league:
                fragment = new TeamFragment();
                break;

            case R.id.ic_profil:
                fragment = new ProfileFragment(username, fullname, password, id);
                break;
        }
        return loadFragment(fragment);
    }

    private Boolean checkIncoming(){
        Boolean check = false;
        if(getIntent().hasExtra("username")){
            check = true;
        }
        return check;
    }

}