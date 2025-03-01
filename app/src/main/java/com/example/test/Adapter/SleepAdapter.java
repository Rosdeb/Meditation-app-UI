package com.example.test.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Models.Sleep;
import com.example.test.R;
import com.example.test.page.Player_music;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.viewholder> {
    List<Sleep> itemlist;

    public SleepAdapter(List<Sleep> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public SleepAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sleep,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepAdapter.viewholder holder, int position) {

        Sleep id = itemlist.get(position);
        holder.textView.setText(id.getTitle());
        holder.imageView.setImageResource(id.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Player_music.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sleep);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
