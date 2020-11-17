package com.example.noq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.parse.ParseUser;

import java.util.HashMap;

public class feedback extends AppCompatActivity {
    ImageButton imbtn00;  //back button

    TextView shopname;  //shop name ke lie
    // private RatingBar ratingBar;
    EditText multiline1;   // writing feedback in multiline text
    Button button00;     // button for sending (send button)
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;
    TextView tvFeedback;
    RatingBar rbStars;
    ImageButton backtheactivity;
    StorageReference m;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        imbtn00=findViewById(R.id.imbtn00);      //back button

        //userid=getIntent().getStringExtra("userid");

        shopname=findViewById(R.id.textView00);   //shop name ke lie
        multiline1=findViewById(R.id.multiline1);   // writing feedback in multiline text
        button00=findViewById(R.id.button00);   // button for sending (send button)
        fauth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        m = FirebaseStorage.getInstance().getReference();
        // userid=getIntent().getStringExtra("userid");
        userid = fauth.getCurrentUser().getUid();




        loadMyFeedback();
        loadShopInfo();

        tvFeedback = findViewById(R.id.textView17);
        rbStars = findViewById(R.id.ratingBar);    // rating bar
        backtheactivity=(ImageButton) findViewById(R.id.imbtn00);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0)
                {
                    tvFeedback.setText("Very Dissatisfied");
                }
                else if(rating==1)
                {
                    tvFeedback.setText("Dissatisfied");
                }
                else if(rating==2 || rating==3 || rating==3.5)
                {
                    tvFeedback.setText("OK");
                }
                else if(rating==4)
                {
                    tvFeedback.setText("Satisfied");
                }
                else if(rating==5)
                {
                    tvFeedback.setText("Very Satisfied");
                }
                else
                {

                }
            }
        });



        imbtn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendemoshop();
            }
        });

        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });





    }

    private void loadShopInfo() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("customer");
        ref.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String shopName=""+dataSnapshot.child("shopName").getValue();
                shopname.setText(shopName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void loadMyFeedback() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("customer");
        ref.child(userid).child("Ratings").child(fauth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String uid=""+dataSnapshot.child("uid").getValue();
                            String ratings=""+dataSnapshot.child("ratings").getValue();
                            String feedback=""+dataSnapshot.child("feedback").getValue();
                            String timestamp=""+dataSnapshot.child("timestamp").getValue();

                            float myRating=Float.parseFloat(ratings);
                            rbStars.setRating(myRating);
                            multiline1.setText(feedback);
                        }}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void opendemoshop() {
        Intent intent = new Intent(this, demoshop.class);

        startActivity(intent);}

    public void inputData(){
        String ratings=""+rbStars.getRating();
        String feedback=multiline1.getText().toString().trim();

        String timestamp=""+System.currentTimeMillis();

        HashMap<String, Object>hashMap=new HashMap<>();
        hashMap.put("uid",""+ fauth.getUid());
        hashMap.put("ratings",""+ ratings);
        hashMap.put("feedback",""+ feedback);
        hashMap.put("timestamp",""+ timestamp);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("customer");
        ref.child(userid).child("Ratings").child(fauth.getUid()).updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(feedback.this,"Feedback is saved in database yuhuuu",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(feedback.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }
}