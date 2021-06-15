package com.example.nba.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nba.R;
import com.example.nba.database.AppDatabase;
import com.example.nba.database.DataUser;
import com.example.nba.database.DataUserDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvRegister;
    EditText etUsername, etPassword;
    Button btnLogin;
    DataUserDAO dataUserDAO;
    String dbUsername, dbPassword, dbFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.ti_username);
        etPassword = findViewById(R.id.ti_password);

        dataUserDAO = AppDatabase.getAppDatabase(this).dao();

        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(btnLogin.equals(v)){
            String sUsername = etUsername.getText().toString();
            String sPassword = etPassword.getText().toString();
            if(sUsername.equals("")){
                etUsername.setError("Username is required!");
            }
            if(sPassword.equals("")){
                etPassword.setError("Password is required!");
            }
            if(!sUsername.equals("") && !sPassword.equals("")){
                checkUser(sUsername, sPassword);
                if(dbUsername.equals(sUsername) && dbPassword.equals(sPassword)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("username", dbUsername);
                    intent.putExtra("fullname", dbFullname);
                    intent.putExtra("password", dbPassword);
                    startActivity(intent);
                    finish();
                }
                else if(!dbUsername.equals(sUsername)){
                    etUsername.setError( "Username or E-mail is wrong!" );
                } else if(!dbPassword.equals(sPassword)){
                    etPassword.setError( "Password is wrong!" );
                }
            }
        }
        if(tvRegister.equals(v)){
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void checkUser(String username, String password){
        List<DataUser> datas = dataUserDAO.getData();
        Log.d("Hasil : ", String.valueOf(datas.size()));
        for(DataUser dataUser : datas){
            System.out.println("Output");
            if(dataUser.getUsername().equals(username) && dataUser.getPassword().equals(password)){
                Log.d("Hasil : ", dataUser.getUsername());
                dbUsername = dataUser.getUsername();
                dbFullname = dataUser.getFullname();
                dbPassword = dataUser.getPassword();
                break;
            }
        }
    }
}