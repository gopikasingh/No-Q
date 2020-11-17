package com.example.noq;

import android.app.AlertDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity1 extends AppCompatActivity {

    private Button button3;
    private Button button7;
    private ProgressBar progressBarrr;
    private EditText editText, editText2;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        //setTitle("Login");
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        progressBarrr = (ProgressBar) findViewById(R.id.progressbar50);
        progressBarrr.setVisibility(View.INVISIBLE);

        button3 = (Button) findViewById(R.id.button3);
        button7 = (Button) findViewById(R.id.button7);

        //fauth = FirebaseAuth.getInstance();
        //fstore = FirebaseFirestore.getInstance();

        //userid = fauth.getCurrentUser().getUid();



        /*if (ParseUser.getCurrentUser() != null){ //if already logged in
            ParseUser.getCurrentUser();
            ParseUser.logOut();
        }*/
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarrr.setVisibility(View.VISIBLE);

                ParseUser.logInInBackground(editText.getText().toString(), editText2.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        /*if (user!= null && e==null){
                            progressBarrr.setVisibility(View.INVISIBLE);
                            FancyToast.makeText(MainActivity1.this,"Login Successful "+editText.getText().toString(),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else {
                            progressBarrr.setVisibility(View.INVISIBLE);
                            ParseUser.logOut();
                            FancyToast.makeText(MainActivity1.this,"Login failed for "+editText.getText().toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                        //progressBarrr.setVisibility(View.VISIBLE);
                        (editText2).setError(null);

                        // Login with Parse
                        ParseUser.logInInBackground(editText.getText().toString(), editText2.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {*/
                        if (user != null) {
                            if(user.getBoolean("emailVerified")) {
                                progressBarrr.setVisibility(View.INVISIBLE);
                                FancyToast.makeText(MainActivity1.this,"Login Successful for "+editText.getText().toString(),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                alertDisplayer("Login Sucessful", "Welcome, " + editText.getText().toString() + "!", false);
                                //for more fancy toast messages -> https://github.com/Shashank02051997/FancyToast-Android
                            }
                            else
                            {
                                ParseUser.logOut();
                                progressBarrr.setVisibility(View.INVISIBLE);
                                alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                            }
                        } else {
                            YoYo.with(Techniques.Shake)
                                    .duration(500)
                                    .repeat(0)
                                    .playOn(button7);


                            ParseUser.logOut();
                            progressBarrr.setVisibility(View.INVISIBLE);
                            alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                        }
                        //});


                    }

                });

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                opensignup();

            }

        });
    }

    public void opensignup() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    /*public void openCustnewacc() {
        Intent intent = new Intent(this, Custnewacc.class);
        startActivity(intent);
    }
    public void opendemoshop() {
        Intent intent = new Intent(this, cardmain.class);
        startActivity(intent);
    }*/
    //public void loginispressed(View v){
    //}

    public void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity1.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                Intent intent = new Intent(MainActivity1.this, next.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(MainActivity1.this, addshop.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }

                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


}
/*<ProgressBar
                android:id="@+id/progressbar"
                style="@android:style/Widget.Material.Light.ProgressBar"
                android:layout_width="397dp"
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
* */