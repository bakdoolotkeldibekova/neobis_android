package com.example.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textCount: TextView
    lateinit var button: Button

    companion object {
        const val COUNT_KEY = "COUNT_KEY"
    }

    private var count = 0
        set(value) {
            field = value
            textCount.text = value.toString().padStart(4, '0')
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyTag", "onCreate")

        textCount = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            count++
            textCount.text = count.toString().padStart(4, '0')
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        count = savedInstanceState.getInt(COUNT_KEY)
    }
}
