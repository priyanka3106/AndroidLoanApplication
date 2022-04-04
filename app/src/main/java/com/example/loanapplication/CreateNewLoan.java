package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewLoan extends AppCompatActivity {

    EditText loanTypeName,maxLoanAmount,loanTenure,loanRate;
    Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_loan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loanTypeName=findViewById(R.id.LoanTypeName);
        maxLoanAmount=findViewById(R.id.MaxLoanAmount);
        loanTenure=findViewById(R.id.LoanTenure);
        loanRate=findViewById(R.id.LoanRate);

        createBtn=findViewById(R.id.CreateButton);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loanTypeNamedb=loanTypeName.getText().toString();
                long maxLoanAmountdb= Long.parseLong(maxLoanAmount.getText().toString());
                int loanTenuredb= Integer.parseInt(loanTenure.getText().toString());
                float loanRatedb= Float.parseFloat(loanRate.getText().toString());
                if(loanTypeNamedb.equals("") || maxLoanAmountdb==0.0 || loanTenuredb==0 || loanRatedb==0.0){
                    Toast.makeText(CreateNewLoan.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                }else{
                    boolean status=insertData(loanTypeNamedb,maxLoanAmountdb,loanTenuredb,loanRatedb);
                    if (status == true) {
                        Toast.makeText(CreateNewLoan.this, "Data Saved "+loanTenuredb,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateNewLoan.this, ManageLoanAdmin.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(CreateNewLoan.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private boolean insertData(String loanTypeNamedb, long maxLoanAmountdb, int loanTenuredb, float loanRatedb) {
        DBHelper db=new DBHelper(this);

        boolean status=db.insertLoanTypes(loanTypeNamedb,maxLoanAmountdb,loanTenuredb,loanRatedb);
        db.close();
        return status;
    }
}