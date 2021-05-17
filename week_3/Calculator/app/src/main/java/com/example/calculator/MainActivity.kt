package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var firstNumber: EditText
    lateinit var secondNumber: EditText

    var firstNum: Int = 0
    var secondNum: Int = 0
    var resultNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumber = findViewById(R.id.editViewFirstNumber)
        secondNumber = findViewById(R.id.editViewSecondNumber)

        var buttonPlus: Button = findViewById(R.id.buttonPlus)
        var buttonMinus: Button = findViewById(R.id.buttonMinus)
        var buttonMultiple: Button = findViewById(R.id.buttonMultiple)
        var buttonDivide: Button = findViewById(R.id.buttonDivide)

        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonMultiple.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        firstNum = firstNumber.text.toString().toInt()
        secondNum = secondNumber.text.toString().toInt()

        val intent = Intent(this,ResultActivity::class.java)

        when (v!!.id) {
            R.id.buttonPlus -> {
                resultNum = firstNum + secondNum
            }
            R.id.buttonMinus -> {
                resultNum = firstNum - secondNum
            }
            R.id.buttonMultiple -> {
                resultNum = firstNum * secondNum
            }
            R.id.buttonDivide -> {
                if (secondNum == 0) {
                    Toast.makeText(this, "Impossible divide to ZERO!", Toast.LENGTH_SHORT).show()
                    intent.putExtra("error", "Impossible divide to ZERO!")
                }
                else
                    resultNum = firstNum / secondNum
            }
        }
        val string = resultNum.toString()
        intent.putExtra("result", string)
        startActivity(intent)

    }

}