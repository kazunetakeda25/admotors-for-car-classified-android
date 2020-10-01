package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.SellerType;

import java.util.List;

@Dao
public interface SellerTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SellerType sellerType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(SellerType sellerType);

    @Query("DELETE FROM SellerType")
    void deleteAllSellerType();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SellerType> SellerTypeList);

    @Query("SELECT * FROM SellerType ORDER BY addedDate DESC")
    LiveData<List<SellerType>> getAllSellerType();
}
