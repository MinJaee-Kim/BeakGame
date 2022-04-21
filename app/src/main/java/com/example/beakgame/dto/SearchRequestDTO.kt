package com.example.beakgame.dto

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class SearchRequestDTO(
    @SerializedName("display") var display : Int?)

//data class Items(
//    @SerializedName("title") var title : String?,
//    @SerializedName("description") var description : String?,
//    @SerializedName("title") var link : String?)