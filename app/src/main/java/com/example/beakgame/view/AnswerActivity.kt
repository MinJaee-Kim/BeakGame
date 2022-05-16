package com.example.beakgame.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.airbnb.lottie.Lottie
import com.example.beakgame.*

class AnswerActivity : AppCompatActivity() {
    private lateinit var answerBtn:Button
    private lateinit var answerScore:TextView
    private lateinit var animation:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerBtn = findViewById(R.id.answerBtn)
        answerScore = findViewById(R.id.answerScore)
        animation = findViewById(R.id.answerLottAni)

        Information.myViewModel.currentValue.observe(this) {
            answerScore.text = it.toString()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            animation.visibility = View.INVISIBLE
        }, 2600)
        answerBtnClick()
    }

    private fun answerBtnClick() {
        answerBtn.setOnClickListener {
            finish()
        }
    }
}