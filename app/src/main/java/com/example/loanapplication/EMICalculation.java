package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class EMICalculation extends AppCompatActivity {
    TextView Amount,Tenure,Rateint,Rate,Emi,Interest,Payment;
    SeekBar AmountSeekBar, TenureSeekBar,RateSeekBar;
    Button calculate;
    float emi,TA,ti;


    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Amount=findViewById(R.id.amount);
        Tenure=findViewById(R.id.tenure);
        Rateint=findViewById(R.id.rateint);

        AmountSeekBar=findViewById(R.id.seekbarincome);
        TenureSeekBar=findViewById(R.id.seekbartenure);
        RateSeekBar=findViewById(R.id.seekbarrate);

        int amtsteps=1000;
        int atmmin=0;
        int atmmax=2000000;
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
        int timemax=20;
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

        float ratesetps= 0.5F;
        float ratemax=20.0F;
        float ratemin=0.0F;
        RateSeekBar.setMax((int) ((ratemax-ratemin)/ratesetps));

        RateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float rate=ratemin+(i*ratesetps);
                Rateint.setText(""+rate);
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
                float i=Float.parseFloat(Rateint.getText().toString());
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
                Toast.makeText(EMICalculation.this, "Here are new details", Toast.LENGTH_SHORT).show();

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