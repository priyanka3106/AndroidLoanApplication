package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeAdminDashboard extends AppCompatActivity {
    CardView UserListCard,LoanCard,ManageCard,ManageLoanRateCard,AboutUsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UserListCard=findViewById(R.id.profileCard);

        UserListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeAdminDashboard.this,UserListAdmin.class);
                startActivity(i);
            }
        });
        LoanCard=findViewById(R.id.loanCard);

        LoanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeAdminDashboard.this,AppliedUserList.class);
                startActivity(i);
            }
        });


        ManageCard=findViewById(R.id.ManageCard);

        ManageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeAdminDashboard.this,ManageLoanAdmin.class);
                startActivity(i);
            }
        });

        ManageLoanRateCard=findViewById(R.id.ManageLoanRateCard);

        ManageLoanRateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeAdminDashboard.this,LoanTypesAdmin.class);
                startActivity(i);
            }
        });

        AboutUsCard=findViewById(R.id.aboutUsCardadmin);

        AboutUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeAdminDashboard.this,AboutUs.class);
                startActivity(i);
            }
        });

    }
}