package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.Color;

import java.util.List;

@Dao
public interface ItemColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Color color);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Color color);

    @Query("DELETE FROM Color")
    void deleteAllItemColor();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Color> itemColorList);

    @Query("SELECT * FROM Color ORDER BY addedDate DESC")
    LiveData<List<Color>> getAllItemColor();
}
