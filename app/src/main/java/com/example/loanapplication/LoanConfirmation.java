package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LoanConfirmation extends AppCompatActivity {

    CheckBox checkbox;
    TextView Emi,Apr,Tenure,Interest,Payment;
    Button ApplyForLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_confirmation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Emi=findViewById(R.id.emi);
        Apr=findViewById(R.id.apr);
        Tenure=findViewById(R.id.tenure);
        Interest=findViewById(R.id.interest);
        Payment=findViewById(R.id.payment);

        String email=getIntent().getStringExtra("email");
        String loanTypeName=getIntent().getStringExtra("loanTypeName");

        float rate = getIntent().getFloatExtra("rate",0);
        float emi = getIntent().getFloatExtra("emi",0);
        float interest = getIntent().getFloatExtra("interest",0);
        float payment =getIntent().getFloatExtra("payment",0);
        float amt = getIntent().getFloatExtra("amt",0);
        int tenure = getIntent().getIntExtra("tenure",0);

        Emi.setText("INR "+emi);
        Apr.setText(""+10+" %");
        Tenure.setText(""+(int)(6));
        Interest.setText("INR "+interest);
        Payment.setText("INR "+payment);

        ApplyForLoan=findViewById(R.id.applyloan);

        checkbox=findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ApplyForLoan.setEnabled(true);
                }else{
                    ApplyForLoan.setEnabled(false);
                }
            }
        });

        ApplyForLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();
                int loanappId=random.nextInt(100-1)+1;
                boolean status=insertLoanApplication(loanappId,loanTypeName,email,amt,6,10,interest,payment,emi);
                if (status == true) {
                    Toast.makeText(LoanConfirmation.this, "Application Submitted Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoanConfirmation.this,HomeDashboard.class);
                    i.putExtra("email",email);
                    startActivity(i);

                } else {
                    Toast.makeText(LoanConfirmation.this, "Application Not Submitted Sucessfully" , Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    private boolean insertLoanApplication(int loanappId, String loanTypeName, String email, float amt, int tenure, float rate, float interest, float payment, float emi) {
    DBHelper db=new DBHelper(this);
    boolean status=db.insertLoanApp(loanappId,loanTypeName,email,amt,tenure,rate,interest,payment,emi,"pending");
    return status;
    }
}