package com.example.beakgame

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager
import com.example.beakgame.dto.SearchRequestDTO
import java.util.*

object Information {
    private const val ID = "gnVo4vAboIZQYqGSCEXz"
    private const val SECRET = "TqeFWa2Ffe"
    private var searchInfo: SearchRequestDTO? = null
    lateinit var myViewModel: MyViewModel
    var link: String? = null
    lateinit var result: String
    var overLabCheck: LinkedList<String> = LinkedList()
    var resultArray = arrayOf<String>("조보아씨 이리와바유", "아이 왜이러세유", "어우 미치겠다",
    "이거에유~", "이..이 맛은?", "진짜 맛있어유!")
    //todo
    var resultImgArray = arrayOf<Int>()

    fun getRetrofit(search: String) : SearchRequestDTO? {
        val retrofitClient = RetrofitClient

        searchInfo = retrofitClient.searchAPI.getSearchInfo(ID,SECRET,"20",search).execute().body()

        return searchInfo
    }
}