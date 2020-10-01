package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.PSCount;

import java.util.List;

/**
 * Created by Panacea-Soft on 2019-08-28.
 * Contact Email : teamps.is.cool@gmail.com
 */


@Dao
public interface PSCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PSCount> psCountList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PSCount psCount);

    @Query("DELETE FROM pscount")
    void deleteAll();

    @Query("SELECT * FROM pscount LIMIT 1")
    LiveData<PSCount> getPSCount();

}
