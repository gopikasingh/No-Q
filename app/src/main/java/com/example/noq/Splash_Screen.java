package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    ImageView logo;
    TextView noq;
    TextView tagline;
    Animation from_bottom;
    Animation from_top;
    Animation left_to_right;
    Animation right_to_left;
    Animation zoom_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        logo = findViewById(R.id.logo);
        noq = findViewById(R.id.textView14);
        tagline = findViewById(R.id.textView12);

        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        from_top = AnimationUtils.loadAnimation(this, R.anim.from_top);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        zoom_in = AnimationUtils.loadAnimation(this, R.anim.zoom_in);

        noq.setAnimation(from_top);
        logo.setAnimation(from_top);
        tagline.setAnimation(from_bottom);


        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }, 3000);
    }
}