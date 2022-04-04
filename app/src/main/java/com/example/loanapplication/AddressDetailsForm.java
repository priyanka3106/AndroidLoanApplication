package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddressDetailsForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner  apartmentdetails,durationofstay;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apartmentdetails=findViewById(R.id.apartmentdetails);
        ArrayAdapter<CharSequence> apadaptor=ArrayAdapter.createFromResource(this,R.array.appartmentdetails,android.R.layout.simple_spinner_item);
        apadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apartmentdetails.setAdapter(apadaptor);
        apartmentdetails.setOnItemSelectedListener(this);

        durationofstay=findViewById(R.id.durationofstay);
        ArrayAdapter<CharSequence> dpadaptor=ArrayAdapter.createFromResource(this,R.array.durationofstay,android.R.layout.simple_spinner_item);
        dpadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationofstay.setAdapter(dpadaptor);
        durationofstay.setOnItemSelectedListener(this);

        String email=getIntent().getStringExtra("email");

        next=findViewById(R.id.next3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos= apartmentdetails.getSelectedItemPosition();//id of spinner
                String apartmentdetails[]=getResources().getStringArray(R.array.appartmentdetails);//string.xml
                String apartmentdetailsdb=apartmentdetails[pos];

                int pos1= durationofstay.getSelectedItemPosition();//id of spinner
                String durationofstay[]=getResources().getStringArray(R.array.durationofstay);//string.xml
                String durationofstaydb=durationofstay[pos1];

                if(apartmentdetailsdb.equals("") || durationofstaydb.equals(""))
                {
                    Toast.makeText(AddressDetailsForm.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean status=insertData(apartmentdetailsdb,durationofstaydb,true,email);
                    if (status == true) {
                        Toast.makeText(AddressDetailsForm.this, "Data Saved ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddressDetailsForm.this, IncomeDetailsForm.class);
                        i.putExtra("email",email);
                        startActivity(i);

                    } else {
                        Toast.makeText(AddressDetailsForm.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private boolean insertData(String apartmentdetailsdb, String durationofstaydb, boolean b, String email) {
        DBHelper db=new DBHelper(this);
        boolean status=db.insertAddDetails(apartmentdetailsdb,durationofstaydb,b,email);
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