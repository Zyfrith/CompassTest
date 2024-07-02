package com.example.compasstest.data.response

import com.google.gson.annotations.SerializedName

data class ImageResponse (
    @SerializedName("url")
    val image: String
)