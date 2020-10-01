package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.ItemLocation;

import java.util.List;

@Dao
public interface ItemLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemLocation itemLocation);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemLocation itemLocation);

    @Query("DELETE FROM ItemLocation")
    void deleteAllItemLocation();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemLocation> itemLocationList);

    @Query("SELECT * FROM ItemLocation ORDER BY addedDate DESC")
    LiveData<List<ItemLocation>> getAllItemLocation();

}
