package com.example.beakgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import java.lang.Exception


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
            if (searchText.text.isEmpty()) {
                Toast.makeText(this, "음식을 입력해보세요", Toast.LENGTH_SHORT).show()
            } else {
                Thread {
                    kotlin.run {
                        try {
                            searchInfo = retrofitInformation.getRetrofit(searchText.text.toString(), this)
                            Log.d("TAG", searchInfo.toString())
                        } catch (e : IOException) {
                            searchInfo = null
                            e.printStackTrace()
                        }
                    }
                }.start()
                val intent = Intent(this, AnswerActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun sendViewModel() {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        myViewModel.currentValue.observe(this, Observer {

        })
    }

    private fun sendEnterKey() {
        searchText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                if (searchText.text.isEmpty()) {
                    Toast.makeText(this, "음식을 입력해보세요", Toast.LENGTH_SHORT).show()
                } else {
                    Thread {
                        kotlin.run {
                            try {
                                searchInfo = retrofitInformation.getRetrofit(searchText.text.toString(), this)
                            } catch (e:Exception){
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
            true
        }
    }

    private fun isCurrect(searchInfo: SearchRequestDTO) {

    }
}