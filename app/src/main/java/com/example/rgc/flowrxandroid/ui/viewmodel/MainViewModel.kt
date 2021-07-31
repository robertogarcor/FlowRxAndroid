package com.example.rgc.flowrxandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.rgc.flowrxandroid.data.domain.model.Movie
import com.example.rgc.flowrxandroid.data.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository : MovieRepositoryImpl) : ViewModel() {

    private val TAG = "MainViewModel_lastVisible"

    val movies : Flow<List<Movie>> get() = repository.getMovies()

    //val lastVisibleItem = MutableLiveData<Int>().apply { value = 0 }
    val lastVisibleItem : MutableStateFlow<Int> =  MutableStateFlow(0)

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                lastVisibleItem.collect { notifyLastVisible(it) }
            }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        Log.d(TAG, " : $lastVisible")
        repository.requiredNewData(lastVisible)
    }
}