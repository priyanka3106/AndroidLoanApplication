package com.example.loanapplication;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loanapplication.*;

import java.io.IOException;
import java.io.InputStream;

public class KYCDashboard extends AppCompatActivity {

    Button UploadAadher,UploadPan,UploadBank,UploadSelfie;
    ImageView StatusAadher,StatusPan,StatusBank,StatusSelfie;


    @Override




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycdashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email=getIntent().getStringExtra("email");

        UploadAadher=findViewById(R.id.uploadAadher);

        UploadAadher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(KYCDashboard.this,UploadSelectImage.class);
                i.putExtra("email",email);
                i.putExtra("docType","AadherCard");
                startActivity(i);

            }
        });

        UploadPan=findViewById(R.id.panupload);

        UploadPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(KYCDashboard.this,UploadSelectImage.class);
                i.putExtra("email",email);
                i.putExtra("docType","PanCard");
                startActivity(i);

            }
        });

        UploadBank=findViewById(R.id.bankupload);

        UploadBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(KYCDashboard.this,UploadSelectImage.class);
                i.putExtra("email",email);
                i.putExtra("docType","Bank");
                startActivity(i);

            }
        });

        UploadSelfie=findViewById(R.id.selfieupload);

        UploadSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(KYCDashboard.this,UploadSelectImage.class);
                i.putExtra("email",email);
                i.putExtra("docType","Selfie");
                startActivity(i);

            }
        });

        StatusAadher=findViewById(R.id.statusAadher);
        StatusPan=findViewById(R.id.statusPan);
        StatusBank=findViewById(R.id.statusBank);
        StatusSelfie=findViewById(R.id.statusSelfie);

        DBHelper db=new DBHelper(this);
        boolean aadherStatus = false,panStatus = false,bankStatus=false,selfieStatus=false;


        Cursor aadherCursor=db.getKYCAadherCard(email);
        while(aadherCursor.moveToNext()){
            aadherStatus=aadherCursor.getInt(1)>0;
        }
        Cursor panCursor=db.getKYCPanCard(email);
        while(panCursor.moveToNext()){
            panStatus=panCursor.getInt(1)>0;
        }
        Cursor bankCursor=db.getKYCBank(email);
        while(bankCursor.moveToNext()){
            bankStatus=bankCursor.getInt(1)>0;
        }
        Cursor selfieCursor=db.getKYCSelfie(email);
        while(selfieCursor.moveToNext()){
            selfieStatus=selfieCursor.getInt(1)>0;
        }
        db.close();

        if(aadherStatus){
            StatusAadher.setImageResource(R.drawable.correct);
        }
        if(panStatus){
            StatusPan.setImageResource(R.drawable.correct);
        }
        if(bankStatus){
            StatusBank.setImageResource(R.drawable.correct);
        }
        if(selfieStatus){
            StatusSelfie.setImageResource(R.drawable.correct);
        }

    }

}