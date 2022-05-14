package com.example.beakgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beakgame.dto.SearchRequestDTO
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var searchText : EditText
    lateinit var sendBtn : ImageButton
    lateinit var myViewModel: MyViewModel
    private val retrofitInformation = RetrofitInformation
    private var searchInfo: SearchRequestDTO? = null

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
            isCallFood()
        }
    }

    private fun sendEnterKey() {
        searchText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                isCallFood()
            }
            true
        }
    }

    private fun isCallFood() {
        if (searchText.text.isEmpty()) {
            Toast.makeText(this, "음식을 입력해보세요", Toast.LENGTH_SHORT).show()
        } else {
            Thread {
                kotlin.run {
                    try {
                        if (retrofitInformation.getRetrofit(searchText.text.toString(), this)==null){
                            Handler(Looper.getMainLooper()).postDelayed({
                                Toast.makeText(this, "음식을 입력해주세요", Toast.LENGTH_SHORT).show()
                            }, 0)
                        } else {
                            searchInfo = retrofitInformation.getRetrofit(searchText.text.toString(), this)
                            Log.d("TAG", searchInfo.toString())
                            val intent = Intent(this, AnswerActivity::class.java)
                            startActivity(intent)
                        }
                    } catch (e : IOException) {
                        searchInfo = null
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }

    private fun sendViewModel() {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        myViewModel.currentValue.observe(this, Observer {

        })
    }

    private fun isCurrect(searchInfo: SearchRequestDTO) {

    }
}