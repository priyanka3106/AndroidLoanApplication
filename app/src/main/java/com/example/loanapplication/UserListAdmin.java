package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserListAdmin extends AppCompatActivity {
    ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBHelper db=new DBHelper(this);

        listUser=findViewById(R.id.listUser);
        Cursor result=db.getUserDetails();
        ArrayList<String> list=new ArrayList<>();
        while (result.moveToNext()){
           String firstname= result.getString(0);
           String lastname= result.getString(1);
           list.add(firstname+" "+lastname);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listUser.setAdapter(arrayAdapter);

      /*listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               int cursor= result.getPosition(i);
               String name=cursor.getString(0);
               Toast.makeText(UserListAdmin.this, "Name" +name, Toast.LENGTH_SHORT).show();
           }
       });*/

    }
}