package com.leper182.fitnesskit.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Timetable.class,version = 1,exportSchema = false)
public abstract class TimetableDatabase extends RoomDatabase {
    private static TimetableDatabase database;
    private final static String DB_NAME = "timetables.db";
    private final static Object LOCK = new Object();

    public static TimetableDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, TimetableDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }
    public abstract TimetablesDao timetablesDao();

}
