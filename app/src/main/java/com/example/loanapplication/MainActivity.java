package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText FirstNameTxt,LastNameTxt,EmailTxt,PhnTxt,DobTxt;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirstNameTxt=findViewById(R.id.FirstNameText);
        LastNameTxt=findViewById(R.id.LastNameText);
        EmailTxt=findViewById(R.id.EmailIDText);
        PhnTxt=findViewById(R.id.MobileNumberText);
        DobTxt=findViewById(R.id.DateofBirthText);
        submit=findViewById(R.id.button);
        DBHelper db=new DBHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DobTxt.setInputType(InputType.TYPE_CLASS_NUMBER);//phone number variable

                String firstnamedb=FirstNameTxt.getText().toString();
                String lastnamedb=LastNameTxt.getText().toString();
                String emaildb=EmailTxt.getText().toString();
                String passworddb=PhnTxt.getText().toString();
                String mobiledb=DobTxt.getText().toString();

                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";//reg for email
                String passwordRegex = "^(?=.*[0-9])"//reg for password
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern p = Pattern.compile(passwordRegex);
                if(firstnamedb.equals("")|| lastnamedb.equals("") || emaildb.equals("")|| passworddb.equals("")||mobiledb.equals("")){
                    Toast.makeText(MainActivity.this,"Please fill all the deatils",Toast.LENGTH_SHORT).show();
                }else if(!emaildb.matches(regex)){
                    Toast.makeText(MainActivity.this,"Email is not correct",Toast.LENGTH_SHORT).show();
                }else if(!passworddb.matches(passwordRegex)){
                    Toast.makeText(MainActivity.this,"Passowrd is Weak!! Make it Strong",Toast.LENGTH_SHORT).show();
                }else if(mobiledb.length()<10){
                    Toast.makeText(MainActivity.this,"Please enter valid mobile no",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean status=db.insertUserDetail(firstnamedb,lastnamedb, mobiledb,passworddb,emaildb);

                    if(status==true)
                    {
                        Toast.makeText(MainActivity.this,"Sucessfully",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,LoginMain.class);
                        startActivity(i);
                        db.close();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Unsucessfully",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    private void sendEmail(String emaildb,String password)
    {
        final String username="piyapatil311298@gmail.com";
        final String password1="Neela@1234";
    Properties props=new Properties();
    props.put("mail.smtp.auth","true");
    props.put("mail.smtp.starttls.enable",true);
    props.put("mail.smtp.host","smtp.gmail.com");
    props.put("mail.smtp.port","587");
    Session session=Session.getInstance(props,
            new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password1);
                }
            });
    try
    {
        Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emaildb));
        message.setSubject("Username and Password ");
        message.setText("Username is"+emaildb+"Password"+password);
        Transport.send(message);
        Toast.makeText(MainActivity.this,"Email Send Sucessfully",Toast.LENGTH_LONG).show();
    }
    catch(MessagingException e)
    {
        throw new RuntimeException(e);
    }
    }
}