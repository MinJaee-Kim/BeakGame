package com.minjaee.beakgame

import com.minjaee.beakgame.service.RetrofitService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchAPI: RetrofitService = retrofit.create(RetrofitService::class.java)
}