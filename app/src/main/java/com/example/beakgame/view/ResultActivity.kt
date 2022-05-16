package com.example.beakgame.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beakgame.ActionType
import com.example.beakgame.Information
import com.example.beakgame.R

class ResultActivity : AppCompatActivity() {
    private lateinit var resultText:TextView
    private lateinit var resultScore:TextView
    private lateinit var resultImage:ImageView
    private lateinit var resultBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.resultText)
        resultScore = findViewById(R.id.resultScore)
        resultImage = findViewById(R.id.resultIv1)
        resultBtn = findViewById(R.id.resultBtn)

        Information.myViewModel.currentValue.observe(this) {
            resultScore.text = it.toString()
        }
        reStartBtnClick()
    }

    private fun reStartBtnClick() {
        resultBtn.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Information.overLabCheck.clear()
        Information.myViewModel.updateValue(actionType = ActionType.RESET)
    }
}