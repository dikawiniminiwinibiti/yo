package com.example.nba.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nba.R;
import com.example.nba.fragment.MatchFragment;
import com.example.nba.fragment.ProfileFragment;
import com.example.nba.fragment.TeamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navbar = findViewById(R.id.main_navbar);
        navbar.setOnNavigationItemSelectedListener(this);

        loadFragment(new MatchFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.ic_match:
                fragment = new MatchFragment();
                break;

            case R.id.ic_league:
                fragment = new TeamFragment();
                break;

            case R.id.ic_profil:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

}