package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionLoan extends AppCompatActivity {
    TextView loanTypeName,loanMaxAmount,loanTenure,loanRate;
    Button selectLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_loan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loanTypeName=findViewById(R.id.LoanTypeName);
        loanMaxAmount=findViewById(R.id.LoanMaxAmount);
        loanTenure=findViewById(R.id.LoanTenure);
        loanRate=findViewById(R.id.LoanRate);

        String loanType=getIntent().getStringExtra("loanTypeName");
        String email=getIntent().getStringExtra("email");

        DBHelper db=new DBHelper(this);

        Cursor result=db.getLoanTypeByLoanName(loanType);
        while (result.moveToNext()){
            loanTypeName.setText(result.getString(0));
            loanMaxAmount.setText(""+result.getDouble(1));
            loanTenure.setText(""+result.getInt(2));
            loanRate.setText(""+result.getFloat(3));

        }
        db.close();
        selectLoan= findViewById(R.id.SelectLoan);

        selectLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectionLoan.this, "LonTYpeName "+loanTypeName, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SelectionLoan.this,LoanCalculation.class);

                i.putExtra("loanTypeName",loanType);
//                i.putExtra("loanTenure",Integer.parseInt(loanTenure.getText().toString()));
           //     i.putExtra("loanRate",Float.parseFloat(loanRate.getText().toString()));
                i.putExtra("email",email);

                startActivity(i);
            }
        });


    }
}