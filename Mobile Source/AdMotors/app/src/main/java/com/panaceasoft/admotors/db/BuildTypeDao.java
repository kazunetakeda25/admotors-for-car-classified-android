package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.BuildType;

import java.util.List;

@Dao
public interface BuildTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BuildType buildType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(BuildType buildType);

    @Query("DELETE FROM BuildType")
    void deleteAllBuildType();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BuildType> BuildTypeList);

    @Query("SELECT * FROM BuildType ORDER BY addedDate DESC")
    LiveData<List<BuildType>> getAllBuildType();
}
