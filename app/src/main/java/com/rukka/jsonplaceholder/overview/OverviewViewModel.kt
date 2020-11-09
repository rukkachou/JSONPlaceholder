package com.rukka.jsonplaceholder.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rukka.jsonplaceholder.networks.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class Status {
    LOADING,
    FAILED,
    DONE
}

class OverviewViewModel : ViewModel() {
    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com/photos/"
        const val maxDataNumber = 5000
        const val singleDataNumber = 8
    }

    private val _allData = MutableLiveData<List<Property>>()
    val allData : LiveData<List<Property>>
        get() = _allData

    private val _status = MutableLiveData<Status>()
    val status : LiveData<Status>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<Property>()
    val navigateToSelectedProperty: LiveData<Property>
        get() = _navigateToSelectedProperty

    init {
        _status.value = Status.LOADING
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            val list: MutableList<Property> = _allData.value?.toMutableList() ?: mutableListOf()
            val start = (_allData.value?.size ?: 0) + 1
            for (index in start until start + singleDataNumber) {
                if (index > maxDataNumber) break
                withContext(Dispatchers.IO) {
                    loadJsonDataWithUrl("$baseUrl$index")
                }.let {
                    list.add(it)
                }

                withContext(Dispatchers.IO) {
                    loadImageWithUrl(list[index-1].thumbnailImageUrl)
                }.let {
                    list[index-1].bitmap = it
                }
                _allData.value = list
            }
            _status.value = Status.DONE
        }
    }

    fun displayDetailProperty(property: Property) {
        _navigateToSelectedProperty.value = property
    }

    fun displayDetailPropertyCompleted() {
        _navigateToSelectedProperty.value = null
    }

    fun loadMore() {
        if (_status.value == Status.DONE) {
            _status.value = Status.LOADING
            refreshData()
        }
    }
}