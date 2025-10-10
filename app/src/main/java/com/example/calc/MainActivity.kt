package com.example.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var firstNumber = 0.0
    var secondNumber = 0.0
    var operation = ""
    var isNewInput = true
    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button0: Button = findViewById(R.id.button0)
        tvDisplay = findViewById(R.id.tvDisplay)

        button1.setOnClickListener { appendToDisplay("1") }
        button2.setOnClickListener { appendToDisplay("2") }
        button3.setOnClickListener { appendToDisplay("3") }
        button4.setOnClickListener { appendToDisplay("4") }
        button5.setOnClickListener { appendToDisplay("5") }
        button6.setOnClickListener { appendToDisplay("6") }
        button7.setOnClickListener { appendToDisplay("7") }
        button8.setOnClickListener { appendToDisplay("8") }
        button9.setOnClickListener { appendToDisplay("9") }
        button0.setOnClickListener { appendToDisplay("0") }

        findViewById<Button>(R.id.buttonPlus).setOnClickListener { performOperation("+") }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { performOperation("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { performOperation("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { performOperation("/") }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clearDisplay() }

    }

    private fun appendToDisplay(value: String) {
        if (isNewInput) {
            tvDisplay.text = value
            isNewInput = false
        } else {
            tvDisplay.text = tvDisplay.text.toString() + value
        }
    }

    private fun performOperation(operator: String) {
        firstNumber = tvDisplay.text.toString().toDouble()
        operation = operator
        isNewInput = true
    }

    private fun calculateResult() {
        secondNumber = tvDisplay.text.toString().toDouble()
        if (operation == "/" && secondNumber == 0.0) {
            tvDisplay.text = "Error"
            isNewInput = true
            return
        }
        when (operation) {
            "+" -> tvDisplay.text = (firstNumber + secondNumber).toString()
            "-" -> tvDisplay.text = (firstNumber - secondNumber).toString()
            "*" -> tvDisplay.text = (firstNumber * secondNumber).toString()
            "/" -> tvDisplay.text = (firstNumber / secondNumber).toString()
        }
        isNewInput = true
    }

    private fun clearDisplay() {
        tvDisplay.text = "0"
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
        isNewInput = true
    }


}