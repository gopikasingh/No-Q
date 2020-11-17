package com.example.noq;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signup extends AppCompatActivity {
    private Button button5, button4;
    private ProgressBar progressBar;
    //button4 is for signup
    private EditText editTextfirstname, editTextlastname, editTextusername,
            editTextshopname, editTextcountry, editTextstate, editTextcity, editTextpincode,
            editTextstreedaddress, editTextphonenumber, editTextemail, editTextTextPassword,
            editTextTextconfirmPassword3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");

        editTextfirstname = findViewById(R.id.editTextfirstname);
        editTextlastname = findViewById(R.id.editTextlastname);
        editTextusername = findViewById(R.id.editTextusername);
        //editTextshopname = findViewById(R.id.editTextshopname);
        //editTextcountry = findViewById(R.id.editTextcountry);
        //editTextstate = findViewById(R.id.editTextstate);
        //editTextcity = findViewById(R.id.editTextcity);
        //editTextpincode = findViewById(R.id.editTextpincode);
        //editTextstreedaddress = findViewById(R.id.editTextstreedaddress);
        //editTextphonenumber = findViewById(R.id.editTextphonenumber);
        editTextemail = findViewById(R.id.editTextemail);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextconfirmPassword3 = findViewById(R.id.editTextTextconfirmPassword3);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        button4 = (Button) findViewById(R.id.button4);

        /*if (ParseUser.getCurrentUser() != null){ //if already logged in
            ParseUser.getCurrentUser();
            ParseUser.logOut();
        }*/
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ParseUser user = new ParseUser();
                progressBar.setVisibility(View.VISIBLE);
                user.setPassword(editTextTextPassword.getText().toString());
                user.setUsername(editTextusername.getText().toString());*/
                
                /*user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) { //if no error
                            progressBar.setVisibility(View.INVISIBLE);
                            FancyToast.makeText(signup.this,
                                    "Sign-Up Successful "+editTextfirstname.getText().toString(),
                                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            openMainActivity1();
                        }
                        else{
                            progressBar.setVisibility(View.INVISIBLE);
                            FancyToast.makeText(signup.this,
                                    "Sign-up failed for "+editTextfirstname.getText().toString(),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });*/

                //signupispressed();

                progressBar.setVisibility(View.VISIBLE);


                try {
                    // Reset errors
                    // Sign up with Parse

                    ParseUser user = new ParseUser();
                    progressBar.setVisibility(View.VISIBLE);
                    user.setEmail(editTextemail.getText().toString());
                    user.setPassword(editTextTextPassword.getText().toString());
                    user.setUsername(editTextusername.getText().toString());

                    /*
                    ParseUser user = new ParseUser();

                    user.setUsername(edtusername.getText().toString());
                    user.setPassword(edtpass.getText().toString());
                    user.setEmail(edtemail.getText().toString());*/

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
                }


            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity1();

            }
        });
    }

    public void openMainActivity1() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
        //Toast.makeText(this,"Signed in successfully", Toast.LENGTH_SHORT).show();
    }

    public void signupispressed() {
        Toast.makeText(this,"Signed in successfully", Toast.LENGTH_SHORT).show();
    }

    public void openloginpage() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }

    private void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(signup.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(signup.this, MainActivity1.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


}
