package com.example.beakgame.dto

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

public data class SearchRequestDTO(
    @SerializedName("items") var items : List<Items>,
    @SerializedName("display") var display : Int?)

data class Items(
    @SerializedName("title") var title : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("link") var link : String?)