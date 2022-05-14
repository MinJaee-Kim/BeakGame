package com.example.beakgame.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beakgame.*

class AnswerActivity : AppCompatActivity() {
    private lateinit var answerBtn:Button
    private lateinit var answerScore:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerBtn = findViewById(R.id.answerBtn)
        answerScore = findViewById(R.id.answerScore)

        Information.myViewModel.currentValue.observe(this) {
            answerScore.text = it.toString()
        }
        answerBtnClick()
    }

    private fun answerBtnClick() {
        answerBtn.setOnClickListener {
            finish()
        }
    }
}