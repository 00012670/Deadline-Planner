package com.example.myapplication.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.network.deadline.DeadlineRequest
import com.example.myapplication.network.myResponse.MyDeadlineResponse
import com.example.myapplication.utils.Constants
import kotlinx.coroutines.launch

class AddNewViewModel : ViewModel() {

    val deadlineInsertResponse: MutableLiveData<MyDeadlineResponse> by lazy {
        MutableLiveData<MyDeadlineResponse>()
    }

    fun saveNewDeadlineToRemoteDb(deadline: DeadlineRequest) {

        viewModelScope.launch {
            try {

                val response: MyDeadlineResponse =
                    RetrofitInstance.deadlineService.insertNewDeadline(
                        Constants.STUDENT_ID,
                        deadline,
                    )

                deadlineInsertResponse.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}