package com.example.test.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Models.Models;
import com.example.test.R;
import com.example.test.page.FirstPagge;
import com.example.test.page.Reminder;
import com.example.test.page.SecondPage;

import java.util.List;

public class Adapterr extends RecyclerView.Adapter<Adapterr.viewholder> {

    List<Models> listitem;

    public Adapterr(List<Models> listitem) {
        this.listitem = listitem;
    }

    @NonNull
    @Override
    public Adapterr.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterr.viewholder holder, int position) {

        Models models = listitem.get(position);
        holder.itemImage.setImageResource(models.getImage());
        holder.itemTitle.setText(models.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Reminder.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemImage=itemView.findViewById(R.id.itemimage);
            itemTitle=itemView.findViewById(R.id.itemtitle);
        }
    }
}
