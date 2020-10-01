package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.ItemHistory;

import java.util.List;

@Dao
public abstract class HistoryDao {
    //region history

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(ItemHistory basket);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract void update(ItemHistory basket);

    @Query("DELETE FROM ItemHistory")
    public abstract void deleteHistoryItem();

    @Query("SELECT * FROM (SELECT * FROM ItemHistory ORDER BY historyDate DESC) LIMIT :limit Offset 0")
    public abstract LiveData<List<ItemHistory>> getAllHistoryItemListData(String limit);

    @Query("DELETE FROM ItemHistory WHERE id =:id")
    public abstract void deleteHistoryItemById(String id);

    //endregion
}
