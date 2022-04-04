package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoanEligibility extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText Income,Expense,Otherloanamount;
    Button check;
    Spinner ReasonForLoan;
    TextView amount,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_eligibility);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Income=findViewById(R.id.income);
        Expense=findViewById(R.id.expense);
        Otherloanamount=findViewById(R.id.otherloanamount);

       ReasonForLoan=findViewById(R.id.ReasonForLoan);
        ArrayAdapter<CharSequence> nadaptor=ArrayAdapter.createFromResource(this,R.array.ReasonForLoan,android.R.layout.simple_spinner_item);
        nadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ReasonForLoan.setAdapter(nadaptor);
        ReasonForLoan.setOnItemSelectedListener(this);

        String email=getIntent().getStringExtra("email");
        String loanTypeName=getIntent().getStringExtra("loanTypeName");



        float rate = getIntent().getFloatExtra("rate",0);
        float emi = getIntent().getFloatExtra("emi",0);
        float interest = getIntent().getFloatExtra("interest",0);
        float payment =getIntent().getFloatExtra("payment",0);
        float amt = getIntent().getFloatExtra("amt",0);
        float tenure = getIntent().getFloatExtra("tenure",0);

        amount=findViewById(R.id.amount);
        time=findViewById(R.id.tenure);
        
        amount.setText(""+amt);
        time.setText(""+tenure);

        check=findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float income= Float.parseFloat(Income.getText().toString());
                float expense= Float.parseFloat(Expense.getText().toString());
                float otherloanamount= Float.parseFloat(Otherloanamount.getText().toString());
                if(income>(emi+expense+otherloanamount)){
                    Intent i = new Intent(LoanEligibility.this, LoanConfirmation.class);
                    i.putExtra("rate", rate);
                    i.putExtra("emi", emi);
                    i.putExtra("interest", interest);
                    i.putExtra("payment", payment);
                    i.putExtra("amt", amt);
                    i.putExtra("tenure", tenure);
                    i.putExtra("email",email);
                    i.putExtra("loanTypeName",loanTypeName);

                    Toast.makeText(LoanEligibility.this, "LonTYpeName "+loanTypeName, Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }else{
                    Toast.makeText(LoanEligibility.this,"Sorry, But You Are Not Eligibile For Loan",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}