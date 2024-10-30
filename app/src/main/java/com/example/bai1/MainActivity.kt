package com.example.bai1

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val editText = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textView = findViewById<TextView>(R.id.textViewError)

        btnShow.setOnClickListener {
            val inputText = editText.text.toString()
            if (TextUtils.isEmpty(inputText)) {
                textView.text = "Vui lòng nhập số nguyên dương."
                return@setOnClickListener
            }

            val n = inputText.toInt()
            val selectedId = radioGroup.checkedRadioButtonId
            val list = when (selectedId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioPrime -> getPrimeNumbers(n)
                else -> listOf()
            }

            if (list.isEmpty()) {
                textView.text = "Không có số thỏa mãn."
            } else {
                textView.text = ""
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
                listView.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getPrimeNumbers(n: Int): List<Int> {
        return (1..n).filter { isPrime(it) }
    }

    private fun isPrime(num: Int): Boolean {
        val sqrt = Math.sqrt(num.toDouble()).toInt()
        return sqrt * sqrt == num
    }
}
