package com.example.asus.probazaizpit;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CatDao {
    @Insert
    void insert(Cat cat);

    @Query("DELETE FROM cats")
    void deleteAll();

    @Query("SELECT * from cats ORDER BY txtNameCat ASC")
    LiveData<List<Cat>> getAllCats();
}
