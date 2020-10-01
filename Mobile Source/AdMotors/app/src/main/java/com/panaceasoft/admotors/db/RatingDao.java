package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.Rating;

import java.util.List;

@Dao
public interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Rating> ratingList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Rating rating);

    @Query("DELETE FROM Rating")
    void deleteAll();

    @Query("SELECT * FROM Rating WHERE toUserId = :toUserId")
    LiveData<List<Rating>> getRatingById(String toUserId);

}
