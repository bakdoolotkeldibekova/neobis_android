package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textView: TextView = findViewById(R.id.textViewResult)

        if (intent.getStringExtra("error")==null)
            textView.text =intent.getStringExtra("result")
        else
            textView.text = intent.getStringExtra("error")
    }
}

