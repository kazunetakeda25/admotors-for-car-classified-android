package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.ItemType;

import java.util.List;

@Dao
public interface ItemTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemType itemType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemType itemType);

    @Query("DELETE FROM ItemType")
    void deleteAllItemType();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemType> itemTypeList);

    @Query("SELECT * FROM ItemType ORDER BY addedDate DESC")
    LiveData<List<ItemType>> getAllItemType();

}
