package com.example.noq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

public class inshop extends AppCompatActivity {

    TextView shopname, addres, email, first, pin, phone;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;
    ImageView img;
    StorageReference ref;
    DatabaseReference mDatabase;
    ProgressBar progressBar30;
    Button buttongoto;
    Button button45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inshop);

        first = findViewById(R.id.textfn);
        //last = findViewById(R.id.textln);
        //city = findViewById(R.id.textcity2);
        //state = findViewById(R.id.textstate);
        //country = findViewById(R.id.textcountry);
        pin = findViewById(R.id.textpin);
        shopname = findViewById(R.id.Textshopp);
        addres = findViewById(R.id.Textaddress);
        phone = findViewById(R.id.Textphone);
        email = findViewById(R.id.textemaill);
        buttongoto = findViewById(R.id.buttongoto);

        progressBar30 = (ProgressBar) findViewById(R.id.progressbar30);
        progressBar30.setVisibility(View.INVISIBLE);

        img = findViewById(R.id.new_post_image);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();


        ref = FirebaseStorage.getInstance().getReference();
        StorageReference localRef = ref.child("users/"+fauth.getCurrentUser().getUid()+"/shopimage.jpg");  //every user has their own image
        localRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //progressBar3.setVisibility(View.INVISIBLE);
                Picasso.get().load(uri).fit().centerCrop().into(img); /////////////////////////////////////////////
                //String uris= uri.toString();
                //mDatabase.child("shops").child(fauth.getCurrentUser().getUid()).setValue(uri);
                //mDatabase.child("shops").child(fauth.getCurrentUser().getUid()+"/shopImage").setValue(uris);
            }
        });

        userid = fauth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("userinfo").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("TAG","Error:"+error.getMessage());
                }
                else {
                    first.setText(value.getString("fn")+" "+value.getString("ln"));
                    //last.setText(value.getString("ln"));
                    //city.setText(value.getString("city"));
                    //state.setText(value.getString("state"));
                    //country.setText(value.getString("cc"));
                    pin.setText("Pincode: "+value.getString("pin"));
                    shopname.setText(value.getString("sn"));
                    addres.setText("Address: "+value.getString("add")+",\n"+
                            value.getString("city")+", "+value.getString("state")
                            +",\n"+
                            value.getString("cc"));
                    phone.setText("Contact Info. -"+"\nPhone: "+value.getString("phone"));
                    email.setText("E-mail: "+value.getString("em"));
                }

            }
        });

        buttongoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadditem();
            }
        });




        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open galary
                progressBar30.setVisibility(View.VISIBLE);
                Intent opengalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //EXTERNAL_CONTENT_URI returns uri of the selected image
                startActivityForResult(opengalIntent, 1000);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            if(resultCode == Activity.RESULT_OK) {
                Uri imageuri = data.getData();
                img.setImageURI(imageuri);

                uploadimafetofirebase(imageuri);


            }
        }
    }

    public void uploadimafetofirebase(Uri imageuri) {
        final StorageReference file = ref.child("users/"+fauth.getCurrentUser().getUid()+"/shopimage.jpg");
        file.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //add toast for success
                FancyToast.makeText(inshop.this,"Image uploaded on database",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //progressBar3.setVisibility(View.INVISIBLE);
                        Picasso.get().load(uri).into(img);
                        String uris= uri.toString();
                        //mDatabase.child("shops").child(fauth.getCurrentUser().getUid()).setValue(uri);
                        mDatabase.child("shops").child(fauth.getCurrentUser().getUid()+"/shopImage").setValue(uris);
                    }
                });
                progressBar30.setVisibility(View.INVISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //add toat for failure "image not uploaded to database"
                FancyToast.makeText(inshop.this,"image not uploaded to database",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                progressBar30.setVisibility(View.INVISIBLE);

                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressBar30.setVisibility(View.INVISIBLE);
                        Picasso.get().load(uri).into(img );

                    }
                });
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        gotologin();
    }

    public void gotologin() {
        Intent intent = new Intent(this, next.class);
        startActivity(intent);
    }

    public void openadditem() {
        Intent intent = new Intent(this, itemlist.class);
        startActivity(intent);
    }
}