package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity {
    Button loginBtn, registerBtn ,loginAsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registerBtn=findViewById(R.id.Register);
        loginBtn=findViewById(R.id.login);
        loginAsBtn=findViewById(R.id.LoginAsAdmin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginScreen.this,LoginMain.class);//one page to another
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginScreen.this,MainActivity.class);
                startActivity(i);
            }
        });
        loginAsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginScreen.this,AdminLogin.class);
                startActivity(i);
            }
        });
    }
}