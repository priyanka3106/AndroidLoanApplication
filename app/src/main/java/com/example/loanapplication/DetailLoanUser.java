package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kotlinx.coroutines.Job;

public class DetailLoanUser extends AppCompatActivity {

    TextView Name,Email,Gender,Nationality,MaritalStatus,Address,City,State,PostalCode,ApartmentDetails,DurationOfStay,Occupations,JobType,WorkOfExp,BankName,AccountNumber,ifscCode;
    Button accept,reject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loan_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email=getIntent().getStringExtra("email");
        String loanTypeName=getIntent().getStringExtra("loanTypeName");


        Name=findViewById(R.id.namec);
        Email=findViewById(R.id.emailc);
        Gender=findViewById(R.id.genderc);
        Nationality=findViewById(R.id.nationalityc);
        MaritalStatus=findViewById(R.id.maritalStatusc);
        Address=findViewById(R.id.addressc);
        City=findViewById(R.id.cityc);
        State=findViewById(R.id.statec);
        PostalCode=findViewById(R.id.postalcodec);
        ApartmentDetails=findViewById(R.id.apartmentDetailsc);
        DurationOfStay=findViewById(R.id.durationOfStayc);
        Occupations=findViewById(R.id.occupationsc);
        JobType=findViewById(R.id.jobTypec);
        WorkOfExp=findViewById(R.id.workOfExperiencec);
        BankName=findViewById(R.id.bankNamec);
        AccountNumber=findViewById(R.id.accountNumberc);
        ifscCode=findViewById(R.id.ifscCodec);

        DBHelper personalDb=new DBHelper(this);
        Cursor perosnalCursor= personalDb.getPersonalInformation(email);
        while(perosnalCursor.moveToNext()){
            Name.setText(perosnalCursor.getString(0)+" "+perosnalCursor.getString(1));
            Email.setText(perosnalCursor.getString(7));
            Gender.setText("Female");
            Nationality.setText(perosnalCursor.getString(4));
            MaritalStatus.setText(perosnalCursor.getString(5));
        }
        personalDb.close();

        DBHelper contactDb=new DBHelper(this);
        Cursor contactCursor= contactDb.getContactInformation(email);
        while(contactCursor.moveToNext()){
            Address.setText(contactCursor.getString(0));
            City.setText(contactCursor.getString(1));
            State.setText(contactCursor.getString(2));
            PostalCode.setText(contactCursor.getString(3));
        }
        contactDb.close();

        DBHelper addDb=new DBHelper(this);
        Cursor addCursor=addDb.getAddressInformation(email);
        while (addCursor.moveToNext()){
            ApartmentDetails.setText(addCursor.getString(0));
            DurationOfStay.setText(addCursor.getString(1));
        }
        addDb.close();

        DBHelper incomeDb=new DBHelper(this);
        Cursor incomeCursor=incomeDb.getIncomeDetails(email);
        while(incomeCursor.moveToNext()){
            Occupations.setText(incomeCursor.getString(0));
            JobType.setText(incomeCursor.getString(3));
            WorkOfExp.setText(""+incomeCursor.getInt(6));
        }
        incomeDb.close();;

        DBHelper bankDb=new DBHelper(this);
        Cursor bankCursor=bankDb.getBankDetails(email);
        while(bankCursor.moveToNext()){
            BankName.setText(bankCursor.getString(0));
            AccountNumber.setText(""+bankCursor.getInt(1));
            ifscCode.setText(bankCursor.getString(2));
        }
        bankDb.close();


        accept=findViewById(R.id.approvec);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status= updateStatus(loanTypeName,email,"approve");
                if (status == true) {
                    Toast.makeText(DetailLoanUser.this, "Application Approved ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DetailLoanUser.this, HomeAdminDashboard.class);
                    startActivity(i);

                } else {
                    Toast.makeText(DetailLoanUser.this, "Not able to Update status " , Toast.LENGTH_SHORT).show();
                }
            }
        });


        reject=findViewById(R.id.rejectc);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status= updateStatus(loanTypeName,email,"reject");
                if (status == true) {
                    Toast.makeText(DetailLoanUser.this, "Application Rejected",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DetailLoanUser.this, HomeAdminDashboard.class);
                    startActivity(i);

                } else {
                    Toast.makeText(DetailLoanUser.this, "Not able to Update status " , Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private boolean updateStatus(String loanTypeName, String email, String appStatus) {
        DBHelper db=new DBHelper(this);
        boolean status=db.updateLoanApplicationStatus(loanTypeName,email,appStatus);
        db.close();
        return status;
    }
}