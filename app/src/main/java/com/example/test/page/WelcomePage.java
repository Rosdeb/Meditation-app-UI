package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;

import java.util.logging.Handler;

public class WelcomePage extends AppCompatActivity {

    MaterialButton getstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        getstarted=findViewById(R.id.getstarted);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(WelcomePage.this, ChoiseTopic.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0, 0); // Removes transition animation
                    finish();

            }
        });
    }
}