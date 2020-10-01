package com.panaceasoft.admotors.db;

import com.panaceasoft.admotors.viewobject.City;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<City> cities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(City city);

    @Query("DELETE FROM City")
    void deleteAll();

    @Query("SELECT * FROM City WHERE id = :id")
    LiveData<City> getCityById(String id);

    @Query("DELETE FROM City WHERE id = :id")
    void deleteCityById(String id);

    @Query("SELECT c.* FROM City c, CityMap cm WHERE c.id = cm.cityId AND cm.mapKey = :mapKey ORDER BY cm.sorting asc")
    LiveData<List<City>> getCityListByMapKey (String mapKey);
}
