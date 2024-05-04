package com.example.project02.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project02.Item;
import com.example.project02.User;

@Database(entities = {User.class, Item.class}, version=2)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "User.DB";
    public static final String USER_TABLE = "user";
    public static final String ITEM_TABLE = "item";

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();

    public abstract ItemDAO ItemDAO();

    public static AppDatabase getInstance(Context context){
        if(instance==null){
            synchronized (LOCK){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}