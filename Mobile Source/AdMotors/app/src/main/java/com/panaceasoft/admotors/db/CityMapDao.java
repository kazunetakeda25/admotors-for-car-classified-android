package com.panaceasoft.admotors.db;

import com.panaceasoft.admotors.viewobject.CityMap;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CityMapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CityMap cityMap);

    @Query("DELETE FROM CityMap WHERE mapKey = :key")
    void deleteByMapKey(String key);

    @Query("SELECT max(sorting) from CityMap WHERE mapKey = :value ")
    int getMaxSortingByValue(String value);

    @Query("DELETE FROM CityMap")
    void deleteAll();
}
