package com.example.beakgame.service

import com.example.beakgame.dto.SearchRequestDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    private val Base_URL: String get() = "https://openapi.naver.com"

    @GET("/v1/search/blog")
    fun getSearchInfo(
        @Query("query") query: String
    ) : Call<SearchRequestDTO.Data>

}