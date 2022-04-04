package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class EignDashboard extends AppCompatActivity {

    CheckBox checkbox;
    Button Accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eign_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkbox=findViewById(R.id.checkBox);
        Accept=findViewById(R.id.take);

        DBHelper db=new DBHelper(this);

        String email=getIntent().getStringExtra("email");


        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Accept.setEnabled(true);
                }else{
                    Accept.setEnabled(false);
                }
            }
        });

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status=db.insertEsign(true,email);
                if (status == true) {
                    Toast.makeText(EignDashboard.this, "Esign Agreement Accepted", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(EignDashboard.this,ProfileDashboard.class);
                    i.putExtra("email",email);
                    startActivity(i);

                } else {
                    Toast.makeText(EignDashboard.this, "Esign Not Accepted" , Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}