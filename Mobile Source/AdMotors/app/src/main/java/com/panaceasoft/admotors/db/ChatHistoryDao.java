package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.ChatHistory;
import com.panaceasoft.admotors.viewobject.ChatHistoryMap;

import java.util.List;

/**
 * Created by Panacea-Soft on 2019-06-25.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Dao
public interface ChatHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatHistory chatHistory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ChatHistory> chatHistoryList);

    @Query("DELETE FROM ChatHistory")
    void deleteAll();

    @Query("DELETE FROM ChatHistory WHERE id = :id")
    void deleteItemById(String id);

    @Query("SELECT * FROM ChatHistory ch WHERE ch.id = :id LIMIT 1")
    LiveData<ChatHistory> getChatHistoryById(String id);

    @Query("SELECT * FROM ChatHistory ch WHERE ch.item_id = :itemId AND ch.buyer_userId = :buyerUserId AND ch.seller_userId = :sellerUserId LIMIT 1")
    LiveData<ChatHistory> getChatHistory(String itemId, String buyerUserId, String sellerUserId);

    @Query("SELECT ch.* FROM ChatHistory ch, ChatHistoryMap chm WHERE ch.id = chm.chatHistoryId AND chm.mapKey = :value ORDER BY chm.sorting asc")
    LiveData<List<ChatHistory>> getChatHistoryByKey (String value);

    @Query("DELETE FROM ChatHistory WHERE item_id = :itemId AND buyer_userId = :buyerUserId AND seller_userId = :sellerUserId")
    void deleteChatHistory(String itemId, String buyerUserId, String sellerUserId);

    // Chat History Map

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatHistoryMap chatHistoryMap);

    @Query("DELETE FROM ChatHistoryMap WHERE mapKey = :key")
    void deleteByMapKey(String key);

    @Query("SELECT max(sorting) from ChatHistoryMap WHERE mapKey = :value ")
    int getMaxSortingByValue(String value);

    @Query("SELECT * FROM ChatHistoryMap")
    List<ChatHistoryMap> getAll();

    @Query("DELETE FROM ChatHistoryMap")
    void deleteAllChatHistoryMap();


}
