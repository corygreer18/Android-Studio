package com.example.project02;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDatabase;

@Entity(tableName = AppDatabase.ITEM_TABLE)
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int mItemID;


    private String mItemName;
    private int mItemPrice;
    private int mItemQuantity;

    public int getItemID() {
        return mItemID;
    }

    public void setItemID(int itemID) {
        mItemID = itemID;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public int getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        mItemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return mItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        mItemQuantity = itemQuantity;
    }

    public Item(String itemName, int itemPrice, int itemQuantity) {
        mItemName = itemName;
        mItemPrice = itemPrice;
        mItemQuantity = itemQuantity;
    }
}
