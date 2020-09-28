package com.example.noq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    private Button button;
    private Button buttonshop;
    private Button buttontrolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();



        button2 = (Button) findViewById(R.id.button2);
        button  =(Button) findViewById(R.id.button);
        buttonshop = (Button) findViewById(R.id.buttonshop);
        buttontrolley = (Button) findViewById(R.id.buttontrolley);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity1();

            }});
        buttonshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity1();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCustomer();

            }});
        buttontrolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomer();
            }
        });
    }

    public void openMainActivity1() {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);

    }
    public void openCustomer() {
        Intent intent = new Intent(this, Customer.class);
        startActivity(intent);

    }

}


