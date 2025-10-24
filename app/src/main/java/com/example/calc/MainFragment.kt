package com.example.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calc.databinding.FragmentMainBinding

class MainFragment : Fragment() {


    var firstNumber = 0.0
    var secondNumber = 0.0
    var operation = ""
    var isNewInput = true
    private lateinit var tvDisplay: TextView

    private var _binding : FragmentMainBinding? = null
    private val binding : FragmentMainBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding
            .inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvDisplay=binding.tvDisplay

        binding.button1.setOnClickListener { appendToDisplay("1") }
        binding.button2.setOnClickListener { appendToDisplay("2") }
        binding.button3.setOnClickListener { appendToDisplay("3") }
        binding.button4.setOnClickListener { appendToDisplay("4") }
        binding.button5.setOnClickListener { appendToDisplay("5") }
        binding.button6.setOnClickListener { appendToDisplay("6") }
        binding.button7.setOnClickListener { appendToDisplay("7") }
        binding.button8.setOnClickListener { appendToDisplay("8") }
        binding.button9.setOnClickListener { appendToDisplay("9") }
        binding.button0.setOnClickListener { appendToDisplay("0") }

        binding.buttonPlus.setOnClickListener { performOperation("+") }
        binding.buttonMinus.setOnClickListener { performOperation("-") }
        binding.buttonMultiply.setOnClickListener { performOperation("*") }
        binding.buttonDivide.setOnClickListener { performOperation("/") }
        binding.buttonEquals.setOnClickListener { calculateResult() }
        binding.buttonClear.setOnClickListener { clearDisplay() }



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