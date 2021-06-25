package com.example.inventoryapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventoryapp.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);


    //    @Update (onConflict = OnConflictStrategy.REPLACE)
    @Query("UPDATE items_table SET name = :name, price = :price, quantity = :quantity," +
            " supplier =:supplier, picture =:picture WHERE mId = :id")
    void update(int id,String name, Integer price, Integer quantity, String supplier, String picture);

    @Query("SELECT * FROM items_table")
    List<Item> getAllNotes();

    @Query("DELETE FROM items_table")
    void deleteAllNotes();

    @Query("DELETE FROM items_table WHERE mId = :id")
    void deleteItem(int id);
}
