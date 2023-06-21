package com.example.myapplication.listView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Deadline
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.network.deadline.DeadlineResponse
import com.example.myapplication.network.myResponse.MyDeadlineListResponse
import com.example.myapplication.network.myResponse.MyDeadlineResponse
import com.example.myapplication.utils.Constants
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val deadlinesLiveData: MutableLiveData<List<Deadline>> by lazy {
        MutableLiveData<List<Deadline>>()
    }

    init {
        getListOfDeadlineFromRemoteDb()
       // deleteAllDeadlines()
    }

    fun getListOfDeadlineFromRemoteDb() {
        viewModelScope.launch {
            try {
                val response: MyDeadlineListResponse<DeadlineResponse> =
                    RetrofitInstance.deadlineService.getAllDeadlines(Constants.STUDENT_ID)
                val deadlinesFromResponse = response.data

                if (deadlinesFromResponse != null) {
                    val myDeadlines = mutableListOf<Deadline>()

                    for (deadlinesFromResponse in deadlinesFromResponse) {
                        myDeadlines.add(
                            Deadline(
                                deadlinesFromResponse.id,
                                deadlinesFromResponse.title,
                                deadlinesFromResponse.date,
                                deadlinesFromResponse.info,
                                deadlinesFromResponse.steps
                            )
                        )
                    }

                    deadlinesLiveData.value = myDeadlines
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteAllDeadlines() {
        viewModelScope.launch {
            try {

                val response: MyDeadlineResponse =
                    RetrofitInstance.deadlineService.deleteAllDeadlines(
                        Constants.STUDENT_ID
                    )

                Log.d("Delete_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
