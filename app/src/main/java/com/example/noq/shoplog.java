package com.example.noq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

public class shoplog extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText editTextfirst, editTextlast,
            editTextshopname, editTextcountry, editTextstate, editTextcity,
            editTextpincode, editTextstreedaddress, editTextphonenumber,
            editTextemail, editTextTextPassword, editTextTextconfirmPassword3;
    FirebaseAuth fauth;
    private Button button44;
    private Button button55;
    FirebaseFirestore fstore;
    String userID;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplog);

        editTextfirst = findViewById(R.id.editTextfirst);
        editTextlast = findViewById(R.id.editTextlast);
        //editTextshopkey = findViewById(R.id.editTextshopkey);
        editTextshopname = findViewById(R.id.editTextshopname);
        editTextcountry = findViewById(R.id.editTextcountry);
        editTextstate = findViewById(R.id.editTextstate);
        editTextcity = findViewById(R.id.editTextcity);
        editTextpincode = findViewById(R.id.editTextpincode);
        editTextstreedaddress = findViewById(R.id.editTextstreedaddress);
        editTextphonenumber = findViewById(R.id.editTextphonenumber);
        editTextemail = findViewById(R.id.editTextemail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextconfirmPassword3 = findViewById(R.id.editTextTextconfirmPassword3);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button44 = (Button) findViewById(R.id.button44);

        button55 = (Button) findViewById(R.id.button55);



        if (fauth.getCurrentUser() != null) {
            openentershop();
            finish();

        }

        button55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotologin();

            }
        });

        button44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn = editTextfirst.getText().toString();
                final String ln = editTextlast.getText().toString();
                //final String sk = editTextshopkey.getText().toString();
                final String shopname = editTextshopname.getText().toString();
                final String cc = editTextcountry.getText().toString();
                final String state = editTextstate.getText().toString();
                final String city = editTextcity.getText().toString();
                final String pin = editTextpincode.getText().toString();
                final String add = editTextstreedaddress.getText().toString();
                final String pass = editTextTextPassword.getText().toString();
                final String em = editTextemail.getText().toString();
                final String phone = editTextphonenumber.getText().toString();


                if(TextUtils.isEmpty(em)) {
                    editTextemail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(fn)) {
                    editTextfirst.setError("Please enter your name");
                    return;
                }

                if(TextUtils.isEmpty(shopname)) {
                    editTextshopname.setError("Please enter your shop name");
                    return;
                }

                if(TextUtils.isEmpty(cc)) {
                    editTextcountry.setError("Please enter country");
                    return;
                }

                if(TextUtils.isEmpty(state)) {
                    editTextstate.setError("Please enter your state");
                    return;
                }

                if(TextUtils.isEmpty(city)) {
                    editTextcity.setError("Please enter your city");
                    return;
                }

                if(TextUtils.isEmpty(pin)) {
                    editTextpincode.setError("Please enter your pin");
                    return;
                }

                if(TextUtils.isEmpty(add)) {
                    editTextstreedaddress.setError("Please enter your address");
                    return;
                }

                if(TextUtils.isEmpty(pass)) {
                    editTextTextPassword.setError("Invalid Password!");
                    return;
                }

                if(pass.length() < 7) {
                    editTextTextPassword.setError("Password must be atleast seven characters long");
                    return;
                }

                if(!pass.equals(editTextTextconfirmPassword3.getText().toString())) {
                    editTextTextconfirmPassword3.setError("Password does not match");
                    return;
                }

                //register shop
                fauth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FancyToast.makeText(shoplog.this,""+editTextshopname.getText().toString()+" added successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            userID = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("userinfo").document(userID);

                            Map<String, Object> user = new HashMap<>(); //hash map to create data
                            user.put("fn", fn);
                            user.put("ln", ln);
                            //user.put("sk", sk);
                            user.put("sn", shopname);
                            user.put("cc", cc);
                            user.put("state", state);
                            user.put("city", city);
                            user.put("pin", pin);
                            user.put("add", add);
                            user.put("em", em);
                            user.put("pass", pass);
                            user.put("phone", phone);




                            //next step https://youtu.be/pAhYEy6s9wQ

                            //adding to realtimedatabase
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "user profile is created for "+userID); //ctrl+alt+C to make "TAG" -> constant

                                }
                            });

                            mDatabase.child("shops").child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(shoplog.this, "Uploading to the database is done", Toast.LENGTH_SHORT).show();
                                    //Intent mainIntent = new Intent(shoplog.this, itemlist.class);
                                    //startActivity(mainIntent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(shoplog.this, "Problem in registuring the information", Toast.LENGTH_SHORT).show();
                                }
                            });


                            openentershop();
                        } else {
                            FancyToast.makeText(shoplog.this,"Can't add "+editTextshopname.getText().toString()+", Please try again later " +task.getException().getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

            }
        });


    }

    public void openentershop() {
        Intent intent = new Intent(this, inshop.class);
        startActivity(intent);
    }
    public void gotologin() {
        Intent intent = new Intent(this, next.class);
        startActivity(intent);
    }

}
/*
*
<ProgressBar
        android:id="@+id/progressbar"
        style="@android:style/Widget.Material.Light.ProgressBar"
        android:layout_width="match_parent"
        android:layout_height="744dp"
        android:background="@color/grey"
        android:indeterminate="true"
        android:indeterminateTint="@color/ic_launcher_background"
        android:max="100"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.555"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
*/