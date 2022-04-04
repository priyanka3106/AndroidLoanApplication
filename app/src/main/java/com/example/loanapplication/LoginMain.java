package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMain extends AppCompatActivity {

    EditText usernameTxt,passwordTxt;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        usernameTxt=findViewById(R.id.UsernameId);
        passwordTxt=findViewById(R.id.PasswordId);
        loginBtn=findViewById(R.id.login);

        DBHelper db=new DBHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestring=usernameTxt.getText().toString();
                String passwordstring=passwordTxt.getText().toString();
                Boolean status=db.loginUser(usernamestring,passwordstring);
                if(status){
                    Toast.makeText(LoginMain.this, "Login Sucessffully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginMain.this,HomeDashboard.class);
                    i.putExtra("email",usernamestring); // To Transfer data from one activity to second activity
                    startActivity(i);
                }else{
                    Toast.makeText(LoginMain.this, "Login Unsucessffully", Toast.LENGTH_SHORT).show();
                }
                            }
        });


    }
}