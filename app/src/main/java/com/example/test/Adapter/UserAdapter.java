package com.example.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.Models.User;
import com.example.test.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewholder> {

    List<User> item;

    public UserAdapter(List<User> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public UserAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serach_api,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewholder holder, int position) {

        User category = item.get(position);
        holder.name.setText(category.getLogin());

        Glide.with(holder.itemView.getContext())
                .load(category.getAvatarUrl())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class viewholder extends  RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvCategoryName);
            image= itemView.findViewById(R.id.ivCategoryImage);

        }
    }
}
