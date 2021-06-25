package com.example.inventoryapp.editor_activity_package;

import android.app.Application;

import com.example.inventoryapp.Item;
import com.example.inventoryapp.data.AppDatabase;

public class EditorPresenter implements EditorContract.Presenter {

    EditorContract.View view;
    AppDatabase appDatabase;

    public EditorPresenter(EditorContract.View view, Application application) {
        this.view = view;
        appDatabase = AppDatabase.getInstance(application);
    }

    @Override
    public void deleteItem(int id) { appDatabase.itemDao().deleteItem(id); }

    @Override
    public void insert(Item item) { appDatabase.itemDao().insert(item); }

    @Override
    public void update(int id, String name, Integer price, Integer quantity, String supplier, String picture)
    { appDatabase.itemDao().update(id, name, price, quantity, supplier, picture); }

}
