package com.example.test.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "first_name")
    public String firstname;

    @ColumnInfo(name = "last_name")
    public String lastname;


    public User( String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;


    }

    public int getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
