package com.example.myapplication.network.myResponse
import com.google.gson.annotations.SerializedName

class MyDeadlineListResponse<T> (@SerializedName("data")
                         val data: List<T>?) : MyDeadlineResponse()