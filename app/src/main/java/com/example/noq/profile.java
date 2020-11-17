package com.example.noq;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import com.parse.ParseUser;
import android.net.Uri;
import android.graphics.Bitmap;






public class profile extends AppCompatActivity implements View.OnClickListener {
    private Button button11;
    //private TextView textView15;
     //private TextView textView16;

    private ImageButton imageButton7;
    private ImageButton imageButton8;
    private static final int CAMERA_IMAGE_REQUEST_CODE = 1000;

    private Bitmap bitmap;
    private ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView15 = findViewById(R.id.textView15);
        TextView textView16 = findViewById(R.id.textView16);

        ImageButton imageButton7 = findViewById(R.id.imageButton755);
        ImageButton imageButton8 = findViewById(R.id.imageButton855);

        imageButton7.setOnClickListener(/*(View.OnClickListener)*/ profile.this);
        imageButton8.setOnClickListener(profile.this);

        textView15.setText(("Username: " + ParseUser.getCurrentUser().get("username")));
        textView16.setText(("Email: " + ParseUser.getCurrentUser().get("email")));

        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(); }});
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.imageButton755) {


            int permissionResult = ContextCompat.checkSelfPermission(profile.this, Manifest.permission.CAMERA);
            if (permissionResult == PackageManager.PERMISSION_GRANTED) {


                PackageManager packageManager = getPackageManager();
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_IMAGE_REQUEST_CODE);
                } else {

                    Toast.makeText(profile.this, "Your device does not have a camera", Toast.LENGTH_SHORT).show();

                }

            } else {

                ActivityCompat.requestPermissions(profile.this,
                        new String[]{Manifest.permission.CAMERA},
                        1);

            }

        } else if (v.getId() == R.id.imageButton855) {

         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(profile.this, "Picture Taken", Toast.LENGTH_SHORT).show();

       /* if (requestCode == CAMERA_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();

            bitmap = (Bitmap) bundle.get("data");
           imageView5.setImageBitmap(bitmap);
            }*/
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
