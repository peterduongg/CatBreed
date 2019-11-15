package com.example.catbreed.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//favourite cat only needed to display cat name
//persists when app is closed

@Entity
public class FavouriteCat {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
