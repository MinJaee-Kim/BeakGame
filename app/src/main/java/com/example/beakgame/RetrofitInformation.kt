package com.example.beakgame

import android.util.Log
import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetrofitInformation {
    private val id = "gnVo4vAboIZQYqGSCEXz"
    private val secret = "TqeFWa2Ffe"
    private lateinit var searchInfo:SearchRequestDTO

    fun getRetrofit(search: String){
        val retrofitClient = RetrofitClient

        retrofitClient.searchAPI.getSearchInfo(id,secret,"20",search).enqueue(object :
            Callback<SearchRequestDTO> {
            override fun onResponse(
                call: Call<SearchRequestDTO>,
                response: Response<SearchRequestDTO>
            ) {
                searchInfo = response.body()!!
                Log.d("통신 성공", searchInfo.items.get(0).description.toString())
            }

            override fun onFailure(call: Call<SearchRequestDTO>, t: Throwable) {
                Log.d("통신 실패", t.message.toString())
            }
        })
    }
}