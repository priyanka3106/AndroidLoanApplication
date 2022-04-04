package com.example.loanapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonalDatabase extends SQLiteOpenHelper {
    public PersonalDatabase(Context context) {
        super(context,"Loandatabase.db", null   , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table PersonalInformation(firstName Text,lastName Text,dateofBirth Text,nationality Text,mstatus Text, isCompleted boolean, email Text primary key)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists PersonalInformation");

    }

    public boolean insertPersonalInformation(String FirstName, String LastName, String DateofBirth,String Nationality, String Mstatus,Boolean Iscompleted, String Email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("firstName", FirstName);//(col name,user data)
        con.put("lastName", LastName);
        con.put("dateofBirth", DateofBirth);
        //con.put("gender",  Gender);
        con.put("nationality", Nationality);
        con.put("mstatus", Mstatus);
        con.put("isCompleted", Iscompleted);
        con.put("email", Email);
        long result = sqlDb.insert("PersonalInformation", null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
}
