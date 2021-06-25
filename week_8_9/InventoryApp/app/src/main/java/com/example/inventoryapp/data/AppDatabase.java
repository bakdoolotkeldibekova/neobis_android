package com.example.inventoryapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.inventoryapp.Item;

@Database(entities = {Item.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract ItemDao itemDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database_name")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
