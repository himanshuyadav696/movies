package com.example.moviess

import retrofit2.Call
import retrofit2.http.GET

interface TvApiInterface {
    @GET("popular?api_key=d0f88cda76de892dbcbfa13076fc9006")
    fun getMyData3():Call<TvShows>
}