package com.example.catbreed.Other;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.catbreed.model.Cat;

import java.util.List;

@Dao
public interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Cat> cat);

    @Update
    public void update(Cat... books);

    @Delete
    public void delete(Cat cat);

    @Query("SELECT * FROM Cat")
    public List<Cat> getAllCats();

    @Query("SELECT * FROM Cat WHERE id = :id")
    public Cat getCat (String id);
}
