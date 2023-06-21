package com.example.myapplication.network.deadline

import com.google.gson.annotations.SerializedName

data class DeadlineRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val date: String,
    @SerializedName("description")
    val info: String,
    @SerializedName("color")
    val steps: String
)