package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.danae.wat2340.R;

public class WelcomeScreen extends AppCompatActivity {
    ImageView fish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        fish = (ImageView) findViewById(R.id.background);
        fish.setBackgroundResource(R.drawable.animation);
        AnimationDrawable fishAnimation = (AnimationDrawable) fish.getBackground();
        fishAnimation.start();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_welcome_screen);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this, LoginActivity.class));
            }
        });
    }
}
