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
    const val ID = "gnVo4vAboIZQYqGSCEXz"
    const val SECRET = "TqeFWa2Ffe"
    private var searchInfo: SearchRequestDTO? = null

    fun getRetrofit(search: String, context: Context) : SearchRequestDTO? {
        val retrofitClient = RetrofitClient

            retrofitClient.searchAPI.getSearchInfo(ID,SECRET,"20",search).enqueue(object :
                Callback<SearchRequestDTO> {
                override fun onResponse(
                    call: Call<SearchRequestDTO>,
                    response: Response<SearchRequestDTO>
                ) {
                    if (response.body()==null) {
                        Toast.makeText(context, "음식을 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        searchInfo = response.body()!!
                    }
                }

                override fun onFailure(call: Call<SearchRequestDTO>, t: Throwable) {
                    Toast.makeText(context, "네트워크 상태를 확인해주세요", Toast.LENGTH_SHORT).show()
                    Log.d("통신 실패", t.message.toString())
                    return
                }
            })
        try {
            Thread.sleep(1000)
        } catch (e:Exception) {
            e.printStackTrace()
        }

        return searchInfo
    }
}