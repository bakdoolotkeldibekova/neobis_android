package com.example.inventoryapp.main_activity_package;

import android.app.Application;

import com.example.inventoryapp.Item;
import com.example.inventoryapp.data.AppDatabase;

import java.util.List;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;
    private AppDatabase appDatabase;

    public MainPresenter(MainContract.View view, Application application){
        this.view = view;
        appDatabase = AppDatabase.getInstance(application);
    }

    @Override
    public void deleteAll() { appDatabase.itemDao().deleteAllNotes(); }

    @Override
    public List<Item> getAllItems() { return appDatabase.itemDao().getAllNotes(); }

    @Override
    public Item getItem(int position) {
        return getItem(position);
    }

}
