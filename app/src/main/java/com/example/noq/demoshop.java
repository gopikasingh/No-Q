package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class demoshop extends AppCompatActivity {

    private ImageButton items;
    private ImageButton cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoshop);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        items= (ImageButton) findViewById(R.id.imageButton4);
        cont= (ImageButton) findViewById(R.id.imageButton3);
        items.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openshopbuttonactivity(); }});
    }
    public void openshopbuttonactivity() {
        Intent intent = new Intent(this, inventory.class);
        startActivity(intent);
    }
}