package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.Transmission;

import java.util.List;
@Dao
public interface TransmissionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transmission transmission);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Transmission transmission);

    @Query("DELETE FROM Transmission")
    void deleteAllItemPriceType();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Transmission> itemPriceTypeList);

    @Query("SELECT * FROM Transmission ORDER BY addedDate DESC")
    LiveData<List<Transmission>> getAllItemPriceType();
}
