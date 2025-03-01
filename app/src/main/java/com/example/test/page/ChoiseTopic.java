package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.test.Adapter.Adapterr;
import com.example.test.Models.Models;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ChoiseTopic extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapterr adapter;
    private List<Models> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_topic);


        recyclerView=findViewById(R.id.recylcerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        itemlist = new ArrayList<>();
        itemlist.add(new Models("Reduce Striss", R.drawable.mask));
        itemlist.add(new Models("", R.drawable.mask2));
        itemlist.add(new Models("Increase Happiness", R.drawable.mask1));
        itemlist.add(new Models("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new Models("", R.drawable.group2));
        itemlist.add(new Models("Reduce Striss", R.drawable.mask));
        itemlist.add(new Models("", R.drawable.mask2));
        itemlist.add(new Models("Increase Happiness", R.drawable.mask1));
        itemlist.add(new Models("Reduce Anxiety", R.drawable.yellow));
        itemlist.add(new Models("", R.drawable.group2));
        adapter= new Adapterr(itemlist);
        recyclerView.setAdapter(adapter);




    }
}