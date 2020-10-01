package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.ItemFavourite;
import com.panaceasoft.admotors.viewobject.ItemFromFollower;

import java.util.List;


@Dao
public interface ItemDao {

    //region Item list

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Item> itemList);

    @Query("DELETE FROM Item")
    void deleteAll();

    @Query("DELETE FROM Item WHERE id = :id")
    void deleteItemById(String id);

    @Query("SELECT i.* FROM Item i, ItemMap im WHERE i.id = im.itemId AND im.mapKey = :value ORDER BY im.sorting asc")
    LiveData<List<Item>> getItemByKey (String value);

    @Query("SELECT i.* FROM Item i, ItemMap im WHERE i.id = im.itemId AND im.mapKey = :value ORDER BY im.sorting asc limit:limit")
    LiveData<List<Item>> getItemByKeyByLimit (String value,String limit);

    //item favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(ItemFavourite itemFavourite);

    @Query("DELETE FROM ItemFavourite")
    void deleteAllFavouriteItems();

    @Query("SELECT isFavourited FROM Item WHERE id =:itemId")
    public abstract String selectFavouriteById(String itemId);

    @Query("UPDATE Item SET isFavourited =:is_favourited WHERE id =:itemId")
    public abstract void updateProductForFavById(String itemId,String is_favourited);

    @Query("DELETE FROM ItemFavourite where itemId = :itemId")
    public abstract void deleteFavouriteItemByItemId(String itemId);

    @Query("SELECT prd.* FROM Item prd, ItemFavourite fp WHERE prd.id = fp.itemId order by fp.sorting ")
    LiveData<List<Item>> getAllFavouriteProducts();

    @Query("SELECT max(sorting) from ItemFavourite")
    int getMaxSortingFavourite();

    //item from follower

    @Query("DELETE FROM ItemFromFollower")
    void deleteAllItemFromFollower();

    @Query("DELETE FROM ItemFromFollower WHERE userId =:followUserId")
    void deleteAllItemFromFollowerByUserId(String followUserId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItemFromFollower(ItemFromFollower itemFromFollower);

    @Query("SELECT item.* FROM Item item, ItemFromFollower itemFromFollower WHERE item.id = itemFromFollower.itemId order by itemFromFollower.sorting ")
    LiveData<List<Item>> getAllItemFromFollower();

    //item detail

    @Query("SELECT * FROM Item WHERE id =:itemId ORDER BY addedDate DESC")
    public abstract LiveData<Item> getItemById(String itemId);

    @Query("SELECT * FROM Item WHERE id =:itemId ORDER BY addedDate DESC")
    public abstract Item getItemObjectById(String itemId);

    //endregion
}
