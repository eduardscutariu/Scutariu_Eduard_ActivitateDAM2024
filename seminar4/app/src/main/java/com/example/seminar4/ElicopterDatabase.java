package com.example.seminar4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Elicopter.class}, version = 1)
@TypeConverters({ConversieDateToLong.class})

public abstract class ElicopterDatabase extends RoomDatabase {
    public abstract ElicopterDAO elicopterDAO();

    private static ElicopterDatabase instance;

    public static synchronized ElicopterDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ElicopterDatabase.class, "TabelElicoptere")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
