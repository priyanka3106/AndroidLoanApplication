package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeDashboard extends AppCompatActivity {

    CardView ProfileCard,LoanCard,CalEMICard,LoanTypeCard,MyLoanCard,AboutUsCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ProfileCard=findViewById(R.id.profileCard);

        String email=getIntent().getStringExtra("email");

        ProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,ProfileDashboard.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        LoanCard=findViewById(R.id.LoanCard);

        LoanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,LoanSelectionSchema.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        LoanTypeCard=findViewById(R.id.loanTypeCard);

        LoanTypeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,LoanTypeUser.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        LoanCard=findViewById(R.id.LoanCard);

        LoanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,LoanSelectionSchema.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        CalEMICard=findViewById(R.id.calEMICard);

        CalEMICard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,EMICalculation.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        MyLoanCard=findViewById(R.id.myLoanCard);

        MyLoanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,MyLoanApplication.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        AboutUsCard=findViewById(R.id.aboutUsCard);

        AboutUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeDashboard.this,AboutUs.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

    }
}