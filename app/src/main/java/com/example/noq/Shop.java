package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Shop extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        btn = (Button) findViewById(R.id.button21);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openshopbuttonactivity(); }});
    }
    public void openshopbuttonactivity() {
        Intent intent = new Intent(this, demoshop.class);
        startActivity(intent);

    }
}