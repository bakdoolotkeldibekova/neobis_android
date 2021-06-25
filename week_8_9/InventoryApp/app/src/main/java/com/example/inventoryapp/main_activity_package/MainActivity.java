package com.example.inventoryapp.main_activity_package;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.editor_activity_package.EditorActivity;
import com.example.inventoryapp.Item;
import com.example.inventoryapp.ItemAdapter;
import com.example.inventoryapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MainPresenter(this, getApplication());
        setViewItems();
        FloatingActionButton buttonAddNote = findViewById(R.id.fab);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setViewItems() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        itemAdapter = new ItemAdapter(this);
        itemAdapter.setItems(presenter.getAllItems());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.invalidateItemDecorations();
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Item item) {
                Intent intentEditMode = new Intent(MainActivity.this, EditorActivity.class);
                intentEditMode.putExtra(EditorActivity.EXTRA_ID, item.getId());
                intentEditMode.putExtra("LIST", item);
                startActivity(intentEditMode);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_btn:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete all items?");
                // add the buttons
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(presenter.getAllItems() != null) {
                            presenter.deleteAll();
                            setViewItems();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Database is empty", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(MainActivity.this, "All items deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}