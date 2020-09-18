package com.example.noq;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Customer extends AppCompatActivity {
     private Button button8;
    private Button button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        button8 = (Button) findViewById(R.id.button8);
        button6 = (Button) findViewById(R.id.button6);

        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCustnewacc();

            }
        });
          button6.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            opendemoshop();

        }
    });}
        public void openCustnewacc() {
            Intent intent = new Intent(this, Custnewacc.class);
            startActivity(intent);
        }
    public void opendemoshop() {
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    }


