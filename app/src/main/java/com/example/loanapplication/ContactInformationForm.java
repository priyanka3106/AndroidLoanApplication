package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactInformationForm extends AppCompatActivity {

    EditText addressline1,addressline2,city,state,postalcode;

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addressline1=findViewById(R.id.addressline1);
        addressline2=findViewById(R.id.addressline2);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        postalcode=findViewById(R.id.postalcode);
        next=findViewById(R.id.next2);
        String email=getIntent().getStringExtra("email");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addressline1db=addressline1.getText().toString();
                String addressline2db=addressline2.getText().toString();
                String citydb=city.getText().toString();
                String statedb=state.getText().toString();
                String postalcodedb=postalcode.getText().toString();
                if(addressline1db.equals("") || addressline2db.equals("")|| citydb.equals("") || statedb.equals("") || postalcodedb.equals(""))
                {

                    Toast.makeText(ContactInformationForm.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean status=insertData(addressline1db+addressline2db,citydb,statedb,postalcodedb,true,email);
                    if (status == true) {
                        Toast.makeText(ContactInformationForm.this, "Data Saved ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ContactInformationForm.this, AddressDetailsForm.class);
                        i.putExtra("email",email);
                        startActivity(i);

                    } else {
                        Toast.makeText(ContactInformationForm.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean insertData(String address, String citydb, String statedb, String postalcodedb, boolean isCompleted, String email) {
        DBHelper db=new DBHelper(this);
        boolean status=db.insertContactDetail(address,citydb,statedb,postalcodedb,isCompleted,email);
        db.close();
        return status;
    }
}