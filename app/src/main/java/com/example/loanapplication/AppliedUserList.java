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

public class AppliedUserList extends AppCompatActivity {
    ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_user_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBHelper db=new DBHelper(this);
        listUser=findViewById(R.id.listUser);
        Cursor result=db.getLoanApplicationUser();
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
            String listEmail= result.getString(0);
            list.add(listEmail);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listUser.setAdapter(arrayAdapter);

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String userEmail=list.get(i);
                Intent intent=new Intent(AppliedUserList.this,UserLoanList.class);
                intent.putExtra("email",userEmail);
                startActivity(intent);
            }
        });
    }
}