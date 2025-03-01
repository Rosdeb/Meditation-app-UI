package com.example.test.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.test.Adapter.Adapter1;
import com.example.test.Adapter.SleepAdapter;
import com.example.test.Models.Models1;
import com.example.test.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Sleep extends Fragment {
    boolean isSelected = false;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView recyclerView ;
    List<com.example.test.Models.Sleep> itemlist;
    SleepAdapter sleepAdapter;
    RelativeLayout relativeLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sleep, container, false);



        MaterialButton all = view.findViewById(R.id.all);
        recyclerView=view.findViewById(R.id.recyclerview5);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        itemlist = new ArrayList<>();
        itemlist.add(new com.example.test.Models.Sleep("Reduce", R.drawable.mask));
        itemlist.add(new com.example.test.Models.Sleep("afaf", R.drawable.mask2));
        itemlist.add(new com.example.test.Models.Sleep("Increase ", R.drawable.mask1));
        itemlist.add(new com.example.test.Models.Sleep("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new com.example.test.Models.Sleep("", R.drawable.group2));
        itemlist.add(new com.example.test.Models.Sleep("Reduce ", R.drawable.mask));
        itemlist.add(new com.example.test.Models.Sleep("asfasf", R.drawable.mask2));
        itemlist.add(new com.example.test.Models.Sleep("Increase ", R.drawable.mask1));
        itemlist.add(new com.example.test.Models.Sleep("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new com.example.test.Models.Sleep("afasf", R.drawable.group2));
        sleepAdapter= new SleepAdapter(itemlist);

        recyclerView.setAdapter(sleepAdapter);

//        GradientDrawable background = (GradientDrawable) all.getBackground();
//
//// Change the border color programmatically
//        background.setStroke(2, getResources().getColor(R.color.button_selected_color));

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !isSelected;

                if (isSelected){
                    all.setBackgroundColor(getResources().getColor(R.color.color_pressed));  // Change background color
                    all.setIconTintResource(R.color.red);
                   // RelativeLayout relativeLayout = view.findViewById(R.id.show_all);
                  //  relativeLayout.setVisibility(View.GONE);
                    replaceFragment(new Music_play());

                }else {
                    all.setBackgroundColor(getResources().getColor(R.color.color_unpressed));

                   // relativeLayout.setVisibility(View.VISIBLE);
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        return view;
    }
    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.click_visible, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Enables back navigation
        transaction.commit();
    }

}