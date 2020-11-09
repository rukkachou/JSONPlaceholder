package com.rukka.jsonplaceholder.detail

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.rukka.jsonplaceholder.networks.Property
import com.rukka.jsonplaceholder.networks.loadImageWithUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(property: Property) : ViewModel() {
    private val _selectedItem = MutableLiveData<Property>()
    val selectedItem: LiveData<Property>
        get() = _selectedItem

    val selectedId = Transformations.map(_selectedItem) {
        "Id: " + it.id
    }

    val selectedTitle = Transformations.map(_selectedItem) {
        "Title: " + it.title
    }

    private val _selectedBitmap = MutableLiveData<Bitmap>()
    val selectedBitmap: LiveData<Bitmap>
        get() = _selectedBitmap

    init {
       _selectedItem.value = property
        setBitmap()
    }

    private fun setBitmap() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadImageWithUrl(selectedItem.value?.imageUrl)
            }.let {
                _selectedBitmap.value = it
            }
        }
    }
}

class DetailViewModelFactory(private val property: Property) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(property) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}