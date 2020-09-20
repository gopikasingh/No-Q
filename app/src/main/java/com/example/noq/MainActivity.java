package com.example.noq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button) findViewById(R.id.button2);
        button  =(Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity1();

            }});

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCustomer();

            }});
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


