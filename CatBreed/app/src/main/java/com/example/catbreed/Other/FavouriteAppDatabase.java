package com.example.catbreed.Other;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.catbreed.model.Cat;


@Database(entities = {Cat.class}, version = 2, exportSchema = false)
public abstract class FavouriteAppDatabase extends RoomDatabase {
    public abstract FavouriteCatDao favouriteCatDao();

    private static FavouriteAppDatabase instance;
    public static FavouriteAppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, FavouriteAppDatabase.class, "favouriteCatDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
