package com.example.noq;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

public class next extends AppCompatActivity {
    EditText editText55, editText255;
    Button button755;
    Button txt3;
    ProgressBar progressbar5055;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        editText55 = findViewById(R.id.editText55);
        editText255 = findViewById(R.id.editText255);
        button755 = (Button) findViewById(R.id.button755);
        txt3 = (Button) findViewById(R.id.txt3);
        progressbar5055 =  findViewById(R.id.progressbar5055);
        fauth = FirebaseAuth.getInstance();

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar5055.setVisibility(View.INVISIBLE);
                create_ashop();
            }
        });

        button755.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = editText55.getText().toString();
                String pass = editText255.getText().toString();

                if(TextUtils.isEmpty(em)) {
                    editText55.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    editText255.setError("Invalid Password!");
                    return;
                }
                progressbar5055.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressbar5055.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            FancyToast.makeText(next.this,"Welcome!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                            eeeeentershop();
                        }else {
                            FancyToast.makeText(next.this,"Please try again later \n" +task.getException().getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }

                    }
                });
            }
        });
    }
    public void eeeeentershop() {
        Intent intent = new Intent(this, inshop.class);
        startActivity(intent);
    }
    public void create_ashop() {
        Intent intent = new Intent(this, shoplog.class);
        startActivity(intent);
    }
}