package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;

public class FirstPagge extends AppCompatActivity {
    MaterialButton signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_pagge);



        signup=findViewById(R.id.singup);
        signup.setSoundEffectsEnabled(false); // Disable sound effects
        signup.setHapticFeedbackEnabled(false); // Disable vibration feedback
        signup.setClickable(true); // Ensure button is clickable
        signup.setFocusable(false); // Prevent focus issues
        signup.setFocusableInTouchMode(false);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstPagge.this, SecondPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0); // Removes transition animation


            }
        });

    }
}