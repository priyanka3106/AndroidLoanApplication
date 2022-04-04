package com.example.loanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.view.View;
import android.provider.MediaStore;
import android.Manifest;
import android.widget.Button;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.net.Uri;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import android.os.Bundle;
import android.widget.Toast;


public class UploadSelectImage extends AppCompatActivity {

    ImageView imgToShow;
    Button SelectImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_select_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email=getIntent().getStringExtra("email");
        String docType=getIntent().getStringExtra("docType");

        imgToShow=findViewById(R.id.imageToshow);
        imgToShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePicture();
            }
        });

        SelectImg=findViewById(R.id.selectImg);
        SelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(email,docType);
            }
        });
    }

    private void saveUser(String email,String docType) {
        byte[] bytesPP = convertImageViewToByteArray(imgToShow);
        DBHelper db=new DBHelper(this);
        boolean status=false;

        if(docType.equals("AadherCard")){
            status=db.insertKYCAadherCard(bytesPP,true,email);
        }else if(docType.equals("PanCard")){
            status=db.insertKYCPanCard(bytesPP,true,email);
        }else if(docType.equals("Bank")){
            status=db.insertKYCBank(bytesPP,true,email);
        }else if(docType.equals("Selfie")){
            status=db.insertKYCSelfie(bytesPP,true,email);
        }



        if(status){
            Toast.makeText(UploadSelectImage.this, "successfully saved", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(UploadSelectImage.this,KYCDashboard.class);
            i.putExtra("email",email);
            startActivity(i);
        }else{
            Toast.makeText(UploadSelectImage.this, "Not able to saved", Toast.LENGTH_SHORT).show();
        }

    }

    private byte[] convertImageViewToByteArray(ImageView imgToShow) {
        Bitmap bitmap = ((BitmapDrawable) imgToShow.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void chooseProfilePicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadSelectImage.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_profile_picture, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageViewADPPCamera = dialogView.findViewById(R.id.imageViewADPPCamera);
        ImageView imageViewADPPGallery = dialogView.findViewById(R.id.imageViewADPPGallery);

        final AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();

        imageViewADPPCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAndRequestPermissions()) {
                    takePictureFromCamera();
                    alertDialogProfilePicture.cancel();
                }
            }
        });

        imageViewADPPGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.cancel();
            }
        });
    }

    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePictureFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, 2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    imgToShow.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    imgToShow.setImageBitmap(bitmapImage);
                }
                break;
        }
    }
    private boolean checkAndRequestPermissions() {
        if(Build.VERSION.SDK_INT >= 23){
            int cameraPermission = ActivityCompat.checkSelfPermission(UploadSelectImage.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(UploadSelectImage.this, new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }
}