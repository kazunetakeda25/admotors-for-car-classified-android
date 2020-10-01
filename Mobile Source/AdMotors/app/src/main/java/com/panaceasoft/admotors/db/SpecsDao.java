package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.ItemSpecs;

import java.util.List;

@Dao
public abstract class SpecsDao {
    //region product color

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<ItemSpecs> productSpecsList);

    @Query("SELECT * FROM ItemSpecs WHERE itemId =:itemId")
    public abstract LiveData<List<ItemSpecs>> getItemSpecsById(String itemId);

    @Query("DELETE FROM ItemSpecs WHERE itemId =:itemId")
    public abstract void deleteItemSpecsById(String itemId);

    @Query("DELETE FROM ItemSpecs")
    public abstract void deleteAll();
}
