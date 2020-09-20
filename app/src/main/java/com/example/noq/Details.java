package com.example.noq;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Details extends AppCompatActivity {
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        //Intent j = getIntent();
        String title = i.getStringExtra("title");
        //int image = j.getIntExtra("image");
        textTitle = findViewById(R.id.detailTitle);
        textTitle.setText(title);

    }
}
