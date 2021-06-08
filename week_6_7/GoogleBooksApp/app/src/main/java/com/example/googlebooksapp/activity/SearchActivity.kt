package com.example.googlebooksapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.googlebooksapp.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchText = findViewById<View>(R.id.text_search) as EditText
        val searchButton: Button = findViewById<View>(R.id.button_search) as Button

        searchButton.setOnClickListener {
            val text = searchText.text.toString()
            if (text.isEmpty() || text == " ") {
                Toast.makeText(this, "Enter the name of the book", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, BookActivity::class.java)
                intent.putExtra("Search", text)
                startActivity(intent)
            }
        }
    }
}