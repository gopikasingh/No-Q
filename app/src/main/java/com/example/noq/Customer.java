package com.example.noq;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Customer extends AppCompatActivity {
    private Button button8;
    private Button button6;
    private EditText edtl_username, edtl_pass;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        button8 = (Button) findViewById(R.id.button8);
        button6 = (Button) findViewById(R.id.button6);

        edtl_username = findViewById(R.id.editTextTextPersonName);
        edtl_pass = findViewById(R.id.editTextTextPassword2);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);
        progressBar2.setVisibility(View.INVISIBLE);


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
                progressBar2.setVisibility(View.VISIBLE);
                (edtl_pass).setError(null);

                // Login with Parse
                ParseUser.logInInBackground(edtl_username.getText().toString(), edtl_pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            if(parseUser.getBoolean("emailVerified")) {
                                progressBar2.setVisibility(View.INVISIBLE);
                                FancyToast.makeText(Customer.this,"Login Successful"+edtl_username.getText().toString(),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                alertDisplayer("Login Sucessful", "Welcome, " + edtl_username.getText().toString() + "!", false);
                                //for more fancy toast messages -> https://github.com/Shashank02051997/FancyToast-Android
                            }
                            else
                            {
                                ParseUser.logOut();
                                progressBar2.setVisibility(View.INVISIBLE);
                                alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                            }
                        } else {
                            YoYo.with(Techniques.Shake)
                                    .duration(500)
                                    .repeat(0)
                                    .playOn(button6);


                            ParseUser.logOut();
                            progressBar2.setVisibility(View.INVISIBLE);
                            alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                        }


                    }
                });




            }

        });
    }
    public void openCustnewacc() {
        Intent intent = new Intent(this, Custnewacc.class);
        startActivity(intent);
    }
    public void opendemoshop() {
        Intent intent = new Intent(this, cardmain.class);
        startActivity(intent);
    }
    //public void loginispressed(View v){



    //}

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
    }

}


