package com.daclink.gymlog_v_sp22.DB;

import android.content.Context;
import android.renderscript.Element;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.gymlog_v_sp22.GymLog;
import com.daclink.gymlog_v_sp22.User;

@TypeConverters
@Database(entities = {GymLog.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "GymLog.db";
    public static final String GYMLOG_TABLE = "gymlog_table";
    public static final String USER_TABLE = "USER_TABLE";

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract GymLogDAO GymLogDAO();

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
