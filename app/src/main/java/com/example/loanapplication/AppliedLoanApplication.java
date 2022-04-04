package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppliedLoanApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_loan_application);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}