package com.example.project02;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mLogID;


    private String mUser;
    private String mPass;
    private boolean isAdmin;


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getLogID() {
        return mLogID;
    }

    public void setLogID(int logID) {
        mLogID = logID;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public String getPass() {
        return mPass;
    }

    public void setPass(String pass) {
        mPass = pass;
    }

    public User(String user, String pass, Boolean admin) {
        mUser = user;
        mPass = pass;
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "User='" + mUser + '\'' +
                ",Pass='" + mPass + '\'' +
                '}';
    }
}
