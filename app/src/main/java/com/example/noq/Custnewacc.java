package com.example.noq;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


//import android.app.ProgressDialog;


public class Custnewacc extends AppCompatActivity {

    private Button button9;
    private Button button10;
    private EditText edtname, edtusername, edtpass, edtemail, etdconpass;

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


        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloginpage();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signupispressed();

                try {
                    // Reset errors
                    // Sign up with Parse
                    ParseUser user = new ParseUser();

                    user.setUsername(edtusername.getText().toString());
                    user.setPassword(edtpass.getText().toString());
                    user.setEmail(edtemail.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                ParseUser.logOut();
                                alertDisplayer("Account Created Successfully!", "Please verify your email before Login", false);
                            } else {
                                ParseUser.logOut();
                                alertDisplayer("Error Account Creation failed", "Account could not be created" + " :" + e.getMessage(), true);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }



    public void signupispressed() {
        Toast.makeText(this,"Signed in successfully", Toast.LENGTH_SHORT).show();
    }

    public void openloginpage() {
        Intent intent = new Intent(this, Customer.class);
        startActivity(intent);
    }

    private void alertDisplayer(String title,String message, final boolean error){
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
    }

}