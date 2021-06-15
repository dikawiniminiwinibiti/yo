package com.example.nba.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nba.R;
import com.example.nba.database.AppDatabase;
import com.example.nba.database.DataUser;
import com.example.nba.database.DataUserDAO;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    DataUserDAO dataUserDAO;
    EditText etUsername, etFullname, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataUserDAO = AppDatabase.getAppDatabase(this).dao();

        etUsername = findViewById(R.id.ti_username);
        etFullname = findViewById(R.id.ti_fullname);
        etPassword = findViewById(R.id.ti_password);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = etUsername.getText().toString();
        String fullname = etFullname.getText().toString();
        String password = etPassword.getText().toString();

        DataUser dataUser = new DataUser(username, fullname, password);
        dataUserDAO.InsertData(dataUser);
        finish();
    }
}