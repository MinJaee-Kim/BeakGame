package com.example.beakgame.view

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
import androidx.lifecycle.ViewModelProvider
import com.example.beakgame.ActionType
import com.example.beakgame.MyViewModel
import com.example.beakgame.R
import com.example.beakgame.Information
import com.example.beakgame.Information.myViewModel
import com.example.beakgame.dto.SearchRequestDTO
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var searchText : EditText
    lateinit var sendBtn : ImageButton
    private var searchInfo: SearchRequestDTO? = null
    private var linkedList:LinkedList<String> = LinkedList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.searchEditText)
        sendBtn = findViewById(R.id.sendBtn)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        sendBtnTouch()
        sendEnterKey()
    }


    private fun sendBtnTouch() {
        sendBtn.setOnClickListener {
            isCallFood()
        }
    }

    private fun sendEnterKey() {
        searchText.setOnKeyListener { _, keyCode, event ->
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
                        if (Information.getRetrofit(searchText.text.toString()) ==null){
                            Handler(Looper.getMainLooper()).postDelayed({
                                Toast.makeText(this, "음식을 입력해주세요", Toast.LENGTH_SHORT).show()
                            }, 0)
                        } else {
                            searchInfo = Information.getRetrofit(searchText.text.toString())
                            linkedList.add(searchText.text.toString())
                            if (searchInfo?.let { isCorrect(it) } == true){
                                myViewModel.updateValue(actionType = ActionType.PLUS)
                                val intent = Intent(this, AnswerActivity::class.java)
                                startActivity(intent)
                            } else {
                                myViewModel.updateValue(actionType = ActionType.RESET)
                                val intent = Intent(this, ResultActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    } catch (e : IOException) {
                        searchInfo = null
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }

    private fun isCorrect(searchInfo: SearchRequestDTO) : Boolean {
        var isBeak = false
        var isCorrect = false

        for (i in searchInfo.items.indices){
            if (!isBeak) {
                if (searchInfo.items[i].description?.contains("백종원") == true){
                    Log.d("TAG", "isCurrect: 백종원"+searchInfo.items[i].description)
                    isBeak = true
                }
            }

            if (!isCorrect) {
                if (searchInfo.items[i].description?.contains("레시피") == true ||
                    searchInfo.items[i].description?.contains("만들기") == true
                ) {
                    Log.d("TAG", "isCurrect: 확인" + searchInfo.items[i].description)
                    isCorrect = true
                }
            }
        }

        return if (isBeak) {
            false
        } else isCorrect
    }
}