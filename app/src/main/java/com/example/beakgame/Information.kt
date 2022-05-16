package com.example.beakgame

import com.example.beakgame.dto.SearchRequestDTO
import java.util.*

object Information {
    private const val ID = "gnVo4vAboIZQYqGSCEXz"
    private const val SECRET = "TqeFWa2Ffe"
    private var searchInfo: SearchRequestDTO? = null
    lateinit var myViewModel: MyViewModel
    var overLabCheck: LinkedList<String> = LinkedList()

    fun getRetrofit(search: String) : SearchRequestDTO? {
        val retrofitClient = RetrofitClient

        searchInfo = retrofitClient.searchAPI.getSearchInfo(ID,SECRET,"20",search).execute().body()

        return searchInfo
    }
}