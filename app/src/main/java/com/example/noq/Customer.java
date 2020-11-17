package com.example.noq;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Customer extends AppCompatActivity {
    private Button button8;
    private Button button6;
    private EditText edtl_email, edtl_pass;
    public ProgressBar progressBar7;
    FirebaseAuth fauth;
    //private RecyclerView recyclerView;
    //Adapter adapter; // Create Object of the Adapter class
    //DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        button8 = (Button) findViewById(R.id.button8);
        button6 = (Button) findViewById(R.id.button6);

        edtl_email = findViewById(R.id.editTextTextPersonName);
        edtl_pass = findViewById(R.id.editTextTextPassword2);

        progressBar7 = (ProgressBar) findViewById(R.id.progressbar7);
        progressBar7.setVisibility(View.INVISIBLE);


        fauth = FirebaseAuth.getInstance();

        // Create a instance of the database and get
        // its reference
        /*mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));*/

        //FirebaseRecyclerOptions<person> options
        //= new FirebaseRecyclerOptions.Builder<person>()
        //.setQuery(mbase, person.class)
        //.build();

        /*final FirebaseRecyclerOptions<shopgetter> options =
                new FirebaseRecyclerOptions.Builder<shopgetter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shops"), shopgetter.class)
                        .build();*/

        // Connecting object of required Adapter class to
        // the Adapter class itself
        //adapter = new Adapter(options);
        // Connecting Adapter class with the Recycler view*/
        //recyclerView.setAdapter(adapter);


        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCustnewacc();

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opendemoshop();
                // Reset errors
                progressBar7.setVisibility(View.VISIBLE);
                (edtl_pass).setError(null);

                String mail = edtl_email.getText().toString();
                String password = edtl_pass.getText().toString();

                if(TextUtils.isEmpty(mail)) {
                    edtl_email.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    edtl_pass.setError("Invalid Password!");
                    return;
                }


                fauth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar7.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            FancyToast.makeText(Customer.this,"Welcome!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                            eeeeentershop();
                        }else {
                            FancyToast.makeText(Customer.this,"Please try again later \n" +task.getException().getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }

                    }
                });

                // Login with Parse
                /*ParseUser.logInInBackground(edtl_username.getText().toString(), edtl_pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            progressBar7.setVisibility(View.INVISIBLE);
                            if(parseUser.getBoolean("emailVerified")) {

                                FancyToast.makeText(Customer.this,"Login Successful"+edtl_username.getText().toString(),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                alertDisplayer("Login Sucessful", "Welcome, " + edtl_username.getText().toString() + "!", false);
                                //for more fancy toast messages -> https://github.com/Shashank02051997/FancyToast-Android
                            }
                            else
                            {
                                ParseUser.logOut();
                                //progressBar7.setVisibility(View.INVISIBLE);
                                alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                            }
                        } else {
                            YoYo.with(Techniques.Shake)
                                    .duration(500)
                                    .repeat(0)
                                    .playOn(button6);


                            ParseUser.logOut();
                            //progressBar7.setVisibility(View.INVISIBLE);
                            alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                        }


                    }
                });*/




            }

        });
    }
    public void openCustnewacc() {
        Intent intent = new Intent(this, Custnewacc.class);
        startActivity(intent);
    }
    public void eeeeentershop() {
        Intent intent = new Intent(this, cardmain.class);
        startActivity(intent);
    }
    //public void loginispressed(View v){



    //}
    /*
    public void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(Customer.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(Customer.this, cardmain.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }*/

}