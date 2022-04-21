package com.example.beakgame.service

import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {
    @GET("v1/search/blog")
    fun getSearchInfo(
        @Header("X-Naver-Client-Id") id : String,
        @Header("X-Naver-Client-Secret") secret : String,
        @Query("display") display: String,
        @Query("query") query: String,
    ) : Call<SearchRequestDTO>

}