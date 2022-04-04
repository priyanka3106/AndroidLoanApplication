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
import android.widget.Toast;

public class BankDetailsForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner bankname;
    EditText accountnumber,retypeaccount,ifscCode;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bankname=findViewById(R.id.bankname);
        ArrayAdapter<CharSequence> bankadaptor=ArrayAdapter.createFromResource(this,R.array.bankname,android.R.layout.simple_spinner_item);
        bankadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankname.setAdapter(bankadaptor);
        bankname.setOnItemSelectedListener(this);


        accountnumber=findViewById(R.id.accountnumber);
        retypeaccount=findViewById(R.id.retypeaccount);
        ifscCode=findViewById(R.id.ifscCode);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int pos= bankname.getSelectedItemPosition();//id of spinner
                String bankname[]=getResources().getStringArray(R.array.bankname);//string.xml
                String banknamedb=bankname[pos];

                String accountnumberdb=accountnumber.getText().toString();
                String retypeaccountdb=retypeaccount.getText().toString();
                String ifscCodedb=ifscCode.getText().toString();
                String email=getIntent().getStringExtra("email");

                if(banknamedb.contains("select") || accountnumberdb.equals("") || banknamedb.equals("") || retypeaccountdb.equals("") || ifscCodedb.equals(""))
                {
                    Toast.makeText(BankDetailsForm.this,"Please fill all the details",Toast.LENGTH_SHORT).show();

                }else if(!accountnumberdb.equals(retypeaccountdb)){
                    Toast.makeText(BankDetailsForm.this,"Please check Account No ..It's not matching",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean status=insertData(banknamedb,Long.parseLong(accountnumberdb),ifscCodedb,true,email);
                    if (status == true) {
                        Toast.makeText(BankDetailsForm.this, "Data Saved ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(BankDetailsForm.this, ProfileDashboard.class);
                        i.putExtra("email",email);
                        startActivity(i);

                    } else {
                        Toast.makeText(BankDetailsForm.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    private boolean insertData(String banknamedb, long accountnumberdb, String ifscCodedb, boolean b, String email) {
        DBHelper db=new DBHelper(this);
        boolean status=db.insertBankDetails(banknamedb,accountnumberdb,ifscCodedb,b,email);
        db.close();
        return status;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}