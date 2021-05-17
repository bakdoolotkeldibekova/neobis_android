package com.example.greeterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        textView = findViewById(R.id.textView)

        findViewById<Button>(R.id.buttonGreet).setOnClickListener{
            showText(it)
        }
    }

    private fun showText(view: View) {
        if (editTextName.text.toString() == ""){
            Toast.makeText(this, "Enter the name, please!", Toast.LENGTH_SHORT).show()
            textView.text = ""
        }
        else
            textView.text = "Hello, " + editTextName.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val text = textView.text.toString()
        outState.putString("savedText", text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val text = savedInstanceState.getString("savedText").toString()
        textView.text = text
    }
}