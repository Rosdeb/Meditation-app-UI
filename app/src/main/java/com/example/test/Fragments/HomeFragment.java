package com.example.test.Fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Adapter.Adapter1;
import com.example.test.Models.Models1;
import com.example.test.R;
import com.example.test.page.WelcomePage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    CircleImageView play;
    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    MaterialButton start;

    Adapter1 adapter;
    List<Models1> itemlist;
    BottomNavigationView bottomNavigationView;
    private boolean isChecked= false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        play=view.findViewById(R.id.playon);
        start=view.findViewById(R.id.staed);

        //notification ---------------------------////
        createnotificationChanell();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shownotification();
            }
            private void shownotification() {
                Intent intent = new Intent(getContext(), WelcomePage.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"my_channel_id")
                        .setAutoCancel(true)
                        .setContentTitle("New Notifications")
                        .setContentText("You are started basic course")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.home)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                if (manager!=null){
                    manager.notify(1,builder.build());
                }

            }
        });

        //id defined
        nestedScrollView =view.findViewById(R.id.idnestedscroll);

       /* nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                if (scrollY > oldScrollY) {
                    // Hide BottomNavigationView with animation
                    bottomNavigationView.animate().translationX(bottomNavigationView.getWidth()).setDuration(500);
                } else if (scrollY < oldScrollY) {
                    // If scrolling up, show BottomNavigationView
                    bottomNavigationView.animate().translationX(0).setDuration(500);
                }
            }

        });*/
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked){
                    play.setBackgroundColor(Color.parseColor("#8E97FD"));
                }else {
                    play.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                isChecked=!isChecked; //toggle state

            }

        });

        recyclerView=view.findViewById(R.id.recylcerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        itemlist = new ArrayList<>();
        itemlist.add(new Models1("Reduce", R.drawable.mask));
        itemlist.add(new Models1("afaf", R.drawable.mask2));
        itemlist.add(new Models1("Increase ", R.drawable.mask1));
        itemlist.add(new Models1("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new Models1("", R.drawable.group2));
        itemlist.add(new Models1("Reduce ", R.drawable.mask));
        itemlist.add(new Models1("asfasf", R.drawable.mask2));
        itemlist.add(new Models1("Increase ", R.drawable.mask1));
        itemlist.add(new Models1("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new Models1("afasf", R.drawable.group2));
        adapter= new Adapter1(itemlist);

        recyclerView.setAdapter(adapter);

        return view;

    }
    private void createnotificationChanell() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "my_channel_id",
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            if (manager!=null){
                manager.createNotificationChannel(channel);
            }

        }

    }

}