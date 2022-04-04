package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class IncomeDetailsForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText employerdetails,year;
    Spinner occupations,frequencyofincome,paymentmethod,employertype,jobtype;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        employerdetails=findViewById(R.id.employerdetails);

        year=findViewById(R.id.year);

        occupations=findViewById(R.id.occupations);
        ArrayAdapter<CharSequence> oadaptor=ArrayAdapter.createFromResource(this,R.array.occupation,android.R.layout.simple_spinner_item);
        oadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupations.setAdapter(oadaptor);
        occupations.setOnItemSelectedListener(this);

        frequencyofincome=findViewById(R.id.frequencyofincome);
        ArrayAdapter<CharSequence> fadaptor=ArrayAdapter.createFromResource(this,R.array.frequencyofincome,android.R.layout.simple_spinner_item);
        fadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencyofincome.setAdapter(fadaptor);
        frequencyofincome.setOnItemSelectedListener(this);

        paymentmethod=findViewById(R.id.paymentmethod);
        ArrayAdapter<CharSequence> padaptor=ArrayAdapter.createFromResource(this,R.array.paymentmethod,android.R.layout.simple_spinner_item);
        padaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentmethod.setAdapter(padaptor);
        paymentmethod.setOnItemSelectedListener(this);

        employertype=findViewById(R.id.employertype);
        ArrayAdapter<CharSequence> eadaptor=ArrayAdapter.createFromResource(this,R.array.employertype,android.R.layout.simple_spinner_item);
        eadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employertype.setAdapter(eadaptor);
        employertype.setOnItemSelectedListener(this);

        jobtype=findViewById(R.id.jobtype);
        ArrayAdapter<CharSequence> finadaptor=ArrayAdapter.createFromResource(this,R.array.jobtype,android.R.layout.simple_spinner_item);
        finadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobtype.setAdapter(finadaptor);
        jobtype.setOnItemSelectedListener(this);


        String email=getIntent().getStringExtra("email");




        next=findViewById(R.id.next4);
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String employerdetailsdb=employerdetails.getText().toString();
                String yeardb=year.getText().toString();



                int pos= occupations.getSelectedItemPosition();//id of spinner
                String occupations[]=getResources().getStringArray(R.array.occupation);//string.xml
                String occupationsdb=occupations[pos];

                int pos1= frequencyofincome.getSelectedItemPosition();//id of spinner
                String frequencyofincome[]=getResources().getStringArray(R.array.frequencyofincome);//string.xml
                String frequencyofincomedb=frequencyofincome[pos1];

                int p= paymentmethod.getSelectedItemPosition();//id of spinner
                String paymentmethods[]=getResources().getStringArray(R.array.paymentmethod);//string.xml
                String paymentmethoddb=paymentmethods[p];

                int pos2= employertype.getSelectedItemPosition();//id of spinner
                String employertype[]=getResources().getStringArray(R.array.employertype);//string.xml
                String employertypedb=employertype[pos2];


                int pos3= jobtype.getSelectedItemPosition();//id of spinner
                String jobtype[]=getResources().getStringArray(R.array.jobtype);//string.xml
                String jobtypedb=jobtype[pos3];

                if(employerdetailsdb.equals("") || paymentmethoddb.contains("Select") || yeardb.equals("") || occupationsdb.contains("Select") || frequencyofincomedb.contains("Select") || employertypedb.contains("Select") || jobtypedb.contains("Select"))
                {
                    Toast.makeText(IncomeDetailsForm.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    boolean status=insertData(occupationsdb,frequencyofincomedb,paymentmethoddb,employertypedb,jobtypedb,employerdetailsdb,Integer.parseInt(yeardb),true,email);
                    if (status == true) {
                        Toast.makeText(IncomeDetailsForm.this, "Data Saved ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(IncomeDetailsForm.this,BankDetailsForm.class);
                        i.putExtra("email",email);
                        startActivity(i);

                    } else {
                        Toast.makeText(IncomeDetailsForm.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private boolean insertData(String occupationsdb, String frequencyofincomedb, String paymentmethoddb, String employertypedb, String jobtypedb, String employerdetailsdb, int yeardb, boolean b, String email) {
    DBHelper db=new DBHelper(this);
    boolean status=db.insertIncomeDetails(occupationsdb,frequencyofincomedb,paymentmethoddb,employertypedb,jobtypedb,employerdetailsdb,yeardb,b,email);
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