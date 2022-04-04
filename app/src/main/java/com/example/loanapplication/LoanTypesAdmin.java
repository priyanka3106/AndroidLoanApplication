package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoanTypesAdmin extends AppCompatActivity {

    ListView LoanTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_types_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        LoanTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String loanTypeName=list.get(i);
                Intent intent=new Intent(LoanTypesAdmin.this,ManageLoanRateAdmin.class);
                intent.putExtra("loanTypeName",loanTypeName);
                startActivity(intent);
            }
        });

    }
}