package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProfileDashboard extends AppCompatActivity {
 CardView Detailcard,KYCcard,Esigncard;
 ImageView StatusDetails,StatusKYC,StatusEsign;

 TextView ProfileStatus;//ktrueSign,kwrongSign,etrueSign,ewrongSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        StatusDetails=findViewById(R.id.statusDetails);
        StatusKYC=findViewById(R.id.statusKYC);
        StatusEsign=findViewById(R.id.statusEsign);

        String email=getIntent().getStringExtra("email");


        boolean profileStatus=getProfileStatus(email);
        if(profileStatus){
            StatusDetails.setImageResource(R.drawable.correct);
        }
        boolean kycStatus=getKYCStatus(email);
        if(kycStatus){
            StatusKYC.setImageResource(R.drawable.correct);
        }
        boolean esignStatus=getEsignStatus(email);
        if(esignStatus){
            StatusEsign.setImageResource(R.drawable.correct);
        }




        Detailcard =findViewById(R.id.detailscard);
        Detailcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileDashboard.this,PersonalInformationForm.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        KYCcard =findViewById(R.id.kyccard);
        KYCcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileDashboard.this,KYCDashboard.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        Esigncard=findViewById(R.id.esgincard);
        Esigncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileDashboard.this,EignDashboard.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

    }

    private boolean getEsignStatus(String email) {
        
        DBHelper db=new DBHelper(this);
        boolean status = false;
        Cursor result=db.getEsign(email);
        while(result.moveToNext()){
           status= result.getInt(0)>0;
        }
        return status;
    }

    private boolean getKYCStatus(String email) {

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
        if(aadherStatus && panStatus && bankStatus && selfieStatus){
            return true;
        }else{
            return false;
        }
    }

    private boolean getProfileStatus(String email) {
        DBHelper db=new DBHelper(this);
        boolean status1 = false,status2= false,status3= false,status4= false,status5= false;

        Cursor pcursor=db.getPersonalInformation(email);
        while(pcursor.moveToNext()){
            status1=pcursor.getInt(6)>0;
        }
        Cursor ccursor=db.getContactInformation(email);
        while(ccursor.moveToNext()){
            status2=ccursor.getInt(4)>0;
        }
        Cursor acursor=db.getAddressInformation(email);
        while(acursor.moveToNext()){
            status3=acursor.getInt(2)>0;
        }
        Cursor icursor=db.getIncomeDetails(email);
        while(icursor.moveToNext()){
            status4=icursor.getInt(6)>0;
        }
        Cursor bcursor=db.getBankDetails(email);
        while(bcursor.moveToNext()){
            status5=bcursor.getInt(3)>0;
        }
        if(status1 && status2 && status3 && status4 && status5 ){
            return true;
        }else{
            return false;
        }

    }
}