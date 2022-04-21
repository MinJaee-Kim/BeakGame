package com.example.beakgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val id = "gnVo4vAboIZQYqGSCEXz"
    private val secret = "TqeFWa2Ffe"
    lateinit var searchText : EditText
    lateinit var sendBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.searchEditText)
        sendBtn = findViewById(R.id.sendBtn)

        searchFood()
    }

    fun searchFood() {
        val retrofitClient = RetrofitClient()
        
        retrofitClient.searchAPI.getSearchInfo(id,secret,"20","김치찌개").enqueue(object : Callback<SearchRequestDTO> {
            override fun onResponse(
                call: Call<SearchRequestDTO>,
                response: Response<SearchRequestDTO>
            ) {
                val searchInfo = response.body()
                Log.d("통신 성공", searchInfo?.display.toString())
            }

            override fun onFailure(call: Call<SearchRequestDTO>, t: Throwable) {
                Log.d("통신 실패", t.message.toString())
            }
        })
    }
}