package com.example.noq;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Customer extends AppCompatActivity {
    private Button button8;
    private Button button6;
    private EditText edtl_username, edtl_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        button8 = (Button) findViewById(R.id.button8);
        button6 = (Button) findViewById(R.id.button6);

        edtl_username = findViewById(R.id.editTextTextPersonName);
        edtl_pass = findViewById(R.id.editTextTextPassword2);


        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCustnewacc();

            }
        });

        //button6.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //opendemoshop();


            //}

        //});
    }
    public void openCustnewacc() {
        Intent intent = new Intent(this, Custnewacc.class);
        startActivity(intent);
    }
    public void opendemoshop() {
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }
    public void loginispressed(View v){

        // Reset errors
        (edtl_pass).setError(null);

        // Login with Parse
        ParseUser.logInInBackground(edtl_username.getText().toString(), edtl_pass.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    if(parseUser.getBoolean("emailVerified")) {
                        alertDisplayer("Login Sucessful", "Welcome, " + edtl_username.getText().toString() + "!", false);
                    }
                    else
                    {
                        ParseUser.logOut();
                        alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                    }
                } else {
                    ParseUser.logOut();
                    alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                }


            }
        });


    }

    private void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(Customer.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(Customer.this, Shop.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}


