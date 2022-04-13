package com.example.moviess

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("popular?api_key=d0f88cda76de892dbcbfa13076fc9006")
    fun getData():Call<PopularMovies>

}