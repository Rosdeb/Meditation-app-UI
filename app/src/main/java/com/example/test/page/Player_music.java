package com.example.test.page;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Models.AudioModel;
import com.example.test.MyMediaPlayer;
import com.example.test.R;
import com.example.test.Service.MusicService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player_music extends AppCompatActivity {

    SeekBar seekBar;
    ImageButton back;
    ImageView forword,decrease;

    TextView currenttime,titlemusic;
    TextView totaltime;
    ArrayList<AudioModel> songsList;
    AudioModel currentSong;
    ImageView play,pause;

    MediaPlayer mediaPlayer = MyMediaPlayer.getIntance();

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_music);



        back=findViewById(R.id.back_cross);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        titlemusic=findViewById(R.id.title_music);
        forword=findViewById(R.id.forword_15);
        decrease=findViewById(R.id.deiscrease);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deincrease(15000);
            }
        });

        forword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forwoard(15000);
            }
        });


        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        currenttime=findViewById(R.id.current_time);
        totaltime=findViewById(R.id.total_time);
        seekBar=findViewById(R.id.seekbar);

        songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");

        if (songsList != null && !songsList.isEmpty()) {
            setResourcesWithMusic();
        } else {
            Toast.makeText(this, "No songs available!", Toast.LENGTH_SHORT).show();
        }

        playmusic();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pausemusic();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        handler.post(updateSeekBar);

    }

    private void deincrease(int milliSeconds){
        if (mediaPlayer!=null){
            int currentposition = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            int newposition= Math.min(currentposition - milliSeconds,duration);
            mediaPlayer.seekTo(newposition);
            seekBar.setProgress(newposition);
        }
    }

    private void Forwoard(int milliseconds) {

        if (mediaPlayer!=null){
            int currentposition = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            int newPosition = Math.min(currentposition+milliseconds,duration);
            mediaPlayer.seekTo(newPosition);
            seekBar.setProgress(newPosition);
            currenttime.setText(convertToMMSS(String.valueOf(newPosition)));
        }
    }
    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                int currentPos = mediaPlayer.getCurrentPosition();
                seekBar.setProgress(currentPos);
                currenttime.setText(convertToMMSS(String.valueOf(currentPos)));
            }
            handler.postDelayed(this, 500); // Update every 500ms
        }
    };

    void playnextsong(){
        if(MyMediaPlayer.currentIndex==0){
            return;
        }
        MyMediaPlayer.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }
    void playprevius(){
        if(MyMediaPlayer.currentIndex==0){
            return;
        }
        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    void playmusic() {
        mediaPlayer.reset();  // Reset the MediaPlayer
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepareAsync();  // Use prepareAsync for non-blocking

            // Start music once it's prepared
            mediaPlayer.setOnPreparedListener(mp -> {
                seekBar.setMax(mp.getDuration()); // Now duration is available
                totaltime.setText(convertToMMSS(String.valueOf(mp.getDuration())));
                mp.start();
                handler.post(updateSeekBar); // Ensure SeekBar updates start
            });
            mediaPlayer.setOnCompletionListener(mp ->{

                seekBar.setProgress(0);
                currenttime.setText("00:00");
            });
        } catch (IOException e) {
            Log.e("PlayerMusic", "Error preparing or playing the music", e);
        }
    }

    void pausemusic(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            pause.setImageResource(R.drawable.play);

        }else {
            mediaPlayer.start();
            pause.setImageResource(R.drawable.pause);
        }
    }

    void setResourcesWithMusic(){
        currentSong = songsList.get(MyMediaPlayer.currentIndex);

        titlemusic.setText(currentSong.getTitle());
        totaltime.setText(currentSong.getDuration());

    }
    @SuppressLint("DefaultLocale")
    public static String convertToMMSS(String duration) {
        try {
            long millis = Long.parseLong(duration); // Ensure the duration is a valid number
            return String.format("%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1), TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "00:00";  // Default value in case of error
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer!=null){
            try {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();

            }catch (IllegalStateException e){
                e.printStackTrace();

            }
        }
        handler.removeCallbacks(updateSeekBar);
    }
}