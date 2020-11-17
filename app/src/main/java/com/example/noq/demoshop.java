package com.example.noq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class demoshop extends AppCompatActivity {

    private ImageButton items;
    private ImageButton cont;
    private TextView t;
    String key;
    String sn = "";
    itemlistshopper s = new itemlistshopper();
    feedback d = new feedback();
    private static final String TAG = "demoshop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        //Intent j = getIntent();
        key = i.getStringExtra("key");
        setContentView(R.layout.activity_demoshop);
        t = (TextView) findViewById(R.id.text10);
        getname();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        items = (ImageButton) findViewById(R.id.imageButton4);
        cont = (ImageButton) findViewById(R.id.imageButton3);
        items.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openshopbuttonactivity();
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openfeedback();
            }
        });


    }

    public void getname() {
        FirebaseDatabase.getInstance().getReference().child("shops").child(key).child("sn")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        sn = snapshot.getValue(String.class);
                        Log.i(TAG, "nhi mila" + sn);
                        //set(sn);
                        t.setText(snapshot.getValue(String.class));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i(TAG, "nhi mila");

                    }
                });

    }

    public void set(String s) {
        t.setText(s);
    }

    public void openshopbuttonactivity() {
        Intent intent = new Intent(this, itemlistshopper.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }

    public void openfeedback() {
        Intent intent = new Intent(this, feedback.class);

        startActivity(intent);
    }
}

