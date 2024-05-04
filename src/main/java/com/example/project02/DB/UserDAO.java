package com.example.project02.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//import com.example.project02.Items;
import com.example.project02.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User...users);

    @Update
    void update(User...users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();


    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUser = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mLogID = :userId")
    User getUserByUserId(int userId);

    // ITEMS DAO
//    @Insert
//    void insert(Items...items);
//
//    @Update
//    void update(Items...items);
//
//    @Delete
//    void delete(Items... items);
//
//    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE)
//    List<Items> getAllItems();
//
//
//    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemName = :item")
//    Items getItemByItemName(String item);
//
//
//    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemID = :itemId")
//    Items getItembyItemId(int itemId);



    // @Query("SELECT * FROM user WHERE mUser = :testuser1 AND mPass = :testuser1")
    // SELECT * FROM user WHERE mUser = "testuser1" AND mPass = "testuser1"
    // write same for admin

}
