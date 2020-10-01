package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.Model;

import java.util.List;

@Dao
public interface ItemModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Model model);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Model model);

    @Query("DELETE FROM Model")
    void deleteAllSubCategory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Model> models);

    @Query("SELECT * FROM Model ORDER BY addedDate DESC")
    LiveData<List<Model>> getAllModel();

    @Query("SELECT * FROM Model WHERE manufacturerId=:manufacturerId")
    LiveData<List<Model>> getModelList(String manufacturerId);

}
