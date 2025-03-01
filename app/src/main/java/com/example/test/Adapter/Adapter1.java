package com.example.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Models.Models;
import com.example.test.Models.Models1;
import com.example.test.R;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.viewHolder> {

    List<Models1> itemlist;

    public Adapter1(List<Models1> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public Adapter1.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.viewHolder holder, int position) {

        Models1 models1 = itemlist.get(position);
        holder.imageView.setImageResource(models1.getImage());
        holder.textView.setText(models1.getTitile());

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class viewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.picture);
            textView=itemView.findViewById(R.id.titile);

        }
    }
}
