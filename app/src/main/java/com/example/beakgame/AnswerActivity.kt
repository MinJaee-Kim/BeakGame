package com.example.beakgame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AnswerActivity : AppCompatActivity() {
    private lateinit var answerBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerBtn = findViewById(R.id.answerBtn)
        answerBtnClick()

    }

    private fun answerBtnClick() {
        answerBtn.setOnClickListener {
            finish()
        }
    }
}