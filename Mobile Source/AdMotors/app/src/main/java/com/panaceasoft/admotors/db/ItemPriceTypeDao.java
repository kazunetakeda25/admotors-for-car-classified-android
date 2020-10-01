package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.ItemPriceType;

import java.util.List;

@Dao
public interface ItemPriceTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemPriceType itemPriceType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemPriceType itemPriceType);

    @Query("DELETE FROM ItemPriceType")
    void deleteAllItemPriceType();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemPriceType> itemPriceTypeList);

    @Query("SELECT * FROM ItemPriceType ORDER BY addedDate DESC")
    LiveData<List<ItemPriceType>> getAllItemPriceType();

}
