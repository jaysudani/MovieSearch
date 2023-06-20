package com.example.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.Repository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    var myResponseOfMovieList : MutableLiveData<MovieList> = MutableLiveData()
    var myResponseOfMovieDetailView : MutableLiveData<MovieDetailModel> = MutableLiveData()

    fun getMovieList(name : String,page : String){
        viewModelScope.launch {
            val response = repository.getMovieList(name,page)
            myResponseOfMovieList.value = response
        }
    }

    fun getMovieDetail(id : String){
        viewModelScope.launch {
            val response = repository.getMovieDetail(id)
            myResponseOfMovieDetailView.value = response
        }
    }
}