package com.example.api

import com.example.model.MovieList
import com.example.model.MovieDetailModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SimpleApi {

    @GET("?apikey=3581e2f1")
    suspend fun getMovieList(@QueryMap options : Map<String,String>) : MovieList


    @GET("?&apikey=3581e2f1")
    suspend fun getMovieDetail(@Query("i") id : String) : MovieDetailModel



}