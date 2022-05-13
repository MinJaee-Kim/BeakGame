package com.example.beakgame

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

object RetrofitInformation {
    private val id = "gnVo4vAboIZQYqGSCEXz"
    private val secret = "TqeFWa2Ffe"
    private lateinit var searchInfo:SearchRequestDTO

    fun getRetrofit(search: String, context: Context){
        val retrofitClient = RetrofitClient

            retrofitClient.searchAPI.getSearchInfo(id,secret,"20",search).enqueue(object :
                Callback<SearchRequestDTO> {
                override fun onResponse(
                    call: Call<SearchRequestDTO>,
                    response: Response<SearchRequestDTO>
                ) {
                    if (response.body()==null) {
                        Toast.makeText(context, "음식을 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        searchInfo = response.body()!!
                        Log.d("통신 성공", searchInfo.items.get(0).description.toString())
                    }
                }

                override fun onFailure(call: Call<SearchRequestDTO>, t: Throwable) {
                    Toast.makeText(context, "네트워크 상태를 확인해주세요", Toast.LENGTH_SHORT).show()
                    Log.d("통신 실패", t.message.toString())
                }
            })
    }
}