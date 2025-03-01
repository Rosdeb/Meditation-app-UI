package com.example.test.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class},version = 124)
public abstract class Appdatabase extends  RoomDatabase{
    public abstract UserDao dao();
    private static volatile Appdatabase instance;

    public static synchronized Appdatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            Appdatabase.class, "room_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()  // Only for debugging
                    .build();
        }
        return instance;
    }

}