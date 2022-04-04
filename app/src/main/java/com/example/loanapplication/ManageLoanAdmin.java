package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ManageLoanAdmin extends AppCompatActivity {

    Button createNewLoan;
    ListView LoanTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_loan_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        createNewLoan=findViewById(R.id.newLoanbtn);
        createNewLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ManageLoanAdmin.this,CreateNewLoan.class);
                startActivity(i);
            }
        });

        DBHelper db=new DBHelper(this);
        LoanTypeList=findViewById(R.id.loanTypeList);
        Cursor result=db.getLoanType();
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
            String loantypename= result.getString(0);
            list.add(loantypename);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        LoanTypeList.setAdapter(arrayAdapter);
    }
}