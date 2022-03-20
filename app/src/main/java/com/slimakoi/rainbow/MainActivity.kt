package com.slimakoi.rainbow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNickname = findViewById<EditText>(R.id.editTextNickname)
        val buttonAddition = findViewById<Button>(R.id.buttonAddition)
        val buttonSubtraction = findViewById<Button>(R.id.buttonSubtraction)
        val buttonMultiplication = findViewById<Button>(R.id.buttonMultiplication)
        val buttonDivision = findViewById<Button>(R.id.buttonDivision)

        buttonAddition.setOnClickListener {
            if (editTextNickname.text.isEmpty()) {
                Toast.makeText(this, "Nickname is required!", Toast.LENGTH_SHORT).show()
            } else {
                nickname = editTextNickname.text.toString()
                startActivity(Intent(this, CalculationActivity::class.java).putExtra(CalculationActivity.calculationType, "Addition"))
            }
        }

        buttonSubtraction.setOnClickListener {
            if (editTextNickname.text.isEmpty()) {
                Toast.makeText(this, "Nickname is required!", Toast.LENGTH_SHORT).show()
            } else {
                nickname = editTextNickname.text.toString()
                startActivity(Intent(this, CalculationActivity::class.java).putExtra(CalculationActivity.calculationType, "Subtraction"))
            }
        }

        buttonMultiplication.setOnClickListener {
            if (editTextNickname.text.isEmpty()) {
                Toast.makeText(this, "Nickname is required!", Toast.LENGTH_SHORT).show()
            } else {
                nickname = editTextNickname.text.toString()
                startActivity(Intent(this, CalculationActivity::class.java).putExtra(CalculationActivity.calculationType, "Multiplication"))
            }
        }

        buttonDivision.setOnClickListener {
            if (editTextNickname.text.isEmpty()) {
                Toast.makeText(this, "Nickname is required!", Toast.LENGTH_SHORT).show()
                nickname = editTextNickname.text.toString()
            } else {
                startActivity(Intent(this, CalculationActivity::class.java).putExtra(CalculationActivity.calculationType, "Division"))
            }
        }
    }
}