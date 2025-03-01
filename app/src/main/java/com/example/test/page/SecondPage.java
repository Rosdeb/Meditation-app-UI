package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.R;
import com.google.android.material.button.MaterialButton;

public class SecondPage extends AppCompatActivity {


    MaterialButton singin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);


        singin=findViewById(R.id.login);
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondPage.this, WelcomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0); // Removes transition animation
                finish();

            }
        });


    }
}