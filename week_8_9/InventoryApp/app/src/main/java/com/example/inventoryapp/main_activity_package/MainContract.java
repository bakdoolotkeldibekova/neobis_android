package com.example.inventoryapp.main_activity_package;

import com.example.inventoryapp.Item;

import java.util.List;

public interface MainContract {

    interface View {}

    interface Presenter {
        void deleteAll();
        List<Item> getAllItems();
        Item getItem(int position);
    }
}
