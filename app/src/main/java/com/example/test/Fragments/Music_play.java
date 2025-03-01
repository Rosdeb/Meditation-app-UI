package com.example.test.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Adapter.AudioAdapter;
import com.example.test.Adapter.SleepAdapter;
import com.example.test.Models.AudioModel;
import com.example.test.Models.Models;
import com.example.test.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class Music_play extends Fragment {

  ImageButton back;
  RecyclerView recyclerView;
  TextView Musicapp;


  private ArrayList<AudioModel> songsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_play, container, false);
        Musicapp = view.findViewById(R.id.sleepmusic);
        back=view.findViewById(R.id.back_fragment);
        recyclerView=view.findViewById(R.id.recyclerview25);

        if (!checkPermission()) {
            requestPermission();
        } else {
            loadAudioFiles();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ColorFilter colorFilter = back.getColorFilter();
                if (colorFilter == null) {
                    back.setColorFilter(Color.RED); // Set color
                    replaceFragment(new Sleep());
                } else {
                    back.clearColorFilter(); // Reset to original
                }

            }
        });
        return view;

    }
    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.back_fragment21, newFragment);
        // Enables back navigation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(requireContext(), "READ PERMISSION IS REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }

    private void loadAudioFiles() {
        songsList.clear();

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        Cursor cursor = requireContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);
                String path = cursor.getString(1);
                String duration = formatDuration(cursor.getLong(2));

                if (new File(path).exists()) {
                    songsList.add(new AudioModel(title, path, duration));
                }
            }
            cursor.close();
        }

        if (songsList.isEmpty()) {
            Musicapp.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));
            recyclerView.setAdapter(new AudioAdapter(songsList, requireContext()));
        }
    }

    private String formatDuration(long durationInMillis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public void onResume() {
        super.onResume();
        if (recyclerView != null) {
            recyclerView.setAdapter(new AudioAdapter(songsList,requireContext()));
        }
    }
}