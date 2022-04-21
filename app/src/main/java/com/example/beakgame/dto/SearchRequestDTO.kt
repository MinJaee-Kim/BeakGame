package com.example.beakgame.dto

import com.google.gson.annotations.SerializedName

data class SearchRequestDTO(
    @SerializedName("items") var items : String,
    @SerializedName("display") var display : Int
    ) {
    data class Data(
        @SerializedName("items") var items : String,
        @SerializedName("link") var link : String,
        @SerializedName("description") var description : String,
    )
}
