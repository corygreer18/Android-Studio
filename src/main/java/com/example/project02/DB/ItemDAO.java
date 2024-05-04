package com.example.project02.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insert(Item...items);

    @Update
    void update(Item...items);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE)
    List<Item> getAllItems();


    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemName = :item")
    Item getItemByItemName(String item);


    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemID = :itemId")
    Item getItembyItemId(int itemId);



    // @Query("SELECT * FROM user WHERE mUser = :testuser1 AND mPass = :testuser1")
    // SELECT * FROM user WHERE mUser = "testuser1" AND mPass = "testuser1"
    // write same for admin

}
