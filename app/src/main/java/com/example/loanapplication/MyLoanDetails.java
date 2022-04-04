package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class MyLoanDetails extends AppCompatActivity {
    TextView loanTypeName,loanMaxAmount,loanTenure,loanRate,totalInterest,totalPaybale,emi,Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loanTypeName=findViewById(R.id.LoanTypeName);
        loanMaxAmount=findViewById(R.id.LoanMaxAmount);
        loanTenure=findViewById(R.id.LoanTenure);
        loanRate=findViewById(R.id.LoanRate);
        totalInterest=findViewById(R.id.TotalInterest);
        totalPaybale=findViewById(R.id.TotalPaybale);
        emi=findViewById(R.id.Emi);
        Status=findViewById(R.id.status);

        String loanType=getIntent().getStringExtra("loanTypeName");
        String email=getIntent().getStringExtra("email");

        DBHelper db=new DBHelper(this);
        Cursor result=db.getLoanApplicationDataByLoanTypeName(loanType,email);
        while (result.moveToNext()){
            loanTypeName.setText(result.getString(1));
            loanMaxAmount.setText(""+result.getDouble(3));
            loanTenure.setText(""+result.getInt(4));
            loanRate.setText(""+result.getFloat(5));
            totalInterest.setText(""+result.getFloat(6));
            totalPaybale.setText(""+result.getFloat(7));
            emi.setText(""+result.getFloat(8));
            Status.setText(result.getString(9));

        }

        db.close();


    }
}