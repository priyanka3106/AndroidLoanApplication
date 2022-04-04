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

public class ManageLoanRateAdmin extends AppCompatActivity {
    TextView loanTypeName,loanMaxAmount,loanTenure;
    EditText loanRate;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_loan_rate_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loanTypeName=findViewById(R.id.LoanTypeName);
        loanMaxAmount=findViewById(R.id.LoanMaxAmount);
        loanTenure=findViewById(R.id.LoanTenure);
        loanRate=findViewById(R.id.LoanRate);

        String loanType=getIntent().getStringExtra("loanTypeName");

        DBHelper db=new DBHelper(this);

        Cursor result=db.getLoanTypeByLoanName(loanType);


        while (result.moveToNext()){
            loanTypeName.setText(result.getString(0));
      loanMaxAmount.setText(""+result.getDouble(1));
       loanTenure.setText(""+result.getInt(2));
         loanRate.setText(""+result.getFloat(3));

        }
        db.close();

        update=findViewById(R.id.UpdateLoanRate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loanTypeNameDb=loanTypeName.getText().toString();
                double loanMaxAmountDb= Double.parseDouble(loanMaxAmount.getText().toString());
                int loanTenureDb= Integer.parseInt(loanTenure.getText().toString());
                float loanRateDb= Float.parseFloat(loanRate.getText().toString());
               boolean status= updateLoanRate(loanTypeNameDb,loanRateDb);
                if (status == true) {
                    Toast.makeText(ManageLoanRateAdmin.this, "Data Update Sucessfully ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ManageLoanRateAdmin.this, HomeAdminDashboard.class);
                    startActivity(i);

                } else {
                    Toast.makeText(ManageLoanRateAdmin.this, "Data Not Updateted " , Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private boolean updateLoanRate(String loanTypeNameDb, float loanRateDb) {
        DBHelper db=new DBHelper(this);
        boolean status=db.updateLoanTypeByRate(loanTypeNameDb,loanRateDb);
        return status;
    }
}
