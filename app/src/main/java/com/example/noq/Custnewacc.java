package com.example.noq;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


import android.app.ProgressDialog;

import java.util.HashMap;
import java.util.Map;


public class Custnewacc extends AppCompatActivity {

    public static final String TAG = "TAG";
    private Button button9;
    private Button button10;
    private EditText edtname, edtusername, edtpass, edtemail, etdconpass;
    private ProgressBar progressBar;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userIDofcust;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custnewacc);

        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);

        edtname = findViewById(R.id.editTextTextPersonName10);
        edtusername = findViewById(R.id.editTextTextPersonName4);
        edtpass = findViewById(R.id.editTextTextPassword4);
        edtemail = findViewById(R.id.editTextTextPersonName6);
        etdconpass = findViewById(R.id.editTextTextPassword5);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloginpage();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String custname = edtname.getText().toString();
                final String custusername = edtusername.getText().toString();
                final String edpass = edtpass.getText().toString();
                final String conpass = etdconpass.getText().toString();
                final String custmail = edtemail.getText().toString();

                if(TextUtils.isEmpty(custname)) {
                    edtname.setError("Please enter your name");
                    return;
                }

                if(TextUtils.isEmpty(custusername)) {
                    edtusername.setError("Please enter your username");
                    return;
                }

                if(TextUtils.isEmpty(custmail)) {
                    edtemail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(edpass)) {
                    edtpass.setError("Invalid Password!");
                    return;
                }
                if(edpass.length() < 7) {
                    edtpass.setError("Password must be atleast seven characters long");
                    return;
                }

                if(!edpass.equals(etdconpass.getText().toString())) {
                    etdconpass.setError("Password does not match");
                    return;
                }

                signupispressed();
                progressBar.setVisibility(View.VISIBLE);

                //register user
                fauth.createUserWithEmailAndPassword(custmail, edpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FancyToast.makeText(Custnewacc.this,""+custname+" added successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                            userIDofcust = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Shopperinfo").document(userIDofcust);

                            Map<String, Object> shopperuser = new HashMap<>(); //hash map to create data
                            shopperuser.put("name", custname);
                            shopperuser.put("username", custusername);
                            shopperuser.put("email", custmail);
                            shopperuser.put("password", edpass);

                            //adding to realtimedatabase
                            documentReference.set(shopperuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User profile is created for "+userIDofcust); //ctrl+alt+C to make "TAG" -> constant

                                }
                            });

                            mDatabase.child("customer").child(userIDofcust).setValue(shopperuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Custnewacc.this, "Uploading to the database is done", Toast.LENGTH_SHORT).show();
                                    //Intent mainIntent = new Intent(shoplog.this, itemlist.class);
                                    //startActivity(mainIntent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Custnewacc.this, "Problem in registuring the information", Toast.LENGTH_SHORT).show();
                                }
                            });

                            aftersignup();



                        } else {
                            FancyToast.makeText(Custnewacc.this,"Can't add "+custname +", Please try again later \n" +task.getException().getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

                /*try {
                    // Reset errors
                    // Sign up with Parse
                    ParseUser user = new ParseUser();

                    user.setUsername(edtusername.getText().toString());
                    user.setPassword(edtpass.getText().toString());
                    user.setEmail(edtemail.getText().toString());

                    //ProgressBar progressBar = new ProgressBar(Custnewacc.this, null, android.R.attr.progressBarStyle);
                    //progressBar.setMessage("Signing up " + edtusername.getText().toString());
                    //progressDialog.show();



                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                ParseUser.logOut();
                                progressBar.setVisibility(View.INVISIBLE);
                                alertDisplayer("Account Created Successfully!", "Please verify your email before Login", false);
                            } else {
                                ParseUser.logOut();
                                progressBar.setVisibility(View.INVISIBLE);
                                alertDisplayer("Error Account Creation failed", "Account could not be created" + " :" + e.getMessage(), true);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

            }
        });
    }



    public void signupispressed() {
        Toast.makeText(this,"Signed in successfully", Toast.LENGTH_SHORT).show();
    }

    public void aftersignup() {
        Intent intent = new Intent(this, cardmain.class);
        startActivity(intent);
    }

    public void openloginpage() {
        Intent intent = new Intent(this, Customer.class);
        startActivity(intent);
    }

    /*private void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(Custnewacc.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(Custnewacc.this, Customer.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }*/

}