package com.example.beakgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val id = "gnVo4vAboIZQYqGSCEXz"
    private val secret = "TqeFWa2Ffe"
    lateinit var searchText : EditText
    lateinit var sendBtn : ImageButton
    val retrofitInformation = RetrofitInformation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.searchEditText)
        sendBtn = findViewById(R.id.sendBtn)

        sendBtnTouch()
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
}