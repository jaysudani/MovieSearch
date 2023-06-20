package com.example.repository

import com.example.api.RetrofitInstance
import com.example.model.MovieList
import com.example.model.MovieDetailModel

class Repository {

    suspend fun getMovieList(name : String,page : String) : MovieList{
        val mp = mutableMapOf<String,String>()
        mp["s"]=name
        mp["page"]=page



        return RetrofitInstance.api.getMovieList(mp)
    }

    suspend fun getMovieDetail(id : String) : MovieDetailModel{
        return RetrofitInstance.api.getMovieDetail(id)
    }
}