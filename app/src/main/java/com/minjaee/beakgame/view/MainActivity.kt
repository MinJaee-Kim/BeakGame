package com.minjaee.beakgame.view

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
import com.minjaee.beakgame.*
import com.minjaee.beakgame.Information.myViewModel
import com.minjaee.beakgame.dto.SearchRequestDTO
import com.minjaee.beakgame.ActionType
import com.minjaee.beakgame.Information
import com.minjaee.beakgame.LottieDialogFragment
import com.minjaee.beakgame.MyViewModel
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var searchText : EditText
    lateinit var sendBtn : ImageButton
    private var index:Int = 0
    private var searchInfo: SearchRequestDTO? = null
    private val lottieDialogFragment by lazy { LottieDialogFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.mainSearchEt)
        sendBtn = findViewById(R.id.mainSendBtn)
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
                            lottieDialogFragment.show(supportFragmentManager, "loader")
                            Handler(Looper.getMainLooper()).postDelayed({
                                lottieDialogFragment.dismissAllowingStateLoss()
                            }, 2500)
                            searchInfo = Information.getRetrofit(searchText.text.toString())
                            Thread.sleep(3000)
                            if (searchInfo?.let { isCorrect(it) } == 1){
                                myViewModel.updateValue(actionType = ActionType.PLUS)
                                Information.overLabCheck.add(searchText.text.toString())
                                val intent = Intent(this, AnswerActivity::class.java)
                                startActivity(intent)
                            } else if (searchInfo?.let { isCorrect(it) } == 0) {
                                //백종원임
                                Information.result = "여깄어유"
                                val intent = Intent(this, ResultActivity::class.java)
                                Information.link = searchInfo?.items?.get(index)?.link.toString()
                                startActivity(intent)
                            } else if (searchInfo?.let { isCorrect(it) } == 2) {
                                //음식 아님
                                Information.result = "음식이\n아니에유!"
                                val intent = Intent(this, ResultActivity::class.java)
                                startActivity(intent)
                            } else {
                                //중복
                                Information.result = "나왔던\n거에유"
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

    private fun isCorrect(searchInfo: SearchRequestDTO) : Int {
        var isMaking = false

        if (Information.overLabCheck.contains(searchText.text.toString())) {
            index = 100
            return 3
        }

        for (i in searchInfo.items.indices){
            if (searchInfo.items[i].description?.contains("백종원") == true){
                Log.d("TAG", "isCurrect: 백종원"+searchInfo.items[i].description)
                index = i
                return 0
            }

            if (searchInfo.items[i].description?.contains("레시피") == true ||
                searchInfo.items[i].description?.contains("만들기") == true
            ) {
                Log.d("TAG", "isCurrect: 확인" + searchInfo.items[i].description)
                index = 99
                isMaking = true
            }
        }

        if (!isMaking) {
            return 2
        }

        return 1
    }
}