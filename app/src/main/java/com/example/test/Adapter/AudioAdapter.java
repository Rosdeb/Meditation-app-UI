package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Models.AudioModel;
import com.example.test.MyMediaPlayer;
import com.example.test.R;
import com.example.test.page.Player_music;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.viewholder> {

    private ArrayList<AudioModel> audioList;
    Context context;


    public AudioAdapter(ArrayList<AudioModel> audioList, Context context) {
        this.audioList = audioList;
        this.context = context;
    }

    @NonNull
    @Override
    public AudioAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sleep2,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.viewholder holder, int position) {

        AudioModel audioModel = audioList.get(position);
        holder.title.setText(audioModel.getTitle());
        holder.duration.setText(audioModel.getDuration());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMediaPlayer.getIntance().reset();
                MyMediaPlayer.currentIndex = position;
                Intent intent = new Intent(holder.itemView.getContext(), Player_music.class);
                intent.putParcelableArrayListExtra("LIST",(ArrayList<AudioModel>)audioList);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView title,duration;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.idddd);
            duration=itemView.findViewById(R.id.duration25);

        }
    }
}
