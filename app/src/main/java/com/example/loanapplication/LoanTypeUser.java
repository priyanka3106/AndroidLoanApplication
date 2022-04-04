package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LoanTypeUser extends AppCompatActivity {
    ListView LoanTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_type_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoanTypeList=findViewById(R.id.loanTypeList);
        DBHelper db=new DBHelper(this);
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