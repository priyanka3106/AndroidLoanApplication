package com.example.loanapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loanapplication.Database.PersonalDatabase;

import java.lang.reflect.Array;

public class PersonalInformationForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView date;
    EditText firstName,lastName,DateOfBirth;
    RadioGroup Gender;
    RadioButton GenderBtn;
    Spinner nationlity,mstatus;
    CalendarView calendarView;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        DateOfBirth = findViewById(R.id.datofbirth);
        Gender = findViewById(R.id.gender);

        DBHelper db = new DBHelper(this);
        String email = getIntent().getStringExtra("email");


        getData(email);


        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                String date = d + "/" + m + "/" + y;
                DateOfBirth.setText(date);
            }
        });


        nationlity = findViewById(R.id.NationalitySpin);
        ArrayAdapter<CharSequence> nadaptor = ArrayAdapter.createFromResource(this, R.array.nationlity, android.R.layout.simple_spinner_item);
        nadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationlity.setAdapter(nadaptor);
        nationlity.setOnItemSelectedListener(this);


        mstatus = findViewById(R.id.MaritalStatus);
        ArrayAdapter<CharSequence> madaptor = ArrayAdapter.createFromResource(this, R.array.mstatus, android.R.layout.simple_spinner_item);
        madaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mstatus.setAdapter(madaptor);
        mstatus.setOnItemSelectedListener(this);




        next = findViewById(R.id.next1);
        //PersonalDatabase pdb=new PersonalDatabase (this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstnamedb = firstName.getText().toString();
                String lastnamedb = lastName.getText().toString();
                String dateofbirthdb = DateOfBirth.getText().toString();

                int selectedGender=Gender.getCheckedRadioButtonId();
                GenderBtn=findViewById(selectedGender);
                String genderdb=GenderBtn.getText().toString();

                int pos = nationlity.getSelectedItemPosition();//id of spinner
                String nationlities[] = getResources().getStringArray(R.array.nationlity);//string.xml
                String nationlitydb = nationlities[pos];

                int pos1 = mstatus.getSelectedItemPosition();
                String mstatus[] = getResources().getStringArray(R.array.mstatus);
                String mstatusdb = mstatus[pos1];

                if (firstnamedb.equals("") || lastnamedb.equals("") || dateofbirthdb.equals("") || nationlitydb.contains("Select") || mstatusdb.contains("Select")) {
                    Toast.makeText(PersonalInformationForm.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    boolean status = db.insertPersonalInformation(firstnamedb, lastnamedb, dateofbirthdb, genderdb, nationlitydb, mstatusdb, true, email);
                    //Boolean status=db.insertPersonalInformation(firstnamedb,lastnamedb,dateofbirthdb,nationlitydb,mstatusdb,true,email);

                    if (status == true) {
                        Toast.makeText(PersonalInformationForm.this, "Data Saved " + firstnamedb + " " + genderdb, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PersonalInformationForm.this, ContactInformationForm.class);
                        i.putExtra("email",email);
                        startActivity(i);

                    } else {
                        Toast.makeText(PersonalInformationForm.this, "Data Not Saved " , Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });




    }
    private void getData(String email) {
        DBHelper db=new DBHelper(this);

        Cursor result= db.getUserDetailUsername(email);
        while(result.moveToNext()){
            firstName.setText(result.getString(0));
            lastName.setText(result.getString(1));
        }
        db.close();

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String mstatus=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

    /*private boolean insertData(String firstnamedb,String lastnamedb,String dateofbirthdb,String genderdb,String nationlitydb,String mstatusdb,Boolean isCompleted,String email) {
        DBHelper db1=new DBHelper(this);
        boolean status=db1.insertPersonalInformation(firstnamedb,lastnamedb,dateofbirthdb,genderdb,nationlitydb,mstatusdb,isCompleted,email);

        db1.close();
        return status;
    }




*/