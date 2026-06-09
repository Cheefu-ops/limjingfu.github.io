package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        btnCalculate = findViewById(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val heightText = etHeight.text.toString()
        val weightText = etWeight.text.toString()

        if (heightText.isEmpty() || weightText.isEmpty()) {
            Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show()
            return
        }

        val heightValue = heightText.toDouble()
        val weightValue = weightText.toDouble()

        if (heightValue <= 0 || weightValue <= 0) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val heightInMeters = heightValue / 100.0
        val bmi = weightValue / (heightInMeters * heightInMeters)

        val category: String
        val message: String

        if (bmi < 18.5) {
            category = "Under Weight"
            message = "You are underweight"
        } else if (bmi < 25) {
            category = "Healthy Weight Range"
            message = "You are in a Healthy Weight R18ange"
        } else if (bmi < 30) {
            category = "Over Weight"
            message = "You are overweight"
        } else {
            category = "Obese"
            message = "You are obese"
        }

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("BMI", bmi)
        intent.putExtra("CATEGORY", category)
        intent.putExtra("MESSAGE", message)
        startActivity(intent)
    }
}