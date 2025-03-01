package com.example.test.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.test.MyMediaPlayer;
import com.example.test.R;

import org.jetbrains.annotations.Nullable;

public class MusicService extends android.app.Service {
    private static final String CHANNEL_ID = "MusicServiceChannel";
    private static final String ACTION_PLAY = "ACTION_PLAY";
    private static final String ACTION_PAUSE = "ACTION_PAUSE";
    private static final String ACTION_STOP = "ACTION_STOP";

    private MediaPlayer mediaPlayer;
    private String songTitle = ""; // Variable to hold the song title

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (intent.hasExtra("songTitle")) {
            songTitle = intent.getStringExtra("songTitle"); // Get the song title
        }

        if (action != null) {
            if (action.equals(ACTION_PLAY)) {
                playMusic();
            } else if (action.equals(ACTION_PAUSE)) {
                pauseMusic();
            } else if (action.equals(ACTION_STOP)) {
                stopMusic();
            }
        } else {
            playMusic(); // Default to play
        }

        return START_STICKY;
    }

    private void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            startForeground(1, buildNotification(true)); // Show Play Notification
        }
    }

    private void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            startForeground(1, buildNotification(false)); // Show Pause Notification
        }
    }

    private void stopMusic() {
        mediaPlayer.stop();
        stopForeground(true);
        stopSelf();
    }

    private Notification buildNotification(boolean isPlaying) {
        // Intent for Play, Pause, and Stop actions
        PendingIntent playIntent = PendingIntent.getService(this, 0,
                new Intent(this, MusicService.class).setAction(ACTION_PLAY), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pauseIntent = PendingIntent.getService(this, 0,
                new Intent(this, MusicService.class).setAction(ACTION_PAUSE), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent stopIntent = PendingIntent.getService(this, 0,
                new Intent(this, MusicService.class).setAction(ACTION_STOP), PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification Channel (Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Music Player", NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        // Return the notification with media controls
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music is Playing")
                .setContentText(songTitle)  // Display the song title in the notification
                .setSmallIcon(R.drawable.mask) // Your app icon
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(isPlaying ? R.drawable.pause : R.drawable.play,
                        isPlaying ? "Pause" : "Play", isPlaying ? pauseIntent : playIntent)
                .addAction(R.drawable.music, "Stop", stopIntent)
                .setOngoing(true)
                .setAutoCancel(false)
                .build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
