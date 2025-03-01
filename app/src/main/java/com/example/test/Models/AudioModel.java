package com.example.test.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class AudioModel implements Parcelable {

    String title,path,duration;

    public AudioModel(String title, String path, String duration) {
        this.title = title;
        this.path = path;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public String getDuration() {
        return duration;
    }


    protected AudioModel(Parcel in) {
        title = in.readString();
        path = in.readString();
        duration = in.readString();
    }
    public static final Creator<AudioModel> CREATOR = new Creator<AudioModel>() {
        @Override
        public AudioModel createFromParcel(Parcel in) {
            return new AudioModel(in);
        }

        @Override
        public AudioModel[] newArray(int size) {
            return new AudioModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(path);
        parcel.writeString(duration);
    }
}
