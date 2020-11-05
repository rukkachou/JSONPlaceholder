package com.rukka.jsonplaceholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rukka.jsonplaceholder.networks.Api
import com.rukka.jsonplaceholder.networks.Property
import kotlinx.coroutines.launch

enum class Status {
    LOADING,
    FAILED,
    DONE
}

class OverviewViewModel : ViewModel() {
    private val _allData = MutableLiveData<List<Property>>()
    val allData : LiveData<List<Property>>
        get() = _allData

    private val _status = MutableLiveData<Status>()
    val status : LiveData<Status>
        get() = _status

    init {
        _status.value = Status.LOADING
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            try {
                _allData.value = Api.retrofitService.getAll()
                _status.value = Status.DONE
            } catch (e : Exception) {
                e.printStackTrace()
                _status.value = Status.FAILED
            }
        }
    }
}