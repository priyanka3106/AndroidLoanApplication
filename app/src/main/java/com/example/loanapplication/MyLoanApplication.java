package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyLoanApplication extends AppCompatActivity {
    ListView myLoanApplicationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_application);
        myLoanApplicationList=findViewById(R.id.MyLoanApplicationList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email=getIntent().getStringExtra("email");


        DBHelper db=new DBHelper(this);
        Cursor result=db.getLoanApplicationData(email);
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
            String myLoanApplication= result.getString(1);
            list.add(myLoanApplication);
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        myLoanApplicationList.setAdapter(arrayAdapter);

        myLoanApplicationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String loanTypeName=list.get(i);
                Intent intent=new Intent(MyLoanApplication.this,MyLoanDetails.class);

                intent.putExtra("loanTypeName",loanTypeName);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}