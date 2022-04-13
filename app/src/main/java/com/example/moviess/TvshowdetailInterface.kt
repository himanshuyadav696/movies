package com.example.moviess

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvshowdetailInterface {
    @GET("3/tv/{movie_id}?api_key=d0f88cda76de892dbcbfa13076fc9006")
    fun getMyData5(@Path("movie_id") movie_id:Int?): Call<Tvshowsdetail>
}