package com.example.test.page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.test.Adapter.Adapter1;
import com.example.test.Adapter.Adapterr;
import com.example.test.Adapter.Viewpager_2;
import com.example.test.Fragments.HomeFragment;
import com.example.test.Fragments.Meditate;
import com.example.test.Fragments.Music;
import com.example.test.Fragments.Profile;
import com.example.test.Fragments.Sleep;
import com.example.test.Models.Models;
import com.example.test.Models.Models1;
import com.example.test.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewpager2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomnavigation);
        viewpager2=findViewById(R.id.fragments);
        setupviewpager2();

//        if (savedInstanceState==null){
//            loadFragment(new HomeFragment());
//        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id==R.id.navigation_home){
                    viewpager2.setCurrentItem(0);
                    return true;
                } if (id==R.id.navigation_moon){
                    viewpager2.setCurrentItem(1);
                    return true;
                } if (id==R.id.navigation_dot){
                    viewpager2.setCurrentItem(2);
                    return true;
                } if (id==R.id.navigation_music){
                    viewpager2.setCurrentItem(3);
                    return true;
                } if (id==R.id.navigation_profile){
                    viewpager2.setCurrentItem(4);
                    return true;
                }
                //checked

                return true;
            }
        });

    }

//    private void loadFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragments, fragment);
//        transaction.addToBackStack(null);
//        transaction.commitNow();
//    }
    private void setupviewpager2(){

        Viewpager_2 adapter= new Viewpager_2(getSupportFragmentManager(),getLifecycle());
        viewpager2.setAdapter(adapter);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_moon);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_dot);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_music);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
                        break;

                }
            }
        });
    }

}