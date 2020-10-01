package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.panaceasoft.admotors.viewobject.ItemCurrency;
import java.util.List;

@Dao
public interface ItemCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemCurrency itemCurrency);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ItemCurrency itemCurrency);

    @Query("DELETE FROM ItemCurrency")
    void deleteAllItemCurrency();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemCurrency> itemCurrencyList);

    @Query("SELECT * FROM ItemCurrency ORDER BY addedDate DESC")
    LiveData<List<ItemCurrency>> getAllItemCurrency();

}
