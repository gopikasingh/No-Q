package com.example.noq;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.parse.ParseUser;

import java.util.UUID;

public class profile2 extends AppCompatActivity {
    private Button button15;
    private ImageView imageView6;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        TextView textView15 = findViewById(R.id.textView15);
        TextView textView16 = findViewById(R.id.textView16);

        imageView6 =findViewById(R.id.imageView5);

        textView15.setText(("Username: " + ParseUser.getCurrentUser().get("username")));
        textView16.setText(("Email: \n" + ParseUser.getCurrentUser().get("email")));



        /*ParseUser user = ParseAuth.getInstance().getCurrentUser();


            if (data.getData() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .into(imageView6);
            }*/

        FirebaseApp.initializeApp(this);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        button15 = (Button) findViewById(R.id.button18);

        button15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity();

            }

        });

        imageView6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                choosePicture();
            }});
    }

    private void choosePicture() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK &&data!=null && data.getData()!=null){
            imageUri=data.getData();
            imageView6.setImageURI(imageUri);
            uploadPicture();
        }

    }

    private void uploadPicture() {
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

        final ProgressBar pb = new ProgressBar(this);
        pb.setTransitionName("Uploading Image...");
        pb.setVisibility(View.VISIBLE);

        // final ProgressDialog pd=new ProgressDialog(this);
        //pd.setTitle("Uploading image...");
        //pd.show();


        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_LONG).show();


                    }
                })




                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());

                        // pb.setTransitionName("Percentage: " + (int) progressPercent + "%");
                    }
                });
    }

    public void openMainActivity() {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}