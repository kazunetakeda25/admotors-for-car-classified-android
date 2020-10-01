package com.panaceasoft.admotors.db;

import com.panaceasoft.admotors.viewobject.Noti;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Noti noti);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Noti noti);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotificationList(List<Noti> NotiList);

    @Query("DELETE FROM Noti")
    void deleteAllNotificationList();

    @Query("SELECT * FROM Noti order by addedDate desc")
    LiveData<List<Noti>> getAllNotificationList();

    @Query("DELETE FROM Noti WHERE id = :id")
    void deleteNotificationById(String id);

    @Query("SELECT * FROM Noti WHERE id = :id")
    LiveData<Noti> getNotificationById(String id);

}
