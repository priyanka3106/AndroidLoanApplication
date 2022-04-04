package com.example.loanapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class LoanCalculation extends AppCompatActivity {
    TextView Amount,Tenure,Rate,Emi,Interest,Payment;
    SeekBar AmountSeekBar, TenureSeekBar;
    Button calculate,applayLoan;
    float emi,TA,ti;
    
    
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Amount=findViewById(R.id.amount);
        Tenure=findViewById(R.id.tenure);

        AmountSeekBar=findViewById(R.id.seekbarincome);
        TenureSeekBar=findViewById(R.id.seekbartenure);

        String email=getIntent().getStringExtra("email");
        String loanTypeName=getIntent().getStringExtra("loanTypeName");

        int atmmax=100000;
        int timemax=20;

        DBHelper db=new DBHelper(this);
        Cursor result=db.getLoanTypeByLoanName(loanTypeName);
        while(result.moveToNext()){
            atmmax=result.getInt(1);
            timemax=result.getInt(2);
        }

        int amtsteps=1000;
        int atmmin=0;
       // int atmmax=getIntent().getIntExtra("loanMaxAmount",100000);
        AmountSeekBar.setMax((atmmax-atmmin)/amtsteps);

        AmountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               int value=atmmin+(i*amtsteps);
                Amount.setText(""+value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int timesetps=1;
       // int timemax=getIntent().getIntExtra("loanTenure",10);
        int timemin=0;
        TenureSeekBar.setMax((timemax-timemin)/timesetps);

        TenureSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int time=timemin+(i*timesetps);
                Tenure.setText(""+time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculate=findViewById(R.id.calculate);
        Rate=findViewById(R.id.rate);
        Emi=findViewById(R.id.emi);
        Interest=findViewById(R.id.interest);
        Payment=findViewById(R.id.payment);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float p= Float.parseFloat(Amount.getText().toString());
                float y=Float.parseFloat(Tenure.getText().toString());
                float i=getIntent().getFloatExtra("loanRate",10);;
                float Principal = calPric(p);
                float rate = calInt(i);
                float Months = calMonth(y);
                float Dvdnt = calDvdnt(rate, Months);
                float FD = calFinalDvdnt(Principal, rate, Dvdnt);
                float D = calDivider(Dvdnt);
                emi = calEmi(FD, D);
                TA = calTa(emi, Months);
                ti = calTotalInt(TA, Principal);

                Rate.setText(""+df.format(i)+" %");
                Emi.setText(""+df.format(emi));
                Interest.setText(""+df.format(ti));
                Payment.setText(""+df.format(TA));
                Toast.makeText(LoanCalculation.this, "Here are new details", Toast.LENGTH_SHORT).show();

            }
        });
        

        applayLoan=findViewById(R.id.ApplyLoan);
        applayLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               float amt= Float.parseFloat(Amount.getText().toString());
               float tenure = Float.parseFloat(Tenure.getText().toString());

         if (amt == 0 || tenure == 0) {
                Toast.makeText(LoanCalculation.this, "Please fill Loan Amount and Loan Tenure", Toast.LENGTH_SHORT).show();
               } else {
                    Intent i = new Intent(LoanCalculation.this, LoanEligibility.class);
                  i.putExtra("rate", 9.0);
                    i.putExtra("emi", emi);
                    i.putExtra("interest", ti);
                    i.putExtra("payment", TA);
                 i.putExtra("amt", amt);
                  i.putExtra("tenure", tenure);
             Toast.makeText(LoanCalculation.this, "LonTYpeName "+loanTypeName, Toast.LENGTH_SHORT).show();
             i.putExtra("loanTypeName",loanTypeName);
             i.putExtra("email",email);
                    startActivity(i);
                }
            }
        });

    }

    public float calPric(float p)
    {
        return (float)(p);
    }
    public float calInt(float i) {
        return (float)(i / 12 / 100);
    }
    public float calMonth(float y) {
        return (float)(y * 12);
    }
    public float calDvdnt(float Rate, float Months) {
        return (float)(Math.pow(1 + Rate, Months));
    }
    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
        return (float)(Principal * Rate * Dvdnt);
    }
    public float calDivider(float Dvdnt) {
        return (float)(Dvdnt - 1);
    }
    public float calEmi(float FD, Float D) {
        return (float)(FD / D);
    }
    public float calTa(float emi, Float Months) {
        return (float)(emi * Months);
    }
    public float calTotalInt(float TA, float Principal) {
        return (float)(TA - Principal);
    }
}