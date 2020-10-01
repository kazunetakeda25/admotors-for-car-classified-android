package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.messageHolder.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Message message);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Message> messageList);

    @Query("DELETE FROM Message WHERE sessionId = :value")
    void deleteAll(String value);

    @Query("DELETE FROM Message WHERE id = :id")
    void deleteMessageById(String id);

    @Query("DELETE FROM Message")
    void deleteAllMessage();

    @Query("SELECT * FROM MESSAGE WHERE sessionId=:key AND itemId=:itemId ORDER BY addedDate")
    LiveData<List<Message>> getMessages(String key, String itemId);

    @Query("SELECT * FROM MESSAGE")
    LiveData<List<Message>> getAllMessages();
}
