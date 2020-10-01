package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.Manufacturer;

import java.util.List;

@Dao
public interface ItemManufacturerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Manufacturer manufacturer);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Manufacturer manufacturer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Manufacturer> cityManufacturers);

    @Query("DELETE FROM Manufacturer")
    void deleteAllCityCategory();

    @Query("DELETE FROM Manufacturer WHERE id = :id")
    void deleteCityCategoryById(String id);

    @Query("SELECT max(sorting) from Manufacturer ")
    int getMaxSortingByValue();

    @Query("SELECT * FROM Manufacturer  ORDER BY sorting")
    LiveData<List<Manufacturer>> getAllCityManufacturerById();

    @Query("DELETE FROM Manufacturer WHERE id =:id")
    public abstract void deleteItemCategoryById(String id);

    @Query("SELECT * FROM Manufacturer ORDER BY sorting desc limit :limit")
    LiveData<List<Manufacturer>> getItemManufacturerByLimit(String limit);

}
