package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LoanSelectionSchema extends AppCompatActivity {
    ListView LoanTypeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_selection_schema);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoanTypeList=findViewById(R.id.loanTypeList);
        DBHelper db=new DBHelper(this);
        Cursor result=db.getLoanType();
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
            String loantypename= result.getString(0);
            list.add(loantypename);
        }
        String email=getIntent().getStringExtra("email");

        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        LoanTypeList.setAdapter(arrayAdapter);

        LoanTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String loanTypeName=list.get(i);
                Intent intent=new Intent(LoanSelectionSchema.this,SelectionLoan.class);
                intent.putExtra("loanTypeName",loanTypeName);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });




    }
}