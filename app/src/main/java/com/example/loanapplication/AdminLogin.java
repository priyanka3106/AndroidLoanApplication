package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText usernameTxt,passwordTxt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_admin_login);
        usernameTxt=findViewById(R.id.UsernameId);
        passwordTxt=findViewById(R.id.PasswordId);
        loginBtn=findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestring=usernameTxt.getText().toString();
                String passwordstring=passwordTxt.getText().toString();
                if(usernamestring.equals("admin") && passwordstring.equals("admin123")){
                    Toast.makeText(AdminLogin.this, "Login Sucessffully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(AdminLogin.this,HomeAdminDashboard.class);
                    startActivity(i);
                }else{
                    Toast.makeText(AdminLogin.this, "Login Unsucessffully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}