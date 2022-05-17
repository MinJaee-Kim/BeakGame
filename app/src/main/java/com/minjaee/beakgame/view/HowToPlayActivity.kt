package com.minjaee.beakgame.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.minjaee.beakgame.R

class HowToPlayActivity : AppCompatActivity() {
    lateinit var closeBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howtoplay)

        closeBtn = findViewById(R.id.howtoCloseBtn)

        closeBtn.setOnClickListener{
            finish()
        }
    }
}