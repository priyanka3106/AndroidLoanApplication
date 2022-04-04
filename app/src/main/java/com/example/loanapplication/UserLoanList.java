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

public class UserLoanList extends AppCompatActivity {
    ListView listLoanUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_loan_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email=getIntent().getStringExtra("email");

        DBHelper db=new DBHelper(this);
        listLoanUser=findViewById(R.id.listUserLoans);
        Cursor result=db.getpendingLoanApplicationData(email,"pending");
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
            String listLoan= result.getString(1);
            list.add(listLoan);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listLoanUser.setAdapter(arrayAdapter);

        listLoanUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String loanTypeName=list.get(i);
                Intent intent=new Intent(UserLoanList.this, DetailLoanUser.class);
                Toast.makeText(UserLoanList.this, "LonTYpeName "+loanTypeName, Toast.LENGTH_SHORT).show();
                intent.putExtra("email",email);
                intent.putExtra("loanTypeName",loanTypeName);
                startActivity(intent);
            }
        });

    }
}