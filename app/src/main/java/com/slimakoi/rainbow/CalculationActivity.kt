package com.slimakoi.rainbow

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.slimakoi.rainbow.stuff.Value
import org.jetbrains.anko.doAsync

fun getQuestion(calculationType: String): String {
    var question = "null"

    when (calculationType) {
        "Addition" -> {
            question = "${(0..10).random()} + ${(0..10).random()}"
        }
        "Subtraction" -> {
            question = "${(0..10).random()} - ${(0..10).random()}"
        }
        "Multiplication" -> {
            question = "${(0..10).random()} * ${(0..10).random()}"
        }
        "Division" -> {
            question = "${(0..10).random()} / ${(0..10).random()}"
        }
    }

    return question
}

class CalculationActivity : AppCompatActivity() {
    companion object {
        const val calculationType = "null"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        val calculationType = intent.getStringExtra(calculationType)

        val textCalculation = findViewById<TextView>(R.id.textCalculation)
        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val textQuestions = findViewById<TextView>(R.id.textQuestions)

        val heartOne = findViewById<ImageView>(R.id.heartOne)
        val heartTwo = findViewById<ImageView>(R.id.heartTwo)
        val heartThree = findViewById<ImageView>(R.id.heartThree)

        var questionNumber = 0

        var enabled = true

        doAsync {
            while (enabled) {
                enabled = false

                var question = getQuestion(calculationType.toString())
                textCalculation.text = question

                buttonNext.setOnClickListener {
                    if (editTextInput.text.isEmpty()) {
                        val alert = AlertDialog.Builder(this@CalculationActivity)
                        alert.setTitle("You forgot something")
                        alert.setMessage("Answer cannot be empty!")
                        alert.show()
                        return@setOnClickListener
                    }

                    val questionAnswer = Value(question).resolve()

                    if (editTextInput.text.toString().toFloat() == questionAnswer) {
                        editTextInput.setText("")

                        questionNumber += 1
                        right_questions += 1
                        textQuestions.text = "$questionNumber / $max_questions"
                        question = getQuestion(calculationType.toString())
                        textCalculation.text = question
                        enabled = true

                        if (questionNumber == (max_questions + 1)) {
                            finish()
                            startActivity(Intent(this@CalculationActivity, FinalActivity::class.java).putExtra(FinalActivity.finalType, "Won"))
                        }
                    } else {
                        editTextInput.setText("")

                        questionNumber += 1
                        textQuestions.text = "$questionNumber / $max_questions"
                        question = getQuestion(calculationType.toString())
                        textCalculation.text = question

                        lives -= 1

                        if (lives == 2) {
                            heartThree.visibility = View.INVISIBLE
                        }

                        if (lives == 1) {
                            heartTwo.visibility = View.INVISIBLE
                        }

                        if (lives == 0) {
                            heartOne.visibility = View.INVISIBLE
                        }

                        if (lives == -1) {
                            lives = max_lives
                            finish()
                            startActivity(Intent(this@CalculationActivity, FinalActivity::class.java).putExtra(FinalActivity.finalType, "Lost"))
                        }

                        enabled = true
                    }
                }
            }
        }
    }
}