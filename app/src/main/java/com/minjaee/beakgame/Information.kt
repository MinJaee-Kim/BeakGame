package com.minjaee.beakgame

import com.minjaee.beakgame.dto.SearchRequestDTO
import java.util.*

object Information {
    private const val ID = "gnVo4vAboIZQYqGSCEXz"
    private const val SECRET = "TqeFWa2Ffe"
    private var searchInfo: SearchRequestDTO? = null
    lateinit var myViewModel: MyViewModel
    var link: String? = null
    lateinit var result: String
    var overLabCheck: LinkedList<String> = LinkedList()
    var resultArray = arrayOf<String>("조보아씨 이리와바유", "아이 왜이러세유", "이거에유~",
    "어우 미치겠다", "이..이 맛은?", "진짜 맛있어유!")
    var resultImgArray = arrayOf<Int>(
        R.drawable.horrbeak,
        R.drawable.angrybeak,
        R.drawable.happybeak,
        R.drawable.happybeak2,
        R.drawable.happybeak3,
        R.drawable.happybeak4
    )

    fun getRetrofit(search: String) : SearchRequestDTO? {
        val retrofitClient = RetrofitClient

        searchInfo = RetrofitClient.searchAPI.getSearchInfo(ID, SECRET,"20",search).execute().body()

        return searchInfo
    }
}