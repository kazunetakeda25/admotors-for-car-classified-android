package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.ItemCondition;

import java.util.List;

@Dao
public interface ItemConditionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemCondition itemCondition);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemCondition itemCondition);

    @Query("DELETE FROM ItemCondition")
    void deleteAllItemCondition();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemCondition> itemConditionList);

    @Query("SELECT * FROM ItemCondition ORDER BY addedDate DESC")
    LiveData<List<ItemCondition>> getAllItemCondition();

}
