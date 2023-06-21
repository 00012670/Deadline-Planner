package com.example.myapplication.network

import com.example.myapplication.network.deadline.DeadlineRequest
import com.example.myapplication.network.deadline.DeadlineResponse
import com.example.myapplication.network.myResponse.MyDeadlineItemResponse
import com.example.myapplication.network.myResponse.MyDeadlineListResponse
import com.example.myapplication.network.myResponse.MyDeadlineResponse
import retrofit2.http.*

interface DeadlineService {
    @GET("records/all")
    suspend fun getAllDeadlines(
        @Query("student_id") student_id: String
    ): MyDeadlineListResponse<DeadlineResponse>

    @GET("records/{record_id}")
    suspend fun getOneDeadlineById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyDeadlineItemResponse<DeadlineResponse>

    @POST("records")
    suspend fun insertNewDeadline(
        @Query("student_id") student_id: String,
        @Body deadlineRequest: DeadlineRequest
    ): MyDeadlineResponse

    @PUT("records/{record_id}")
    suspend fun updateOneDeadlineById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body deadlineRequest: DeadlineRequest
    ): MyDeadlineResponse

    @DELETE("records/{record_id}")
    suspend fun deleteOneDeadlineById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyDeadlineResponse

    @DELETE("records/all")
    suspend fun deleteAllDeadlines(
        @Query("student_id") student_id: String
    ): MyDeadlineResponse
}