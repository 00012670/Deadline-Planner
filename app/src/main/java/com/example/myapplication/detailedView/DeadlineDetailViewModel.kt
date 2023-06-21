package com.example.myapplication.detailedView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Deadline
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.network.deadline.DeadlineRequest
import com.example.myapplication.network.deadline.DeadlineResponse
import com.example.myapplication.network.myResponse.MyDeadlineItemResponse
import com.example.myapplication.network.myResponse.MyDeadlineResponse
import com.example.myapplication.utils.Constants
import kotlinx.coroutines.launch

class DetailedViewModel(deadlineId: String) : ViewModel() {

    val deadlineLiveData: MutableLiveData<Deadline> by lazy {
        MutableLiveData<Deadline>()
    }

    init {
  //    deleteOneDeadlineById(deadlineId)
//        editDeadlineById(
//            deadlineId,
//            DeadlineRequest(
//                "Edited title",
//                "Edited date",
//                "Edited info",
//                "Edited steps"
//            )
//        )
        getDeadlineByIdFromRemoteDb(deadlineId)
    }


    private fun getDeadlineByIdFromRemoteDb(deadlineId: String) {
        viewModelScope.launch {
            try {
                val response: MyDeadlineItemResponse<DeadlineResponse> =
                    RetrofitInstance.deadlineService.getOneDeadlineById(
                        deadlineId,
                        Constants.STUDENT_ID
                    )
                val deadlineFromResponse = response.data

                if (deadlineFromResponse != null) {
                    deadlineLiveData.value = Deadline(
                        deadlineFromResponse.id,
                        deadlineFromResponse.title,
                        deadlineFromResponse.date,
                        deadlineFromResponse.info,
                        deadlineFromResponse.steps,
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun editDeadlineById(deadlineId: String, deadlineRequest: DeadlineRequest) {
        viewModelScope.launch {
            try {

                val response: MyDeadlineResponse =
                    RetrofitInstance.deadlineService.updateOneDeadlineById(
                        deadlineId,
                        Constants.STUDENT_ID,
                        deadlineRequest
                    )

                Log.d("Update_response", response.message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun deleteOneDeadlineById(deadlineId: String) {
        viewModelScope.launch {
            try {

                val response: MyDeadlineResponse =
                    RetrofitInstance.deadlineService.deleteOneDeadlineById(
                        deadlineId,
                        Constants.STUDENT_ID
                    )

                Log.d("Delete_response", response.message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}