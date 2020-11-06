package com.rukka.jsonplaceholder.detail

import androidx.lifecycle.*
import com.rukka.jsonplaceholder.networks.Property

class DetailViewModel(property: Property) : ViewModel() {
    private val _selectedItem = MutableLiveData<Property>()
    val selectedItem: LiveData<Property>
        get() = _selectedItem

    val seletctedId = Transformations.map(_selectedItem) {
        "Id: " + it.id
    }

    val seletctedTitle = Transformations.map(_selectedItem) {
        "Title: " + it.title
    }

    init {
       _selectedItem.value = property
    }
}

class DetailViewModelFactory(private val property: Property) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(property) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}