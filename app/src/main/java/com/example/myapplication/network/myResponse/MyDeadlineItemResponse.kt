package com.example.myapplication.network.myResponse
import com.google.gson.annotations.SerializedName

class MyDeadlineItemResponse<T> (@SerializedName("data")
                         val data: T?) : MyDeadlineResponse()