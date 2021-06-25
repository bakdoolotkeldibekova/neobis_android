package com.example.inventoryapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "items_table")
public class Item implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int mId;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "price")
    private Integer mPrice;
    @ColumnInfo(name = "quantity")
    private Integer mQuantity;
    @ColumnInfo(name = "supplier")
    private String mSupplier;
    @ColumnInfo(name = "picture")
    private String mImageUri;

    public Item(String name, int price, int quantity, String supplier, String imageUri) {
        this.mName = name;
        this.mPrice = price;
        this.mQuantity = quantity;
        this.mSupplier = supplier;
        this.mImageUri = imageUri;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Integer getPrice() {
        return mPrice;
    }

    public Integer getQuantity() {
        return mQuantity;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public String getImageUri() { return mImageUri; }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPrice=" + mPrice +
                ", mQuantity=" + mQuantity +
                ", mSupplier='" + mSupplier + '\'' +
                ", mImageUri='" + mImageUri + '\'' +
                '}';
    }
}
