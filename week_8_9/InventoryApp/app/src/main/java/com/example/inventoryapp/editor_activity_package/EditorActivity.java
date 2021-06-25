package com.example.inventoryapp.editor_activity_package;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.inventoryapp.Item;
import com.example.inventoryapp.main_activity_package.MainActivity;
import com.example.inventoryapp.R;

import java.io.Serializable;

public class EditorActivity extends AppCompatActivity implements EditorContract.View, Serializable {

    public static final String EXTRA_ID = "com.example.inventoryapp.editor_activity_package.EXTRA_ID";

    private static final int GALLERY_REQUEST_CODE = 123;
    EditorContract.Presenter presenter;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextSupplier;
    private ImageView imageView;
    Uri imageUri;
    String image;
    String galleryImage;
    private NumberPicker quantity;
    private Button buttonImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new EditorPresenter(this,getApplication());

        editTextName = findViewById(R.id.name_editText);
        editTextPrice = findViewById(R.id.price_editText);
        editTextSupplier = findViewById(R.id.supplier_editText);
        imageView = findViewById(R.id.imageView);
        buttonImage = findViewById(R.id.image_btn);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

        quantity = findViewById(R.id.number_picker);
        quantity.setMinValue(0);
        quantity.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            getItemFromIntent((Item) intent.getSerializableExtra("LIST"));
        }
        else {
            setTitle("Add Note");
        }
    }

    private void getItemFromIntent(Item item) {
        editTextName.setText(item.getName());
        editTextPrice.setText(String.valueOf(item.getPrice()));
        editTextSupplier.setText(item.getSupplier());
        quantity.setValue(item.getQuantity());
        galleryImage = item.getImageUri();
        Glide
                    .with(getApplicationContext())
                    .load(galleryImage)
                    .into(imageView);
        if(galleryImage == null){
            buttonImage.setText("Set image");
        } else{
            buttonImage.setText("Change image");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            image = imageUri.toString();
            Glide
                    .with(getApplicationContext())
                    .load(image)
                    .into(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.editor_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                saveNote();
                return true;
            case R.id.delete_item_btn:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete this item?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = getIntent().getIntExtra(EXTRA_ID, -1);
                        presenter.deleteItem(id);
                        finish();
                        Toast.makeText(EditorActivity.this, "Item is deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String name = editTextName.getText().toString();
        String priceString = editTextPrice.getText().toString();
        String supplier = editTextSupplier.getText().toString();
        int quantityValue = quantity.getValue();
        if (name.trim().isEmpty() || supplier.trim().isEmpty() || priceString.trim().isEmpty()) {
            Toast.makeText(this, "Please make sure all the fields are entered", Toast.LENGTH_SHORT).show();
            return;
        }
        int price = Integer.parseInt(priceString);
        Item item = new Item(name,price,quantityValue,supplier,image);

        Intent intent = getIntent();
        Item item2 =(Item) intent.getSerializableExtra("LIST");

        if (item2 != null) {
            int id = item2.getId();
            presenter.update(id, item.getName(), item.getPrice(), item.getQuantity(), item.getSupplier(), item.getImageUri());
            Toast.makeText(EditorActivity.this, "Item is updated", Toast.LENGTH_SHORT).show();
        }
        else {
            presenter.insert(item);
            Toast.makeText(EditorActivity.this, "Item is saved", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result","Back Press");
        setResult(MainActivity.RESULT_CANCELED,intent);
        finish();
        super.onBackPressed();
    }
}
