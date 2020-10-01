package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.Banner;

import java.util.List;

@Dao
public interface HomeBannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Banner> banners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Banner banner);

    @Query("SELECT * FROM Banner WHERE id = :id")
    LiveData<Banner> getBannerById(String id);

    @Query("SELECT * FROM Banner ORDER BY addedDate desc")
    LiveData<List<Banner>> getAllHomeBanner();

    @Query("SELECT * FROM Banner ORDER BY addedDate desc limit :limit")
    LiveData<List<Banner>> getAllHomeBannerByLimit(String limit);

    @Query("DELETE FROM Banner")
    void deleteAll();
}
