package com.example.beakgame

import com.example.beakgame.dto.SearchRequestDTO

object RetrofitInformation {
    private const val ID = "gnVo4vAboIZQYqGSCEXz"
    private const val SECRET = "TqeFWa2Ffe"
    private var searchInfo: SearchRequestDTO? = null

    fun getRetrofit(search: String) : SearchRequestDTO? {
        val retrofitClient = RetrofitClient

        searchInfo = retrofitClient.searchAPI.getSearchInfo(ID,SECRET,"20",search).execute().body()

        return searchInfo
    }
}