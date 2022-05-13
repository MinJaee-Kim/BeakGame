package com.example.beakgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var searchText : EditText
    lateinit var sendBtn : ImageButton
    private val retrofitInformation = RetrofitInformation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.searchEditText)
        sendBtn = findViewById(R.id.sendBtn)

        sendBtnTouch()
        sendEnterKey()
    }


    private fun sendBtnTouch() {
        sendBtn.setOnClickListener {
            if (searchText.text.isEmpty()) {
                Toast.makeText(this, "음식을 입력해보세요", Toast.LENGTH_SHORT).show()
            } else {
                retrofitInformation.getRetrofit(searchText.text.toString(), this)
            }
        }
    }

    private fun sendEnterKey() {
        searchText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                if (searchText.text.isEmpty()) {
                    Toast.makeText(this, "음식을 입력해보세요", Toast.LENGTH_SHORT).show()
                } else {
                    retrofitInformation.getRetrofit(searchText.text.toString(), this)
                }
            }
            true
        }
    }
}