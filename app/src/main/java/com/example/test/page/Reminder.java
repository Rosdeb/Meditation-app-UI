package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.test.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Reminder extends AppCompatActivity {
    private boolean isSelected = false;
    private boolean isSelected1 = false;
    private boolean isSelected2 = false;
    private boolean isSelected3 = false;
    private boolean isSelected4 = false;
    private boolean isSelected5 = false;
    private boolean isSelected6 = false;

    ImageView sun,mon,th,wed,thr,fri,str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        sun=findViewById(R.id.sunday);
        mon=findViewById(R.id.monday);
        th=findViewById(R.id.thuesday);
        wed=findViewById(R.id.wednesday);
        thr=findViewById(R.id.tharsday);
        fri=findViewById(R.id.friday);
        str=findViewById(R.id.setarday);

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    sun.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    sun.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected = !isSelected; // Toggle state
                Intent intent = new Intent(Reminder.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0); // Removes transition animation

            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected1) {
                    mon.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    mon.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected1 = !isSelected1; // Toggle state
            }
        });
        th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected2) {
                    th.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    th.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected2 = !isSelected2; // Toggle state
            }
        });
        thr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected3) {
                    thr.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    thr.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected3 = !isSelected3; // Toggle state
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected4) {
                    wed.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    wed.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected4= !isSelected4; // Toggle state
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected5) {
                    fri.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    fri.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected5 = !isSelected5; // Toggle state
            }
        });
        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected6) {
                    str.setBackgroundColor(Color.parseColor("#A1A4B2")); // Default color
                } else {
                    str.setBackgroundColor(Color.parseColor("#8E97FD")); // Clicked color
                }
                isSelected6 = !isSelected6; // Toggle state
            }
        });
    }
}