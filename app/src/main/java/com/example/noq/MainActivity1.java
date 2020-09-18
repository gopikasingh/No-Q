package com.example.noq;

import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;


        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {

    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        button3 = (Button) findViewById(R.id.button3);

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

}