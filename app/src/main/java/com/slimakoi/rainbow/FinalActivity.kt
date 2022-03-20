package com.slimakoi.rainbow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FinalActivity : AppCompatActivity() {
    companion object {
        const val finalType = "null"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val finalType = intent.getStringExtra(FinalActivity.finalType)

        val finalText = findViewById<TextView>(R.id.finalText)
        val imageWon = findViewById<ImageView>(R.id.imageWon)
        val imageLost = findViewById<ImageView>(R.id.imageLost)

        if (finalType == "Won") {
            imageWon.visibility = View.VISIBLE
            finalText.text = "Congratulations $nickname, you've answered $right_questions questions right out of $max_questions!"
        } else {
            imageLost.visibility = View.VISIBLE
            finalText.text = "Sad news $nickname, you've answered $right_questions questions right but you lost all of your lives!"
        }
    }
}