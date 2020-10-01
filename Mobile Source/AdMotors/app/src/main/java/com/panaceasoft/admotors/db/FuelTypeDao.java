package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.FuelType;

import java.util.List;

@Dao
public interface FuelTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FuelType fuelType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(FuelType fuelType);

    @Query("DELETE FROM FuelType")
    void deleteAllItemFuel();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FuelType> itemFuelTypeList);

    @Query("SELECT * FROM FuelType ORDER BY addedDate DESC")
    LiveData<List<FuelType>> getAllItemFuel();
}
