package com.panaceasoft.admotors.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.panaceasoft.admotors.viewobject.User;
import com.panaceasoft.admotors.viewobject.UserLogin;

import java.util.List;

/**
 * Created by Panacea-Soft on 12/6/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(User user);

    @Query("DELETE FROM User")
    void deleteUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> userList);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM User WHERE userId = :userId")
    LiveData<User> getUserData(String userId);

    @Query("SELECT u.* FROM User u, UserMap um WHERE u.userId = um.userId AND um.mapKey = :value ORDER BY um.sorting")
    LiveData<List<User>> getUserByKey (String value);

    @Query("SELECT u.* FROM User u, UserMap um WHERE u.userId = um.userId AND um.mapKey = :value ORDER BY um.sorting asc limit:limit")
    LiveData<List<User>> getUserByKeyByLimit (String value,String limit);

    //user follow post

    @Query("SELECT isFollowed FROM User WHERE userId =:userId")
    public abstract String selectUserFollowById(String userId);

    @Query("UPDATE User SET isFollowed =:isFollowed WHERE userId =:userId")
    public abstract void updateUserFollowById(String userId,String isFollowed);



    //region User Login Related

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLogin userLogin);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(UserLogin userLogin);

    @Query("SELECT * FROM UserLogin WHERE userId = :userId")
    LiveData<UserLogin> getUserLoginData(String userId);

    @Query("SELECT * FROM UserLogin")
    LiveData<List<UserLogin>> getUserLoginData();

    @Query("DELETE FROM UserLogin")
    void deleteUserLogin();

    //endregion
}
